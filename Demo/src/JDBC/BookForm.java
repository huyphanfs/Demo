package JDBC;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtGenre;
	private JTextField txtYear;
	private JTable table;
	private String url = "jdbc:mysql://localhost:3306/book";
	private String user = "root";
	private String pass = "";
	private int idSelected;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookForm frame = new BookForm();
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
	public BookForm() {
		setTitle("Book Management");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtTitle = new JTextField();
		txtTitle.setBackground(Color.WHITE);
		txtTitle.setBounds(139, 33, 629, 19);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(139, 85, 289, 19);
		contentPane.add(txtAuthor);	
	
		
		txtGenre = new JTextField();
		txtGenre.setBounds(542, 85, 226, 19);
		contentPane.add(txtGenre);
		txtGenre.setColumns(10);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(139, 138, 171, 19);
		contentPane.add(txtYear);
		
			
		JLabel lblNewLabel_1 = new JLabel("Title:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setBounds(68, 34, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Author:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setBounds(68, 88, 61, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Genre:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setForeground(Color.YELLOW);
		lblNewLabel_3.setBounds(476, 86, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Year:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setForeground(Color.YELLOW);
		lblNewLabel_4.setBounds(68, 141, 61, 13);
		contentPane.add(lblNewLabel_4);
		
		
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to insert a book ?", "Confirmation", 
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					Insert insert = new Insert();
					insert.setVisible(true);
					dispose();
				}				
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnInsert.setBounds(368, 137, 93, 21);
		contentPane.add(btnInsert);		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to delete this book ?", "Confirmation", 
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					String sqlString = "DELETE FROM book WHERE id=?";
					try {
						Connection connection = DriverManager.getConnection(url, user, pass);
						PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
						preparedStatement.setInt(1, (int)table.getValueAt(table.getSelectedRow(), 0));
						if(preparedStatement.executeUpdate()==1) {
							DefaultTableModel model =(DefaultTableModel)table.getModel();
							model.removeRow(table.getSelectedRow());
							JOptionPane.showMessageDialog(null,"Delete the book successfully");
						}else {
							JOptionPane.showMessageDialog(null,"Delete the book unsuccessfully");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(523, 137, 93, 21);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to update the book ?", "Confirmation", 
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					String sqlString ="UPDATE book SET title=?, author=?, genre=?, year=? WHERE id=?";
					try {
						Connection connection = DriverManager.getConnection(url, user, pass);
						PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
						
						preparedStatement.setString(1, txtTitle.getText().trim());
						preparedStatement.setString(2, txtAuthor.getText().trim());
						preparedStatement.setString(3, txtGenre.getText().trim());
						preparedStatement.setInt(4, Integer.parseInt(txtYear.getText().trim()));
						preparedStatement.setInt(5, idSelected);
						
						if(preparedStatement.executeUpdate()==1) {
							initData();
							JOptionPane.showMessageDialog(null, "Update successfully!");
						}else {
							JOptionPane.showMessageDialog(null, "Update unsuccessfully!");
						}					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setBounds(675, 139, 93, 21);
		contentPane.add(btnUpdate);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 185, 758, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idSelected = (int) table.getValueAt(table.getSelectedRow(), 0);
				int year = (int) table.getValueAt(table.getSelectedRow(), 4);
				txtTitle.setText((String) table.getValueAt(table.getSelectedRow(), 1));
				txtAuthor.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				txtGenre.setText((String) table.getValueAt(table.getSelectedRow(), 3));
				txtYear.setText(String.valueOf(year));
			}
		});
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"ID", "Title", "Author", "Genre", "Published year"
				}
			));
			table.getColumnModel().getColumn(0).setMinWidth(30);
			table.getColumnModel().getColumn(0).setMaxWidth(40);
			table.getColumnModel().getColumn(4).setMinWidth(110);
			table.getColumnModel().getColumn(4).setMaxWidth(120);

		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(5, 5, 875, 581);
		lblNewLabel.setIcon(new ImageIcon(BookForm.class.getResource("/img/book.jpg")));
		contentPane.add(lblNewLabel);	
		
		initData();
		
	}
	
	public void initData() {
		String sql = "select * from book";
		try(Connection connection = DriverManager.getConnection(url, user, pass);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql)) {		
			
			if(resultSet != null) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setRowCount(0);
				while (resultSet.next()) {
					model.addRow(new Object[] {resultSet.getInt(1),resultSet.getString("title"), resultSet.getString("author"),
							resultSet.getString("genre"), resultSet.getInt("year")});
				}
			}else {
				JOptionPane.showMessageDialog(null, "The data is empty!");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error !!!");
			e.printStackTrace();
		}
	}

	
}
