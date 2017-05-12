package alexeylyucbehnko.webappchoosertest.engine.dagger.componets;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;

import javax.inject.Singleton;

import alexeylyucbehnko.webappchoosertest.engine.EngineApp;
import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.AcceleratorModule;
import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.AppModule;
import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.BatteryModule;
import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.GpsModule;
import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.NetworkModule;

import alexeylyucbehnko.webappchoosertest.utils.LocationsUtils;
import alexeylyucbehnko.webappchoosertest.web.apps.AcceleratorWebAppInterface;
import alexeylyucbehnko.webappchoosertest.web.apps.BatteryWebAppInterface;
import alexeylyucbehnko.webappchoosertest.web.apps.LocationsWebAppInterface;
import dagger.Component;

/**
 * Created by ally on 11/05/17.
 */

@Singleton
@Component(modules = {AppModule.class,
        AcceleratorModule.class,
        BatteryModule.class,
        GpsModule.class,
        NetworkModule.class})
public interface ApplicationComponent {
    SensorManager getSensorManager();
    Sensor getSensor();

    Context getContext();

    BatteryManager getBatteryManager();
    BatteryWebAppInterface getBatteryWebAppInterface();

    LocationManager getLocationManager();
    LocationsUtils getLocationsUtils();
    LocationsWebAppInterface getLocationsWebAppInterface();

    AcceleratorWebAppInterface getAcceleratorWebAppInterface();

    ConnectivityManager getConnectivityManager();

    void inject(EngineApp pEngineApp);
}
