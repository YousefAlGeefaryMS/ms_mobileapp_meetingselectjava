package com.meetingselect.meetingselect.mainviewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meetingselect.meetingselect.data.model.AcceptConfirmMS2Model.AcceptConfirmResponseModel;
import com.meetingselect.meetingselect.data.model.AccountInformation.AccountInformationResponseModel;
import com.meetingselect.meetingselect.data.model.AccountInformationMS2.AccountInformationMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.ConfirmedBookingsModelMS2.ConfirmedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.FinalizeCartModel.FinalizeCartResponseModel;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.BillingAddress;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.Room;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.TaxLinesItem;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.Venue;
import com.meetingselect.meetingselect.data.model.LoginModelMS2.LoginResponseModelMS2;
import com.meetingselect.meetingselect.data.model.LoginModelMS4.LoginResponseModel;
import com.meetingselect.meetingselect.data.model.PendingBookingsModelMS2.PendingBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.RegisterModelMS4.RegisterResponseModel;
import com.meetingselect.meetingselect.data.model.SavedBookingsMS2.SavedBookingsMS2ResponseModel;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.LocationSearchResponseModel;
import com.meetingselect.meetingselect.data.model.SearchLocationModel.SearchLocationModel;
import com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel.VenueAvailabilityResponseModel;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.RoomsAvailability.VenueDetailsResponseRoomsAvailableModel;
import com.meetingselect.meetingselect.data.model.VenueDetailsModel.VenueDetailsResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModel.VenueResultsInstantBookingResponseModel;
import com.meetingselect.meetingselect.data.model.VenueResultsModel.RFPModel.VenueResultsResponseModel;
import com.meetingselect.meetingselect.main.myrequests.respository.AcceptConfirmBookingsMS2Repo;
import com.meetingselect.meetingselect.main.myrequests.respository.ConfirmedBookingsMS2Repo;
import com.meetingselect.meetingselect.main.myrequests.respository.PendingBookingsMS2Repo;
import com.meetingselect.meetingselect.main.myrequests.respository.SavedBookingsMS2Repo;
import com.meetingselect.meetingselect.main.profile.Account.LoginMS2.LoginPageMS2Repo;
import com.meetingselect.meetingselect.main.profile.Account.LoginMS4.LoginRepo;
import com.meetingselect.meetingselect.main.profile.Account.RegistrationMS4.RegistrationRepo;
import com.meetingselect.meetingselect.main.searchhome.AccountInformationRepo.AccountInformationMS2Repo;
import com.meetingselect.meetingselect.main.searchhome.AccountInformationRepo.AccountInformationRepo;
import com.meetingselect.meetingselect.main.searchhome.SearchVenueModule.repository.LocationSearchRepo;
import com.meetingselect.meetingselect.main.searchhome.SearchVenueModule.repository.SearchVenueRepo;
import com.meetingselect.meetingselect.main.searchhome.VenueDetailsModule.VenueDetailsRepo;
import com.meetingselect.meetingselect.main.searchhome.VenueInstantBookingProcess.FinishingConfirmingBookingRepo;
import com.meetingselect.meetingselect.main.searchhome.VenueInstantBookingProcess.VenueInstantBookingProcessRepo;
import com.meetingselect.meetingselect.main.searchhome.VenueResultsModule.repository.VenueResultsInstantBookingRepo;
import com.meetingselect.meetingselect.main.searchhome.VenueResultsModule.repository.VenueResultsMSFourInstantBookingRepo;
import com.meetingselect.meetingselect.data.model.VenueResultsInstantBookingModelMS4.ResponseModel.VenueResultInstantBookingResponseModel;
import com.meetingselect.meetingselect.main.searchhome.VenueResultsModule.repository.VenueResultsRepo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

import static android.content.Context.MODE_PRIVATE;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "MainViewModel";

    // Search Location
    private SearchVenueRepo mSearchVenueRepo;
    // This is the old location results carrier in the search location module
    private MutableLiveData<List<SearchLocationModel>> mSearchVenueLiveData;

    // This is the BehaviourSubject for Location Results in the search location Module
    private BehaviorSubject<LocationSearchResponseModel> mLocationSearchResults = BehaviorSubject.create();

    // This is the BehaviourSubject for Venue Results for RFP Bookings (MS2 Venues)
    private BehaviorSubject<VenueResultsResponseModel> mVenueResultsBehaviorSubject = BehaviorSubject.create();

    // This is the BehaviourSubject for details of the venue selected
    private BehaviorSubject<VenueDetailsResponseModel> mVenueDetailsBehaviorSubject = BehaviorSubject.create();

    // This is the BehaviourSubject for Rooms in the Venue Details page
    private BehaviorSubject<VenueDetailsResponseRoomsAvailableModel> mVenueDetailsRoomsBehaviorSubject = BehaviorSubject.create();

    // This is the BehaviourSubject for getting Venue Results for InstantBooking Venues (MS4 Venues)
    private BehaviorSubject<VenueResultsInstantBookingResponseModel> mVenueResultsInstantBookingBehaviorSubject = BehaviorSubject.create();
    private BehaviorSubject<VenueResultInstantBookingResponseModel> mVenueResultsInstantBookingBehaviourSubjectMS4 = BehaviorSubject.create();

    private BehaviorSubject<FinalizeCartResponseModel> mFinalizeCartBehaviourSubject = BehaviorSubject.create();

    // Behaviour Subject for the response from the Registration API MS4
    private BehaviorSubject<RegisterResponseModel> mRegistrationResponseModelBehaviourSubject = BehaviorSubject.create();

    // Behaviour Subject for the response from the Login API MS4
    private BehaviorSubject<LoginResponseModel> mLoginResponseModelBehaviourSubject = BehaviorSubject.create();

    // Behaviour Subject for the response from the Login API MS2
    private BehaviorSubject<LoginResponseModelMS2> mLoginMS2ResponseModelBehaviourSubject = BehaviorSubject.create();

    // Behaviour Subject for the response from Account Info MS2
    private BehaviorSubject<AccountInformationMS2ResponseModel> mAccountInformationMS2ResponseModelBehavioruSubject = BehaviorSubject.create();

    // Behaviour Subject for the response from the AccountInformation API to get information about the user
    private BehaviorSubject<AccountInformationResponseModel> mAccountInformationResponseModelBehaviourSubject = BehaviorSubject.create();

    private BehaviorSubject<VenueAvailabilityResponseModel> mVenueAvailabilityResponseModel = BehaviorSubject.create();

    private final MutableLiveData<List<String>> SearchHistoryLocations = new MutableLiveData<>(Collections.emptyList());


    // Behaviour Subject for the response from Saved Bookings
    private BehaviorSubject<SavedBookingsMS2ResponseModel> mSavedBookingsResponseModelBehaviorSubject = BehaviorSubject.create();
    // Behaviour Subject for the response from Pending Bookings
    private BehaviorSubject<PendingBookingsMS2ResponseModel> mPendingBookingsResponseModelBehaviorSubject = BehaviorSubject.create();
    // Behaviour Subject for the response from Confirmed Bookings
    private BehaviorSubject<ConfirmedBookingsMS2ResponseModel> mConfirmedBookingsResponseModelBehaviorSubject = BehaviorSubject.create();


    // Behaviour Subject for the response from the AcceptConfirm API
    private BehaviorSubject<AcceptConfirmResponseModel> mAcceptConfirmResponseModelBehaviourSubject = BehaviorSubject.create();


    //    private LiveData<List<Ven>>
    private final SharedPreferences mSharedPreferences;
    private final Gson gson = new Gson();

    // Shared Preferences
    private static final String SHARED_PREFSLoc = "sharedPrefsLoc";
    private static final String SHARED_PREFSLotLon = "sharedPrefsLotLon";
    private static final String SHARED_PREFSBookingType = "sharedPrefsBookingType";
    private static final String SHARED_PREFSVenueID = "sharedPrefsVenueID";
    private static final String SHARED_PREFSLogState = "sharedPrefsLogState";
    private static final String SHARED_PREFSEmailAddress = "sharedPrefsEmailAddress";
    private static final String SHARED_PREFSDate = "sharedPrefsDate";
    private static final String SHARED_PREFSGuests = "sharedPrefsGuests";
    private static final String SHARED_PREFSIBnumber = "sharedPrefsIBNumber";

    // THIS IS FOR MS2 LOGIN
    private static final String SHARED_PREFSUserToken = "sharedPrefsUserToken";
    private static final String SHARED_PREFSMS2LoginEmail = "sharedPrefsUserEmail";
    private static final String SHARED_PREFSMS2LoginPassword = "sharedPrefsUserPassword";



    // Keys
    private static final String KEYLocationName = "KEYLocationName";
    private static final String KEYLatLon = "KEYLatLon";
    private static final String KEYBookingType = "KEYBookingType";
    private static final String KEYVenueID = "KEYVenueID";
    private static final String KEYLogState = "KEYLogState";
    private static final String KEYEmailAddress = "KEYEmailAddress";
    private static final String KEYDate = "KEYDate";
    private static final String KEYGuests = "KEYGuests";
    private static final String KEYIBNumber = "KEYIBNumber";


    // THIS IS FOR MS2 LOGIN MECHANISM FOR TOKEN
    private static final String KEYUserToken = "KEYUserToken";
    private static final String KEYMS2LoginEmail = "KEYUserEmail";
    private static final String KEYMS2LoginPassword = "KEYUserPassword";




    // This is the Constructor with SharedPreferences that belong to the SearchLocation name in the mainhomepage

    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);

        String LocationNames = mSharedPreferences.getString("SearchHistoryLocations", "[]");

        SearchHistoryLocations.setValue(gson.fromJson(LocationNames, new TypeToken<ArrayList<String>>() {}.getType()));

    }

    // Venue Details Module

    public void getVenueDetailsMVVM(String VenueID) {
        VenueDetailsRepo mVenueDetailsRepo = new VenueDetailsRepo();
        mVenueDetailsRepo.getVenueDetails(VenueID);
        mVenueDetailsBehaviorSubject = mVenueDetailsRepo.getmVenueDetailsBehaviorSubject();
    }

    public void VenueDetailsRoomMVVM(String startDate, String endDate, int startTime, int endTime, int locationID, int seats, String language) {
        VenueDetailsRepo mVenueDetailsRepo = new VenueDetailsRepo();
        mVenueDetailsRepo.getRoomDetails(startDate, endDate, startTime, endTime, locationID, seats, language);
        mVenueDetailsRoomsBehaviorSubject = mVenueDetailsRepo.getmVenueDetailsRoomsBehaviorSubject();

    }

    public BehaviorSubject<VenueDetailsResponseModel> getVenueDetailsBehaviorSubject() {
        return mVenueDetailsBehaviorSubject;
    }

    public BehaviorSubject<VenueDetailsResponseRoomsAvailableModel> getVenueDetailsRoomsBehaviorSubject() {
        return mVenueDetailsRoomsBehaviorSubject;
    }


    // Venue Results Instant Booking
    public void getVenueResultsInstantBookingMVVM (String cityname) {
        VenueResultsInstantBookingRepo venueResultsInstantBookingRepo = new VenueResultsInstantBookingRepo();
        venueResultsInstantBookingRepo.getVenueResultsInstantBooking(cityname);
        mVenueResultsInstantBookingBehaviorSubject = venueResultsInstantBookingRepo.getmVenueResultsInstantBookingBehaviorSubject();
    }

    public BehaviorSubject<VenueResultsInstantBookingResponseModel> getmVenueResultsInstantBookingBehaviorSubject() {
        return mVenueResultsInstantBookingBehaviorSubject;
    }


    // Get Venue Results Instant Booking via MS4

    public void getVenueResultsInstantBookingMS4ViewModel(String locationName, int attendeeNumber, String dateTime, int days) {

        VenueResultsMSFourInstantBookingRepo venueResultsMSFourInstantBookingRepo = new VenueResultsMSFourInstantBookingRepo();
        venueResultsMSFourInstantBookingRepo.getVenueResultsInstantBookingMS4(locationName, attendeeNumber, dateTime, days);
        mVenueResultsInstantBookingBehaviourSubjectMS4 = venueResultsMSFourInstantBookingRepo.getmVenueResultsInstantBookingResponseModelBehaviourSubjectRepository();
    }

    public BehaviorSubject<VenueResultInstantBookingResponseModel> getmVenueResultsInstantBookingBehaviourSubjectMS4() {
        return mVenueResultsInstantBookingBehaviourSubjectMS4;
    }

    // Search VenueResults Module

    public void VenueResultsMVVM(String latitude, String longitude, String isInstantBooking, String language) {
        VenueResultsRepo mVenueResultsRepo = new VenueResultsRepo();
        mVenueResultsRepo.getVenueResults(latitude, longitude, isInstantBooking, language);
        mVenueResultsBehaviorSubject = mVenueResultsRepo.getmVenueResultsBehaviorSubject();
    }

    public BehaviorSubject<VenueResultsResponseModel> getVenueResultsBehaviorSubject() {
        return mVenueResultsBehaviorSubject;
    }

    // Registration Module MS4

    public void UserRegistrationMVVM(String fullName, String companyName, String email, String password, String countrycode, String phoneNumber) {
        RegistrationRepo registrationRepo = new RegistrationRepo();
        registrationRepo.getRegisterUserRepo(fullName, companyName, email, password, countrycode, phoneNumber);
        mRegistrationResponseModelBehaviourSubject = registrationRepo.getmRegisterResults();

    }

    public BehaviorSubject<RegisterResponseModel> getmRegistrationResponseModelBehaviourSubject() {
        return mRegistrationResponseModelBehaviourSubject;
    }

    // Login Module MS4

    public void UserLoginMVVM(String email, String password) {
        LoginRepo loginRepo = new LoginRepo();
        loginRepo.getLoginUserRepo(email, password);
//        mLoginResponseModelBehaviourSubject = loginRepo.getmLoginResultsBehaviourSubject();
    }

    public BehaviorSubject<LoginResponseModel> getmLoginResponseModelBehaviourSubject() {
        return mLoginResponseModelBehaviourSubject;
    }

    // Account Information Module

    public void AccountInformationMVVM(String emailAddress) {
        AccountInformationRepo accountInformationRepo = new AccountInformationRepo();
        accountInformationRepo.getAccountInformationRepo(emailAddress);
        mAccountInformationResponseModelBehaviourSubject = accountInformationRepo.getmAccountInformationBehaviourSubjectRepo();

    }

    public BehaviorSubject<AccountInformationResponseModel> getmAccountInformationResponseModelBehaviourSubject() {
        return mAccountInformationResponseModelBehaviourSubject;
    }


    // Login Module MS2

    public void UserLoginMS2MVVM(String username, String password) {
        LoginPageMS2Repo loginPageMS2Repo = new LoginPageMS2Repo();
        loginPageMS2Repo.getLoginMS2Repo(username, password);
        mLoginMS2ResponseModelBehaviourSubject = loginPageMS2Repo.getmLoginMS2ResponseModelBehaviourSubjectRepo();
    }

    public BehaviorSubject<LoginResponseModelMS2> getmLoginMS2ResponseModelBehaviourSubject() {
        return mLoginMS2ResponseModelBehaviourSubject;
    }

    // Account information for MS2 API

    public void getAccountInformationMS2(String token) {
        AccountInformationMS2Repo accountInformationMS2Repo = new AccountInformationMS2Repo();
        accountInformationMS2Repo.getAccountInformationMS2(token);
        mAccountInformationMS2ResponseModelBehavioruSubject = accountInformationMS2Repo.getmAccountInformationMS2ResponseModelRepoBehaviourSubject();
    }

    public BehaviorSubject<AccountInformationMS2ResponseModel> getmAccountInformationMS2ResponseModelBehavioruSubject() {
        return mAccountInformationMS2ResponseModelBehavioruSubject;
    }

    // Search Location Module

    // Using MS2 API
    public void SearchLocation(String location) {
        mSearchVenueRepo = new SearchVenueRepo();
        mSearchVenueRepo.getLocationResults(location);
        mSearchVenueLiveData = mSearchVenueRepo.getLocationResultsLiveData();
    }

    public LiveData<List<SearchLocationModel>> getSearchVenueLiveData() {
        return mSearchVenueLiveData;
    }

    // Using MS4 API

    public void LocationSearch(String location) {
        LocationSearchRepo mLocationSearchRepo = new LocationSearchRepo();
        mLocationSearchRepo.getLocationSearchResultsRepository(location);
        mLocationSearchResults = mLocationSearchRepo.getmLocationSearchResponseModelBehaviourSubject();
    }

    public BehaviorSubject<LocationSearchResponseModel> getmLocationSearchResults() {
        return mLocationSearchResults;
    }

    public void AvailabilitySelect(int VenueLocationID, int SearchId, int SpaceId, int CurrencyID, int CRC, double price, String hash) {
        VenueInstantBookingProcessRepo venueInstantBookingProcessRepo = new VenueInstantBookingProcessRepo();
        venueInstantBookingProcessRepo.getAvailabilitySelectRepo(VenueLocationID, SearchId, SpaceId, CurrencyID, CRC, price, hash);
        mVenueAvailabilityResponseModel = venueInstantBookingProcessRepo.getmVenueResultsInstantBookingResponseModel();
    }

    public BehaviorSubject<VenueAvailabilityResponseModel> getmVenueAvailabilityResponseModel() {
        return mVenueAvailabilityResponseModel;
    }

    public void FinalizeCart(String CartKey) {
        FinishingConfirmingBookingRepo finishingConfirmingBookingRepo = new FinishingConfirmingBookingRepo();
        finishingConfirmingBookingRepo.getFinalizeCart(CartKey);
        mFinalizeCartBehaviourSubject = finishingConfirmingBookingRepo.getmFinalizeCartModel();
    }

    public void SendBookingToTwoPointZero(String bookingId,
                                          String bookingExternalId,
                                          String bookingNumber,
                                          String bookingDate,
                                          String meetingName,
                                          String arrivalDate,
                                          String departureDate,
                                          int attendees,
                                          Venue venue,
                                          Room room,
                                          List<Object> amenities,
                                          List<TaxLinesItem> taxLines,
                                          String currencyCode,
                                          int totalPriceExcludingTax,
                                          int totalTax,
                                          int totalPriceIncludingTax,
                                          int plannerId,
                                          List<Object> companyReferences,
                                          BillingAddress billingAddress) {

        FinishingConfirmingBookingRepo finishingConfirmingBookingRepo = new FinishingConfirmingBookingRepo();
        finishingConfirmingBookingRepo.sendBookingToTwoPointZero(bookingId, bookingExternalId, bookingNumber, bookingDate, meetingName, arrivalDate, departureDate, attendees, venue, room, amenities, taxLines, currencyCode, totalPriceExcludingTax, totalTax, totalPriceIncludingTax, plannerId, companyReferences, billingAddress);

    }

    public BehaviorSubject<FinalizeCartResponseModel> getmFinalizeCartBehaviourSubject() {
        return mFinalizeCartBehaviourSubject;
    }

    // Location history in the searchlocationpage
    public void addLocationToHistory(String locationName) {

        List<String> searchHistoryLocation = new ArrayList<>(Objects.requireNonNull(this.SearchHistoryLocations.getValue()));

        if(searchHistoryLocation.contains(locationName)) {
            searchHistoryLocation.remove(locationName);
        }
        searchHistoryLocation.add(0, locationName);

        if(searchHistoryLocation.size() == 6) {
            searchHistoryLocation.remove(5);
        }
        
        SearchHistoryLocations.setValue(Collections.unmodifiableList(searchHistoryLocation));

        mSharedPreferences.edit().putString("SearchHistoryLocations", gson.toJson(searchHistoryLocation)).apply();

    }

    public LiveData<List<String>> getHistory() {
        return SearchHistoryLocations;
    }


    // Get SavedBookingsMS2
    public void getSavedBookingsMVVM(String token, String meetingName, int pageIndex, int pageSize) {
        SavedBookingsMS2Repo savedBookingsMS2Repo = new SavedBookingsMS2Repo();
        savedBookingsMS2Repo.getSavedBookingsMS2Repo(token, meetingName, pageIndex, pageSize);
        mSavedBookingsResponseModelBehaviorSubject = savedBookingsMS2Repo.getmSavedBookingsMS2ResponseBehaviourSubject();
    }

    public BehaviorSubject<SavedBookingsMS2ResponseModel> getmSavedBookingsResponseModelBehaviorSubject() {
        return mSavedBookingsResponseModelBehaviorSubject;
    }

    // Get PendingBookingsMS2
    public void getPendingBookingsMS2(String token, String meetingName, int pageIndex, int pageSize) {
        PendingBookingsMS2Repo pendingBookingsMS2Repo = new PendingBookingsMS2Repo();
        pendingBookingsMS2Repo.getPendingBookingsMS2(token, meetingName, pageIndex, pageSize);
        mPendingBookingsResponseModelBehaviorSubject = pendingBookingsMS2Repo.getmPendingBookingsResponseModelMS2BehaviourSubject();
    }

    public BehaviorSubject<PendingBookingsMS2ResponseModel> getmPendingBookingsResponseModelBehaviorSubject() {
        return mPendingBookingsResponseModelBehaviorSubject;
    }

    // Get ConfirmedBookingsMS2
    public void getConfirmedBookingsMVVM(String token, String meetingName, int pageIndex, int pageSize) {
        ConfirmedBookingsMS2Repo confirmedBookingsMS2Repo = new ConfirmedBookingsMS2Repo();
        confirmedBookingsMS2Repo.getConfirmedBookingsMS2(token, meetingName, pageIndex, pageSize);
        mConfirmedBookingsResponseModelBehaviorSubject = confirmedBookingsMS2Repo.getmConfirmedBookingsResponseModelBehaviourSubject();
    }
    public BehaviorSubject<ConfirmedBookingsMS2ResponseModel> getmConfirmedBookingsResponseModelBehaviorSubject() {
        return mConfirmedBookingsResponseModelBehaviorSubject;
    }

    // Get AcceptConfirm
    public void getAcceptConfirmBookingMS2(String token, String proposalID) {
        AcceptConfirmBookingsMS2Repo acceptConfirmBookingsMS2Repo = new AcceptConfirmBookingsMS2Repo();
        acceptConfirmBookingsMS2Repo.getConfirmedBookings(token, proposalID);
        mAcceptConfirmResponseModelBehaviourSubject = acceptConfirmBookingsMS2Repo.getmAcceptConfirmBehaviourSubject();
    }

    public BehaviorSubject<AcceptConfirmResponseModel> getmAcceptConfirmResponseModelBehaviourSubject() {
        return mAcceptConfirmResponseModelBehaviourSubject;
    }

    // This is used to transfer information from the location searching part to the main searchhomepage
    // The reason I used sharedpreferences is because since we are going to keep it in sharedpreferences anyway
    // It would be useful to just use the tools available for that.

// Search Homepage
    // Save Section for SearchHomepage Location and Latitude/Longitude and BookingType and VenueId

    public void saveLocationName(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSLoc, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYLocationName, text);
        editor.apply();
    }

    public void saveLatLon(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSLotLon, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYLatLon, text);
        editor.apply();
    }

    public void saveBookingType(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSBookingType, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYBookingType, text);
        editor.apply();
    }

    public void saveVenueId(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSVenueID, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYVenueID, text);
        editor.apply();
    }

    // Logged In State

    public void saveLogState(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSLogState, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYLogState, text);
        editor.apply();
    }

    public void saveEmailAddress(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSEmailAddress, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYEmailAddress, text);
        editor.apply();
    }

    public void saveDate(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSDate, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYDate, text);
        editor.apply();
    }

    public void saveGuest(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSGuests, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYGuests, text);
        editor.apply();
    }

    public void saveInstantBookingNumber(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSIBnumber, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYIBNumber, text);
        editor.apply();
    }

    public void saveUserToken(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSUserToken, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYUserToken, text);
        editor.apply();

    }

    public void saveUserEmail(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSMS2LoginEmail, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEYMS2LoginEmail, text);
        editor.apply();

    }

    public void saveUserPassword(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSMS2LoginPassword, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        Log.d(TAG, "saveUserPassword: " + text);
        editor.putString(KEYMS2LoginPassword, text);
        editor.apply();
    }



    // Load Section for SharedPreferences

    public String loadLocationName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSLoc, MODE_PRIVATE);
        String text = sharedPreferences.getString(KEYLocationName, "Search for the location of your Next Meeting");
        return text;
    }

    public String loadLatLon(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSLotLon, MODE_PRIVATE);
        String latlon = sharedPreferences.getString(KEYLatLon, "Search for the location of your Next Meeting");
        Log.d(TAG, "loadLatLon: " + latlon);
        return latlon;
    }

    public String loadBookingType(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSBookingType, MODE_PRIVATE);
        String bookingType = sharedPreferences.getString(KEYBookingType, "Request for Proposal");
        return bookingType;
    }

    public String loadVenueID(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSVenueID, MODE_PRIVATE);
        String venueid = sharedPreferences.getString(KEYVenueID, "");
        return venueid;
    }

    public String loadLogState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSLogState, MODE_PRIVATE);
        String LogState = sharedPreferences.getString(KEYLogState, "false");
        return LogState;
    }

    public String loadEmailAddress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSEmailAddress, MODE_PRIVATE);
        String EmailAddress = sharedPreferences.getString(KEYEmailAddress, "false");
        return EmailAddress;
    }

    public String loadDate(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSDate, MODE_PRIVATE);
        String date = sharedPreferences.getString(KEYDate, "false");
        return date;
    }

    public String loadGuest(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSGuests, MODE_PRIVATE);
        String guest = sharedPreferences.getString(KEYGuests, "3");
        return guest;
    }

    public String loadInstantBookingNumber(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSIBnumber, MODE_PRIVATE);
        String IBNumber = sharedPreferences.getString(KEYIBNumber, "0");
        return IBNumber;
    }

    public String loadUserToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSUserToken, MODE_PRIVATE);
        String UserToken = sharedPreferences.getString(KEYUserToken, "0");
        return UserToken;
    }

    public String loadUserEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSMS2LoginEmail, MODE_PRIVATE);
        String UserEmailMS2 = sharedPreferences.getString(KEYMS2LoginEmail, "0");
        return UserEmailMS2;
    }

    public String loadUserPassword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFSMS2LoginPassword, MODE_PRIVATE);
        String UserPasswordMS2 = sharedPreferences.getString(KEYMS2LoginPassword, "0");
        return UserPasswordMS2;
    }







}
