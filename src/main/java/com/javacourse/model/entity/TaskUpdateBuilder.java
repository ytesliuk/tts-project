package com.javacourse.model.entity;

import java.time.Duration;

public class TaskUpdateBuilder extends TaskRecordBuilder{
    private User owner;
    private TaskUpdate.Status status;
    private Duration spentTime;
    private TaskUpdate.Category category;


    public TaskUpdateBuilder setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public TaskUpdateBuilder setStatus(TaskUpdate.Status status) {
        this.status = status;
        return this;
    }

    public TaskUpdateBuilder setSpentTime(Duration spentTime) {
        this.spentTime = spentTime;
        return this;
    }

    public TaskUpdateBuilder setCategory(TaskUpdate.Category category) {
        this.category = category;
        return this;
    }

    public TaskUpdate build(){
        return new TaskUpdate(id, task, recordTime, comment, recorder, owner, status, spentTime, category);
    }
}
