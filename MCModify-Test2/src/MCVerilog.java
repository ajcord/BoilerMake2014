import java.util.ArrayList;

import minecraft.BlockWriter;
import minecraft.IDs;
import parsetree.*;

public class MCVerilog {

	public static void main(String[] args) {
		String filepath = "sc_block.txt";
		Module head = new Module(filepath);
		ArrayList<ArrayList<Module>> levelArray = head.getLevelArray();
		Module start = levelArray.get(0).get(0);
		// Connect to level 1
		/* Using gates pointed in the x direction */
		int x = 0;
		int y = 56;
		int z = 0;
		for (int i = 0; i < levelArray.size() - 1; i++) {
			ArrayList<Pair> first = new ArrayList<Pair>();
			ArrayList<Pair> second = new ArrayList<Pair>();
			for (int j = 0; j < levelArray.get(i).size(); j++) {
				ArrayList<Pair> locations = new ArrayList<Pair>();
				BlockWriter.loadChunk(x, y, z);
				// Connect level n-1 to n
				Module currentModule = levelArray.get(i).get(j);
				if (currentModule.type == 1) {
					LogicGate.placeANDGateAt(x, y, z);
					locations.add(new Pair(x + 5, y, z));
					z += 6;
				} else if (currentModule.type == 2) {
					LogicGate.placeORGateAt(x, y, z);
					locations.add(new Pair(x + 5, y, z));
					z += 6;
				} else if (currentModule.type == 3) {
					LogicGate.placeNOTGateAt(x, y, z);
					locations.add(new Pair(x + 5, y, z));
					z += 3;
				} else if (currentModule == head) {
					LogicGate.placeSTARTGateAt(x, y, z, currentModule.output.size());
					for (int k = 0; k < currentModule.output.size(); k++)
						locations.add(new Pair(x + 5, y, z + 3 * k));
				}
				BlockWriter.saveChunk();
				
				for(int k = 0; k < currentModule.output.size(); k++) {
					Wire currentWire = currentModule.output.get(k);
					int dz = 0;
					for (int inputIndex = 0; inputIndex < currentWire.goingTo.size(); inputIndex++) {
						Module targetModule = currentWire.goingTo.get(inputIndex);
						first.addAll(locations);
						dz = getOffset(levelArray.get(i+1), targetModule.name);
						for (int locIndex = 0; locIndex < locations.size(); locIndex++) {
							Pair location = locations.get(locIndex);
							if (BlockWriter.getBlock(location.x + 10, location.y, dz) != IDs.Air) {
								//Something is already using this input. Use the next input.
								dz += 3;
							}
							second.add(new Pair(location.x + 10, location.y, dz));
						}
					}
				}
				ArrayList<ArrayList<Pair>> results = Pair.crossingProblem(first, second);
				Interpreter.Interpret(results);
			}
			x += 16;
			z = 0;
		}
		// Connect to tail
		Module end = levelArray.get(levelArray.size() - 1).get(0);

	}
	
	public static int getOffset(ArrayList<Module> list, String name ){
		int t = 0;
		for(int i = 0; i < list.size(); i++){
			String check = list.get(i).name;
			if(check.equals(name)) return t;
			if(list.get(i).type == 1 || list.get(i).type == 2) t += 6;
			else t += 3;
		}
		return t;
		
	}

}
