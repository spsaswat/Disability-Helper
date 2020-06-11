package com.example.webview_testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText ed1;

    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
        load_view();
    }
    public void load_view() {
        String url = "https://android-owasp-hackathon.herokuapp.com/";

        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        wv1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            wv1.getSettings().setAllowFileAccessFromFileURLs(true);
            wv1.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        if(WebViewStateHolder.INSTANCE.getBundle() == null) { //this works only on single instance of webview, use a map with TAG if you need more
            wv1.loadUrl(url);
        } else {
            wv1.restoreState(WebViewStateHolder.INSTANCE.getBundle());
        }
    }

    private enum WebViewStateHolder
    {
        INSTANCE;

        private Bundle bundle;

        public void saveWebViewState(WebView webView)
        {
            bundle = new Bundle();
            webView.saveState(bundle);
        }

        public Bundle getBundle()
        {
            return bundle;
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
