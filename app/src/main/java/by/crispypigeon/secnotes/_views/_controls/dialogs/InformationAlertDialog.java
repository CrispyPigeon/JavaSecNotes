package by.crispypigeon.secnotes._views._controls.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._views._controls.dialogs.NoticeDialogListener;

public class InformationAlertDialog extends DialogFragment {

    private String title;
    private String description;

    public InformationAlertDialog(String title, String description){
        this.title = title;
        this.description = description;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(description)
                .setPositiveButton(getText(R.string.ok), (dialog, id) -> new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
