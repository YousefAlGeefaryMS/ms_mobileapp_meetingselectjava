package com.meetingselect.meetingselect.main.profile.Account.RegistrationMS4.VerificationProcess;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.meetingselect.meetingselect.R;


public class VerifyRegistrationPage extends Fragment {

    private static final String TAG = "VerificationRouteFlow";

//    private String firstNameInput, lastNameInput, companyInput, emailInput, passwordInput, phoneInput, countryInput, countryInputCode;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_registration_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



            String firstNameInput = getArguments().get("firstNameInput").toString();
            String lastNameInput = getArguments().get("lastNameInput").toString();
            String companyInput = getArguments().get("companyInput").toString();
            String emailInput = getArguments().get("emailInput").toString();
            String passwordInput = getArguments().get("passwordInput").toString();
            String phoneInput = getArguments().get("phoneInput").toString();
            String countryInput = getArguments().get("countryname").toString();
            String countryInputCode = getArguments().get("countrycode").toString();




        Bundle bundle = new Bundle();
        bundle.putString("firstNameInput", firstNameInput);
        bundle.putString("lastNameInput", lastNameInput);
        bundle.putString("companyInput", companyInput);
        bundle.putString("emailInput", emailInput);
        bundle.putString("passwordInput", passwordInput);
        bundle.putString("phoneInput", phoneInput);
        bundle.putString("countryname", countryInput);
        bundle.putString("countrycode", countryInputCode);

        Button PhoneNumberVerificationFlow = view.findViewById(R.id.verification_phonenumberverification_button);
        Button EmailAddressVerificationFlow = view.findViewById(R.id.verification_emailaddressverification_button);

        PhoneNumberVerificationFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_verifyRegistrationPage_to_phoneNumberVerificationFlow, bundle);
            }
        });

        EmailAddressVerificationFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_verifyRegistrationPage_to_emailVerificationFlow, bundle);
            }
        });



    }
}