package com.restuarantwebclient;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.RestaurantApplication;

public class LoadMenuWebActivity extends AppCompatActivity {
    private static String PACKAGE_NAME = "com.restaurantproxyserver";
    private static String CLASS_NAME = "com.restaurantproxyserver.service.FetchDataActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       triggerWebServer();
        setContentView(R.layout.activity_restaurant_web);



    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWebView();
    }

    private void loadWebView(){
        WebView htmlWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = htmlWebView.getSettings();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowUniversalAccessFromFileURLs(true);
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowContentAccess(true);
            webSettings.setAppCacheEnabled(true);
        }

        htmlWebView.setWebChromeClient(new WebChromeClient());
        htmlWebView.setWebViewClient(new WebViewClient());
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(true);
        htmlWebView.loadUrl("file:///android_asset/webpage.html");

    }

    private void triggerWebServer(){
        try {
            final Intent intent = new Intent();
            intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
            startActivity(intent);
        }
        catch (ActivityNotFoundException stacktrace){
            stacktrace.printStackTrace();
        }

    }




}
