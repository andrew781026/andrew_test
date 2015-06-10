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
		case "����":
			DB = "ACC";
			break;
		case "�O��":
			DB = "LJ";
			break;
		case "�ùd":
			DB = "YC";
			break;
		}

		System.out.println("this is start_day=>"+start_day);
		System.out.println("this is DB=>"+DB);
		System.out.println("this is end_day=>"+end_day);

		try ( // �� SQL server �s�u
		CRUD_for_DB order_statistic_CRUD = new CRUD_for_DB(
				"192.168.0.11", 1433, DB, "zc_own", "Z334");) {

			// ����SQL�y�k

			sql_msg = "exec  andrew_test.test.order_statistic " + " '" + DB + "' " + " , ? , ? , ? ";
			if (order_statistic_CRUD != null) {
				order_statistic_CRUD.conduct_prepareStatement( sql_msg ,
						start_day, end_day, input_product_IDs);

			} else {
				JOptionPane.showMessageDialog(new JOptionPane(),
						"��Ʈw�s�����ѡA�гq����Ʈw�޲z�H��", "��Ʈw�s������",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JOptionPane(),
					"��Ʈw�s�����ѡA�гq����Ʈw�޲z�H��", "��Ʈw�s������", JOptionPane.ERROR_MESSAGE);
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
