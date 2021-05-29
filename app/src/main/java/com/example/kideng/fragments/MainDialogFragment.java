package com.example.kideng.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import com.example.kideng.R;

public class MainDialogFragment extends android.app.DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setView(R.layout.dialog)
                .create();
    }
}
