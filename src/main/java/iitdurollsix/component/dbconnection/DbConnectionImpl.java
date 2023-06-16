package iitdurollsix.component.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import iitdurollsix.exception.RollSixCustomException;

public class DbConnectionImpl implements DbConnectionInterface {

	private Connection con = null;
	public DbConnectionImpl() {
		try {
			con = DriverManager
					  .getConnection("jdbc:mysql://localhost:3306/rollsix", "root", "iit123");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean validateUserNamePassword(String userName, String password) throws RollSixCustomException {
		try{
			//Statement statement = con.createStatement();
			//statement.executeUpdate("create table Temp (col1 char(5), col2 char(5))");
			//ResultSet resultSet = statement.executeQuery("select city from address");
//			while(resultSet.next()) {
//				System.out.println(resultSet.getString(1));
//				
//			}
			
//			PreparedStatement obj = con.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
//			obj.setInt(1, 1);
//			obj.setString(2, "bngferoz@gmail.com");
//			obj.setString(3, "Feroz");
//			obj.setString(4, "Ahmed");
//			obj.setString(5, "Mogbazar");
//			obj.setString(6, "iit123");
//			obj.setString(7, "admin");
//			obj.executeUpdate();
			
			PreparedStatement obj = con.prepareStatement("select email from user where email=? and password = ?");
			obj.setString(1, userName);
			obj.setString(2, password);
			ResultSet rs = obj.executeQuery();
			if(rs.next()) {
				return true;
			}
				   
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollSixCustomException(password, LocalDateTime.now());
		}
		return false;
	}

}
