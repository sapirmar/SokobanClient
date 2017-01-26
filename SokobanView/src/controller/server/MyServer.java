package controller.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;

import controller.Controller;
import controller.SokobanController;

public class MyServer {
	private int port;
	private Client_Handler ch;
	private volatile boolean stop;

	public MyServer(int port,Client_Handler ch) {

		this.port=port;
		this.ch=ch;
		stop=false;

	}
	private void runServer()throws Exception {
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(1000*60);

		try{
		Socket aClient=server.accept(); // blocking call
		new Thread(new Runnable() {
		public void run() {
		try {
		ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
		aClient.getInputStream().close();
		aClient.getOutputStream().close();
		aClient.close();
		} catch (IOException e) {
			e.printStackTrace();
			}
		}
		}).start();
		}catch(SocketTimeoutException e) {
			e.printStackTrace();
			}
		server.close();

		}

	public void start(){
		new Thread(new Runnable() {
		public void run() {
		try{

			runServer();}catch (Exception e){}
		}
		}).start();
		}
		public void stop(){
		stop=true;

		}

}

