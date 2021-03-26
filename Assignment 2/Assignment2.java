import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Assignment2 {

	public static void main(String[] args) {
		File file = null;
		Scanner readFromFile = null;
		String line = null;
		if (args.length ==0)
		{
			System.out.println("Error: no file found");
			System.exit(1);
		}
		file = new File(args[0]);
		try {
			readFromFile = new Scanner(file);
		}
		catch(FileNotFoundException exception) {
			System.out.print("ERROR: File not found for \"");
	         System.out.println(args[0]+"\"");
	     
	         System.exit(1)	;
		}
		System.out.print("Reading from file \"" + args[0] + "\":\n");
		 while (readFromFile.hasNextLine()) {
			 line = readFromFile.nextLine();
			 System.out.println(line);
		 }

	}

}
