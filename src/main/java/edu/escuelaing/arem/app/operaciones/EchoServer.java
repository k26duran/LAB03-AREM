package edu.escuelaing.arem.app.operaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine, funcion;
		funcion="coseno";
		while ((inputLine = in.readLine()) != null) {
			StringTokenizer c = new StringTokenizer(inputLine);
			String temp=c.nextToken();
			double valor =0;
			outputLine = "Respuesta ";
			if (esNum(temp)== true) {
				if(funcion.equals("coseno")) {
					Double result = Double.parseDouble(temp);
					valor = Math.cos(result);
					outputLine+=valor;
				}
				else if(funcion.equals("seno")) {
					Double result = Double.parseDouble(temp);
					valor = Math.sin(result);
					outputLine+=valor;
				}
				else {
					Double result = Double.parseDouble(temp);
					valor = Math.tan(result);
					outputLine+=valor;
				}
			}
			else {
				if(temp.equals("fun:sin")) {
					funcion="seno";
					outputLine+="la funcion actual es seno";
				}
				else if(temp.equals("fun:cos") || temp.equals("fun:")) {
					funcion="coseno";
					outputLine+="la funcion actual es coseno";
				}
				else if(temp.equals("fun:tan")) {
					funcion="tangente";
					outputLine+="la funcion actual es tangente";
				}
			}
			out.println(outputLine);
			if (temp.equals("Bye"))
				break;
		}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
	
	public static boolean esNum(String c) {
        boolean resultado;
        try {
            Integer.parseInt(c);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }

}
