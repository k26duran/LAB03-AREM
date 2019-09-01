package edu.escuelaing.arem.app;

import java.io.*;
import java.net.*;


public class Ejercicio1 {
	
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://github.com:443/k26duran");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
		
			System.out.println(url.getFile());
			System.out.println(url.getAuthority());
			System.out.println(url.getProtocol());
			System.out.println(url.getPath());
			System.out.println(url.getHost());
			System.out.println(url.getPort());
			System.out.println(url.getQuery());
			System.out.println(url.getFile());
			System.out.println(url.getRef());
			
			

		} catch (IOException x) {
			System.err.println(x);

		}
	}

}