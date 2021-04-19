import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class Graficar extends Thread{
	Visualpanel visual;
	Interfas inter;
	Millisecond milisegundoformat;
	int milisegundo=0;
	int TamanoSerie;
	String contadordato;
	StringBuffer buffer;
	
	
	public Graficar()
	{
		TamanoSerie=100;
		inter.series.setMaximumItemCount(TamanoSerie);
		buffer = new StringBuffer(inter.longitud*30);
		this.start();
		
	}
	
	
	public void run() {
		
//		  for(int j=0;j<inter.longitud;j++) {
//				System.out.print(j);
//				System.out.print(" HOLA ");
//				System.out.println(inter.vector[j]); 
//				}
	       	
		for(int i=0;i<inter.longitud;i++) {
			  
			  //addOrUpdate()
				  try {
					Thread.sleep(1);
					milisegundo++;
					milisegundoformat= new Millisecond();
					long mili= milisegundoformat.getMillisecond();
					Second segundoformat = milisegundoformat.getSecond();
					int segunto = segundoformat.getSecond();
					
					
					if(milisegundo==inter.TiempoMuestreo) {
						  inter.series.add(milisegundoformat,inter.vector[i]);// los periodos de tiempo no se deben repetir
						  //textArea.append(String.valueOf(milisegundoformat)+" "+String.valueOf(inter.vector[i])+"\n");
						  milisegundo=0;
						  
						  buffer.append(String.valueOf(segunto)+":"+mili+"	 "+String.valueOf(inter.vector[i])+"\n");
						  //System.out.println(buffer);
					  }
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  		  
			  }

	}
}