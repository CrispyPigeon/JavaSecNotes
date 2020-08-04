package by.crispypigeon.secnotes._views.notes.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes.data.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private static final String dateFormatPattern = "dd.MM HH:mm";

    private ArrayList<Note> notesList = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
    private OnNoteListener onNoteListener;

    public NotesAdapter(OnNoteListener onNoteListener) {
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_template_item, parent, false);
        return new NoteViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(notesList.get(position), simpleDateFormat);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void setNotesList(ArrayList<Note> notesList) {
        this.notesList = notesList;
        notifyDataSetChanged();
    }

    public void clearNotesList() {
        notesList.clear();
        notifyDataSetChanged();
    }

    public Note getNoteByPosition(int position) {
        return notesList.get(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dateTextView;

        private OnNoteListener mOnNoteListener;

        public NoteViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.noteTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.noteDescriptionTextView);
            dateTextView = itemView.findViewById(R.id.noteDateTextView);

            this.mOnNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        public void bind(Note note, SimpleDateFormat simpleDateFormat) {
            titleTextView.setText(note.title);
            descriptionTextView.setText(note.description);
            dateTextView.setText(simpleDateFormat.format(note.date));

        }

        @Override
        public void onClick(View v) {
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
