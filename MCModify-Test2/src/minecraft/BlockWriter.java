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
	/*
	private static Chunk currentChunk;
	private static LocChunkInRegion currentChunkLoc;
	private static Region currentRegion;
	private static int currentRegionX, currentRegionZ;
	*/
	

	public static void setBlock(int x, int y, int z, short blockID) {

		if (y > 255 || y < 0) {
			throw new IllegalArgumentException("Invalid y coordinate ("+x+","+y+","+z+")");
		}
		try {
			//Get the region number from the x, z coordinates
			int regionX = x >> 5;
			int regionZ = z >> 5;
			
			//Get the chunk position within the region
			int chunkX = (x >> 4) % 32;
			int chunkZ = (z >> 4) % 32;
			if (chunkX < 0) chunkX += 32;
			if (chunkZ < 0) chunkZ += 32;
			
			//Get the block coordinate within the chunk
			x %= 16;
			z %= 16;
			if (x < 0) x += 16;
			if (z < 0) z += 16;
			
			//Make a copy of the region file
			String filename = "region/r." + regionX + "." + regionZ + ".mca";
			//copyFile("region_template.mca.in", filename);
			
			//Check if we are within the same region as the previous write
			Region region = new FileRegion(new File(filename));
			/*
			if (currentRegion != null &&
					regionX == currentRegionX && regionZ == currentRegionZ) {
				//Same region
				region = currentRegion;
			} else {
				//Different region. First, write the previous chunk and clear it out
				//before we change regions.
				if (currentChunk != null && currentChunkLoc != null) {
					currentRegion.setChunk(currentChunkLoc, currentChunk);
					System.out.println("Wrote chunk at location " + currentChunkLoc.x + ", " + currentChunkLoc.z);
					currentChunkLoc = null;
					currentChunk = null;
				}
				
				//Load the new region
				region = new FileRegion(new File(filename));
				currentRegion = region;
				currentRegionX = regionX;
				currentRegionZ = regionZ;
			}
			*/
			
			//Check if we are within the same chunk as the previous write
			/*
			Chunk chunk;
			LocChunkInRegion loc = new LocChunkInRegion(chunkX, chunkZ);
			if (currentChunk != null && currentChunkLoc != null &&
					currentChunkLoc.x == loc.x && currentChunkLoc.z == loc.z) {
				//Same chunk
				chunk = currentChunk;
			} else {
				//Different chunk. First, write the previous chunk before
				//getting the new one.
				if (currentChunk != null && currentChunkLoc != null) {
					currentRegion.setChunk(currentChunkLoc, currentChunk);
					System.out.println("Wrote chunk at location " + loc.x + ", " + loc.z);
				}
				chunk = region.getChunk(loc);
				currentChunk = chunk;
				currentChunkLoc = loc;
			}
			*/

			LocChunkInRegion loc = new LocChunkInRegion(chunkX, chunkZ);
			Chunk chunk = region.getChunk(loc);
			if (chunk != null) {
				chunk.BlockID(x, y, z, blockID);
				region.setChunk(loc, chunk);
				//System.out.println("Wrote chunk at location " + loc.x + ", " + loc.z);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
