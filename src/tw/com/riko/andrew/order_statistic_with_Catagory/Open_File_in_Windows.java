package tw.com.riko.andrew.order_statistic_with_Catagory;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Open_File_in_Windows extends JFrame {
	public Open_File_in_Windows() {
		JButton btnFile = new JButton("¶}±ÒÀÉ®×");
		JPanel jp = new JPanel();
		jp.add(btnFile);
		this.setLayout(new BorderLayout());
		this.add(jp);
		this.setVisible(true);
		//this.setVisible(false);
		this.setSize(200, 80);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		btnFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnFileAction();
			}
		});
	}

	public Open_File_in_Windows(String file) {
		this.open_File(file);
	}
	
	
	
	public void btnFileAction() {
		FileDialog fd = new FileDialog(this, "", FileDialog.LOAD);
		fd.setVisible(true);
		String file = fd.getFile();
		if (file == null)
			return;
		file = fd.getDirectory() + file;
		try {
			Runtime.getRuntime().exec(
					"cmd.exe /c start rundll32 url.dll,FileProtocolHandler "
							+ file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void open_File(String file) {
		
		if (file == null)
			return;
	
		try {
			Runtime.getRuntime().exec(
					"cmd.exe /c start rundll32 url.dll,FileProtocolHandler "
							+ file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Open_File_in_Windows();
	}
}