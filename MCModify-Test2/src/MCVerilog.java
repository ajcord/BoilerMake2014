import java.util.ArrayList;

import minecraft.BlockWriter;
import parsetree.*;


public class MCVerilog {

	public static void main(String[] args) {
		BlockWriter.loadChunk(0, 0, 0);
		String filepath = "sc_block.txt";
		Module head = new Module(filepath);
		ArrayList<ArrayList<Module>> levelArray = head.getLevelArray();
		Module start = levelArray.get(0).get(0); 
		//Connect to level 1
		/* Using gates pointed in the x direction */
		int x = 0;
		int y = 56;
		int z = 0;
		ArrayList<Pair> first;
		ArrayList<Pair> second;
		Pair location;
		for(int i = 1; i<levelArray.size() - 1; i++){
			for(int j = 0; j<levelArray.get(i).size(); j++){
				//Connect level n-1 to n 
				Module temp = levelArray.get(i).get(j);
				if(temp.type == 1){
					LogicGate.placeANDGateAt(x,y,z);
					location = new Pair(x+3,y,z+1);
				}
				else if(temp.type == 2){
					LogicGate.placeORGateAt(x,y,z);
					location = new Pair(x+4,y,z+1);
				}
				else if(temp.type == 3){
					LogicGate.placeNOTGateAt(x,y,z);
					location = new Pair(x+2,y,z);
				}
				else if(temp == head){
					LogicGate.placeSTARTGateAt(x,y,z,temp.output.size());
					ArrayList<Pair> locations = new ArrayList<Pair>();
					for(int k = 0; k < temp.output.size(); k++) locations.add(new Pair(x+1,y,z + 2*k)); 
				}
				z+=4;
			}
			x += 16;
		}
		//Connect to tail
		Module end = levelArray.get(levelArray.size()-1).get(0);
		BlockWriter.saveChunk();
		
	}

}
