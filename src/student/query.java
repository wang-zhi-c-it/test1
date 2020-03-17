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
	Connection con; //与特定数据库的连接（会话）。
	  Statement sql; //用于执行静态 SQL 语句并返回它所生成结果的对象。
	  JButton b1,b2;//定义两个按钮
	  Object[] columns={"编号","姓名","学院","是否确诊患病","是否在武汉","是否在湖北","近期是否到达武汉","近期是否到达湖北",
"是否与武汉人员接触","是否与湖北人员接触","时间"};//字段
	   Object[][] data=new Object[200][11];
	   JTable table;
	   JScrollPane sc;
	query (){
		try{										//错误处理机制
		    Class.forName("com.mysql.jdbc.Driver");	//通过 Class.forName为数据库管理系统加载一个JDBC驱动程序。
		    }
		   catch(ClassNotFoundException e){}		//如果加载驱动失败 控制台抛出异常
		    try{									//如果加载驱动成功， 调用驱动连接特定数据库
		     con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text","root","123");
		  
		     sql=con.createStatement();      //调取con成员方法获取Statement对象
		    }
		    catch(SQLException ee){}//异常处理块
		    
		   setLayout(new BorderLayout());//布局为边框布局
		   JPanel p1=new JPanel();//定义一个面板的对象为p1

		   table = new JTable(data, columns);
			table.setPreferredScrollableViewportSize(new Dimension(400, 300));
             sc = new JScrollPane(table) {
            	 public Dimension getPreferredSize() {
    			     return new Dimension(1300, 400);//括号内参数，可以根据需要更改
    			   }
            };
			
	        p1.add(sc);
	        add(p1,"Center");
			   setSize(350,300);
			   setBackground(Color.pink);
		 
		  
		  b1=new JButton("查询全部");  
		   b2=new JButton("清空");
		 //给两个按钮添加监视器
		   b1.addActionListener(this);
		   b2.addActionListener(this);
		   //将两个按钮添加到面板p1
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
		 ((DefaultTableModel) table.getModel()).getDataVector().clear();   //清除表格数据
		 ((DefaultTableModel) table.getModel()).fireTableDataChanged();//通知模型更新
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

