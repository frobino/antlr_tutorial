all: Main.class

wget:
	wget http://www.antlr.org/download/antlr-4.6-complete.jar

run: Main.class
	java -cp .:antlr-4.6-complete.jar Main

Main.class: Main.java
	javac -cp .:antlr-4.6-complete.jar Main.java

clean:
	rm *.class

veryclean:
	rm *.class *.dot *.pdf *.tokens XMLLexer*.java XMLParser*.java *~

generate:
	java -cp antlr-4.6-complete.jar org.antlr.v4.Tool XMLLexer.g4 XMLParser.g4
