package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn=null;
		PreparedStatement st=null;
		
		try {
			
			conn=DB.getConnection();
			
			st=conn.prepareStatement(
					"insert into department (Name) values ('D1'),('D2')"
					,Statement.RETURN_GENERATED_KEYS);
			
			int RowsSuccess=st.executeUpdate();
			
			if(RowsSuccess>0) {
				ResultSet rs=st.getGeneratedKeys();
				while(rs.next()) {
					int id=rs.getInt(1);
					System.out.println("Done! ID="+id);
				}
			}
			else {
				System.out.println("No Rows Affect");
			}
			
			st=conn.prepareStatement(
					"DELETE FROM department "
					+"WHERE "
					+"Id = ?"
					);
			
			st.setInt(1, 6);
			
			int rowsAffected=st.executeUpdate();
			
			System.out.println("Done! Rows Affected:"+rowsAffected);
		}
		catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	
		
		
	}

}
