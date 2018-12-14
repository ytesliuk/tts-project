package com.javacourse.model.entity;

import lombok.Getter;

import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
@Getter
public class TaskComment extends TaskRecord {
    private TaskComment quoteComment;

    public TaskComment(long id, Task task, Instant recordTime, String comment, User recorder, TaskComment quoteComment) {
        super(id, task, recordTime, comment, recorder);
        this.quoteComment = quoteComment;
    }

}
