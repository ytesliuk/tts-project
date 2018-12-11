package model.dao.mapper;

import model.entity.Comment;
import model.entity.Task;
import model.entity.TaskRecord;
import model.entity.TaskUpdate;
import model.service.UserService;

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
            throw new RuntimeException(e);
        }
        return task;
    }

    public Comment commentMapping(ResultSet rs) {
        try {
            Comment comment = Comment.builder().build();
            setGeneralTaskRecordFields(comment, rs);
            //TODO: quot comment;
            return comment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private TaskUpdate taskUpdateMapping(ResultSet rs) throws SQLException {
        TaskUpdate tu = TaskUpdate.builder()
                .category(TaskUpdate.Category.valueOf(rs.getString("category")))
                .status(TaskUpdate.Status.valueOf(rs.getString("status")))
                .spentTime(Duration.ofMinutes(rs.getLong("spent_time")))
                .owner(Optional.ofNullable(new UserService().findUser((rs.getLong("owner_id"))))
                        .orElse(null))
                .build();

        setGeneralTaskRecordFields(tu,rs);
        task.getId();
        return tu;
    }

    private void setGeneralTaskRecordFields(TaskRecord taskRecord, ResultSet rs) throws SQLException {
        taskRecord.setRecorder(new UserService().findUser(rs.getLong("recorder_id")));
        taskRecord.setRecordTime(rs.getTimestamp("update_time").toInstant());
        taskRecord.setComment(rs.getString("comment"));
        taskRecord.setTask(task);
    }

}
