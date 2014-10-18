import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Module {
	static ArrayList<ArrayList<Module>> moduleLevels;
	static ArrayList<Wire> wires =  new ArrayList<Wire>();
	static ArrayList<Module> modules = new ArrayList<Module>();
	static ArrayList<Module> heads = new ArrayList<Module>();
	static boolean setHead = false;
	static Module head;
	int level=-1;
	int type =-1; // 1 is AND, 2 is OR, 3 is NOT
	//int width; // Amount of levels it takes up (1 for primitives, more for complex)
	String name;
	ArrayList<Wire> output;
	ArrayList<Wire> input;
	public Module(String name,ArrayList<Wire> input, Wire output,int level,int type){//USED IF PRIMITATIVE TYPE
		this.level = level; 
		this.name = name;
		this.output = new ArrayList<Wire>();
		(this.output).add(output);
		this.input = (ArrayList<Wire>) input.clone();
		this.type = type; 
	}
	public Module(String file){
		int counter = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = "Great Scott";
		while((line != null)){
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String linetemp = line;
			if(line.equals("")) continue;
			line = line.replaceAll("\t","");//Line should be free of blank space
			line = line.replaceAll(" ","");
			System.out.println("Line read: "+ line);
			if(line.substring(0,6).equals("module") && counter == 0){
				System.out.println("Creating the first module");
				int firstParen = line.indexOf("(");
				name = line.substring(6,firstParen);
				level = 0;
				counter++;
				if(!setHead) head = this;
				setHead = true;
				//modules.add(this);
			}
			else if(line.substring(0,3).equals("and")){
				makePrimative(line,"and",1);
			}
			else if(line.substring(0,2).equals("or")){
				makePrimative(line,"or",2);
			}
			else if(line.substring(0,3).equals("not")){
				makePrimative(line,"not",3);
			}
			else if(line.substring(0,5).equals("input")){
				this.output = globalWires(line,"input");
			}
			else if(line.substring(0,6).equals("output")){
				this.input = globalWires(line,"output");
			}
			else if(line.substring(0,4).equals("wire")){
				globalWires(line,"wire");
			}
			else if(line.equals("endmodule")){
				break;
			}
			else{
				/*non primitive module */
				linetemp = linetemp.replaceAll("\t", "");
				int index = linetemp.indexOf(" ");//NOTE: TABS MAY CAUSE ERRORS HERE!!
				String filename = linetemp.substring(0,index);
				filename = filename.replaceAll(" ","");
				filename = filename + ".txt";
				String newFile = (filename);
				Module newMod = new Module(newFile);
				//if(isHead(newMod)) heads.add(newMod);
				newMod.name = line.substring(index, line.indexOf("("));
				linetemp = linetemp.substring(index + (newMod.name.length()), line.indexOf(","));
				modules.add(newMod);
			}
		}
	/*	ArrayList<Module> kiddies;
		for(int i = 0; i < modules.size(); i++){
			Module temp = modules.get(i);
			kiddies = temp.getChildren();
			for(int j = 0; j < kiddies.size(); j++){
				if(kiddies.get(j).level <= level){
					kiddies.get(j).level = level + 1;
				}
			}
			
		}*/
		recurseLongest(head);
	}
	public Wire findWire(String name){
		System.out.println();
		for(int i = 0; i < wires.size(); i++)
			if(((wires.get(i)).name).equals(name)) return wires.get(i);
		return new Wire();
	}
	public Module makePrimative(String line, String gate, int type){//or	o1(w1,b,c);
		int i = line.indexOf("(");
		String mod = line.substring(gate.length(),i);
		line = line.substring(i+1);
		int k = line.indexOf(",");
		String output = line.substring(0,k);
		i = line.indexOf(")");
		line = line.substring(k+1,i);
		ArrayList<Wire> inputs = createWires(line);
		Wire leaving = findWire(output);
		ArrayList<Wire> coming = new ArrayList<Wire>();
		for(i=0; i< inputs.size();i++){
			coming.add(findWire((inputs.get(i)).name));
		}
		Module primative = new Module(mod,coming, leaving,this.level+1,type);
		//if(isHead(primative)) heads.add(primative);
		modules.add(primative);
		for(i = 0; i < inputs.size(); i++){
			(((primative.input).get(i)).goingTo).add(primative);
		}
		leaving.comingFrom = primative;
		return primative;
	}
	public ArrayList<Wire> globalWires(String line, String deliminator){// output	a,d;
		line = line.substring(deliminator.length());
		if(line.indexOf(";") !=-1) line = line.substring(0,line.indexOf(";"));
		ArrayList<Wire> newwires = createWires(line);
		for(int i = 0; i < newwires.size(); i++){
			wires.add(newwires.get(i));
		}
		return newwires;
	}
	
	public ArrayList<Wire> createWires (String line){ // line = "a,b,c"
		ArrayList<Wire> nwires = new ArrayList<Wire>();
		String[] split = line.split(",");
		for(int i = 0; i<split.length;i++){
			nwires.add(new Wire(split[i]));
		}
		return nwires;
	}/*
	public boolean isHead(Module mod){
		boolean isHead = true;
		ArrayList<Wire> inputs = mod.input;
		for(Wire w : inputs){
			if(getParent(w)!=null){
				return false;
			}
		}
		return isHead;
	}*/
	public Module getParent(Wire wire){
		return wire.comingFrom;
	}
	
	public ArrayList<Module> getChildren(){
		if(this == null || output.size() == 0) return null;
		ArrayList<Module> temp = new ArrayList<Module>();
		for(int i = 0; i < output.size(); i++){
			Wire outward = (output.get(i));
			for(int j = 0; j < outward.goingTo.size(); j++){
				temp.add(outward.goingTo.get(j));
			}
		}
		return temp;
	}
	
	public void recurseLongest(Module node){
		if(node == null) return;
		if(node == head){//HOW TO TELL THAT IT IS THE HEAD??
			node.level = 0;
		}
		else{
			ArrayList<Integer> parentlevel = new ArrayList<Integer>();
			for(int i = 0; i < input.size();i++){
				parentlevel.add(getParent(input.get(i)).level);
			}
			Integer plevel = Collections.max(parentlevel);
			node.level = plevel + 1;
		}
		ArrayList<Module> kids = node.getChildren();
		if(kids == null) return;
		for(int i = 0; i < kids.size();i++){
			recurseLongest(kids.get(i));
		}
		return;
	}
	
}
