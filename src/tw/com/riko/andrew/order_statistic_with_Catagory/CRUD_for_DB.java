package tw.com.riko.andrew.order_statistic_with_Catagory;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class CRUD_for_DB implements AutoCloseable {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	private static CRUD_for_DB My_sql_test_uniqueInstance;

	public CRUD_for_DB(String IP, int PORT, String DataBase, String user,
			String password) throws SQLException {

		this.connectDB(IP, PORT, DataBase, user, password);

	}

	public static CRUD_for_DB getInstance(String IP, int PORT, String DataBase,
			String user, String password) throws SQLException {
		if (My_sql_test_uniqueInstance == null) {
			My_sql_test_uniqueInstance = new CRUD_for_DB(IP, PORT, DataBase,
					user, password);

		}
		return My_sql_test_uniqueInstance;
	}

	public void connectDB(String IP, int PORT, String DataBase, String user,
			String password) throws SQLException {
		SQLServerDataSource ds = new SQLServerDataSource();

		ds.setServerName(IP);
		ds.setPortNumber(PORT);
		ds.setDatabaseName(DataBase);
		ds.setUser(user);
		ds.setPassword(password);

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		con = ds.getConnection();
		stmt = con.createStatement();

	}



	public void save_Resultset_To_Excel(ArrayList<ResultSet> List_ResultSets) {
		try {
			// 以下選擇檔案要存的路徑
			File_Chooser choose = new File_Chooser();
			String file_path = choose.file_Choose();
			String sheet_name = "List";

			
			// 將取出的資料存成excel
			List_ResultSetToExcel liResultSetToExcel = new List_ResultSetToExcel(List_ResultSets.get(0),
					sheet_name);

			liResultSetToExcel.generate(new File(file_path) , List_ResultSets  );

			// 開啟剛剛存好的excel
			Open_File_in_Windows ex = new Open_File_in_Windows(file_path);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save_Resultset_To_Excel(ResultSet resultSet) {
		try {
			// 以下選擇檔案要存的路徑
			File_Chooser choose = new File_Chooser();
			String file_path = choose.file_Choose();
			String sheet_name = "List";

			// 將取出的資料存成excel
			ResultSetToExcel resultSetToExcel = new ResultSetToExcel(resultSet,
					sheet_name);

			resultSetToExcel.generate(new File(file_path));

			// 開啟剛剛存好的excel
			Open_File_in_Windows ex = new Open_File_in_Windows(file_path);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet conduct_prepareStatement(String SQL, String start_day,
			String end_day, ArrayList<String> input_product_IDs) {

		try {
			
			ArrayList<ResultSet> resultSet_List = new ArrayList() ;

			int split_base = 5000 ; // 一次篩選出幾個品號的資料
			int amount_of_ResultSet = (input_product_IDs.size() / split_base) + 1;
			

			for (int j = 0; j < amount_of_ResultSet; j++) {//for start

				StringBuilder input_product_ID_for_SQL = new StringBuilder(/*"'"*/);

				for (int i = 0 + j * split_base ; i < (j+1) * split_base - 1 ; i++ ) {
					if(i < input_product_IDs.size())	{	input_product_ID_for_SQL.append(input_product_IDs.get(i) + ",");}
				}

				//if((j+1) * split_base - 1 < input_product_IDs.size())	{	input_product_ID_for_SQL.append(input_product_IDs.get((j+1) * split_base - 1));}
				
				//input_product_ID_for_SQL.append("'");
				System.out.println();
				System.out.println(input_product_ID_for_SQL);

				PreparedStatement preparedStatement = con.prepareStatement(SQL);

				// DB宣告已在Connect.SQL宣告了!
				preparedStatement.setString(1, "'" + start_day + "'");// 放入預交日期--
																		// 開始
																		// 20141103
				preparedStatement.setString(2, "'" + end_day + "'");// 放入預交日期---截止日
																	// 20150922

				preparedStatement.setString(3,
						input_product_ID_for_SQL.toString());

				boolean b = preparedStatement.execute();
				if (b == true) {
					rs = preparedStatement.getResultSet();
					System.out.println(rs);
					// rs = my_statement.getResultSet();
					resultSet_List.add(rs);
					
					//this.save_Resultset_To_Excel(rs);
					//return rs;
				}

			}//for end
			
			this.save_Resultset_To_Excel(resultSet_List);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}

	

	@Override
	public void close() throws SQLException {

		// con = null 等GC回收connection才會關閉
		// con.close() 立即關閉connection

		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();

	}

}
