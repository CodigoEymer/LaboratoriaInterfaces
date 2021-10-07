import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;
import java.util.List;

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

import DAOint_proceso_vars_data.int_proceso_vars_dataT;
//import Dao.*;

//import DAOint_proceso_vars_data.*;

public class Graficar extends Thread{
	Interfas inter;
	PrincipalM principalM;
	List<int_proceso_vars_dataT> Registros;
	int_proceso_vars_dataT registro;
	
	Millisecond milisegundoformat;
	int TamanoSerie;
	String contadordato;
	static StringBuffer buffer;
	static double data=0;
	float lectura=0;
	int ControlGráfica = 0;
	int c=0;
	
	public Graficar()
	{
		TamanoSerie=100;
		inter.series.setMaximumItemCount(TamanoSerie); //Tamaño maximo de la serie
		buffer = new StringBuffer(10000);
		Registros=principalM.IMipvd.getAllint_proceso_vars_dataT();	

		this.start();
		
	}
	
	public void setFlagGraficar(int ControlGráfica) {
		this.ControlGráfica=ControlGráfica;
	}
	
	public void run() {	  
		System.out.println("run");
		while(true) {
				  try {										
					  Thread.sleep(0,001);		
					if(ControlGráfica==1) {
						//System.out.println("***********************");
						while(c<Registros.size()) {
							Thread.sleep(1);
							milisegundoformat= new Millisecond();
							long mili= milisegundoformat.getMillisecond();
							Second segundoformat = milisegundoformat.getSecond();
							int segundo = segundoformat.getSecond();
							Minute minutoformat  = segundoformat.getMinute();
							int minuto = minutoformat.getMinute();
							
							System.out.println("c: "+c+" rSize: "+Registros.size());
							registro=Registros.get(c);
							lectura=registro.getValor();											
							data=(double)lectura;
							inter.series.add(milisegundoformat,data);							  
							buffer.append(String.valueOf(minuto)+":"+String.valueOf(segundo)+":"+mili+"	 "+String.valueOf(data)+"\n");
							c++;
						}
						System.out.println("esperando llenado de registros");
						Thread.sleep(1);	
						
						/*
						if(conexionserial.getDatoRecibido()==1) {
							conexionserial.setDatoRecibido();
							float lectura= conexionserial.newLectura();						
							data=(double)lectura;
							inter.series.add(milisegundoformat,data);
							  
							buffer.append(String.valueOf(minuto)+":"+String.valueOf(segundo)+":"+mili+"	 "+String.valueOf(data)+"\n");
							c++;												
						}*/
					}					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  		  
			  }

	}
}