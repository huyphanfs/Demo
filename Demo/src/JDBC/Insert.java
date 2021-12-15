package JDBC;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Insert extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtGenre;
	private JTextField txtYear;
	private String url = "jdbc:mysql://localhost:3306/book";
	private String user = "root";
	private String pass = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Insert frame = new Insert();
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
	public Insert() {
		setTitle("Insert");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTitle.setBounds(124, 37, 410, 23);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(124, 97, 410, 23);
		contentPane.add(txtAuthor);
		
		txtGenre = new JTextField();
		txtGenre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtGenre.setColumns(10);
		txtGenre.setBounds(124, 157, 410, 23);
		contentPane.add(txtGenre);
		
		txtYear = new JTextField();
		txtYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtYear.setColumns(10);
		txtYear.setBounds(124, 217, 410, 23);
		contentPane.add(txtYear);
		
		JLabel lblNewLabel = new JLabel("Title:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(35, 42, 82, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAuthor.setBounds(34, 100, 82, 13);
		contentPane.add(lblAuthor);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGenre.setBounds(34, 160, 82, 13);
		contentPane.add(lblGenre);
		
		JLabel lblPublishedYear = new JLabel("Published year:");
		lblPublishedYear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPublishedYear.setBounds(32, 222, 89, 13);
		contentPane.add(lblPublishedYear);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO `book`(`title`, `author`, `genre`, `year`) VALUES (?,?,?,?)";
				Book book = new Book();
				book.setTitle(txtTitle.getText().trim());
				book.setAuthor(txtAuthor.getText().trim());
				book.setGenre(txtGenre.getText().trim());
				book.setYear(Integer.parseInt(txtYear.getText().trim()));
				try {
					Connection connection = DriverManager.getConnection(url, user, pass);
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, book.getTitle());
					preparedStatement.setString(2, book.getAuthor());
					preparedStatement.setString(3, book.getGenre());
					preparedStatement.setInt(4, book.getYear());
					
					if(preparedStatement.executeUpdate()==1) {
						dispose();
						JOptionPane.showMessageDialog(null, "Insert book successfully!");					
						BookForm bookForm = new BookForm();
						bookForm.setVisible(true);					
					}else {
						JOptionPane.showMessageDialog(null, "Insert book unsuccessfully!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(124, 270, 124, 21);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookForm bookForm = new BookForm();
				bookForm.setVisible(true);
				dispose();
				
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(297, 272, 124, 21);
		contentPane.add(btnCancel);
	}
}
