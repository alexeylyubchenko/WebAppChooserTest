package alexeylyucbehnko.webappchoosertest.engine.dagger.modules;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 10/05/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager(Context pContext) {
        return (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
