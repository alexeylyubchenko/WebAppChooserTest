package alexeylyucbehnko.webappchoosertest.web.apps.base;

import android.webkit.JavascriptInterface;

/**
 * Created by ally on 10/05/17.
 */

public abstract class BaseWebAppInterface {

    @JavascriptInterface
    public String publishUpdatedData() {
        return getDataString();
    }

    protected abstract String getDataString();
}
