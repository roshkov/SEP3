package tier3.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tier3.view.Tier3MovieCreatorView;

public class Tier3MovieCreatorController implements Runnable {
	private ServerSocket welcomeSocket;
	private Tier3MovieCreatorView view;
	public Tier3MovieCreatorController(Tier3MovieCreatorView view)
	{
		this.view = view;
		startServer(1097);
	}
	
	
	public void startServer(int port)
	{
		try {
			view.show("Starting tier3 server");
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
	            Tier3MovieCreatorThreadHandler c;
	            c = new Tier3MovieCreatorThreadHandler(socket, view);
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
