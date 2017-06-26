package controller;

import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.command.Command;

public class Controller extends Observable{
	private BlockingQueue<Command> queue;
	private boolean isStopped=false;
	private Thread thread;
	public Controller() {
		queue=new ArrayBlockingQueue<Command>(10);
	}
/**
 * 
 * @param command  
  */
	public void insertCommand(Command command)
	{
		try{
			queue.put(command);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * function execute all the commands from the blocking queue
	 */
	public void start(){
		thread=new Thread(new Runnable() {

			public void run() {
				while(!isStopped){
					try{
						Command cmd=queue.poll(1,TimeUnit.SECONDS);
						if (cmd!=null)
							cmd.execute();
					}catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();


	}
	/**
	 * stop the thread that execute the commands
	 */
	public void stop(){
		isStopped=true;
		//thread.stop();
		//System.exit(0);
	}
}

