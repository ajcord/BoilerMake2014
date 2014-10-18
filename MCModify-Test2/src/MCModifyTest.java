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
	final static byte LEVER_BLOCK_TOP_FACING_EAST=5;
	final static byte LEVER_BLOCK_TOP_FACING_SOUTH=6;
	final static byte LEVER_BLOCK_BOTTOM_FACING_SOUTH=7;
	final static byte LEVER_ACTIVE=8;
	
	public static void main(String[] args) throws Throwable
	{
		/*
		for (int k = 20; k < 50; k++) {
			for (int m = 20; m < 50; m++) {
				BlockWriter.loadChunk(k*16, 0, m*16);
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						BlockWriter.setBlock(k*16 + i, 56, m*16 + j, IDs.BlockOfEmerald);
					}
				}
				BlockWriter.saveChunk();
			}
		}
		*/
		
		BlockWriter.loadChunk(0, 0, 0);
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
		int x= 6; int y=56; int z= 1;
		
		logicGate.placeNOTGateAt(x,y,z);
		BlockWriter.setBlock(x, y, z-1, IDs.Lever, LEVER_BLOCK_TOP_FACING_SOUTH);
		
		
		x=12; y=56; z= 1;
		logicGate.placeANDGateAt(x, y, z);
		BlockWriter.setBlock(x+1, y, z-2, IDs.Lever, LEVER_BLOCK_TOP_FACING_SOUTH);
		BlockWriter.setBlock(x-1, y, z-2, IDs.Lever, LEVER_BLOCK_TOP_FACING_SOUTH);
		
		x=18; y=56; z= 1;
		logicGate.placeORGateAt(x,y,z);
		BlockWriter.setBlock(x+1, y, z-3, IDs.Lever, LEVER_BLOCK_TOP_FACING_SOUTH);
		BlockWriter.setBlock(x-1, y, z-3, IDs.Lever, LEVER_BLOCK_TOP_FACING_SOUTH);
		
		
		BlockWriter.saveChunk();
			
		/*
		final Level level;
		
		FileInputStream fis = new FileInputStream(TestingUtils.getInputFile("level.dat"));
		try
		{
			level = new Level((Tag.Compound)Tag.deserialize(CompressionScheme.GZip.getInputStream(fis)));
		}
		finally{
			
		}

		Level.Player player = level.Player();
		player.PosY(100.0);
		player.Flying(true);
		Inventory i = player.Inventory();
		Inventory.Item item = new Inventory.Item(WrittenBook, 0, 1);
		item.Title("Ethonian Battle Book");
		item.Author("Vechs");
		item.Pages().add("��4As you hold this book, you feel the power to ��lincenerate��r��4 your enemies!");
		item.EnchantLevel(Inventory.Item.Enchantment.FireAspect, (short)2);
		i.Item(7, item);

		try(FileOutputStream fos = new FileOutputStream(TestingUtils.getOutputFile("level.dat")))
		{
			level.ToNBT("").serialize(CompressionScheme.GZip.getOutputStream(fos));
		}
		*/
	}
}
