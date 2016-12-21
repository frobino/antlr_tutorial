import java.io.BufferedReader;
import java.io.FileReader;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;

public class ANTLRDemo {
	
    public static void main(String[] args) throws Exception {
    	
    	// Create a String containing metadata to be parsed
    	FileReader fr = new FileReader("/home/a502038/Workspaces/workspace_java/ANTLRDemo/src/metadata_simple.txt");
    	BufferedReader br = new BufferedReader(fr);
    	String metadata = "";
    	String sCurrentLine;
    	while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
    			metadata = metadata.concat(sCurrentLine + "\n");
    			// metadata = metadata.concat(sCurrentLine);
			}
        br.close();
    	
    	// ANTLRStringStream in = new ANTLRStringStream("12*(5-6)");
    	//
    	// Reader metadataTextInput = new StringReader(metadata);
    	// ANTLRStringStream in = new ANTLRStringStream(metadataTextInput.toString());
    	ANTLRStringStream in = new ANTLRStringStream(metadata);    	
        CTFLexer lexer = new CTFLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CTFParser parser = new CTFParser(tokens);
        
        CTFParser.parse_return returnValue = parser.parse();
        CommonTree tree = (CommonTree)returnValue.getTree();
        DOTTreeGenerator gen = new DOTTreeGenerator();
        StringTemplate st = gen.toDOT(tree);
        System.out.println(st);
        
    }
}

