package com.javacourse.model.entity;

import java.time.Instant;

public abstract class TaskRecordBuilder {
    protected long id;
    protected Task task;
    protected Instant recordTime;
    protected String comment;
    protected User recorder;

    public TaskRecordBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public TaskRecordBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public TaskRecordBuilder setTask(Task task) {
        this.task = task;
        return this;
    }

    public TaskRecordBuilder setRecordTime(Instant recordTime) {
        this.recordTime = recordTime;
        return this;
    }

    public TaskRecordBuilder setRecorder(User recorder) {
        this.recorder = recorder;
        return this;
    }

    public abstract <T extends TaskRecord> T build();
}
