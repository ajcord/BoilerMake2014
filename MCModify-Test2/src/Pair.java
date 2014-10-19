import java.util.ArrayList;

import minecraft.IDs;

public class Pair {
	int x;//We know delta x is 10 (between output and input)
	int y;
	int z;
	int height;
	short topBlock;
	public Pair(int a, int b, int c) {
		x = a;
		y = b;
		z = c;
		topBlock = IDs.Air;
	}
	
	public Pair(int a, int b, int c, short topBlock) {
		this(a, b, c);
		this.topBlock = topBlock;
	}

	public static ArrayList<ArrayList<Pair>> crossingProblem(ArrayList<Pair> pairs, ArrayList<Pair> finals) {
		if (pairs.size() != finals.size()) {
			System.out.println("You need to pass in the same size arrays");
			return null;
		}
		ArrayList<ArrayList<Pair>> solution = new ArrayList<ArrayList<Pair>>();
		/*
		 * Given n amount of points starting at y = 0 which must reach m amount
		 * of points on y = 10, we MUST have some crossing if they are not
		 * aligned one to one. To make this simple, we will give each path a
		 * value of 2, 4, 6, or 8 to traverse
		 */
		int targetHeight = 0;
		for (int i = 0; i < pairs.size(); i++) {
			pairs.get(i).height = targetHeight; //Set the current pair's height
			int x = pairs.get(i).x;
			int y = pairs.get(i).y - 1;
			int z = pairs.get(i).z;
			/* 
			 * Generate a new ArrayList of Pairs to contain the path from
			 * pairs.get(i) to finals.get(i)
			 */
			ArrayList<Pair> p = new ArrayList<Pair>();
			
			//Elevate from ground level to the current height
			int dx = 0;
			int dy = 0;
			int dz = 0;
			if (targetHeight == 0) {
				//No need to elevate. Stay at ground level.
				for ( ; dx < 2; dx++) {
					p.add(new Pair(x + dx, y + dy, z + dz, IDs.RedstoneWire));
				}
			} else {
				while (dy + 2 < targetHeight) {
					//Spiral staircase up to get to targetHeight
					p.add(new Pair(x + dx++, y + dy++, z + dz, IDs.RedstoneWire));
					p.add(new Pair(x + dx, y + dy++, z + dz++, IDs.RedstoneWire));
					p.add(new Pair(x + dx--, y + dy++, z + dz, IDs.RedstoneWire));
					p.add(new Pair(x + dx, y + dy++, z + dz--, IDs.RedstoneWire));
				}
				if (dy < targetHeight) {
					//Add a stairstep to get it up to targetHeight
					p.add(new Pair(x + dx++, y + dy++, z + dz, IDs.RedstoneWire));
					p.add(new Pair(x + dx++, y + dy++, z + dz, IDs.RedstoneWire));
				} else {
					//Extend the path twice to get it in line with the stairstepped ones
					p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
					p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
				}
			}
			
			//Add a wire and repeater to ensure the signal gets where it needs to
			p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
			p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneRepeaterOff));
			
			//Move into alignment with target position
			Pair expect = finals.get(i);
			while (z + dz != expect.z) {
				if (z + dz < expect.z) {
					p.add(new Pair(x + dx, y + dy, z + dz++, IDs.RedstoneWire));
				} else {
					p.add(new Pair(x + dx, y + dy, z + dz--, IDs.RedstoneWire));
				}
			}
			
			//Add a wire or two to give some space for the descent
			p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
			p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
			
			//Descend from the current height to ground level
			if (targetHeight == 0) {
				//No need to descend. Stay at ground level.
				for ( ; dx < 9; dx++) {
					p.add(new Pair(x + dx, y + dy, z + dz, IDs.RedstoneWire));
				}
			} else {
				while (dy - 2 > expect.y - y) {
					//Spiral staircase down to get to expect.y
					p.add(new Pair(x + dx++, y + dy--, z + dz, IDs.RedstoneWire));
					p.add(new Pair(x + dx, y + dy--, z + dz++, IDs.RedstoneWire));
					p.add(new Pair(x + dx--, y + dy--, z + dz, IDs.RedstoneWire));
					p.add(new Pair(x + dx, y + dy--, z + dz--, IDs.RedstoneWire));
				}
				if (dy > expect.y - y) {
					//Add a stairstep to get it down to expect.y
					p.add(new Pair(x + dx++, y + dy--, z + dz, IDs.RedstoneWire));
					p.add(new Pair(x + dx++, y + dy--, z + dz, IDs.RedstoneWire));
				} else {
					//Extend the path twice to get it in line with the stairstepped ones
					p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
					p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
				}
				p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
			}
			
			//Add a final wire and repeater to ensure a good input signal
			p.add(new Pair(x + dx++, y + dy, z + dz, IDs.RedstoneWire));
			p.add(new Pair(x + dx, y + dy, z + dz, IDs.RedstoneRepeaterOff));
			
			solution.add(p);
			
			targetHeight += 2; //Move the next pair up by 2 blocks
		}
	
		return solution;
	}
}
