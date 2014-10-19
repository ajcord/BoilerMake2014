import java.util.ArrayList;

public class PairTest {

	public static void main(String[] args) {
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

}
