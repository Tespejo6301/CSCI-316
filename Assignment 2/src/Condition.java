
public class Condition {
	private LexicalAnalyzer data;
	private Token nextToken;
	public Condition(LexicalAnalyzer data) {
		this.data = data;
	}
	public Token Condi() {
		nextToken =  Expression(nextToken);
		System.out.println(nextToken);
		return nextToken;
	}
	public Token Expression(Token nextToken) {
		
		
		Simpexpr(nextToken);
		
		    // nextToken = this.nextToken;
			if(nextToken.getSym() == Symbol.EQL||nextToken.getSym() == Symbol.NEQ 
					|| nextToken.getSym() == Symbol.LSS ||nextToken.getSym() == Symbol.LEQ 
					||nextToken.getSym() == Symbol.GTR ||nextToken.getSym() == Symbol.GEQ)
			{
				nextToken = data.getToken();
				Simpexpr(nextToken);
		        
		    }
			System.out.println(nextToken);
			return nextToken;
	}
			
			public void Simpexpr(Token nextToken) 
			{
				TERM(nextToken);
				 
				while (nextToken.getSym() == Symbol.IDENT)
				{
					this.nextToken = data.getToken();
					if(nextToken.getSym() == Symbol.PLUS || nextToken.getSym() == Symbol.MINUS)
					{
						nextToken = data.getToken();
						TERM(nextToken);
						
					}
				}
	
			}
			
			private void TERM(Token nextToken) {
				
				//this.nextToken = data.getToken();
				primary(nextToken);
				while((nextToken.getSym() == Symbol.TIMES
						||nextToken.getSym() == Symbol.SLASH 
						|| nextToken.getSym() == Symbol.REMSYM) )
				{
					//System.out.println(nextToken);
					nextToken = data.getToken();
					primary(nextToken); 
					
			    }
				

			}
			private void primary(Token nextToken) {
				Error err = new Error();
				System.out.println(nextToken);
				if (nextToken.getSym() == Symbol.LPAREN) 
				{
					nextToken = data.getToken();
					Expression(nextToken);
					if( nextToken.getSym() != Symbol.RPAREN)
					{
						err.missRParen(data.getCurrCharPosNum());
					}
					nextToken = data.getToken();
				}
				else if(nextToken.getSym() == Symbol.IDENT 
						|| nextToken.getSym() == Symbol.NUMLIT
						|| nextToken.getSym() == Symbol.TRUESYM
						|| nextToken.getSym() == Symbol.FALSESYM)
				{	
					 nextToken = data.getToken();
				}
				
			}
		    
		
		
		
	
	
}