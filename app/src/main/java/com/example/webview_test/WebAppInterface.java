package com.example.webview_test;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.webview_test.databinding.FragmentSecondBinding;

public class WebAppInterface {
    SecondFragment mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(SecondFragment c) {
        mContext = c;
    }

    @JavascriptInterface
    public void conversationIdChanged(String toast) {
        Toast.makeText(mContext.getContext(), "Conversation id changed to: " + toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void chatClosed() {
        mContext.back();
    }
}
