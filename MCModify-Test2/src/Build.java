import minecraft.*;


public class Build {

	
	//given an array list of pairs, build sandstone block with with redstone on top for all
	public static void createCircleStairs(int x, int y, int z){
		//will be 4 high
		
	}
	
	public static void createStepStairs(int x, int y, int z){
		//will be 2 high
		buildDefaultBlock(x,y,z);
		buildDefaultBlock(x+1,y+1,z);
		buildDefaultBlock(x+2,y+1,z);
		
	}
	
	public static void createBus(int x, int y, int z){
		buildDefaultBlock(x,y,z);
		buildDefaultBlock(x+1,y,z);
		
		buildCustomBlock(x,y,z+1, IDs.RedstoneRepeater);
		
	}
	
	public static void buildDefaultBlock(int x, int y, int z){
		//default block is Sandstone with redstone on top
		BlockWriter.setBlock(x, y, z, IDs.Sandstone);
		BlockWriter.setBlock(x, y+1, z, IDs.RedstoneWire);
		System.out.println("Default block built.");
		
	}
	public static void buildCustomBlock(int x, int y, int z, int buildID){
		//default block is Sandstone with redstone on top
		BlockWriter.setBlock(x, y, z, IDs.Sandstone);
		if (buildID == IDs.RedstoneRepeater){
			BlockWriter.setBlock(x, y+1, z, (short) buildID, 2);
		}
		else
			BlockWriter.setBlock(x, y+1, z, buildID);
		System.out.println("Block with "+ buildID + " on top.");
		
	}

}
