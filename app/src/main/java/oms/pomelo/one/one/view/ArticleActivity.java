package oms.pomelo.one.one.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import oms.pomelo.one.MyWebView;
import oms.pomelo.one.R;
import oms.pomelo.one.base.BaseActivity;
import oms.pomelo.one.utils.StatusBarUtils;

public class ArticleActivity extends BaseActivity {

    private MyWebView mWebView;
    private WebSettings mWebSettings;
    private String webUrl;
    private String mType;
    private TextView mTvTitle;
    private ImageView mIvBack;
    private ConstraintLayout mTitleBar;
    private TextView mTvLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        StatusBarUtils.setStatusBarColor(this, Color.WHITE);
        StatusBarUtils.StatusBarLightMode(this);

        initView();
        initWebView();
        initListener();
    }

    private void initView() {
        mType = getIntent().getStringExtra("type");
        mTvTitle = findViewById(R.id.tvTitle);
        mIvBack = findViewById(R.id.ivLeft);
        mTitleBar = findViewById(R.id.titleBar);
        mTvLoading = findViewById(R.id.tvLoading);
        if (!TextUtils.isEmpty(mType)) {
            String text = mType.substring(2, 4);
            if (mType.equals("- ONE电台 -")) {
                mTitleBar.setVisibility(View.GONE);
            }
            mTvTitle.setText(text);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webUrl = getIntent().getStringExtra("webUrl");
        mWebView = findViewById(R.id.webView);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true); //可以与Js进行交互
        //设置自适应屏幕，两者合用
        mWebSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        mWebSettings.setLoadWithOverviewMode(true); //缩放至屏幕的大小
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存

        if (webUrl != null) {
            mWebView.loadUrl(webUrl);
        }
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) {
                    return false;
                }
                try {
                    if (!url.startsWith("http:") || !url.startsWith("https:")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设置加载开始的操作（如loading）
                mTvLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //设置加载结束的操作（如结束loading）
//                hideHeader();
                mTvLoading.setVisibility(View.GONE);
            }
        });

        //设置WebChromeClient类
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取网站标题
            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    mTvLoading.setText("正在加载..." + progress);
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                    mTvLoading.setText(progress);
                }
            }
        });

    }

    private void initListener() {
        mWebView.setOnDrawListener(new MyWebView.OnDrawListener() {
            @Override
            public void onDrawCallBack() {
                hideHeader();
                hideFooter();
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void hideHeader() {
        try {
            String javaScript = "javascript:function hideHeader() { "
                    + "document.getElementsByClassName('header')[0].style.display='none'"
                    + "}";
            mWebView.loadUrl(javaScript);
            mWebView.loadUrl("javaScript:hideHeader();");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideFooter() {
        try {
            String javaScript = "javascript:function hideFooter() { "
                    + "document.getElementsByClassName('footer')[0].style.display='none'"
                    + "}";
            mWebView.loadUrl(javaScript);
            mWebView.loadUrl("javaScript:hideFooter();");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mWebSettings.setJavaScriptEnabled(false); //避免造成资源浪费
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebSettings.setJavaScriptEnabled(true);
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
