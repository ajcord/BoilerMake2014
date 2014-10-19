import minecraft.*;


public class Build {

	
	//given an array list of pairs, build sandstone block with with redstone on top for all
	public static void createCircleStairs(){
		//will be 4 high
		
	}
	
	public static void createStepStairs(){
		//will be 2 high
		
		
	}
	
	public static void createBus(){
		
		
	}
	
	private static void buildDefaultBlock(int x, int y, int z){
		//default block is Sandstone with redstone on top
		BlockWriter.setBlock(x, y, z, IDs.Sandstone);
		BlockWriter.setBlock(x, y+1, z, IDs.RedstoneWire);
		System.out.println("Default block built.");
		
	}
	private static void buildCustomBlock(int x, int y, int z, short blockID){
		//default block is Sandstone with redstone on top
		BlockWriter.setBlock(x, y, z, IDs.Sandstone);
		BlockWriter.setBlock(x, y+1, z, blockID);
		System.out.println("Block with "+ blockID + " on top.");
		
	}

}
