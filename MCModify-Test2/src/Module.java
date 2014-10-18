import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Module {
	static ArrayList<ArrayList<Module>> moduleLevels;
	static ArrayList<Wire> wires =  new ArrayList<Wire>();
	static ArrayList<Module> modules;
	int level;
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
	public Module(String file, int level){
		this.level = level;
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
			if(line.equals("")) continue;
			line = line.replaceAll("\t","");//Line should be free of blank space
			line = line.replaceAll(" ","");
			System.out.println("Line read: "+ line);
			if(line.substring(0,6).equals("module") && counter == 0){
				System.out.println("Creating the first module");
				int firstParen = line.indexOf("(");
				name = line.substring(6,firstParen);
				counter++;
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
				
			}
		}
		
	}
/* if -1, no wire. If not, returns # of wire*/

	public Wire findWire(String name){
		System.out.print("Going to find wire " + name + "\nCurrent wires are: ");
		for(int a = 0; a < wires.size(); a++)
			System.out.print(wires.get(a).name + ", ");
		System.out.println();
		for(int i = 0; i < wires.size(); i++)
			if(((wires.get(i)).name).equals(name)) return wires.get(i);
		return new Wire();
	}
	public Module makePrimative(String line, String gate, int type){//or	o1(w1,b,c);
		int i = line.indexOf("(");
		String mod = line.substring(gate.length(),i);
		line = line.substring(i+1);
		i = line.indexOf(",");
		String output = line.substring(0,i);
		i = line.indexOf(")");
		line = line.substring(0,i);
		ArrayList<Wire> inputs = createWires(line);
		System.out.print("Going to find wire " + output + "\nCurrent wires are: ");
		for(int a = 0; a < wires.size(); a++)
			System.out.print(wires.get(a).name + ", ");
		System.out.println();
		Wire leaving = findWire(output);
		ArrayList<Wire> coming = new ArrayList<Wire>();
		for(i=0; i< inputs.size();i++){
			coming.add(findWire((inputs.get(i)).name));
		}
		Module primative = new Module(mod,coming, leaving,this.level+1,type);
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
		System.out.print("After adding wires, current wires are: ");
		for(int a = 0; a < wires.size(); a++)
			System.out.print(wires.get(a).name + ", ");
		System.out.println();
		return newwires;
	}
	
	public ArrayList<Wire> createWires (String line){ // line = "a,b,c"
		ArrayList<Wire> nwires = new ArrayList<Wire>();
		String[] split = line.split(",");
		for(int i = 0; i<split.length;i++){
			nwires.add(new Wire(split[i]));
		}
		return nwires;
	}
	
}
