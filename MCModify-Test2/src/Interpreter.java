import java.util.ArrayList;

import parsetree.Module;
import minecraft.BlockWriter;
import minecraft.IDs;

public class Interpreter {

	public static void Interpret(ArrayList<ArrayList<Pair>> input) {
		// interprets moduleLevels and sends commands to build class
		// given an array list of pairs, build sandstone block with with
		// redstone on top for all
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.get(i).size(); j++) {
				Pair point = input.get(i).get(j);
				BlockWriter.loadChunk(point.x, point.y, point.z);
				BlockWriter.setBlock(point.x, point.y, point.z, IDs.Sandstone);
				if (point.topBlock == IDs.RedstoneRepeater
						|| point.topBlock == IDs.RedstoneRepeaterOff) {
					BlockWriter.setBlock(point.x, point.y + 1, point.z,
							IDs.RedstoneRepeaterOff, (byte) 1);
				}// byte 1 implies repeater facing east
				else {
					BlockWriter.setBlock(point.x, point.y + 1, point.z,
							point.topBlock);
				}
				BlockWriter.saveChunk();
			}
		}

	}

}
