

public class Error {
	
	public void wrongSyntax(int error){
		caret(error);
		System.out.println("Error: Program syntactically correct" + " at position " + error);
		System.exit(1);
	}
	public void missEnd(int error){	
		caret(error);
		System.out.println("Error: Missing End Symbol" + " at position " + error);
		System.exit(1);
	}
	public void missStart(int error){
		caret(error);
		System.out.println("Error: Missing Begin Symbol" + " at position " + error);
		System.exit(1);
	}
	public void notEnd(int error){
		caret(error);
		System.out.println("Error: Did not reach the end of the file" + " at position " + error);
		System.exit(1);
	}
	public void missIdent(int error){
		caret(error);
		System.out.println("Error: Missing an Identifier" + " at position " + error);
		System.exit(1);
	}
	public void missSemicolon(int error){
		caret(error);
		System.out.println("Error: Missing Semicolon" + " at position " + error);
		System.exit(1);
	}
	public void missSymbol(int error) {
		caret(error);
		System.out.println("Error: Missing Boolean or Integer Symbol" + " at position " + error);	
		System.exit(1);
	}
	public void missColon(int error){
		caret(error);
		System.out.println("Error: Missing Colon" + " at position " + error);
		System.exit(1);
	}
	public void missIs(int error){
		caret(error);
		System.out.println("Error: Missing Is Symbol" + " at position " + error);
		System.exit(1);
	}
	public void missExpression(int error) {
		caret(error);
		System.out.println("Error: Missing an Expression" + " at position " + error);
		System.exit(1);
		
	}
	public void missProcess(int error){
		caret(error);
		System.out.println("Error: Missing Is Process Symbol" + " at position " + error);
		System.exit(1);
		
	}
	public void missCondition(int error) {
		caret(error);
		System.out.println("Error: Missing A Condition Symbol" + " at position " + error);
		System.exit(1);
		
	}
	public void missIF(int error) {
		caret(error);
		System.out.println("Error: Missing IF Statement" + " at position " + error);
		System.exit(1);
		
	}
	public void missElse(int error) {
		caret(error);
		System.out.println("Error: Missing ELSE Statement" + " at position " + error);
		System.exit(1);
		
	}
	public void missThen(int error) {
		caret(error);
		System.out.println("Error: Missing THEN Statement" + " at position " + error);
		System.exit(1);
		
	}
	public void missLoop(int error) {
		caret(error);
	
		System.out.println("Error: Missing LOOP Statement" + " at position " + error);
		System.exit(1);
		
	}
	public void missRParen(int error) {
		caret(error);
		System.out.println("Error: Missing RParen Statement" + " at position " + error);
		System.exit(1);
		
	}
	public void missLParen(int error) {
		caret(error);
		System.out.println("Error: Missing LParen Statement" );
		System.exit(1);
		
	}
	public void missBecomes(int error) {
		caret(error);
		System.out.println("Error: Missing Become " );
		System.exit(1);
		
	}
	
	private void caret(int error)
	{
		String point = " ";
		for (int i = 1; i < error; i++)
		{
			point = point + " "; 
			if (error == i + 1)
			{
				point = point + "^";
			}
		}
		System.out.println(point);
		
	}
	
	
	
}
