all: ANTLRDemo.class

run: ANTLRDemo.class
	java -cp .:antlr-3.5.2-complete.jar ANTLRDemo > ast-tree.dot

show:
	dot -Tpdf ast-tree.dot -o ast-tree.pdf
	evince ast-tree.pdf

ANTLRDemo.class: ANTLRDemo.java
	javac -cp .:antlr-3.5.2-complete.jar ANTLRDemo.java

clean:
	rm *.class

veryclean:
	rm *.class *.dot *.pdf

# java -cp antlr-3.5.2-complete.jar org.antlr.Tool CTFLexer.g CTFParser.g 
