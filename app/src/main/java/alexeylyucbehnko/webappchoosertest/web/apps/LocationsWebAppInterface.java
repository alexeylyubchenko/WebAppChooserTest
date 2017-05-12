package alexeylyucbehnko.webappchoosertest.web.apps;

import android.location.LocationManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import alexeylyucbehnko.webappchoosertest.utils.LocationsUtils;
import alexeylyucbehnko.webappchoosertest.web.apps.base.BaseWebAppInterface;

/**
 * Created by ally on 10/05/17.
 */

@Singleton
public class LocationsWebAppInterface extends BaseWebAppInterface {

    LocationsUtils mLocationsUtils;

    @Inject
    public LocationsWebAppInterface(LocationsUtils pLocationsUtils) {
        this.mLocationsUtils = pLocationsUtils;
    }

    @Override
    protected String getDataString() {
        return mLocationsUtils.getPreparedDataInStringFormat();
    }

}

