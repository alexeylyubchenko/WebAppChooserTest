package alexeylyucbehnko.webappchoosertest.engine;

import android.app.Application;

import alexeylyucbehnko.webappchoosertest.engine.dagger.componets.ApplicationComponent;
import alexeylyucbehnko.webappchoosertest.engine.dagger.componets.DaggerApplicationComponent;
import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.AppModule;
/**
 * Created by ally on 10/05/17.
 */

public class EngineApp extends Application {

    private static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
