package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.model.City;
import de.whisdol.greencity.model.User;

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
    public void createUser(String userName, String password, City city) {

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
}
