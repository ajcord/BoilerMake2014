package parsetree;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ParseMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "verilogtest.txt";
		Module head = new Module(file);
		Module temp = head;
		System.out.print("Head Module named " + temp.name + " has inputs: ");
		for(int i = 0; i < (temp.input).size();i++){
			System.out.print((temp.input.get(i)).name + " ");
		}
		System.out.print(" and outputs: ");
		for(int i = 0; i < (temp.output).size();i++){
			System.out.print((temp.output.get(i)).name + " ");
		}
		System.out.println("and level: " + temp.level);
		for(int j = 0; j < (Module.modules).size(); j++ ){
			temp = Module.modules.get(j);
			System.out.print("Module " + temp.name + " has inputs: ");
			for(int i = 0; i < (temp.input).size();i++){
				System.out.print((temp.input.get(i)).name + " ");
			}
			System.out.print(" and outputs: ");
			for(int i = 0; i < (temp.output).size();i++){
				System.out.print((temp.output.get(i)).name + " ");
			}
			System.out.println("and level: " + temp.level + " and is type " + temp.type);
			System.out.print("\nModule " + temp.name + " also has these kids: ");
			ArrayList<Module> children = temp.getChildren();
			for(int i = 0; i < children.size(); i++)
				System.out.print((children.get(i)).name + " ");
			System.out.println();
		}
	}
}
