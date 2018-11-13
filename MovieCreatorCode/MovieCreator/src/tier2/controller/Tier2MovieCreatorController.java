package tier2.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import common.Package;
import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreatorController implements Runnable {
	private ServerSocket welcomeSocket;
	private Tier2MovieCreatorView view;
	private Socket serverSocket;
	public Tier2MovieCreatorController(Tier2MovieCreatorView view) throws UnknownHostException
	{
		this.view = view;
		startServer(1098);
		
		// Connecting to database server socket, remove hard coding, took it from thread handler so server can connect to db 
		// without having a t1 client first
		
		try {
			view.show("Connecting to tier3 server");
			serverSocket = new Socket("localhost", 1097);
		} catch (IOException e) {
			view.show("Database offline, couldn't connect to server");
			e.printStackTrace();
		}
		
	}
	
	
	public void startServer(int port)
	{
		try {
			view.show("Starting tier2 server");
			welcomeSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Socket getServerSocket()
	{
		return serverSocket;
	}
	@Override
	public void run() {
		while (true)
	      {
	         view.show("Waiting for a client...");
	         try
	         {
	            Socket socket = welcomeSocket.accept();
	            Tier2MovieCreatorThreadHandler c;
	            c = new Tier2MovieCreatorThreadHandler(socket, view, serverSocket);
	            Thread t = new Thread(c);
	            t.start();
	            view.show("Client connected");
	         }
	         catch (IOException e)
	         {
	            view.show("Error in server. Message: " + e.getMessage());
	         }
	      }
	}
	

}
