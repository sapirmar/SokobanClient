package controller.command;

import java.util.LinkedList;
import java.util.List;

public abstract class Command implements ICommand{
	protected List<String> params;
	 public void setParams(LinkedList<String> params){
		 this.params= new LinkedList<String>();
		 for (String s : params) {
			 this.params.add(s);
		 }
		// this.params=params;

		//}
	 }
	public abstract void execute();

}
