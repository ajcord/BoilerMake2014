import java.util.ArrayList;

import parsetree.Module;
import minecraft.BlockWriter;


public class Interpreter {
	
	int value;
	static ArrayList<ArrayList<Module>> moduleLevels= Module.moduleLevels;
	
	public static void main(String[] args){
		BlockWriter.loadChunk(0, 0, 0); //temp variables
		int input=1;
		Interpret(input);
		//more shit
		BlockWriter.saveChunk();
	}
	
	public static void Interpret(int input){
		//interprets moduleLevels and sends commands to build class
		//given an array list of pairs, build sandstone block with with redstone on top for all
	}
	
}
