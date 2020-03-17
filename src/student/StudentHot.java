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
	Connection con; //���ض����ݿ�����ӣ��Ự����
	  Statement sql; //����ִ�о�̬ SQL ��䲢�����������ɽ���Ķ���
	  JButton b1,b2;//����������ť
	  Object[] columns={"���","����","ѧԺ","�Ƿ���","�Ƿ����人","�Ƿ��ں���","�����Ƿ񵽴��人","�����Ƿ񵽴����",
"�Ƿ����人��Ա�Ӵ�","�Ƿ��������Ա�Ӵ�","ʱ��"};//�ֶ�
	   Object[][] data=new Object[200][11];
	   JTable table;
	   JScrollPane sc;
		ChartPanel frame1;
	StudentHot(){
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
		   
//		   JFrame frame=new JFrame("�������ͳ��");
//		   frame.setLayout(new GridLayout(2,2,10,10));
//		   frame.add(new BarChart().getChartPanel());   //�������ͼ
//		   frame.setBounds(0, 0, 600, 800);
//		   frame.setVisible(true);
		   
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
		   b2=new JButton("������״ͼ");
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
	}else if(e.getSource()==b2){
		JFrame frame=new JFrame("�������ͳ��");
		   frame.setLayout(new GridLayout(2,2,10,10));
		   frame.add(new BarChart().getChartPanel());   //�������ͼ
		   frame.setBounds(0, 0, 600, 800);
		   frame.setVisible(true);
	}
	}


public void insert() throws SQLException{
	 int i,j;String num="��";
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
	CategoryDataset dataset = getDataSet();//����õ����ݴ��ݸ�CategoryDataset��Ķ���
   JFreeChart chart = ChartFactory.createBarChart3D(
  		                 "�Ƿ񻼲�", // ͼ�����
                       "�Ƿ񻼲�", // Ŀ¼�����ʾ��ǩ
                       "��������", // ��ֵ�����ʾ��ǩ
                       dataset, // ���ݼ�
                       PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                       true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                       false,  // �Ƿ����ɹ���
                       false  // �Ƿ�����URL����
                       );
  
   CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
   CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�
    domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
    domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
    ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
    rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
     chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
     chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������

    frame1=new ChartPanel(chart,true);  //����Ҳ������chartFrame,����ֱ������һ��������Frame
    
}
private static CategoryDataset getDataSet() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(90, "����", "����");//�ɼ�1
    dataset.addValue(5, "����", "����");//�ɼ�2

    return dataset;
 }
 public ChartPanel getChartPanel(){
	   return frame1;

 	}
 
}