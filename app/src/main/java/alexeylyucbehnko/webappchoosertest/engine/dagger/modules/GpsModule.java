package alexeylyucbehnko.webappchoosertest.engine.dagger.modules;

import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

import alexeylyucbehnko.webappchoosertest.utils.LocationsUtils;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 10/05/17.
 */

@Module
public class GpsModule {

    @Provides
    @Singleton
    public LocationManager provideLocationManager(Context pContext) {
        return (LocationManager) pContext.getSystemService(Context.LOCATION_SERVICE);
    }
}
