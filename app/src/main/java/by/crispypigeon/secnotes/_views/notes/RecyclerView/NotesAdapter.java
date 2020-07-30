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

public class NotesAdapter extends  RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private static final String dateFormatPattern  = "dd.MM HH:mm";

    ArrayList<Note> notesList = new ArrayList<>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatPattern);

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_template_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(notesList.get(position), simpleDateFormat);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void setNotesList(ArrayList<Note> notesList){
        this.notesList = notesList;
        notifyDataSetChanged();
    }

    public void clearNotesList(){
        notesList.clear();
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dateTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.noteTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.noteDescriptionTextView);
            dateTextView = itemView.findViewById(R.id.noteDateTextView);
        }

        public void bind(Note note, SimpleDateFormat simpleDateFormat){
            titleTextView.setText(note.title);
            descriptionTextView.setText(note.description);
            dateTextView.setText(simpleDateFormat.format(note.date));

        }
    }
}
