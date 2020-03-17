package student;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.JOptionPane;
public class StudentManagement extends JFrame implements ActionListener{
	//继承Java的JFrame类，JFrame 是Java的窗体类，继承它可以重写它的一些方法达到更方便编程的作用
	// 是实现 ActionListener 接口，为动作监听接口，是Java swing 监听窗体动作的一个接口
	JMenuBar bar = null;
	JMenu menu1,menu2,menu3,menu4,menu5;//    菜单栏
	JMenuItem item1,item2,item3,item4,item5,item6,item7,item8;//  项目栏  
	StudentAdd zengjia;
	StudentQuery chaxun;
	StudentUpdate gengxin;  //.............................
	StudentDelete shanchu; 
	time time;
	StudentHot studentHot;//.............................
   query query;
	 
	StudentManagement(){
		super("疫情信息管理平台");
		zengjia = new StudentAdd();
		chaxun = new StudentQuery();
		gengxin = new StudentUpdate();  // ....................
		shanchu = new StudentDelete(); 
		query=new query();
        time  = new time();
        studentHot=new StudentHot();//.....................
		//ceshi = new JDBCTest();//             自己添加
		bar = new JMenuBar();
		menu1 = new JMenu("信息录入");
		menu2 = new JMenu("信息查询");
		menu3 = new JMenu("信息更新");
		menu4 = new JMenu("信息删除");
		menu5 = new JMenu("退出系统");
		item1 = new JMenuItem("录  入");
		item3 = new JMenuItem("查  询 全 部");
		item2 = new JMenuItem("按学号查 询");
		item4= new JMenuItem("更  新");
		item5 = new JMenuItem("删  除");
		item6 = new JMenuItem("退  出");
		item7 = new JMenuItem("按时间查询");
		item8 = new JMenuItem("查询患病同学");
		menu1.add(item1);
		menu2.add(item3);
		menu2.add(item2);
		menu2.add(item7);
		menu2.add(item8);
		menu3.add(item4);
		menu4.add(item5);
		menu5.add(item6);
		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu4);
		bar.add(menu5);
		setJMenuBar(bar);
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		item8.addActionListener(this);
					
		JLabel label = new JLabel("疫情信息管理系统",JLabel.CENTER);
		label.setFont(new Font("疫情信息管理系统",0,35));
		String s = " ";
		Font f = new Font(s,Font.BOLD,60);
		label.setBackground(new Color(0,255,255));
		label.setForeground(new Color(255,128,255));
		add(label,"Center");
		setVisible(true);
		setSize(350,300);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == item1){
			this.getContentPane().removeAll();
			this.getContentPane().add(zengjia,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
			
		}
		if(e.getSource() == item2){
			this.getContentPane().removeAll();
			this.getContentPane().add(chaxun,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
			
		}
		
		
		
		if(e.getSource() == item3){
			this.getContentPane().removeAll();
			this.getContentPane().add(query,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
			
		
	}
		if(e.getSource() == item4){
			this.getContentPane().removeAll();
			this.getContentPane().add(gengxin,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
			
}
		if(e.getSource() == item5){
			this.getContentPane().removeAll();
			this.getContentPane().add(shanchu,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
			
}
		if(e.getSource() == item7){
			this.getContentPane().removeAll();
			this.getContentPane().add(time,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
			
			if(e.getSource() == item8){
				this.getContentPane().removeAll();
				this.getContentPane().add(studentHot,"Center");
				this.getContentPane().repaint();
				this.getContentPane().validate();
			
}
	if(e.getSource() == item6){
		
		System.exit(0);
	}
	}
	public static void main(String args[]){
	StudentManagement stuM = new StudentManagement();
		stuM.setVisible(true);
		
		stuM.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}

