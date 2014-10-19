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
		
		int x=0, y=56, z= 3;
		LogicGate.placeANDGateAt(x, y, z);
		
		BlockWriter.saveChunk();
			
	}
}
