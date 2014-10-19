import java.util.ArrayList;

public class PairTest {

	public static void main(String[] args) {
		int test = 3;
		switch(test) {
		case 1:
			testSimpleMapping();
			break;
		case 2:
			testMediumMapping();
			break;
		case 3:
			testHardMapping();
			break;
		}
	}
	
	public static void testSimpleMapping() {
		//Tests 4 outputs going directly to 4 inputs, no crossover
		ArrayList<Pair> start = new ArrayList<Pair>();
		ArrayList<Pair> finish = new ArrayList<Pair>();
		start.add(new Pair(0, 56, 0));
		start.add(new Pair(0, 56, 3));
		start.add(new Pair(0, 56, 6));
		start.add(new Pair(0, 56, 9));
		finish.add(new Pair(10, 56, 0));
		finish.add(new Pair(10, 56, 3));
		finish.add(new Pair(10, 56, 6));
		finish.add(new Pair(10, 56, 9));
		ArrayList<ArrayList<Pair>> results = Pair.crossingProblem(start, finish);
		Interpreter.Interpret(results);
	}
	
	public static void testMediumMapping() {
		//Tests 4 outputs going to 4 inputs, with crossover
		ArrayList<Pair> start = new ArrayList<Pair>();
		ArrayList<Pair> finish = new ArrayList<Pair>();
		start.add(new Pair(0, 56, 0));
		start.add(new Pair(0, 56, 3));
		start.add(new Pair(0, 56, 6));
		start.add(new Pair(0, 56, 9));
		finish.add(new Pair(10, 56, 9));
		finish.add(new Pair(10, 56, 6));
		finish.add(new Pair(10, 56, 0));
		finish.add(new Pair(10, 56, 3));
		ArrayList<ArrayList<Pair>> results = Pair.crossingProblem(start, finish);
		Interpreter.Interpret(results);
	}
	
	public static void testHardMapping() {
		//Tests 7 outputs going to 14 inputs, with crossover
		ArrayList<Pair> start = new ArrayList<Pair>();
		ArrayList<Pair> finish = new ArrayList<Pair>();
		for (int i = 0; i < 7; i++) {
			start.add(new Pair(0, 56, 3*i));
			start.add(new Pair(0, 56, 3*i));
		}
		for (int i = 15; i > 0; i--) {
			finish.add(new Pair(10, 56, 3*i));
		}
		ArrayList<ArrayList<Pair>> results = Pair.crossingProblem(start, finish);
		Interpreter.Interpret(results);
	}

}
