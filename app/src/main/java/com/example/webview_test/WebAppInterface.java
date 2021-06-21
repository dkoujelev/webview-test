package com.example.webview_test;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    SecondFragment fragment;

    WebAppInterface(SecondFragment f) {
        fragment = f;
    }

    @JavascriptInterface
    public void conversationIdChanged(String toast) {
        Toast.makeText(fragment.getContext(), "Conversation id changed to: " + toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void chatClosed() {
        fragment.back();
    }
}
