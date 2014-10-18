import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Module {
	static ArrayList<ArrayList<Module>> moduleLevels;
	static ArrayList<Wire> allWires;
	int level;
	int type =-1; // 1 is AND, 2 is OR, 3 is NOT
	//int width; // Amount of levels it takes up (1 for primitives, more for complex)
	String name;
	ArrayList<Wire> output;
	ArrayList<Wire> input;
	ArrayList<Wire>	wires;
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
			line.replaceAll("\\s+","");//Line should be free of blank space
			if(line.substring(0,6).equals("module") && counter == 0){
				int firstParen = line.indexOf("(");
				name = line.substring(7,firstParen);
				Wire tempInput[];
				Wire tempOutput[];
				counter++;
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
				this.wires = buildArray(line,"input");
			}
			else if(line.substring(0,6).equals("output")){
				this.wires = buildArray(line,"output");
			}
			else if(line.substring(0,4).equals("wire")){
				this.wires = buildArray(line,"wire");
			}
			else if(line.equals("endmodule")){
				break;
			}
			else{
				/*non primitive module */
			}
		}
		modules = new Module[10];
		wires = new Wire[20];
		
	}
/* if -1, no wire. If not, returns # of wire*/

	public Wire findWire(String name){
		for(int i = 0; i < allWires.size(); i++)
			if(((allWires.get(i)).name).equals(name)) return allWires.get(i);
		return new Wire();
	}
	public void makePrimative(String line, String gate, int type){
		int i = line.indexOf("(");
		String mod = line.substring(gate.length(),i);
		line = line.substring(gate.length(),i);
		i = line.indexOf(",");
		String output = line.substring(0,i);
		i = line.indexOf(")");
		line = line.substring(0,i);
		ArrayList<Wire> inputs = buildArray(line,"");
		Wire leaving = findWire(output);
		ArrayList<Wire> coming = new ArrayList<Wire>();
		for(i=0; i< inputs.size();i++){
			coming.add(findWire((inputs.get(i)).name));
		}
		Module primative = new Module(mod,coming, leaving,this.level+1,type);
		leaving.comingFrom = primative;
	}
	public ArrayList<Wire> buildArray(String line, String deliminator){
		line = line.substring(deliminator.length(),line.length());
		int i = line.indexOf(",");
		ArrayList<Wire> wires = new ArrayList<Wire>();
		while(i != -1){
			String wire = line.substring(0,i);
			wires.add(new Wire(wire));
			line = line.substring(i,line.length());
			i = line.indexOf(",");
		}
		return wires;
	}
	
}
