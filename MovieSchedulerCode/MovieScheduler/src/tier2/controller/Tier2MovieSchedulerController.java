package tier2.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import tier2.view.Tier2MovieSchedulerView;

public class Tier2MovieSchedulerController {
	private Tier2MovieSchedulerView view;
	private Tier2MovieSchedulerServer server;
	
	public Tier2MovieSchedulerController(Tier2MovieSchedulerView view) throws UnknownHostException
	{
		this.view = view;
		try {
			this.server = new Tier2MovieSchedulerServer(1098, this); //TODO hardcoded port number
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Thread t = new Thread(this.server);
		t.start();
	}
	
	public Tier2MovieSchedulerView getView()
	{
		return this.view;
	}
}
