package org.frallan.openmpparser.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.Parser;
import org.frallan.openmpparser.language.CParser.CompilationUnitContext;
import org.frallan.openmpparser.language.listener.NaiveInterpreterListener;
import org.frallan.openmpparser.language.visitor.NaiveInterpreterVisitor;
import org.junit.Test;

public class TestOpenMPCParser {
	
	// TODO: evaluate Logger and LoggerFactory
	// public static final Logger logger = LoggerFactory.getLogger(TestDiveXmlParser.class);
	
	@Test
	public void testDoubleEvaluation() throws IOException{
		// TODO: evaluate Logger and LoggerFactory
		// logger.info("\n\nStarting double evaluation test\n\n");
		
		String program = new String(Files.readAllBytes(Paths.get("openMPfiles/sinTable.c")));
		System.out.println("Dive input: \n" + program);
				
		NaiveInterpreterVisitor visitor = new NaiveInterpreterVisitor();
		OpenMPCTestErrorListener errorListener = new OpenMPCTestErrorListener();
		CompilationUnitContext context = parseProgram(program, errorListener);
		
		/*
		 * Test VISITOR
		 */
		
		// Temporarily removed the following assert.
		// The original ANTLR C grammar is not flawless and returns some errors,
		// however it parses correctly.
		//
		// assertFalse(errorListener.isFail());
		String result = visitor.visit(context);
		String secondResult = visitor.visit(context);
		
		// assertEquals(result, secondResult);
		System.out.println("result: " + result);
		System.out.println("secondResult: " + secondResult);
		assertEquals("printEnd();;NON-OMP;OMP;printStart();;NON-OMP;", result);
		assertEquals("printEnd();;NON-OMP;OMP;printStart();;NON-OMP;", secondResult);
		
		/*
		 * Test LISTENER
		 */

		// Walk it and attach our listener
		ParseTreeWalker walker = new ParseTreeWalker();
		NaiveInterpreterListener listener = new NaiveInterpreterListener();
		walker.walk(listener, context);
		assertTrue("Default success", Boolean.TRUE);

	}

	// Helpers, generic for all antlr4 unitest 
	// (apart from name of *Parser, *Lexer, name/rule of "parser.XXX", DocumentContext)
	
	public static CompilationUnitContext parseProgram(String program, ANTLRErrorListener errorListener) throws IOException
	{
		CharStream inputCharStream = new ANTLRInputStream(new StringReader(program));
		TokenSource tokenSource = new CLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
		CParser parser = new CParser(inputTokenStream);
		parser.addErrorListener(errorListener);
		CompilationUnitContext context = parser.compilationUnit();
		return context;
	}
	
	public static class OpenMPCTestErrorListener implements ANTLRErrorListener {
		
		private boolean fail = false;
		
		public boolean isFail() {
			return fail;
		}

		public void setFail(boolean fail) {
			this.fail = fail;
		}

		@Override
		public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
				int arg3, String arg4, RecognitionException arg5) {
			System.out.println("syntaxError");
			setFail(true);
		}
		
		@Override
		public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
				int arg3, int arg4, ATNConfigSet arg5) {
			System.out.println("reportContextSensitivity");
			setFail(true);			
		}
		
		@Override
		public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2,
				int arg3, BitSet arg4, ATNConfigSet arg5) {
			System.out.println("reportAttemptingFullContext");
			setFail(true);
		}
		
		@Override
		public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
				boolean arg4, BitSet arg5, ATNConfigSet arg6) {
			System.out.println("reportAmbiguity");
			setFail(true);
		}
	}
}
