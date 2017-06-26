package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;

public class MyClientHandler extends java.util.Observable implements Client_Handler {

	@Override
	public void handleClient(InputStream informClient, OutputStream outToClient) {
		//init streams
		BufferedReader fromClient = new BufferedReader( new InputStreamReader(informClient));
		PrintWriter toClient = new PrintWriter(outToClient, true);
		
		String input;
		String output;
		boolean isStopped=false;
		
		while(!isStopped==true){
			printInstructions(toClient);//print the menu options to the client
			try {
				input=fromClient.readLine();
				String[] command=input.split(" ");
				
				switch(command[0])
				{
				case "move" : case "load": case "save":{
					LinkedList<String> params=new LinkedList<>();
					params.add(command[0]);
					params.add(command[1]);
					//update the observer about the new commands
					setChanged();
					notifyObservers(params);
					break;
				}
				case "exit":{
					isStopped=false;
					LinkedList<String> params=new LinkedList<>();
					params.add(command[0]);
					//update the observer about the new commands
					setChanged();
					notifyObservers(params);
					break;
				}
				default:{
					toClient.println("wrong input");
					break;
				}
					
				}
				
				fromClient.close();
				toClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
	}

	public void printInstructions(PrintWriter toClient){
		toClient.println("MENU: ");
		toClient.println("'move <up,down,left,right>' ");
		toClient.println("'load <levelname>'");
		toClient.println("'save <levelname>'");
		toClient.println("'exit'");
	}



}
