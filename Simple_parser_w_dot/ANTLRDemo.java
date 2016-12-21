import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.stringtemplate.*;

public class ANTLRDemo {
    public static void main(String[] args) throws Exception {
        ANTLRStringStream in = new ANTLRStringStream("12 * (5 - 6); 2^3^(4 + 1);");
        ExpLexer lexer = new ExpLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpParser parser = new ExpParser(tokens);
        ExpParser.parse_return returnValue = parser.parse();
        CommonTree tree = (CommonTree)returnValue.getTree();
        DOTTreeGenerator gen = new DOTTreeGenerator();
        StringTemplate st = gen.toDOT(tree);
        System.out.println(st);
    }
}
