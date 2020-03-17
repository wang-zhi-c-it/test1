package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
public class StudentHot extends JPanel implements ActionListener {
	Connection con; //与特定数据库的连接（会话）。
	  Statement sql; //用于执行静态 SQL 语句并返回它所生成结果的对象。
	  JButton b1,b2;//定义两个按钮
	  Object[] columns={"编号","姓名","学院","是否发热","是否在武汉","是否在湖北","近期是否到达武汉","近期是否到达湖北",
"是否与武汉人员接触","是否与湖北人员接触","时间"};//字段
	   Object[][] data=new Object[200][11];
	   JTable table;
	   JScrollPane sc;
		ChartPanel frame1;
	StudentHot(){
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
		   
//		   JFrame frame=new JFrame("患病情况统计");
//		   frame.setLayout(new GridLayout(2,2,10,10));
//		   frame.add(new BarChart().getChartPanel());   //添加柱形图
//		   frame.setBounds(0, 0, 600, 800);
//		   frame.setVisible(true);
		   
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
		   b2=new JButton("生成柱状图");
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
	}else if(e.getSource()==b2){
		JFrame frame=new JFrame("患病情况统计");
		   frame.setLayout(new GridLayout(2,2,10,10));
		   frame.add(new BarChart().getChartPanel());   //添加柱形图
		   frame.setBounds(0, 0, 600, 800);
		   frame.setVisible(true);
	}
	}


public void insert() throws SQLException{
	 int i,j;String num="是";
	con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/text","root","123");
	
	ResultSet rs = sql.executeQuery("SELECT*  FROM message where hot_message= '"+ num +"'" );
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
public  void BarChart(){
	CategoryDataset dataset = getDataSet();//将获得的数据传递给CategoryDataset类的对象
   JFreeChart chart = ChartFactory.createBarChart3D(
  		                 "是否患病", // 图表标题
                       "是否患病", // 目录轴的显示标签
                       "患病个数", // 数值轴的显示标签
                       dataset, // 数据集
                       PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                       true, // 是否显示图例(对于简单的柱状图必须是false)
                       false,  // 是否生成工具
                       false  // 是否生成URL链接
                       );
  
   CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
   CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
    domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
    domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
    ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
    rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
     chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
     chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

    frame1=new ChartPanel(chart,true);  //这里也可以用chartFrame,可以直接生成一个独立的Frame
    
}
private static CategoryDataset getDataSet() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(90, "健康", "健康");//成绩1
    dataset.addValue(5, "患病", "患病");//成绩2

    return dataset;
 }
 public ChartPanel getChartPanel(){
	   return frame1;

 	}
 
}