package db;

import javafx.beans.property.SimpleStringProperty;

public class RowDb {
	private SimpleStringProperty NickName;
	private SimpleStringProperty LevelID;
	private SimpleStringProperty Steps;
	private SimpleStringProperty Timer;
	public String getNickName() {
		return NickName.get();
	}
	public void setNickName(String nickName) {
		this.NickName=new SimpleStringProperty(nickName);
	}
	public String getLevelID() {
		return LevelID.get();
	}
	public void setLevelID(String levelID) {
		this.LevelID = new SimpleStringProperty(levelID);
	}
	public String getSteps() {
		return Steps.get();
	}
	public void setSteps(String steps) {
		this.Steps=new SimpleStringProperty(steps);
	}
	public String getTimer() {
		return Timer.get();
	}
	public void setTimer(String timer) {
		this.Timer = new SimpleStringProperty(timer);
	}
	public RowDb() {
		// TODO Auto-generated constructor stub
	}
	public RowDb(String nickName, String levelID, String steps,
			String timer) {
		this.NickName=new SimpleStringProperty();
		this.LevelID=new SimpleStringProperty();
		this.Steps=new SimpleStringProperty();
		this.Timer=new SimpleStringProperty();

		NickName.set(nickName);
		LevelID .set(levelID);
		Steps.set(steps);
		Timer.set(timer);
	}

}
