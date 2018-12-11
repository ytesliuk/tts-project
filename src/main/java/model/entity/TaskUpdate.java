package model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
@Getter
@Setter
public class TaskUpdate extends TaskRecord {
    public enum Status{OPEN,REOPEN,ASSIGNED, IN_PROGRESS,RESOLVED,CLOSED,ON_HOLD}
    public enum Category{Technical_Support,OPR,Access,Accounting,Other}


    private User owner;
    private Status status;
    private Duration spentTime;
    private Category category;

    @Builder
    public TaskUpdate(long id, Task task, Instant recordTime, String comment,
                      User recorder, User owner, Status status, Duration spentTime, Category category) {
        super(id, task, recordTime, comment, recorder);
        this.owner = owner;
        this.status = status;
        this.spentTime = spentTime;
        this.category = category;
    }

    public TaskUpdate(TaskUpdate update){
        super(update.getId(),update.getTask(), Instant.now(), "", update.getRecorder());
        this.owner = update.getOwner();
        this.status = update.getStatus();
        this.category = update.getCategory();

    }

    @Override
    public String toString() {
        return "TaskUpdate{" + super.toString() + "owner=" + owner + ", status=" + status + ", spentTime=" + spentTime + ", category=" + category + '\'' + '}';
    }


}
