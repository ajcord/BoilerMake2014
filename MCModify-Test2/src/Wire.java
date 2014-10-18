import java.util.ArrayList;


public class Wire {
	String id;
	Module comingFrom;
	ArrayList<Module> goingTo;
	public Wire(String id, Module comingFrom, ArrayList<Module> goingTo) {
		super();
		this.id = id;
		this.comingFrom = comingFrom;
		this.goingTo = (ArrayList<Module>) goingTo.clone();
	}
	public Wire(String id){
		super();
		this.id = id;
	}
	
	
	
}
