package model.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
@Getter
public class TaskUpdate extends TaskRecord {
    public enum Status{OPEN,REOPEN,ASSIGNED, IN_PROGRESS,RESOLVED,CLOSED,ON_HOLD}

    private User owner;
    private Status status;
    private Duration spentTime;
    private String category;

    @Builder
    public TaskUpdate(long id, Task task, Instant recordTime, String comment,
                      User recorder, User owner, Status status, Duration spentTime, String category) {
        super(id, task, recordTime, comment, recorder);
        this.owner = owner;
        this.status = status;
        this.spentTime = spentTime;
        this.category = category;
    }

    @Override
    public String toString() {
        return "TaskUpdate{" + super.toString() + "owner=" + owner + ", status=" + status + ", spentTime=" + spentTime + ", category='" + category + '\'' + '}';
    }


}
