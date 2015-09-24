package granoeste.net.webviewfragmentexample;

import android.content.MutableContextWrapper;
import android.content.pm.ActivityInfo;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static granoeste.net.webviewfragmentexample.LogUtils.LOGV;
import static granoeste.net.webviewfragmentexample.LogUtils.makeLogTag;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = makeLogTag(WebViewActivity.class);
    private View mContent;
    private WebView mWebView;
    protected boolean mHasSavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGV(TAG, "onCreate()");

        mHasSavedInstanceState = (savedInstanceState != null);

        mContent = (View) getLastCustomNonConfigurationInstance();
        if (mContent == null) {
            LayoutInflater inflater = getLayoutInflater()
                    .cloneInContext(new MutableContextWrapper(this));
            ViewGroup parent = (ViewGroup) getWindow().getDecorView()
                    .findViewById(Window.ID_ANDROID_CONTENT);
            mContent = inflater.inflate(R.layout.activity_webview, parent, false);
            setContentView(mContent);
        } else {
            MutableContextWrapper context = (MutableContextWrapper) mContent.getContext();
            context.setBaseContext(this);
            setContentView(mContent);
        }

        mWebView = (WebView) findViewById(R.id.web_view);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);

        mWebView.setInitialScale(1);
        mWebView.setWebViewClient(mWebViewClient);
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
            setProgressBarIndeterminateVisibility(true);
            setTitle("Now loading...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            setProgressBarIndeterminateVisibility(false);
            setTitle(view.getTitle());
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LOGV(TAG, "onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGV(TAG, "onResume()");

        if (!mHasSavedInstanceState) {
            mWebView.loadUrl("http://www.yahoo.co.jp");
        }

        mWebView.onResume();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        LOGV(TAG, "onRetainCustomNonConfigurationInstance()");
//        return super.onRetainCustomNonConfigurationInstance();

        // We detach our content and return it to be retained
        ((ViewGroup) findViewById(Window.ID_ANDROID_CONTENT)).removeView(mContent);
        return mContent;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LOGV(TAG, "onRestoreInstanceState()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LOGV(TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LOGV(TAG, "onPause()");
        mWebView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LOGV(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LOGV(TAG, "onDestroy()");

        int changingConfigurations = getChangingConfigurations();
        LOGV(TAG, "getChangingConfigurations() :" + changingConfigurations + " (" + String.format("0x%08x", changingConfigurations) + ")");

        if (((ActivityInfo.CONFIG_ORIENTATION | ActivityInfo.CONFIG_SCREEN_SIZE) & changingConfigurations) != 0) {
            LOGV(TAG, "画面回転");
        }

        // Activity再作成以外の破棄の場合。
        if (changingConfigurations == 0) {
            // WebViewを破棄する。
            if (mWebView != null) {
                mWebView.stopLoading();
                mWebView.setWebChromeClient(null);
                mWebView.setWebViewClient(null);
                unregisterForContextMenu(mWebView);
                mWebView.destroy();
                mWebView = null;
            }
        }
    }
}