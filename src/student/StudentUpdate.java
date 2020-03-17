package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//更新
public class StudentUpdate extends JPanel implements ActionListener {
	Connection con;
	Statement sql;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
	JButton b1, b2,b3;
	Box baseBox, bv1, bv2;
	int flag = 0;

	StudentUpdate() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text", "root","123");
			sql = con.createStatement();
		} catch (SQLException ee) {
		}
		setLayout(new BorderLayout());
		b1 = new JButton("查找");
		b2 = new JButton("更新");
		b2.setBackground(Color.RED);
		b3 = new JButton("重置");
		b3.setBackground(Color.green);
		b1.addActionListener(this);
		
		b3.setBackground(Color.BLUE);
		b2.addActionListener(this);
		b3.addActionListener(this);

		t1 = new JTextField(8);
		t2 = new JTextField(16);
		t3 = new JTextField(16);
		t4 = new JTextField(16);
		t5 = new JTextField(16);
		t6 = new JTextField(16);
		t7 = new JTextField(16);
		t8 = new JTextField(16);
		t9 = new JTextField(16);
		t10 = new JTextField(16);
		t11 = new JTextField(16);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		p1.add(new JLabel("请输入要修改的学生学号："));
		p1.add(t1);
		p1.add(b1);
		p3.add(b2);
	    p3.add(b3);
		bv1 = Box.createVerticalBox();
		   bv1.add(new JLabel("姓名"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("学院"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("是否确诊患病"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("是否在武汉"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("是否在湖北"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("近期是否到达武汉"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("近期是否到达湖北"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("是否与武汉人员接触"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("是否与湖北人员接触"));
		   bv1.add(Box.createVerticalStrut(8));
		   bv1.add(new JLabel("时间"));
		   bv1.add(Box.createVerticalStrut(8));
		bv2 = Box.createVerticalBox();
		bv2.add(t2);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t3);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t4);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t5);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t6);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t7);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t8);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t9);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t10);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t11);
		bv2.add(Box.createVerticalStrut(8));

		baseBox = Box.createHorizontalBox();
		baseBox.add(bv1);
		baseBox.add(Box.createHorizontalStrut(10));
		baseBox.add(bv2);

		p2.add(baseBox);
		add(p1, "North");
		add(p2, "Center");
	    add(p3,"South");
		setSize(350, 300);
		setBackground(Color.white);
       }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1||e.getSource() ==b3) {
			try {
				sure();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == b2) {
			try {
				update();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void update() throws SQLException {
		String num,name,gender,address,phone,major,come1,come2,touch1,touch2,time;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text", "root",
					"123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		num = "'" + t1.getText().trim() + "'";
		name = "'" + t2.getText().trim() + "'";
		gender = "'" + t3.getText().trim() + "'";
		address = "'" + t4.getText().trim() + "'";
		phone = "'" + t5.getText().trim() + "'";
		major = "'" + t6.getText().trim() + "'";
		come1 = "'" + t7.getText().trim() + "'";
		come2= "'" + t8.getText().trim() + "'";
		touch1 = "'" + t9.getText().trim() + "'";
	   touch2= "'" + t10.getText().trim() + "'";
		time = "'" + t11.getText().trim() + "'";
		
		
		String s1 = "UPDATE message SET id=" + num + " WHERE id = " + num;
		sql.executeUpdate(s1);

		String s2 = "UPDATE message SET  name=" + name + " WHERE id = " + num;
		sql.executeUpdate(s2);

		String s3 = "UPDATE message  SET college=" + gender + " WHERE id = " + num;
		sql.executeUpdate(s3);

		String s4 = "UPDATE message SET hot_message=" + address + " WHERE id = " + num;
		sql.executeUpdate(s4);

		String s5 = "UPDATE message SET where_wuhan=" + phone + " WHERE id = " + num;
		sql.executeUpdate(s5);

		String s6 = "UPDATE message SET where_hubei=" + major + " WHERE id = " + num;
		sql.executeUpdate(s6);
		String s7 = "UPDATE message SET come_wuhan=" + come1 + " WHERE id = " + num;
		sql.executeUpdate(s7);

		String s8 = "UPDATE message SET come_hubei=" + come2 + " WHERE id = " + num;
		sql.executeUpdate(s8);

		String s9 = "UPDATE message SET touch_wuhan=" + touch1 + " WHERE id = " + num;
		sql.executeUpdate(s9);

		String s10 = "UPDATE message SET  touch_hubei=" + touch2 + " WHERE id = " + num;
		sql.executeUpdate(s10);

		String s11 = "UPDATE message SET time=" + time + " WHERE id = " + num;
		sql.executeUpdate(s11);
		JOptionPane.showMessageDialog(this, "更新成功!", "提示对话框", JOptionPane.INFORMATION_MESSAGE);
		con.close();

	}

	public void sure() throws SQLException {
		String num,name,gender,address,phone,major,come1,come2,touch1,touch2,time;
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text", "root",
				"123");
		num = t1.getText().trim();
		ResultSet rs = sql.executeQuery("SELECT* FROM message WHERE id = '" + num + "'");

		if (rs.next()) {
			name = rs.getString("name");
			gender = rs.getString("college");
			address = rs.getString("hot_message");
			phone = rs.getString("where_wuhan");
			major = rs.getString("where_hubei");
			come1=rs.getString("come_wuhan");
			come2=rs.getString("come_wuhan");
			touch1=rs.getString("touch_wuhan");
			touch2=rs.getString("touch_wuhan");
			time =rs.getString("time");
			t2.setText(name);
			t3.setText(gender);
			t4.setText(address);
			t5.setText(phone);
			t6.setText(major);
			t7.setText(come1);
			t8.setText(come2);
			t9.setText(touch1);
			t10.setText(touch2);
			t11.setText(time);
			flag = 1;
		} else {
			JOptionPane.showMessageDialog(this, "没有该学生!", "提示对话框", JOptionPane.INFORMATION_MESSAGE);
		}
		con.close();
		if (flag == 0) {
			t1.setText("没有该学生");
		}
	}

}
