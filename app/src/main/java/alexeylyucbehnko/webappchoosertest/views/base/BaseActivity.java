package alexeylyucbehnko.webappchoosertest.views.base;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import alexeylyucbehnko.webappchoosertest.R;

/**
 * Created by ally on 10/05/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected void showSnackbarMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    protected void showAlertDialog(String title, String message, final int alertDialogRequestCode) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(R.string.button_alert_dialog_positive_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogCallBack_onPositiveButtonClick(alertDialogRequestCode);
            }
        }).setNegativeButton(R.string.button_alert_dialog_negative_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogCallBack_onNegativeButtonClick(alertDialogRequestCode);
            }
        }).create();

        alertDialog.show();

    }

    protected void alertDialogCallBack_onPositiveButtonClick(int requestCode) {
        finish();
    }

    protected void alertDialogCallBack_onNegativeButtonClick(int requestCode) {
        finish();
    }
}