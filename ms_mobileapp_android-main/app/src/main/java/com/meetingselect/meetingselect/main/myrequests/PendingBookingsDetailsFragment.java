package com.meetingselect.meetingselect.main.myrequests;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
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
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model.AcceptConfirmResponseModel;
import com.meetingselect.meetingselect.main.myrequests.adapters.PendingBookingsDetailsAdapter;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import java.lang.invoke.MutableCallSite;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PendingBookingsDetailsFragment extends Fragment implements PendingBookingsDetailsAdapter.onPDFLinkClicked{

    private static final String TAG = "PendingBookingsDetailsFrag";

    private CompositeDisposable mDisposable;

    private MutableLiveData<AcceptConfirmResponseModel> mAcceptConfirmResponseModel = new MutableLiveData<>();
    private List<MutableLiveData<AcceptConfirmResponseModel>> mHolderOfResponses = new ArrayList<>();
    private int initialListSize = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pending_bookings_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.pendingbookingdetails_shimmerlayout_shimmerlayout);
        shimmerFrameLayout.startShimmer();

        shimmerFrameLayout.setVisibility(View.VISIBLE);


        BottomNavigationView navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.GONE);

        mDisposable = new CompositeDisposable();
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        String token = mMainViewModel.loadUserToken(requireActivity());

        try {
            ArrayList<String> proposalId = getArguments().getStringArrayList("ProposalIDs");

            initialListSize = proposalId.size();

            for (String post : proposalId) {

                mHolderOfResponses.clear();
                getAcceptConfirmBooking(token, post);

                Log.d(TAG, "onViewCreated: " + post);
            }


        } catch (NullPointerException e) {
            Log.e(TAG, "onViewCreated: ", e);
        }
    }


    private void getAcceptConfirmBooking(String token, String proposalID) {
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Log.d(TAG, "getAcceptConfirmBooking: This RUnning");
        mMainViewModel.getAcceptConfirmBookingMS2(token, proposalID);

        mDisposable = new CompositeDisposable();

        mMainViewModel.getmAcceptConfirmResponseModelBehaviourSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AcceptConfirmResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull AcceptConfirmResponseModel acceptConfirmResponseModel) {
                mAcceptConfirmResponseModel.setValue(acceptConfirmResponseModel);
                mHolderOfResponses.add(mAcceptConfirmResponseModel);
                initProposalsRecyclerView(mHolderOfResponses);
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


    private void initProposalsRecyclerView(List<MutableLiveData<AcceptConfirmResponseModel>> mHolderOfResponses) {
        Log.d(TAG, "initProposalsRecyclerView: " + initialListSize);
        Log.d(TAG, "initProposalsRecyclerView: " + mHolderOfResponses.size());
        if (isAdded() && initialListSize == mHolderOfResponses.size()) {
            ShimmerFrameLayout shimmerFrameLayout = requireView().findViewById(R.id.pendingbookingdetails_shimmerlayout_shimmerlayout);



            RecyclerView recyclerView = requireView().findViewById(R.id.pendingbookingsdetails_recyclerview_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            PendingBookingsDetailsAdapter adapter = new PendingBookingsDetailsAdapter(requireActivity(), this);
            recyclerView.setAdapter(adapter);

            adapter.updateData(mHolderOfResponses);

            Handler handler=new Handler();
            Runnable r= new Runnable() {
                public void run() {

                    shimmerFrameLayout.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            };
            handler.postDelayed(r, 1000);

        }

    }

    @Override
    public void onPDFLinkClicked(String pdflink) {

        if(pdflink.equals("#") || pdflink.isEmpty()) {
            Toast.makeText(requireActivity(), "This PDF is unavailable for this proposal.", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "onPDFLinkClicked: " + pdflink);

            Bundle bundle = new Bundle();

            bundle.putString("PDFLinkPendingDetails", pdflink);
            Navigation.findNavController(requireView()).navigate(R.id.action_pendingBookingsDetailsFragment_to_PDFWebView, bundle);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mDisposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
