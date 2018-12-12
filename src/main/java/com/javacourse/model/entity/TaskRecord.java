package com.javacourse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class TaskRecord {
    private long id;
    private Task task;
    private Instant recordTime;
    private String comment;
    private User recorder;

    @Override
    public String toString() {
        return "TaskRecord{" + "id=" + id + ", task=" + task + ", recordTime=" + recordTime + ", comment='" + comment + '\'' + ", recorder=" + recorder + '}';
    }
}
