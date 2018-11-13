package tier2.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreatorController {
	
	private Tier2MovieCreatorView view;
	private Tier2MovieCreatorServer server;
	
	public Tier2MovieCreatorController(Tier2MovieCreatorView view) throws UnknownHostException
	{
		this.view = view;
		try {
			this.server = new Tier2MovieCreatorServer(1098, this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread t = new Thread(this.server);
		t.start();
	}
	
	public Tier2MovieCreatorView getView()
	{
		return this.view;
	}
}
