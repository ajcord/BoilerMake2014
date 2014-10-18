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
	public Wire(String id, Module comingFrom){
		super();
		this.id = id;
		this.comingFrom = comingFrom;
	}
	public Wire(String id){
		super();
		this.id = id;
	}
	public Wire(){
		System.out.println("We may have a problem");
	}
	
	
}
