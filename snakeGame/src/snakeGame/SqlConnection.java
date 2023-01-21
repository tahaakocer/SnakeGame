package snakeGame;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class SqlConnection {
	private static String username = "root";
	private static String password = "123456";
	private static String url = "jdbc:mysql://localhost:3306/d";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String sqlQueryYaz = "INSERT INTO snakegame.scoreboard (isim,score,date) VALUES (?,?,?)";
	public static Connection myConn;
	private static PreparedStatement myPreStat;
	private static ResultSet myRes;
	
	static {
		
		try {
			Class.forName(driver);
			myConn = (Connection)DriverManager.getConnection(url,username,password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void skorYaz(String isim,int score,java.util.Date dt){
		try {
			myPreStat = myConn.prepareStatement(sqlQueryYaz);
			myPreStat.setString(1,isim);
			myPreStat.setInt(2, score);
			Timestamp timestamp = new Timestamp(dt.getTime());
			myPreStat.setTimestamp(3, timestamp);
			myPreStat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
