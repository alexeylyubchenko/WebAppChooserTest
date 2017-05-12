package alexeylyucbehnko.webappchoosertest.engine.dagger.modules;

import android.content.Context;
import android.os.BatteryManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 10/05/17.
 */

@Module
public class BatteryModule {

    @Provides
    @Singleton
    public BatteryManager provideBatteryManager(Context pContext) {
        return (BatteryManager) pContext.getSystemService(Context.BATTERY_SERVICE);
    }
}
