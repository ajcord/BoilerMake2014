package parsetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Module {
	static ArrayList<Wire> wires = new ArrayList<Wire>();
	static ArrayList<Module> modules = new ArrayList<Module>();
	static boolean setHead = false;
	static Module head;
	int level = -1;
	public int type = -1; // 1 is AND, 2 is OR, 3 is NOT, 4 is NO-OP
	// int width; // Amount of levels it takes up (1 for primitives, more for
	// complex)
	public String name;
	public ArrayList<Wire> output;
	ArrayList<Wire> input;

	public Module(String name, ArrayList<Wire> input, Wire output, int level,
			int type) {// USED IF PRIMITATIVE TYPE
		System.out.println("Creating basic module " + name + " with level "
				+ level);
		this.level = -1;
		this.name = name;
		this.output = new ArrayList<Wire>();
		(this.output).add(output);
		this.input = (ArrayList<Wire>) input.clone();
		this.type = type;
	}

	public Module(String name, Wire input, Wire output, int type, int level) {
		System.out.println("Creating basic module " + name + " with level "
				+ level);
		this.level = level;
		this.name = name;
		this.output = new ArrayList<Wire>();
		this.input = new ArrayList<Wire>();
		(this.output).add(output);
		(this.input).add(input);
		this.type = type;
	}

	public Module(String file) {
		int counter = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = "Great Scott";
		while ((line != null)) {
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String linetemp = line;
			if (line.equals(""))
				continue;
			line = line.replaceAll("\t", "");// Line should be free of blank
												// space
			line = line.replaceAll(" ", "");
			System.out.println("Line read: " + line);
			if (line.substring(0, 6).equals("module") && counter == 0) {
				int firstParen = line.indexOf("(");
				name = line.substring(6, firstParen);
				level = 0;
				counter++;
				if (!setHead) {
					head = this;
					setHead = true;
					modules.add(this);
				}
				// modules.add(this);1
			} else if (line.substring(0, 3).equals("and")) {
				makePrimative(line, "and", 1);
			} else if (line.substring(0, 2).equals("or")) {
				makePrimative(line, "or", 2);
			} else if (line.substring(0, 3).equals("not")) {
				makePrimative(line, "not", 3);
			} else if (line.substring(0, 5).equals("input")) {
				this.output = globalWires(line, "input");
				for (Wire w : this.output)
					w.comingFrom = this;
			} else if (line.substring(0, 6).equals("output")) {
				this.input = globalWires(line, "output");
				// for(Wire w : this.input)
				// w.goingTo.add(this);
			} else if (line.substring(0, 4).equals("wire")) {
				globalWires(line, "wire");
			} else if (line.equals("endmodule")) {
				break;
			} else {
				/* non primitive module */
				linetemp = linetemp.replaceAll("\t", "");
				int index = linetemp.indexOf(" ");// NOTE: TABS MAY CAUSE ERRORS
													// HERE!!
				String filename = linetemp.substring(0, index);
				filename = filename.replaceAll(" ", "");
				filename = filename + ".txt";
				String newFile = filename;
				// Figure out what my wire names are before I step into function
				String[] params = linetemp.substring(linetemp.indexOf("(") + 1,
						linetemp.indexOf(")")).split(",");
				// Figure out what my wire names are after I step into function
				BufferedReader brr = null;
				try {
					brr = new BufferedReader(new FileReader(newFile));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String printline = "";
				String readline = "New Params";
				try {
					readline = brr.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Now I have the module declaration line
				String[] newParams = readline.substring(
						readline.indexOf("(") + 1, readline.indexOf(")"))
						.split(",");
				// Find and replace one at a time
				while (readline != null) {
					System.out.println("Parsing:" + readline);
					if (readline.indexOf("(") != -1)
						printline += replaceString(readline, "(", newParams,
								params);
					else if (readline.indexOf("output") != -1)
						printline += replaceString(readline, "output",
								newParams, params);
					else if (readline.indexOf("input") != -1)
						printline += replaceString(readline, "input",
								newParams, params);
					else if (readline.indexOf("wire") != -1)
						printline += replaceString(readline, "wire", newParams,
								params);
					else
						printline += readline;
					printline += '\n';
					try {
						readline = brr.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// Write to the file
				try {
					PrintWriter pw = new PrintWriter(new BufferedWriter(
							new FileWriter(newFile)));
					pw.println(printline);
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Module newMod = new Module(newFile);
				for (Wire w : newMod.input)
					w.goingTo.add(newMod);
				newMod.name = line.substring(index, line.indexOf("("));
				linetemp = linetemp.substring(index + (newMod.name.length()),
						line.indexOf(","));
				modules.add(newMod);
			}
		}
	}

	/*
	 * Returns an arraylist of arraylist of modules where modules.get(0) returns
	 * a module array representing the zeorth level
	 */
	public ArrayList<ArrayList<Module>> getLevelArray() {
		recurseLongest(head);
		int maxLevel = -1;
		for (Module m : Module.modules)
			if (m.level > maxLevel)
				maxLevel = m.level;
		System.out.println("Max Level is: " + maxLevel);
		ArrayList<ArrayList<Module>> modules = new ArrayList<ArrayList<Module>>(
				maxLevel + 2);
		for (int i = 0; i < maxLevel + 2; i++)
			modules.add(new ArrayList<Module>());
		for (Module m : Module.modules)
			modules.get(m.level).add(m); // 1 line 2-d array by Chong
		while (modules.get(0).size() != 0)
			modules.get(0).remove(modules.get(0).size() - 1);
		modules.get(0).add(head);
		modules.get(maxLevel + 1).add(
				new Module("TAIL", head.input, null, maxLevel + 1, -1));
		System.out.println("Finished setting moduleLevels!");

		return modules;
	}

	public String replaceString(String readline, String deliminator,
			String[] newParams, String[] params) {
		// Gets everything to the left of the open parenthesis
		String left = readline.substring(0, readline.indexOf(deliminator));
		String right = readline.substring(readline.indexOf(deliminator));
		for (int i = 0; i < newParams.length; i++) {
			right = right.replaceAll(newParams[i], params[i]);
		}
		return left + right;
	}

	public Wire findWire(String name) {
		for (int i = 0; i < wires.size(); i++)
			if ((wires.get(i)).name.equals(name)) {
				System.out.println("Found wire " + wires.get(i).name);
				return wires.get(i);

			}
		System.out.println("Did not find Wire");
		return new Wire();
	}

	public Module makePrimative(String line, String gate, int type) {// or
		for (int a = 0; a < wires.size(); a++)
			System.out.print(wires.get(a).name + ", ");
		System.out.println();
		int i = line.indexOf("(");
		String mod = line.substring(gate.length(), i);
		line = line.substring(i + 1);
		i = line.indexOf(",");
		String output = line.substring(0, i);
		int k = line.indexOf(",");
		String out = line.substring(0, k);
		i = line.indexOf(")");
		line = line.substring(0, i);
		line = line.substring(k + 1, i);
		ArrayList<Wire> inputs = createWires(line);
		Wire leaving = findWire(output);
		ArrayList<Wire> coming = new ArrayList<Wire>();
		for (i = 0; i < inputs.size(); i++) {
			coming.add(findWire((inputs.get(i)).name));
		}
		Module primative = new Module(mod, coming, leaving, this.level + 1,
				type);
		modules.add(primative);
		for (i = 0; i < primative.input.size(); i++) {
			Wire j = (primative.input).get(i);
			ArrayList<Module> m = j.goingTo;
			m.add(primative);
		}
		leaving.comingFrom = primative;
		return primative;
	}

	public ArrayList<Wire> globalWires(String line, String deliminator) {// output
																			// a,d;
		line = line.substring(deliminator.length());
		if (line.indexOf(";") != -1)
			line = line.substring(0, line.indexOf(";"));
		ArrayList<Wire> newwires = createWires(line);
		for (int i = 0; i < newwires.size(); i++) {
			Wire tempwire = newwires.get(i);
			if ((findWire(tempwire.name).name).equals("NONE AVAILABLE"))
				wires.add(tempwire);
		}
		return newwires;
	}

	public ArrayList<Wire> createWires(String line) { // line = "a,b,c"
		ArrayList<Wire> nwires = new ArrayList<Wire>();
		String[] split = line.split(",");
		for (int i = 0; i < split.length; i++) {
			nwires.add(new Wire(split[i]));// How do we make sure we do not
											// create 2 wires
		}
		return nwires;
	}/*
	 * public boolean isHead(Module mod){ boolean isHead = true; ArrayList<Wire>
	 * inputs = mod.input; for(Wire w : inputs){ if(getParent(w)!=null){ return
	 * false; } } return isHead; }
	 */

	public Module getParent(Wire wire) {
		return wire.comingFrom;
	}

	public ArrayList<Module> getChildren() {
		if (this == null || output.size() == 0)
			return null;
		ArrayList<Module> temp = new ArrayList<Module>();
		for (int i = 0; i < output.size(); i++) {
			Wire outward = (output.get(i));
			for (int j = 0; j < outward.goingTo.size(); j++) {
				temp.add(outward.goingTo.get(j));
			}
		}
		return temp;
	}

	public void recurseLongest(Module node) {
		if (node == null)
			return;
		if (node == head) {// HOW TO TELL THAT IT IS THE HEAD??
			node.level = 0;
		} else {
			ArrayList<Integer> parentlevel = new ArrayList<Integer>();
			for (int i = 0; i < (node.input).size(); i++) {
				Module w = getParent((node.input).get(i));
				System.out.println("Getting parent from wire "
						+ (node.input).get(i).name + " for node " + node.name);
				int y = w.level;
				System.out.println("Node " + node.name + " has a parent "
						+ w.name + " through wire " + (node.input).get(i).name
						+ " whose level is " + y);
				parentlevel.add(y);
			}
			Integer plevel = Collections.max(parentlevel);
			System.out.println("Node " + node.name + " has a new level of "
					+ (plevel + 1));
			node.level = plevel + 1;
		}
		ArrayList<Module> kids = node.getChildren();
		if (kids == null)
			return;
		for (int i = 0; i < kids.size(); i++) {
			recurseLongest(kids.get(i));
		}
		return;
	}

	public static ArrayList<ArrayList<Module>> improvedLevelArray(
			ArrayList<ArrayList<Module>> current) {
		ArrayList<ArrayList<Module>> modules = (ArrayList<ArrayList<Module>>) current.clone();
		for (int i = 0; i < modules.size(); i++) {
			for (int j = 0; j < modules.get(i).size(); j++) {
				Module now = modules.get(i).get(j);
				for (int k = 0; k < now.output.size(); k++) {
					Wire present = now.output.get(k);
					if(present != null){
					for (int l = 0; l < present.goingTo.size(); l++) {
						Module next = present.goingTo.get(l);
						int diff = now.level - next.level;
						while (diff > 1) {
							String name = "" + now + " " + next + " " + diff;
							Wire win = new Wire("IN" + name, now);
							Wire wout = new Wire("OUT" + name);
							if (diff == 2) {
								wout.goingTo.add(next);
							}
							Module mnew = new Module(name, win, wout, 4,
									now.level + 1);
							win.goingTo.add(mnew);
							wout.comingFrom = mnew;
							now = mnew;
							modules.get(mnew.level).add(mnew);
							diff--;
						}
					}
					}
				}
			}

		}
		return modules;
	}
}