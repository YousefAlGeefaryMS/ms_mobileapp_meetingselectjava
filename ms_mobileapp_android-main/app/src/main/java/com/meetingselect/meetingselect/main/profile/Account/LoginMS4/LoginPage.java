package com.meetingselect.meetingselect.main.profile.Account.LoginMS4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.api.JSONAPIHolder;
import com.meetingselect.meetingselect.data.model.LoginModelMS4.LoginPostModel;
import com.meetingselect.meetingselect.data.model.LoginModelMS4.LoginResponseModel;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginPage extends Fragment {

    private static final String TAG = "LoginPage";

    private BottomNavigationView navBar;

    private CompositeDisposable mDisposable;

    private JSONAPIHolder jsonapiHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.GONE);

        TextView ConditionsPrivacyStatement = view.findViewById(R.id.profile_loginpageagreetoterms_textview);

        ConditionsPrivacyStatement.setMovementMethod(LinkMovementMethod.getInstance());

        EditText EmailAddressET = view.findViewById(R.id.profile_loginpage_emailaddress_editext);
        EditText PasswordET = view.findViewById(R.id.profile_loginpage_password_editext);

        Button LoginButton = view.findViewById(R.id.profile_login_button_loginpage);
        mDisposable = new CompositeDisposable();


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(completeValidation()) {
                    String emailInput = EmailAddressET.getText().toString().trim();
                    String passwordInput = PasswordET.getText().toString().trim();

//                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                    OkHttpClient client = new OkHttpClient.Builder()
//                            .addInterceptor(interceptor).build();

                    Retrofit retrofit = new Retrofit.Builder()
//                            .client(client)
                            .baseUrl("https://test-ms4-apigateway.azurewebsites.net/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    jsonapiHolder = retrofit.create(JSONAPIHolder.class);

                    LoginPostModel loginPostModel = new LoginPostModel(emailInput, passwordInput);

                    Call<LoginResponseModel> call = jsonapiHolder.getLoginUser(loginPostModel);

                    call.enqueue(new Callback<LoginResponseModel>() {
                        @Override
                        public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {


                            LoginResponseModel loginResponseModel = response.body();

                            Log.d(TAG, "onResponse: " + loginResponseModel.getMessage());


                            Log.d(TAG, "onResponse: Test");

                            MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

//                            mainViewModel.saveLogState(requireActivity(), "true");
                        }

                        @Override
                        public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                        }
                    });

                    Log.d(TAG, "EmailAddres: " + emailInput);
                    Log.d(TAG, "Password: " + passwordInput);
                }

            }
        });

    }

    private boolean completeValidation() {
        return basicLoginValidationEmail() && basicLoginValidationPassword();
    }


    private boolean basicLoginValidationEmail() {

        EditText EmailAddressET = requireView().findViewById(R.id.profile_loginpage_emailaddress_editext);

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

        EditText PasswordET = requireView().findViewById(R.id.profile_loginpage_password_editext);

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