package com.meetingselect.meetingselect.main.searchhome.VenueInstantBookingProcess;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.meetingselect.meetingselect.R;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.Address;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.BillingAddress;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.Room;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.TaxLinesItem;
import com.meetingselect.meetingselect.data.model.InstantBookingTwoPointZeroModel.Venue;
import com.meetingselect.meetingselect.data.model.VenueAvailabilitySelectModel.VenueAvailabilityResponseModel.SpacesItem;
import com.meetingselect.meetingselect.mainviewmodel.MainViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class FinishingConfirmingBooking extends Fragment {


    private static final String TAG = "FinsihBookingRepo";

    private CompositeDisposable mDisposable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finishing_confirming_booking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            String strDate = sdf.format(c.getTime());

            String CartKey = getArguments().get("CartKey").toString();
            String MeetingName = (String) getArguments().get("MeetingNameIB").toString();
            String FirstName = (String) getArguments().get("FirstNameIB").toString();
            String LastName = (String) getArguments().get("LastNameIB").toString();
            String AddressLineOne = (String) getArguments().get("AddressLineOneIB").toString();
            String AddressLineTwo = (String) getArguments().get("AddressLineTwoIB").toString();
            String Zipcode = (String) getArguments().get("ZipcodeIB").toString();
            String CityName = (String) getArguments().get("CityNameIB").toString();
            String CountryName = (String) getArguments().get("CountryNameIB").toString();
            String CountryCode = (String) getArguments().get("CountryCodeIB").toString();
            String VenueAddress = (String) getArguments().get("VenueAddressIB").toString();
            String BookingDate = (String) getArguments().get("BookingDateIB").toString();

            // Time Format Edit
            String StartTimeRaw = (String) getArguments().get("StartTimeIB").toString();
            String[] startTimeSplit = StartTimeRaw.split(":");
            String StartTime = checkDigit(Integer.parseInt(startTimeSplit[0])) + ":" + checkDigit(Integer.parseInt(startTimeSplit[1])) + ":" + "00";

            Log.d(TAG, "onViewCreated: " + StartTime);
            Log.d(TAG, "onViewCreated: " + strDate);


            String EndTimeRaw = (String) getArguments().get("EndTimeIB");
            String[] endTimeSplit = EndTimeRaw.split(":");
            String EndTime = checkDigit(Integer.parseInt(endTimeSplit[0])) + ":" + checkDigit(Integer.parseInt(endTimeSplit[1])) + ":" + "00";

            Log.d(TAG, "onViewCreated: " + CartKey);
            Log.d(TAG, "onViewCreated: " + MeetingName);
            Log.d(TAG, "onViewCreated: " + FirstName);
            Log.d(TAG, "onViewCreated: " + LastName);
            Log.d(TAG, "onViewCreated: " + AddressLineOne);
            Log.d(TAG, "onViewCreated: " + AddressLineTwo);
            Log.d(TAG, "onViewCreated: " + Zipcode);
            Log.d(TAG, "onViewCreated: " + CityName);
            Log.d(TAG, "onViewCreated: " + CountryName);
            Log.d(TAG, "onViewCreated: " + CountryCode);
            Log.d(TAG, "onViewCreated: " + VenueAddress);

            sendBookingToTwoPointZero(CartKey, MeetingName, StartTime, EndTime, VenueAddress, strDate, AddressLineOne, Zipcode, CityName, CountryCode, CountryName, StartTimeRaw, EndTimeRaw, BookingDate);


        } catch (NullPointerException e) {

            Log.d(TAG, "onViewCreated: ");
        }
    }

    private void sendBookingToTwoPointZero(String CartKey,
                                           String MeetingName,
                                           String ArrivalTime,
                                           String DepartureTime,
                                           String VenueAddress,
                                           String BookingTime,
                                           String AddressLine,
                                           String Zipcode,
                                           String CityName,
                                           String CountryCode,
                                           String CountryName,
                                           String StartTimeRaw,
                                           String EndTimeRaw,
                                           String BookingDate) {

        MainViewModel mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mMainViewModel.FinalizeCart(CartKey);

        mDisposable = new CompositeDisposable();

        Disposable disposable = mMainViewModel.getmFinalizeCartBehaviourSubject().subscribe(finalizeCartResponseModelTest -> {



            // Arrival Date
            String ArrivalDateRaw = finalizeCartResponseModelTest.getStartDate();
            String[] arrivalDateSplit = ArrivalDateRaw.split("T");

            String[] arrivalDateMoreSplit = arrivalDateSplit[0].split("-");
            String ArrivalDate = arrivalDateMoreSplit[0] + ":" + arrivalDateMoreSplit[1] + ":" + arrivalDateMoreSplit[2] + " " + ArrivalTime;

            // Departure Date
            String DepartureDateRaw = finalizeCartResponseModelTest.getEndDate();
            String[] departureDateSplit = DepartureDateRaw.split("T");
            String[] departureDateMoreSplit = departureDateSplit[0].split("-");
            String DepartureDate = departureDateMoreSplit[0] + ":" + departureDateMoreSplit[1] + ":" + departureDateMoreSplit[2] + " " + DepartureTime;

            // Venue Model

            // Addres Within VenueModel
            Address address = new Address(VenueAddress, "", "", "", "");
            Venue venue = new Venue("", finalizeCartResponseModelTest.getLocationName(), address, "", "");

            // Room Model
            Room room = new Room(finalizeCartResponseModelTest.getSpaces().get(0).getSpaceName(),
                    "U-Shape",
                    String.valueOf(finalizeCartResponseModelTest.getSpaces().get(0).getCurrencyId()),
                    finalizeCartResponseModelTest.getSpaces().get(0).getPricePerSeat(),
                    "person",
                    finalizeCartResponseModelTest.getTotalSeats(),
                    finalizeCartResponseModelTest.getSpaces().get(0).getPriceTotal());

            // TaxLine Model
//            TaxLinesItem taxLinesItem = new TaxLinesItem(0, "EUR", 0);

            Log.d(TAG, "sendBookingToTwoPointZero: Test");

            // Billing Address
            BillingAddress billingAddress = new BillingAddress(AddressLine, Zipcode, CityName, CountryCode, CountryName);

            List<TaxLinesItem> taxLinesItems = Collections.emptyList();
            List<Object> Amenities = Collections.emptyList();
            List<Object> CompanyReferences = Collections.emptyList();

            mMainViewModel.SendBookingToTwoPointZero(CartKey,
                    String.valueOf(finalizeCartResponseModelTest.getId()),
                    "MSI5396",
                    BookingTime,
                    MeetingName,
                    ArrivalDate,
                    DepartureDate,
                    finalizeCartResponseModelTest.getTotalSeats(),
                    venue,
                    room,
                    Amenities,
                    taxLinesItems,
                    "EUR",
                    0,
                    0,
                    0,
                    363676,
                    CompanyReferences,
                    billingAddress
            );


            // DisplayResults
            onSuccessfulBooking("MSI5396",
                    String.valueOf(finalizeCartResponseModelTest.getId()),
                    finalizeCartResponseModelTest.getLocationName(),
                    VenueAddress,
                    BookingDate,
                    StartTimeRaw,
                    EndTimeRaw,
                    finalizeCartResponseModelTest.getSpaces().get(0).getSpaceName(),
                    String.valueOf(finalizeCartResponseModelTest.getTotalSeats()),
                    String.valueOf(finalizeCartResponseModelTest.getSpaces().get(0).getPricePerSeat()),
                    String.valueOf(finalizeCartResponseModelTest.getTotalExclTax()),
                    String.valueOf(finalizeCartResponseModelTest.getTotalInclTax()),
                    String.valueOf(finalizeCartResponseModelTest.getTaxTotals().get(0).getTotal()));

        });

        // finalizeCartResponseModelTest.getTotalInclTax())

        mDisposable.add(disposable);

    }


    private void onSuccessfulBooking(String BookingNumber,
                                     String ReservationID,
                                     String LocationName,
                                     String LocationAddress,
                                     String BookingDate,
                                     String StartTime,
                                     String EndTime,
                                     String SpaceName,
                                     String Seats,
                                     String PricePerSeat,
                                     String TotalExclTax,
                                     String TotalInclTax,
                                     String TaxTotal) {


        ConstraintLayout VenueDetails = requireView().findViewById(R.id.finishingconfirmingbooking_secondmainlayout_constraintlayout);

        ImageView Checkmarkgif = requireView().findViewById(R.id.finishconfirmingbooking_checkmarkgif_imageview);
        TextView VenueNameTextView = requireView().findViewById(R.id.finishconfirmingbooking_venuename_textview);
        TextView VenueAddressTextView = requireView().findViewById(R.id.finishconfirmingbooking_venueaddress_textview);
        TextView BookingDateTextView = requireView().findViewById(R.id.finishconfirmingbooking_dateofbooking_textview);
        TextView StartTimeTextView = requireView().findViewById(R.id.finishconfirmingbooking_starttime_textview);
        TextView EndTimeTextView = requireView().findViewById(R.id.finishconfirmingbooking_endtime_textview);
        TextView RoomNameTextView = requireView().findViewById(R.id.finishconfirmingbooking_roomname_textview);
        TextView AttendeeNumberTextView = requireView().findViewById(R.id.finishconfirmingbooking_numberofattendees_textview);
        TextView PricePerSeatTextView = requireView().findViewById(R.id.finishconfirmingbooking_roomrental_textview);
        TextView TotalExclTaxTextView = requireView().findViewById(R.id.finishconfirmingbooking_totalexctax_textview);
        TextView TotalTax = requireView().findViewById(R.id.finishconfirmingbooking_totaltax_textview);
        TextView TotalInclTaxTextView = requireView().findViewById(R.id.finishconfirmingbooking_totalfeeinlcvat_textview);
        TextView BookingNumberTextView = requireView().findViewById(R.id.finishconfirmingbooking_bookingnumber_textview);
        TextView ReservationIDTextView = requireView().findViewById(R.id.finishconfirmingbooking_reservationid_textview);

        BookingNumberTextView.setText(BookingNumber);
        ReservationIDTextView.setText(ReservationID);
        VenueNameTextView.setText(LocationName);
        VenueAddressTextView.setText(LocationAddress);
        BookingDateTextView.setText(BookingDate);
        StartTimeTextView.setText(StartTime);
        EndTimeTextView.setText(EndTime);
        RoomNameTextView.setText(SpaceName);
        AttendeeNumberTextView.setText(Seats);
        PricePerSeatTextView.setText("€" + PricePerSeat);
        TotalExclTaxTextView.setText("€" + TotalExclTax);
        TotalTax.setText(TaxTotal + "%");
        TotalInclTaxTextView.setText("€" + TotalInclTax);

        VenueDetails.setVisibility(View.VISIBLE);

        Glide.with(this).asGif().load(R.drawable.checkmarkgif).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                resource.setLoopCount(1);
                resource.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        //do whatever after specified number of loops complete
                    }
                });
                return false;
            }
        }).into(Checkmarkgif);

    }

    private String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}