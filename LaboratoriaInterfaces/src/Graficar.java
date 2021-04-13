import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

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
	int minuto=0;
	
	static TimeSeries series = new TimeSeries("GRAFICA", Millisecond.class);
	static TimeSeriesCollection datos = new TimeSeriesCollection(series);
	static JFreeChart grafica = ChartFactory.createTimeSeriesChart("", "t", "v", datos, true, true, true);
	final JPanel panelgrafica=new ChartPanel(grafica);
	
	XYPlot dibujo= grafica.getXYPlot();
	XYLineAndShapeRenderer render= new XYLineAndShapeRenderer();
	
	funcionesAD funcion;
	static double[] vector;
	int longitud;
	Second segundoformat;
	Millisecond milisegundoformat;
	
	
	public Graficar()
	{
		this.start();
		render.setSeriesLinesVisible(0, true);	// Activar o desactivar las lineas conectoras entre puntos
		render.setSeriesPaint(0, Color.BLUE);
		render.setBaseOutlineStroke(new BasicStroke(0.1f));	// Tamaño de los puntos
		dibujo.setRenderer(render);
		
		longitud=  1000;
		funcion= new funcionesAD(longitud);
		

	}
	
	
	public void run() {
	       	
		for(int i=0;i<longitud;i++) {
			  
			  //addOrUpdate()
				  try {
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  segundoformat  = new Second();
				  milisegundoformat= new Millisecond();
				  series.add(milisegundoformat,vector[i]);// los periodos de tiempo no se deben repetir
			  
			  
			  }

	}
}