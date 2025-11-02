import org.testng.annotations.Test;
import java.sql.*;

public class ConnectionJdbc {
    // Database configuration
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Tnet@101530";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/classicmodels";
    private static final String QUERY = "SELECT firstname, lastname FROM employees WHERE employeeNumber = 1621";

    @Test
    public void mysqlConnectionTest() throws SQLException {
        // Use try-with-resources to ensure proper closure of JDBC resources
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY)) {

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");

                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
            }
        }
        // SQLException is thrown from the method signature
    }
}
