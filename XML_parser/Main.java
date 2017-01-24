import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.tool.DOTGenerator;

// import org.antlr.runtime.*;
// import org.antlr.runtime.tree.*;

//import org.antlr.runtime.*;
import java.io.FileReader;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.io.BufferedReader;


public class Main {

  public static void main(String[] args) throws Exception {

      FileReader fr = new FileReader("/home/a502038/Workspaces/workspace_java/xml2/src/SrsActivator.dive");
      BufferedReader br = new BufferedReader(fr);
      String input = "";
      String sCurrentLine;
      while ((sCurrentLine = br.readLine()) != null) {
	 //  System.out.println(sCurrentLine);
	  input = input.concat(sCurrentLine + "\n");
      }
      br.close();

      // Get our lexer
      XMLLexer lexer = new XMLLexer(new ANTLRInputStream(input));

      // Get a list of matched tokens
      CommonTokenStream tokens = new CommonTokenStream(lexer);

      // Pass the tokens to the parser
      XMLParser parser = new XMLParser(tokens);

      // Specify our entry point
      XMLParser.DocumentContext documentContext = parser.document();
      
      // TODO: check the following block
      // Walk it and attach our listener (NOTE: needed?)
      ParseTreeWalker walker = new ParseTreeWalker();
      XMLParserListener listener = new XMLParserBaseListener();
      walker.walk(listener, documentContext);
      
      // Show CST/AST in text LISP format
      System.out.println(documentContext.toStringTree());
      
      // Show AST in GUI #1, source: http://stackoverflow.com/questions/23809005/how-to-display-antlr-tree-gui
      // Problem: no automatic zooming, etc.
      JFrame frame = new JFrame("Antlr AST");
      JPanel panel = new JPanel();
      TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()),documentContext);
      viewr.setScale(0.5);//scale a little
      panel.add(viewr);
      frame.add(panel);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800,800);
      frame.setVisible(true);
      
      // Show AST in GUI #2, source http://stackoverflow.com/questions/34832518/antlr4-dotgenerator-example
      JPanel panel2 = new JPanel();
	  TreeViewer viewr2 = new TreeViewer(Arrays.asList(parser.getRuleNames()),documentContext);
      viewr2.setScale(1);
	  panel2.add(viewr2);
      JOptionPane.showConfirmDialog(null, panel2, "ParseTree", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      
      // TODO: try to implement the method #3! Not clear how to do, 
      // Show AST in GUI #3, generate DOT representation
      //
      // The following show how we were generating DOT in Antrl3, using the DOTTreeGenerator and StringTemplate
      //
      // DOTTreeGenerator gen = new DOTTreeGenerator();
      // StringTemplate st = gen.toDOT(tree);
      // System.out.println(gen.toDOT(documentContext)));
      //
      // In Antlr4 there is a new DOTGenerator class that should be used in these cases, but we have not found examples how to use it
      // http://www.antlr.org/api/JavaTool/org/antlr/v4/tool/DOTGenerator.html
      
      // Show AST GUI #4:
	  TreeViewer viewr3 = new TreeViewer(Arrays.asList(parser.getRuleNames()),documentContext);
      ImagePanel2 imagePanel2 = new ImagePanel2(viewr3);
      ImageZoom zoom = new ImageZoom(imagePanel2);
        
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.getContentPane().add(zoom.getUIPanel(), "North");
      f.getContentPane().add(new JScrollPane(panel));
      f.setSize(400,400);
      f.setLocation(200,200);
      f.setVisible(true);
      
      //XMLParser.parse_return returnValue = parser.parse();
      
      //CommonTree tree = (CommonTree)returnValue.getTree();
      // DOTTreeGenerator gen = new DOTTreeGenerator();
      // StringTemplate st = gen.toDOT(tree);
      // System.out.println(st);

      //TParser parser = new TParser(new CommonTokenStream(lexer));
      //ParseTree tree = parser.parse();
      //System.out.println(tree.toStringTree(parser));
  }
  
}


/*
private void printDrink(String drinkSentence) {
    // Get our lexer
    DrinkLexer lexer = new DrinkLexer(new ANTLRInputStream(drinkSentence));
 
    // Get a list of matched tokens
    CommonTokenStream tokens = new CommonTokenStream(lexer);
 
    // Pass the tokens to the parser
    DrinkParser parser = new DrinkParser(tokens);
 
    // Specify our entry point
    DrinkSentenceContext drinkSentenceContext = parser.drinkSentence();
 
    // Walk it and attach our listener
    ParseTreeWalker walker = new ParseTreeWalker();
    AntlrDrinkListener listener = new AntlrDrinkListener();
    walker.walk(listener, drinkSentenceContext);
}
*/
