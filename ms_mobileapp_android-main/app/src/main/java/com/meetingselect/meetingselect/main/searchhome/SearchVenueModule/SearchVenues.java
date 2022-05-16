package com.meetingselect.meetingselect.main.searchhome.SearchVenueModule;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.rxbinding2.widget.RxSearchView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.Data;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.LocationSearchResponseModel;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.SearchLocationModel;
import com.meetingselect.meetingselect.main.searchhome.SearchHomepage;
import com.meetingselect.meetingselect.main.searchhome.SearchVenueModule.repository.LocationSearchRepo;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SearchVenues extends Fragment implements SearchVenueAdapter.OnItemClickedListener {

    private static final String TAG = "SearchVenues";


    private BottomNavigationView navBar;
    private CompositeDisposable mDisposable;

    // MutableLiveData for the Adapter
    private final MutableLiveData<List<String>> mSearchResultsLocation = new MutableLiveData<>(Collections.emptyList());
    private final MutableLiveData<List<String>> mSearchHistory = new MutableLiveData<>(Collections.emptyList());

//    private final MutableLiveData<LocationSearchResponseModel> mLocationSearchMutableLiveData = new MutableLiveData<>();

    public SearchVenues() {
        super(R.layout.fragment_search_venues);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_venues, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView results = view.findViewById(R.id.SearchResults_RecentSearches);
        results.setVisibility(View.INVISIBLE);

        mDisposable = new CompositeDisposable();
        SearchView mSearchVenuesSV = view.findViewById(R.id.search_view_searchvenues);
        mDisposable.add(
                RxSearchView.queryTextChanges(mSearchVenuesSV)
//                .filter(text -> text.length() >= 3)
                        .debounce(350, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::getSearchLocationResults));

        navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.GONE);

        ProgressBar pb = view.findViewById(R.id.progress_bar_searchvenues);
//        pb.setVisibility(View.INVISIBLE);


        int searchPlateId = mSearchVenuesSV.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        // Getting the 'search_plate' LinearLayout.
        View searchPlate = mSearchVenuesSV.findViewById(searchPlateId);
        // Setting background of 'search_plate' to earlier defined drawable.
        searchPlate.setBackgroundResource(R.drawable.search_plate_bg);

        // on Error background
        TextView errorTV = view.findViewById(R.id.error_searchvenues);
        errorTV.setVisibility(View.INVISIBLE);
        Button errorButton = view.findViewById(R.id.reload_button_searchvenues);
        errorButton.setVisibility(View.INVISIBLE);

        errorButton.setOnClickListener(v -> refreshFragment());


//        initRecyclerViewSearchResults();


    }


    private void getSearchLocationResults(CharSequence location) {
        TextView results = requireView().findViewById(R.id.SearchResults_RecentSearches);
        ProgressBar pb = requireView().findViewById(R.id.progress_bar_searchvenues);
        Log.d(TAG, "getSearchLocationResults: " + location);
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mDisposable = new CompositeDisposable();

        if (!(location.length() == 0)) {

            Log.d(TAG, "QueryUnEmpty: " + location + " " + location.length());
//            mMainViewModel.SearchLocation(String.valueOf(location));
            pb.setVisibility(View.VISIBLE);

            mMainViewModel.LocationSearch(String.valueOf(location));

            mMainViewModel.getmLocationSearchResults().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LocationSearchResponseModel>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposable.add(d);
                    Log.d(TAG, "onSubscribe: ");
                }

                @Override
                public void onNext(@NonNull LocationSearchResponseModel locationSearchResponseModel) {
                    Log.d(TAG, "onNext: ");
                    try {

                        List<String> mListOfLocations = new ArrayList<>(locationSearchResponseModel.getData().size());

                        for (Data post : locationSearchResponseModel.getData()) {

                            mListOfLocations.add(post.getName());

                            Log.d(TAG, "onNext: " + post.getName());
                            Log.d(TAG, "  --------------------------------- ");

                        }


                        if(!mListOfLocations.isEmpty()) {

                            pb.setVisibility(View.INVISIBLE);
                            mSearchResultsLocation.setValue(mListOfLocations);
                            initRecyclerViewSearchResults();
                            results.setVisibility(View.VISIBLE);
                            results.setText("Search Results");
                        }

//                        mLocationSearchMutableLiveData.setValue(locationSearchResponseModel);
                        Log.d(TAG, "onNext: " + locationSearchResponseModel.getData().get(0).getName());
                    } catch (NullPointerException e) {
                        Log.e(TAG, "onNext: ", e);

                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d(TAG, "onError: ");
                    SearchVenues.this.onError();
                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "onComplete: ");
                }
            });
            // More efficient Storage using MutableLiveData
//            Log.d(TAG, "getSearchLocationResults: " + mMainViewModel.getSearchVenueLiveData().getValue());

//            mMainViewModel.getSearchVenueLiveData().observe(getViewLifecycleOwner(), LocationData -> {
//                Log.e(TAG, "getSearchLocationResults: " + LocationData);
//                try {
//
//                    List<String> LocationResults = new ArrayList<>(LocationData.size());
//
//                    if (LocationData.size() != 0) {
//                        results.setText("Search Results");
//                        Log.d(TAG, "LatLon: " + LocationData.get(0).getLat());
//                        Log.d(TAG, "LatLon: " + LocationData.get(0).getLon());
//
//                        for (SearchLocationModel post : LocationData) {
//
//                            LocationResults.add(post.getDisplayName());
////                            LocationResults.add(post.getLat());
////                            LocationResults.add(post.getLon());
////                            Log.d(TAG, "ID: " + post.getId());
////                            Log.d(TAG, "DisplayName: " + post.getDisplayName());
////                            Log.d(TAG, "VenueNameURLRewrite: " + post.getVenueNameURLWrite());
////                            Log.d(TAG, "Latitude: " + post.getLat());
////                            Log.d(TAG, "Longitude: " + post.getLon());
//
//                        }
//
//                        pb.setVisibility(View.INVISIBLE);
//                        mSearchResultsLocation.setValue(LocationResults);
//                        initRecyclerViewSearchResults();
//                        results.setVisibility(View.VISIBLE);
//
//                    }
//
//                } catch (NullPointerException e) {
//                    onError();
//                }
//
//
//            });

        } else {
            pb.setVisibility(View.VISIBLE);
            results.setVisibility(View.INVISIBLE);
            results.setText("Recent Searches");
            TextView errorTV = requireView().findViewById(R.id.error_searchvenues);
            errorTV.setVisibility(View.INVISIBLE);
            Button errorButton = requireView().findViewById(R.id.reload_button_searchvenues);
            errorButton.setVisibility(View.INVISIBLE);

//            results.setVisibility(View.INVISIBLE);
            Log.d(TAG, "QueryEmpty: " + location);
            List<String> locationNamesEmpty = new ArrayList<>();
            mSearchResultsLocation.setValue(Collections.unmodifiableList(locationNamesEmpty));
            initRecyclerViewSearchHistory();
            results.setVisibility(View.VISIBLE);
            pb.setVisibility(View.INVISIBLE);
        }

    }


    public void getSearchHistory() {
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        TextView results = requireView().findViewById(R.id.SearchResults_RecentSearches);

        mMainViewModel.getHistory().observe(getViewLifecycleOwner(), (data) -> {
            if (data.size() != 0) {
                mSearchHistory.setValue(data);
                results.setVisibility(View.VISIBLE);

            } else {
                results.setVisibility(View.INVISIBLE);
            }
        });
    }


    private void initRecyclerViewSearchResults() {
        RecyclerView recyclerViewSearchResultsLocation = requireView().findViewById(R.id.search_venue_recyclerview);
        SearchVenueAdapter adapter = new SearchVenueAdapter(getActivity(), this);
        recyclerViewSearchResultsLocation.setAdapter(adapter);
        recyclerViewSearchResultsLocation.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSearchResultsLocation.observe(getViewLifecycleOwner(), adapter::updateData);

    }


    private void initRecyclerViewSearchHistory() {
        RecyclerView recyclerViewSearchResultsLocation = requireView().findViewById(R.id.search_venue_recyclerview);
        recyclerViewSearchResultsLocation.setVisibility(View.VISIBLE);
        SearchVenueAdapter adapter = new SearchVenueAdapter(getActivity(), this);
        recyclerViewSearchResultsLocation.setAdapter(adapter);
        recyclerViewSearchResultsLocation.setLayoutManager(new LinearLayoutManager(getActivity()));
        getSearchHistory();


        mSearchHistory.observe(getViewLifecycleOwner(), adapter::updateData);


    }

    @Override
    public void onLocationClicked(String locationName) {
        Log.d(TAG, "onLocationClicked: " + locationName);
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
//        getLatLon(locationName);
        mMainViewModel.saveLocationName(requireContext(), locationName);
        mMainViewModel.addLocationToHistory(locationName);
        hideKeyboard(requireActivity());


        Log.d(TAG, "onViewCreated: " + locationName.split(",")[0]);
//        mMainViewModel.init();
//        mMainViewModel.SearchLocation(locationName.split(",")[0]);
//        mMainViewModel.getSearchVenueLiveData().observe(getViewLifecycleOwner(), (data) -> {
//
//            String lat = data.get(0).getLat();
//            String lon = data.get(0).getLon();
//            String latlon = lat + "," + lon;
//
//            Log.d(TAG, "onLocationClicked: " + latlon);
//
//
//            mMainViewModel.saveLatLon(requireActivity(), latlon);
//        });


        Navigation.findNavController(requireView()).popBackStack();

//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        SearchHomepage SearchHomepageTag = (SearchHomepage) fragmentManager.findFragmentByTag("SearchHomepage");
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.main_activity_frame_layout, SearchHomepage.class, null, "SearchHomepage");
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();


    }


    public void goToSearchHomepage() {

        Navigation.findNavController(requireView()).navigate(R.id.action_searchVenues_to_searchHomepage);
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

    private void onError() {
        Log.d(TAG, "onError: ");
        TextView results = requireView().findViewById(R.id.SearchResults_RecentSearches);
        ProgressBar pb = requireView().findViewById(R.id.progress_bar_searchvenues);
        RecyclerView recyclerViewSearchResultsLocation = requireView().findViewById(R.id.search_venue_recyclerview);
//        hideKeyboard(requireActivity());

        TextView errorTV = requireView().findViewById(R.id.error_searchvenues);
        errorTV.setVisibility(View.VISIBLE);
        Button errorButton = requireView().findViewById(R.id.reload_button_searchvenues);
        errorButton.setVisibility(View.VISIBLE);

        results.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.INVISIBLE);
        recyclerViewSearchResultsLocation.setVisibility(View.INVISIBLE);


    }


    private void refreshFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        SearchVenues SearchHomepageTag = (SearchVenues) fragmentManager.findFragmentByTag("SearchVenues");

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_frame_layout, SearchVenues.class, null, "SearchVenues");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // Do NOT delete dispose. Switching back and forth rapidly causes crash as the async task need to wrap up when replacing fragments

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

