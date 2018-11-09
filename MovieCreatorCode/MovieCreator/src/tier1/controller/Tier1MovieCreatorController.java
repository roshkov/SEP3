package tier1.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import common.Package;
import tier1.view.Tier1MovieCreatorView;

public class Tier1MovieCreatorController implements Runnable {
	private Socket serverSocket;
	private Tier1MovieCreatorView view;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public Tier1MovieCreatorController(Tier1MovieCreatorView view) {
		try {
			this.view = view;
			view.show("Starting tier1 client");
			serverSocket = new Socket("localhost", 1098);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean ok = true;
		while (ok) {
			ok = false;
			view.show("Waiting for a client...");
			Package GET = new Package("GET");

			try {
				// Read from stream : String tmp = inputStream.readUTF();
				inputStream = new DataInputStream(serverSocket.getInputStream());
				// Write into stream : outputStream.writeUTF(new String("text to send"));
				outputStream = new DataOutputStream(serverSocket.getOutputStream());
				Gson gson = new Gson();
				
				// send to tier 2 server
				String json = gson.toJson(GET);
				outputStream.writeUTF(json);
				
				// receive from tier 2 server
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer , Package.class);
				view.show("package: " + request.getText());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}