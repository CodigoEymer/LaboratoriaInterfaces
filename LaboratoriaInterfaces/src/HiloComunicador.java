import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;


import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class HiloComunicador implements Runnable, SerialPortEventListener{
	
	static Enumeration<?> portList;
	static CommPortIdentifier portId;
	static SerialPort serialPort;
	static InputStream inputStream;
	static OutputStream outputStream;
	
	static int ControlEnvioCabecera=0;
    static byte Cabecera,Trama1,Trama2=0x00;
    int Cdigital=0;
    
    static int DatosRecibidosProteus=0;
    static double DatosRecibidosProteusD=0;
    float valor=0;
    static int DatoRecibido= 0;
    byte[] readBuffer;
    
    String puerto = null;
    String puertos[];
    int controlEnvio=0;
    Thread HiloTransmisión;
    
	public HiloComunicador(String puerto) {
		this.puerto=puerto;
		controlEnvio=1;
		
	}
	public void openSerial() {
		controlEnvio=1;
		portList = CommPortIdentifier.getPortIdentifiers();
		
		try {
			while(portList.hasMoreElements()) {
				
				portId = (CommPortIdentifier) portList.nextElement();
				if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					if (portId.getName().equals(puerto)) // if (portId.getName().equals("/dev/term/a"))
					 {
						try 
						{
				            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
				        } catch (PortInUseException e) {}

				        try 
						{
				            inputStream = serialPort.getInputStream();
				            outputStream = serialPort.getOutputStream();
				        } catch (IOException e) {}
						
						try 
						{
				            serialPort.addEventListener(this);
						} catch (TooManyListenersException e) {}
				        serialPort.notifyOnDataAvailable(true);
						
				        try {
				            serialPort.setSerialPortParams(9600,
				                SerialPort.DATABITS_8,
				                SerialPort.STOPBITS_1,
				                SerialPort.PARITY_NONE);
				        } catch (UnsupportedCommOperationException e) {}
					 }
					}
				}
			
			
			} catch(Exception e) {
			e.printStackTrace();
			}
	}
	
	public void conectar() {
		HiloTransmisión = new Thread(this);
		openSerial();	
		HiloTransmisión.start();
	}
	public void reconectar() {
		serialPort.close();
		openSerial();	
	}
	public void desconectar() {
		serialPort.close();
		controlEnvio=0;
	}
	
	
	
	public String[] Vpuertos() {
		int n=0;
		portList = CommPortIdentifier.getPortIdentifiers();
		while(portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			n++;
		}
		portList = CommPortIdentifier.getPortIdentifiers();
		puertos= new String[n];
		for(int i=0;i<n;i++) {
			portId = (CommPortIdentifier) portList.nextElement();
			
			puertos[i]=portId.getName();
			System.out.println(puertos[i]);
		}
		
		return this.puertos;
	}
	
	public void setPuerto(String puerto) {
		this.puerto=puerto;
	}

	@Override
	public void run() {
		
		try {
		while(controlEnvio==1) {
			
			Thread.sleep(10);
			
			if(ControlEnvioCabecera==1) {
				outputStream.write(Cabecera);
				outputStream.write(Trama1);
				
				ControlEnvioCabecera=0;
			}
			if(ControlEnvioCabecera==2) {
				outputStream.write(Cabecera);
				outputStream.write(Trama1);
				outputStream.write(Trama2);
				
				ControlEnvioCabecera=0;
			}
		}
		}catch (InterruptedException e) {} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		
		switch(event.getEventType()) 
		{
			case SerialPortEvent.BI:
			case SerialPortEvent.OE:
			case SerialPortEvent.FE:
			case SerialPortEvent.PE:
			case SerialPortEvent.CD:
			case SerialPortEvent.CTS:
			case SerialPortEvent.DSR:
			case SerialPortEvent.RI:
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				break;
			case SerialPortEvent.DATA_AVAILABLE:
			readBuffer = new byte[1];
			
				try 
				{
					while (inputStream.available() > 0) 
					{
						inputStream.read(readBuffer);
					}
					DatosRecibidosProteus=((readBuffer[0] & 0xff));	// Recibe el valor del ADC
					DatoRecibido=1;
					
					if(Cdigital==1) {
						DatosRecibidosProteus=((readBuffer[0] & 0b00000001));
					}
					if(Cdigital==2) {
						DatosRecibidosProteus=((readBuffer[0] & 0b00000010));
					}
					if(Cdigital==3) {
						DatosRecibidosProteus=((readBuffer[0] & 0b00000100));
					}
					if(Cdigital==4) {
						DatosRecibidosProteus=((readBuffer[0] & 0b00001000));
					}
					
					if(Cdigital==0) {
						valor=(float)DatosRecibidosProteus;
						valor=valor*(0.01953125f);	// Convierte el valor del ADC en voltaje
						}else {
							valor=(float)DatosRecibidosProteus;
							if(valor!=0) {
								valor=5.0f;
							}
						}
					
				} catch (IOException e) {}
				
				break;
        }
		
	}
	/**
	 * Este metodo  Devuelve la lectura recibida por el puerto serial.
	 * 
	 * @return float con valor de la lectura hecha por el microcontrolador.
	 */
	public float newLectura() {
		return this.valor;
	}
	
	public void setCanal(int Cdigital) {
		this.Cdigital=Cdigital;
	}
	
	/**
	 * Este metodo  Devuelve flag de dato recibido.
	 * 
	 * @return Int cn flag de dato recibido por serial.
	 */
	public int getDatoRecibido() {
		return this.DatoRecibido;
	}
	public void setDatoRecibido() {
		this.DatoRecibido=0;
	}
	/**
	 * Este metodo permite establecer la bandera para enviar una o dos tramas.
	 * 
	 * @param CEC int que establece la bandera. CEC=1 indica que se enviara solo el byte trama1, CEC=2 indica que se enviara dos bytes, la trama1 y trama 2. 
	 */
	public void setControlEnvioCabecera(int CEC) {
		this.ControlEnvioCabecera=CEC;
	}
	/**
	 * Este metodo permite establecer la cabecera del protocolo de transmicion para enviar los comandos hacia el microcontrolador.
	 * Cabecera 1: 0x1f	Elección de canales de entrada
	 * Cabecera 2: 0x2f	Activación de señales de salida
	 * Cabecera 3: 0x3f	Cambio de FM
	 * Cabecera 4: 0x4f Para graficar y no graficar.
	 * @param Cabecera byte que establece la cabecera
	 */
	public void setCabecera(byte Cabecera) {
		this.Cabecera=Cabecera;
	}
	/**
	 * Este metodo permite establece el byte a enviar por la trama 1 del protocolo de transmicion.
	 * @param Trama1 byte que establece la trama 1 a enviar
	 */
	public void setTrama1(byte Trama1) {
		this.Trama1=Trama1;
	}
	/**
	 * Este metodo permite establece el byte a enviar por la trama 2 del protocolo de transmicion.
	 * @param Trama2 byte que establece la trama 2 a enviar
	 */
	public void setTrama2(byte Trama2) {
		this.Trama2=Trama2;
	}

}