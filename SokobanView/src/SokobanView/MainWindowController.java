package SokobanView;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import java.util.function.Predicate;

import org.hibernate.cfg.SetSimpleValueTypeSecondPass;

import com.sun.javafx.geom.AreaOp.EOWindOp;
import com.sun.prism.impl.Disposer.Record;

import db.Records;
import db.RowDb;

import javafx.application.Platform;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javassist.expr.NewArray;
import model.data.Level2D;
import view.View;

public class MainWindowController extends Observable implements Initializable, View {
	private String userCommand;
	private Level2D level;
	private LinkedList<String> params;
	private HashMap<String, String> hm;
	private StringProperty count_steps;
	private StringProperty count_time;
	private StringProperty show_if_win;
	private Timer t;
	private int count;
	private int time_count;
	private String musicfile;
	private boolean win;
	private Media song;
	final String records = "Records";
	private TextField searchField;
	Stage stage;
	ObservableList<RowDb> data;
	Scene scene;

	// private TableView<RowDb> table2;

	//@FXML
	private TableView<RowDb> table;
	@FXML
	Text if_won;
	@FXML
	Text steps;
	@FXML
	Label timer;
	@FXML
	SokobanDisplayer sk;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		hm = new HashMap<String, String>();
		this.readKeysFromXML();
		count = 0;
		time_count = 0;
		musicfile = "./media/sokobansong.mp3";
		startMedia();
		count_time = new SimpleStringProperty();
		count_time.set("Timer: " + time_count);
		count_steps = new SimpleStringProperty();
		count_steps.set("Steps: " + count);
		show_if_win = new SimpleStringProperty();
		if_won.textProperty().bind(show_if_win);
		timer.textProperty().bind(count_time);
		steps.textProperty().bind(count_steps);
		stage = new Stage();
		if (level != null) {
			sk.setLevel(level);
			win = level.checkIfWin();
		}
		sk.reDraw();

		sk.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> sk.requestFocus());
		sk.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (level != null) {
					userCommand = hm.get("" + event.getCode());
					params = new LinkedList<>();
					String[] arr;
					arr = userCommand.split(" ");
					for (String string : arr) {
						params.add(string);
					}
					setChanged();
					notifyObservers(params);

					winLevel();

				}
			}

		});

		table = new TableView<RowDb>();

		/*
		table.setRowFactory(tv->{
			TableRow<RowDb> row=new TableRow<>();
			row.setOnMouseClicked(e->{
				RowDb rowdata=row.getItem();
				System.out.println(rowdata.getNickName());

			});
			return row;
		});
		/*table.addEventFilter(MouseEvent.MOUSE_CLICKED, e->table.requestFocus());
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
			//RowDb rowchose=table.getSelectionModel().getSelectedItem();


			}
		});*/



	}


	public void initializeDefaultKeys() {
		hm.put("UP", "move up");
		hm.put("DOWN", "move down");
		hm.put("LEFT", "move left");
		hm.put("RIGHT", "move right");

	}

	public void load() {
		show_if_win.set("");
		if (level != null) {
			t.cancel();
			time_count = 0;
			count = 0;
			count_steps.set("Steps: " + getCount());
		}

		params = new LinkedList<>();
		params.add("load");
		FileChooser file = new FileChooser();
		file.setTitle("load level");
		file.setInitialDirectory(new File("./levels"));
		File chosen = file.showOpenDialog(null);
		if (chosen != null) {
			userCommand = chosen.getPath();
			params.add(userCommand);
			setChanged();
			notifyObservers(params);

		}

	}

	public void winLevel() {
		if (level.checkIfWin()) {
			show_if_win.set("YOU WON!!!");
			t.cancel();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					recordDialog();

				}
			});

		}
	}

	public void save() {
		params = new LinkedList<>();
		params.add("save");
		FileChooser file = new FileChooser();
		file.setTitle("save level");

		file.setInitialDirectory(new File("./levels"));
		file.getExtensionFilters().add(new FileChooser.ExtensionFilter("text(*.txt)", "*.txt"));
		file.getExtensionFilters().add(new FileChooser.ExtensionFilter("XMC(*.xml)", "*.xml"));
		file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object(*.obj)", "*.obj"));
		File chosen = file.showSaveDialog(null);
		if (chosen != null) {
			String path = chosen.getPath();
			params.add(path);
			setChanged();
			notifyObservers(params);

		}

	}

	public void exit() {
		userCommand = "exit";
		params = new LinkedList<>();
		params.add(userCommand);
		setChanged();
		notifyObservers(params);

	}

	public void startMedia() {

		song = new Media(new File(musicfile).toURI().toString());
		MediaPlayer mp = new MediaPlayer(song);
		mp.play();

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	public void startTimer() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						count_time.set("Timer: " + (getTime_count()));
						time_count++;

					}
				});

			}
		}, 0, 1000);

	}

	public void readKeysFromXML() {
		XMLDecoder xd;
		try {
			xd = new XMLDecoder(new FileInputStream(new File("./keys/defaultKeys.xml")));
			hm.put((String) xd.readObject(), "move up");
			hm.put((String) xd.readObject(), "move down");
			hm.put((String) xd.readObject(), "move right");
			hm.put((String) xd.readObject(), "move left");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getUserCommand() {
		return userCommand;
	}

	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
		// setChanged();
		// notifyObservers();
	}

	@Override
	public void setCurrentLevel(Level2D level) {
		this.level = level;
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
		count_steps.set("Steps: " + getCount());

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void stopTimer() {
		if (level != null) {
			t.cancel();
			this.time_count = 0;
		}

	}

	public void recordDialog() {

		params = new LinkedList<>();
		params.add("record");
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("You WON!!!");
		alert.setHeaderText("Answer the question :");
		alert.setContentText("Are you want to save your record?");
		Optional<ButtonType> result = alert.showAndWait();// the choise
		if (result.get() == ButtonType.OK) {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Save record");
			dialog.setHeaderText("Look, save your record");
			dialog.setContentText("Please enter your Nickname:");
			// Traditional way to get the response value.
			Optional<String> result_name = dialog.showAndWait();
			if (result_name.isPresent()) {
				System.out.println("Your name: " + result_name.get());
				params.add(result_name.get()); // nickname
				params.add(level.getLevelId()); // level id
				params.add("" + time_count); // timer
				params.add("" + count); // steps
				setChanged();
				notifyObservers(params);
				Alert showAlert = new Alert(AlertType.CONFIRMATION);
				showAlert.setTitle("##RECORDS##");
				showAlert.setHeaderText("Answer the question :");
				showAlert.setContentText("Are you want to see the international Recrods table?");
				Optional<ButtonType> result2 = showAlert.showAndWait();
				LinkedList<String> a=new LinkedList<String>();
				if (result2.get() == ButtonType.OK)
				{
					a.add("showdb");
					setChanged();
					notifyObservers(a);
				}
				else{

					showAlert.close();
				}


			}
		} else {
			// ... user chose CANCEL or closed the dialog
			alert.close();

		}
	}

	public void show_Table_OtherWindow(List<Records> list_records) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ObservableList<RowDb> tabledata;
				Pane anchopane_table = new AnchorPane();
				// TableView<RowDb> table2 = new TableView<RowDb>();
				TableView<RowDb> table2 = new TableView<RowDb>();
				List<RowDb> list = new ArrayList<RowDb>();

				for (Records records : list_records) {
					String[] split = records.toString().split(",");
					list.add(new RowDb(split[0], split[1], split[2], split[3]));
				}
				tabledata = FXCollections.observableArrayList();/// observable
																/// list
																/// delegate
				for (RowDb rowDb : list) {
					tabledata.add(rowDb);
				}
				table.setEditable(true);
				TableColumn<RowDb, String> column1 = new TableColumn<>("NickName");
				column1.setCellValueFactory(new PropertyValueFactory<RowDb, String>("NickName"));
				TableColumn<RowDb, String> column2 = new TableColumn<>("LevelID");
				column2.setCellValueFactory(new PropertyValueFactory<RowDb, String>("LevelID"));
				TableColumn<RowDb, String> column3 = new TableColumn<>("Steps");
				column3.setCellValueFactory(new PropertyValueFactory<RowDb, String>("Steps"));
				TableColumn<RowDb, String> column4 = new TableColumn<>("Timer");
				column4.setCellValueFactory(new PropertyValueFactory<RowDb, String>("Timer"));

				table2.getColumns().addAll(column1, column2, column3, column4);
				table2.setStyle("-fx-alignment: CENTER-LEFT;");
				table2.setItems(tabledata);
				Scene scene2 = new Scene(new Group());
				anchopane_table.getChildren().add(table2);
				((Group) scene2.getRoot()).getChildren().add(anchopane_table);
				// ((Group) scene.getRoot()).getChildren().add(searchField);

				Stage table_stage = new Stage();
				table_stage.setTitle("Table");
				table_stage.setScene(scene2);
				table_stage.show();

			}
		});

	}

	public void onUpadteTableButton(List<Records> list_records) {

		VBox vbox1 = new VBox();
		Pane anchopane = new AnchorPane();
		searchField = new TextField();
		searchField.setPromptText("search levelID/Nickname");
		searchField.setMaxWidth(200);
		table = new TableView<RowDb>();
		List<RowDb> list = new ArrayList<RowDb>();
		String[] split;
		for (Records records : list_records) {
			split = records.toString().split(",");
			list.add(new RowDb(split[0], split[1], split[2], split[3]));
		}

		// updateTableButton = new Button();

		data = FXCollections.observableArrayList();/// observable list delegate
		for (RowDb rowDb : list) {
			data.add(rowDb);
		}
		table.setEditable(true);

		FilteredList<RowDb> filteredData = new FilteredList<>(data, e -> true);
		searchField.setOnKeyReleased(e -> {
			searchField.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
				filteredData.setPredicate((Predicate<? super RowDb>) rowdb -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					String lowerCaseFilter = newValue.toLowerCase();
					if (rowdb.getNickName().contains(newValue)) {
						return true;
					} else if (rowdb.getLevelID().toLowerCase().contains(lowerCaseFilter)) {
						return true;
					}

					return false;
				});
			});
			SortedList<RowDb> sorterData = new SortedList<>(filteredData);
			sorterData.comparatorProperty().bind(table.comparatorProperty());
			table.setItems(sorterData);
		});

		TableColumn<RowDb, String> column1 = new TableColumn<>("NickName");
		column1.setCellValueFactory(new PropertyValueFactory<RowDb, String>("NickName"));
		TableColumn<RowDb, String> column2 = new TableColumn<>("LevelID");
		column2.setCellValueFactory(new PropertyValueFactory<RowDb, String>("LevelID"));
		TableColumn<RowDb, String> column3 = new TableColumn<>("Steps");
		column3.setCellValueFactory(new PropertyValueFactory<RowDb, String>("Steps"));
		TableColumn<RowDb, String> column4 = new TableColumn<>("Timer");
		column4.setCellValueFactory(new PropertyValueFactory<RowDb, String>("Timer"));
		table.getColumns().addAll(column1, column2, column3, column4);
		table.setStyle("-fx-alignment: CENTER-LEFT;");
		table.setItems(data);
		// Scene scene = new Scene(new Group());
		scene = new Scene(new Group());
		Button toptime = new Button("Top 10 by time");
		Button topsteps = new Button("Top 10 by steps");
		table.setRowFactory(tv->{
			TableRow<RowDb> row=new TableRow<RowDb>();
			row.setOnMouseClicked(event->{
				if (!row.isEmpty())
				{

				RowDb rowdata=row.getItem();
				LinkedList<String> params1=new LinkedList<>();
				params1.add("searchdb");
				params1.add("Records");
				params1.add("NickName");
				params1.add(rowdata.getNickName());
				setChanged();
				notifyObservers(params1);
				}
			});
			return row;
		});


		toptime.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				LinkedList<String> a = new LinkedList<String>();
				a.add("tablewindow");
				a.add("Timer");
				a.add("Records");
				a.add("" + 10);
				setChanged();

				notifyObservers(a);

			}
		});

		topsteps.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				LinkedList<String> b = new LinkedList<String>();
				b.add("tablewindow");
				b.add("Steps");
				b.add("Records");
				b.add("" + 10);
				setChanged();
				notifyObservers(b);

			}
		});

		anchopane.setPadding(new Insets(20, 20, 20, 20));
		anchopane.getChildren().add(table);
		anchopane.getChildren().add(toptime);
		anchopane.getChildren().add(topsteps);

		anchopane.getChildren().add(searchField);
		AnchorPane.setLeftAnchor(table, 40.0);
		AnchorPane.setRightAnchor(table, 50.0);
		AnchorPane.setTopAnchor(table, 70.0);
		AnchorPane.setRightAnchor(toptime, 130.0);
		AnchorPane.setRightAnchor(topsteps, 10.0);

		((Group) scene.getRoot()).getChildren().add(anchopane);

		// ((Group) scene.getRoot()).getChildren().add(searchField);

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// Stage stage = new Stage();
				// age=new Stage();

				stage.setTitle("RECORDS");
				stage.setScene(scene);
				stage.show();

			}
		});

	}

}
