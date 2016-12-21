grammar Exp;

options { 
  output=AST; 
}

tokens {
  ROOT;
  EXPRESSION;
}

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

Number
  :  Digit+ ('.' Digit+)?
  ;

fragment
Digit
  :  '0'..'9'
  ;

Space
  :  (' ' | '\t' | '\r' | '\n') {skip();}
  ;
