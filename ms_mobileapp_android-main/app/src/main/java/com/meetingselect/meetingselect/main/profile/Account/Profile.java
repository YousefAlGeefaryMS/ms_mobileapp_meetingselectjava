package com.meetingselect.meetingselect.main.profile.Account;

import android.content.Intent;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.meetingselect.meetingselect.HelperClasses.SpacingItemDecorator;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.main.profile.Account.LoginMS2.LoginPageMS2;
import com.meetingselect.meetingselect.main.profile.options.ProfileAdapter;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class Profile extends Fragment implements ProfileAdapter.onOptionClicked{

    private static final String TAG = "Profile";
    private BottomNavigationView navBar;
    ArrayList<String> OptionsList = new ArrayList<>();

    private CompositeDisposable mDisposable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.VISIBLE);

//        CheckLogState();
        OptionsList.clear();

        OptionsList.add("Settings");
        OptionsList.add("List your Hotel or Venue");
        OptionsList.add("Give us your feedback");
        OptionsList.add("General Conditions");
        OptionsList.add("Privacy Statement");
        OptionsList.add("Disclaimer");
        OptionsList.add("Contact MeetingSelect");



        mDisposable = new CompositeDisposable();
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        String logState = mMainViewModel.loadLogState(requireActivity());
        if(!(logState.equals("false"))) {
            ConstraintLayout constraintLayout = view.findViewById(R.id.profile_loginregisterforplatformscontainer_constraintlayout);
            constraintLayout.setVisibility(View.GONE);
            TextView mLoggedInText = view.findViewById(R.id.profile_hellouser_notloggedin_string);

            String token = mMainViewModel.loadUserToken(requireActivity());
            mMainViewModel.getAccountInformationMS2(token);

            Disposable disposable = mMainViewModel.getmAccountInformationMS2ResponseModelBehavioruSubject().subscribe(v -> {
                Log.d(TAG, "onViewCreated: " + v.getFirstName());
                mLoggedInText.setText("Welcome, " + v.getFirstName() + "!");

            });

            mDisposable.add(disposable);
            OptionsList.add("Manage Profile");
            OptionsList.add("Log Out");
        }


        Button LogInButtonMS4 = view.findViewById(R.id.profile_loginms4_button);
        Button RegisterButtonMS4 = view.findViewById(R.id.profile_registerms4_button);

        Button LogInButtonMS2 = view.findViewById(R.id.profile_loginms2_button);

        LogInButtonMS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profile_to_loginPageMS2);
            }
        });

        RegisterButtonMS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profile_to_registrationPage);
            }
        });

        LogInButtonMS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profile_to_loginPage);
            }
        });



        RecyclerView recyclerView = view.findViewById(R.id.profile_options_recyclerview);
        ProfileAdapter adapter = new ProfileAdapter(requireActivity(), this);
        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
        recyclerView.addItemDecoration(spacingItemDecorator);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);
        adapter.updateData(OptionsList);
        
    }

    private String CheckLogState() {
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        String LogState = mainViewModel.loadLogState(requireActivity());

        Log.d(TAG, "CheckLogState: " + LogState);

        return LogState;
    }

    @Override
    public void onOptionClicked(String option) {

        Log.d(TAG, "onOptionClicked: " + option);
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        switch(option) {

            case "Manage Profile":
                Navigation.findNavController(requireView()).navigate(R.id.action_profile_to_manageProfile);
                break;
            case "Settings":
                Navigation.findNavController(requireView()).navigate(R.id.action_profile_to_settings);
                break;
            case "List your Hotel or Venue":
                Intent ListHotelOrVenue = new Intent( Intent.ACTION_VIEW , Uri.parse("https://www.meetingselect.com/en/contact?#sectionGetInTouch"));
                startActivity(ListHotelOrVenue);
                break;
            case "Give us your feedback":
                Intent Feedback = new Intent( Intent.ACTION_VIEW , Uri.parse("https://www.meetingselect.com/en/Send-us-your-feedback"));
                startActivity(Feedback);
                break;
            case "General Conditions":
                Intent GeneralConditions = new Intent( Intent.ACTION_VIEW , Uri.parse("https://www.meetingselect.com/en/General-Conditions"));
                startActivity(GeneralConditions);
                break;
            case "Privacy Statement":
                Intent PrivacyStatement = new Intent( Intent.ACTION_VIEW , Uri.parse("https://www.meetingselect.com/en/Privacy-Policy"));
                startActivity(PrivacyStatement);
                break;
            case "Disclaimer":
                Intent Disclaimer = new Intent( Intent.ACTION_VIEW , Uri.parse("https://www.meetingselect.com/en/Disclaimer"));
                startActivity(Disclaimer);
                break;
            case "Contact MeetingSelect":
                Intent ContactMeetingSelect = new Intent( Intent.ACTION_VIEW , Uri.parse("https://www.meetingselect.com/en/contact"));
                startActivity(ContactMeetingSelect);
                break;
            case "Log Out":
                mMainViewModel.saveLogState(requireActivity(), "false");
                mMainViewModel.saveEmailAddress(requireActivity(), "");
                ConstraintLayout constraintLayout = requireView().findViewById(R.id.profile_loginregisterforplatformscontainer_constraintlayout);
                constraintLayout.setVisibility(View.VISIBLE);
                TextView mLoggedInText = requireView().findViewById(R.id.profile_hellouser_notloggedin_string);
                mLoggedInText.setText("You are not Logged in");
                OptionsList.remove("Manage Profile");
                OptionsList.remove("Log Out");

                RecyclerView recyclerView = requireView().findViewById(R.id.profile_options_recyclerview);
                ProfileAdapter adapter = new ProfileAdapter(requireActivity(), this);
                SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
                recyclerView.addItemDecoration(spacingItemDecorator);

                recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
                recyclerView.setAdapter(adapter);
                adapter.updateData(OptionsList);
                break;

        }

    }
}