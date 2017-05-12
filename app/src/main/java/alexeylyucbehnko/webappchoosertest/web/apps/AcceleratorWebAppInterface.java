package alexeylyucbehnko.webappchoosertest.web.apps;

import javax.inject.Inject;
import javax.inject.Singleton;

import alexeylyucbehnko.webappchoosertest.utils.TimeUtils;
import alexeylyucbehnko.webappchoosertest.web.apps.base.BaseWebAppInterface;

/**
 * Created by ally on 10/05/17.
 */

@Singleton
public class AcceleratorWebAppInterface extends BaseWebAppInterface {

    private float mX,mY,mZ;

    @Inject
    public AcceleratorWebAppInterface() {

    }

    public void setXYZ(float x, float y, float z) {
        this.mX = x;
        this.mY = y;
        this.mZ = z;
    }

    @Override
    protected String getDataString() {
        return this.getPreparedDataInStringFormat();
    }

    private String getPreparedDataInStringFormat() {
        StringBuilder result = new StringBuilder();
        result.append("X=");
        result.append(mX);
        result.append(";");

        result.append("Y=");
        result.append(mY);
        result.append(";");

        result.append("Z=");
        result.append(mZ);
        result.append(";");

        result.append("TIMESTAMP=");
        result.append(TimeUtils.getCurrentTimeStamp());
        result.append(";");

        return result.toString();
    }
}
