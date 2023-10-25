package com.fontiana.mobilebanking;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class JavaScriptBrigde {
    private Context context;
    private WebView webView;

    public JavaScriptBrigde(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }

    @JavascriptInterface
    public void execute(String estruturaNativa) throws JSONException {
        JSONObject jsonObj = new JSONObject(estruturaNativa);
        String metodo = jsonObj.getString("metodo");
        String modulo = jsonObj.getString("modulo");
        String chave = jsonObj.getString("chave");
        JSONObject paramsObj = new JSONObject(jsonObj.getString("params"));

        webView.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Log.i("TESTE_NATIVO", "javascript:window.callbackSucesso('" + modulo + "', '" + chave + "')");
                webView.loadUrl("javascript:window.callbackSucesso('" + modulo + "', '" + chave + "')");
            }
        });

        if (modulo == "Alerta") {
            String mensagem = paramsObj.getString("mensagem");
            if (metodo == "Toast") {
                Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
