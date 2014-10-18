import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Module {
	static ArrayList<ArrayList<Module>> moduleLevels;
	//static ArrayList<Wire> allWires;
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
			else if(line.substring(0,4).equals("wire")){
				line = line.substring(4,line.length());
				int i = line.indexOf(",");
				while(i != -1){
					String wire = line.substring(0,i);
					wires.add(new Wire(wire));
					line = line.substring(i,line.length());
					i = line.indexOf(",");
				}
			}
			else if(line.substring(0,3).equals("and")){
				int i = line.indexOf("(");
				String mod = line.substring(3,i);
				int j = line.indexOf(",");
				String outputname = line.substring(i+1,j);
				i = line.indexOf(",");
			}
			else if(line.substring(0,2).equals("or")){
				
			}
			else if(line.substring(0,3).equals("not")){
				
			}
			else if(line.substring(0,5).equals("input")){
				
			}
			else if(line.substring(0,6).equals("output")){
				
			}
			else if(line.substring(0,4).equals("wire")){
				
			}
			else if(line.equals("endmodule")){
				
			}
			else{
				/*non primitive module */
			}
		}
		modules = new Module[10];
		wires = new Wire[20];
		
	}
/* if -1, no wire. If not, returns # of wire*/

	public int findWire(String name){
		for(int i = 0; i < allWires.size(); i++)
			if(((allWires.get(i)).id).equals(name)) return i;
		return -1;
	}
}
