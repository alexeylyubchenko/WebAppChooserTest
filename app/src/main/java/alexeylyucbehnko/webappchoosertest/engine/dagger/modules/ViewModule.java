package alexeylyucbehnko.webappchoosertest.engine.dagger.modules;

import javax.inject.Singleton;

import alexeylyucbehnko.webappchoosertest.views.base.iView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 11/05/17.
 */

@Module
public class ViewModule {

    iView mIView;

    public ViewModule(iView pIView) {
        this.mIView = pIView;
    }

    @Provides
    @PerActivity
    public iView provideIView() {
        return this.mIView;
    }
}
