/**
 * 
 */
package com.paralleltest.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.annotations.DataProvider;

import com.datasource.db.DatabaseConnectionManager;


/**
 * @author james.ngondo
 *
 */

public class DBDataProvider {

	private static String[][] inputArr;

	public static void main(String[] args) {
	
	}

	public Object[][] getDatabaseData() {

		Connection con = null;
		
		try {
				con = DatabaseConnectionManager.getConnectionManagerInstance().connect();
	
				Statement stmt = con.createStatement();
	
				ResultSet rs = stmt.executeQuery("SELECT * FROM click_acadamy_login");
	
				rs.last();
				int rows = rs.getRow();
	
				ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
				int cols = rsmd.getColumnCount();
				System.out.println("Rows: " + rows + " -- Cols: " + cols);
				inputArr = new String[rows][cols];
	
				int i = 0;
				rs.beforeFirst();
				// Iterating the data in the Table
	
				while (rs.next()) {
	
					for (int j = 0; j < cols; j++) {
						inputArr[i][j] = rs.getString(j + 1);
						System.out.print("values:: " + inputArr[i][j] + ":::" + i + ":::" + j);
	
					}
					System.out.println();
					i++;
	
				}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("cannot connect to database...");
		}

		return inputArr;

	}
	
	@DataProvider(name = "databaseTestData", parallel=true)
	public Object[][] databaseTestData()
	{
		Object[][] data= getDatabaseData();
		
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{
		 
				System.out.println("in for :: " + data[i][j]);
				 
			}
		}
		
		return data;
	}
	
	 @DataProvider(name="SearchProvider", parallel=true)
	    public Object[][] getDataFromDataprovider(){
	    return new Object[][] 
	    	{
	            { "unresticteduser@gmail.com", "123456" },
	            { "resticteduser@gmail.com", "123456" }
	        };

	    }


}
