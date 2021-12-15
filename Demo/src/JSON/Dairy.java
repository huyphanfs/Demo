package JSON;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Dairy extends JFrame {

	private JPanel contentPane;
	private JTextField textDate;
	List<News> lst = new ArrayList<News>();
	Gson gson = new Gson();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dairy frame = new Dairy();
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
	public Dairy() {
		setTitle("Diary by JSON");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		textDate = new JTextField();
		textDate.setEditable(false);
		textDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textDate.setBounds(313, 10, 373, 21);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date datetime = new Date();
		textDate.setText(dateFormat.format(datetime));
		
		
		JLabel lblNewLabel = new JLabel("Time:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(261, 14, 42, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(154, 41, 532, 490);
		contentPane.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);

		
		JButton btnRead = new JButton("");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gson gson = new Gson();
				FileReader reader;
				try {
					reader = new FileReader("./src/img/file.json");
					java.lang.reflect.Type type = new TypeToken<List<News>>() {}.getType();
					List<News> list = gson.fromJson(reader, type);
					for (News news : list) {
						textArea.append(news.getDate() + "\n");
						textArea.append(news.getContent() + "\n\n");
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRead.setIcon(new ImageIcon("./src/img/owl.jpg"));
		btnRead.setBounds(24, 70, 107, 107);
		contentPane.add(btnRead);
		
		JButton btnWrite = new JButton("");
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date1 = new Date();
				textDate.setText(dateFormat.format(date1));
				textArea.setText("");
				textArea.setEditable(true);
			}
		});
		btnWrite.setIcon(new ImageIcon("./src/img/notebook.jpg"));
		btnWrite.setBounds(24, 226, 110, 107);
		contentPane.add(btnWrite);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				News news = new News();
				news.setDate(textDate.getText());
				news.setContent(textArea.getText());
				lst.add(news);
				
				try {
					FileWriter writer = new FileWriter("./src/img/file.json");
					gson.toJson(lst,writer);					
					JOptionPane.showMessageDialog(rootPane, "Keeping Diary is finished!");
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textArea.setText("");
				
				
				
			}
		});
		btnSave.setIcon(new ImageIcon("./src/img/save.jpg"));
		btnSave.setBounds(24, 384, 107, 107);
		contentPane.add(btnSave);
	}
}
