import java.util.ArrayList;

import parsetree.Module;
import minecraft.BlockWriter;


public class Interpreter {
	
	int value;
	static ArrayList<ArrayList<Module>> moduleLevels= Module.moduleLevels;
	
	
	public static void Interpret(ArrayList<ArrayList<Pair>> input){
		//interprets moduleLevels and sends commands to build class
		//given an array list of pairs, build sandstone block with with redstone on top for all
		BlockWriter.loadChunk(0, 0, 0); //temp variables
		for(int i=0; i< input.size(); i++){
			
			for(int j=0; j< input.get(i).size(); j++){
				Pair point=input.get(i).get(j);
				BlockWriter.setBlock(point.x, point.y, point.z, point.topBlock);
			}
		}
		
		BlockWriter.saveChunk();
	}
	
}
