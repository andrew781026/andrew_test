package tw.com.riko.andrew.order_statistic_with_Catagory;

import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class File_Chooser {

	

	public String file_Choose() {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"EXCEL files", "xls" );

		// �]�w�x�s������title
		chooser.setDialogTitle("�п�ܭn�x�s����m");

		chooser.setFileFilter(filter);
		Component parent = null;
		int returnVal = chooser.showSaveDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			System.out.println("this is the file Absolute Path: "
					+ chooser.getSelectedFile().getAbsolutePath());
		}
		return chooser.getSelectedFile().getAbsolutePath().toString() + ".xls";
	}
	
	public String file_Read_Choose() {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"EXCEL files", "xls" ,"xlsx" );

		// �]�w�x�s������title
		chooser.setDialogTitle("�п�ܭn����~�����ɮ�");

		chooser.setFileFilter(filter);
		Component parent = null;
		int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			System.out.println("this is the file Absolute Path: "
					+ chooser.getSelectedFile().getAbsolutePath());
		}
		return chooser.getSelectedFile().getAbsolutePath().toString();
	}

}
