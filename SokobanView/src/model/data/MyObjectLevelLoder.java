package model.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;



public class MyObjectLevelLoder implements LevelLoader {

	public Level load_level(InputStream in){
		Level new_level=new Level();
		try{
		ObjectInputStream oi=new ObjectInputStream(in);
		new_level=(Level)oi.readObject();
		oi.close();
		return new_level;
		
	}catch(IOException e)
	{
		System.out.println("can not load the level");
		e.printStackTrace();
		
	}
	catch (ClassNotFoundException e)
		{
		System.out.println("can not load the level");
			e.printStackTrace();
		}
		return null;
	}


	

	

		
		
	}


