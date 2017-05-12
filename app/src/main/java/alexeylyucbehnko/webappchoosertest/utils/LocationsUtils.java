package alexeylyucbehnko.webappchoosertest.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import alexeylyucbehnko.webappchoosertest.R;
import alexeylyucbehnko.webappchoosertest.utils.base.iDataStringProvider;

/**
 * Created by ally on 11/05/17.
 */

@Singleton
public class LocationsUtils implements iDataStringProvider {

    private LocationManager mLocationManager;
    private Context mContext;

    @Inject
    public LocationsUtils(LocationManager pLocationManager, Context pContext) {
        this.mLocationManager = pLocationManager;
        this.mContext = pContext;
    }


    @Override
    public String getPreparedDataInStringFormat() {
        Location location = this.getBestLocation();

        if(location == null) {
            return mContext.getString(R.string.error_get_location);
        }

        return this.formatLocationToString(location);
    }

    private String formatLocationToString(Location pLocation) {
        StringBuilder result = new StringBuilder();
        result.append("LONG=");
        result.append(pLocation.getLongitude());
        result.append(";");

        result.append("LAT=");
        result.append(pLocation.getLatitude());
        result.append(";");

        result.append("TIMESTAMP=");
        result.append(TimeUtils.getCurrentTimeStamp());
        result.append(";");

        return result.toString();
    }

    private Location getBestLocation() {
        Location locationGPS;
        Location locationNetwork;

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            try {
                locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                locationNetwork = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                long locationGPSTime = 0;
                if (locationGPS != null) {
                    locationGPSTime = locationGPS.getTime();
                }

                long locationNetworkTime = 0;
                if (locationNetwork != null) {
                    locationNetworkTime = locationNetwork.getTime();
                }

                return locationGPSTime < locationNetworkTime ? locationNetwork : locationGPS;

            } catch (Exception ex) {
                Log.e(getClass().getName().toString(), ex.getMessage());
            }
        }

        return null;
    }
}
