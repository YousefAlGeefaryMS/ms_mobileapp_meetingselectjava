package com.meetingselect.meetingselect.main.profile.Account.RegistrationMS4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.meetingselect.meetingselect.HelperClasses.ValidateInputRegistration;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.CountryOrDistrict.CountryOrDistrictModel;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class RegistrationPage extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "RegistrationPage";

    private CompositeDisposable mDisposable;

    private BottomNavigationView navBar;

    private boolean mRepeatedEmailBoolean = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_page, container, false);
    }

    private final List<CountryOrDistrictModel> mListOfCountryDistricts = new ArrayList<>();
    private final ArrayList<String> CountryName = new ArrayList<>();
    private final ArrayList<String> CountryCode = new ArrayList<>();
    private String countryInput = "CountryName";
    private String countryInputCode = "CountryCode";


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.GONE);

        TextView ConditionsPrivacyStatement = view.findViewById(R.id.registration_registrationpageagreetoterms_textview);

        ConditionsPrivacyStatement.setMovementMethod(LinkMovementMethod.getInstance());

        EditText mFirstName = view.findViewById(R.id.registration_registrationpage_firstname_editext);
        EditText mLastName = view.findViewById(R.id.registration_registrationpage_lastname_editext);
        EditText mCompanyName = view.findViewById(R.id.registration_registrationpage_companyname_editext);
        EditText mEmail = view.findViewById(R.id.registration_registrationpage_emailaddress_editext);
        EditText mPassword = view.findViewById(R.id.registration_registrationpage_password_editext);
        EditText mPhoneNumber = view.findViewById(R.id.registration_registrationpage_phonenumber_editext);
        Button mRegister = view.findViewById(R.id.registration_registration_button_loginpage);


        ValidateInputRegistration validateInputRegistration = new ValidateInputRegistration(requireActivity(), mFirstName, mLastName, mCompanyName, mEmail, mPassword, mPhoneNumber);

        mDisposable = new CompositeDisposable();

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation.findNavController(view).navigate(R.id.action_registrationPage_to_verifyRegistrationPage);

//                validateInputRegistration.CompleteValidation();

                checkForNonRepeatedEmailAddress(mEmail.getText().toString(), mEmail);

                Log.d(TAG, "onClick: " + mEmail.getText().toString());
                Log.d(TAG, "onClick: " + mRepeatedEmailBoolean);

                if (validateInputRegistration.CompleteValidation() && checkForNonRepeatedEmailAddress(mEmail.toString(), mEmail)) {
                    if (!(countryInput.equals("Choose Country"))) {
//                        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
                        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

                        String firstNameInput = mFirstName.getText().toString().trim();
                        String lastNameInput = mLastName.getText().toString().trim();
                        String companyInput = mCompanyName.getText().toString().trim();
                        String emailInput = mEmail.getText().toString().trim();
                        String passwordInput = mPassword.getText().toString().trim();
                        String phoneInput = mPhoneNumber.getText().toString().trim();

                        String fullname = firstNameInput + " " + lastNameInput;
//                        mMainViewModel.UserRegistrationMVVM(fullname, companyInput, emailInput, passwordInput, countryInputCode, phoneInput);

                        Log.d(TAG, "Name: " + fullname);
                        Log.d(TAG, "Company Input: " + companyInput);
                        Log.d(TAG, "Email: " + emailInput);
                        Log.d(TAG, "Password: " + passwordInput);
                        Log.d(TAG, "Country Input Code: " + countryInputCode);
                        Log.d(TAG, "Phone Input: " + phoneInput);

                        Bundle bundle = new Bundle();
                        Log.d(TAG, "onClick: " + firstNameInput + " " + lastNameInput + " " + companyInput + " " + emailInput + " " + passwordInput + " " +
                                phoneInput + " " + companyInput + " " + countryInputCode);
                        bundle.putString("firstNameInput", firstNameInput);
                        bundle.putString("lastNameInput", lastNameInput);
                        bundle.putString("companyInput", companyInput);
                        bundle.putString("emailInput", emailInput);
                        bundle.putString("passwordInput", passwordInput);
                        bundle.putString("phoneInput", phoneInput);
                        bundle.putString("countryname", countryInput);
                        bundle.putString("countrycode", countryInputCode);

                        Navigation.findNavController(view).navigate(R.id.action_registrationPage_to_verifyRegistrationPage, bundle);

//                        Disposable disposable  = mMainViewModel.getmRegistrationResponseModelBehaviourSubject().subscribe(response -> {
//
//                            Log.d(TAG, "onClick: " + response.getData());
//                            Log.d(TAG, "onClick: " + response.getStatusCode());
//                            Log.d(TAG, "onClick: " + response.getMessage());
//
//
//                            if(response.getStatusCode() == 200) {
//                                Navigation.findNavController(view).navigate(R.id.action_registrationPage_to_searchHomepage);
//                            }
//
//                            Toast.makeText(requireActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        });

//                        mDisposable.add(disposable);

                    } else {
                        Toast.makeText(requireActivity(), "Must Choose Country.", Toast.LENGTH_SHORT).show();

                    }

                }
                Log.d(TAG, "onClick: " + countryInput);
                Log.d(TAG, "onClick: " + countryInputCode);
            }
        });

        readCSVFile();

        for (CountryOrDistrictModel post : mListOfCountryDistricts) {

            CountryName.add(post.getCountryName());
            CountryCode.add(post.getCountryCode());

        }

        CountryName.set(0, "Choose Country");

        Log.d(TAG, "onViewCreated: " + CountryName.contains("Afghanistan"));

//        if(CountryName.contains("Afghanistan")) {
//            int position = CountryName.indexOf("Afghanistan");
//            Log.d(TAG, "onViewCreated: " + CountryCode.get(position));
//        }

        Spinner mSpinner = view.findViewById(R.id.registration_registrationpage_country_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, CountryName);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(this);


    }

    private boolean checkForNonRepeatedEmailAddress(String emailAddress, EditText emailEditText) {

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);


        mDisposable = new CompositeDisposable();
//            String mUserEmailAddress =  mMainViewModel.loadEmailAddress(requireActivity());
        mMainViewModel.AccountInformationMVVM(emailAddress.trim());
        Disposable disposable = mMainViewModel.getmAccountInformationResponseModelBehaviourSubject().subscribe(accountInformationResponseModel -> {

            if (accountInformationResponseModel.getMessage().equals("Success")) {

//                    String userFullname = accountInformationResponseModel.getData().getFullName();
//                    String[] splittedUserFullname = userFullname.split("\\s+");
//                    String userFirstName = splittedUserFullname[0];

                Log.d(TAG, "checkForNonRepeatedEmailAddress: Repeated Email");
                emailEditText.setError("This email is already in use.");
                mRepeatedEmailBoolean = false;

            } else {

                Log.d(TAG, "checkForNonRepeatedEmailAddress: Non-RepeatedEmail");
                mRepeatedEmailBoolean = true;

            }
        });

        mDisposable.add(disposable);

        return mRepeatedEmailBoolean;
    }


    private void readCSVFile() {


        InputStream is = requireActivity().getResources().openRawResource(R.raw.country_or_district);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        countryInput = CountryName.get(position);
        countryInputCode = CountryCode.get(position);
        Log.d(TAG, "onItemSelected: " + CountryName.get(position));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    @Override
    public void onPause() {
        super.onPause();
        mDisposable.dispose();
    }
}