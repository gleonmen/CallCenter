package com.almundo.callcenter.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Call {
    private static final Logger LOGGER = LoggerFactory.getLogger(Call.class);
    private int id;
    private String description;
    private LocalDateTime inComingDate;
    private long solvedBy;
    private LocalDateTime resolvedDate;
    private int duration;

    public Call(int id, String description) {
        this.id = id;
        this.description = description;
        this.inComingDate = LocalDateTime.now();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void solve(long id) {
        LOGGER.info("Call No " + this.getId() + ", attended by Employee : " + id);
        this.resolvedDate = LocalDateTime.now();
        this.solvedBy = id;
        LOGGER.info("Call No " + this.getId() + " solved!!");
    }
}
