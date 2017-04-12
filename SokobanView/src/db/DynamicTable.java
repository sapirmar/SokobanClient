package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Query;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DynamicTable extends Application {
	// TABLE VIEW AND DATA
	private ObservableList<ObservableList> data;
	private TableView tableview;
	private SessionFactory factory;
	public static void main(String[] args) {
		launch(args);
	}

	// CONNECTION DATABASE
	public void buildData() {
		  Connection conn = null;
		   Statement stmt = null;
		   try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Connecting to database...");
			conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433");
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql=("SELECT * FROM Records");
			ResultSet rs = stmt.executeQuery(sql);

			/*while(rs.next()){
				String nickname=rs.getString("NickName");
				String levelid=rs.getString("LevelID");
				int steps=rs.getInt("Steps");
				int timer=rs.getInt("Timer");
			}
			*/
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int j=i;
				TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i+1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());

					}

				});

				tableview.getColumns().addAll(col);
				System.out.println("Column ["+i+"]");

			}
			while(rs.next())
			{
				ObservableList<String> row=FXCollections.observableArrayList();
				for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));

				}
				System.out.println("row 1 adden"+ row);
				data.add(row);
			}
			tableview.setItems(data);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





	}

	@Override
	public void start(Stage stage) throws Exception {
		tableview=new TableView();
		buildData();
		Scene scene=new Scene(tableview);
		stage.setScene(scene);
		stage.show();


	}

}
