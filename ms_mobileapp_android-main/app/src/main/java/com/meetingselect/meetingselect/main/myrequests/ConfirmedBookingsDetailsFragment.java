package com.meetingselect.meetingselect.main.myrequests;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model.AcceptConfirmResponseModel;
import com.meetingselect.meetingselect.main.myrequests.respository.AcceptConfirmBookingsMS2Repo;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.flowable.FlowableGenerate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class ConfirmedBookingsDetailsFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ConfirmedDetails";

    private CompositeDisposable mDisposable;

    private TextView mMeetingNameTextView, mCompanyNameTextView, mProposalIDTextView, mRFPIDTextView, mRFPPDFLinkTextView, mVenueNameTextView, mOtherVenueNamesTextView, mProposalPDFTextView, mCostLocationTextView, mDivisionTextView, mDivisionIDTextView, mDepartmentTextView, mContactNameTextView, mNoticeBoardNameTextView, mCommentForDeclineTextView, mReasonForDeclineTextView, mCommentTextView, mSeriesRFPIDsTextView, mArrivalDateTextView, mDepartureDateTextView, mProposalAttachmentsTextView, mCurrencyCodeTextView, mTotalEstimatedCostExclTaxTextView, mCompanyCurrencyTextView, mCompanyCurrencySignTextView, mTotalEstimatedCostExclInCompanyCurrencyTaxTextView, mAcceptVersionNumberTextView;
    private TextView mCommentForDeclineStringTextView, mReasonForDeclineStringTextView;
    private String mRFPPdfLink, mProposalPDFLink, mSupplierPDFLink;
    private NestedScrollView mMainHeadLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmed_bookings_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        String token = mMainViewModel.loadUserToken(requireActivity());

        mMainHeadLayout = view.findViewById(R.id.confirmedbookingsdetails_mainheadscrollviewlayout_nestedscrollview);

        mMeetingNameTextView = view.findViewById(R.id.confirmedbookingsdetails_meetingname_textview);
        mCompanyNameTextView = view.findViewById(R.id.confirmedbookingsdetails_companyname_textview);
        mProposalIDTextView = view.findViewById(R.id.confirmedbookingsdetails_proposalid_textview);
        mRFPIDTextView = view.findViewById(R.id.confirmedbookingsdetails_rfpid_textview4);
        mRFPPDFLinkTextView = view.findViewById(R.id.confirmedbookingsdetails_rfppdflink_textview);
        mVenueNameTextView = view.findViewById(R.id.confirmedbookingsdetails_venuename_textview);
        mOtherVenueNamesTextView = view.findViewById(R.id.confirmedbookingsdetails_venuenames_textview);
        mProposalPDFTextView = view.findViewById(R.id.confirmedbookingsdetails_proposalpdflink_textview4);
        mCostLocationTextView = view.findViewById(R.id.confirmedbookingsdetails_costlocation_textview);
        mDivisionTextView = view.findViewById(R.id.confirmedbookingsdetails_division_textview);
        mDivisionIDTextView = view.findViewById(R.id.confirmedbookingsdetails_divisionid_textview);
        mDepartmentTextView = view.findViewById(R.id.confirmedbookingsdetails_department_textview);
        mContactNameTextView = view.findViewById(R.id.confirmedbookingsdetails_contactname_textview);
        mNoticeBoardNameTextView = view.findViewById(R.id.confirmedbookingsdetails_noticeboardname_textview);
        mCommentForDeclineTextView = view.findViewById(R.id.confirmedbookingsdetails_commentfordecline_textview);
        mReasonForDeclineTextView = view.findViewById(R.id.confirmedbookingsdetails_reasonfordecline_textview);
        mCommentTextView = view.findViewById(R.id.confirmedbookingsdetails_comment_textview);
        mSeriesRFPIDsTextView = view.findViewById(R.id.confirmedbookingsdetails_seriesrfpid_textview);
        mArrivalDateTextView = view.findViewById(R.id.confirmedbookingsdetails_arrivaldate_textview);
        mDepartureDateTextView = view.findViewById(R.id.confirmedbookingsdetails_departuredate_textview);
        mProposalAttachmentsTextView = view.findViewById(R.id.confirmedbookingsdetails_proposalattachments_textview);
        mCurrencyCodeTextView = view.findViewById(R.id.confirmedbookingsdetails_currencycode_textview);
        mTotalEstimatedCostExclTaxTextView = view.findViewById(R.id.confirmedbookingsdetails_totalestimatedcostexcltax_textview);
        mCompanyCurrencyTextView = view.findViewById(R.id.confirmedbookingsdetails_companycurrency_textview);
        mCompanyCurrencySignTextView = view.findViewById(R.id.confirmedbookingsdetails_companycurrencysign_textview);
        mTotalEstimatedCostExclInCompanyCurrencyTaxTextView = view.findViewById(R.id.confirmedbookingsdetails_totalestimatedcostexcltaxincompanycurrency_textview);
        mCommentForDeclineStringTextView = view.findViewById(R.id.confirmedbookingsdetails_commentfordeclinestring_textview);
        mReasonForDeclineStringTextView = view.findViewById(R.id.confirmedbookingsdetails_reasonfordeclinestring_textview);
        mAcceptVersionNumberTextView = view.findViewById(R.id.confirmedbookingsdetails_acceptversionnumber_textview);


        mRFPPDFLinkTextView.setOnClickListener(this);
        mProposalPDFTextView.setOnClickListener(this);

        BottomNavigationView navBar = requireActivity().findViewById(R.id.bottomNavigationViewMainActivity);
        navBar.setVisibility(View.GONE);

        try {
            String proposalId = (String) getArguments().getString("ProposalID");

            getAcceptConfirmBooking(token, proposalId);

            Log.d(TAG, "onViewCreated: " + proposalId);

        } catch (NullPointerException e) {
            Log.e(TAG, "onViewCreated: ", e);
        }
    }



    private void getAcceptConfirmBooking(String token, String proposalID) {
        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);


        mMainViewModel.getAcceptConfirmBookingMS2(token, proposalID);

        mDisposable = new CompositeDisposable();

        mMainViewModel.getmAcceptConfirmResponseModelBehaviourSubject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AcceptConfirmResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull AcceptConfirmResponseModel acceptConfirmResponseModel) {
                String RFPID = String.valueOf(acceptConfirmResponseModel.getRfpId());
                String RFPIDURL = acceptConfirmResponseModel.getRfpPdfUrl();
                String MeetingName = acceptConfirmResponseModel.getMeetingName();
                String CompanyName = acceptConfirmResponseModel.getCompanyName();
                String ProposalID = String.valueOf(acceptConfirmResponseModel.getProposalId());
                String VenueName = acceptConfirmResponseModel.getVenueName();
                String VenueNames = acceptConfirmResponseModel.getGetVenueNames();
                String SupplierPDFPath = acceptConfirmResponseModel.getSupplierPdfPath();
                String ProposalPDFURL = acceptConfirmResponseModel.getProposalPdfUrl();
                String CostLocation = acceptConfirmResponseModel.getCostLocation();
                String Division = acceptConfirmResponseModel.getDivision();
                String DivisionID = acceptConfirmResponseModel.getDivisionId();
                String Department = acceptConfirmResponseModel.getDepartment();
                String ContactName = String.valueOf(acceptConfirmResponseModel.getContactName());
                String NoticeBoardName = String.valueOf(acceptConfirmResponseModel.getNoticeBoardName());
                String Comment = String.valueOf(acceptConfirmResponseModel.getComment());
                String CommentForDecline = String.valueOf(acceptConfirmResponseModel.getCommentForDecline());
                String DeclineReason = String.valueOf(acceptConfirmResponseModel.getDeclineReason());
                List<Object> SeriesRFPIds = acceptConfirmResponseModel.getSeriesRfpIds();
                String ArrivalDate = acceptConfirmResponseModel.getRFPDateDetailsEntities().get(0).getArrivalDate();
                String DepartureDate = acceptConfirmResponseModel.getRFPDateDetailsEntities().get(0).getDepartureDate();
                String ProposalAttachments = acceptConfirmResponseModel.getProposalAttachments().get(0).getURL();
                String CurrencyCode = acceptConfirmResponseModel.getCurrencyCode();
                String TotalEstCostExclTax = String.valueOf(acceptConfirmResponseModel.getTotalEstimatedCostExclTax());
                String CompanyCurrency = acceptConfirmResponseModel.getCompanyCurrency();
                String CompanyCurrencySign = acceptConfirmResponseModel.getCompanyCurrencySign();
                String TotalEstCostExclTaxInCompanyCurrency = String.valueOf(acceptConfirmResponseModel.getTotalEstimatedCostExclTaxCompanyCurrency());
                String AcceptVersionNumber = String.valueOf(acceptConfirmResponseModel.getAcceptVersionNumber());
                incurChangesOnTextViews(MeetingName, CompanyName, ProposalID, RFPID, RFPIDURL, VenueName, VenueNames, SupplierPDFPath, ProposalPDFURL, CostLocation, Division, DivisionID, Department, AcceptVersionNumber, ContactName, NoticeBoardName, CommentForDecline, DeclineReason,  Comment, SeriesRFPIds, ArrivalDate, DepartureDate, ProposalAttachments, CurrencyCode, TotalEstCostExclTax, CompanyCurrency, CompanyCurrencySign, TotalEstCostExclTaxInCompanyCurrency);

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

    private void incurChangesOnTextViews(String meetingName,
                                         String companyName,
                                         String proposalID,
                                         String RFPID,
                                         String RFPPdfLink,
                                         String VenueName,
                                         String VenueNames,
                                         String SupplierPDF,
                                         String ProposalPDF,
                                         String CostLocation,
                                         String Division,
                                         String DivisonID,
                                         String Department,
                                         String AcceptVersionNumber,
                                         String ContactName,
                                         String NoticeBoardName,
                                         String CommentForDecline,
                                         String ReasonForDecline,
                                         String Comment,
                                         List<Object> SeriesRFPIDs,
                                         String ArrivalDate,
                                         String DepartureDate,
                                         String ProposalAttachments,
                                         String CurrencyCode,
                                         String TotalEstCostExclTax,
                                         String CompanyCurrency,
                                         String CompanyCurrencySign,
                                         String TotalEstCostExclTaxInCompanyCurrency) {

        Log.d(TAG, "incurChangesOnTextViews: " + meetingName);


        mRFPPdfLink = RFPPdfLink;
        mProposalPDFLink = ProposalPDF;
        mSupplierPDFLink = SupplierPDF;

        mMeetingNameTextView.setText(meetingName);
        mCompanyNameTextView.setText(companyName);
        mProposalIDTextView.setText(proposalID);
        mRFPIDTextView.setText(RFPID);
        mRFPPDFLinkTextView.setText("Click here to view PDF");
        mVenueNameTextView.setText(VenueName);
        mOtherVenueNamesTextView.setText(VenueNames);
        mProposalPDFTextView.setText("Click here to view PDF");
        mCostLocationTextView.setText(CostLocation);
        mDivisionTextView.setText(Division);
        mDivisionIDTextView.setText(DivisonID);
        mDepartmentTextView.setText(Department);
        mAcceptVersionNumberTextView.setText(AcceptVersionNumber);
        mContactNameTextView.setText(ContactName);
        if (CommentForDecline == null || CommentForDecline == "null") {
            mCommentForDeclineTextView.setVisibility(View.GONE);
            mReasonForDeclineTextView.setVisibility(View.GONE);
            mCommentForDeclineStringTextView.setVisibility(View.GONE);
            mReasonForDeclineStringTextView.setVisibility(View.GONE);
        }
        mNoticeBoardNameTextView.setText(NoticeBoardName);
        mCommentForDeclineTextView.setText(CommentForDecline);
        mReasonForDeclineTextView.setText(ReasonForDecline);
        mCommentTextView.setText(Comment);
        mSeriesRFPIDsTextView.setText("N/A");

        String ArrivalDateFormatted = getFormattedDate(ArrivalDate);
        mArrivalDateTextView.setText(ArrivalDateFormatted);
        String DepartureDateFormatted = getFormattedDate(DepartureDate);
        mDepartureDateTextView.setText(DepartureDateFormatted);
        mProposalAttachmentsTextView.setText(ProposalAttachments);
        mCurrencyCodeTextView.setText(CurrencyCode);
        mTotalEstimatedCostExclTaxTextView.setText(TotalEstCostExclTax);
        mCompanyCurrencyTextView.setText(CompanyCurrency);
        mCompanyCurrencySignTextView.setText(CompanyCurrencySign);
        mTotalEstimatedCostExclInCompanyCurrencyTaxTextView.setText(TotalEstCostExclTaxInCompanyCurrency);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mMainHeadLayout.setVisibility(View.VISIBLE);

            }
        }, 1000);


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

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.confirmedbookingsdetails_rfppdflink_textview) {


            if(mRFPPdfLink.equals("#")) {

                Toast.makeText(requireActivity(), "PDF Unavailable", Toast.LENGTH_SHORT).show();
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("RFPPDFLink", mRFPPdfLink);
                Navigation.findNavController(requireView()).navigate(R.id.action_confirmedBookingsDetailsFragment_to_PDFWebView, bundle);


            }
        } else if (v.getId() == R.id.confirmedbookingsdetails_proposalpdflink_textview4) {


            if(mProposalPDFLink.equals("")) {
                Toast.makeText(requireActivity(), "PDF Unavailable", Toast.LENGTH_SHORT).show();

            } else {
                Bundle bundle = new Bundle();

                bundle.putString("ProposalPDFLink", mProposalPDFLink);

                Navigation.findNavController(requireView()).navigate(R.id.action_confirmedBookingsDetailsFragment_to_PDFWebView, bundle);

            }

        }

    }

    private String getFormattedDate(String rawDate) {
        String[] splittedSelectedDateFromTime = rawDate.split("T");

        String[] splittedSelectedDateFromHyphen = splittedSelectedDateFromTime[0].split("-");

        Log.d(TAG, "getFormattedDate: " + splittedSelectedDateFromHyphen[0] + splittedSelectedDateFromHyphen[1] + splittedSelectedDateFromHyphen[2]);

        Log.d(TAG, "getFormattedDate: " + splittedSelectedDateFromHyphen[2] + " " + getMonthNumber(splittedSelectedDateFromHyphen[1]) + " " + splittedSelectedDateFromHyphen[0]);


        String finalFormattedDate = splittedSelectedDateFromHyphen[2] + " " + getMonthNumber(splittedSelectedDateFromHyphen[1]) + " " + splittedSelectedDateFromHyphen[0];

        return finalFormattedDate;
    }

    private String getMonthNumber(String monthNumber) {

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");

        cal.set(Calendar.MONTH, Integer.parseInt(monthNumber));
        String month_name = month_date.format(cal.getTime());

        Log.e("",""+month_name);



        return month_name;
    }
}