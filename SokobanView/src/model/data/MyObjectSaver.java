package model.data;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;



public class MyObjectSaver implements Level_Saver {

	public MyObjectSaver()
	{
		
	}

	public void SaveLevel(OutputStream out, Level2D level2d) throws IOException {
		
		ObjectOutputStream oi=new ObjectOutputStream(out);
		oi.writeObject(level2d);
	}

}
