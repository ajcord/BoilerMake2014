import minecraft.Chunk;
import minecraft.CompressionScheme;
import minecraft.IDs;
import minecraft.Inventory;
import minecraft.Level;
import nbt.Tag;
import test.TestingUtils;
import static minecraft.IDs.WrittenBook;
import minecraft.BlockWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MCModifyTest
{
	final static byte LEVER_BLOCK_BOTTOM_FACING_EAST=0;
	final static byte LEVER_BLOCK_SIDE_FACING_EAST=1;
	final static byte LEVER_BLOCK_SIDE_FACING_WEST=2;
	final static byte LEVER_BLOCK_SIDE_FACING_SOUTH=3;
	final static byte LEVER_BLOCK_SIDE_FACING_NORTH=4;
	final static byte LEVER_BLOCK_TOP_FACING_SOUTH=5;
	final static byte LEVER_BLOCK_TOP_FACING_EAST=6;
	final static byte LEVER_BLOCK_BOTTOM_FACING_SOUTH=7;
	final static byte LEVER_ACTIVE=8;
	
	public static void main(String[] args) throws Throwable
	{
		
		BlockWriter.loadChunk(0, 0, 0);
<<<<<<< HEAD
		/*
		for (int x = 1; x < 8; x += 2) {
			
			BlockWriter.setBlock(x, 56, 0, IDs.Lever, LEVER_BLOCK_TOP_FACING_SOUTH);
			for (int z = 1; z < 5; z++) {
				BlockWriter.setBlock(x, 56, z, IDs.RedstoneWire);
				if (z == 3) {
					BlockWriter.setBlock(3, 56, z, IDs.Sandstone);
					BlockWriter.setBlock(5, 56, z, IDs.Sandstone);
					BlockWriter.setBlock(7, 56, z, IDs.Sandstone);
					BlockWriter.setBlock(3, 57, z, IDs.RedstoneWire);
					BlockWriter.setBlock(5, 57, z, IDs.RedstoneWire);
					BlockWriter.setBlock(7, 57, z, IDs.RedstoneWire);
				}
				if (z == 4) {
					BlockWriter.setBlock(3, 57, z, IDs.Sandstone);
					BlockWriter.setBlock(5, 57, z, IDs.Sandstone);
					BlockWriter.setBlock(7, 57, z, IDs.Sandstone);
					BlockWriter.setBlock(3, 58, z, IDs.RedstoneWire);
					BlockWriter.setBlock(5, 58, z, IDs.RedstoneWire);
					BlockWriter.setBlock(7, 58, z, IDs.RedstoneWire);
				}
			}
		}
		*/
		int x= 6; int y=56; int z= 5;
		
		//logicGate.placeNOTGateAt(x,y,z);
		
		
		x=8; y=56; z= 5;
		logicGate.placeANDGateAt(x, y, z);
		
		x=11; y=56; z= 5;
		//logicGate.placeORGateAt(x,y,z);
		
=======
>>>>>>> FETCH_HEAD
		
		int x;int y=56; int z= 3;
		for (x=0; x< 6; x++){
			BlockWriter.setBlock(x, y, z, IDs.RedstoneRepeater, (byte) 1); //1 indicates repeater facing east
			BlockWriter.setBlock(x, y, z+1, IDs.RedstoneRepeater, (byte) 1);
			BlockWriter.setBlock(x, y, z+2, IDs.RedstoneRepeater, (byte) 1);
			BlockWriter.setBlock(x, y, z+3, IDs.RedstoneRepeater, (byte) 1);
		}
		
		BlockWriter.saveChunk();
			
	}
}
