package com.meetingselect.meetingselect.HelperClasses;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ValidateInputRegistration {


    private static final String TAG = "ValidateInput";

    private final Context mContext;

    private final EditText mFirstName;
    private final EditText mLastName;
    private final EditText mCompanyName;
    private final EditText mEmailInput;
    private final EditText mPassword;
    private EditText mCountry;
    private final EditText mPhoneNumber;

    public ValidateInputRegistration(Context mContext, EditText mFirstName, EditText mLastName, EditText mCompanyName, EditText mEmailInput, EditText mPassword, EditText mPhoneNumber) {
        this.mContext = mContext;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mCompanyName = mCompanyName;
        this.mEmailInput = mEmailInput;
        this.mPassword = mPassword;
        this.mPhoneNumber = mPhoneNumber;
    }

    public boolean CompleteValidation() {

        return validateNameCompany() && validateEmail() && validatePassword() && validatePhoneNumber();

    }


    private boolean validateNameCompany() {

        String firstNameInput = mFirstName.getText().toString().trim();
        String lastNameInput = mLastName.getText().toString().trim();
        String companyInput = mCompanyName.getText().toString().trim();

        if (firstNameInput.isEmpty()) {
            mFirstName.setError("This field must be filled.");
            return false;
        }
        if (lastNameInput.isEmpty()) {
            mLastName.setError("This field must be filled.");
            return false;
        }
        if (companyInput.isEmpty()) {
            mCompanyName.setError("This field must be filled.");
            return false;
        } else {
            return true;
        }

    }

    private boolean validateEmail() {

        String emailInput = mEmailInput.getText().toString().trim();

        if (emailInput.isEmpty()) {
            mEmailInput.setError("This field must be filled.");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mEmailInput.setError("Invalid Email Address.");
            return false;
        } else {
            return true;
        }

    }

    private boolean validatePassword() {

        String passwordInput = mPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            mPassword.setError("This field must be filled.");
            return false;
        } else if (passwordInput.length() < 8 || passwordInput.length() > 20) {
            mPassword.setError("Must have Min. 8 Char & Max. 20 Char");
            return false;
        } else return mCheckPasswordRequirements();

    }



    private boolean validatePhoneNumber() {

        String phoneInput = mPhoneNumber.getText().toString().trim();

        if(phoneInput.isEmpty()) {
            mPhoneNumber.setError("This field must be filled.");
            return false;
        } else if(!(Patterns.PHONE.matcher(phoneInput).matches())) {
            mPhoneNumber.setError("Invalid Phone Number.");
            return false;
        } else {
            return true;
        }


    }

    private boolean mCheckPasswordRequirements() {
        String str = mPassword.getText().toString().trim();
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean specialLetterFlag = false;

        for(int i=0;i < str.length();i++) {
            Pattern my_pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher my_match = my_pattern.matcher(str);
//            Log.d(TAG, "mCheckPasswordRequirements: " + str.length());

            ch = str.charAt(i);
            if(Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            }
            else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            } else if (my_match.find()) {
                specialLetterFlag = true;
            }


            if(numberFlag && capitalFlag && lowerCaseFlag && specialLetterFlag)
                return true;
        }

        if(!numberFlag) {
            mPassword.setError("Must have Min 1 Number");
        } else if(!lowerCaseFlag) {
            mPassword.setError("Must have Min 1 Lowercase");
        } else if (!capitalFlag) {
            mPassword.setError("Must have Min 1 Uppercase");
        } else if (!specialLetterFlag) {
            mPassword.setError("Must have Min 1 Special Letter");
        }

        return false;

    }


}
