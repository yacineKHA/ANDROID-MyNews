package com.projet5.mynewsreprog;

import android.os.Bundle;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * @author Yacine
 * @since 2020
 * The class is to get and set a WebView.
 */
public class WebView extends AppCompatActivity {

    private android.webkit.WebView webView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        setToolbar();
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().getJavaScriptEnabled();
        String url = getIntent().getExtras().get("url").toString();
        webView.loadUrl(url);
    }

    /**
     * Set and show the toolbar.
     */
    private void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
            finish();
        return super.onSupportNavigateUp();
    }
}
