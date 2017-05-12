package alexeylyucbehnko.webappchoosertest.presenters.base;

import alexeylyucbehnko.webappchoosertest.views.base.iView;

/**
 * Created by ally on 10/05/17.
 */

public interface iMainPresenter {
    boolean init();
    void onWebAppFinishedLoading();
    void onStartAcceleratorObserving();
    void onStartGPSObserving();
    void onStartBatteryStateObserving();
    void onStopObservingAll();
}
