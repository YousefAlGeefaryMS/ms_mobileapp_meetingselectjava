package com.meetingselect.meetingselect.main.searchhome.VenueDetailsModule;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability.VenueDetailsResponseRoomsAvailableModel;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class VenueDetails extends Fragment implements OnMapReadyCallback, VenueDetailsAdapter.onRoomClicked, View.OnClickListener {

    // Unscrollable RecyclerView, Check ReadingList

    private static final String TAG = "VenueDetails";

    private String ExternalVenueID = "false";

    private String VenueNameArgument = "";
    private String VenueGUIDArgument = "";

    private int SpacePosition;

    // save externalvenueid in savedinstancestate and get it back before onresume ez

    private CompositeDisposable mDisposable;
    public static Double LongitudeVenueDetails;
    public static Double LatitudeVenueDetails;

    private MutableLiveData<VenueDetailsResponseRoomsAvailableModel> mVenueDetailsRoomsMutableLiveData = new MutableLiveData<>();



    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_venue_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            VenueNameArgument = String.valueOf(getArguments().get("VenueName"));
            VenueGUIDArgument = String.valueOf(getArguments().get("VenueGuid"));

            getVenueDetailsInstantBooking(VenueNameArgument, VenueGUIDArgument);
        } catch (NullPointerException e) {
            getVenueDetails();
        }

        Button AddToCartBookNow = view.findViewById(R.id.venues_addtocartbooknowbutton_venuedetails);

        AddToCartBookNow.setOnClickListener(this);

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        TextView mGuestNumber = requireView().findViewById(R.id.venuedetails_attendeescount_textview);

        ConstraintLayout mGuestPicker = requireView().findViewById(R.id.venuedetails_guestchooserlayout_constraintlayout);
        mGuestPicker.setOnClickListener(this);

        ConstraintLayout mStartTime = requireView().findViewById(R.id.venuedetails_starttimelayout_constraintlayout);
        mStartTime.setOnClickListener(this);

        ConstraintLayout mEndTime = requireView().findViewById(R.id.venuedetails_endtimelayout_constraintlayout);
        mEndTime.setOnClickListener(this);

        ConstraintLayout mDatePicker = requireView().findViewById(R.id.venuedetails_datechooserlayout_constraintlayout);
        mDatePicker.setOnClickListener(this);

        mGuestNumber.setText(mMainViewModel.loadGuest(requireActivity()));

        TextView mDatePickerTV = requireView().findViewById(R.id.venuedetails_date_textview);

        Spinner mSpinner = view.findViewById(R.id.venuedetails_orientation_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.orientation, R.layout.my_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);


        // Date Configuration
        Calendar dateConfig = Calendar.getInstance();
        Date today = dateConfig.getTime();

        dateConfig.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = dateConfig.getTime();

        DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");

        String todayDate = dateFormat.format(today);

        mDatePickerTV.setText(todayDate);

        // Layouts

    }


    private void getVenueDetails() {

        Log.d(TAG, "getVenueDetails: ");

        NestedScrollView scrollView = getView().findViewById(R.id.venues_childscrollview_venuedetails);
        ProgressBar progressBar = getView().findViewById(R.id.progress_bar_venuedetails);

        scrollView.setVisibility(View.GONE);


        // First Card

        // Name and Description
        TextView VenueNameDetails = getView().findViewById(R.id.venues_name_venuedetails);
        TextView VenueDescriptionDetails = getView().findViewById(R.id.venues_description_venuedetails);

        // RFP or Not
        ImageView InstantOrNotIMG = getView().findViewById(R.id.instantimg_venuedetails);
        TextView InstantOrNotTV = getView().findViewById(R.id.instantTV_venuedetails);

        // MeetingRooms

        // Button
        Button AddToCartBookNow = getView().findViewById(R.id.venues_addtocartbooknowbutton_venuedetails);

        // Location TV
        TextView VenueAddressDetails = getView().findViewById(R.id.venue_venueaddress_venuedetails);


        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        String VenueID = mainViewModel.loadVenueID(requireActivity());
        Log.d(TAG, "getVenueDetails: " + VenueID);
        mainViewModel.getVenueDetailsMVVM(VenueID);

        mDisposable = new CompositeDisposable();


        Disposable disposableDetails = mainViewModel.getVenueDetailsBehaviorSubject().subscribe(venueDetailsResponseModel -> {

            VenueNameDetails.setText(venueDetailsResponseModel.getVenueName());
            VenueDescriptionDetails.setText(Html.fromHtml(venueDetailsResponseModel.getDescription()).toString());
            VenueAddressDetails.setText(venueDetailsResponseModel.getAddress());

            Log.d(TAG, "getVenueDetails: " + venueDetailsResponseModel.getVenueName());
            LatitudeVenueDetails = venueDetailsResponseModel.getLatitude();
            LongitudeVenueDetails = venueDetailsResponseModel.getLongitude();

            progressBar.setVisibility(View.INVISIBLE);

            scrollView.setVisibility(View.VISIBLE);

        });

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.venues_venuemap_venuedetails);


        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        mDisposable.add(disposableDetails);

    }

    private void getVenueDetailsInstantBooking(String VenueName, String VenueGuid) {

        NestedScrollView scrollView = requireView().findViewById(R.id.venues_childscrollview_venuedetails);
        ProgressBar progressBar = requireView().findViewById(R.id.progress_bar_venuedetails);

        scrollView.setVisibility(View.GONE);

        // First Card

        // Name and Description
        TextView VenueNameDetails = getView().findViewById(R.id.venues_name_venuedetails);
        TextView VenueDescriptionDetails = getView().findViewById(R.id.venues_description_venuedetails);

        // RFP or Not
        ImageView InstantOrNotIMG = getView().findViewById(R.id.instantimg_venuedetails);
        TextView InstantOrNotTV = getView().findViewById(R.id.instantTV_venuedetails);

        // MeetingRooms

        // Button
        Button AddToCartBookNow = requireView().findViewById(R.id.venues_addtocartbooknowbutton_venuedetails);

        // Location TV
        TextView VenueAddressDetails = getView().findViewById(R.id.venue_venueaddress_venuedetails);


        Log.d(TAG, "getVenueDetailsInstantBooking: " + VenueName);
        Log.d(TAG, "getVenueDetailsInstantBooking: " + VenueGuid);

        mDisposable = new CompositeDisposable();

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mainViewModel.SearchLocation(String.valueOf(VenueName));

        mainViewModel.getSearchVenueLiveData().observe(getViewLifecycleOwner(), v -> {
            try {

                Log.d(TAG, "getVenueDetailsInstantBooking: " + v.get(0).getId());

                mainViewModel.getVenueDetailsMVVM(v.get(0).getId());

//                MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
                Log.d(TAG, "onVenueClicked: " + v.get(0).getId());
                mainViewModel.saveVenueId(requireActivity(), v.get(0).getId());


                Disposable venuedetailsdisposable = mainViewModel.getVenueDetailsBehaviorSubject().subscribe(venueDetailsResponseModel -> {

                    Log.d(TAG, "getVenueDetailsInstantBooking: " + venueDetailsResponseModel.getVenueName());
                    Log.d(TAG, "getVenueDetailsInstantBooking: " + venueDetailsResponseModel.getAddress());

                    VenueNameDetails.setText(venueDetailsResponseModel.getVenueName());
                    VenueDescriptionDetails.setText(Html.fromHtml(venueDetailsResponseModel.getDescription()).toString());
                    VenueAddressDetails.setText(venueDetailsResponseModel.getAddress());

                    Log.d(TAG, "getVenueDetails: " + venueDetailsResponseModel.getVenueName());
                    LatitudeVenueDetails = venueDetailsResponseModel.getLatitude();
                    LongitudeVenueDetails = venueDetailsResponseModel.getLongitude();
                    InstantOrNotIMG.setImageResource(R.drawable.ic_baseline_offline_bolt_24);
                    InstantOrNotTV.setText("Instant Booking");
                    AddToCartBookNow.setText("Book Now");




                    if (venueDetailsResponseModel.getExternalVenueID() != null) {

                        ExternalVenueID = String.valueOf(venueDetailsResponseModel.getExternalVenueID());
                        getVenueInstantBookingRooms();

                    }


                    progressBar.setVisibility(View.INVISIBLE);
                    scrollView.setVisibility(View.VISIBLE);

                });

                mDisposable.add(venuedetailsdisposable);

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                Log.e(TAG, "getVenueDetailsInstantBooking: ", e);
            }
        });


        // Map Manager
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.venues_venuemap_venuedetails);


        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
//        Log.d(TAG, "getVenueDetailsInstantBooking: " + VenueNewID);


    }

    private void getVenueInstantBookingRooms() {

        TextView NoResultsText = requireView().findViewById(R.id.venues_noresultstext_textview);

        TextView roomPriceIndicationTextView = requireView().findViewById(R.id.venues_meetingroomtotalprice_venuedetails);
        roomPriceIndicationTextView.setText("To be decided");

        NoResultsText.setVisibility(View.INVISIBLE);
        // Create separate function and ask it to include external venueid, we use this to generate new rooms without refreshing entire screen ez
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Log.d(TAG, "getVenueInstantBookingRoomsEVI: " + ExternalVenueID);


        // Get Selected Date and Give it to the API
        TextView mCurrentDate = requireView().findViewById(R.id.venuedetails_date_textview);
        String SelectedDate = String.valueOf(mCurrentDate.getText());
        String[] splittedSelectedDate = SelectedDate.split("\\s+");
        getMonthNumber(splittedSelectedDate[0]);
        String startEndDate = getMonthNumber(splittedSelectedDate[0]) + "-" + splittedSelectedDate[1].replaceAll(",", "") + "-" + splittedSelectedDate[2];
        Log.d(TAG, "getVenueInstantBookingRoomsStartEndDate: " + startEndDate);

        // Get Selected Guest Numbers
        TextView mGuestNumbers = requireView().findViewById(R.id.venuedetails_attendeescount_textview);
        String guestCount = String.valueOf(mGuestNumbers.getText());

        // Get Times
        TextView startTime = requireView().findViewById(R.id.venuedetails_starttime_textview);
        String[] startTimeSplittedString  = String.valueOf(startTime.getText()).split(":");
        int calculateHourStartTime = Integer.parseInt(startTimeSplittedString[0]) * 6;
        int calculateMinuteStartTime = Integer.parseInt(startTimeSplittedString[1]);
        int finalStartTime = (calculateHourStartTime + calculateMinuteStartTime) * 10;

        TextView endTime = requireView().findViewById(R.id.venuedetails_endtime_textview);
        String[] endTimeSplittedString  = String.valueOf(endTime.getText()).split(":");
        int calculateHourEndtime = Integer.parseInt(endTimeSplittedString[0]) * 6;
        Log.d(TAG, "getVenueInstantBookingRooms: " + calculateHourEndtime);
        int calculateMinuteEndTime = Integer.parseInt(endTimeSplittedString[1]);
        Log.d(TAG, "getVenueInstantBookingRooms: " + calculateMinuteEndTime);
        int finalEndTime = (calculateHourEndtime + calculateMinuteEndTime) * 10;

        Log.d(TAG, "getVenueInstantBookingRoomsTime: " + finalStartTime);
        Log.d(TAG, "getVenueInstantBookingRoomsTime: " + finalEndTime);

        if(!ExternalVenueID.equals("false")) {
            mainViewModel.VenueDetailsRoomMVVM(startEndDate, startEndDate, finalStartTime, finalEndTime, Integer.parseInt(ExternalVenueID), Integer.parseInt(guestCount), "en");

        }



        if(mainViewModel.getVenueDetailsRoomsBehaviorSubject().getValue() == null) {

            Log.d(TAG, "getVenueInstantBookingRoomsEmpty: ");
            initRecyclerViewVenueRooms();

        }

        Disposable venuedetailsroomsdisposable = mainViewModel.getVenueDetailsRoomsBehaviorSubject().subscribe(rooms -> {



            if(rooms.getLocations().size() != 0) {

                for(int i = 0; i < rooms.getLocations().get(0).getSpaces().size(); i++) {

                    Log.d(TAG, "getVenueInstantBookingRooms: -------------------------------");
                    Log.d(TAG, "getVenueInstantBookingRooms: " + rooms.getLocations().get(0).getSpaces().get(i).getSpaceName());
                    Log.d(TAG, "getVenueInstantBookingRooms: " + rooms.getLocations().get(0).getSpaces().get(i).getSpaceId());
                    Log.d(TAG, "getVenueInstantBookingRooms: " + rooms.getLocations().get(0).getSpaces().get(i).getCurrencyId());
                    Log.d(TAG, "getVenueInstantBookingRooms: " + rooms.getLocations().get(0).getSpaces().get(i).getCrc());
                    Log.d(TAG, "getVenueInstantBookingRooms: " + rooms.getLocations().get(0).getSpaces().get(i).getPrice());
                    Log.d(TAG, "getVenueInstantBookingRooms: " + rooms.getLocations().get(0).getSpaces().get(i).getHash());
                    Log.d(TAG, "getVenueInstantBookingRooms: " + rooms.getLocations().get(0).getSpaces().get(i).getSeats());
                    Log.d(TAG, "getVenueInstantBookingRooms: -------------------------------");

                    if(rooms.getLocations().get(0).getSpaces().get(i).getHash().isEmpty()) {

                        rooms.getLocations().get(0).getSpaces().remove(i);
                    }

                    mVenueDetailsRoomsMutableLiveData.setValue(rooms);

                    initRecyclerViewVenueRooms();
                }
            } else {


                mVenueDetailsRoomsMutableLiveData.setValue(null);

                initRecyclerViewVenueRooms();

                noRoomResults();
            }

//            Log.d(TAG, "getVenueInstantBookingRooms: " + rooms.get(0).getInternalName());
        });

        mDisposable.add(venuedetailsroomsdisposable);
    }

    private void noRoomResults() {

        TextView NoResultsText = requireView().findViewById(R.id.venues_noresultstext_textview);

        NoResultsText.setVisibility(View.VISIBLE);
    }

    private String getMonthNumber(String monthName) {

        Log.d(TAG, "getMonthNumber: " + monthName);
        Date date = new Date();
        try {
            date = new SimpleDateFormat("MMM").parse(monthName);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int monthNumber;
        monthNumber = cal.get(Calendar.MONTH) + 1;

        Log.d(TAG, "getMonthNumber: " + monthNumber);

        String finalMonthNumber = checkDigit(monthNumber);

        Log.d(TAG, "getMonthNumber: " + finalMonthNumber);


        return finalMonthNumber;

    }

    private void initRecyclerViewVenueRooms() {

        RecyclerView recyclerView = requireView().findViewById(R.id.venues_meetingroomsrv_venuedetails);
        VenueDetailsAdapter adapter = new VenueDetailsAdapter(getActivity(), this);
//        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(10);
//        recyclerView.addItemDecoration(itemDecorator);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        adapter.updateData(mVenueDetailsRoomsMutableLiveData);
    }

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        String VenueID = mainViewModel.loadVenueID(requireActivity());
        Log.d(TAG, "getVenueDetails: " + VenueID);
        mainViewModel.getVenueDetailsMVVM(VenueID);

        Disposable disposableMap = mainViewModel.getVenueDetailsBehaviorSubject().doOnComplete(() -> Log.d(TAG, "getVenueDetails: ")).subscribe(venueDetailsResponseModel -> {

            mMap = googleMap;


            LatLng location = new LatLng(venueDetailsResponseModel.getLatitude(), venueDetailsResponseModel.getLongitude());

            mMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title("Marker on Venue Location"));

            // Move the camera instantly to Sydney with a zoom of 15.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomIn());

            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

            // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(location)      // Sets the center of the map to Mountain View
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        });

        // Bug where map moves on scrollview, check reading list

        mDisposable.add(disposableMap);

    }

    @Override
    public void onRoomClicked(String TotalPrice, int spacePosition) {

        TextView totalPriceTV = getView().findViewById(R.id.venues_meetingroomtotalprice_venuedetails);
        totalPriceTV.setText(TotalPrice);

        SpacePosition = spacePosition;

        Log.d(TAG, "onRoomClicked: " + TotalPrice);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {

            mDisposable.dispose();
        }

        if (mMap != null) {

            mMap.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMap != null) {

            mMap.clear();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mDisposable != null) {

            mDisposable.dispose();
        }

        if (mMap != null) {

            mMap.clear();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        if (isAdded()) {

            MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            TextView mGuestNumber = requireView().findViewById(R.id.venuedetails_attendeescount_textview);

            mGuestNumber.setText(mMainViewModel.loadGuest(requireActivity()));

            TextView mDatePickerTV = requireView().findViewById(R.id.venuedetails_date_textview);

            Log.d(TAG, "onResume: This Works");
            // Date Configuration
            Calendar dateConfig = Calendar.getInstance();
            Date today = dateConfig.getTime();

            dateConfig.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = dateConfig.getTime();

            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

            String todayDate = dateFormat.format(today);

            mDatePickerTV.setText(todayDate);

            ConstraintLayout mGuestPicker = requireView().findViewById(R.id.venuedetails_guestchooserlayout_constraintlayout);
            mGuestPicker.setOnClickListener(this);

            ConstraintLayout mStartTime = requireView().findViewById(R.id.venuedetails_starttimelayout_constraintlayout);
            mStartTime.setOnClickListener(this);

            ConstraintLayout mEndTime = requireView().findViewById(R.id.venuedetails_endtimelayout_constraintlayout);
            mEndTime.setOnClickListener(this);

            ConstraintLayout mDatePicker = requireView().findViewById(R.id.venuedetails_datechooserlayout_constraintlayout);
            mDatePicker.setOnClickListener(this);

            getVenueDetailsInstantBooking(VenueNameArgument, VenueGUIDArgument);
//            getVenueInstantBookingRooms();


        }
    }

    @Override
    public void onClick(View v) {
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        int id = v.getId();

        TextView mDatePickerTV = requireView().findViewById(R.id.venuedetails_date_textview);

        if (id == R.id.venuedetails_datechooserlayout_constraintlayout) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.clear();

            long today = MaterialDatePicker.todayInUtcMilliseconds();

            calendar.setTimeInMillis(today);

            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            long january = calendar.getTimeInMillis();

            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            long december = calendar.getTimeInMillis();

            // Set only from year 2021 forward
            CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
            constraintBuilder.setValidator(DateValidatorPointForward.now());
            constraintBuilder.setStart(january);
//                constraintBuilder.setEnd(december);

            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
            builder.setTitleText("Select a date for your Booking");
            builder.setCalendarConstraints(constraintBuilder.build());
            final MaterialDatePicker<Long> materialDatePicker = builder.build();
            materialDatePicker.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER");


            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("MMM dd yyyy", Locale.US);
                Date date = new Date(selection + offsetFromUTC);

                Log.d(TAG, "onClick: " + simpleFormat.format(date));

                mDatePickerTV.setText(simpleFormat.format(date));

                mMainViewModel.saveDate(requireActivity(), simpleFormat.format(date));
                Log.d(TAG, "onClick: " + materialDatePicker.getHeaderText());
            });
        } else if (id == R.id.venuedetails_guestchooserlayout_constraintlayout) {
            openNumberPicker();
        } else if (id == R.id.venuedetails_starttimelayout_constraintlayout) {
            openTimePickerStartTime();
        } else if (id == R.id.venuedetails_endtimelayout_constraintlayout) {
            openTimePickerEndTime();
        } else if(id == R.id.venues_addtocartbooknowbutton_venuedetails) {
            // Go To InstantBooking page
            TextView roomPriceIndicationTextView = requireView().findViewById(R.id.venues_meetingroomtotalprice_venuedetails);
            String roomPriceIndication = roomPriceIndicationTextView.getText().toString();

            Log.d(TAG, "onClick: " + roomPriceIndication);

            if(roomPriceIndication == "To be decided") {

                Toast.makeText(requireActivity(), "Choose a Room first to proceed", Toast.LENGTH_SHORT).show();

            } else {

                Bundle bundle = new Bundle();

                TextView VenueAddressDetails = requireView().findViewById(R.id.venue_venueaddress_venuedetails);
                TextView mCurrentDate = requireView().findViewById(R.id.venuedetails_date_textview);
                TextView startTime = requireView().findViewById(R.id.venuedetails_starttime_textview);
                TextView endTime = requireView().findViewById(R.id.venuedetails_endtime_textview);


                Log.d(TAG, "onClick: " + SpacePosition);

                Log.d(TAG, "onVenueResultInstantBookingClickedBundle: " + mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getLocationId());
                Log.d(TAG, "onVenueResultInstantBookingClickedBundle: " + mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSearchId());
                Log.d(TAG, "onVenueResultInstantBookingClickedBundle: " + mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getSpaceId());
                Log.d(TAG, "onVenueResultInstantBookingClickedBundle: " + mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getCurrencyId());
                Log.d(TAG, "onVenueResultInstantBookingClickedBundle: " + mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getCrc());
                Log.d(TAG, "onVenueResultInstantBookingClickedBundle: " + mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getPrice());
                Log.d(TAG, "onVenueResultInstantBookingClickedBundle: " + mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getHash());

                bundle.putInt("VenueLocationID", mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getLocationId());
                bundle.putString("VenueAddress", String.valueOf(VenueAddressDetails.getText()));
                bundle.putString("BookingDate", String.valueOf(mCurrentDate.getText()));
                bundle.putString("StartTime", String.valueOf(startTime.getText()));
                bundle.putString("EndTime", String.valueOf(endTime.getText()));
                bundle.putInt("RoomSearchID", mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSearchId());
                bundle.putInt("SpaceID", mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getSpaceId());
                bundle.putInt("CurrencyID", mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getCurrencyId());
                bundle.putInt("CRC", mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getCrc());
                bundle.putDouble("Price", mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getPrice());
                bundle.putString("Hash", String.valueOf(mVenueDetailsRoomsMutableLiveData.getValue().getLocations().get(0).getSpaces().get(SpacePosition).getHash()));

                Navigation.findNavController(requireView()).navigate(R.id.action_venueDetails_to_venueInstantBookingProcess, bundle);

            }
        }

//        getVenueInstantBookingRooms();
    }

    private void openNumberPicker() {
        //Inflating a LinearLayout dynamically to add TextInputLayout
        //This will be added in AlertDialog
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.view_number_dialog, null);
        NumberPicker numberpicker = (NumberPicker) linearLayout.findViewById(R.id.numberPicker1);
        TextView mAttendeesTextView = requireView().findViewById(R.id.venuedetails_attendeescount_textview);

        numberpicker.setMinValue(1);
        numberpicker.setMaxValue(9999);
        numberpicker.setValue(Integer.parseInt(String.valueOf(mAttendeesTextView.getText())));
        //Finally building an AlertDialog
        final AlertDialog builder = new AlertDialog.Builder(requireActivity())
                .setPositiveButton("Submit", null)
                .setNegativeButton("Cancel", null)
                .setView(linearLayout)
                .setCancelable(true)
                .create();
        builder.show();
        //Setting up OnClickListener on positive button of AlertDialog


        builder.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {

            // Code on submit
            int pickedNumber = numberpicker.getValue();
            mAttendeesTextView.setText(String.valueOf(pickedNumber));

            mMainViewModel.saveGuest(requireActivity(), String.valueOf(pickedNumber));

            builder.dismiss();

//            getVenueDetailsInstantBooking(VenueNameArgument, VenueGUIDArgument);
            getVenueInstantBookingRooms();
            TextView totalPriceTV = requireView().findViewById(R.id.venues_meetingroomtotalprice_venuedetails);
            totalPriceTV.setText("To be decided");
            
        });


    }

    private void openTimePickerStartTime() {

        // ---
        TextView endTime = requireView().findViewById(R.id.venuedetails_endtime_textview);
        String endTimeRaw = String.valueOf(endTime.getText());
        String[] endTimeSplitted = endTimeRaw.split(":");
        // ---


        TextView startTime = requireView().findViewById(R.id.venuedetails_starttime_textview);
        String startTimeRaw = String.valueOf(startTime.getText());
        String[] splitted = startTimeRaw.split(":");
        TimePicker picker = (TimePicker) requireView().findViewById(R.id.venuedetails_timepicker_timepicker);
        picker.setVisibility(View.VISIBLE);
        picker.setIs24HourView(false);


        TimePickerDialog pickerDialog;
        pickerDialog = new TimePickerDialog(requireActivity(), R.style.CustomDatePickerDialog,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        tp.setIs24HourView(false);
                        if(!(Integer.parseInt(endTimeSplitted[0]) < sHour)){
                            startTime.setText(checkDigit(sHour) + ":" + checkDigit(sMinute));
//                            getVenueDetailsInstantBooking(VenueNameArgument, VenueGUIDArgument);
                            getVenueInstantBookingRooms();
                            TextView totalPriceTV = requireView().findViewById(R.id.venues_meetingroomtotalprice_venuedetails);
                            totalPriceTV.setText("To be decided");

                        } else {
                            Toast.makeText(requireContext(), "You cannot select Start Time before End Time", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), true);

        pickerDialog.show();




    }


    private void openTimePickerEndTime() {

        TextView startTime = requireView().findViewById(R.id.venuedetails_starttime_textview);

        String startTimeRaw = String.valueOf(startTime.getText());
        String[] splittedStartTime = startTimeRaw.split(":");

        TextView endTime = requireView().findViewById(R.id.venuedetails_endtime_textview);
        String endTimeRaw = String.valueOf(endTime.getText());
        String[] splitted = endTimeRaw.split(":");
        TimePicker picker = (TimePicker) requireView().findViewById(R.id.venuedetails_timepicker_timepicker);
        picker.setVisibility(View.VISIBLE);
        picker.setIs24HourView(true);


        TimePickerDialog pickerDialog;
        pickerDialog = new TimePickerDialog(requireActivity(), R.style.CustomDatePickerDialog,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        tp.setIs24HourView(false);
                        if(!(Integer.parseInt(splittedStartTime[0]) > sHour)){
                            endTime.setText(checkDigit(sHour) + ":" + checkDigit(sMinute));
//                            getVenueDetailsInstantBooking(VenueNameArgument, VenueGUIDArgument);
                            getVenueInstantBookingRooms();
                            TextView totalPriceTV = requireView().findViewById(R.id.venues_meetingroomtotalprice_venuedetails);
                            totalPriceTV.setText("To be decided");

                        } else {
                            Toast.makeText(requireContext(), "You cannot select End Time before Start Time", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), true);

        pickerDialog.show();

    }

    private String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }


}