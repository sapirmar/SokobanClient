package controller.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer implements Server {
	
	private volatile boolean isStopped = false;
	private int port = 0;
	private Client_Handler ch = null;
	private ExecutorService executor = Executors.newCachedThreadPool();
	ServerSocket mainSocket = null;
	
	
	
	public MyServer(int port, Client_Handler ch) {
		
		this.port = port;
		this.ch = ch;
	}
	private void runServer() throws Exception {
		mainSocket = new ServerSocket(port); // The server socket
		System.out.println("The server is alive.");

		while (isStopped != true) {
			Socket connectionSocket = mainSocket.accept(); // blocking call

			
			System.out.println("client connected to server on port :: " + connectionSocket.getPort());

			executor.submit(new Runnable() {				
				@Override
				public void run() {
					try {
						ch.handleClient(connectionSocket.getInputStream(), connectionSocket.getOutputStream());
					} catch (Exception e) {e.printStackTrace();} 	
				}
			});
		}	
		mainSocket.close();
	}

	@Override
	public void start() throws Exception {
		if (ch == null)
			throw new Exception("There is not client handler");

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {runServer(); } catch (Exception e) {}
			}
		}).start();
		
	}

	@Override
	public void stop() throws Exception {
		isStopped = true;
		mainSocket.close();
		executor.shutdown();
		System.out.println("Server is closed");
		
	}

}

