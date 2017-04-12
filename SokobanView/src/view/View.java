package view;

import java.util.List;

import db.Records;
import model.data.Level;
import model.data.Level2D;

public interface View{
	//void display(Level level);
	//void displayError(String msg);
	void start();
	void setCurrentLevel(Level2D level);
	void display(Level2D level);
	public void startTimer();
	public void stopTimer();
	public void addStep();
	public void winLevel();
	public void onUpadteTableButton(List<Records> list_records);
	public void show_Table_OtherWindow(List<Records> list_records);
}
