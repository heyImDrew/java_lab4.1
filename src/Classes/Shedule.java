package Classes;

import java.io.IOException;

public class Shedule implements java.io.Serializable {
    private int group_number;
    private String lesson;
    private String date;
    private String time_from;
    private String time_to;

    public int get_group_number() { return this.group_number; }
    public void set_group_number(int group_number) { this.group_number = group_number; }
    public String get_lesson() { return this.lesson; }
    public void set_lesson(String lesson) { this.lesson = lesson; }
    public String get_date() { return this.date; }
    public void set_date(String date) { this.date = date; }
    public String get_time_from() { return this.time_from; }
    public void set_time_from(String time_from) { this.time_from = time_from; }
    public String get_time_to() { return this.time_to; }
    public void set_time_to(String time_to) { this.time_to = time_to; }
    public void printInfo() { System.out.println(this.get_group_number() + " " + this.get_lesson() + " " + this.get_date() + " " + this.get_time_from() + " " + this.get_time_to()); }

    public Shedule(int group_number, String lesson, String date, String time_from, String time_to) {
        this.group_number = group_number;
        this.lesson = lesson;
        this.date = date;
        this.time_from = time_from;
        this.time_to = time_to;
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
                stream.writeInt(this.get_group_number());
                stream.writeObject(this.get_lesson());
                stream.writeObject(this.get_date());
                stream.writeObject(this.get_time_from());
                stream.writeObject(this.get_time_to());
            }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
                this.set_group_number(stream.readInt());
                this.set_lesson((String) stream.readObject());
                this.set_date((String) stream.readObject());
                this.set_time_from((String) stream.readObject());
                this.set_time_to((String) stream.readObject());
    }
}
