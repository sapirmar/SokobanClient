package controller.command;

import java.util.HashMap;
import java.util.LinkedList;

import model.Model;
import model.data.Level;
import model.data.Level2D;



public class SaveCommand extends Command {
         private Model model;
		  private String str;
		// private String type;
		//  HashMap<String,IlevelSaverCreator> hm;
	     public SaveCommand (Model model, LinkedList<String> params) {
		this.model=model;
		this.params=params;
		if(this.params!=null)
		str=params.get(0); // the name of file
		//System.out.println(str);
		  //save according to the the type
		  //hm=new HashMap<String,IlevelSaverCreator>();
		  //hm.put("txt",new TextLevelSaverCreator());
		  //hm.put("xml", new XmlSaverCreator() );
		//  hm.put("obj", new ObjectLevelSaverCreator());
		  }
		 
		public void execute() {		
			//this.type=str.substring(str.length()-3);
			//System.out.println(str);
			//hm.get(type).create((Level2D)model.getCurrentLevel(),str);
			str=params.remove(0);
			model.save_level(model.getCurrentLevel(),str);
			
		}
	
			public Level getLevel() {
				return model.getCurrentLevel();
			}
			public void setLevel(Level2D level) {
			 
			}
			public String getFilename() {
				return str;
			}
			public void setFilename(String filename) {
				this.str = new String(filename);
			}
		
		

}
