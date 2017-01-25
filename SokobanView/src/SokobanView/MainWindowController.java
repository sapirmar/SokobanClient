package SokobanView;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.IntPredicate;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controller.SokobanController;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.data.Level2D;
import view.View;

public class MainWindowController extends Observable implements Initializable ,View  {
	private String userCommand;
	 private Level2D level;
	 private LinkedList<String> params;
	 private StringProperty count_steps;
	 private StringProperty count_time;
	 private StringProperty show_if_win;
	 private Timer t;
	 private int count;
	 private int time_count;
	 private String musicfile;
	 private boolean win;
	 private Media song;

	 @FXML
	 Text if_won;
	 @FXML
	 Label steps;
	 @FXML
	 Label timer;
	@FXML
	SokobanDisplayer sk;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		count =0;
		time_count=0;
		musicfile="./media/sokobansong.mp3";
		startMedia();
		count_time=new SimpleStringProperty();
		count_time.set("Timer: "+time_count);
		count_steps=new SimpleStringProperty();
		count_steps.set("Steps: "+count);
		show_if_win=new SimpleStringProperty();
		if_won.textProperty().bind(show_if_win);
		timer.textProperty().bind(count_time);
		steps.textProperty().bind(count_steps);
		if(level!=null){
		sk.setLevel(level);
		win=level.checkIfWin();

		}


		sk.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sk.requestFocus());
		sk.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode()==KeyCode.UP)
				{
					params= new LinkedList<>();
					params.add("move");
					params.add("up");
					setChanged();
					notifyObservers(params);
					//count++;
					//count_steps.set("Steps: "+count);
					addStep();
					winLevel();


				}
				if (event.getCode()==KeyCode.DOWN)
				{
					params= new LinkedList<>();
					params.add("move");
					params.add("down");
					setChanged();
					notifyObservers(params);
					//count++;
					//count_steps.set("Steps: "+count);
					addStep();
					winLevel();
				}
				if (event.getCode()==KeyCode.LEFT)
				{
					params= new LinkedList<>();
					params.add("move");
					params.add("left");
					setChanged();
					notifyObservers(params);
					//count++;
					//count_steps.set("Steps: "+count);
					addStep();
					winLevel();


				}
				if (event.getCode()==KeyCode.RIGHT)
				{
					params= new LinkedList<>();
					params.add("move");
					params.add("right");
					setChanged();
					notifyObservers(params);
					//count++;
					//count_steps.set("Steps: "+count);
					addStep();
					winLevel();

				}
			}

		});



	}
	public void load()
	{
		show_if_win.set("");
		if(level!=null){
			t.cancel();
			time_count=0;
		}
		params=new LinkedList<>();
		params.add("load");
		FileChooser file=new FileChooser();
		file.setTitle("load level");
		file.setInitialDirectory(new File("./levels"));
		File chosen=file.showOpenDialog(null);
		if(chosen!=null)
		{
		userCommand=chosen.getPath();
		params.add(userCommand);
		setChanged();
		notifyObservers(params);
	    //this.startTimer();

		}


	}
	public void winLevel()
		{
			if ( level.checkIfWin()){
				show_if_win.set("YOU WON!!!");
				t.cancel();
			}
		}
	public void save()
	{
		params=new LinkedList<>();
		params.add("save");
		FileChooser file=new FileChooser();
		file.setTitle("save level");

		file.setInitialDirectory(new File("./levels"));
		file.getExtensionFilters().add(new FileChooser.ExtensionFilter("text(*.txt)","*.txt"));
		file.getExtensionFilters().add(new FileChooser.ExtensionFilter("XMC(*.xml)","*.xml"));
		file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object(*.obj)","*.obj"));
		File chosen=file.showSaveDialog(null);
			if (chosen!=null){
				String path=chosen.getPath();
				params.add(path);
				setChanged();
				notifyObservers(params);

			}


	}
	public void exit()
	{
		userCommand="exit";
		params=new LinkedList<>();
		params.add(userCommand);
		setChanged();
		notifyObservers(params);

	}

	public void startMedia()
	{

		song=new Media(new File(musicfile).toURI().toString());
		MediaPlayer mp=new MediaPlayer(song);
		mp.play();


	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	public void startTimer(){
		t=new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						count_time.set("Timer: "+(getTime_count()));
						time_count++;

					}
				});

			}
		}, 0, 1000);

	}
	public String getUserCommand() {
		return userCommand;
	}
	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
		//setChanged();
		//notifyObservers();
	}
	@Override
	public void setCurrentLevel(Level2D level) {
		this.level=level;
		sk.setLevel(level);
	}

	@Override
	public void display(Level2D level) {
		sk.reDraw();
	}
	public StringProperty getCount_time() {
		return count_time;
	}
	public void setCount_time(StringProperty count_time) {
		this.count_time = count_time;
	}
	public int getTime_count() {
		return time_count;
	}
	public void setTime_count(int time_count) {
		this.time_count = time_count;
	}
	@Override
	public void addStep() {
		++count;
		count_steps.set("Steps: "+getCount());


	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public void stopTimer() {
		if(level!=null)
		{
		t.cancel();
		this.time_count=0;
		}

	}

}
