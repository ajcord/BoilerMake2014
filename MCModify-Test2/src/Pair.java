import java.util.ArrayList;

public class Pair{
	int x;//We know delta y is 10
	int y;
	int z;
	public Pair(int a, int b, int c){
		x = a;
		y = b;
		z = c;
	}

	public ArrayList<Path> crossingProblem(ArrayList<Pair> pairs) {
		ArrayList<Path> solution = new ArrayList<Path>();
		/*
		 * Given n amount of points starting at y = 0 which must reach m amount of points on y = 10,
		 * we MUST have some crossing if they are not aligned one to one. To make this simple, we will
		 * give each path a value of 2, 4, 6, or 8 to traverse 
		 * 
		 */
		
	
		return solution;
	}
}
