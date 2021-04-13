import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class Visualpanel extends JPanel
	{
	static TimeSeries series = new TimeSeries("GRAFICA", Minute.class);
	final TimeSeriesCollection datos = new TimeSeriesCollection(series);
	final JFreeChart grafica = ChartFactory.createTimeSeriesChart("", "t", "v", datos, true, true, true);
	JPanel panel1=new ChartPanel(grafica);

	int minuto=0;
	
	public Visualpanel()
	{
		//vector=vectorcito;
		init();
		
	}
	
	 public void init() {
		 this.setBorder(BorderFactory.createLineBorder(Color.blue));
		 this.setLayout(new GridLayout(1, 1));
		 this.add(panel1);
		 this.setVisible(true);
	    }
}