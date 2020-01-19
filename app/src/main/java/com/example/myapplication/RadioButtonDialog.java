package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RadioButtonDialog extends DialogFragment {

    int position = 0;

    public interface SingleChoiceListener {
        void onPoitiveButtonClicked(String[] options, int position);

        void onNegativeButtonClicked();
    }

    SingleChoiceListener mListener;

    @NonNull
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (SingleChoiceListener) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + "SingleChoice not implemented");
        }
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] options = getActivity().getResources().getStringArray(R.array.radioButtonChoices);

        builder.setSingleChoiceItems(options, position, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                position = which;
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onPoitiveButtonClicked(options, position);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onNegativeButtonClicked();
            }
        });

        return builder.create();
    }
}
