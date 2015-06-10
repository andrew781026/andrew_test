package tw.com.riko.andrew.order_statistic_with_Catagory;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Connect {

	static int flag = 0;

	public void conduct_Connect(String start_day, String end_day, String DB,
			ArrayList<String> input_product_IDs) {
		String sql_msg = "";

		switch (DB) {
		case "測試":
			DB = "ACC";
			break;
		case "力科":
			DB = "LJ";
			break;
		case "永鉅":
			DB = "YC";
			break;
		}

		System.out.println("this is start_day=>"+start_day);
		System.out.println("this is DB=>"+DB);
		System.out.println("this is end_day=>"+end_day);

		try ( // 跟 SQL server 連線
		CRUD_for_DB order_statistic_CRUD = new CRUD_for_DB(
				"192.168.0.11", 1433, DB, "zc_own", "Z334");) {

			// 執行SQL語法

			sql_msg = "exec  andrew_test.test.order_statistic " + " '" + DB + "' " + " , ? , ? , ? ";
			if (order_statistic_CRUD != null) {
				order_statistic_CRUD.conduct_prepareStatement( sql_msg ,
						start_day, end_day, input_product_IDs);

			} else {
				JOptionPane.showMessageDialog(new JOptionPane(),
						"資料庫連接失敗，請通知資料庫管理人員", "資料庫連接失敗",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JOptionPane(),
					"資料庫連接失敗，請通知資料庫管理人員", "資料庫連接失敗", JOptionPane.ERROR_MESSAGE);
		}

	}

	private String read_SQL_file(String file_path) {
		String sql_msg = "";

		FileInputStream fstream;
		try {
			fstream = new FileInputStream(file_path);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
				sql_msg += strLine + "\r\n";
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sql_msg;
	}

}
