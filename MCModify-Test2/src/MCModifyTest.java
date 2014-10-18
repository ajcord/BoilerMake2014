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
	public static void main(String[] args) throws Throwable
	{
		/*
		BlockWriter.loadChunk(0, 56, 0);
		BlockWriter.setBlock(0, 56, 0, IDs.BlockOfGold);
		BlockWriter.saveChunk();
		*/
		
		for (int k = 0; k < 12; k++) {
			BlockWriter.loadChunk(k*16, 0, 0);
			//System.out.println("Loading Chunk: " + k);
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 16; j++) {
					BlockWriter.setBlock(k*16 + i,  56, j, IDs.BlockOfEmerald);
				}
			}
			BlockWriter.saveChunk();
			//System.out.println("Saved Chunk: " + k);
		}
		
		for (int k = 0; k < 12; k++) { //simple change
			BlockWriter.loadChunk(k*16, 0, 0);
			for (int x =1; x<8; x+=2){
				BlockWriter.setBlock(x,1,1, IDs.Lever);
				for (int z=1; z<2; z++){
					BlockWriter.setBlock(x, 1, z, IDs.RedstoneWire);
					if (z==3){
						BlockWriter.setBlock(3, 1, z, IDs.Sandstone);
						BlockWriter.setBlock(5, 1, z, IDs.Sandstone);
						BlockWriter.setBlock(7, 1, z, IDs.Sandstone);
						BlockWriter.setBlock(3, 2, z, IDs.RedstoneWire);
						BlockWriter.setBlock(5, 2, z, IDs.RedstoneWire);
						BlockWriter.setBlock(7, 2, z, IDs.RedstoneWire);
					}
					if(z==4){
						BlockWriter.setBlock(3, 2, z, IDs.Sandstone);
						BlockWriter.setBlock(5, 2, z, IDs.Sandstone);
						BlockWriter.setBlock(7, 2, z, IDs.Sandstone);
						BlockWriter.setBlock(3, 3, z, IDs.RedstoneWire);
						BlockWriter.setBlock(5, 3, z, IDs.RedstoneWire);
						BlockWriter.setBlock(7, 3, z, IDs.RedstoneWire);
					}
				}
				}
			}
			
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
