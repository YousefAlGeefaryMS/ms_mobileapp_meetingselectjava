package com.meetingselect.meetingselect.main.profile.Account.RegistrationMS4.VerificationProcess.PhoneNumberVerificationFlow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.meetingselect.meetingselect.HelperClasses.OtpEditText;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class PhoneNumberVerificationFlow extends Fragment {

    private static final String TAG = "PhoneNumberVerf";

    String verificationCodeBySystem;

    private String mPhoneNumber = "";

    private String firstNameInput, lastNameInput, companyInput, emailInput, passwordInput, phoneInput, countryInput, countryInputCode;

    private CompositeDisposable mDisposable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_number_verification_flow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            firstNameInput = getArguments().get("firstNameInput").toString();
            lastNameInput = getArguments().get("lastNameInput").toString();
            companyInput = getArguments().get("companyInput").toString();
            emailInput = getArguments().get("emailInput").toString();
            passwordInput = getArguments().get("passwordInput").toString();
            phoneInput = getArguments().get("phoneInput").toString();
            countryInput = getArguments().get("countryname").toString();
            countryInputCode = getArguments().get("countrycode").toString();

            Button mVerifyButton = view.findViewById(R.id.verificationphonenumber_phonenumberverification_button);

            OtpEditText mOTPEditText = view.findViewById(R.id.verificationphonenumber_otpeditext_edittext);

            // Send verification number, if the sim-card that is related to the number is installed in the phone
            // No Need for OTP code that is instant Login.

            sendVerificationNumberToUser(phoneInput);

            // Check Edittext that it is not null & to check the value of the OTP

            mVerifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String mOTPInput = mOTPEditText.getText().toString();


                    if (mOTPInput.isEmpty() || mOTPInput.length() < 6) {
                        mOTPEditText.setError("Wrong OTP Code");
//                    mOTPCode.requestFocus();

                    } else {
                        verifyCode(mOTPInput);


                    }

                }
            });



    }

    // Sending the verfication number to the user

    // first function sets the settings of the otp.
    // Phone Number, Timeout, Which activity it relates to and the callbacks to check on the status of the verification.

    private void sendVerificationNumberToUser(String mPhoneNumber) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder()
                .setPhoneNumber("+31" + mPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(mCallbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationCodeBySystem = s;
            Log.d(TAG, "onVerificationCompleted: Sent");


        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                verifyCode(code);
                Log.d(TAG, "onVerificationCompleted: Sent");
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(requireActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // Verify OTP-code sent by SMS

    private void verifyCode(String verificationCode) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, verificationCode);

        signInUserByCredential(credential);
    }

    // Sign-in user into Firebase database for deletion

    private void signInUserByCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show();
                            RegisterUser();
//                            Navigation.findNavController(requireView()).navigate(R.id.action_phoneNumberVerificationFlow_to_searchHomepage);

                        } else {

                            Toast.makeText(requireActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    // Register user in the Meetingselect database

    private void RegisterUser() {

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        String fullname = firstNameInput + " " + lastNameInput;

        mMainViewModel.UserRegistrationMVVM(fullname, companyInput, emailInput, passwordInput, countryInputCode, phoneInput);
        mDisposable = new CompositeDisposable();
        Disposable disposable = mMainViewModel.getmRegistrationResponseModelBehaviourSubject().subscribe(registerResponseModel -> {

            if (registerResponseModel.getStatusCode() == 200) {

                Toast.makeText(requireActivity(), registerResponseModel.getMessage(), Toast.LENGTH_LONG).show();
                mMainViewModel.saveLogState(requireActivity(), "true");
                mMainViewModel.saveEmailAddress(requireActivity(), emailInput);
                goToSearchHomepage();

            } else {

                Toast.makeText(requireActivity(), registerResponseModel.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        mDisposable.add(disposable);
    }

    // Function to redirect towards Homepage

    private void goToSearchHomepage() {
        Navigation.findNavController(requireView()).navigate(R.id.action_phoneNumberVerificationFlow_to_searchHomepage);


    }

    // Disposable to dispose of anything that is left behind in this fragment

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mDisposable != null) {

            mDisposable.dispose();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mDisposable != null) {

            mDisposable.dispose();
        }
    }
}