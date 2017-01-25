package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;


import java.util.Scanner;

import model.data.Level;
import model.data.Level2D;

public class MyView extends Observable implements View {
private List<String> params ;
private String exitString;
private PrintWriter writer;
private BufferedReader reader;


//באפר רידר ורייטר
public MyView(BufferedReader reader,PrintWriter writer,String exitString) {
	params= new LinkedList<String>();
	this.reader=reader;
	this.writer=writer;
	this.exitString=exitString;
}


	public void start() {

		Thread thread = new Thread (new Runnable() {

		
	@Override
	public void run() {
		String commandLine="";
	do{
	writer.print("enter command : ");
	writer.flush();
	try{
	commandLine=reader.readLine();
	String[] arr = commandLine.split(" ");
	for (String s : arr)
	{
		params.add(s);
	}
	setChanged();
	notifyObservers(params);

	}catch(IOException e)
	{
		e.printStackTrace();
	}
}while(!(commandLine.equals(exitString)));
	}
});
thread.start();

	}


	@Override
	public void setCurrentLevel(Level2D level) {
		// TODO Auto-generated method stub

	}


	@Override
	public void display(Level2D level) {
		// TODO Auto-generated method stub

	}


/*	@Override
	public void displayError(String msg) {
		writer.write("Error : "+ msg);
		writer.flush();
	}
*/
}
