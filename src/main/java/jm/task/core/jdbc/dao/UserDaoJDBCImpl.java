package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getUtil().getConnection()) {
            Statement statement = connection.createStatement();
            try {
                connection.setAutoCommit(false);

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (\n" +
                        "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `lastname` VARCHAR(45) NOT NULL,\n" +
                        "  `age` TINYINT NOT NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);\n");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getUtil().getConnection()) {
            Statement statement = connection.createStatement();
            try {
                connection.setAutoCommit(false);
//            Statement statement = connection.createStatement();
                statement.executeUpdate("\t\n" +
                        "DELETE FROM user;");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO user  (name, lastName, age) values (?,?,?)";
        try (Connection connection = Util.getUtil().getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL);
            try {
                connection.setAutoCommit(false);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM user WHERE id=?";
        try (Connection connection = Util.getUtil().getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL);
            try {
                connection.setAutoCommit(false);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();
        try (Connection connection = Util.getUtil().getConnection()) {
            Statement statement = connection.createStatement();
            try {
                connection.setAutoCommit(false);
                ResultSet rs = statement.executeQuery("SELECT * FROM user");
                while (rs.next()) {
                    String name = rs.getString("name");
                    String lastname = rs.getString("lastname");
                    Byte age = rs.getByte("age");
                    allUser.add(new User(name, lastname, age));
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUser;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getUtil().getConnection()) {
            Statement statement = connection.createStatement();
            try {
                connection.setAutoCommit(false);
                statement.executeUpdate("TRUNCATE TABLE user");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
