package minecraft;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import location.LocChunkInRegion;
import minecraft.Chunk;
import minecraft.FileRegion;
import minecraft.Region;

public class BlockWriter {
	private static Chunk currentChunk;
	private static LocChunkInRegion currentLoc;
	private static Region currentRegion;
	
	public static void loadChunk(int x, int y, int z) {
		try {
			//Get the chunk position
			int chunkX = x >> 4;
			int chunkZ = z >> 4;
			
			//Get the region number from chunk position
			int regionX = chunkX >> 5;
			int regionZ = chunkZ >> 5;
			
			//Get the block coordinate within the chunk
			x %= 16;
			z %= 16;
			if (x < 0) x += 16;
			if (z < 0) z += 16;
			
			System.out.println("Chunk: " + chunkX + " " + chunkZ);
			
			String filename = "region/r." + regionX + "." + regionZ + ".mca";
			currentRegion = new FileRegion(new File(filename));
			currentLoc = new LocChunkInRegion(chunkX, chunkZ);
			currentChunk = currentRegion.getChunk(currentLoc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveChunk() {
		try {
			if (currentLoc != null && currentChunk != null) {
				currentRegion.setChunk(currentLoc, currentChunk);
				System.out.println("Wrote chunk at location " + currentLoc.x + ", " + currentLoc.z);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setBlock(int x, int y, int z, short blockID) {

		if (y > 255 || y < 0) {
			throw new IllegalArgumentException("Invalid y coordinate ("+x+","+y+","+z+")");
		}
		
		if (currentChunk != null) {
			//Get the block coordinate within the chunk
			x %= 16;
			z %= 16;
			if (x < 0) x += 16;
			if (z < 0) z += 16;
			currentChunk.BlockID(x, y, z, blockID);
			System.out.println("Coords: " + x + " " + y + " " + z);
		}
	}

	private static void copyFile(String sourceName, String targetName)
			throws IOException {
		File sourceFile = new File(sourceName);
		File destFile = new File(targetName);

		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new RandomAccessFile(sourceFile, "rw").getChannel();
			destination = new RandomAccessFile(destFile, "rw").getChannel();

			long position = 0;
			long count = source.size();

			source.transferTo(position, count, destination);
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
}
