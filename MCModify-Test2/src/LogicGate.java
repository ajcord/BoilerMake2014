import minecraft.BlockWriter;
import minecraft.IDs;


public class LogicGate {

	final static byte LEVER_BLOCK_BOTTOM_FACING_EAST=0;
	final static byte LEVER_BLOCK_SIDE_FACING_EAST=1;
	final static byte LEVER_BLOCK_SIDE_FACING_WEST=2;
	final static byte LEVER_BLOCK_SIDE_FACING_SOUTH=3;
	final static byte LEVER_BLOCK_SIDE_FACING_NORTH=4;
	final static byte LEVER_BLOCK_TOP_FACING_SOUTH=5;
	final static byte LEVER_BLOCK_TOP_FACING_EAST=6;
	final static byte LEVER_BLOCK_BOTTOM_FACING_SOUTH=7;
	final static byte LEVER_ACTIVE=8;
	final static byte REDSTONE_TORCH_ON_GROUND=0;
	final static byte REDSTONE_TORCH_ON_BLOCK_WEST_SIDE=1;
	final static byte REDSTONE_TORCH_ON_BLOCK_EAST_SIDE=2;
	final static byte REDSTONE_TORCH_ON_BLOCK_NORTH_SIDE=3;
	final static byte REDSTONE_TORCH_ON_BLOCK_SOUTH_SIDE=4;
	final static byte REDSTONE_TORCH_ON_BLOCK_BOTTOM=5;
	
	public LogicGate(){
		System.out.println("Logic gate made.");
	}
	
	public static void placeANDGateAt(int x, int y, int z){
		if( x<0 || y<0 || z<0);
		else{
			BlockWriter.setBlock(x+1, y, z+1, IDs.Sandstone);
			BlockWriter.setBlock(x+2, y, z+1, IDs.Sandstone);
			BlockWriter.setBlock(x, y, z+1, IDs.Sandstone);
			BlockWriter.setBlock(x, y, z, IDs.RedstoneWire);//Redstone wire on North side
			BlockWriter.setBlock(x+2, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x+1, y+1, z+1, IDs.RedstoneWire);
			BlockWriter.setBlock(x+2, y+1, z+1, IDs.RedstoneTorch); //REDSTONE_TORCH_ON_GROUND
			BlockWriter.setBlock(x, y+1, z+1, IDs.RedstoneTorch); //REDSTONE_TORCH_ON_GROUND
			
			BlockWriter.setBlock(x+1, y, z+2, IDs.RedstoneTorch, REDSTONE_TORCH_ON_BLOCK_NORTH_SIDE);
			BlockWriter.setBlock(x+1, y, z+3, IDs.RedstoneWire);//Redstone wire on South, output
		}
		
	}
	
	public static void placeORGateAt(int x, int y, int z){
		if( x<0 || y<0 || z<0);
		else{
			BlockWriter.setBlock(x+1, y, z+2, IDs.RedstoneWire); //connecting wire
			BlockWriter.setBlock(x+1, y, z+3, IDs.RedstoneWire);
			BlockWriter.setBlock(x, y, z+2, IDs.RedstoneWire);
			BlockWriter.setBlock(x, y, z+1, IDs.RedstoneWire);
			BlockWriter.setBlock(x+2, y, z+1, IDs.RedstoneWire);
			BlockWriter.setBlock(x, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x+2, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x+2, y, z+2, IDs.RedstoneWire);
		}
		
		
	}
	
	public static void placeNOTGateAt(int x, int y, int z){
		if( x<0 || y<0 || z<0);
		else{
			BlockWriter.setBlock(x, y, z+1, IDs.Sandstone);
			BlockWriter.setBlock(x, y, z, IDs.RedstoneWire);
			BlockWriter.setBlock(x, y, z+2, IDs.RedstoneTorch, REDSTONE_TORCH_ON_BLOCK_NORTH_SIDE);
			BlockWriter.setBlock(x, y, z+3, IDs.RedstoneWire);
		}
		
	}
/*	
	private logicGate AND(){
		BlockWriter.setBlock(3, 56, 1, IDs.Sandstone);
	}
	
	private logicGate OR(){
		
	}

	private logicGate NOT(){
	
	}
	*/
}
