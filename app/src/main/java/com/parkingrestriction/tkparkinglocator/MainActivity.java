package com.parkingrestriction.tkparkinglocator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends ActionBarActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.activity_main_webview);
        WebSettings webSettings = myWebView.getSettings();
        //Utilises Javascripts' feature on the device
        webSettings.setJavaScriptEnabled(true);
        //Sets the webpage URL to be loaded by the webview
        myWebView.loadUrl("http://parkingrestriction.com/");
        //Sets the TK Parking App as the default web view instead of browsers on User's mobile
        //myWebView.setWebViewClient(new WebViewClient()); No longer needed but preserved
        //this instance extends the webviewclient
        myWebView.setWebViewClient(new MyAppWebViewClient(){

                @Override
                public void onPageFinished(WebView view, String url){
                    //Hides the Initial splash image
                    findViewById(R.id.tklocatorsplash1).setVisibility(View.GONE);
                    //Shows webView
                    findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
        }
    });

    }

    //Allows user to utilise Back Feature as they would on a browser
    @Override
    public void onBackPressed(){
        if (myWebView.canGoBack()){
            myWebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyAppWebViewClient extends WebViewClient{}

}
