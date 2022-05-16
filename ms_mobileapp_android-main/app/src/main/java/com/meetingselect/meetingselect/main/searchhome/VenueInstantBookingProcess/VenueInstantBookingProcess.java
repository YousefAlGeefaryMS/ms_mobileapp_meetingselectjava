package com.meetingselect.meetingselect.main.searchhome.VenueInstantBookingProcess;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.meetingselect.meetingselect.HelperClasses.CustomScrollView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.CountryOrDistrict.CountryOrDistrictModel;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class VenueInstantBookingProcess extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "VenueInstntBkingProcess";

    private CompositeDisposable mDisposable;

    private final List<CountryOrDistrictModel> mListOfCountryDistricts = new ArrayList<>();
    private final ArrayList<String> CountryName = new ArrayList<>();
    private final ArrayList<String> CountryCode = new ArrayList<>();
    private String countryInput = "CountryName";
    private String countryInputCode = "CountryCode";
    private String CartKey = "";

    private String VenueAddress = "";
    private String StartTime = "";
    private String EndTime = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_venue_instant_booking_process, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Define all EditTexts

        try {

            int VenueLocationID = (int) getArguments().get("VenueLocationID");
            VenueAddress = (String) getArguments().get("VenueAddress");
            String BookingDate = (String) getArguments().get("BookingDate");
            StartTime = (String) getArguments().get("StartTime");
            EndTime = (String) getArguments().get("EndTime");
            int SearchID = (int) getArguments().get("RoomSearchID");
            int SpaceID = (int) getArguments().get("SpaceID");
            int CurrencyID = (int) getArguments().get("CurrencyID");
            int CRC = (int) getArguments().get("CRC");
            double Price = (double) getArguments().get("Price");
            String Hash = (String) getArguments().get("Hash");

            Log.d(TAG, "onViewCreated: " + VenueLocationID);
            Log.d(TAG, "onViewCreated: " + VenueAddress);
            Log.d(TAG, "onViewCreated: " + SearchID);
            Log.d(TAG, "onViewCreated: " + SpaceID);
            Log.d(TAG, "onViewCreated: " + CurrencyID);
            Log.d(TAG, "onViewCreated: " + CRC);
            Log.d(TAG, "onViewCreated: " + Price);
            Log.d(TAG, "onViewCreated: " + Hash);




            getAvailabilitySelect(VenueLocationID, SearchID, SpaceID, CurrencyID, CRC, Price, Hash, VenueAddress, BookingDate, StartTime, EndTime);





        } catch (NullPointerException e) {

            Log.d(TAG, "onViewCreated: ");
        }


        Button BookNow = view.findViewById(R.id.venueinstantbooking_booknowbutton_button);

        BookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: " + countryInputCode);
                Log.d(TAG, "onClick: " + countryInput);

                if(checkValidityOfInfo()) {
                    Bundle bundle = new Bundle();

                    EditText MeetingNameEditText = requireView().findViewById(R.id.venueinstantbooking_meetingname_edittext);
                    EditText FirstNameEditText = requireView().findViewById(R.id.venueinstantbooking_firstname_edittext);
                    EditText LastNameEditText = requireView().findViewById(R.id.venueinstantbooking_lastname_edittext);
                    EditText AddressLineOneEditText = requireView().findViewById(R.id.venueinstantbooking_addresslineone_edittext);
                    EditText AddressLineTwoEditText = requireView().findViewById(R.id.venueinstantbooking_addresslinetwo_edittext);
                    EditText ZipCodeEditText = requireView().findViewById(R.id.venueinstantbooking_zipcode_edittext);
                    EditText CityNameEditText = requireView().findViewById(R.id.venueinstantbooking_city_edittext);

                    TextView VenueAddressTextView = requireView().findViewById(R.id.venueinstantbooking_venueaddress_textview);
                    TextView BookingDateTextView = requireView().findViewById(R.id.venueinstantbooking_dateofbooking_textview);
                    TextView StartTimeTextView = requireView().findViewById(R.id.venueinstantbooking_starttime_textview);
                    TextView EndTimeTextView = requireView().findViewById(R.id.venueinstantbooking_endtime_textview);

                    String MeetingName = MeetingNameEditText.getText().toString().trim();
                    String FirstName = FirstNameEditText.getText().toString().trim();
                    String LastName = LastNameEditText.getText().toString().trim();
                    String AddressLineOne = AddressLineOneEditText.getText().toString().trim();
                    String AddressLineTwo = AddressLineTwoEditText.getText().toString().trim();
                    String Zipcode = ZipCodeEditText.getText().toString().trim();
                    String CityName = CityNameEditText.getText().toString().trim();
                    String BookingDate = BookingDateTextView.getText().toString().trim();



//                    Log.d(TAG, "getAvailabilitySelect: " + VenueAddress);
//                    Log.d(TAG, "getAvailabilitySelect: " + BookingDate);
//                    Log.d(TAG, "getAvailabilitySelect: " + StartTime);
//                    Log.d(TAG, "getAvailabilitySelect: " + EndTime);



                    bundle.putString("MeetingNameIB", MeetingName);
                    bundle.putString("FirstNameIB", FirstName);
                    bundle.putString("LastNameIB", LastName);
                    bundle.putString("AddressLineOneIB", AddressLineOne);
                    bundle.putString("AddressLineTwoIB", AddressLineTwo);
                    bundle.putString("ZipcodeIB", Zipcode);
                    bundle.putString("CityNameIB", CityName);
                    bundle.putString("CountryNameIB", countryInput);
                    bundle.putString("CountryCodeIB", countryInputCode);
                    bundle.putString("VenueAddressIB", VenueAddress);
                    bundle.putString("StartTimeIB", StartTime);
                    bundle.putString("EndTimeIB", EndTime);
                    bundle.putString("BookingDateIB", BookingDate);
                    bundle.putString("CartKey", CartKey);


                    Navigation.findNavController(requireView()).navigate(R.id.action_venueInstantBookingProcess_to_finishingConfirmingBooking, bundle);


                }

            }
        });

        readCSVFile();

        for(CountryOrDistrictModel post : mListOfCountryDistricts) {

            CountryName.add(post.getCountryName());
            CountryCode.add(post.getCountryCode());

        }

        CountryName.set(0, "Choose Country");

        Log.d(TAG, "onViewCreated: " + CountryName.contains("Afghanistan"));

//        if(CountryName.contains("Afghanistan")) {
//            int position = CountryName.indexOf("Afghanistan");
//            Log.d(TAG, "onViewCreated: " + CountryCode.get(position));
//        }

        Spinner mSpinner = view.findViewById(R.id.venueinstantbooking_country_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, CountryName);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(this);

        TextView VenueAddressTextView = requireView().findViewById(R.id.venueinstantbooking_venueaddress_textview);







    }

    private boolean checkValidityOfInfo() {

        EditText MeetingNameEditText = requireView().findViewById(R.id.venueinstantbooking_meetingname_edittext);
        EditText FirstNameEditText = requireView().findViewById(R.id.venueinstantbooking_firstname_edittext);
        EditText LastNameEditText = requireView().findViewById(R.id.venueinstantbooking_lastname_edittext);
        EditText AddressLineOneEditText = requireView().findViewById(R.id.venueinstantbooking_addresslineone_edittext);
        EditText AddressLineTwoEditText = requireView().findViewById(R.id.venueinstantbooking_addresslinetwo_edittext);
        EditText ZipCodeEditText = requireView().findViewById(R.id.venueinstantbooking_zipcode_edittext);
        EditText CityNameEditText = requireView().findViewById(R.id.venueinstantbooking_city_edittext);

        String MeetingName = MeetingNameEditText.getText().toString().trim();
        String FirstName = FirstNameEditText.getText().toString().trim();
        String LastName = LastNameEditText.getText().toString().trim();
        String AddressLineOne = AddressLineOneEditText.getText().toString().trim();
        String AddressLineTwo = AddressLineTwoEditText.getText().toString().trim();
        String Zipcode = ZipCodeEditText.getText().toString().trim();
        String CityName = CityNameEditText.getText().toString().trim();


        if(MeetingName.isEmpty()) {
            MeetingNameEditText.setError("This field is required.");
            return false;
        } else if(FirstName.isEmpty()) {
            FirstNameEditText.setError("This field is required.");
            return false;
        } else if(LastName.isEmpty()) {
            LastNameEditText.setError("This field is required.");
            return false;
        } else if(AddressLineOne.isEmpty()) {
            AddressLineOneEditText.setError("This field is required.");
            return false;
        } else if(Zipcode.isEmpty()) {
            ZipCodeEditText.setError("This field is required.");
            return false;
        } else if(CityName.isEmpty()) {
            CityNameEditText.setError("This field is required.");
            return false;
        } else {
            return true;
        }



    }

    private boolean getAvailabilitySelect(int VenueLocationID, int SearchId, int SpaceId, int CurrencyID, int CRC, double price, String hash, String VenueAddress, String BookingDate, String StartTime, String EndTime) {

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        ShapeableImageView VenueShapeableImageView = requireView().findViewById(R.id.venueinstantbooking_venueimage_shapeableimageview);
        TextView VenueNameTextView = requireView().findViewById(R.id.venueinstantbooking_venuename_textview);
        TextView VenueAddressTextView = requireView().findViewById(R.id.venueinstantbooking_venueaddress_textview);
        TextView BookingDateTextView = requireView().findViewById(R.id.venueinstantbooking_dateofbooking_textview);
        TextView StartTimeTextView = requireView().findViewById(R.id.venueinstantbooking_starttime_textview);
        TextView EndTimeTextView = requireView().findViewById(R.id.venueinstantbooking_endtime_textview);
        TextView RoomNameTextView = requireView().findViewById(R.id.venueinstantbooking_roomname_textview);
        TextView AttendeeNumberTextView = requireView().findViewById(R.id.venueinstantbooking_numberofattendees_textview);
        TextView PricePerSeatTextView = requireView().findViewById(R.id.venueinstantbooking_roomrental_textview);
        TextView TotalExclTaxTextView = requireView().findViewById(R.id.venueinstantbooking_totalexctax_textview);
        TextView TotalTax = requireView().findViewById(R.id.venueinstantbooking_totaltax_textview);
        TextView TotalInclTaxTextView = requireView().findViewById(R.id.venueinstantbooking_totalfeeinlcvat_textview);


        mDisposable = new CompositeDisposable();

        mainViewModel.AvailabilitySelect(VenueLocationID, SearchId, SpaceId, CurrencyID, CRC, price, hash);

        Disposable disposable = mainViewModel.getmVenueAvailabilityResponseModel().subscribe(venueAvailabilityResponseModel -> {
            Log.d(TAG, "getAvailabilitySelect: " +  venueAvailabilityResponseModel.getCartKey());

            Log.d(TAG, "getAvailabilitySelect: " + venueAvailabilityResponseModel.getLocationName());
            Log.d(TAG, "getAvailabilitySelect: " + venueAvailabilityResponseModel.getLocationImage());
            Log.d(TAG, "getAvailabilitySelect: " + VenueAddress);
            Log.d(TAG, "getAvailabilitySelect: " + BookingDate);
            Log.d(TAG, "getAvailabilitySelect: " + StartTime);
            Log.d(TAG, "getAvailabilitySelect: " + EndTime);

            Log.d(TAG, "getAvailabilitySelect: " + venueAvailabilityResponseModel.getSpaces().get(0).getSpaceName());
            Log.d(TAG, "getAvailabilitySelect: " + venueAvailabilityResponseModel.getSpaces().get(0).getSeats());
            Log.d(TAG, "getAvailabilitySelect: " + venueAvailabilityResponseModel.getSpaces().get(0).getPricePerSeat());
            Log.d(TAG, "getAvailabilitySelect: " + venueAvailabilityResponseModel.getTotalExclTax());
            Log.d(TAG, "getAvailabilitySelect: " + venueAvailabilityResponseModel.getTotalInclTax());
            Log.d(TAG, "getAvailabilitySelect: " + venueAvailabilityResponseModel.getTaxTotals().get(0).getPercentage() + "%");


            CartKey = venueAvailabilityResponseModel.getCartKey();

            Glide.with(requireActivity())
                    .asBitmap()
                    .load("https://az691754.vo.msecnd.net/website-staging/" +  venueAvailabilityResponseModel.getLocationId() + "/" + venueAvailabilityResponseModel.getSpaces().get(0).getSpaceImage())
                    .into(VenueShapeableImageView);

            VenueNameTextView.setText(venueAvailabilityResponseModel.getLocationName());
            VenueAddressTextView.setText(VenueAddress);
            BookingDateTextView.setText(BookingDate);
            StartTimeTextView.setText(StartTime);
            EndTimeTextView.setText(EndTime);
            RoomNameTextView.setText(venueAvailabilityResponseModel.getSpaces().get(0).getSpaceName());
            AttendeeNumberTextView.setText(String.valueOf(venueAvailabilityResponseModel.getSpaces().get(0).getSeats()));
            PricePerSeatTextView.setText("€" + venueAvailabilityResponseModel.getSpaces().get(0).getPricePerSeat());
            TotalExclTaxTextView.setText("€" + venueAvailabilityResponseModel.getTotalExclTax());
            TotalTax.setText(venueAvailabilityResponseModel.getTaxTotals().get(0).getPercentage() + "%");
            TotalInclTaxTextView.setText("€" + venueAvailabilityResponseModel.getTotalInclTax());



        });




        mDisposable.add(disposable);

        if(isAdded() && VenueAddressTextView.getText().toString().equals("Vijzelstraat 68-72, Amsterdam, Netherlands")) {

            CustomScrollView customScrollView = requireView().findViewById(R.id.venueinstantbooking_secondmainlayout_customscrollview);
            customScrollView.setVisibility(View.VISIBLE);
        }

        return true;


    }

    private void readCSVFile() {


        InputStream is = requireActivity().getResources().openRawResource(R.raw.country_or_district);
        BufferedReader reader = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        }

        String line = "";

        try {
            while( (line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                CountryOrDistrictModel sample = new CountryOrDistrictModel();

                sample.setCountryCode(tokens[1]);

                sample.setCountryName(tokens[2]);
                mListOfCountryDistricts.add(sample);

            }

        } catch (IOException e) {
            Log.wtf("Registration", "ErrorReadingFile" + line, e);
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {

            mDisposable.dispose();
        }

    }


    @Override
    public void onPause() {
        super.onPause();

        if (mDisposable != null) {

            mDisposable.dispose();
        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        countryInput = CountryName.get(position);
        countryInputCode = CountryCode.get(position);
        Log.d(TAG, "onItemSelected: " + CountryName.get(position));
        if(isAdded()) {

            CustomScrollView customScrollView = requireView().findViewById(R.id.venueinstantbooking_secondmainlayout_customscrollview);
            customScrollView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}