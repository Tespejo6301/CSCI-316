

public class Assignment2 {

	public static void main(String[] args) {

		if (args.length == 0)
		{
			System.out.println("Error: no file found");
			System.exit(1);
		}
		LexicalAnalyzer data = new LexicalAnalyzer(args[0]);
		SynParser parse = new SynParser(data);
	  
		parse.Start();	
	 
		
		
	}

}
