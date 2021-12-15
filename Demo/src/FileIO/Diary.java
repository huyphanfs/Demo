package FileIO;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.logging.*;

public class Diary extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JButton btnWrite;
	private JButton btnSave;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Diary frame = new Diary();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Diary() {
		
		setTitle("Dairy by FileIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Time:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(265, 21, 45, 13);
		contentPane.add(lblNewLabel);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDate.setBounds(320, 19, 271, 19);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(152, 49, 439, 477);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);
		

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		txtDate.setText(dateFormat.format(date));
		
		JButton btnRead = new JButton("");	
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dailyString="";
				String dateString;
				String content;
			
			try (DataInputStream in = new DataInputStream(new BufferedInputStream( new FileInputStream("./src/img/text.txt")))) {
				while (true) {
					dateString = in.readUTF();
					content = in.readUTF();
					dailyString += dateString + "\n" + content + "\n\n";
					}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Logger.getLogger(Diary.class.getName()).log(Level.SEVERE, null, e1);
			}		
				textArea.setText(dailyString);
				textArea.setEditable(false);
			}
		});
		btnRead.setIcon(new ImageIcon("./src/img/owl.jpg"));
		btnRead.setBounds(24, 83, 97, 97);
		contentPane.add(btnRead);
		
		btnWrite = new JButton("");
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date date = new Date();
				txtDate.setText(dateFormat.format(date));
				textArea.setText("");
				textArea.setEditable(true);				
			}
		});
		btnWrite.setIcon(new ImageIcon("./src/img/notebook.jpg"));
		btnWrite.setBounds(24, 238, 97, 97);
		contentPane.add(btnWrite);
		
		btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateString = txtDate.getText();
				String content = textArea.getText();
				
				try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream (new FileOutputStream("./src/img/text.txt", true)))) {
						out.writeUTF(dateString);
						out.writeUTF(content);
						JOptionPane.showMessageDialog(rootPane, "Keeping Diary is finished!");
						out.close();
			}
			catch (FileNotFoundException ex) {
				Logger.getLogger(Diary.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) { Logger.getLogger(Diary.class.getName()).log(Level.SEVERE, null,
				ex);
				}
			}		
		});
		btnSave.setIcon(new ImageIcon("./src/img/save.jpg"));
		btnSave.setBounds(24, 393, 97, 97);
		contentPane.add(btnSave);
		
		
	}

	
}
