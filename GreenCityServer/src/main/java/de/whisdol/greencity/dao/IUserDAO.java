package de.whisdol.greencity.dao;

import de.whisdol.greencity.api.ObjectNotFoundException;
import de.whisdol.greencity.model.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by cedric on 22.04.17.
 */
public interface IUserDAO
{
    void setDataSource(DataSource ds);

    User createUser(User user);

    User selectUserById(long id) throws ObjectNotFoundException;

    User selectUserByName(String Name) throws ObjectNotFoundException;

    List<User> selectAllUsers();

    void deleteUser(User user);

}
