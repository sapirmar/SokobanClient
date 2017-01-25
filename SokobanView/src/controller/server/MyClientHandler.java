package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;

import javafx.beans.Observable;

public class MyClientHandler extends java.util.Observable implements Client_Handler {
private BufferedReader reader;
private PrintWriter writer;
private String command;
private LinkedList<String> params;

public MyClientHandler() {
	params = new LinkedList<String>();
	command = "";
}
	@Override
	public void handleClient(InputStream informClient, OutputStream outToClient) {
	reader = new BufferedReader(new InputStreamReader(informClient));
	writer= new PrintWriter(outToClient);
	writer.print("menu: \n-load+file name\n-save+file name\n-move up/down/right/left\n-exit");
	writer.flush();
	do{
	try {
		command=reader.readLine();
		String[] arr = command.split(" ");
		for (String s : arr)
		{
			params.add(s);
		}
		setChanged();
		notifyObservers(params);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}while(!command.equals("exit"));
	//params.add("exit");
	//setChanged();
	//notifyObservers(params);



	}

}
