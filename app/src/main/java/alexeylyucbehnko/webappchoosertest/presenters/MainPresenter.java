package alexeylyucbehnko.webappchoosertest.presenters;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

import alexeylyucbehnko.webappchoosertest.R;
import alexeylyucbehnko.webappchoosertest.engine.EngineApp;
import alexeylyucbehnko.webappchoosertest.presenters.base.iMainPresenter;
import alexeylyucbehnko.webappchoosertest.utils.Constants;
import alexeylyucbehnko.webappchoosertest.views.base.iView;
import alexeylyucbehnko.webappchoosertest.web.apps.AcceleratorWebAppInterface;
import alexeylyucbehnko.webappchoosertest.web.apps.BatteryWebAppInterface;
import alexeylyucbehnko.webappchoosertest.web.apps.LocationsWebAppInterface;
import alexeylyucbehnko.webappchoosertest.web.apps.base.BaseWebAppInterface;
import butterknife.BindString;
import butterknife.ButterKnife;


public class MainPresenter implements iMainPresenter, SensorEventListener {

    private iView mMainView;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    private float x,y,z;

    private BaseWebAppInterface mBaseWebAppInterface;
    private LocationsWebAppInterface mLocationsWebAppInterface;
    private BatteryWebAppInterface mBatteryWebAppInterface;
    private AcceleratorWebAppInterface mAcceleratorWebAppInterface;
    private ConnectivityManager mConnectivityManager;

    @Inject
    public MainPresenter(iView pIView, SensorManager pSensorManager, Sensor pSensor,
                         LocationsWebAppInterface pLocationsWebAppInterface,
                         BatteryWebAppInterface pBatteryWebAppInterface,
                         AcceleratorWebAppInterface pAcceleratorWebAppInterface,
                         ConnectivityManager pConnectivityManager) {
        this.mMainView = pIView;
        this.mSensorManager = pSensorManager;
        this.mSensor = pSensor;

        this.mAcceleratorWebAppInterface = pAcceleratorWebAppInterface;
        this.mBatteryWebAppInterface = pBatteryWebAppInterface;
        this.mLocationsWebAppInterface = pLocationsWebAppInterface;

        this.mConnectivityManager = pConnectivityManager;

    }

    @Override
    public boolean init() {
        mBaseWebAppInterface = mAcceleratorWebAppInterface;
        return setUpWebView(Constants.WEB_APP1_URL);
    }

    @Override
    public void onWebAppFinishedLoading() {
        mMainView.hideLoadingProcess();
    }

    private boolean setUpWebView(String url) {
        if(mConnectivityManager != null && mConnectivityManager.getActiveNetworkInfo() != null
                && mConnectivityManager.getActiveNetworkInfo().isConnected()) {
            mMainView.showLoadingProcess();
            mMainView.getWebView().addJavascriptInterface(mBaseWebAppInterface, Constants.WEB_INTERFACE_NAME);
            mMainView.getWebView().loadUrl(url);
            return true;
        } else {
            String error_no_connection = EngineApp.getApplicationComponent().getContext().getString(R.string.error_no_connection);
            String error_no_connection_ttl = EngineApp.getApplicationComponent().getContext().getString(R.string.error_no_connection_ttl);
            this.mMainView.showDialogMessage(error_no_connection_ttl, error_no_connection, Constants.DIALOG_REQUEST_CODE);
            return false;
        }

    }

    @Override
    public void onStartAcceleratorObserving() {
        onStopObservingAll();
        mSensorManager.registerListener(this, mSensor, 1000000, 1000000);
        mBaseWebAppInterface = mAcceleratorWebAppInterface;
        setUpWebView(Constants.WEB_APP1_URL);
    }

    @Override
    public void onStartGPSObserving() {
        onStopObservingAll();
        mBaseWebAppInterface = mLocationsWebAppInterface;
        setUpWebView(Constants.WEB_APP2_URL);
    }

    @Override
    public void onStartBatteryStateObserving() {
        onStopObservingAll();
        mBaseWebAppInterface = mBatteryWebAppInterface;
        setUpWebView(Constants.WEB_APP3_URL);
    }

    @Override
    public void onStopObservingAll() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            x = values[0];
            y = values[1];
            z = values[2];
            mAcceleratorWebAppInterface.setXYZ(x,y,z);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
