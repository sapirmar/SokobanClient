package view;

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
}
