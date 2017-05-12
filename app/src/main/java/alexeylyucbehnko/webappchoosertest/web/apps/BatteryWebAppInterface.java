package alexeylyucbehnko.webappchoosertest.web.apps;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import alexeylyucbehnko.webappchoosertest.utils.TimeUtils;
import alexeylyucbehnko.webappchoosertest.web.apps.base.BaseWebAppInterface;

/**
 * Created by ally on 10/05/17.
 */

@Singleton
public class BatteryWebAppInterface extends BaseWebAppInterface {

    BatteryManager mBatteryManager;
    Context mContext;

    @Inject
    public BatteryWebAppInterface(BatteryManager pBatteryManager, Context pContext) {
        this.mBatteryManager = pBatteryManager;
        this.mContext = pContext;
    }

    public String getDataString2() {
        return this.getPreparedDataInStringFormat();
    }

    @Override
    protected String getDataString() {
        return this.getPreparedDataInStringFormat();
    }

    private String getPreparedDataInStringFormat() {
        StringBuilder result = new StringBuilder();

        int batteryLvl = mBatteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        if(batteryLvl < 1) {
            batteryLvl = (int) this.getBatteryLevel();
        }

        result.append("BATTERY=");
        result.append(batteryLvl);
        result.append("%;");

        result.append("TIMESTAMP=");
        result.append(TimeUtils.getCurrentTimeStamp());
        result.append(";");

        return result.toString();
    }

    private float getBatteryLevel() {
        Intent batteryIntent = mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return ((float)level / (float)scale) * 100.0f;
    }
}
