package com.example.kideng.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import com.example.kideng.R;

//TODO: зачем создавать целый класс ради диалогового окошка?
// Причём как я понял клик не обрабатывается
// Попробуй использовать просто метод создания алерт диалога и кастомизируй через styles.xml
public class MainDialogFragment extends android.app.DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setView(R.layout.dialog)
                .create();
    }
}
