/*
 * Trisha Espejo
 * DUE: 11/18/2020
 * Professor Svitak
 * CSCI 316
 */
public class SynParser {
	private LexicalAnalyzer data;
	private Token nextToken;
	public SynParser(LexicalAnalyzer data) {
		this.data = data;
		nextToken = data.getToken();
	}
	
	public void Start() {
		
		Error err = new Error();
		
		if (nextToken.getSym() ==  Symbol.PROCSYM)
		{
			nextToken = data.getToken();
			if (nextToken.getSym() == Symbol.IDENT)
			{
				nextToken = data.getToken();
				
				if (nextToken.getSym() == Symbol.ISSYM)
				{
					nextToken = data.getToken();
						decPart();
						
					if (nextToken.getSym() == Symbol.BEGINSYM)
					{
						
								nextToken = data.getToken();
								 SEQOFSTMT();
								
						
						if(nextToken.getSym() == Symbol.ENDSYM)
						{
							
							nextToken = data.getToken();
							 
							if(nextToken.getSym() == Symbol.SEMICOLON)
							{
								nextToken = data.getToken();
								
								if(nextToken.getSym() == Symbol.EOI)
									System.out.println("Program syntactically correct.");
								else
									err.notEnd(data.getCurrCharPosNum());	
							}
							else
								err.missSemicolon(data.getCurrCharPosNum());
						
						}
						else
							err.missEnd(data.getCurrCharPosNum());
					}
					else 
						err.missStart(data.getCurrCharPosNum());
				}
				else
					err.missIs(data.getCurrCharPosNum());
			}
			else
				err.missIdent(data.getCurrCharPosNum());		
		}
		else
			err.missProcess(data.getCurrCharPosNum());
	}
	
	
	private void decPart() {
		while (nextToken.getSym() == Symbol.IDENT)
			objectDec();
	}

	private void objectDec() {
			nextToken = data.getToken();
			Error err = new Error();
			while (nextToken.getSym() == Symbol.COMMA) {
		
				nextToken = data.getToken();
				if (nextToken.getSym() == Symbol.IDENT)
					nextToken = data.getToken();
				else 
					err.missIdent(data.getCurrCharPosNum());
				
			}
			if (nextToken.getSym() == Symbol.COLON) {
				nextToken = data.getToken();
				if (nextToken.getSym() == Symbol.BOOLSYM || nextToken.getSym() == Symbol.INTSYM) {
					nextToken = data.getToken();
					if (nextToken.getSym() == Symbol.SEMICOLON)
						nextToken = data.getToken();
					else {
					err.missSemicolon(data.getCurrCharPosNum());
				     }
			    } 
				else
			     err.missSymbol(data.getCurrCharPosNum());
		    }
			else {
				err.missColon(data.getCurrCharPosNum());
		    }
	}
	public void SEQOFSTMT() {
		
		while(nextToken.getSym() == Symbol.NULLSYM || nextToken.getSym() == Symbol.IDENT ||
			  nextToken.getSym() == Symbol.IFSYM ||  nextToken.getSym() == Symbol.WHILESYM ||
			  nextToken.getSym() == Symbol.GETSYM || nextToken.getSym() == Symbol.PUTSYM ||
			   nextToken.getSym() == Symbol.NEWLINE)
		{
			Stmt();
		}	
	}
	

	private void Stmt() {
		Error err = new Error();
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
				Expression();
				if (nextToken.getSym() == Symbol.SEMICOLON) {
					nextToken = data.getToken();
				}
				else
					err.missSemicolon(data.getCurrCharPosNum());
			}
			else
				err.missBecomes(data.getCurrCharPosNum());
		}
			
		else if(nextToken.getSym() == Symbol.IFSYM)
		{
			nextToken = data.getToken();
			 Condi();
			if(nextToken.getSym() == Symbol.THENSYM) {
				nextToken = data.getToken();
				SEQOFSTMT();
				if(nextToken.getSym() == Symbol.ELSESYM)
				{
					nextToken = data.getToken();
					SEQOFSTMT();
				}	
				if(nextToken.getSym() == Symbol.ENDSYM)
				{
					nextToken = data.getToken();
					if(nextToken.getSym() == Symbol.IFSYM)
					{
						nextToken = data.getToken();
						if(nextToken.getSym()== Symbol.SEMICOLON)
							nextToken = data.getToken();
						else
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
				
	       }
		else if(nextToken.getSym() == Symbol.WHILESYM)
			{
				nextToken = data.getToken();
				Condi();		
				if (nextToken.getSym() == Symbol.LOOPSYM)
				{
					nextToken = data.getToken();
					 SEQOFSTMT();
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
		    				if (nextToken.getSym() == Symbol.SEMICOLON)
		    					nextToken = data.getToken();
		    				else	
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
			
		public void Condi() {
			Expression();
			
		}
		public void Expression()
		{
			
			
			Simpexpr();
			
			   
				if(nextToken.getSym() == Symbol.EQL||nextToken.getSym() == Symbol.NEQ 
						|| nextToken.getSym() == Symbol.LSS ||nextToken.getSym() == Symbol.LEQ 
						||nextToken.getSym() == Symbol.GTR ||nextToken.getSym() == Symbol.GEQ)
				{
					nextToken = data.getToken();
					Simpexpr();
			        
			    }
				
	  }
				
			public void Simpexpr() 
			{
				TERM();
						 
				while (nextToken.getSym() == Symbol.PLUS || nextToken.getSym() == Symbol.MINUS)
				{
					nextToken = data.getToken();
					TERM();
								
							
				}
			}
				
			private void TERM()
			{
				primary();
				while((nextToken.getSym() == Symbol.TIMES ||nextToken.getSym() == Symbol.SLASH || nextToken.getSym() == Symbol.REMSYM))
				{
					nextToken = data.getToken();
					primary(); 	
				}
			}
				
				private void primary() 
				{
					Error err = new Error();
					
					if (nextToken.getSym() == Symbol.LPAREN) 
					{
						nextToken = data.getToken();
						Expression();
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
				
		



	
