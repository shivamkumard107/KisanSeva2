package com.uttarakhand.kisanseva2.activities.generalInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.uttarakhand.kisanseva2.R;

public class WebActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String link=getIntent().getStringExtra("link");
        String title_temp=getIntent().getStringExtra("title");

        if(title_temp!=null){
            getSupportActionBar().setTitle(title_temp);
        }

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);

        Toast.makeText(WebActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();

        if(link==null){
            webView.loadUrl("http://www.google.com");
        }else{
            webView.loadUrl(link);
        }
    }
}
