package com.why.wyhuiying1511a;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        web = findViewById(R.id.web);
        //接收传过来的URL
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        //加载
        web.loadUrl(url);//加载网址到视图
        //设置
        web.setWebViewClient(new WebViewClient());
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

    }
    }


