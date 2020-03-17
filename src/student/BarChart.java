package student;

import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.GridLayout;
import javax.swing.JFrame;
public class BarChart {
	ChartPanel frame1;
	public  BarChart(){
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
	   public static void main(String args[]){
		   JFrame frame=new JFrame("�������ͳ��");
		   frame.setLayout(new GridLayout(2,2,10,10));
		   frame.add(new BarChart().getChartPanel());   //�������ͼ
		   frame.setBounds(0, 0, 900, 800);
		   frame.setVisible(true);
	   }
}

