package com.meetingselect.meetingselect.main.searchhome.VenueResultsModule;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.meetingselect.meetingselect.HelperClasses.SpacingItemDecorator;
import com.meetingselect.meetingselect.R;

import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModel.VenueResultsInstantBookingResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel.VenueResultInstantBookingResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel.VenueResultsResponseModel;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class VenueResults extends Fragment implements VenueResultsAdapter.onVenueResultClicked, View.OnClickListener, AdapterView.OnItemSelectedListener, VenueResultsInstantBookingAdapter.onVenueResultInstantBookingClicked {
    private static final String TAG = "VenueResults";

    private final MutableLiveData<VenueResultsResponseModel> mVenueResultsResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<VenueResultsInstantBookingResponseModel> mVenueResultsInstantBookingResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<VenueResultInstantBookingResponseModel> mVenueResultsInstantBookingResponseMS4MutableLiveData = new MutableLiveData<>();

    private boolean mVenueResultsInstantBookingBoolean = true;
    private boolean mVenueResultsRequestForProposalBoolean = true;
    private boolean mNoResultsFlag = false;
    private CompositeDisposable mDisposable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_venue_results, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: FirstCalled");


        requireActivity();
        if (isAdded()) {

            final ConstraintLayout mRecyclerViewConstraintLayout = requireView().findViewById(R.id.venueresults_recyclerviewholder_constraintlayout);

            mRecyclerViewConstraintLayout.setVisibility(View.GONE);

            ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.venueresults_shimmerlayout_shimmerlayout);
            shimmerFrameLayout.startShimmer();


//            mRecyclerViewConstraintLayout.setVisibility(View.GONE);


            //    protected View mView;
            BottomNavigationView navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
            navBar.setVisibility(View.GONE);
            MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            TextView noresults_error = view.findViewById(R.id.noresults_venueresults);
            Button backToHomepage = view.findViewById(R.id.reload_button_venueresults);

//            TextView mGuestNumber = view.findViewById(R.id.searchhomepage_attendeescount_textview);
//            TextView mDate = view.findViewById(R.id.searchhomepage_date_textview);
//
//            mGuestNumber.setText(mMainViewModel.loadGuest(requireActivity()));
//            mDate.setText(mMainViewModel.loadDate(requireActivity()));

            noresults_error.setVisibility(View.INVISIBLE);
            backToHomepage.setVisibility(View.INVISIBLE);

            ConstraintLayout mSearchLayout = view.findViewById(R.id.venueresults_searchlocationlayout_constraintlayout);
            mSearchLayout.setOnClickListener(this);
            ConstraintLayout mAttendeesNumberLayout = view.findViewById(R.id.venueresults_guestchooserlayout_constraintlayout);
            mAttendeesNumberLayout.setOnClickListener(this);
            ConstraintLayout mDatePickerLayout = view.findViewById(R.id.venueresults_datechooserlayout_constraintlayout);
            mDatePickerLayout.setOnClickListener(this);

            TextView CityName = view.findViewById(R.id.venueresults_search_venue_keyword);

            CityName.setText(mMainViewModel.loadLocationName(requireActivity()));

            Log.d(TAG, "onViewCreated: " + mMainViewModel.loadLocationName(requireActivity()));

            Spinner mSpinner = view.findViewById(R.id.venueresults_typeofbooking_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.bookingType, R.layout.my_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(adapter);


            // ---------


            TextView mGuestNumber = requireView().findViewById(R.id.venueresults_attendeescount_textview);

            mGuestNumber.setText(mMainViewModel.loadGuest(requireActivity()));

            TextView mDatePickerTV = requireView().findViewById(R.id.venueresults_date_textview);

            String mSharedPreferencesDate = mMainViewModel.loadDate(requireActivity());
            if (!mSharedPreferencesDate.equals("false")) {
                Log.d(TAG, "onViewCreated: " + mSharedPreferencesDate);
                mDatePickerTV.setText(mSharedPreferencesDate);
            } else {

                ConstraintLayout mDatePicker = requireView().findViewById(R.id.venueresults_datechooserlayout_constraintlayout);
                mDatePicker.setOnClickListener(this);

                // Date Configuration
                Calendar dateConfig = Calendar.getInstance();
                Date today = dateConfig.getTime();
                DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.US);

                String todayDate = dateFormat.format(today);

                mDatePickerTV.setText(todayDate);


            }

            // ---------

            String IsInstantBookable;
            String BookingType = mMainViewModel.loadBookingType(requireActivity());


            if ("Instant Booking".equals(BookingType)) {
                mSpinner.setSelection(2);
                IsInstantBookable = "true";
            } else if ("Request for Proposal".equals(BookingType)) {
                IsInstantBookable = "null";
                mSpinner.setSelection(1);
            } else {
                mSpinner.setSelection(0);

                IsInstantBookable = "false";
            }

            mSpinner.setOnItemSelectedListener(this);


            callingVenueResultsMethods();


        }
    }


    private void getVenueResults() {


        onResults();
        requireActivity();
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

//        Log.d(TAG, "getVenueResults: " + mMainViewModel.loadLocationName(requireActivity()));
//        Log.d(TAG, "getVenueResults: " + mMainViewModel.loadBookingType(requireActivity()));

        String cityName = String.valueOf(mMainViewModel.loadLocationName(requireActivity()));
        mMainViewModel.SearchLocation(cityName);

        try {
            mMainViewModel.getSearchVenueLiveData().observe(getViewLifecycleOwner(), strings -> {


                if (strings.size() != 0) {
                    String lat = strings.get(0).getLat();
                    String lon = strings.get(0).getLon();
                    String latlon = lat + "," + lon;
                    Log.d(TAG, "getVenueResults: Called" + latlon);


                    mMainViewModel.VenueResultsMVVM(lat, lon, "false", "en");

                    mMainViewModel.saveLatLon(requireActivity(), latlon);

                    mDisposable = new CompositeDisposable();

                    mMainViewModel.getVenueResultsBehaviorSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<VenueResultsResponseModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mDisposable.add(d);
                        }

                        @Override
                        public void onNext(@NonNull VenueResultsResponseModel venueResultsResponseModel) {
                            if (venueResultsResponseModel.getTotal() == 0) {
                                onNoResults();
                            } else {
                                Log.d(TAG, "onNext: Called");
                                mVenueResultsResponseLiveData.setValue(venueResultsResponseModel);
                                initRecyclerViewVenueResults();
                            }

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: ", e);
                            onNoResults();

                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onComplete: ");

                        }
                    });
                }

            });

        } catch (IndexOutOfBoundsException | IllegalStateException e) {
            Log.e(TAG, "getVenueResults: ", e);
            onNoResults();
        }


    }

    // This is function 
    private void getVenueResultsInstantBookingMS4() {
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        String locationName = mMainViewModel.loadLocationName(requireActivity());

        mMainViewModel.getVenueResultsInstantBookingMVVM(locationName);

        mDisposable = new CompositeDisposable();

        TextView mDatePickerTV = requireView().findViewById(R.id.venueresults_date_textview);
        TextView mAttendeesTV = requireView().findViewById(R.id.venueresults_attendeescount_textview);


        String rawDateFromTextView = mDatePickerTV.getText().toString();
        String numberOfAttendees = mAttendeesTV.getText().toString();

        String[] splittedSelectedDate = rawDateFromTextView.split("\\s+");

        Log.d(TAG, "getVenueResultsInstantBookingMS4: " + splittedSelectedDate[2] + "-" + getMonthNumber(splittedSelectedDate[0]) + "-" + splittedSelectedDate[1] + " ");


        String submittedDate = splittedSelectedDate[2] + "-" + getMonthNumber(splittedSelectedDate[0]) + "-" + splittedSelectedDate[1];
        mMainViewModel.getVenueResultsInstantBookingMS4ViewModel(locationName, Integer.parseInt(numberOfAttendees), submittedDate, 1);

        mMainViewModel.getmVenueResultsInstantBookingBehaviourSubjectMS4().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<VenueResultInstantBookingResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull VenueResultInstantBookingResponseModel venueResultInstantBookingResponseModel) {

                if (venueResultInstantBookingResponseModel.getData().getSingleCityVenues().getTotalCount() != 0) {

                    mVenueResultsInstantBookingResponseMS4MutableLiveData.setValue(venueResultInstantBookingResponseModel);
                    initRecyclerViewVenueResultsInstantBooking();
                    Log.d(TAG, "onNext: " + venueResultInstantBookingResponseModel.getData().getSingleCityVenues().getItems().get(0).getName());
                } else {

                    onNoResults();
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: ", e);
                onNoResults();

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: InstantBooking");

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sBookingType = parent.getItemAtPosition(position).toString();
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mMainViewModel.saveBookingType(requireActivity(), sBookingType);


//        ProgressBar progressBar = requireView().findViewById(R.id.progress_bar_venueresults);
//
//        progressBar.setVisibility(View.VISIBLE);
        RecyclerView recyclerViewVenueResults = requireView().findViewById(R.id.venueresults_results_recyclerview);
        RecyclerView recyclerViewVenueResultsInstantBooking = requireView().findViewById(R.id.venueresults_instantbookablevenues_recyclerview);


        if (sBookingType.equals("Instant Booking")) {
//            getVenueResultsInstantBooking();
            recyclerViewVenueResultsInstantBooking.setVisibility(View.VISIBLE);
            recyclerViewVenueResults.setVisibility(View.GONE);
            Log.d(TAG, "onItemSelected: InstantBooking");
        } else if (sBookingType.equals("All Type of Bookings")) {
            recyclerViewVenueResultsInstantBooking.setVisibility(View.VISIBLE);
            recyclerViewVenueResults.setVisibility(View.VISIBLE);
            Log.d(TAG, "onItemSelected: Both");
        } else {
//            getVenueResults();
            recyclerViewVenueResultsInstantBooking.setVisibility(View.GONE);
            recyclerViewVenueResults.setVisibility(View.VISIBLE);
            Log.d(TAG, "onItemSelected: RFP");
        }


        Log.d(TAG, "onItemSelected: " + sBookingType);

//        constraintLayout.setVisibility(View.VISIBLE);

//        progressBar.setVisibility(View.INVISIBLE);


    }

    private void callingVenueResultsMethods() {

        if (isAdded()) {
            final ConstraintLayout mRecyclerViewConstraintLayout = requireView().findViewById(R.id.venueresults_recyclerviewholder_constraintlayout);

            mRecyclerViewConstraintLayout.setVisibility(View.GONE);

            ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.venueresults_shimmerlayout_shimmerlayout);
            shimmerFrameLayout.setVisibility(View.VISIBLE);
            shimmerFrameLayout.startShimmer();

            getVenueResultsInstantBookingMS4();
            getVenueResults();

            Log.d(TAG, "callingVenueResultsMethods: " + mVenueResultsInstantBookingBoolean);
            Log.d(TAG, "callingVenueResultsMethods: " + mVenueResultsRequestForProposalBoolean);

            Runnable myRunnable = () -> {
                while (mVenueResultsInstantBookingBoolean && mVenueResultsRequestForProposalBoolean) {
                    try {
                        Thread.sleep(1000); // Waits for 1 second (1000 milliseconds)

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mRecyclerViewConstraintLayout.post(() -> {
                        Log.d(TAG, "running: ");
                        // Shimmer
                    });

                }

                try {

                    Log.d(TAG, "run: End of Running");
                    Thread.sleep(5000); // Waits for 5 second (5000 milliseconds)

                    if (isAdded()) {
                        requireActivity().runOnUiThread(() -> {

                            if (!mNoResultsFlag) {
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);

//                                mRecyclerViewRFPVenueResults.setVisibility(View.VISIBLE);
//                                mRecyclerViewIBVenueResults.setVisibility(View.VISIBLE);

                                mRecyclerViewConstraintLayout.setVisibility(View.VISIBLE);
                            }

                        });
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            };

            Thread myThread = new Thread(myRunnable);
            myThread.start();

        }


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initRecyclerViewVenueResults() {

        if (isAdded()) {
            ConstraintLayout constraintLayout = requireView().findViewById(R.id.venueresults_recyclerviewholder_constraintlayout);

            RecyclerView recyclerViewVenueResults = requireView().findViewById(R.id.venueresults_results_recyclerview);
            VenueResultsAdapter adapter = new VenueResultsAdapter(getActivity(), this);
//            SpacingItemDecorator itemDecorator = new SpacingItemDecorator(5);
//            recyclerViewVenueResults.addItemDecoration(itemDecorator);
            recyclerViewVenueResults.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerViewVenueResults.setAdapter(adapter);


            adapter.updateData(mVenueResultsResponseLiveData);

            mVenueResultsRequestForProposalBoolean = false;


            recyclerViewVenueResults.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    recyclerViewVenueResults.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                }
            });

        }

    }

    private void initRecyclerViewVenueResultsInstantBooking() {
        if (isAdded()) {
            RecyclerView recyclerView = requireView().findViewById(R.id.venueresults_instantbookablevenues_recyclerview);
            VenueResultsInstantBookingAdapter adapter = new VenueResultsInstantBookingAdapter(requireActivity(), this);
//            SpacingItemDecorator itemDecorator = new SpacingItemDecorator(5);
//            recyclerView.addItemDecoration(itemDecorator);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerView.setAdapter(adapter);


            adapter.updateData(mVenueResultsInstantBookingResponseMS4MutableLiveData);

            mVenueResultsInstantBookingBoolean = false;

        }

    }

    public void onNoResults() {
        mNoResultsFlag = true;

        TextView noresults_error = requireView().findViewById(R.id.noresults_venueresults);
        Button backToHomepage = requireView().findViewById(R.id.reload_button_venueresults);

        ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.venueresults_shimmerlayout_shimmerlayout);

        noresults_error.setVisibility(View.VISIBLE);
        backToHomepage.setVisibility(View.VISIBLE);
        final ConstraintLayout mRecyclerViewConstraintLayout = requireView().findViewById(R.id.venueresults_recyclerviewholder_constraintlayout);
        mRecyclerViewConstraintLayout.setVisibility(View.GONE);
        shimmerFrameLayout.setVisibility(View.GONE);

        backToHomepage.setOnClickListener(v -> {

            Navigation.findNavController(requireView()).navigate(R.id.action_venueResults_to_searchHomepage);

        });
    }

    public void onResults() {

        TextView noresults_error = requireView().findViewById(R.id.noresults_venueresults);
        Button backToHomepage = requireView().findViewById(R.id.reload_button_venueresults);

        noresults_error.setVisibility(View.INVISIBLE);
        backToHomepage.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onViewCreated: ThirdCalled");

        if (isAdded()) {
            ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.venueresults_shimmerlayout_shimmerlayout);
            shimmerFrameLayout.startShimmer();
            shimmerFrameLayout.setVisibility(View.VISIBLE);

            MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            TextView mGuestNumber = requireView().findViewById(R.id.venueresults_attendeescount_textview);

            mGuestNumber.setText(mMainViewModel.loadGuest(requireActivity()));

            TextView mDatePickerTV = requireView().findViewById(R.id.venueresults_date_textview);

            String mSharedPreferencesDate = mMainViewModel.loadDate(requireActivity());
            if (!mSharedPreferencesDate.equals("false")) {
                mDatePickerTV.setText(mSharedPreferencesDate);
            } else {

                ConstraintLayout mDatePicker = requireView().findViewById(R.id.venueresults_datechooserlayout_constraintlayout);
                mDatePicker.setOnClickListener(this);

                // Date Configuration
                Calendar dateConfig = Calendar.getInstance();
                Date today = dateConfig.getTime();


                DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.US);

                String todayDate = dateFormat.format(today);

                mDatePickerTV.setText(todayDate);


            }

            callingVenueResultsMethods();
        }

    }


    @Override
    public void onVenueClicked(String venueid) {

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        Log.d(TAG, "onVenueClicked: " + venueid);
        mMainViewModel.saveVenueId(requireActivity(), venueid);

        Navigation.findNavController(requireView()).navigate(R.id.action_venueResults_to_venueDetails);

    }

    @Override
    public void onClick(View v) {

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        int id = v.getId();

        TextView mDatePickerTV = requireView().findViewById(R.id.venueresults_date_textview);


        if (id == R.id.venueresults_datechooserlayout_constraintlayout) {
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

                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("MMM dd yyyy", Locale.US);
                Date date = new Date(selection + offsetFromUTC);

                Log.d(TAG, "onClick: " + simpleFormat.format(date));

                mDatePickerTV.setText(simpleFormat.format(date));

                mMainViewModel.saveDate(requireActivity(), simpleFormat.format(date));

                callingVenueResultsMethods();
                Log.d(TAG, "onClick: " + materialDatePicker.getHeaderText());
            });
        } else if (id == R.id.venueresults_searchlocationlayout_constraintlayout) {
            Log.d(TAG, "FragTransition: ");

            // After Code

            Navigation.findNavController(requireView()).navigate(R.id.action_venueResults_to_searchVenues);


        } else if (id == R.id.venueresults_guestchooserlayout_constraintlayout) {
            openNumberPicker();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public void openNumberPicker() {
        //Inflating a LinearLayout dynamically to add TextInputLayout
        //This will be added in AlertDialog
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.view_number_dialog, null);
        NumberPicker numberpicker = (NumberPicker) linearLayout.findViewById(R.id.numberPicker1);
        TextView mAttendeesTextView = requireView().findViewById(R.id.venueresults_attendeescount_textview);

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

            callingVenueResultsMethods();

        });
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;


        ConnectivityManager cm = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public void onVenueResultInstantBookingClicked(String venueName, String venueGuid) {

        Bundle bundle = new Bundle();

        bundle.putString("VenueName", venueName);
        bundle.putString("VenueGuid", venueGuid);

        Log.d(TAG, "onVenueResultInstantBookingClicked: " + venueName);
        Log.d(TAG, "onVenueResultInstantBookingClicked: " + venueGuid);


        Navigation.findNavController(requireView()).navigate(R.id.action_venueResults_to_venueDetails, bundle);


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

    private String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

}