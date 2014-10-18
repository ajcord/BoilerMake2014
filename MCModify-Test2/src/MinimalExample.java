import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import location.LocChunkInRegion;
import minecraft.FileRegion;
import minecraft.Region;
import minecraft.Chunk;
import minecraft.IDs;

public class MinimalExample {
	public static void main(String[] args) {
		try {
            //Region region = new Region(new File("flatworld_master_r.0.0.mca"));
            String filename = "r.0.0.mca";
            //===================================================
            //the missing line that solved both problems
            copyFile("r.0.0.mca.in",filename);
            //===================================================
            Region newregion = new FileRegion(new File(filename)); 
			
			/*LocChunkInRegion loc = new LocChunkInRegion(0, 0);
			Chunk chunk = region.getChunk(loc);
			//chunk.BlockID(0, 57, 0, IDs.Dirt);
			newregion.setChunk(loc, chunk);
			System.out.println("Saved");*/
			
			for (int x = 10; x < 32; x++) {
				for (int y = 10; y < 32; y++) {
					LocChunkInRegion loc = new LocChunkInRegion(x, y);
					Chunk chunk = newregion.getChunk(loc);
					
					//if (chunk != null) {
						for (int k = 0; k < 16; k++) {
							for (int k2 = 0; k2 < 16; k2++) {
								chunk.BlockID(k, 57, k2, IDs.Dirt);
							}
						}
						
						newregion.setChunk(loc, chunk);
						System.out.println("Wrote chunk at location " + loc.x + ", " + loc.z);
					//}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void copyFile(String sourceName, String targetName) throws IOException
    {
        File sourceFile = new File(sourceName);
        File destFile = new File(targetName);

        if (!destFile.exists())
        {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try
        {
            source = new RandomAccessFile(sourceFile, "rw").getChannel();
            destination = new RandomAccessFile(destFile, "rw").getChannel();

            long position = 0;
            long count = source.size();

            source.transferTo(position, count, destination);
        }
        finally
        {
            if (source != null)
            {
                source.close();
            }
            if (destination != null)
            {
                destination.close();
            }
        }
    }
}