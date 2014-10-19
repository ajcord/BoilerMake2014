package parsetree;

import java.util.ArrayList;

public class Wire {
	String name;
	Module comingFrom;
	public ArrayList<Module> goingTo;
	public ArrayList<Integer> inputNumbers;

	public Wire(String name, Module comingFrom, ArrayList<Module> goingTo) {
		super();
		this.name = name;
		this.comingFrom = comingFrom;
		this.goingTo = (ArrayList<Module>) goingTo.clone();
	}

	public Wire(String name, Module comingFrom) {
		super();
		this.name = name;
		this.comingFrom = comingFrom;
		this.goingTo = new ArrayList<Module>();
	}

	public Wire(String name) {
		super();
		this.name = name;
		this.goingTo = new ArrayList<Module>();
		comingFrom = null;
	}

	public Wire() {
		this.name = "NONE AVAILABLE";
		System.out.println("We may have a problem");
	}

}