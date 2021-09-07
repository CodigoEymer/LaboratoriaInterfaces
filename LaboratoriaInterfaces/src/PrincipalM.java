import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PrincipalM {
	static HiloComunicador hiloC;
	static String puertos[];
	static Interfas window;
	public static void main(String [] args)
	{		
		window = new Interfas();
		window.setVisible(true);				
		hiloC = new HiloComunicador();
		
		//	Visualizador de puertos
		puertos= hiloC.Vpuertos();
		window.comboBoxPuertos.setModel(new DefaultComboBoxModel(puertos));
	}
	
	public static void cambioPuerto() {
		puertos= hiloC.Vpuertos();
		hiloC.reconectar();
	}
}
