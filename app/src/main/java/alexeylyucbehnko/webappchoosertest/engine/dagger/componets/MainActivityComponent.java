package alexeylyucbehnko.webappchoosertest.engine.dagger.componets;

import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.PerActivity;
import alexeylyucbehnko.webappchoosertest.engine.dagger.modules.ViewModule;
import alexeylyucbehnko.webappchoosertest.presenters.MainPresenter;
import alexeylyucbehnko.webappchoosertest.views.MainActivity;
import dagger.Component;

/**
 * Created by ally on 10/05/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ViewModule.class})
public interface MainActivityComponent {
    MainPresenter getMainPresenter();

    void injectMainActivity(MainActivity pMainActivity);
}

