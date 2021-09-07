import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Image;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Stroke;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interfas extends JFrame implements ActionListener,Runnable{
	//HiloComunicador comu;
	Graficar graf;
	int inicioGraficar=0;
	ImageIcon imagenPlay;
	ImageIcon imagenStop;
	ImageIcon imagenLimpiar;
	ImageIcon imagenGuardar;
	static Color colorDefault = new Color(240, 240, 240);
	static Color colorAzul = new Color(0, 206, 209);
	static Color color = new Color(240, 240, 240);

	static TimeSeries series = new TimeSeries("GRAFICA", Millisecond.class);
	static TimeSeriesCollection datos = new TimeSeriesCollection(series);
	static JFreeChart grafica = ChartFactory.createTimeSeriesChart("", "t", "v", datos, true, true, true);
	final JPanel panelgrafica=new ChartPanel(grafica);
	XYPlot dibujo= grafica.getXYPlot();
	XYLineAndShapeRenderer render= new XYLineAndShapeRenderer();
	Second segundoformat;
	Millisecond milisegundoformat;

	// Clases usadas
	funcionesAD funcion;
	Graficar graficadora;
	FileDialog fd;
	FileWriter writer;
	PrincipalM principalM;
	
	//
	JTextArea AreaDeTexto;
	
	int bin1=0;
	int bin2=0;
	int bin3=0;
	int bin4=0;
	byte sel=0;
	
	int minuto=0;
	static double[] vector;
	static int longitud;
	static Integer TiempoMuestreo=10;
	static StringBuffer Auxbuffer;
	
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
	JComboBox comboBoxPuertos;
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
	JButton btnNewButton_17;	// Boton detener
	
	JLabel lblNewLabel; // Label Activación
	JLabel lblNewLabel_1; // Tm
	JLabel lblNewLabel_2; // Modificador TM
	JLabel lblNewLabel_3; // Label Seleccion Canal
	private JTextField textField; // Cambio TM
	private JPanel panel_10;
	private JButton btnNewButton_16;
	
	static int Cdigital = 0;
	private Component glue;
	private JLabel lblNewLabel_4;
	private JPanel panel_12;
	private JPanel panel_13;
	private Component verticalGlue_1;
	private JPanel panel_14;
	private JPanel panel_15;
	private JLabel lblNewLabel_5;
	private JTextField textField_1;
	private JPanel panel_16;
	private JLabel lblNewLabel_6;
	private JTextField txtCom;
	

	public Interfas() {
		initialize();
		render.setSeriesLinesVisible(0, false);	// Activar o desactivar las lineas conectoras entre puntos
		render.setSeriesPaint(0, Color.BLUE);
		render.setBaseOutlineStroke(new BasicStroke(0.1f));	// Tamaño de los puntos
		dibujo.setRenderer(render);
		
		longitud=  20000;
		funcion= new funcionesAD(longitud);
		lblNewLabel_2.setText("2");
		panel_8.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel_13, lblNewLabel_1, lblNewLabel_2, panel_12, btnNewButton_14, textField, lblNewLabel_4}));
		
		
		lblNewLabel_4 = new JLabel("...");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 3;
		panel_8.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		glue = Box.createGlue();
		getContentPane().add(glue, BorderLayout.EAST);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		vector= new double[longitud];
		

		setTitle("GUI");
		//setBounds(100, 100, 765, 498);
		setBounds(100, 100, 765, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel,BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Selecci\u00F3n de canal", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.add(panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[] {180};
		gbl_panel_10.rowHeights = new int[] {34, 200, 0};
		gbl_panel_10.columnWeights = new double[]{1.0};
		gbl_panel_10.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_10.setLayout(gbl_panel_10);
		
		
		AreaDeTexto= new JTextArea(4,20);
		
		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_10.add(panel_3, gbc_panel_3);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(this);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Analogo", "Digital"}));

		panel_3.add(comboBox);
		
		lblNewLabel_3 = new JLabel("...");
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lblNewLabel_3);
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.anchor = GridBagConstraints.SOUTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel_10.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new CardLayout(0, 0));
		
		panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(4, 10, 4, 10));
		panel_4.add(panel_5, "Analogo");
		
		btnNewButton = new JButton("Canal 1");		
		btnNewButton.addActionListener(this);
		panel_5.setLayout(new GridLayout(8, 1, 4, 4));
		panel_5.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Canal 2");
		btnNewButton_1.addActionListener(this);
		panel_5.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Canal 3");
		btnNewButton_2.addActionListener(this);
		panel_5.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Canal 4");
		btnNewButton_3.addActionListener(this);
		panel_5.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Canal 5");
		btnNewButton_4.addActionListener(this);
		panel_5.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Canal 6");
		btnNewButton_5.addActionListener(this);
		panel_5.add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("Canal 7");
		btnNewButton_6.addActionListener(this);
		panel_5.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("Canal 8");
		btnNewButton_7.addActionListener(this);
		panel_5.add(btnNewButton_7);
		panel_5.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5, btnNewButton_6, btnNewButton_7}));
		
		panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(4, 10, 20, 10));
		panel_4.add(panel_6, "Digital");
		
		btnNewButton_9 = new JButton("Canal 1");
		btnNewButton_9.addActionListener(this);
		panel_6.setLayout(new GridLayout(6, 1, 2, 5));
		
		Component verticalGlue = Box.createVerticalGlue();
		panel_6.add(verticalGlue);
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
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut_1.gridx = 0;
		gbc_verticalStrut_1.gridy = 2;
		panel_10.add(verticalStrut_1, gbc_verticalStrut_1);
		
		panel_1 = new JPanel();
		panel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_1.setBorder(new TitledBorder(new EmptyBorder(3, 3, 2, 2), "GRÁFICA", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		getContentPane().add(panel_1,BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(10, 5));
		
		btnNewButton_13 = new JButton("");	// Boton play
		btnNewButton_13.addActionListener(this);
		//btnNewButton_13.setSize(35, 30);
		btnNewButton_13.setSize(30, 30);
		imagenPlay = new ImageIcon("Media/play1.png");
		Icon iconoPlay = new ImageIcon(imagenPlay.getImage().getScaledInstance(btnNewButton_13.getWidth(), btnNewButton_13.getHeight(), Image.SCALE_DEFAULT));
		btnNewButton_13.setIcon(iconoPlay);
		
		btnNewButton_15 = new JButton("");		// Boton limpiar
		btnNewButton_15.addActionListener(this);
		imagenLimpiar = new ImageIcon("Media/limpiar.png");
		Icon iconoLimpiar = new ImageIcon(imagenLimpiar.getImage().getScaledInstance(btnNewButton_13.getWidth(), btnNewButton_13.getHeight(), Image.SCALE_DEFAULT));
		btnNewButton_15.setIcon(iconoLimpiar);
		
		btnNewButton_17 =  new JButton("");		// Boton detener
		btnNewButton_17.addActionListener(this);
		imagenStop = new ImageIcon("Media/stop1.png");
		Icon iconoStop = new ImageIcon(imagenStop.getImage().getScaledInstance(btnNewButton_13.getWidth(), btnNewButton_13.getHeight(), Image.SCALE_DEFAULT));
		
		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11, BorderLayout.SOUTH);
		btnNewButton_17.setIcon(iconoStop);

		panel_9 = new JPanel();
		panel_9.add(btnNewButton_13);
		panel_9.add(btnNewButton_17);
		panel_9.add(btnNewButton_15);
		
		btnNewButton_16 = new JButton("");	//Boton guardar
		btnNewButton_16.addActionListener(this);
		imagenGuardar = new ImageIcon("Media/guardar.png");
		Icon iconoGuardar = new ImageIcon(imagenGuardar.getImage().getScaledInstance(btnNewButton_13.getWidth(), btnNewButton_13.getHeight(), Image.SCALE_DEFAULT));
		panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnNewButton_16.setIcon(iconoGuardar);
		panel_9.add(btnNewButton_16);
		panel_11.add(panel_9);
		
		panel_14 = new JPanel();
		//panel_11.add(panel_14);
		
		lblNewLabel_5 = new JLabel("MaxH");
		panel_14.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("100");
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField_1.setEnabled(true);

			}
		});
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.VK_ENTER==e.getKeyCode())
		         {
					textField_1.setEnabled(false);
					Integer ItamanoSerie;
					ItamanoSerie= Integer.parseInt (textField_1.getText());
					int TamanoSerie= ItamanoSerie.intValue();
					series.setMaximumItemCount(TamanoSerie); //Tamaño maximo de la serie
		         }
			}
		});
		panel_14.add(textField_1);
		textField_1.setColumns(5);
		
		panel_15 = new JPanel();
		comboBoxPuertos = new JComboBox();
		comboBoxPuertos.addActionListener(this);

		panel_15.add(comboBoxPuertos);

		//panel_11.add(panel_15);
		
		lblNewLabel_6 = new JLabel("Puerto");
		//panel_15.add(lblNewLabel_6);
		
		txtCom = new JTextField();
		txtCom.setEnabled(false);
		txtCom.setHorizontalAlignment(SwingConstants.CENTER);
		txtCom.setText("COM3");
		txtCom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtCom.setEnabled(true);

			}
		});
		txtCom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.VK_ENTER==e.getKeyCode())
		         {
					txtCom.setEnabled(false);
					HiloComunicador.puerto = txtCom.getText();
		         }
			}
		});
		txtCom.setColumns(5);
		
		panel_16 = new JPanel();
		panel_16.setLayout(new BoxLayout(panel_16, BoxLayout.Y_AXIS));
		panel_16.add(panel_14);
		panel_16.add(panel_15);
		panel_11.add(panel_16);
		
		panel_1.add(panelgrafica, BorderLayout.CENTER);
		panelgrafica.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_8 = new JPanel();
		panel_1.add(panel_8, BorderLayout.NORTH);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[] {546};
		gbl_panel_8.rowHeights = new int[] {10, 30, 30, 10};
		gbl_panel_8.columnWeights = new double[]{0.0};
		gbl_panel_8.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel_8.setLayout(gbl_panel_8);
		
		verticalGlue_1 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_1 = new GridBagConstraints();
		gbc_verticalGlue_1.fill = GridBagConstraints.BOTH;
		gbc_verticalGlue_1.insets = new Insets(0, 0, 5, 0);
		gbc_verticalGlue_1.gridx = 0;
		gbc_verticalGlue_1.gridy = 0;
		panel_8.add(verticalGlue_1, gbc_verticalGlue_1);
		
		panel_13 = new JPanel();
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.insets = new Insets(0, 0, 5, 0);
		gbc_panel_13.gridx = 0;
		gbc_panel_13.gridy = 1;
		panel_8.add(panel_13, gbc_panel_13);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel_1 = new JLabel("Tm(Valores entre 2 y 65535 ms):");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 8);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_13.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(""); // Label TM
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		panel_13.add(lblNewLabel_2);
		
		panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_12.insets = new Insets(0, 0, 5, 0);
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 2;
		panel_8.add(panel_12, gbc_panel_12);
		
		btnNewButton_14 = new JButton("ModificarTM");
		btnNewButton_14.addActionListener(this);
		panel_12.setLayout(new GridLayout(0, 2, 8, 0));
		GridBagConstraints gbc_btnNewButton_14 = new GridBagConstraints();
		gbc_btnNewButton_14.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_14.insets = new Insets(0, 0, 0, 8);
		gbc_btnNewButton_14.gridx = 0;
		gbc_btnNewButton_14.gridy = 2;
		panel_12.add(btnNewButton_14);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tmchar= e.getKeyChar();
				if(Character.isLetter(tmchar)) {
					lblNewLabel_4.setText("Favor digitar valores validos de enteros");					
				}
			}
		});
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel_12.add(textField);
		textField.setColumns(20);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Activaci\u00F3n de se\u00F1al digital", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {180};
		gbl_panel_2.rowHeights = new int[] {30, 60, 35};
		gbl_panel_2.columnWeights = new double[]{0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_2.setLayout(gbl_panel_2);
		
		lblNewLabel = new JLabel("...");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.VERTICAL;
		gbc_panel_7.insets = new Insets(0, 8, 5, 0);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 1;
		panel_2.add(panel_7, gbc_panel_7);
		
		chckbxNewCheckBox = new JCheckBox("Salida 1");
		chckbxNewCheckBox.addActionListener(this);
		panel_7.setLayout(new GridLayout(2, 2, 0, 0));
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
		btnNewButton_8.addActionListener(this);
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 5, 0, 5);
		gbc_btnNewButton_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_8.gridx = 0;
		gbc_btnNewButton_8.gridy = 2;
		panel_2.add(btnNewButton_8, gbc_btnNewButton_8);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==comboBox) {
			if (comboBox.getSelectedIndex() != -1) {
	             CardLayout cardLayout = (CardLayout)(panel_4.getLayout());
	             cardLayout.show(panel_4,(String)comboBox.getItemAt(comboBox.getSelectedIndex()));
	             
	             if((String)comboBox.getItemAt(comboBox.getSelectedIndex())=="Analogo") {
	            	 defaultColores("Digital");
	             }else {
	            	 defaultColores("Analogo");
	             }
	             
	          }		
		}
		if(e.getSource()==comboBoxPuertos) {
			
			if (comboBoxPuertos.getSelectedIndex() != -1) {

				String puertoB= (String)comboBoxPuertos.getItemAt(comboBoxPuertos.getSelectedIndex());
				HiloComunicador.puerto=puertoB;
				
				principalM.cambioPuerto();
			}
		}
		
		
		if(e.getSource()==chckbxNewCheckBox) {
			if(bin1==0) {lblNewLabel.setText("Señal 1 activada");bin1=1;sel +=1;}
			else {lblNewLabel.setText("Señal 1 desactivada");bin1=0;sel-=1;}
			
		}
		
		if(e.getSource()==chckbxNewCheckBox_1) {
			if(bin2==0) {lblNewLabel.setText("Señal 2 activada");bin2=1;sel+=2;}
			else {lblNewLabel.setText("Señal 2 desactivada");bin2=0;sel-=2;}
		}
			
		if(e.getSource()==chckbxNewCheckBox_2)	{
			if(bin3==0) {lblNewLabel.setText("Señal 3 activada");bin3=1;sel+=4;}
			else {lblNewLabel.setText("Señal 3 desactivada");bin3=0;sel-=4;}
		}
		
		if(e.getSource()==chckbxNewCheckBox_3)	{
			if(bin4==0) {lblNewLabel.setText("Señal 4 activada");bin4=1;sel+=8;}
			else {lblNewLabel.setText("Señal 4 desactivada");bin4=0;sel-=8;}
		}
		if(e.getSource()==btnNewButton_8)	{// Boton aplicar
			HiloComunicador.Cabecera=0x2F;
			HiloComunicador.Trama1=sel;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		
		//Botones de canales analogos
		
		if(e.getSource()==btnNewButton) {
			lblNewLabel_3.setText("Señal analoga 1 seleccionada");
			defaultColores("Analogo");
			btnNewButton.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x00;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		
		if(e.getSource()==btnNewButton_1) {
			Cdigital=0;
			lblNewLabel_3.setText("Señal analoga 2 seleccionada");
			defaultColores("Analogo");
			btnNewButton_1.setBackground(colorAzul);
			lblNewLabel_3.setBackground(color);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x01;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		
		if(e.getSource()==btnNewButton_2) {
			Cdigital=0;
			lblNewLabel_3.setText("Señal analoga 3 seleccionada");
			defaultColores("Analogo");
			btnNewButton_2.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x02;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		if(e.getSource()==btnNewButton_3) {
			Cdigital=0;
			lblNewLabel_3.setText("Señal analoga 4 seleccionada");
			defaultColores("Analogo");
			btnNewButton_3.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x03;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		if(e.getSource()==btnNewButton_4) {
			Cdigital=0;
			lblNewLabel_3.setText("Señal analoga 5 seleccionada");
			defaultColores("Analogo");
			btnNewButton_4.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x04;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		if(e.getSource()==btnNewButton_5) {
			Cdigital=0;
			lblNewLabel_3.setText("Señal analoga 6 seleccionada");
			defaultColores("Analogo");
			btnNewButton_5.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x05;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		if(e.getSource()==btnNewButton_6) {
			Cdigital=0;
			lblNewLabel_3.setText("Señal analoga 7 seleccionada");
			defaultColores("Analogo");
			btnNewButton_6.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x06;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		if(e.getSource()==btnNewButton_7) {
			Cdigital=0;
			lblNewLabel_3.setText("Señal analoga 8 seleccionada");
			defaultColores("Analogo");
			btnNewButton_7.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x07;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		//Botones digitales
		
		if(e.getSource()==btnNewButton_9) {	
			Cdigital=1;
			lblNewLabel_3.setText("Señal digital 1 seleccionada");
			defaultColores("Digital");
			btnNewButton_9.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x08;
			HiloComunicador.ControlEnvioCabecera=1;
			
		}
		if(e.getSource()==btnNewButton_10) {
			Cdigital=2;
			lblNewLabel_3.setText("Señal digital 2 seleccionada");
			defaultColores("Digital");
			btnNewButton_10.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x09;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		if(e.getSource()==btnNewButton_11) {
			Cdigital=3;
			lblNewLabel_3.setText("Señal digital 3 seleccionada");
			defaultColores("Digital");
			btnNewButton_11.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x0A;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		if(e.getSource()==btnNewButton_12) {
			Cdigital=4;
			lblNewLabel_3.setText("Señal digital 4 seleccionada");
			defaultColores("Digital");
			btnNewButton_12.setBackground(colorAzul);
			HiloComunicador.Cabecera=0x1F;
			HiloComunicador.Trama1=0x0B;
			HiloComunicador.ControlEnvioCabecera=1;
		}
		
		if(e.getSource()==btnNewButton_14) {	// Boton CambioMuestreo


			TiempoMuestreo= Integer.parseInt (textField.getText());
			Double V=Double.valueOf(textField.getText());
			if(V>1 && V<65531) {
				
				Integer Desplazado= (TiempoMuestreo>>8);
				byte trama1= TiempoMuestreo.byteValue();
				byte trama2= Desplazado.byteValue();
				
				HiloComunicador.Cabecera=0x3F;
				HiloComunicador.Trama1=trama1;
				HiloComunicador.Trama2=trama2;
				
				HiloComunicador.ControlEnvioCabecera=2;
				lblNewLabel_2.setText(textField.getText());
				lblNewLabel_4.setText("...");
				
			}else {
				lblNewLabel_4.setText("Favor digitar un valor valido entre 2 y  65530");
				
			}
			
		}
		
		if(e.getSource()==btnNewButton_15) {	// boton limpiar
			series.clear();
		}

		if(e.getSource()==btnNewButton_13) {	//boton graficar
			if(inicioGraficar == 0) {
				inicioGraficar=1;
				graficadora = new Graficar();
			}
			
			HiloComunicador.Cabecera=0x4f;
			HiloComunicador.Trama1=0x01;
			HiloComunicador.ControlEnvioCabecera=1;
			HiloComunicador.ControlGráfica=1;
	
		}
		
		if(e.getSource()==btnNewButton_17) {	//boton Detener
			
			Auxbuffer = new StringBuffer(10000);
			Auxbuffer = Graficar.buffer;
			//graficadora = new Graficar();
			HiloComunicador.Cabecera=0x4f;
			HiloComunicador.Trama1=0x00;
			HiloComunicador.ControlEnvioCabecera=1;
			HiloComunicador.ControlGráfica=0;
	
		}
		
		if(e.getSource()==btnNewButton_16) {	//boton guardar
			GuargarComo();				 
	
		}				
		
	}
	
	public void GuargarComo() {
		
		fd = new FileDialog(this,"Guardar Datos",FileDialog.SAVE);
		fd.setVisible(true);
		
		try {
			//graficadora = new Graficar();
			writer = new FileWriter(fd.getDirectory()+fd.getFile()+".txt");
			writer.write(Auxbuffer.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void defaultColores(String tipoCanal) {
		if(tipoCanal=="Analogo") {
			btnNewButton.setBackground(colorDefault);
			btnNewButton_1.setBackground(colorDefault);
			btnNewButton_2.setBackground(colorDefault);
			btnNewButton_3.setBackground(colorDefault);
			btnNewButton_4.setBackground(colorDefault);
			btnNewButton_5.setBackground(colorDefault);
			btnNewButton_6.setBackground(colorDefault);
			btnNewButton_7.setBackground(colorDefault);
		}
		if(tipoCanal=="Digital") {
			btnNewButton_9.setBackground(colorDefault);
			btnNewButton_10.setBackground(colorDefault);
			btnNewButton_11.setBackground(colorDefault);
			btnNewButton_12.setBackground(colorDefault);
		}
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
