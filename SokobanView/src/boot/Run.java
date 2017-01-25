package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.SokobanController;
import controller.server.Client_Handler;
import controller.server.Clienthandler;
import controller.server.MyServer;
import view.MyView;
import model.MyModel;

public class Run {


	public static void main(String[] args) {

		MyModel model=new MyModel();
		Clienthandler ch=new Clienthandler();//////////// we need define who is the client handler
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer=new PrintWriter(System.out);//after printing do writer.flush()
		MyView view= new MyView(reader,writer,"exit");
		SokobanController sk= new SokobanController(model, view);
		model.addObserver(sk);
		view.addObserver(sk);
		if(args.length>0)
		if ((args[0]=="server-")&&(isInt(args[1]))){
			int port = Integer.parseInt(args[1]);
			MyServer myserver=new MyServer(port,ch);
			myserver.addObserver(sk);

		}
		//SokobanController sk= new SokobanController(model, view);
		//model.addObserver(sk);
		//view.addObserver(sk);
		view.start();

	}

	public static boolean isInt(String str)
	{
	  try
	  {
	    int d = Integer.parseInt(str);
	  }
	  catch(NumberFormatException nfe)
	  {
	    return false;
	  }
	  return true;
	}

}
