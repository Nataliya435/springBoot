package springboot.Dao;

import org.springframework.stereotype.Repository;
import springboot.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<User> getAllUsers() {
        List<User> users = manager.createQuery("select us from User us").getResultList();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return manager.find(User.class,id);
    }

    @Override
    public void saveUser(User user) {
        manager.persist(user);
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        User user = manager.find(User.class,id);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
    }

    @Override
    public void deleteUser(Long id) {
        manager.remove(getUserById(id));
    }
}
