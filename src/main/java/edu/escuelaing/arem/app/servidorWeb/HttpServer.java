package edu.escuelaing.arem.app.servidorWeb;

import java.net.*;
import java.io.*;

public class HttpServer {
	
	private static Socket clientSocket = null;
	private static ServerSocket serverSocket = null;
	private static boolean seguir = true;

	public static void main(String[] args) throws IOException {

		try {
			serverSocket = new ServerSocket(35000);
			while (seguir) {
				System.out.println("Listo para recibir ... " );
				clientSocket = serverSocket.accept();
				String path = getPageRequest(clientSocket.getInputStream());
				if (path.equals("/html1"))
					html1(clientSocket.getOutputStream());
				else if (path.equals("/html2"))
					html2(clientSocket.getOutputStream());
				else if (path.equals("/image1"))
					image1(clientSocket.getOutputStream());
				else if (path.equals("/image2"))
					image2(clientSocket.getOutputStream());
				else if (path.equals("/image3"))
					image3(clientSocket.getOutputStream());
				else if (path.equals("/css1"))
					css1(clientSocket.getOutputStream());
				clientSocket.close();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		} finally {
			serverSocket.close();
		}
	}
	
	public static void html1(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>HTML1</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<h1>My Web Site, example html1</h1>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	public static void html2(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>HTML 2</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<h1>HTML 2</h1>" + "\r\n");
		response.println("<p>Example</p>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	public static void image1(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<img src=\"https://images.clarin.com/2019/04/17/mCZ-bh5Er_1256x620__1.jpg\"></img>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	public static void image2(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<img src=\"https://www.perritosbebes.com/wp-content/uploads/2018/06/shar-pei-compressor.jpg\"></img>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}

	public static void image3(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/html" + "\r\n");
		response.println("<!DOCTYPE html>" + "\r\n");
		response.println("<html>" + "\r\n");
		response.println("<head>" + "\r\n");
		response.println("<meta charset=\"UTF-8\">" + "\r\n");
		response.println("<title>Index</title>" + "\r\n");
		response.println("</head>" + "\r\n");
		response.println("<body>" + "\r\n");
		response.println("<img src=\"https://s3-us-west-2.amazonaws.com/laprensa-bucket/wp-content/uploads/2016/08/12153212/470-DOM-10DE.jpg\"></img>" + "\r\n");
		response.println("</body>" + "\r\n");
		response.println("</html>" + "\r\n");
		response.flush();
		response.close();
	}
	
	public static void css1(OutputStream os) {
		PrintWriter response = new PrintWriter(os, true);
		response.println("HTTP/1.1 200 OK");
		response.println("Content-Type: text/css" + "\r\n");
		response.println("p {" + "\r\n");
		response.println("color: blue;" + "\r\n");
		response.println("width: 300px;" + "\r\n");
		response.println("}" + "\r\n");
		response.flush();
		response.close();

	}

	public static String getPageRequest(InputStream is) throws IOException {
		is.mark(0);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine = null;
		while ((inputLine = in.readLine()) != null) {
			if (!in.ready())
				break;
			if (inputLine.contains("GET")) {
				String[] get = inputLine.split(" ");
				return get[1];
				}
			break;}
		return "path";
	}


	public static void writeRequest(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine = null;
		while ((inputLine = in.readLine()) != null) {
			if (!in.ready())
				break;
			System.out.println("Received: " + inputLine);
		}
	}

}
