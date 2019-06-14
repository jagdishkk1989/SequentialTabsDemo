package com.jagdish.sequentialtabdemo.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.jagdish.sequentialtabdemo.R;


public class Utility {

    public static void showAlertDialog(Activity activity, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton(activity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
