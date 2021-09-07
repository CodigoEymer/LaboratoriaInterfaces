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
	static int ControlGráfica = 0;
    static byte Cabecera,Trama1,Trama2=0x00;
    
    static int DatosRecibidosProteus=0;
    static double DatosRecibidosProteusD=0;
    static int DatoRecibido= 0;
    
    static String puerto = "COM3";
    String puertos[];
    int controlEnvio=0;
    Thread HiloTransmisión;
    
	public HiloComunicador() {
		controlEnvio=1;
		HiloTransmisión = new Thread(this);
		conectar();
		HiloTransmisión.start();		

	}
	public void conectar() {
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
	
	public void reconectar() {
		controlEnvio=0;
		serialPort.close();
		controlEnvio=1;
		conectar();		

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
		}
		
		return this.puertos;
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
			byte[] readBuffer = new byte[1];
			
				try 
				{
					while (inputStream.available() > 0) 
					{
						inputStream.read(readBuffer);
					}
					DatosRecibidosProteus=((readBuffer[0] & 0xff));	// Recibe el valor del ADC
					DatoRecibido=1;
					
					if(Interfas.Cdigital==1) {
						DatosRecibidosProteus=((readBuffer[0] & 0b00000001));

					}
					if(Interfas.Cdigital==2) {
						DatosRecibidosProteus=((readBuffer[0] & 0b00000010));
					}
					if(Interfas.Cdigital==3) {
						DatosRecibidosProteus=((readBuffer[0] & 0b00000100));
					}
					if(Interfas.Cdigital==4) {
						DatosRecibidosProteus=((readBuffer[0] & 0b00001000));
					}
					DatosRecibidosProteusD=(double)DatosRecibidosProteus;
					
				} catch (IOException e) {}
				
				break;
        }
		
	}

}