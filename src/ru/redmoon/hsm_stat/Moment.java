package ru.redmoon.hsm_stat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс с информацией о моменте (секунде)
 */
public class Moment {
    private LocalDateTime dateTime;
    private int requestsSent = 0;
    private int repliesReceived = 0;

    Moment(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Moment addRequestsSent() {
        this.requestsSent = this.requestsSent + 1;
        return this;
    }

    public Moment addRepliesReceived() {
        this.repliesReceived = this.repliesReceived + 1;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getRequestsSent() {
        return requestsSent;
    }

    public int getRepliesRecieved() {
        return repliesReceived;
    }

    public String toString() {
        DateTimeFormatter day_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter time_formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return this.dateTime.format(day_formatter) + ";"
                + this.dateTime.format(time_formatter) + ";"
                + this.getRequestsSent() + ";"
                + this.getRepliesRecieved();
    }
}
