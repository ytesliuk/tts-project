package com.javacourse.model.dao.mapper;

import com.javacourse.model.dao.UncheckedSQLException;
import com.javacourse.model.entity.*;
import com.javacourse.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Optional;

/**
 * @author Yuliia Tesliuk
 */
public class TaskMapper {
    private Task task;

    public Task mapping(ResultSet rs) {
        UserMapper userMapper = new UserMapper();
        try {
            task = Task.builder()
                    .id(rs.getLong("task_id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .createTime(rs.getTimestamp("create_time").toInstant())
                    .completeTime((rs.getTimestamp("complete_time") != null) ?
                            rs.getTimestamp("complete_time").toInstant() :
                            null) //TODO optional?
                    .creator(userMapper.mapping(rs))
                    .build();
             task.setLastUpdate(taskUpdateMapping(rs));

        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
        return task;
    }

    public TaskComment commentMapping(ResultSet rs) {
        try {
            TaskCommentBuilder builder = new TaskCommentBuilder();
            setGeneralTaskRecordFields(builder, rs);
            //TODO: quot comment;
            return builder.build();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    private TaskUpdate taskUpdateMapping(ResultSet rs) throws SQLException {
        TaskUpdateBuilder builder = new TaskUpdateBuilder();
        setGeneralTaskRecordFields(builder,rs);

        return  builder.setCategory(TaskUpdate.Category.valueOf(rs.getString("category")))
                .setStatus(TaskUpdate.Status.valueOf(rs.getString("status")))
                .setSpentTime(Duration.ofMinutes(rs.getLong("spent_time")))
                .setOwner(Optional.ofNullable(new UserService().findUser((rs.getLong("owner_id"))))
                        .orElse(null))
                .build();
    }

    private void setGeneralTaskRecordFields(TaskRecordBuilder builder, ResultSet rs) throws SQLException {
        builder.setRecorder(new UserService().findUser(rs.getLong("recorder_id")))
                .setRecordTime(rs.getTimestamp("update_time").toInstant())
                .setComment(rs.getString("comment"))
                .setTask(task);
    }

}
