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
	Connection con; //与特定数据库的连接（会话）。
	  Statement sql; //用于执行静态 SQL 语句并返回它所生成结果的对象。
	  JButton b1,b;//定义两个按钮
	  JTextField t1;
	  Object[] columns={"编号","姓名","学院","是否确诊患病","是否在武汉","是否在湖北","近期是否到达武汉","近期是否到达湖北",
"是否与武汉人员接触","是否与湖北人员接触","时间"};//字段
	   Object[][] data=new Object[200][11];
	   JTable table;
	   JScrollPane sc;
	time (){
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
		   
			b = new JButton("查询");
			b.setBackground(Color.orange);
			b.addActionListener(this);
			t1 = new JTextField(16);
			
			
		   JPanel p1=new JPanel();//定义一个面板的对象为p1
		   JPanel p2=new JPanel();

		   table = new JTable(data, columns);
			table.setPreferredScrollableViewportSize(new Dimension(400, 300));
           sc = new JScrollPane(table) {
          	 public Dimension getPreferredSize() {
  			     return new Dimension(1300, 400);//括号内参数，可以根据需要更改
  			   }
          };
            p2.add(new JLabel("请输入时间："));
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
