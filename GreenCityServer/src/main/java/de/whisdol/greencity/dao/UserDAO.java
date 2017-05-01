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
        insert.update("INSERT INTO address ( user_name, password, user_role, city_id, last_activity, comment_count, avatar_id ) VALUES ( ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{user.getUserName(), user.getPassword(), user.getUserRole(), user.getCity().getId(), user.getLastActivity(), user.getCommentCount(), user.getAvatar().getId()});
        return getUserbyUser(user);
    }

    @Override
    public User selectUserById(long id) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public User selectUserByName(String Name) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }

    public User getUserbyUser(User user) {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        User returnUser;
        try {
            returnUser = (User) select.queryForObject("SELECT user_id, user_name, password, user_role, avatar_id, city_id, last_activity, comment_count FROM user WHERE user_name = ?",
                    new Object[]{user.getUserName()},
                    new UserRowMapper());
        } catch (Exception e) {
            // Query failed or returned not exactly one Object
            throw new ObjectNotFoundException("User", "Name: " + user.getUserName());
        }
        return returnUser;
    }
}
