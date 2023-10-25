package com.fontiana.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.webView);

        // Configurar a WebView para habilitar JavaScript (se necessário)
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        JavaScriptBrigde jsInterface = new JavaScriptBrigde(this, webView);
        webView.addJavascriptInterface(jsInterface, "Native");

        // Configurar o WebViewClient para abrir links na mesma WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().getPath().contains("testhome")) {
                    webView.loadUrl("file:///android_asset/index.html");
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                if (request.getUrl().getPath().contains("redirecionamentos") && errorResponse.getStatusCode() == 401) {
                    webView.loadUrl("file:///android_asset/index.html");
                }
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });

        // Carregar uma URL na WebView (por exemplo, google.com)
        webView.loadUrl("file:///android_asset/index.html");
    }
}