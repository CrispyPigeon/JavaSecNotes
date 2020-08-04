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

public class MessageAlertDialog extends DialogFragment {

    private String title;
    private String description;
    private String positiveVariant;

    private NoticeDialogListener noticeDialogListener;

    public MessageAlertDialog(String title, String description, String positiveVariant) {
        this.title = title;
        this.description = description;
        this.positiveVariant = positiveVariant;
    }

    public void setNoticeDialogListener(NoticeDialogListener noticeDialogListener) {
        this.noticeDialogListener = noticeDialogListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(description)
                .setPositiveButton(positiveVariant, (dialog, id) -> noticeDialogListener.onDialogPositiveClick(dialog))
                .setNegativeButton(getString(R.string.negativeAnswer), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
