package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.jar.Attributes.Name;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;


public class query extends JPanel implements ActionListener{ 
	Connection con; //���ض����ݿ�����ӣ��Ự����
	  Statement sql; //����ִ�о�̬ SQL ��䲢�����������ɽ���Ķ���
	  JButton b1,b2;//����������ť
	  Object[] columns={"���","����","ѧԺ","�Ƿ�ȷ�ﻼ��","�Ƿ����人","�Ƿ��ں���","�����Ƿ񵽴��人","�����Ƿ񵽴����",
"�Ƿ����人��Ա�Ӵ�","�Ƿ��������Ա�Ӵ�","ʱ��"};//�ֶ�
	   Object[][] data=new Object[200][11];
	   JTable table;
	   JScrollPane sc;
	query (){
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
		   JPanel p1=new JPanel();//����һ�����Ķ���Ϊp1

		   table = new JTable(data, columns);
			table.setPreferredScrollableViewportSize(new Dimension(400, 300));
             sc = new JScrollPane(table) {
            	 public Dimension getPreferredSize() {
    			     return new Dimension(1300, 400);//�����ڲ��������Ը�����Ҫ����
    			   }
            };
			
	        p1.add(sc);
	        add(p1,"Center");
			   setSize(350,300);
			   setBackground(Color.pink);
		 
		  
		  b1=new JButton("��ѯȫ��");  
		   b2=new JButton("���");
		 //��������ť��Ӽ�����
		   b1.addActionListener(this);
		   b2.addActionListener(this);
		   //��������ť��ӵ����p1
		   p1.add(b1);
		   p1.add(b2);   
	
}

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==b1){
		try{insert();}
		catch(SQLException ee){}
	}
	 else if(e.getSource()==b2){
		 ((DefaultTableModel) table.getModel()).getDataVector().clear();   //����������
		 ((DefaultTableModel) table.getModel()).fireTableDataChanged();//֪ͨģ�͸���
		 table.updateUI();
		
	 }
}
public void insert() throws SQLException{
	 int i,j;
	con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text","root","123");
	
	ResultSet rs = sql.executeQuery("SELECT*  FROM message" );
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

