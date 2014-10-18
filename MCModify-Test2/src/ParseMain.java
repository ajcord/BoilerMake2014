import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ParseMain {
	static Module headModule;
	ArrayList<Module> modules;
	ArrayList<Wire> wires;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = args[0];
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
				System.out.printf("Writing head module");
				int firstParen = line.indexOf("(");
				String name = line.substring(7,firstParen);
				Wire tempInput[];
				Wire tempOutput[];
				headModule = new Module(name, tempInput, tempOutput);
				counter++;
			}
			if(line.substring(0,3).equals("and") && counter == 0){
				
			}
		}
		modules = new Module[10];
		wires = new Wire[20];
		
	}
	//Returns -1 if not found
	public int findWire(String a){
		for(int i = 0; i < wires.length; i++){
			if((wires[i].name).equals(a))
				return i;
		}
		return -1;
	}

}
