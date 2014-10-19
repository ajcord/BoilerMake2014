import minecraft.BlockWriter;
import minecraft.IDs;

public class LogicGate {

	final static byte LEVER_BLOCK_BOTTOM_FACING_EAST = 0;
	final static byte LEVER_BLOCK_SIDE_FACING_EAST = 1;
	final static byte LEVER_BLOCK_SIDE_FACING_WEST = 2;
	final static byte LEVER_BLOCK_SIDE_FACING_SOUTH = 3;
	final static byte LEVER_BLOCK_SIDE_FACING_NORTH = 4;
	final static byte LEVER_BLOCK_TOP_FACING_SOUTH = 5;
	final static byte LEVER_BLOCK_TOP_FACING_EAST = 6;
	final static byte LEVER_BLOCK_BOTTOM_FACING_SOUTH = 7;
	final static byte LEVER_ACTIVE = 8;
	final static byte REDSTONE_TORCH_ON_GROUND = 0;
	final static byte REDSTONE_TORCH_ON_BLOCK_FACING_EAST = 1;
	final static byte REDSTONE_TORCH_ON_BLOCK_FACING_WEST = 2;
	final static byte REDSTONE_TORCH_ON_BLOCK_FACING_SOUTH = 3;
	final static byte REDSTONE_TORCH_ON_BLOCK_FACING_NORTH = 4;
	final static byte REDSTONE_TORCH_ON_BLOCK_FACING_UP = 5;

	public LogicGate() {
		System.out.println("Logic gate made.");
	}

	public static void placeANDGateAt(int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0)
			;
		else {
			BlockWriter.setBlock(x + 1, y, z + 1, IDs.Sandstone);
			BlockWriter.setBlock(x + 1, y, z + 2, IDs.Sandstone);
			BlockWriter.setBlock(x + 1, y, z + 3, IDs.Sandstone);
			BlockWriter.setBlock(x + 1, y, z, IDs.Sandstone);
			BlockWriter.setBlock(x, y, z, IDs.RedstoneWire);// Redstone wire on
															// West side
			BlockWriter.setBlock(x, y, z + 3, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 1, y + 1, z + 1, IDs.RedstoneWire);// wire
																		// on
																		// stones
			BlockWriter.setBlock(x + 1, y + 1, z + 2, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 1, y + 1, z + 3, IDs.RedstoneTorchOff); // REDSTONE_TORCH_ON_Block
			BlockWriter.setBlock(x + 1, y + 1, z, IDs.RedstoneTorchOff); // REDSTONE_TORCH_ON_Block

			BlockWriter.setBlock(x + 2, y, z + 1, IDs.RedstoneTorchOff,
					REDSTONE_TORCH_ON_BLOCK_FACING_EAST);
			BlockWriter.setBlock(x + 2, y, z, IDs.RedstoneWire);// Redstone
																	// wire on
																	// East,
																	// output
			BlockWriter.setBlock(x + 3, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 4, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 5, y, z, IDs.RedstoneWire);
		}
		System.out.println("AND Input loc: " + x + "," + y + "," + z);
		System.out.println("AND Input loc: " + x + "," + y + "," + (z + 2));
		System.out.println("AND Output loc: " + (x + 5) + "," + y + ","
				+ (z + 1));

	}

	public static void placeORGateAt(int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0)
			;
		else {
			BlockWriter.setBlock(x + 2, y, z + 1, IDs.RedstoneWire); // connecting
																		// wire
			BlockWriter.setBlock(x + 2, y, z + 2, IDs.RedstoneWire);

			BlockWriter.setBlock(x + 5, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 4, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 3, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 2, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 1, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 1, y, z + 3, IDs.RedstoneWire);
			BlockWriter.setBlock(x, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x, y, z + 3, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 2, y, z + 3, IDs.RedstoneWire);
		}
		System.out.println("OR Input loc: " + x + "," + y + "," + z);
		System.out.println("OR Input loc: " + x + "," + y + "," + (z + 3));
		System.out.println("OR Output loc: " + (x + 5) + "," + y + "," + z);

	}

	public static void placeNOTGateAt(int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0)
			;
		else {
			BlockWriter.setBlock(x + 1, y, z, IDs.Sandstone);
			BlockWriter.setBlock(x, y, z, IDs.RedstoneWire); // input wire
			BlockWriter.setBlock(x + 2, y, z, IDs.RedstoneTorchOff,
					REDSTONE_TORCH_ON_BLOCK_FACING_EAST);
			BlockWriter.setBlock(x + 3, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 4, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x + 5, y, z, IDs.RedstoneWire);
		}
		System.out.println("NOT Input loc: " + x + "," + y + "," + z);
		System.out.println("NOT Output loc: " + (x + 5) + "," + y + "," + z);

	}

	public static void placeSTARTGateAt(int x, int y, int z, int inputs) {
		if (x < 0 || y < 0 || z < 0 || inputs < 1)
			;
		else {
			for (int i = 0; i < inputs; i++) {
				BlockWriter.setBlock(x, y, (z + 3 * i), IDs.RedstoneTorch);
				BlockWriter.setBlock(x + 1, y, (z + 3 * i), IDs.RedstoneWire);
				BlockWriter.setBlock(x + 2, y, (z + 3 * i), IDs.RedstoneWire);
				BlockWriter.setBlock(x + 3, y, (z + 3 * i), IDs.RedstoneWire);
				BlockWriter.setBlock(x + 4, y, (z + 3 * i), IDs.RedstoneWire);
				BlockWriter.setBlock(x + 5, y, (z + 3 * i), IDs.RedstoneWire);
			}
			/* Inputs at x+1,y,z+2*2i */
		}
	}

	public static void placeENDGateAt(int x, int y, int z, int outputs) {
		if (x < 0 || y < 0 || z < 0 || outputs < 1)
			;
		else {
			for (int i = 0; i < outputs; i++) {
				BlockWriter.setBlock(x + 1, y, (z + 3 * i), IDs.RedstoneLamp);
				BlockWriter.setBlock(x, y, (z + 3 * i), IDs.RedstoneWire);
			}
			/* Inputs at x+1,y,z+2*2i */
		}
	}
}
