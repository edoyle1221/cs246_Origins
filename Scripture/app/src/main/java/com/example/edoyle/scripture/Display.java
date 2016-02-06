package com.example.edoyle.scripture;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Arrays;

public class Display extends AppCompatActivity  {
    private WebView mWebView;
    private static String url;
    private int numParts;
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private void constructUrl() {
        String parts[] = MainActivity.scripture.split(" ");
        numParts = parts.length;
        String bookCheck;
        if (parts.length == 3)
            bookCheck = parts[0];
        else if (parts.length == 4)
            bookCheck = parts[0] + " " + parts[1];
        else
            bookCheck = parts[0] + " " + parts[1] + " " + parts [2];

        if (Arrays.asList(MainActivity.OT).contains(bookCheck.toUpperCase())){
            url += "ot/";
            url += MainActivity.myMap.get(Arrays.asList(MainActivity.SCRIPTURES).indexOf(bookCheck.
                    toUpperCase()));
        }
        else if (Arrays.asList(MainActivity.NT).contains(bookCheck.toUpperCase())){
            url += "nt/";
            url += MainActivity.myMap.get(Arrays.asList(MainActivity.SCRIPTURES).indexOf(bookCheck.
                    toUpperCase()));
        }
        else if (Arrays.asList(MainActivity.POGP).contains(bookCheck.toUpperCase())){
            url += "pgp/";
            url += MainActivity.myMap.get(Arrays.asList(MainActivity.SCRIPTURES).indexOf(bookCheck.
                    toUpperCase()));
        }
        else if (Arrays.asList(MainActivity.BOM).contains(bookCheck.toUpperCase())){
            url += "bofm/";
            url += MainActivity.myMap.get(Arrays.asList(MainActivity.SCRIPTURES).indexOf(bookCheck.
                    toUpperCase()));
        }
        else if (Arrays.asList(MainActivity.DC).contains(bookCheck.toUpperCase())){
            url += "dc-testament/dc";
        }
        url += "/" + parts[numParts - 2].trim() + "." + parts[numParts - 1].trim() + "?lang=eng#";
        url += parts[numParts - 1].trim().split("-")[0];
        /// /url += Integer.toString(Integer.parseInt(parts[numParts - 1].trim()) - 1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                       // Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                        finish();
                    }

                    // Right to left swipe action
                    else
                    {
                        // Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        url = "https://www.lds.org/scriptures/";
        constructUrl();
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
              //mWebView.loadUrl("http://www.google.com/");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show ();
        mWebView.loadUrl(url);
        TextView tv = (TextView)findViewById(R.id.scriptureView);
        String parts[] = MainActivity.scripture.split(" ");
        if (parts.length == 3)
            tv.setText(parts[0].trim() + " " + parts[1].trim() + ":" + parts[2].trim());
        else if (parts.length == 4)
            tv.setText(parts[0].trim() + " " + parts[1].trim() + " " + parts[2].trim() +
                    ":" + parts[3].trim());
        else
            tv.setText(parts[0].trim() + " " + parts[1].trim() + " " + parts[2].trim() +
                    " " + parts[3].trim() + ":" + parts[4].trim());

    }

}
