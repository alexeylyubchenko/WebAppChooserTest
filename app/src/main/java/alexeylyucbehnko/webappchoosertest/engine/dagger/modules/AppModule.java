package alexeylyucbehnko.webappchoosertest.engine.dagger.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 10/05/17.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(@NonNull Context pContext) {
        this.mContext = pContext;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

}
