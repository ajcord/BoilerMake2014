import minecraft.*;


public class Build {

	
	//given an array list of pairs, build sandstone block with with redstone on top for all
	
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
			BlockWriter.setBlock(x, y+1, z, buildID, (byte) 1); //1 indicates facing east
		}
		else
			BlockWriter.setBlock(x, y+1, z, buildID);
		System.out.println("Block with "+ buildID + " on top.");
		
	}

}
