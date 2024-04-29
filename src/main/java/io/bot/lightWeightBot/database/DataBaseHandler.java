package io.bot.lightWeightBot.database;

import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;

import java.sql.*;

import static io.bot.lightWeightBot.database.DataConfig.*;

public class DataBaseHandler {
    Connection DBconnection;

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connectionStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        DBconnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/visiters", "timur", "12052005");
        return DBconnection;
    }

    public void addUser(String name, String chatID) {
        if (!checkElement(name)) {
            String insert = "INSERT INTO " + dbName + "(user_name, user_chatID)" + "VALUES(?, ?)";
            try {
                PreparedStatement prSt = getDBConnection().prepareStatement(insert);
                prSt.setString(1, name);
                prSt.setString(2, chatID);
                prSt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    boolean checkElement(String name) {
        String url = "jdbc:mysql://127.0.0.1:3306/visiters";
        String username = "timur";
        String password = "12052005";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM users WHERE user_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name); 

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Элемент найден в базе данных.");
                return true;
            } else {
                System.out.println("Элемент не найден в базе данных.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при выполнении SQL запроса: " + e.getMessage());
            return true;
        }
    }
}
