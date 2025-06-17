package ua.slyrack.springcourse.entity;

public enum Status {
    NEW(0), IN_PROGRESS(1), DONE(2);

    private final int statusNumber;

    Status(int statusNumber){
        this.statusNumber = statusNumber;
    }

    public int getStatusNumber() {
        return statusNumber;
    }
}
