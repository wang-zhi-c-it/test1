package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class time extends JPanel implements ActionListener {
	Connection con; //���ض����ݿ�����ӣ��Ự����
	  Statement sql; //����ִ�о�̬ SQL ��䲢�����������ɽ���Ķ���
	  JButton b1,b;//����������ť
	  JTextField t1;
	  Object[] columns={"���","����","ѧԺ","�Ƿ�ȷ�ﻼ��","�Ƿ����人","�Ƿ��ں���","�����Ƿ񵽴��人","�����Ƿ񵽴����",
"�Ƿ����人��Ա�Ӵ�","�Ƿ��������Ա�Ӵ�","ʱ��"};//�ֶ�
	   Object[][] data=new Object[200][11];
	   JTable table;
	   JScrollPane sc;
	time (){
		try{										//���������
		    Class.forName("com.mysql.jdbc.Driver");	//ͨ�� Class.forNameΪ���ݿ����ϵͳ����һ��JDBC��������
		    }
		   catch(ClassNotFoundException e){}		//�����������ʧ�� ����̨�׳��쳣
		    try{									//������������ɹ��� �������������ض����ݿ�
		     con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text","root","123");
		  
		     sql=con.createStatement();      //��ȡcon��Ա������ȡStatement����
		    }
		    catch(SQLException ee){}//�쳣�����
		    
		   setLayout(new BorderLayout());//����Ϊ�߿򲼾�
		   
			b = new JButton("��ѯ");
			b.setBackground(Color.orange);
			b.addActionListener(this);
			t1 = new JTextField(16);
			
			
		   JPanel p1=new JPanel();//����һ�����Ķ���Ϊp1
		   JPanel p2=new JPanel();

		   table = new JTable(data, columns);
			table.setPreferredScrollableViewportSize(new Dimension(400, 300));
           sc = new JScrollPane(table) {
          	 public Dimension getPreferredSize() {
  			     return new Dimension(1300, 400);//�����ڲ��������Ը�����Ҫ����
  			   }
          };
            p2.add(new JLabel("������ʱ�䣺"));
  		    p2.add(t1);
  		    p2.add(b);
	        p1.add(sc);
	        add(p1,"Center");
	        add(p2,"North");
			 setSize(350,300);
			 setBackground(Color.pink);	
}

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==b){
		try{insert();}
		catch(SQLException ee){}
	}

		
	 }

public void insert() throws SQLException{
	 int i,j;String num;
	con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text","root","123");
	num = t1.getText().trim();
	ResultSet rs = sql.executeQuery("SELECT* FROM text.message where  DATE_FORMAT(time,'%Y%m%d') =  '"+ num +"'" );
	 i=0;
   j=0;
        while(rs.next()){
        	data[i][j++]=rs.getString("id");
            data[i][j++]=rs.getString("name");
            data[i][j++]=rs.getString("college");
            data[i][j++]=rs.getString("hot_message");
            data[i][j++]=rs.getString("where_wuhan");
            data[i][j++]=rs.getString("where_hubei");
            data[i][j++]=rs.getString("come_wuhan");
            data[i][j++]=rs.getString("come_hubei");
            data[i][j++]=rs.getString("touch_wuhan");
            data[i][j++]=rs.getString("touch_wuhan");
            data[i][j]=rs.getString("time");
            i++;
            j=0;
		
       
	}
	   con.close();
	
}

}
