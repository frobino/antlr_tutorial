grammar Exp;

options { 
  output=AST; 
}

tokens {
  ROOT;
  EXPRESSION;
}

/*
 * PARSER RULES (start with lowercase letter)
 */

/* This will be the entry point of our parser. */
/* NOTE: this is also the method name called in ANTRLDemo.java to start the parsing:
   ExpParser.parse_return returnValue = parser.parse(); */
parse
  :  (expression ';')+ -> ^(ROOT expression+) // omit the semi-colon
  ;

expression
  :  addExp -> ^(EXPRESSION addExp)
  ;

addExp
  :  multExp
     ( '+'^ multExp
     | '-'^ multExp
     )*
  ;

multExp
  :  powerExp
     ( '*'^ powerExp
     | '/'^ powerExp
     )*
  ;

powerExp
  :  atom ('^'^ atom)*
  ;

atom
  :  Number
  |  '(' expression ')' -> expression // omit the parenthesis
  ;

/*
 * LEXER RULES (start with CAPITAL letter)
 */

/* "fragment" keyword:
   Rules prefixed with fragment can be called only from other lexer rules; 
   they are not tokens in their own right*/
fragment
Digit
  :  '0'..'9'
  ;

Number
  :  Digit+ ('.' Digit+)?
  ;

Space
  :  (' ' | '\t' | '\r' | '\n') {skip();}
  ;
