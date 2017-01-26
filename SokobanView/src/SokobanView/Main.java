package SokobanView;

import java.util.List;

import controller.Controller;
import controller.SokobanController;
import controller.server.Client_Handler;
import controller.server.MyClientHandler;
import controller.server.MyServer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Controller controller;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			MainWindowController view = loader.getController();
			MyModel model= new MyModel();
			//Controller controller=new Controller();

			//get the args from the main
			List<String> args=getParameters().getRaw();
			if(args.size()>1)
			{
			if(args.get(0).equals("server-")){
				MyClientHandler mch = new MyClientHandler();///////////
				int port = Integer.parseInt(args.get(1));//////////////////
				MyServer server =  new MyServer(port, mch);///////////
				controller=new Controller();//

				SokobanController sokoban_controller=new SokobanController(model, view, server);
				model.addObserver(sokoban_controller);
				view.addObserver(sokoban_controller);
				mch.addObserver(sokoban_controller);
			}
			}
			else
			{
				SokobanController sokoban_controller= new SokobanController(model, view);
				model.addObserver(sokoban_controller);
				view.addObserver(sokoban_controller);
			}
			//MyClientHandler mch = new MyClientHandler();///////////
			//int port = 1337;//////////////////
			//MyServer server =  new MyServer(port, mch);///////////
			//server.start();
			//SokobanController sokoban_controller=new SokobanController(model, view, server);

			//SokobanController sokoban_controller= new SokobanController(model, view);
			//model.addObserver(sokoban_controller);
			//view.addObserver(sokoban_controller);
			//mch.addObserver(sokoban_controller);////////////////



			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}
