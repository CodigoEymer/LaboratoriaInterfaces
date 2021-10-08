import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import DAOint_proceso_vars_data.UpData;
import DAOint_proceso_vars_data.ConexionSerial;
import DAOint_proceso_vars_data.IMPLEMENTint_proceso_vars_data;

public class PrincipalM {
	static HiloComunicador hiloC;
	static String puertos[];
	static Interfas window;
	static UpData updata;
	static ConexionSerial conexionserial;
	static IMPLEMENTint_proceso_vars_data IMipvd;
	public static void main(String [] args)
	{	
		updata = new UpData("COM1", "myintdb2021", "eymer", "1513");
		conexionserial = updata.getConexionserial();
		IMipvd=updata.getIMipvd();
		
		window = new Interfas();
		window.setVisible(true);
			
	}
}
