import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionJdbc {
    private static Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        try (InputStream input = ConnectionJdbc.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            prop.load(input);
        }
        return prop;
    }

    @Test
    public void mysqlConnectionTest() throws SQLException, IOException {
        // Load database properties
        Properties prop = loadProperties();
        String query = "SELECT firstname, lastname FROM employees WHERE employeeNumber = 1621";

        // Use try-with-resources for database connection
        try (Connection connection = DriverManager.getConnection(
                prop.getProperty("db.url"),
                prop.getProperty("db.username"),
                prop.getProperty("db.password"));
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");

                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
            }
        }
    }
}
