package com.javacourse.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
@Getter
public abstract class TaskRecord {
    @Setter
    private long id;
    private Task task;
    private Instant recordTime;
    private String comment;
    private User recorder;

    public TaskRecord(long id, Task task, Instant recordTime, String comment, User recorder) {
        this.id = id;
        this.task = task;
        this.recordTime = recordTime;
        this.comment = comment;
        this.recorder = recorder;
    }

    @Override
    public String toString() {
        return "TaskRecord{" + "id=" + id + ", task=" + task + ", recordTime=" + recordTime + ", comment='" + comment + '\'' + ", recorder=" + recorder + '}';
    }
}
