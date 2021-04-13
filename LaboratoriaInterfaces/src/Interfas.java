import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Stroke;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Interfas extends JFrame implements ActionListener{
	
	static TimeSeries series = new TimeSeries("GRAFICA", Millisecond.class);
	static TimeSeriesCollection datos = new TimeSeriesCollection(series);
	static JFreeChart grafica = ChartFactory.createTimeSeriesChart("", "t", "v", datos, true, true, true);
	final JPanel panelgrafica=new ChartPanel(grafica);
	
	XYPlot dibujo= grafica.getXYPlot();
	XYLineAndShapeRenderer render= new XYLineAndShapeRenderer();
	
	  Second segundoformat;
	  Millisecond milisegundoformat;


	int minuto=0;
	
	funcionesAD funcion;
	
	int bin1=0;
	int bin2=0;
	int bin3=0;
	int bin4=0;
	byte sel=0;
	
	static double[] vector;
	int longitud;
	
	JPanel panel;
	JPanel panel_1;
	JPanel panel_2;
	JPanel panel_3;
	JPanel panel_4;
	JPanel panel_5;
	JPanel panel_6;
	JPanel panel_7;
	JPanel panel_8;
	JPanel panel_9;
	
	JComboBox comboBox;
	JCheckBox chckbxNewCheckBox;
	JCheckBox chckbxNewCheckBox_1;
	JCheckBox chckbxNewCheckBox_2;
	JCheckBox chckbxNewCheckBox_3;
	
	JButton btnNewButton; // Botones analogos
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	JButton btnNewButton_3;
	JButton btnNewButton_4;
	JButton btnNewButton_5;
	JButton btnNewButton_6;
	JButton btnNewButton_7;
	
	JButton btnNewButton_9; //Botones digitales
	JButton btnNewButton_10;
	JButton btnNewButton_11;
	JButton btnNewButton_12;
		
	JButton btnNewButton_8; // Boton Aplicar
	JButton btnNewButton_13; // Boton Graficar
	JButton btnNewButton_14; //ModificarTM
	JButton btnNewButton_15; // Boton Limpiar
	
	JLabel lblNewLabel; // Label Activación
	JLabel lblNewLabel_1; // Tm
	JLabel lblNewLabel_2; // Modificador TM
	JLabel lblNewLabel_3; // Label Seleccion Canal
	private JTextField textField; // Cambio TM
	private JPanel panel_10;
	

	public Interfas() {
		initialize();
		render.setSeriesLinesVisible(0, true);	// Activar o desactivar las lineas conectoras entre puntos
		render.setSeriesPaint(0, Color.BLUE);
		render.setBaseOutlineStroke(new BasicStroke(0.1f));	// Tamaño de los puntos
		dibujo.setRenderer(render);
		
		longitud=  1000;
		funcion= new funcionesAD(longitud);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		vector= new double[longitud];
		

		setTitle("GUI");
		setBounds(100, 100, 765, 498);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 221, 448);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Selecci\u00F3n de canal", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.add(panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[] {209, 0};
		gbl_panel_10.rowHeights = new int[] {34, 200, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_10.add(panel_3, gbc_panel_3);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(this);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Analogo", "Digital"}));
		panel_3.add(comboBox);
		
		lblNewLabel_3 = new JLabel("...");
		panel_3.add(lblNewLabel_3);
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel_10.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new CardLayout(0, 0));
		
		panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(4, 10, 4, 10));
		panel_4.add(panel_5, "Analogo");
		
		btnNewButton = new JButton("Canal 1");
		btnNewButton.addActionListener(this);
		panel_5.setLayout(new GridLayout(4, 2, 4, 4));
		panel_5.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Canal 5");
		btnNewButton_1.addActionListener(this);
		panel_5.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Canal 7");
		btnNewButton_2.addActionListener(this);
		
		btnNewButton_3 = new JButton("Canal 2");
		btnNewButton_3.addActionListener(this);
		panel_5.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Canal 6");
		btnNewButton_4.addActionListener(this);
		panel_5.add(btnNewButton_4);
		
		btnNewButton_6 = new JButton("Canal 3");
		btnNewButton_6.addActionListener(this);
		panel_5.add(btnNewButton_6);
		panel_5.add(btnNewButton_2);
		
		btnNewButton_5 = new JButton("Canal 4");
		btnNewButton_5.addActionListener(this);
		panel_5.add(btnNewButton_5);
		
		btnNewButton_7 = new JButton("Canal 8");
		btnNewButton_7.addActionListener(this);
		panel_5.add(btnNewButton_7);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(4, 10, 20, 10));
		panel_4.add(panel_6, "Digital");
		
		btnNewButton_9 = new JButton("Canal 1");
		btnNewButton_9.addActionListener(this);
		panel_6.setLayout(new GridLayout(4, 1, 2, 5));
		panel_6.add(btnNewButton_9);
		
		btnNewButton_10 = new JButton("Canal 2");
		btnNewButton_10.addActionListener(this);
		panel_6.add(btnNewButton_10);
		
		btnNewButton_11 = new JButton("Canal 3");
		btnNewButton_11.addActionListener(this);
		panel_6.add(btnNewButton_11);
		
		btnNewButton_12 = new JButton("Canal 4");
		btnNewButton_12.addActionListener(this);
		panel_6.add(btnNewButton_12);
		
		panel_1 = new JPanel();
		panel_1.setBounds(241, 11, 508, 448);
		panel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_1.setBorder(new TitledBorder(new EmptyBorder(3, 3, 2, 2), "GRÁFICA", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(2, 5));
		
		btnNewButton_13 = new JButton("Graficar");
		btnNewButton_13.addActionListener(this);
		btnNewButton_15 = new JButton("Limpiar");
		btnNewButton_15.addActionListener(this);
		panel_9 = new JPanel();
		panel_9.add(btnNewButton_13);
		panel_9.add(btnNewButton_15);
		
		panel_1.add(panel_9, BorderLayout.SOUTH);
		panel_1.add(panelgrafica, BorderLayout.CENTER);
		
		panel_8 = new JPanel();
		panel_1.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new GridLayout(2, 2, 0, 0));
		
		lblNewLabel_1 = new JLabel("Tm:");
		panel_8.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(""); // Label TM
		panel_8.add(lblNewLabel_2);
		
		btnNewButton_14 = new JButton("ModificarTM");
		btnNewButton_14.addActionListener(this);
		panel_8.add(btnNewButton_14);
		
		textField = new JTextField(); // Cambio TM
		panel_8.add(textField);
		textField.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Activaci\u00F3n de se\u00F1al digital", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 5));
		
		panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(4);
		panel_2.add(panel_7, BorderLayout.CENTER);
		
		chckbxNewCheckBox = new JCheckBox("Salida 1");
		chckbxNewCheckBox.addActionListener(this);
		panel_7.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("Salida 2");
		chckbxNewCheckBox_1.addActionListener(this);
		panel_7.add(chckbxNewCheckBox_1);
		
		chckbxNewCheckBox_2 = new JCheckBox("Salida 3");
		chckbxNewCheckBox_2.addActionListener(this);
		panel_7.add(chckbxNewCheckBox_2);
		
		chckbxNewCheckBox_3 = new JCheckBox("Salida 4");
		chckbxNewCheckBox_3.addActionListener(this);
		panel_7.add(chckbxNewCheckBox_3);
		
		btnNewButton_8 = new JButton("Aplicar");
		panel_2.add(btnNewButton_8, BorderLayout.SOUTH);
		
		lblNewLabel = new JLabel("...");
		panel_2.add(lblNewLabel, BorderLayout.NORTH);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==comboBox) {
			if (comboBox.getSelectedIndex() != -1) {  
	             CardLayout cardLayout = (CardLayout)(panel_4.getLayout());
	             cardLayout.show(panel_4,(String)comboBox.getItemAt(comboBox.getSelectedIndex()));
	             System. out. println((String)comboBox.getItemAt(comboBox.getSelectedIndex()));
	          }		
		}
		
		if(e.getSource()==chckbxNewCheckBox) {
			if(bin1==0) {lblNewLabel.setText("Señal 1 activada");bin1=1;}
			else {lblNewLabel.setText("Señal 1 desactivada");bin1=0;}
			
		}
		
		if(e.getSource()==chckbxNewCheckBox_1) {
			if(bin2==0) {lblNewLabel.setText("Señal 2 activada");bin2=1;}
			else {lblNewLabel.setText("Señal 2 desactivada");bin2=0;}
		}
			
		if(e.getSource()==chckbxNewCheckBox_2)	{
			if(bin3==0) {lblNewLabel.setText("Señal 3 activada");bin3=1;}
			else {lblNewLabel.setText("Señal 3 desactivada");bin3=0;}
		}
		
		if(e.getSource()==chckbxNewCheckBox_3)	{
			if(bin4==0) {lblNewLabel.setText("Señal 4 activada");bin4=1;}
			else {lblNewLabel.setText("Señal 4 desactivada");bin4=0;}
		}
		
		//Botones de canales analogos
		if(e.getSource()==btnNewButton) {
			vector=funcion.lineal(1,0); //(pendiente,b)
			lblNewLabel_3.setText("Señal analoga 1 seleccionada");
		}
		
		if(e.getSource()==btnNewButton_1) {
			vector=funcion.cuadratica();
			lblNewLabel_3.setText("Señal analoga 2 seleccionada");
		}
		
		if(e.getSource()==btnNewButton_2) {
			vector=funcion.seno();
			lblNewLabel_3.setText("Señal analoga 3 seleccionada");
		}
		if(e.getSource()==btnNewButton_3) {
			vector=funcion.lineal(0.1,0); //(pendiente,b)
			lblNewLabel_3.setText("Señal analoga 4 seleccionada");
		}
		if(e.getSource()==btnNewButton_4) {
			vector=funcion.lineal(1.5,0); //(pendiente,b)
			lblNewLabel_3.setText("Señal analoga 5 seleccionada");
		}
		if(e.getSource()==btnNewButton_5) {
			vector=funcion.lineal(1,5); //(pendiente,b)
			lblNewLabel_3.setText("Señal analoga 6 seleccionada");
		}
		if(e.getSource()==btnNewButton_6) {
			
			lblNewLabel_3.setText("Señal analoga 7 seleccionada");
		}
		if(e.getSource()==btnNewButton_7) {
			
			lblNewLabel_3.setText("Señal analoga 8 seleccionada");
		}
		//Botones digitales
		
		if(e.getSource()==btnNewButton_9) {	
			vector=funcion.digital(1, 1);
			lblNewLabel_3.setText("Señal digital 1 seleccionada");
		}
		if(e.getSource()==btnNewButton_10) {
			vector=funcion.digital(2, 2);
			lblNewLabel_3.setText("Señal digital 2 seleccionada");
		}
		if(e.getSource()==btnNewButton_11) {
			vector=funcion.digital(3, 3);
			lblNewLabel_3.setText("Señal digital 3 seleccionada");
		}
		if(e.getSource()==btnNewButton_12) {
			vector=funcion.digital(5, 2);
			lblNewLabel_3.setText("Señal digital 4 seleccionada");
		}
		
		if(e.getSource()==btnNewButton_14) {
			lblNewLabel_2.setText(textField.getText()); 			
		}
		
		if(e.getSource()==btnNewButton_15) {	// boton limpiar
			System.out.print("Clear");
			series.clear();
		}

		if(e.getSource()==btnNewButton_13) {	//boton graficar

			 
			  for(int j=0;j<longitud;j++) {
					System.out.print(j);
					System.out.print(" HOLA ");
					System.out.println(vector[j]); 
					}
	
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

}
