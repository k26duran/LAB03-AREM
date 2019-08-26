package edu.escuelaing.arem.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Ejercicio2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userUrl = br.readLine();
		String contenido="";

	    URL pagina = new URL("http://www."+ userUrl);
	    
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(pagina.openStream()))) {
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				contenido+=inputLine;
			}
		} catch (IOException io) {
			System.err.println(io);

		}
	    BufferedWriter bw = null;
	    FileWriter fw = null;

	    try {
	        File file = new File("resultado.html");
	        // Se crea el archivo si no existe
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        fw = new FileWriter(file.getAbsoluteFile(), true);
	        bw = new BufferedWriter(fw);
	        bw.write(contenido);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (bw != null)
	                bw.close();
	            if (fw != null)
	                fw.close();
	        } catch (IOException io) {
	            io.printStackTrace();
	        }
	    }
	}
}
