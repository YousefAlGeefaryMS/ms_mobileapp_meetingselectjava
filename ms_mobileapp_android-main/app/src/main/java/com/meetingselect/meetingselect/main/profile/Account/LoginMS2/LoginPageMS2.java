package com.meetingselect.meetingselect.main.profile.Account.LoginMS2;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.LoginModelMS2.LoginResponseModelMS2;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPageMS2 extends Fragment {

    private static final String TAG = "LoginPageMS2";

    private CompositeDisposable mDisposable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_page_m_s2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.GONE);

        Button loginButton = view.findViewById(R.id.loginpagems2_button_loginpage);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoginMechanismMS2();

            }
        });



    }


    private void mLoginMechanismMS2() {
        if(completeValidation()) {

            EditText emailAddressEditText = requireView().findViewById(R.id.loginpagems2_emailaddress_editext);
            EditText passwordEditText = requireView().findViewById(R.id.loginpagems2_password_editext);
            MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            String mEmailAddressInput = emailAddressEditText.getText().toString().trim();
            String mPasswordInput = passwordEditText.getText().toString().trim();

            mMainViewModel.UserLoginMS2MVVM(mEmailAddressInput, mPasswordInput);

            mMainViewModel.saveUserEmail(requireActivity(), mEmailAddressInput);

            Log.d(TAG, "mLoginMechanismMS2: " + mPasswordInput);
            mMainViewModel.saveUserPassword(requireActivity(), mPasswordInput);

            mDisposable = new CompositeDisposable();

            mMainViewModel.getmLoginMS2ResponseModelBehaviourSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginResponseModelMS2>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull LoginResponseModelMS2 loginResponseModelMS2) {
                    Log.d(TAG, "onNext: " + loginResponseModelMS2.getUserName());

                    mMainViewModel.saveUserToken(requireActivity(), loginResponseModelMS2.getToken());
                    mMainViewModel.saveLogState(requireActivity(), "true");
                    Navigation.findNavController(requireView()).navigate(R.id.action_loginPageMS2_to_searchHomepage);


                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d(TAG, "onError: " + e);
                    Toast.makeText(requireActivity(), "Login Failed, Try Again", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "onComplete: ");

                }
            });

        }

    }

    private boolean completeValidation() {
        return basicLoginValidationEmail() && basicLoginValidationPassword();
    }


    private boolean basicLoginValidationEmail() {

        EditText EmailAddressET = requireView().findViewById(R.id.loginpagems2_emailaddress_editext);

        String emailInput = EmailAddressET.getText().toString().trim();

        if(emailInput.isEmpty()) {
            EmailAddressET.setError("This field is required.");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            EmailAddressET.setError("Invalid Email Address.");
            return false;
        } else {
            return true;
        }

    }

    private boolean basicLoginValidationPassword() {

        EditText PasswordET = requireView().findViewById(R.id.loginpagems2_password_editext);

        String passwordInput = PasswordET.getText().toString().trim();

        if(passwordInput.isEmpty()) {
            PasswordET.setError("This field is required.");
            return false;
        } else {
            return true;
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
        mDisposable.dispose();
    }
}