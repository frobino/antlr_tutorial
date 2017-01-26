# Order: wget, generate, compile, show

curr_dir = $(shell pwd)
full_dir = $(foreach dir,$(curr_dir),".:$(dir)/antlr-4.6-complete.jar")
grammarName = ModeTags
grammarMethod = file
inFile = test.xml

wget:
	wget http://www.antlr.org/download/antlr-4.6-complete.jar

generate:
	java -cp antlr-4.6-complete.jar org.antlr.v4.Tool *.g4

compile:
	javac -cp .:antlr-4.6-complete.jar ${grammarName}*.java

setupenv:
	$(info $$curr_dir is [${curr_dir}])	
	$(info $$full_dir is [${full_dir}])
	export CLASSPATH=${full_dir}
	#setenv CLASSPATH ${full_dir}
show:
	java -cp .:antlr-4.6-complete.jar org.antlr.v4.gui.TestRig ${grammarName} ${grammarMethod} -token ${inFile} -gui

clean:
	rm ${grammarName}*.java *.class *.tokens
