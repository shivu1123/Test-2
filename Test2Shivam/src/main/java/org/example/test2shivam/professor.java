package org.example.test2shivam;

public class professor {

    private int id;
    private String pname;
    private String subject;
    private int classroom;

    public professor(int id, String pname, String subject, int classroom) {
        this.id = id;
        this.pname = pname;
        this.subject = subject;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }
}
