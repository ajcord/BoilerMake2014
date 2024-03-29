import java.util.ArrayList;

import parsetree.Module;
import minecraft.BlockWriter;
import minecraft.IDs;

public class Interpreter {

	public static void Interpret(ArrayList<ArrayList<Pair>> input) {
		// interprets moduleLevels and sends commands to build class
		System.out.println(input.size());
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.get(i).size(); j++) {
				Pair point = input.get(i).get(j);
				BlockWriter.loadChunk(point.x, point.y, point.z);
				BlockWriter.setBlock(point.x, point.y, point.z, IDs.Sandstone);
				if (point.topBlock == IDs.RedstoneRepeater) {
					BlockWriter.setBlock(point.x, point.y + 1, point.z,
							IDs.RedstoneRepeaterOff, (byte) 0);
				}// byte 0 implies repeater facing north
				else if(point.topBlock == IDs.RedstoneRepeaterOff){
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
