package com.bashizip.badassview;

import android.graphics.Color;

import java.io.Serializable;

public class Task implements Serializable {

    private String name;
    private int minutes;
    private long createdOn;
    private String createdBy;

    private State state;

    enum State {
        pending, done, assigned
    }

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, State state) {
        this.name = name;
        this.state = state;
    }


    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }
}
