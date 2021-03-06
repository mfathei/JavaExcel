package excel;

// program to extract data from excel file

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExtractExcelData {

	public ExtractExcelData() {
		/* try {
			System.setProperty("file.encoding", "windows-1256");
			Class.forName(DRIVER);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("unable to load excel  driver");
			return;
		} */
	}

	public static void main(String[] args) {

		new ExtractExcelData();

		try {
			// setup the properties 
            java.util.Properties prop = new java.util.Properties();
            prop.put("charSet", "windows-1256");
            prop.put("user", userName);
            prop.put("password", password);
            
            connection = DriverManager.getConnection(URL, prop);
		} catch (SQLException se) {
			System.err.println("cannot connect to excel file");
			return;
		}

		try {
			statement = connection.createStatement();
			String select = "SELECT * FROM [Sheet1$]";
			resultSet = statement.executeQuery(select);
			metaData = resultSet.getMetaData();

			int colcount = metaData.getColumnCount();

			for (int i = 1; i <= colcount; i++) {
				System.out.print(String.format("%-20s", metaData
						.getColumnName(i)));
			}
            System.out.println();
			System.out
					.print("\n------------------------------------------------\n");
            System.out.println();
			while (resultSet.next()) {

				String col1 = resultSet.getString(1);
				String col2 = resultSet.getString(2);
				String col3 = resultSet.getString(3);

				System.out.print(String.format("%-20s", col1));
				System.out.print(String.format("%-20s", col2));
				System.out.println(String.format("%-20s", col3));

				System.out.println();
			}
		} catch (SQLException se) {
			System.err.println("cannot execute query");
			return;
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException se) {
				System.err.println("unable to close excel file");
			}
		}

	}

	private static final String userName = "";
	private static final String password = "";
	private static final String URL = "jdbc:odbc:testexcel";
	private static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	private static ResultSetMetaData metaData;
}
