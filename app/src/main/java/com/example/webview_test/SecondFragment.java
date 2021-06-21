package com.example.webview_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.webview_test.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private String url = "http://joakimtest.boost.ai/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        WebView view = (WebView) rootView.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDomStorageEnabled(true);
        view.addJavascriptInterface(new WebAppInterface(this), "Android");
        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String targetUrl) {
                if (targetUrl.contains("android_asset") || isBaseUrl(targetUrl)){
                    return false;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(targetUrl));
                startActivity(intent);
                return true;
            }
        });
        //view.loadUrl(url);
        view.loadDataWithBaseURL("https://joakimtest.boost.ai", html, "text/html", "UTF-8", "https://joakimtest.boost.ai");

        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void back() {
        this.getActivity().onBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean isBaseUrl(String targetUrl) {
        Uri uri = Uri.parse(url);
        Uri targetUri = Uri.parse(targetUrl);

        return uri.getHost().equals(targetUri.getHost()) && uri.getPath().equals(targetUri.getPath());
    }

    private String html = "<html lang=\"nb\">\n" +
            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
            "  <meta charset=\"utf-8\" />\n" +
            "  <meta\n" +
            "    name=\"viewport\"\n" +
            "    content=\"width=device-width, initial-scale=1, user-scalable=no\"\n" +
            "  />\n" +
            "  <style type=\"text/css\">\n" +
            "    body {\n" +
            "      margin: 0;\n" +
            "      overflow: hidden;\n" +
            "    }\n" +
            "\n" +
            "    .modal_trigger {\n" +
            "      position: fixed;\n" +
            "      bottom: 1em;\n" +
            "      right: 2em;\n" +
            "      cursor: pointer;\n" +
            "      width: 100px;\n" +
            "    }\n" +
            "  </style>\n" +
            "  <body>\n" +
            "    <img src=\"img/agent.png\" class=\"modal_trigger\" width=\"100px\" />\n" +
            "\n" +
            "    <script src=\"chatPanel/chatPanel.js\"></script>\n" +
            "    <script type=\"application/javascript\">\n" +
            "      var conversationIdKey = \"conversationId\";\n" +
            "\n" +
            "      function attachLauncher(cp) {\n" +
            "        document\n" +
            "          .querySelector(\".modal_trigger\")\n" +
            "          .addEventListener(\"click\", cp.show);\n" +
            "      }\n" +
            "\n" +
            "      function createChat() {\n" +
            "        const options = {\n" +
            "          chatPanel: {\n" +
            "            styling: {\n" +
            "              pace: \"supersonic\",\n" +
            "            },\n" +
            "          },\n" +
            "        };\n" +
            "\n" +
            "        window.boost = window.boostInit(\"/\", options);\n" +
            "\n" +
            "        boost.chatPanel.addEventListener(\"conversationIdChanged\", (e) => {\n" +
            "          if (Android && Android.conversationIdChanged) {\n" +
            "            Android.conversationIdChanged(e.detail.conversationId);\n" +
            "          }\n" +
            "        });\n" +
            "\n" +
            "        boost.chatPanel.addEventListener(\"chatPanelClosed\", (e) => {\n" +
            "          if (Android && Android.chatClosed) {\n" +
            "            Android.chatClosed();\n" +
            "          }\n" +
            "        });\n" +
            "\n" +
            "        boost.chatPanel.show();\n" +
            "        attachLauncher(boost.chatPanel);\n" +
            "      }\n" +
            "\n" +
            "      document.addEventListener(\"DOMContentLoaded\", createChat);\n" +
            "    </script>\n" +
            "  </body>\n" +
            "</html>\n";
}

