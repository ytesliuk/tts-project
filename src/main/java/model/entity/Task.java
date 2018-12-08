package model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
@Builder
@Getter
public class Task {

    @Setter
    private long id;
    //private String number;
    private String title;
    private String description;
    private Instant createTime;
    private Instant completeTime;
    private User creator;
    @Setter
    private TaskUpdate lastUpdate;

}
