import java.util.ArrayList;

public class Pair{
	int x;//We know delta y is 10
	int y;
	int z;
	int height;
	public Pair(int a, int b, int c){
		x = a;
		y = b;
		z = c;
	}

	public ArrayList<ArrayList<Pair>> crossingProblem(ArrayList<Pair> pairs, ArrayList<Pair> finals) {
		if(pairs.size() != finals.size()){
			System.out.println("You need to pass in the same size arrays");
			return null;
		}
		ArrayList<ArrayList<Pair>> solution = new ArrayList<ArrayList<Pair>>();
		/*
		 * Given n amount of points starting at y = 0 which must reach m amount of points on y = 10,
		 * we MUST have some crossing if they are not aligned one to one. To make this simple, we will
		 * give each path a value of 2, 4, 6, or 8 to traverse 
		 * 
		 */
		int temp = 0;
		for(int i = 0; i < pairs.size(); i++){
			pairs.get(i).height = temp;
			temp += 2;
			ArrayList<Pair> p = new ArrayList<Pair>();
			p.add(new Pair(x++,y,z));
			for(int z = 0; z < height; z++){
				p.add(new Pair(x,y++,z));
			}
			for(int a = 0; a < 4; a++){
				p.add(new Pair(x++,y,z));
			}
			Pair expect = finals.get(i);
			while(z != expect.z){
				if(z < expect.z) p.add(new Pair(x,y,z++));
				
			}
		}
	
		return solution;
	}
}
