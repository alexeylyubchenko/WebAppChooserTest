package alexeylyucbehnko.webappchoosertest.views.base;

import android.webkit.WebView;

/**
 * Created by ally on 10/05/17.
 */

public interface iView {
    void setUpView();
    void showLoadingProcess();
    void hideLoadingProcess();
    WebView getWebView();
    void showDialogMessage(String title, String message, int requestCode);
}
