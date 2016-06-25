package vn.hus.nlp.tokenizer.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import java.util.Scanner;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import vn.hus.nlp.tokenizer.VietTokenizer;

public class TokenizerServer {

	private static final int port = 2929;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetSocketAddress addr = new InetSocketAddress(port);
		try {
			HttpServer server = HttpServer.create(addr, 0);
			server.createContext("/", new MyHandler());
		    server.setExecutor(Executors.newCachedThreadPool());
		    server.start();
		    System.out.println("server is listening on port " + port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class MyHandler implements HttpHandler {
	
	private static VietTokenizer tokenizer = new VietTokenizer();
	
	public void handle(HttpExchange exchange) throws IOException{
		String method = exchange.getRequestMethod();
		if (method.equalsIgnoreCase("GET")){
			InputStream in = exchange.getRequestBody();
			Scanner sc = new Scanner(in);
			
			Headers resHeader = exchange.getResponseHeaders();
			resHeader.set("Content-Type", "text/plain");			
			
			String inStr = sc.nextLine();
			String result = toJSON(tokenizer.segment(inStr));
			System.out.println(inStr);
			System.out.println(result);
			
			exchange.sendResponseHeaders(200, result.getBytes().length);
			OutputStream resBody = exchange.getResponseBody();
			resBody.write(result.getBytes());
			
			resBody.close();
			
			sc.close();
		}
	}

	private String toJSON(String res){
		StringBuilder json = new StringBuilder("{\"words\":[");
		String[] list = res.split(" +");
		System.out.println(list);
		for (int i=0; i<list.length-1; i++) {
			System.out.println(list[i]);
			json.append("\"" + list[i].replaceAll("_", " ") + "\",");
		}
		if (list.length>0) {
			json.append("\"" + list[list.length - 1].replaceAll("_", " ") + "\"");
		}
		json = json.append("]}");
		return json.toString();
	}
	
}