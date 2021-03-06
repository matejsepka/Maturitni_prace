package com.matej.sepka.appPackage.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.matej.sepka.appPackage.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AboutDialogGroupDetailActivity extends AppCompatDialogFragment {

    //on Create dialog
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //nastavení layoutu
        View view = View.inflate(getContext(), R.layout.dialog_about_group_detail_activity, null);

        builder.setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}