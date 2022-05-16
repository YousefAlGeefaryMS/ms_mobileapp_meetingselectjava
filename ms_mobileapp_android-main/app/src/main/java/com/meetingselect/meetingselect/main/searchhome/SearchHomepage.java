package com.meetingselect.meetingselect.main.searchhome;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.LoginModelMS2.LoginResponseModelMS2;
import com.meetingselect.meetingselect.main.searchhome.AccountInformationRepo.AccountInformationMS2Repo;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchHomepage extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    // implements AdapterView.OnItemSelectedListener, View.OnClickListener
    // TAG
    private static final String TAG = "SearchHomepage";

    private String bookingstatus = "";

    private CompositeDisposable mDisposable;

    // Fragments
    // Search Venue Fragment


    public SearchHomepage() {
        // Required empty public constructor
        super(R.layout.fragment_search_venues_home_page);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_venues_home_page, container, false);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        String logState = mMainViewModel.loadLogState(requireActivity());
        mDisposable = new CompositeDisposable();

        if(!(logState.equals("false"))) {

            String mMS2UserEmail = mMainViewModel.loadUserEmail(requireActivity());
            String mMS2UserPassword = mMainViewModel.loadUserPassword(requireActivity());

            mMainViewModel.UserLoginMS2MVVM(mMS2UserEmail, mMS2UserPassword);

            mMainViewModel.getmLoginMS2ResponseModelBehaviourSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginResponseModelMS2>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull LoginResponseModelMS2 loginResponseModelMS2) {
                    Log.d(TAG, "onNext: " + loginResponseModelMS2.getToken());
                    mMainViewModel.saveUserToken(requireActivity(), loginResponseModelMS2.getToken());

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Toast.makeText(requireActivity(), "Login Failed, Try Again", Toast.LENGTH_SHORT).show();
                    mMainViewModel.saveLogState(requireActivity(), "false");
                }

                @Override
                public void onComplete() {
                }
            });

            String token = mMainViewModel.loadUserToken(requireActivity());

            mMainViewModel.getAccountInformationMS2(token);

            String email = mMainViewModel.loadUserEmail(requireActivity());
            String password = mMainViewModel.loadUserPassword(requireActivity());

            Log.d(TAG, "User Email - " + email);
            Log.d(TAG, "User Password - " + password);

            Log.d(TAG, "onViewCreated: ");

            Disposable disposable = mMainViewModel.getmAccountInformationMS2ResponseModelBehavioruSubject().subscribe(v -> {
                Log.d(TAG, "onViewCreated: " + v.getFirstName());

                TextView headText = view.findViewById(R.id.searchhomepage_wheredoyouwanttobookmeetingstringtext_textview);

                headText.setText("Welcome " + v.getFirstName() + ", Where are you booking your meeting or workspace today?");


            });

            mDisposable.add(disposable);
        }


        hideKeyboard(requireActivity());


        if(mMainViewModel.loadLogState(requireActivity()).equals("true")) {

            String mUserEmailAddress =  mMainViewModel.loadEmailAddress(requireActivity());
            mMainViewModel.AccountInformationMVVM(mUserEmailAddress);
            Disposable disposable = mMainViewModel.getmAccountInformationResponseModelBehaviourSubject().subscribe(accountInformationResponseModel -> {

                if(accountInformationResponseModel.getMessage().equals("Success")) {

                    String userFullname = accountInformationResponseModel.getData().getFullName();
                    String[] splittedUserFullname = userFullname.split("\\s+");
                    String userFirstName = splittedUserFullname[0];

                    TextView headText = view.findViewById(R.id.searchhomepage_wheredoyouwanttobookmeetingstringtext_textview);

                    headText.setText("Welcome " + userFirstName + ", Where are you booking your meeting or workspace today?");

                }

            });

            mDisposable.add(disposable);
        }
        mMainViewModel.loadLocationName(requireActivity());
//        mMainViewModel.init();

        BottomNavigationView navBar = getActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.VISIBLE);

        // Search Venues
        ConstraintLayout mSearchET = view.findViewById(R.id.searchhomepage_searchlocationlayout_constraintlayout);

        mSearchET.setOnClickListener(this);
        // Search Venue Init
        TextView mSearchKeywordTV = view.findViewById(R.id.searchhomepage_search_venue_keyword);
        String Location = mMainViewModel.loadLocationName(requireActivity());

        String[] LocationSeparated = Location.split(",");
        mSearchKeywordTV.setText(LocationSeparated[0]);

        // Add or Remove Attendees buttons
        // Guests Init
        ConstraintLayout mAttendeesNumberLayout = view.findViewById(R.id.searchhomepage_guestchooserlayout_constraintlayout);
        mAttendeesNumberLayout.setOnClickListener(this);

        // Spinner
        // Spinner Init
        Spinner mSpinner = view.findViewById(R.id.searchhomepage_typeofbooking_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.bookingType, R.layout.my_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        // DatePicker
        // DatePicker Init

        TextView mGuestNumber = view.findViewById(R.id.searchhomepage_attendeescount_textview);

        mGuestNumber.setText(mMainViewModel.loadGuest(requireActivity()));

        TextView mDatePickerTV = view.findViewById(R.id.searchhomepage_date_textview);
        ConstraintLayout mDatePicker = view.findViewById(R.id.searchhomepage_datechooserlayout_constraintlayout);
        mDatePicker.setOnClickListener(this);

        String mSharedPreferencesDate = mMainViewModel.loadDate(requireActivity());
        if(!mSharedPreferencesDate.equals("false")) {
            mDatePickerTV.setText(mSharedPreferencesDate);
        } else {

            // Date Configuration
            Calendar dateConfig = Calendar.getInstance();
            Date today = dateConfig.getTime();

            dateConfig.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = dateConfig.getTime();

            DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");

            String todayDate = dateFormat.format(today);
            String tomorrowDate = dateFormat.format(tomorrow);

            Log.d(TAG, "onViewCreated: " + todayDate);

            mDatePickerTV.setText(todayDate);

        }

//        mSpinner.setOnItemSelectedListener(this);
        // Testing

        if(haveNetworkConnection()) {
            mMainViewModel.SearchLocation((String) mSearchKeywordTV.getText());
            mMainViewModel.getSearchVenueLiveData().observe(getViewLifecycleOwner(), strings -> {


                if(!strings.isEmpty()) {
                    String lat = strings.get(0).getLat();
                    String lon = strings.get(0).getLon();
                    String latlon = lat + "," + lon;

                    mMainViewModel.saveLatLon(requireContext(), latlon);
                }


            });
        }
//
//
        // Search Button
        Button SearchButton = view.findViewById(R.id.search_button_searchhomepage);
        SearchButton.setOnClickListener(v -> {
            if("Search for the location of your Next Meeting".equals(mSearchKeywordTV.getText())) {
                Toast.makeText(requireContext(),"Search for a location first", Toast.LENGTH_SHORT).show();
            } else {
                Spinner mSpinner1 = requireView().findViewById(R.id.searchhomepage_typeofbooking_spinner);
                mSpinner1.getSelectedItem();
                Log.d(TAG, "onClick: " + mSpinner1.getSelectedItem());
                Log.d(TAG, "onClickTest: ");
                mMainViewModel.saveBookingType(requireActivity(), String.valueOf(mSpinner1.getSelectedItem()));

                Navigation.findNavController(view).navigate(R.id.action_searchHomepage_to_venueResults);
            }
        });
    }

    // OnClick Listeners


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sBookingType = parent.getItemAtPosition(position).toString();
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        Spinner mSpinner = requireView().findViewById(R.id.searchhomepage_typeofbooking_spinner);





        bookingstatus = sBookingType;
        Log.d(TAG, "onItemSelected: " + sBookingType);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {



    }


    public void onClick(View v) {
        int id = v.getId();

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        TextView mDatePickerTV = requireView().findViewById(R.id.searchhomepage_date_textview);

        if (id == R.id.searchhomepage_datechooserlayout_constraintlayout) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.clear();
            Log.d(TAG, "onClick: clicked");

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
                Log.d(TAG, "onClick: " + materialDatePicker.getHeaderText());
            });

        } else if (id == R.id.searchhomepage_searchlocationlayout_constraintlayout) {
            Log.d(TAG, "FragTransition: ");


            // After Code
            Navigation.findNavController(requireView()).navigate(R.id.action_searchHomepage_to_searchVenues);

        } else if (id == R.id.searchhomepage_guestchooserlayout_constraintlayout) {
            openNumberPicker();
        }
    }


    // Side Modules

    public void openNumberPicker() {
        //Inflating a LinearLayout dynamically to add TextInputLayout
        //This will be added in AlertDialog
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.view_number_dialog, null);
        NumberPicker numberpicker = (NumberPicker) linearLayout.findViewById(R.id.numberPicker1);
        TextView mAttendeesTextView = requireView().findViewById(R.id.searchhomepage_attendeescount_textview);

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

        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
    public void onResume() {
        super.onResume();

        if(isAdded()) {

            MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            Spinner mSpinner = requireView().findViewById(R.id.searchhomepage_typeofbooking_spinner);

            String BookingType = mMainViewModel.loadBookingType(requireActivity());

            if("Instant Booking".equals(BookingType)) {
                mSpinner.setSelection(2);
                Log.d(TAG, "onViewCreated: InstantBooking");
            } else if ("Request for Proposal".equals(BookingType)) {
                mSpinner.setSelection(1);
                Log.d(TAG, "onViewCreated: RFP");
            }
            else {
                mSpinner.setSelection(0);
                Log.d(TAG, "onViewCreated: Anything Else");
            }

            TextView mGuestNumber = requireView().findViewById(R.id.searchhomepage_attendeescount_textview);

            mGuestNumber.setText(mMainViewModel.loadGuest(requireActivity()));

            TextView mDatePickerTV = requireView().findViewById(R.id.searchhomepage_date_textview);

            String mSharedPreferencesDate = mMainViewModel.loadDate(requireActivity());
            if(!mSharedPreferencesDate.equals("false")) {
                mDatePickerTV.setText(mSharedPreferencesDate);
            } else {

                ConstraintLayout mDatePicker = requireView().findViewById(R.id.searchhomepage_datechooserlayout_constraintlayout);
                mDatePicker.setOnClickListener(this);

                // Date Configuration
                Calendar dateConfig = Calendar.getInstance();
                Date today = dateConfig.getTime();

                dateConfig.add(Calendar.DAY_OF_YEAR, 1);
                Date tomorrow = dateConfig.getTime();

                DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");

                String todayDate = dateFormat.format(today);

                mDatePickerTV.setText(todayDate);

            }
        }


    }
}