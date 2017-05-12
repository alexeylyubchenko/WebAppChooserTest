package alexeylyucbehnko.webappchoosertest.engine.dagger.modules;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AcceleratorModule {

    @Provides
    @Singleton
    public Sensor provideSensor(SensorManager pSensorManager) {
        return pSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Provides
    @Singleton
    public SensorManager provideSensorManager(Context pContext) {
        return (SensorManager) pContext.getSystemService(Context.SENSOR_SERVICE);
    }
}
