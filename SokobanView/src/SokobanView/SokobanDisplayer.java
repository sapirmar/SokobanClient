package SokobanView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.data.Level2D;

public class SokobanDisplayer extends Canvas {

	private Level2D level;

	//private int row;
	//private int col;
	private StringProperty wallFilename;
	private StringProperty actorFilename;
	private StringProperty destinationFilename;
	private StringProperty boxFilename;


	public SokobanDisplayer() {
		wallFilename=new SimpleStringProperty();
		actorFilename=new SimpleStringProperty();
		destinationFilename=new SimpleStringProperty();
		boxFilename=new SimpleStringProperty();

		//maybe we need the charcter position
	}
	public void reDraw()
	{

		if(level.getWarehouse()!=null)
		{
			double W=getWidth();
			double H=getHeight();
			double w=W/level.getWarehouse()[0].length;
			double h=H/level.getWarehouse().length;
			Image wall=null;
			Image actor=null;
			Image destinationbox=null;
			Image box=null;
			try {

				wall=new Image(new FileInputStream(wallFilename.get()));
				actor=new Image(new FileInputStream(actorFilename.get()));
				destinationbox= new Image(new FileInputStream(destinationFilename.get()));
				box=new Image(new FileInputStream(boxFilename.get()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			GraphicsContext gc=getGraphicsContext2D();
			gc.clearRect(0, 0, W, H);

			for(int i=0;i<level.warehouse.length;i++)
			{
				for(int j=0;j<level.warehouse[i].length;j++)
				{
					switch(level.warehouse[i][j].getChar()){
						case '@':
							gc.drawImage(box, j*w, i*h, w, h);
							break;
						case'#':
							gc.drawImage(wall, j*w, i*h, w, h);
							break;
						case 'A':
							gc.drawImage(actor, j*w, i*h, w, h);
							break;
						case 'o':
							gc.drawImage(destinationbox, j*w, i*h, w, h);
							break;
						default:
							gc.setFill(Color.WHITE);
							gc.fillRect(j*w, i*h, w, h);
							break;
					}

				}
			}


		}
	}
	public Level2D getLevel() {
		return level;
	}
	public void setLevel(Level2D level) {

		this.level = level;
		reDraw();

	}
	/*public char[][] getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(char[][] warehouse) {
		this.warehouse = warehouse;
	     reDraw();
	}*/
	public String getWallFilename() {
		return wallFilename.get();
	}
	public void setWallFilename(String wallFilename) {
		this.wallFilename.set(wallFilename);
	}
	public String getActorFilename() {
		return actorFilename.get();
	}
	public void setActorFilename(String actorFilename) {
		this.actorFilename.set(actorFilename);
	}
	public String getDestinationFilename() {
		return destinationFilename.get();
	}
	public void setDestinationFilename(String destinationFilename) {
		this.destinationFilename.set(destinationFilename);
	}
	public String getBoxFilename() {
		return boxFilename.get();
	}
	public void setBoxFilename(String boxFilename) {
		this.boxFilename.set(boxFilename);
	}
}