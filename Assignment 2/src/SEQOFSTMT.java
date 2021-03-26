
public class SEQOFSTMT {
	
	private LexicalAnalyzer data;
	private Token nextToken;
	public SEQOFSTMT(LexicalAnalyzer data) {
		this.data = data;
		
	}

	public Token SEQOFSTMT(Token nextToken) {
		
		while(nextToken.getSym() == Symbol.NULLSYM || nextToken.getSym() == Symbol.IDENT ||
			  nextToken.getSym() == Symbol.IFSYM ||  nextToken.getSym() == Symbol.WHILESYM ||
			  nextToken.getSym() == Symbol.GETSYM || nextToken.getSym() == Symbol.PUTSYM ||
			   nextToken.getSym() == Symbol.NEWLINE)
		{
			Stmt(nextToken);
		}
		//System.out.println(nextToken);
		return nextToken;
	}
	

	private void Stmt(Token nextToken) {
		Error err = new Error();
		
		
		Condition con = new Condition(data);
		
		//Token nextToken = data.getToken();
	//	System.out.println(nextToken);
		if (nextToken.getSym() == Symbol.NULLSYM)
			{
			nextToken = data.getToken();
			if (nextToken.getSym() != Symbol.SEMICOLON)
				err.missSemicolon(data.getCurrCharPosNum());
			nextToken = data.getToken();
			}
		else if (nextToken.getSym() == Symbol.IDENT) {
			nextToken = data.getToken();
			if (nextToken.getSym() == Symbol.BECOMES) {
				nextToken = data.getToken();
				con.Expression(nextToken);
				nextToken = data.getToken();
				
			}
		}
			
		else if(nextToken.getSym() == Symbol.IFSYM)
		{
			nextToken = data.getToken();
			//System.out.println(nextToken);
			 nextToken = con.Condi(nextToken);
			 
			 //System.out.println(nextToken);
			if(nextToken.getSym() == Symbol.THENSYM) {
				nextToken = data.getToken();
				SEQOFSTMT(nextToken);
				
			
				if(nextToken.getSym() == Symbol.ELSESYM)
				{
					nextToken = data.getToken();
					SEQOFSTMT(nextToken);
				}	
				if(nextToken.getSym() == Symbol.ENDSYM)
				{
					nextToken = data.getToken();
					if(nextToken.getSym() == Symbol.IFSYM)
					{
						nextToken = data.getToken();
						if(nextToken.getSym()!= Symbol.SEMICOLON)
							err.missSemicolon(data.getCurrCharPosNum());	
					}	
					else
						err.missIF(data.getCurrCharPosNum());
				}
				else
					err.missEnd(data.getCurrCharPosNum());	
			}
			else 
				err.missThen(data.getCurrCharPosNum());
				
			//need to check
	       }
		else if(nextToken.getSym() == Symbol.WHILESYM)
			{
				nextToken = data.getToken();
				con.Condi(nextToken);	
				
				nextToken = data.getToken();	
			
				
				if (nextToken.getSym() == Symbol.LOOPSYM)
				{
					nextToken = data.getToken();
					 SEQOFSTMT(nextToken);
					if (nextToken.getSym() == Symbol.ENDSYM)
					{
						nextToken = data.getToken();
						if (nextToken.getSym() == Symbol.LOOPSYM)
						{
							
							nextToken = data.getToken();
							if (nextToken.getSym() == Symbol.SEMICOLON) 
								nextToken = data.getToken();
							else	
								err.missSemicolon(data.getCurrCharPosNum());
						}
						else 
							err.missLoop(data.getCurrCharPosNum());
					}
					else
						err.missEnd(data.getCurrCharPosNum());	
				}
				else 
					err.missLoop(data.getCurrCharPosNum());
			}
		    else if (nextToken.getSym() == Symbol.PUTSYM || nextToken.getSym() == Symbol.GETSYM)
		    {
		    	nextToken = data.getToken();
		    	if (nextToken.getSym() == Symbol.LPAREN) {
		    		nextToken = data.getToken();
			    		if(nextToken.getSym() == Symbol.IDENT )
						{
							nextToken = data.getToken();
							while(nextToken.getSym() == Symbol.COMMA)
							{
								nextToken = data.getToken();
								if(nextToken.getSym() == Symbol.IDENT )
								{
									nextToken = data.getToken();
								}
								else
								{
									err.missIdent(data.getCurrCharPosNum());
								}
							}
						}
		    			if(nextToken.getSym() == Symbol.RPAREN) {
		    				
		    				nextToken = data.getToken();
		    				if (nextToken.getSym() != Symbol.SEMICOLON)
								err.missSemicolon(data.getCurrCharPosNum());
		    			}
		    			else
		    				err.missRParen(data.getCurrCharPosNum());
		    		
		    	}
		    	else
		    		err.missLParen(data.getCurrCharPosNum());	
		    }
		    else if(nextToken.getSym() == Symbol.NEWLINE)
		    {
		    	nextToken = data.getToken();
		    	
		    	if (nextToken.getSym() != Symbol.SEMICOLON)
					err.missSemicolon(data.getCurrCharPosNum());	
		    	nextToken = data.getToken();
		    }
		    
			
			
		
	}
}