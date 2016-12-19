import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import org.antlr.runtime.*;

import antlr.ParseTree;

public class ANTLRDemo {
	
    public static void main(String[] args) throws Exception {
    	FileReader fr = new FileReader("/home/a502038/Workspaces/workspace_java/ANTLRDemo/src/metadata_simple.txt");
    	BufferedReader br = new BufferedReader(fr);
    	String metadata = "";
    	String sCurrentLine;
    	while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
    			metadata = metadata.concat(sCurrentLine + "\n");
    			// metadata = metadata.concat(sCurrentLine);
			}
    	
    	// ANTLRStringStream in = new ANTLRStringStream("12*(5-6)");
    	//
    	//Reader metadataTextInput = new StringReader(metadata);
    	//ANTLRStringStream in = new ANTLRStringStream(metadataTextInput.toString());
    	ANTLRStringStream in = new ANTLRStringStream(metadata);    	
        CTFLexer lexer = new CTFLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CTFParser parser = new CTFParser(tokens);
        
//        ParseTree tree = parser.; 
//
//        //show AST in console
//        System.out.println(tree.toStringTree(parser));
        
        parser.parse();
        
        br.close();
    }
}

