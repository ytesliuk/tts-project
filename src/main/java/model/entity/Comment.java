package model.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
@Getter
public class Comment extends TaskRecord {
    private Comment quoteComment;

    @Builder
    public Comment(long id, Task task, Instant recordTime, String comment, User recorder) {
        super(id, task, recordTime, comment, recorder);
    }

}
