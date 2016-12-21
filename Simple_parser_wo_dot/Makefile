all: ANTLRDemo.class

run: ANTLRDemo.class
	java -cp .:antlr-3.5.2-complete.jar ANTLRDemo

ANTLRDemo.class: ANTLRDemo.java
	javac -cp .:antlr-3.5.2-complete.jar ANTLRDemo.java

clean:
	rm *.class

veryclean:
	rm *.class *.dot *.pdf

generate:
	java -cp antlr-3.5.2-complete.jar org.antlr.Tool Exp.g 
