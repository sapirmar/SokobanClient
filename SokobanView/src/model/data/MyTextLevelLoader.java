package model.data;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.items.Destination_Box;
import model.items.Items;

public class MyTextLevelLoader implements LevelLoader {
	private int max_line;
	private int count_line;
	public MyTextLevelLoader() {
		max_line=0;
		count_line=0;
	}
	public int getMax_line() {
		return max_line;
	}

	public void setMax_line(int max_line) {
		this.max_line = max_line;
	}

	public int getCount_line() {
		return count_line;
	}

	public void setCount_line(int count_line) {
		this.count_line = count_line;
	}
	
	public Level load_level(InputStream in) {
		String line;
		Level2D new_level=null;
		ArrayList<String> lines= new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		try{
		
			
			while((line=reader.readLine())!=null)
			{
				lines.add(line);
				if(line.length()>max_line)
					max_line=line.length();
				
			}
			count_line=lines.size();
			
			
		
		reader.close();
		
		Items item;
		new_level=new Level2D(count_line, max_line);
		char[][] char_array=new char[count_line][max_line];
		TextItemCreator creator=new TextItemCreator();
		AddArrayListGenarator alg=new AddArrayListGenarator();
		for(int i=0;i<count_line;i++)
		{
		char_array[i]=lines.get(i).toCharArray();	
		}
		for(int i=0;i<count_line;i++)
		{
			for(int j=0;j<max_line;j++){
				
				item=creator.hm.get(new Character(char_array[i][j])).create();
				
				item.p=new Position(i, j);
				if(item.getClass().equals(Destination_Box.class))
				{
					item.getP().setFlagDestination(true);
				}
				
				new_level.warehouse[i][j]=item;
				//update the Array lists.
				alg.getHm().get((char_array)[i][j]).addToArrayList(new_level, item);
				new_level.getItems().add(item);
				
			}
		}
		
		
		
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return new_level;	
		
	}
	
	
	

	
	
	
	
	
}