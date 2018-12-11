package model.dao.mysql;

import controller.util.PropertiesLoader;
import model.dao.TaskDao;
import model.dao.mapper.TaskMapper;
import model.entity.*;

import java.sql.*;
import java.util.*;

/**
 * @author Yuliia Tesliuk
 */
public class MySQLTaskDao implements TaskDao {
    private Connection connection;
    private Properties properties = new PropertiesLoader().getLoadedProperties("mysql_requests.properties");

    MySQLTaskDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Task task) {
        try {
            connection.setAutoCommit(false);
            setGeneralTaskInfo(task);
            createTaskUpdate(task.getLastUpdate());
            setWatcher(task, task.getCreator().getId());

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setGeneralTaskInfo(Task task) throws SQLException {
        PreparedStatement pr = connection.prepareStatement(properties.getProperty("createTask"), Statement.RETURN_GENERATED_KEYS);
        pr.setString(1, task.getTitle());
        pr.setString(2, task.getDescription());
        pr.setTimestamp(3, Timestamp.from(task.getCreateTime()));
        pr.setLong(4, task.getCreator().getId());
        pr.execute();

        ResultSet rs = pr.getGeneratedKeys();
        if (rs.next()) {
            task.setId(rs.getLong("GENERATED_KEY"));
        }
    }

    @Override
    public void createWatcher(Task task, long userId){
        try {
            setWatcher(task, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTaskUpdate(TaskUpdate record){
        try {
            connection.setAutoCommit(false);
            setChangeableTaskInfo(record);
            updateTask(record.getTask());  //save the LastUpdate id to the task table
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTaskComment(Comment comment) {
        try {
            PreparedStatement taskOtherInfo = setGeneralTaskRecordQuery(comment);
            if(comment.getQuoteComment() != null){
                taskOtherInfo.setLong(9, comment.getQuoteComment().getId());
            }
            taskOtherInfo.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> findActiveTasksByCreator(long userId) {
        try {
            List<Task> activeTask = new ArrayList<>();
            PreparedStatement findTasks = connection.prepareStatement(properties.getProperty("findActiveTasksByCreator"));
            findTasks.setLong(1,userId);
            ResultSet rs = findTasks.executeQuery();
            TaskMapper mapper = new TaskMapper();
            while(rs.next()){
                activeTask.add(mapper.mapping(rs));
            }
            return activeTask;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> findActiveTasksByOwner(long userId) {
        try {
            List<Task> activeTask = new ArrayList<>();
            PreparedStatement findTasks = connection.prepareStatement(properties.getProperty("findActiveTasksByOwner"));
            findTasks.setLong(1,userId);
            findTasks.executeQuery();
            ResultSet rs = findTasks.executeQuery();
            TaskMapper mapper = new TaskMapper();
            while(rs.next()){
                activeTask.add(mapper.mapping(rs));
            }
            return activeTask;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAllTaskComment(long id) {
        try {
            List<Comment> comments = new ArrayList<>();
            PreparedStatement findTasks = connection.prepareStatement(properties.getProperty("findTaskComments"));
            findTasks.setLong(1,id);
            ResultSet rs = findTasks.executeQuery();
            TaskMapper mapper = new TaskMapper();
            while(rs.next()){
                comments.add(mapper.commentMapping(rs));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private PreparedStatement setGeneralTaskRecordQuery(TaskRecord record) throws SQLException {
        PreparedStatement taskOtherInfo = connection.prepareStatement(properties.getProperty("createTaskRecord"), Statement.RETURN_GENERATED_KEYS);
        taskOtherInfo.setLong(1, record.getTask().getId());
        taskOtherInfo.setTimestamp(2,Timestamp.from(record.getRecordTime()));
        taskOtherInfo.setLong(3, record.getRecorder().getId());
        taskOtherInfo.setString(4, record.getComment());
        taskOtherInfo.setNull(5, -5);
        taskOtherInfo.setNull(6, -5);
        taskOtherInfo.setNull(7, -5);
        taskOtherInfo.setNull(8, -5);
        taskOtherInfo.setNull(9, -5);

        return taskOtherInfo;
    }

    private PreparedStatement setChangeableTaskInfo(TaskUpdate record) throws SQLException {
        PreparedStatement taskOtherInfo = setGeneralTaskRecordQuery(record);

        taskOtherInfo.setString(5, record.getCategory().toString());
        if(record.getOwner() != null){
            taskOtherInfo.setLong(6, record.getOwner().getId());
        }
        taskOtherInfo.setString(7, record.getStatus().toString());
        if(record.getSpentTime() != null){
            taskOtherInfo.setLong(8, record.getSpentTime().toMillis());
        }
        //  Optional.ofNullable(record.getSpentTime()).ifPresent((x) -> taskOtherInfo.setLong(8, x.toMinutes()));
        taskOtherInfo.execute();
        ResultSet other = taskOtherInfo.getGeneratedKeys();
        if (other.next()) {
            record.setId(other.getLong("GENERATED_KEY"));
        }
        return taskOtherInfo;
    }

    @Override
    public Task findByID(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("findTaskByID"));
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            TaskMapper taskMapper = new TaskMapper();
            if (rs.next()) {
                return taskMapper.mapping(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //TODO
        return null;
    }


    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(properties.getProperty("findAllTasks"));
            TaskMapper taskMapper = new TaskMapper();
            if (rs.next()) {
                tasks.add(taskMapper.mapping(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public void update(Task entity) {
        //TODO
    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("deleteTask"));
            statement.setLong(1, id);

            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw  new RuntimeException();
        }
    }

    private void updateTask(Task entity) throws SQLException {
        PreparedStatement updateTask = connection.prepareStatement(properties.getProperty("updateTask"));
        updateTask.setLong(1,entity.getLastUpdate().getId());
        updateTask.setLong(2,entity.getId());

        updateTask.execute();
    }

    private void setWatcher(Task entity, long userId) throws SQLException {
        PreparedStatement addWatcher = connection.prepareStatement(properties.getProperty("addWatcher"));
        addWatcher.setLong(1, entity.getId());
        addWatcher.setLong(2, userId);
        addWatcher.execute();
    }

}
