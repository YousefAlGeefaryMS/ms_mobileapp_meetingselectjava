package com.meetingselect.meetingselect.main.profile.Account.RegistrationMS4.VerificationProcess.EmailAddressVerificationFlow;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class EmailVerificationFlow extends Fragment {

    private static final String TAG = "EmailVerfFlow";

    private FirebaseAuth mAuth;

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
        return inflater.inflate(R.layout.fragment_email_verification_flow, container, false);
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

        mAuth = FirebaseAuth.getInstance();

        // Check the user is not logged in with firebase, if they are we sign them out.

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            FirebaseAuth.getInstance().signOut();
        }
        createFirebaseUser(emailInput, passwordInput);

    }

    // Create firebase user in-order to initiate verification
    private void createFirebaseUser(String email, String password) {

        mAuth = FirebaseAuth.getInstance();


        Log.d(TAG, "createFirebaseUser: " + email);
        Log.d(TAG, "createFirebaseUser: " + password);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
                            signInUser(email, password);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(requireActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



    }


    // Sign-in user into firebase
    private void signInUser(String email, String password) {
        Log.d(TAG, "signInUser: Success");

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            verifyUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(requireActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    // Verify user from email
    private void verifyUser() {
        Log.d(TAG, "verifyUser: Success");
        final FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {

            Log.d(TAG, "verifyUser: Success");

            user.sendEmailVerification()
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            // Re-enable button

                            if (task.isSuccessful()) {
                                Toast.makeText(requireActivity(),
                                        "Verification email sent to " + user.getEmail(),
                                        Toast.LENGTH_SHORT).show();

                                checkConstantlyForVerification();

                            } else {
                                Log.e(TAG, "sendEmailVerification", task.getException());
                                Toast.makeText(requireActivity(),
                                        "Failed to send verification email.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }


    // Checking constantly for verification for passive email verification
    private void checkConstantlyForVerification() {

        final FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            while(!user.isEmailVerified()) {

                mAuth.getCurrentUser().reload();

                if(user.isEmailVerified()) {
                    signInUserInMeetingSelect(firstNameInput, lastNameInput, companyInput, emailInput, passwordInput, countryInputCode, phoneInput);
                    break;
                }
            }
        }


    }

    // Register and sign in user into meetingselect database
    private void signInUserInMeetingSelect(String firstName, String lastName, String companyName, String email, String password, String countryInput, String phoneInput) {
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        String fullname = firstName + " " + lastName;

        Log.d(TAG, "signInUserInMeetingSelect: Success");

        mMainViewModel.UserRegistrationMVVM(fullname, companyName, email, password, countryInput, phoneInput);

        mDisposable = new CompositeDisposable();

        Disposable disposable = mMainViewModel.getmRegistrationResponseModelBehaviourSubject().subscribe(registerResponseModel -> {
           if(registerResponseModel.getStatusCode() == 200) {

               Toast.makeText(requireActivity(), "Meetingselect account created!", Toast.LENGTH_SHORT).show();
               mMainViewModel.saveLogState(requireActivity(), "true");
               mMainViewModel.saveEmailAddress(requireActivity(), email);

           } else {

               Toast.makeText(requireActivity(), registerResponseModel.getMessage() + "Signing In By Meetingselect failed", Toast.LENGTH_SHORT).show();

           }
            Navigation.findNavController(requireView()).navigate(R.id.action_emailVerificationFlow_to_searchHomepage);
            removeUser();
        });

        mDisposable.add(disposable);
    }

    // Remove user from firebase
    private void removeUser() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if(currentUser != null) {
            currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG,"User Deleted");

                    } else {
                        Log.w(TAG,"User not Deleted");
                    }
                }
            });
        }

    }


    // Checking constantly for verification and dispose of disposables
    @Override
    public void onResume() {
        super.onResume();

        checkConstantlyForVerification();

    }

    @Override
    public void onPause() {
        super.onPause();

        if(mDisposable != null) {
            mDisposable.dispose();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mDisposable != null) {
            mDisposable.dispose();
        }

        removeUser();
    }


}