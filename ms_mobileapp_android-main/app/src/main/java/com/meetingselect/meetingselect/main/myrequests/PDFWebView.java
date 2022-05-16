package com.meetingselect.meetingselect.main.myrequests;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.meetingselect.meetingselect.R;

public class PDFWebView extends Fragment {

    private static final String TAG = "PDFWebView";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pdf_web_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView webView = view.findViewById(R.id.pdfviewer_webview_webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);


        String rfppdfLink = (String) getArguments().getString("RFPPDFLink");

        String proposalPDFLink = (String) getArguments().getString("ProposalPDFLink");

        String proposalDetailsPDFLink = (String) getArguments().getString("PDFLinkPendingDetails");


        if(!(rfppdfLink == null)) {
            webView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + rfppdfLink);

                }
            }, 250);

        } else if(!(proposalPDFLink == null)) {
            webView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + proposalPDFLink);

                }
            }, 250);

        } else if(!(proposalDetailsPDFLink == null)) {
            webView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + proposalDetailsPDFLink);

                }
            }, 250);

        }
//        try {

//            Log.d(TAG, "onViewCreated: " + rfppdfLink);
//
//        } catch (NullPointerException e) {
//            Log.e(TAG, "onViewCreated: ", e);
//        }
//
//        try {

//
//            Log.d(TAG, "onViewCreated: " + proposalPDFLink);
//
//        } catch (NullPointerException e) {
//            Log.e(TAG, "onViewCreated: ", e);
//        }
////
//        try {
//
//            Log.d(TAG, "onViewCreated: " + supplierPDFLink);
//
//        } catch (NullPointerException e) {
//            Log.e(TAG, "onViewCreated: ", e);
//        }
    }
}