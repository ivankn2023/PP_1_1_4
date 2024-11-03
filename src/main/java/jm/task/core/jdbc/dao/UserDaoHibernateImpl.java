package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS userstable.user ( " +
                    "id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(45) NOT NULL, " +
                    "lastName VARCHAR(45) NOT NULL, " +
                    "age INT NOT NULL, " +
                    "PRIMARY KEY (id), " +
                    "UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            session.createSQLQuery("DROP TABLE userstable.user").executeUpdate();

            session.getTransaction().commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.save(new User(name, lastName, age));

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = session.find(User.class, id);
            session.delete(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            users = session.createQuery("from User").getResultList();


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.createQuery("delete from User").executeUpdate();


            session.getTransaction().commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
