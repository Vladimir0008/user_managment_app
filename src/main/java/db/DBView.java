package db;

import model.User;

import java.sql.*;
import java.util.ArrayList;

public class DBView {
    private static final String URL = "jdbc:mysql://localhost:3307/user_management_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            System.out.println("SQL Error");
        }
        return connection;
    }


    public void getAllUsers() {
        ArrayList<User> people = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                people.add(new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        people.forEach(person -> System.out.println(person.toString()));

    }

    public void addUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement =
                    connection.prepareStatement("INSERT INTO users(first_name, last_name, age) VALUES (?, ?, ?)");
            prepareStatement.setString(1, user.getFirstName());
            prepareStatement.setString(2, user.getLastName());
            prepareStatement.setInt(3, user.getAge());

            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(int userId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByFirstName(String firstName) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE first_name = ?");
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getOlderUsersThan(int age) {
        ArrayList<User> people = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM users WHERE age > ?");
            prepareStatement.setInt(1, age);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                people.add(new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        people.forEach(person -> System.out.println(person.toString()));
    }

    public void updateUserDataById(int userId, User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement =
                    connection.prepareStatement("UPDATE users SET first_name = ?, last_name = ?, age = ? WHERE  id = ?");
            prepareStatement.setString(1, user.getFirstName());
            prepareStatement.setString(2, user.getLastName());
            prepareStatement.setInt(3, user.getAge());
            prepareStatement.setInt(4, userId);

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
