package granoeste.net.webviewfragmentexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static granoeste.net.webviewfragmentexample.LogUtils.LOGV;
import static granoeste.net.webviewfragmentexample.LogUtils.makeLogTag;

public class WebViewFragment extends Fragment{
    private static final String TAG = makeLogTag(WebViewFragment.class);
    protected boolean mHasSavedInstanceState;
    private View mContent;
    WebView mWebView;

    public static final String PARAM_URL = "url";

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGV(TAG, "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LOGV(TAG, "onCreateView()");

        // Activity再生成時にFragmentを破棄させない。
        setRetainInstance(true);

        mHasSavedInstanceState = (savedInstanceState != null);

        if (mContent == null) {
            mContent = inflater.inflate(R.layout.fragment_webview, container, false);
        }
        return mContent;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LOGV(TAG, "onViewCreated()");
        super.onViewCreated(view, savedInstanceState);

        if (mWebView == null) {
            mWebView = (WebView) view.findViewById(R.id.web_view);

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

        getActivity().setTitle(mWebView.getTitle());
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
            getActivity().setProgressBarIndeterminateVisibility(true);
            getActivity().setTitle("Now loading...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            getActivity().setProgressBarIndeterminateVisibility(false);
            getActivity().setTitle(view.getTitle());
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        LOGV(TAG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LOGV(TAG, "onResume()");

        if (!mHasSavedInstanceState) {
            mWebView.loadUrl(getArguments().getString(PARAM_URL, "http://www.google.com"));
        }

        mWebView.onResume();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        LOGV(TAG, "onViewStateRestored()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LOGV(TAG, "onSaveInstanceState()");
    }

    @Override
    public void onPause() {
        super.onPause();
        LOGV(TAG, "onPause()");
        mWebView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        LOGV(TAG, "onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LOGV(TAG, "onDestroy()");

        // Activityの再作成ではない場合は呼ばれる。

        // WebViewを破棄する
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
