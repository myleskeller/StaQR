package com.teamadams.staqr;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Dialog {
    public static void createDialog(final Activity activity, String title, String msg, boolean cancellable,
                                    DialogInterface.OnClickListener okListener,
                                    DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton(R.string.ok, okListener);
        alertDialog.setNegativeButton(R.string.cancel, cancelListener);
        if (!cancellable) alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public static void createDialog(final Activity activity, String title, String msg, String positive_button, String negative_button,
                                    DialogInterface.OnClickListener okListener,
                                    DialogInterface.OnClickListener cancelListener,
                                    DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton(positive_button, okListener);
        alertDialog.setNegativeButton(negative_button, cancelListener);
        alertDialog.setCancelable(true);
        alertDialog.setOnCancelListener(onCancelListener);
        alertDialog.show();
    }

    public static void createDialog(final Activity activity, String title, String msg,
                                    DialogInterface.OnClickListener okListener,
                                    DialogInterface.OnClickListener cancelListener,
                                    DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton(R.string.ok, okListener);
        alertDialog.setNegativeButton(R.string.cancel, cancelListener);
        alertDialog.setCancelable(true);
        alertDialog.setOnCancelListener(onCancelListener);
        alertDialog.show();
    }

    public static void createDialog(final Activity activity, String title, String msg, boolean cancellable, String positive_button, String negative_button,
                                    DialogInterface.OnClickListener okListener,
                                    DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton(positive_button, okListener);
        alertDialog.setNegativeButton(negative_button, cancelListener);
        if (!cancellable) alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
