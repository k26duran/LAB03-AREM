package edu.escuelaing.arem.app.datagramas;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeServer implements Runnable{
	
	DatagramSocket socket;

	public DatagramTimeServer() {
		try {
			socket = new DatagramSocket(4445);
		} catch (SocketException ex) {
			Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		DatagramTimeServer ds = new DatagramTimeServer();
		ds.run();
	}
	
	@Override
	public void run() {
		while (true) {
			byte[] buf = new byte[256];
			try {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				String dString = new Date().toString();
				buf = dString.getBytes();
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
				Thread.sleep(3000);
			
			} catch (IOException ex) {
				Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
