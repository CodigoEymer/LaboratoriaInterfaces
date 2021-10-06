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
	HiloComunicador hilocomunicador;
	
	Millisecond milisegundoformat;
	int TamanoSerie;
	String contadordato;
	static StringBuffer buffer;
	static double data=0;
	float lectura=0;
	int ControlGráfica = 0;
	
	public Graficar()
	{
		hilocomunicador= new HiloComunicador("COM1");
		//hilocomunicador.conectar();
		TamanoSerie=100;
		inter.series.setMaximumItemCount(TamanoSerie); //Tamaño maximo de la serie
		buffer = new StringBuffer(10000);
		this.start();
		
	}
	public HiloComunicador getHiloC() {
		return hilocomunicador;
	}
	
	public void setFlagGraficar(int ControlGráfica) {
		this.ControlGráfica=ControlGráfica;
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
					
					
					if(ControlGráfica==1) {
						if(hilocomunicador.getDatoRecibido()==1) {
							hilocomunicador.setDatoRecibido();
							float lectura= hilocomunicador.newLectura();						
							data=(double)lectura;
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