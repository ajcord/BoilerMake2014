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
			//Get the chunk position relative to the world
			int chunkX = x / 16;
			int chunkZ = z / 16;
			
			//Get the region number from chunk position
			int regionX = chunkX / 32;
			int regionZ = chunkZ / 32;
			
			//Convert the chunk position to relative to the region
			chunkX %= 32;
			chunkZ %= 32;
			if (chunkX < 0) chunkX += 32;
			if (chunkZ < 0) chunkZ += 32;
			
			//Get the block coordinate within the chunk
			x %= 16;
			z %= 16;
			if (x < 0) x += 16;
			if (z < 0) z += 16;
			
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
	
	public static short getBlock(int x, int y, int z) {
		if (y > 255 || y < 0) {
			throw new IllegalArgumentException("Invalid y coordinate ("+x+","+y+","+z+")");
		}
		
		if (currentChunk != null) {
			//Get the block coordinate within the chunk
			x %= 16;
			z %= 16;
			if (x < 0) x += 16;
			if (z < 0) z += 16;
			return currentChunk.BlockID(x, y, z);
		}
		return -1;
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
		}
	}

	public static void setBlock(int x, int y, int z, short blockID, byte data) {
		setBlock(x, y, z, blockID);
		if (currentChunk != null) {
			currentChunk.BlockData(x, y, z, data);
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

	public static void setBlock(int x, int y, int z, int blockID,
			byte data) {
		setBlock(x, y, z, (short) blockID);
		if (currentChunk != null) {
			currentChunk.BlockData(x, y, z, data);
		}
		
	}
	
	public static void setBlock(int x, int y, int z, int blockID) {
		setBlock(x, y, z, (short) blockID);
		
	}
}
