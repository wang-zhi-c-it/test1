package student;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
//query  查询
public class StudentQuery extends JPanel implements ActionListener{
	Connection con;
	Statement sql;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
	JButton b;
	Box baseBox,bv1,bv2;
	int flag = 0;
	StudentQuery(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){}
		try{
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text","root","123");
			sql = con.createStatement();
		}
		catch(SQLException ee){}
		setLayout (new BorderLayout());
		b = new JButton("查询");
		b.setBackground(Color.orange);
		b.addActionListener(this);
		t1 = new JTextField(8);
		t2 = new JTextField(16);
		t3 = new JTextField(16);
		t4= new JTextField(16);
		t5 = new JTextField(16);
		t6 = new JTextField(16);
		t7= new JTextField(16);
		t8 = new JTextField(16);
		t9= new JTextField(16);
		t10 = new JTextField(16);
		t11 = new JTextField(16);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		t8.setEditable(false);
		t9.setEditable(false);
		t10.setEditable(false);
		t11.setEditable(false);
		JPanel p1= new JPanel(),p2 = new JPanel();
		p1.add(new JLabel("请输入学号："));
		p1.add(t1);
		p1.add(b);
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
		add(p1,"North");
		add(p2,"Center");
		setSize(350,300);
		setBackground(Color.white);
	}
	public void actionPerformed(ActionEvent e){
		flag = 0;
		try{query();}
		catch(SQLException ee){}
	}
	public void query() throws SQLException{
		String num,name,gender,address,phone,major,come1,come2,touch1,touch2,time;
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text","root","123");
		num = t1.getText().trim();
		ResultSet rs = sql.executeQuery("SELECT* FROM text.message WHERE id = '"+ num +"'");		
	
		if(rs.next()){
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
		}else{
			JOptionPane.showMessageDialog(this,"没有该学生!","提示对话框",JOptionPane.INFORMATION_MESSAGE);
		}
		con.close();
		if(flag == 0){t1.setText("没有该学生");}		
	}
}
