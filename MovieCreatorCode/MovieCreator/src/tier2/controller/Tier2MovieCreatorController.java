package tier2.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreatorController implements Runnable {
	private ServerSocket welcomeSocket;
	private Tier2MovieCreatorView view;
	public Tier2MovieCreatorController(Tier2MovieCreatorView view)
	{
		this.view = view;
		startServer(1098);
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
	@Override
	public void run() {
		while (true)
	      {
	         view.show("Waiting for a client...");
	         try
	         {
	            Socket socket = welcomeSocket.accept();
	            Tier2MovieCreatorThreadHandler c;
	            c = new Tier2MovieCreatorThreadHandler(socket, view);
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
