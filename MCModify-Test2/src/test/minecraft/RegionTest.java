package test.minecraft;

import location.LocChunkInRegion;
import minecraft.Chunk;
import minecraft.FileRegion;
import minecraft.Mob;
import test.TestingUtils;


public class RegionTest
{
	public void modifyEveryChunk() throws Throwable
	{
		FileRegion region = new FileRegion(TestingUtils.getInputFile("r.0.0.mca"));
		FileRegion newregion = new FileRegion(TestingUtils.getOutputFile("r.0.0.mca"));
		for(int x = 0; x < 31; ++x)
		{
			for(int z = 0; z < 31; ++z)
			{
				Chunk chunk = region.getChunk(new LocChunkInRegion(x, z));
				if(chunk != null)
				{
					chunk.Entities().add(new Mob.EnderDragon(x*16+8, 96, z*16+8));
				}
				newregion.setChunk(new LocChunkInRegion(x, z), chunk);
			}
		}
	}
}
