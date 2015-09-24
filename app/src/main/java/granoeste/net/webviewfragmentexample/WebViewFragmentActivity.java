package granoeste.net.webviewfragmentexample;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static granoeste.net.webviewfragmentexample.LogUtils.LOGV;
import static granoeste.net.webviewfragmentexample.LogUtils.makeLogTag;

public class WebViewFragmentActivity extends AppCompatActivity {
    private static final String TAG = makeLogTag(WebViewFragmentActivity.class);
    protected boolean mHasSavedInstanceState;

    WebViewFragment mFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGV(TAG, "onCreate()");

        mHasSavedInstanceState = (savedInstanceState != null);

        setContentView(R.layout.activity_webview_frag);

        if (mHasSavedInstanceState) {
            mFrag = (WebViewFragment) getSupportFragmentManager().findFragmentByTag("webview_frag");
        } else {
            mFrag = new WebViewFragment();
            Bundle args = new Bundle();
            args.putString(WebViewFragment.PARAM_URL, "http://www.yahoo.co.jp");
            mFrag.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mFrag, "webview_frag").commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LOGV(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGV(TAG, "onResume()");
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        LOGV(TAG, "onRetainCustomNonConfigurationInstance()");
        return super.onRetainCustomNonConfigurationInstance();
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
    }
}
