import java.util.ArrayList;

import minecraft.BlockWriter;
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
		ArrayList<Pair> first = new ArrayList<Pair>();
		ArrayList<Pair> second = new ArrayList<Pair>();
		Pair location = null;
		for (int i = 0; i < levelArray.size() - 1; i++) {
			for (int j = 0; j < levelArray.get(i).size(); j++) {
				BlockWriter.loadChunk(x, y, z);
				// Connect level n-1 to n
				Module temp = levelArray.get(i).get(j);
				if (temp.type == 1) {
					LogicGate.placeANDGateAt(x, y, z);
					location = new Pair(x + 5, y, z);
					z += 6;
				} else if (temp.type == 2) {
					LogicGate.placeORGateAt(x, y, z);
					location = new Pair(x + 5, y, z);
					z += 6;
				} else if (temp.type == 3) {
					LogicGate.placeNOTGateAt(x, y, z);
					location = new Pair(x + 5, y, z);
					z += 3;
				} else if (temp == head) {
					LogicGate.placeSTARTGateAt(x, y, z, temp.output.size());
					ArrayList<Pair> locations = new ArrayList<Pair>();
					for (int k = 0; k < temp.output.size(); k++)
						locations.add(new Pair(x + 5, y, z + 3 * k));
					z += 6;
				}
				for(int k = 0; k < temp.output.size(); k++){
					for (int k_input = 0; k_input < temp.output.get(k).goingTo.size(); k++) {
						ArrayList<Wire> connect = temp.output.get(k).goingTo;
					}
				}
				first.add(location);
				BlockWriter.saveChunk();
			}
			x += 16;
			z = 0;
		}
		// Connect to tail
		Module end = levelArray.get(levelArray.size() - 1).get(0);

	}

}
