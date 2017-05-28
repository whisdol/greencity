package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.dao.mapper.UserRowMapper;
import de.whisdol.greencity.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 23.04.17.
 */
public class UserDAO implements IUserDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public User createUser(User user) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO user ( user_name, password, user_role, city_id, last_activity, comment_count, avatar_id ) VALUES ( ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{user.getUserName(), user.getPassword(), user.getUserRole(), user.getCity().getId(), user.getLastActivity(), user.getCommentCount(), user.getAvatar().getId()});
        return getUserbyUser(user);
    }

    @Override
    public User selectUserById(long id) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        User user;
        try {
            user = (User) select.queryForObject("SELECT user_id, user_name, password, user_role, avatar_id, city_id, last_activity, comment_count FROM user WHERE user_id = ?",
                    new Object[]{id},
                    new UserRowMapper());
        } catch (Exception e) {
            throw new ObjectNotFoundException("User", "ID: " + Long.toString(id));
        }
        return user;
    }

    @Override
    public User selectUserByName(String name) throws ObjectNotFoundException {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        User user;
        try {
            user = (User) select.queryForObject("SELECT user_id, user_name, password, user_role, avatar_id, city_id, last_activity, comment_count FROM user WHERE user_name = ?",
                    new Object[]{name},
                    new UserRowMapper());
        } catch (Exception e) {
            // Query failed or returned not exactly one Object
            throw new ObjectNotFoundException("User", "Name: " + name);
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        JdbcTemplate selectAll = new JdbcTemplate(dataSource);
        return (List<User>) selectAll.query("SELECT user_id, user_name, password, user_role, avatar_id, city_id, last_activity, comment_count FROM user",
                new UserRowMapper());
    }

    @Override
    public void deleteUser(long id) {
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM user WHERE user_id = ?",
                new Object[]{id});
    }

    public User getUserbyUser(User user) throws ObjectNotFoundException {
        return selectUserByName(user.getUserName());
    }
}
