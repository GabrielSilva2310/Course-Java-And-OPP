package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn=null;
		Statement st=null;
		
		try {
			conn=DB.getConnection();
			conn.setAutoCommit(false);
			st=conn.createStatement();
			
			int rows1=st.executeUpdate("UPDATE seller SET BaseSalary = 3000 WHERE departmentId = 1");
			
			int rows2=st.executeUpdate("UPDATE seller SET BaseSalary = 2500 WHERE departmentId = 2");
			
			conn.commit();
		
			System.out.println("Rows Affected:"+rows1);
			System.out.println("Rows Affected:"+rows2);
			
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction Rolled back ! Caused by:"+e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying rollback! Caused by:"+e.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	
		
		
	}

}
