package alexeylyucbehnko.webappchoosertest.views;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import javax.inject.Inject;

import alexeylyucbehnko.webappchoosertest.R;
import alexeylyucbehnko.webappchoosertest.engine.EngineApp;
import alexeylyucbehnko.webappchoosertest.engine.dagger.componets.DaggerMainActivityComponent;
import alexeylyucbehnko.webappchoosertest.engine.dagger.componets.MainActivityComponent;
import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.ViewModule;
import alexeylyucbehnko.webappchoosertest.presenters.MainPresenter;
import alexeylyucbehnko.webappchoosertest.utils.Constants;
import alexeylyucbehnko.webappchoosertest.views.base.BaseActivity;
import alexeylyucbehnko.webappchoosertest.views.base.iView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements iView, ActivityCompat.OnRequestPermissionsResultCallback {

    @BindView(R.id.webapp_btn1)
    FloatingActionButton mWebAppFb1;

    @BindView(R.id.webapp_btn2)
    FloatingActionButton mWebAppFb2;

    @BindView(R.id.webapp_btn3)
    FloatingActionButton mWebAppFb3;

    @BindView(R.id.webView)
    WebView mWebView;

    @BindView(R.id.processLoadingBar)
    ProgressBar mLoadingProgressBar;

    @Inject
    MainPresenter mMainPresenter;

    MainActivityComponent mMainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpView();
    }


    @OnClick(R.id.webapp_btn1)
    protected void webapp_btn1_onClick() {
        mMainPresenter.onStartAcceleratorObserving();
    }

    @OnClick(R.id.webapp_btn2)
    protected void webapp_btn2_onClick() {
        mMainPresenter.onStartGPSObserving();
    }

    @OnClick(R.id.webapp_btn3)
    protected void webapp_btn3_onClick() {
        mMainPresenter.onStartBatteryStateObserving();
    }

    @Override
    public void setUpView() {

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .viewModule(new ViewModule(this))
                .applicationComponent(EngineApp.getApplicationComponent())
                .build();

        mMainActivityComponent.injectMainActivity(this);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        tryToConnectToWebView();

        boolean permissionGranted =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(!permissionGranted) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
    }

    private void tryToConnectToWebView() {
        if(mMainPresenter.init()) {
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    mMainPresenter.onWebAppFinishedLoading();
                }
            });
        }
    }

    @Override
    public void showLoadingProcess() {
        this.showLoading(true);
    }

    @Override
    public void hideLoadingProcess() {
        this.showLoading(false);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showLoading(final boolean show) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int animationTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mLoadingProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoadingProgressBar.animate().setDuration(animationTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoadingProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

        } else {
            mLoadingProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public WebView getWebView() {
        return mWebView;
    }

    @Override
    public void showDialogMessage(String title, String message, int requestCode) {
        showAlertDialog(title, message, requestCode);
    }

    @Override
    protected void alertDialogCallBack_onPositiveButtonClick(int requestCode) {
        if(requestCode == Constants.DIALOG_REQUEST_CODE) {
            tryToConnectToWebView();
        }
    }

    protected void alertDialogCallBack_onNegativeButtonClick(int requestCode) {
        super.alertDialogCallBack_onNegativeButtonClick(requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }
}
