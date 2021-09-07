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
	//Visualpanel visual;
	Interfas inter;
	Millisecond milisegundoformat;
	int TamanoSerie;
	String contadordato;
	static StringBuffer buffer;
	static double data=0;
	
	
	public Graficar()
	{
		TamanoSerie=100;
		inter.series.setMaximumItemCount(TamanoSerie); //Tamaño maximo de la serie
		buffer = new StringBuffer(10000);
		this.start();
		
	}
	
	
	public void run() {

	       	
		while(true) {
				  try {
					Thread.sleep(1);
					milisegundoformat= new Millisecond();
					long mili= milisegundoformat.getMillisecond();
					Second segundoformat = milisegundoformat.getSecond();
					int segundo = segundoformat.getSecond();
					Minute minutoformat  = segundoformat.getMinute();
					int minuto = minutoformat.getMinute();
					
					//String filaFile =(String.valueOf(minuto)+":"+String.valueOf(segundo)+":"+mili+"	 "+String.valueOf(data)+"\n");
					//System.out.println(filaFile);
					
					
					if(HiloComunicador.ControlGráfica==1) {
						if(HiloComunicador.DatoRecibido==1) {
							HiloComunicador.DatoRecibido=0;
							if(Interfas.Cdigital==0) {
							  data=(HiloComunicador.DatosRecibidosProteusD)*0.01953125;	// Convierte el valor del ADC en voltaje
							}else {
								data=(HiloComunicador.DatosRecibidosProteusD);
								if(data!=0) {
									data=5;
								}
							}
							  inter.series.add(milisegundoformat,data);// los periodos de tiempo no se deben repetir
							  //textArea.append(String.valueOf(milisegundoformat)+" "+String.valueOf(inter.vector[i])+"\n");
							  
							  buffer.append(String.valueOf(minuto)+":"+String.valueOf(segundo)+":"+mili+"	 "+String.valueOf(data)+"\n");
							  //System.out.println(buffer);
												
						}
					}
					
					
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  		  
			  }

	}
}