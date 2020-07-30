package by.crispypigeon.secnotes.data;

import java.util.Date;

public class Note {

    public int dbId;

    public String title;

    public String description;

    public Date date;

    public Note() {    }

    public Note(int id, String title, String description, Date date) {
        this.dbId = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }
}
