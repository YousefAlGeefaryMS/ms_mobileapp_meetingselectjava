package com.meetingselect.meetingselect.main.myrequests;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.rxbinding2.widget.RxSearchView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2.ConfirmedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.Venue;
import com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.PendingBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.SavedBookingsMS2.ListItem;
import com.meetingselect.meetingselect.data.model.SavedBookingsMS2.SavedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.VenueDetailsResponseModel;
import com.meetingselect.meetingselect.main.myrequests.adapters.ConfirmedBookingsAdapter;
import com.meetingselect.meetingselect.main.myrequests.adapters.PendingBookingsAdapter;
import com.meetingselect.meetingselect.main.myrequests.adapters.SavedBookingsAdapter;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MyRequests extends Fragment implements View.OnClickListener, ConfirmedBookingsAdapter.onConfirmedBookingClicked, PendingBookingsAdapter.onPendingBookingClicked {

    private static final String TAG = "My_Requests";

    private ColorStateList def;
    private TextView mSavedBookingTextView, mPendingBookings, mCompletedBookings, mSelectedTab;
    private View mSavedPendingDivider, mPendingCompletedDivider;
    private RecyclerView mSavedBookingsRecyclerView, mPendingBookingsRecyclerView, mConfirmedBookingsRecyclerView;
    private SearchView mSavedBookingsSearchView, mPendingBookingsSearchView, mConfirmedBookingsSearchView;

    private MutableLiveData<SavedBookingsMS2ResponseModel> mSavedBookingsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<PendingBookingsMS2ResponseModel> mPendingBookingsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ConfirmedBookingsMS2ResponseModel> mConfirmedBookingsMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<VenueDetailsResponseModel> mSavedBookingsImagesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<VenueDetailsResponseModel> mPendingBookingsImagesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<VenueDetailsResponseModel> mConfirmedBookingsImagesMutableLiveData = new MutableLiveData<>();

    private Boolean mSavedProcessRunning;
    private Boolean mPendingProcessRunning;
    private Boolean mConfirmedProcessRunning;
    private Boolean mProcessRunningAndroid;

    private CompositeDisposable mDisposable;

    // This is for a bug fix regarding recyclerview not being invisible due to query calling the functions ez
    private Boolean mAppStartIndicator = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_requests, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.VISIBLE);

        mDisposable = new CompositeDisposable();

        ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.myrequests_shimmerlayout_shimmerlayout);
        shimmerFrameLayout.startShimmer();

        mSavedBookingsSearchView = requireView().findViewById(R.id.myrequests_savedbookingsedittext_edittext);
        mPendingBookingsSearchView = requireView().findViewById(R.id.myrequests_pendingbookingsedittext_edittext);
        mConfirmedBookingsSearchView = requireView().findViewById(R.id.myrequests_confirmedbookingsedittext_edittext);

        Log.d(TAG, "onResume: ");

        mSavedBookingTextView = view.findViewById(R.id.myrequeststoolbar_savedproposals_textview);
        mPendingBookings = view.findViewById(R.id.myrequeststoolbar_pendingproposals_textview);
        mCompletedBookings = view.findViewById(R.id.myrequeststoolbar_confirmedproposals_textview);

        mSavedBookingsRecyclerView = requireView().findViewById(R.id.myrequests_savedbookingsrv_recyclerview);
        mPendingBookingsRecyclerView = requireView().findViewById(R.id.myrequests_pendingbookingsrv_recyclerview);
        mConfirmedBookingsRecyclerView = requireView().findViewById(R.id.myrequests_confirmedbookingsrv_recyclerview);




        mSavedPendingDivider = view.findViewById(R.id.myrequeststoolbar_savedpendingdivider_view);
        mPendingCompletedDivider = view.findViewById(R.id.myrequeststoolbar_pendingconfirmeddivider_view);


        mSavedBookingTextView.setOnClickListener(this);
        mPendingBookings.setOnClickListener(this);
        mCompletedBookings.setOnClickListener(this);

//        mSavedBookingsRecyclerView.setVisibility(View.INVISIBLE);


        mSelectedTab = view.findViewById(R.id.myrequeststoolbar_selectedtext_textview);

        def = mCompletedBookings.getTextColors();
//        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
//
//        String mTokenHeader = mMainViewModel.loadUserToken(requireActivity());
//
//        SavedBookingsMS2Repo savedBookingsMS2Repo = new SavedBookingsMS2Repo();
//        savedBookingsMS2Repo.getSavedBookingsMS2Repo(mTokenHeader, "", 1, 25);
//
//        PendingBookingsMS2Repo pendingBookingsMS2Repo = new PendingBookingsMS2Repo();
//        pendingBookingsMS2Repo.getPendingBookingsMS2(mTokenHeader, "", 1, 25);
//
//        ConfirmedBookingsMS2Repo confirmedBookingsMS2Repo = new ConfirmedBookingsMS2Repo();
//        confirmedBookingsMS2Repo.getConfirmedBookingsMS2(mTokenHeader, "", 1, 25);


        mPendingBookingsSearchView = requireView().findViewById(R.id.myrequests_pendingbookingsedittext_edittext);
        mConfirmedBookingsSearchView = requireView().findViewById(R.id.myrequests_confirmedbookingsedittext_edittext);


        mDisposable.add(
                RxSearchView.queryTextChanges(mPendingBookingsSearchView)
                        .debounce(350, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(c -> {

                            if(mAppStartIndicator) {
                                onResults();
                                getPendingBookings(c);
                            }


                        })
        );
    // Bug this gets executed at first launch

        mDisposable.add(
                RxSearchView.queryTextChanges(mConfirmedBookingsSearchView)
                        .debounce(350, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(v -> {

                            if(mAppStartIndicator) {
                                onResults();
                                getConfirmedBookings(v);
                            }


                        })
        );

        mSavedProcessRunning = true;
        mProcessRunningAndroid = true;
        getSavedBookings("");


        mPendingBookingsRecyclerView.setVisibility(View.GONE);
        mConfirmedBookingsRecyclerView.setVisibility(View.GONE);




    }


    private void getSavedBookings(String meetingName) {

        Log.d(TAG, "getSavedBookings: Triggered");
        mSavedBookingsRecyclerView = requireView().findViewById(R.id.myrequests_savedbookingsrv_recyclerview);
        mSavedBookingsRecyclerView.setVisibility(View.INVISIBLE);
        ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.myrequests_shimmerlayout_shimmerlayout);
        shimmerFrameLayout.setVisibility(View.VISIBLE);

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        String mTokenHeader = mMainViewModel.loadUserToken(requireActivity());

        mMainViewModel.getSavedBookingsMVVM(mTokenHeader, meetingName, 1, 100);

        mMainViewModel.getmSavedBookingsResponseModelBehaviorSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SavedBookingsMS2ResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable.add(mDisposable);
            }

            @Override
            public void onNext(@NonNull SavedBookingsMS2ResponseModel savedBookingsMS2ResponseModel) {


                mSavedBookingsMutableLiveData.setValue(savedBookingsMS2ResponseModel);

                initRecyclerViewSavedBookings();
//                for (ListItem post: savedBookingsMS2ResponseModel.getList()) {
//
//                    if (post.getRfpVenues().isEmpty()) {
//                    } else {
//                        String VenueID = String.valueOf(post.getRfpVenues().get(0).getVenueID());
//                        Log.d(TAG, "onNext: " + VenueID);
//                        getSavedBookingsImages(VenueID);
//                    }
//
//                }


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(requireActivity(), "Loading Failed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void getSavedBookingsImages(String VenueID) {

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Runnable getSavedBookingsImagesRunnable = () -> {
            mMainViewModel.getVenueDetailsMVVM(VenueID);

            Disposable disposable = mMainViewModel.getVenueDetailsBehaviorSubject().subscribe(v -> {
                Log.d(TAG, "getSavedBookingsImagesRunnable: " + v.getPictures().get(0).getOriginalUrl());
//                mSavedBookingsImagesMutableLiveData.setValue(v);

            });

//            try {
//                Thread.sleep(1500);
//                if(isAdded()) {
//                    requireActivity().runOnUiThread(this::initRecyclerViewSavedBookings);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            mDisposable.add(disposable);

        };


        Thread savedBookingsImagesThread = new Thread(getSavedBookingsImagesRunnable);
        savedBookingsImagesThread.start();



    }

    private void getPendingBookings(CharSequence meetingName) {
        Log.d(TAG, "getPending: Triggered");
        mPendingBookingsRecyclerView = requireView().findViewById(R.id.myrequests_pendingbookingsrv_recyclerview);
        mPendingBookingsRecyclerView.setVisibility(View.INVISIBLE);
        ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.myrequests_shimmerlayout_shimmerlayout);
        shimmerFrameLayout.setVisibility(View.VISIBLE);


        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        String mTokenHeader = mMainViewModel.loadUserToken(requireActivity());

        if (!(meetingName.length() == 0)) {
            mMainViewModel.getPendingBookingsMS2(mTokenHeader, String.valueOf(meetingName), 1, 100);

            mMainViewModel.getmPendingBookingsResponseModelBehaviorSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PendingBookingsMS2ResponseModel>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull PendingBookingsMS2ResponseModel pendingBookingsMS2ResponseModel) {
                    mPendingBookingsMutableLiveData.setValue(pendingBookingsMS2ResponseModel);
                    Log.d(TAG, "getPendingOnNExt: Triggered");

                    initRecyclerViewPendingBookings();
//                    for (com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.ListItem post : pendingBookingsMS2ResponseModel.getList()) {
//                        try {
//                            String VenueID = String.valueOf(post.getRfpVenues().get(0).getVenueID());
//                            Log.d(TAG, "onNext: " + VenueID);
//                            getSavedBookingsImages(VenueID);
//                        } catch (NullPointerException | IndexOutOfBoundsException e) {
//                            Log.e(TAG, "onNext: ", e);
//                            continue;
//                        }
//                    }
//

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e(TAG, "onError: ", e);

                }

                @Override
                public void onComplete() {

                }
            });

        } else {
            mMainViewModel.getPendingBookingsMS2(mTokenHeader, "", 1, 100);

            mMainViewModel.getmPendingBookingsResponseModelBehaviorSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PendingBookingsMS2ResponseModel>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull PendingBookingsMS2ResponseModel pendingBookingsMS2ResponseModel) {
                    mPendingBookingsMutableLiveData.setValue(pendingBookingsMS2ResponseModel);
                    Log.d(TAG, "getPendingOnNExt: Triggered");

                    initRecyclerViewPendingBookings();

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e(TAG, "onError: ", e);

                }

                @Override
                public void onComplete() {

                }
            });

        }


    }

    private void getPendingAndConfirmedBookingsImages(String VenueID, int BookingIndicator) {
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mMainViewModel.getVenueDetailsMVVM(VenueID);

        Runnable getSavedBookingsImagesRunnable = () -> {
            mMainViewModel.getVenueDetailsMVVM(VenueID);

            Disposable disposable = mMainViewModel.getVenueDetailsBehaviorSubject().subscribe(v -> {
                if(BookingIndicator == 1) {
                    mPendingBookingsImagesMutableLiveData.setValue(v);
                } else {
                    mConfirmedBookingsImagesMutableLiveData.setValue(v);
                }

            });

            try {
                Thread.sleep(1500);
                if(isAdded()) {
                    requireActivity().runOnUiThread(() -> {

                        if(BookingIndicator == 1) {
                            initRecyclerViewPendingBookings();
                        } else {
                            initRecyclerViewConfirmedBookings();
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mDisposable.add(disposable);

        };


        Thread savedBookingsImagesThread = new Thread(getSavedBookingsImagesRunnable);
        savedBookingsImagesThread.start();











    }

    private void getConfirmedBookings(CharSequence meetingName) {
        mConfirmedBookingsRecyclerView = requireView().findViewById(R.id.myrequests_confirmedbookingsrv_recyclerview);
        mConfirmedBookingsRecyclerView.setVisibility(View.INVISIBLE);

        ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.myrequests_shimmerlayout_shimmerlayout);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        String mTokenHeader = mMainViewModel.loadUserToken(requireActivity());

        mDisposable = new CompositeDisposable();

        if (!(meetingName.length() == 0)) {

            Log.d(TAG, "RxQuery: Query");

            mMainViewModel.getConfirmedBookingsMVVM(mTokenHeader, String.valueOf(meetingName), 1, 100);

            mMainViewModel.getmConfirmedBookingsResponseModelBehaviorSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ConfirmedBookingsMS2ResponseModel>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull ConfirmedBookingsMS2ResponseModel confirmedBookingsMS2ResponseModel) {
                    Log.d(TAG, "RxQuery: onNextConfirmed");
                    mConfirmedBookingsMutableLiveData.setValue(confirmedBookingsMS2ResponseModel);

                    initRecyclerViewConfirmedBookings();


                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e(TAG, "onError: ", e);
                }

                @Override
                public void onComplete() {

                }
            });


        } else {

            Log.d(TAG, "RxQuery: Not Query");


            mMainViewModel.getConfirmedBookingsMVVM(mTokenHeader, "", 1, 100);

            mMainViewModel.getmConfirmedBookingsResponseModelBehaviorSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ConfirmedBookingsMS2ResponseModel>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull ConfirmedBookingsMS2ResponseModel confirmedBookingsMS2ResponseModel) {
                    Log.d(TAG, "RxQuery: onNext");
                    mConfirmedBookingsMutableLiveData.setValue(confirmedBookingsMS2ResponseModel);
                    initRecyclerViewConfirmedBookings();

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e(TAG, "onError: ", e);
                }

                @Override
                public void onComplete() {

                }
            });

        }


    }

    private void noResults() {
        ImageView noResultsImage = requireView().findViewById(R.id.myrequests_noresultsfound_imageview);
        TextView noResultsTextView = requireView().findViewById(R.id.myrequest_noresultsfound_textview);

        noResultsImage.setVisibility(View.VISIBLE);
        noResultsTextView.setVisibility(View.VISIBLE);


    }

    private void onResults() {
        ImageView noResultsImage = requireView().findViewById(R.id.myrequests_noresultsfound_imageview);
        TextView noResultsTextView = requireView().findViewById(R.id.myrequest_noresultsfound_textview);

        noResultsImage.setVisibility(View.INVISIBLE);
        noResultsTextView.setVisibility(View.INVISIBLE);

    }


    private void initRecyclerViewSavedBookings() {

        if (isAdded()) {
            onResults();
            ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.myrequests_shimmerlayout_shimmerlayout);

            shimmerFrameLayout.setVisibility(View.VISIBLE);




            mSavedBookingsSearchView = requireView().findViewById(R.id.myrequests_savedbookingsedittext_edittext);
            enableSearchView(mSavedBookingsSearchView, false);


            mSavedBookingsRecyclerView = requireView().findViewById(R.id.myrequests_savedbookingsrv_recyclerview);
            SavedBookingsAdapter adapter = new SavedBookingsAdapter(requireActivity(), mSavedBookingsMutableLiveData, mSavedBookingsImagesMutableLiveData);
            mSavedBookingsRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            mSavedBookingsRecyclerView.setAdapter(adapter);

            adapter.updateData(mSavedBookingsMutableLiveData, mSavedBookingsImagesMutableLiveData);


            try {

                mSavedBookingsMutableLiveData.getValue().getList().get(0).getUserLastName();
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                noResults();
            }
            shimmerFrameLayout.setVisibility(View.INVISIBLE);

            if (mProcessRunningAndroid && mSavedProcessRunning) {

                mSavedBookingsRecyclerView.setVisibility(View.VISIBLE);
                mPendingBookingsRecyclerView.setVisibility(View.INVISIBLE);
                mConfirmedBookingsRecyclerView.setVisibility(View.INVISIBLE);
            } else {
                Log.d(TAG, "initRecyclerViewConfirmedBookings: he");

                mSavedBookingsRecyclerView.setVisibility(View.INVISIBLE);
            }


        }
    }

    private void initRecyclerViewPendingBookings() {

        if (isAdded()) {
            onResults();



            mPendingBookingsSearchView = requireView().findViewById(R.id.myrequests_pendingbookingsedittext_edittext);
            enableSearchView(mPendingBookingsSearchView, true);

            ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.myrequests_shimmerlayout_shimmerlayout);

            mPendingBookingsRecyclerView = requireView().findViewById(R.id.myrequests_pendingbookingsrv_recyclerview);
            PendingBookingsAdapter adapter = new PendingBookingsAdapter(requireActivity(), mPendingBookingsMutableLiveData, mPendingBookingsImagesMutableLiveData, this);
            mPendingBookingsRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            mPendingBookingsRecyclerView.setAdapter(adapter);

            adapter.updateData(mPendingBookingsMutableLiveData, mPendingBookingsImagesMutableLiveData);
            shimmerFrameLayout.setVisibility(View.INVISIBLE);

            try {
                mPendingBookingsMutableLiveData.getValue().getList().get(0).getUserLastName();
            } catch (IndexOutOfBoundsException | NullPointerException e) {
//                enableSearchView(mPendingBookingsSearchView, false);
                noResults();
            }

            if(mProcessRunningAndroid && mPendingProcessRunning) {

                mPendingBookingsRecyclerView.setVisibility(View.VISIBLE);
                mConfirmedBookingsRecyclerView.setVisibility(View.INVISIBLE);
                mSavedBookingsRecyclerView.setVisibility(View.INVISIBLE);
            } else {
                mPendingBookingsRecyclerView.setVisibility(View.INVISIBLE);

            }


        }
    }

    private void initRecyclerViewConfirmedBookings() {

        if (isAdded()) {
            onResults();


            ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.myrequests_shimmerlayout_shimmerlayout);
            shimmerFrameLayout.setVisibility(View.VISIBLE);

            mConfirmedBookingsSearchView = requireView().findViewById(R.id.myrequests_confirmedbookingsedittext_edittext);
            enableSearchView(mConfirmedBookingsSearchView, true);

            mConfirmedBookingsRecyclerView = requireView().findViewById(R.id.myrequests_confirmedbookingsrv_recyclerview);
            ConfirmedBookingsAdapter adapter = new ConfirmedBookingsAdapter(requireActivity(), this);
            mConfirmedBookingsRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            mConfirmedBookingsRecyclerView.setAdapter(adapter);

            adapter.updateData(mConfirmedBookingsMutableLiveData, mPendingBookingsImagesMutableLiveData);
            shimmerFrameLayout.setVisibility(View.INVISIBLE);


            try {
                mConfirmedBookingsMutableLiveData.getValue().getList().get(0).getLastName();
            } catch (IndexOutOfBoundsException | NullPointerException e) {
//                enableSearchView(mConfirmedBookingsSearchView, false);
                noResults();
            }


            if(mProcessRunningAndroid && mConfirmedProcessRunning) {


                mConfirmedBookingsRecyclerView.setVisibility(View.VISIBLE);
                mPendingBookingsRecyclerView.setVisibility(View.INVISIBLE);
                mSavedBookingsRecyclerView.setVisibility(View.INVISIBLE);
            } else {
                mConfirmedBookingsRecyclerView.setVisibility(View.INVISIBLE);

            }



        }
    }


    @Override
    public void onClick(View v) {

        Handler handler = new Handler();
        ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.myrequests_shimmerlayout_shimmerlayout);
        shimmerFrameLayout.setVisibility(View.VISIBLE);

        onResults();

        if (v.getId() == R.id.myrequeststoolbar_savedproposals_textview) {
//            getSavedBookings("");


            mSavedProcessRunning = true;
            mProcessRunningAndroid = true;
            mPendingProcessRunning = false;
            mConfirmedProcessRunning = false;
            // RecyclerViewsHider
            mPendingBookingsRecyclerView.setVisibility(View.INVISIBLE);
            mConfirmedBookingsRecyclerView.setVisibility(View.INVISIBLE);
//            //
//
            // EditText
            mSavedBookingsSearchView.setVisibility(View.VISIBLE);
            mPendingBookingsSearchView.setVisibility(View.INVISIBLE);
            mConfirmedBookingsSearchView.setVisibility(View.INVISIBLE);

            mSavedPendingDivider.setVisibility(View.INVISIBLE);
            mPendingCompletedDivider.setVisibility(View.VISIBLE);
            mSelectedTab.animate().x(0).setDuration(100);
            mSavedBookingTextView.setTextColor(Color.WHITE);
            mPendingBookings.setTextColor(def);
            mCompletedBookings.setTextColor(def);


            Runnable r = new Runnable() {
                public void run() {
                    initRecyclerViewSavedBookings();
                    //what ever you do here will be done after 3 seconds delay.
                }
            };
            handler.postDelayed(r, 1000);

//

//            initRecyclerViewSavedBookings();

        } else if (v.getId() == R.id.myrequeststoolbar_pendingproposals_textview) {
            mAppStartIndicator = true;

            mProcessRunningAndroid = true;
            mSavedProcessRunning = false;
            mPendingProcessRunning = true;
            mConfirmedProcessRunning = false;


            mSavedPendingDivider.setVisibility(View.INVISIBLE);
            mPendingCompletedDivider.setVisibility(View.INVISIBLE);
            mSavedBookingTextView.setTextColor(def);
            mPendingBookings.setTextColor(Color.WHITE);
            mCompletedBookings.setTextColor(def);

            int size = mPendingBookings.getWidth();
            mSelectedTab.animate().x(size).setDuration(100);



            // EditText
            mPendingBookingsSearchView.setVisibility(View.VISIBLE);
            mPendingBookingsSearchView.clearFocus();
            mConfirmedBookingsSearchView.setVisibility(View.INVISIBLE);
            mSavedBookingsSearchView.setVisibility(View.INVISIBLE);


//
////            mPendingBookingsRecyclerView.setVisibility(View.VISIBLE);
            mConfirmedBookingsRecyclerView.setVisibility(View.INVISIBLE);
            mSavedBookingsRecyclerView.setVisibility(View.INVISIBLE);
//            //
//


            CharSequence pendingBookingsQuery = mPendingBookingsSearchView.getQuery();

            getPendingBookings(pendingBookingsQuery);
        } else if (v.getId() == R.id.myrequeststoolbar_confirmedproposals_textview) {
            mAppStartIndicator = true;

            mProcessRunningAndroid = true;
            mSavedProcessRunning = false;
            mPendingProcessRunning = false;
            mConfirmedProcessRunning = true;

            CharSequence confirmedBookingQuery=  mConfirmedBookingsSearchView.getQuery();
            getConfirmedBookings(confirmedBookingQuery);

//            // RecyclerView hider
////            mConfirmedBookingsRecyclerView.setVisibility(View.VISIBLE);
            mPendingBookingsRecyclerView.setVisibility(View.INVISIBLE);
            mSavedBookingsRecyclerView.setVisibility(View.INVISIBLE);
            //
            // EditText Hider
            mConfirmedBookingsSearchView.setVisibility(View.VISIBLE);
            mConfirmedBookingsSearchView.clearFocus();
            mPendingBookingsSearchView.setVisibility(View.INVISIBLE);
            mSavedBookingsSearchView.setVisibility(View.INVISIBLE);

//            initRecyclerViewConfirmedBookings();
            mSavedPendingDivider.setVisibility(View.VISIBLE);
            mPendingCompletedDivider.setVisibility(View.INVISIBLE);
            mSavedBookingTextView.setTextColor(def);
            mPendingBookings.setTextColor(def);
            mCompletedBookings.setTextColor(Color.WHITE);
            int size = mPendingBookings.getWidth() * 2;
            mSelectedTab.animate().x(size).setDuration(100);


        }


    }


    private void enableSearchView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                enableSearchView(child, enabled);
            }
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
        mAppStartIndicator = false;

        mDisposable.dispose();
    }

    @Override
    public void onConfirmedBookingClicked(String proposalID) {

        Log.d(TAG, "onConfirmedBookingClicked: " +  proposalID);

        Bundle bundle = new Bundle();

        bundle.putString("ProposalID", proposalID);
        Navigation.findNavController(requireView()).navigate(R.id.action_myRequests_to_confirmedBookingsDetailsFragment, bundle);


    }

    @Override
    public void onResume() {
        super.onResume();
        mAppStartIndicator = false;

        mSavedBookingsSearchView.setQuery("", false);
        mPendingBookingsSearchView.setQuery("", false);
        mConfirmedBookingsSearchView.setQuery("", false);

        mPendingBookingsRecyclerView.setVisibility(View.INVISIBLE);
        mConfirmedBookingsRecyclerView.setVisibility(View.INVISIBLE);

        mSavedBookingsSearchView.setQueryHint("Searching unavailable for this category");
        mPendingBookingsSearchView.setQueryHint("Search for your meeting name here");
        mConfirmedBookingsSearchView.setQueryHint("Search for your meeting name here");


        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPendingBookingClicked(ArrayList<String> proposalIDs) {

        for(String post : proposalIDs) {

            Log.d(TAG, "onPendingBookingClicked: " + post);
        }

        Bundle bundle = new Bundle();

        bundle.putStringArrayList("ProposalIDs", proposalIDs);

        Navigation.findNavController(requireView()).navigate(R.id.action_myRequests_to_pendingBookingsDetailsFragment, bundle);



    }
}