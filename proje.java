package swingdersleri;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.sql.Statement;

public class proje extends JFrame {

	
	private JPanel contentPane;
	private JTextField t1;
	private JPasswordField t2;
	static String sifre="muhammed21";
	private JTextField p3t1;
	private JTextField p3t2;
	private JTextField p3t3;
	private JTextField p3t4;
	private JTextField p4t1;
	private JTextField p4t2;
	private JTextField p4t3;
	private JTextField p4t4;
	private JTextField p5t1;
    
	/**
	 * Launch the application.
	 */
	Connection mycon = null ;
	PreparedStatement statements;
	
	public void  kayit() throws SQLException {
		mycon =DriverManager.getConnection("jdbc:mysql://localhost:3306/kayitformu?useTimezone=true&serverTimezone=UTC","Muhammed","Muhammed.23");
		String s="insert into bilgiler(Ad,Soyad,Cep,Tc)values(?,?,?,?)";
		String k="select Tc from bilgiler";
		Statement st= mycon.createStatement();
		ResultSet rs=st.executeQuery(k);
        int sayac=0;
		while(rs.next()) {
			if(rs.getString("Tc").equals(p3t4.getText())) {
				sayac++;
				System.out.println(sayac);
			}
		}
		if(sayac==0) {
			try {
				
				
				statements = mycon.prepareStatement(s);
			    statements.setString(1,p3t1.getText());
				statements.setString(2,p3t2.getText());
				statements.setString(3,p3t3.getText());
				statements.setString(4,p3t4.getText());
				statements.executeUpdate();	
				 JOptionPane.showMessageDialog(null,"Kayıt ekleme Başarılı.");
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		else {
			JOptionPane.showMessageDialog(null,"Aynı Tc nolu birden fazla kayıt oluşturulamaz");
		}
		
	}
	public void Guncelle() {
		try {
			
			mycon =DriverManager.getConnection("jdbc:mysql://localhost:3306/kayitformu?useTimezone=true&serverTimezone=UTC","Muhammed","Muhammed.23");			
            String s="UPDATE bilgiler SET Ad=?,Soyad=?,Cep=?,Tc=? WHERE Tc="+p4t4.getText();    

            statements = mycon.prepareStatement(s);
			statements.setString(1, p4t1.getText());
			statements.setString(2, p4t2.getText());
			statements.setString(3, p4t3.getText());
			statements.setString(4, p4t4.getText());
		    statements.executeUpdate();
            JOptionPane.showMessageDialog(null,"Güncelleme Başarılı.");
		    
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Hata oluştu Hata mesajı:"+e.getMessage());
		}	
	}
	public void Sil() {
		try {
			
			mycon =DriverManager.getConnection("jdbc:mysql://localhost:3306/kayitformu?useTimezone=true&serverTimezone=UTC","Muhammed","Muhammed.23");
			;
			String s="DELETE FROM `bilgiler` WHERE Tc='"+p5t1.getText()+"'";
			System.out.println(s);
			PreparedStatement uygula=mycon.prepareStatement(s);
			uygula.executeUpdate(s);
            JOptionPane.showMessageDialog(null,"Kayıt silme Başarılı.");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Hata oluştu Hata mesajı:"+e.getMessage());
		}	
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					proje frame = new proje();
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
	public proje() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 11, 456, 343);
		contentPane.add(panel1);
		panel1.setLayout(null);
		JLabel lbltitle = new JLabel("VERİ TABANI YÖNETİM SİSTEMİNE HOŞGELDİNİZ.");
		lbltitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltitle.setBounds(70, 11, 329, 14);
		panel1.add(lbltitle);
		
		t1 = new JTextField();
		t1.setBounds(202, 36, 91, 20);
		panel1.add(t1);
		t1.setColumns(10);
		
		t2 = new JPasswordField();
		t2.setBounds(202, 81, 91, 20);
		
		panel1.add(t2);
		
		
		JLabel lblkad = new JLabel("Kullanıcı Adı");
		lblkad.setBounds(129, 39, 63, 14);
		panel1.add(lblkad);
		
		JLabel lblsfre = new JLabel("Şifre");
		lblsfre.setBounds(129, 84, 63, 14);
		panel1.add(lblsfre);
		
		JButton btngrs = new JButton("Giriş");
		
		btngrs.setBounds(202, 114, 83, 23);
		panel1.add(btngrs);
		
		
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 11, 456, 343);
		contentPane.add(panel2);
		panel2.setLayout(null);
		panel2.setVisible(false);
		
		JButton btnek = new JButton("Veri ekle");
		btnek.setBounds(10, 45, 90, 25);
		panel2.add(btnek);
		
		JButton btncık = new JButton("Veri Sil");
		btncık.setBounds(110, 45, 90, 25);
		panel2.add(btncık);
		
		JButton btngnc = new JButton("Update");
		btngnc.setBounds(210, 45, 90, 25);
		panel2.add(btngnc);
		
		JLabel lblNewLabel = new JLabel("LÜTFEN İŞLEMİNİZİ SEÇİNİZ.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(39, 11, 200, 20);
		panel2.add(lblNewLabel);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(10, 11, 456, 343);
		contentPane.add(panel3);
		panel3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Adı");
		lblNewLabel_1.setBounds(10, 20, 100, 20);
		panel3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Soyadı");
		lblNewLabel_2.setBounds(10, 45, 100, 20);
		panel3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cep No");
		lblNewLabel_3.setBounds(10, 70, 100, 20);
		panel3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tc K.N");
		lblNewLabel_4.setBounds(10, 95, 100, 20);
		panel3.add(lblNewLabel_4);
		
		p3t1 = new JTextField();
		p3t1.setBounds(110, 20, 100, 20);
		panel3.add(p3t1);
		p3t1.setColumns(10);
		
		p3t2 = new JTextField();
		p3t2.setBounds(110, 45, 100, 20);
		panel3.add(p3t2);
		p3t2.setColumns(10);
		
		p3t3 = new JTextField();
		p3t3.setBounds(110, 70, 100, 20);
		panel3.add(p3t3);
		p3t3.setColumns(10);
		
		p3t4 = new JTextField();
		p3t4.setBounds(110, 95, 100, 20);
		panel3.add(p3t4);
		p3t4.setColumns(10);
		
		JButton btnekle = new JButton("KAYDET");
		btnekle.setBounds(112, 120, 89, 25);
		panel3.add(btnekle);
		
		JButton btngeri = new JButton("");
		btngeri.setIcon(new ImageIcon("C:\\Users\\zxz\\Desktop\\icon\\images\\r16.png"));
		btngeri.setBounds(0, 0, 20, 20);
		panel3.add(btngeri);
		panel3.setVisible(false);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(10, 11, 456, 343);
		contentPane.add(panel4);
		
		JLabel lblNewLabel_1_1 = new JLabel("Adı");
		lblNewLabel_1_1.setBounds(10, 20, 100, 20);
		panel4.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Soyadı");
		lblNewLabel_2_1.setBounds(10, 45, 100, 20);
		panel4.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Cep No");
		lblNewLabel_3_1.setBounds(10, 70, 100, 20);
		panel4.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Tc K.N");
		lblNewLabel_4_1.setBounds(10, 95, 100, 20);
		panel4.add(lblNewLabel_4_1);
		
		p4t1 = new JTextField();
		p4t1.setColumns(10);
		p4t1.setBounds(110, 20, 100, 20);
		panel4.add(p4t1);
		
		p4t2 = new JTextField();
		p4t2.setColumns(10);
		p4t2.setBounds(110, 45, 100, 20);
		panel4.add(p4t2);
		
		p4t3 = new JTextField();
		p4t3.setColumns(10);
		p4t3.setBounds(110, 70, 100, 20);
		panel4.add(p4t3);
		
		p4t4 = new JTextField();
		p4t4.setColumns(10);
		p4t4.setBounds(110, 95, 100, 20);
		panel4.add(p4t4);
		
		JButton btngüncel = new JButton("GÜNCELLE");
		btngüncel.setBounds(112, 120, 89, 25);
		panel4.add(btngüncel);
		
		JButton btngeri_1 = new JButton("");
		btngeri_1.setIcon(new ImageIcon("C:\\Users\\zxz\\Desktop\\icon\\images\\r16.png"));
		btngeri_1.setBounds(0, 0, 20, 20);
		panel4.add(btngeri_1);
		panel4.setVisible(false);
		
		JPanel panel5 = new JPanel();
		panel5.setBounds(10, 11, 456, 343);
		contentPane.add(panel5);
		panel5.setLayout(null);
		panel5.setVisible(false);
		
		JLabel lblNewLabel_5 = new JLabel("Silinecek olan kişinin Tc kimlik numarası");
		lblNewLabel_5.setBounds(10, 20, 250, 20);
		panel5.add(lblNewLabel_5);
		
		p5t1 = new JTextField();
		p5t1.setBounds(262, 20, 100, 20);
		panel5.add(p5t1);
		p5t1.setColumns(10);
		
		JButton btnsil = new JButton("DELETE");
		btnsil.setBounds(262, 45, 89, 23);
		panel5.add(btnsil);
		
		JButton btngeri_2 = new JButton("");
		btngeri_2.setIcon(new ImageIcon("C:\\Users\\zxz\\Desktop\\icon\\images\\r16.png"));
		btngeri_2.setBounds(0, 0, 20, 20);
		panel5.add(btngeri_2);
	
		
		btngrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String myPass=String.valueOf(t2.getPassword());

			if(t1.getText().equals("muhammed")&& myPass.equals(sifre)) {
				panel1.setVisible(false);
				panel2.setVisible(true);
				
			}
			else {
				JOptionPane.showMessageDialog(null,"Kullanıcı adı veya şifre hatalı");
			}
			}
		});
		btnek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);
				panel3.setVisible(true);
				
				
			}
		});
		btncık.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);
				panel5.setVisible(true);
				
				
			}
		});
		btngnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);
				panel4.setVisible(true);
				
			}
		});
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(true);
				panel3.setVisible(false);
				
			}
		});
		btngeri_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(true);
				panel4.setVisible(false);
				
			}
		});
		btngeri_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(true);
	            panel5.setVisible(false);
				
			}
		});
		btnekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kayit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel2.setVisible(true);
				panel5.setVisible(false);
	            panel1.setVisible(false);
	            panel4.setVisible(false);
	            panel3.setVisible(false);
			}
		});
		btnsil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sil();
				panel2.setVisible(true);
				panel5.setVisible(false);
	            panel1.setVisible(false);
	            panel4.setVisible(false);
	            panel3.setVisible(false);
			}
		});
		btngüncel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guncelle();
				panel2.setVisible(true);
	            panel5.setVisible(false);
	            panel1.setVisible(false);
	            panel4.setVisible(false);
	            panel3.setVisible(false);
			}
		});
		
	}
}
