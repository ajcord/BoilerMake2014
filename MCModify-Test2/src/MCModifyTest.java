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
		
<<<<<<< HEAD
		for (int k = 20; k < 50; k++) {
			for (int m = 20; m < 50; m++) {
				BlockWriter.loadChunk(k*16, 0, m*16);
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						BlockWriter.setBlock(k*16 + i, 56, m*16 + j, IDs.BlockOfEmerald);
					}
=======
		for (int k = 0; k < 12; k++) {
			BlockWriter.loadChunk(k*16, 0, 0);
			//System.out.println("Loading Chunk: " + k);
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 16; j++) {
					BlockWriter.setBlock(k*16 + i,  56, j, IDs.BlockOfEmerald);
>>>>>>> FETCH_HEAD
				}
				BlockWriter.saveChunk();
			}
<<<<<<< HEAD
=======
			BlockWriter.saveChunk();
			//System.out.println("Saved Chunk: " + k);
>>>>>>> FETCH_HEAD
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
		item.Pages().add("§4As you hold this book, you feel the power to §lincenerate§r§4 your enemies!");
		item.EnchantLevel(Inventory.Item.Enchantment.FireAspect, (short)2);
		i.Item(7, item);

		try(FileOutputStream fos = new FileOutputStream(TestingUtils.getOutputFile("level.dat")))
		{
			level.ToNBT("").serialize(CompressionScheme.GZip.getOutputStream(fos));
		}
		*/
	}
}
