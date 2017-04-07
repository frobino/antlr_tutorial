package org.frallan.openmpparser.language.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.frallan.openmpparser.language.XMLParserVisitor;
import org.frallan.openmpparser.language.XMLParser.AttributeContext;
import org.frallan.openmpparser.language.XMLParser.ChardataContext;
import org.frallan.openmpparser.language.XMLParser.ContentContext;
import org.frallan.openmpparser.language.XMLParser.DocumentContext;
import org.frallan.openmpparser.language.XMLParser.ElementContext;
import org.frallan.openmpparser.language.XMLParser.MiscContext;
import org.frallan.openmpparser.language.XMLParser.PrologContext;
import org.frallan.openmpparser.language.XMLParser.ReferenceContext;

public class NaiveInterpreterVisitor implements XMLParserVisitor<String>{

	// TODO: many methods return null, so the test will pass...
	//		 To be fixed with a better solution
	
	// efroroo: above here the reference examples includes 
	// 			other methods and private classes
	
	// Document -> element
	// 			-> content -> element
	//			-> content -> element (-> attribute "BeginAtLabel")
	//			-> content -> browse all elements
	
	@Override
	public String visit(ParseTree arg0) {
		return arg0.accept(this);
	}

	@Override
	public String visitDocument(DocumentContext ctx) {
		// TODO Main parse rule 
		//      ctx."prolog", "misc" or "element"?
		//
		// We are interested in visiting the element only!
		
		return ctx.element().accept(this);
		// return null;
	}
	
	@Override
	public String visitElement(ElementContext ctx) {
		// TODO: with the current impl we reach here from 
		//		 visitDocument
		
		boolean foundBegin = false;
		
		// An Element can contain many Attributes, but only one Content
		for (AttributeContext s : ctx.attribute() ) {
			
			// Read the Attribute name (index 0) of the Element
			String elementUnderAttr = s.getChild(0).getText();
			System.out.println("ELEMENT list under-Attribute: " + elementUnderAttr);
			
			if (elementUnderAttr.equals("beginAtLabel")){
				
				System.out.println("FOUND: found under-Attribute beginAtLabel");
				foundBegin = true;
				
				// TODO: horrific break, change.
				// Once the beginAtLabel is found, we break the for loop
				break;
			}
			
			// TODO: remove this later on, otherwise we jump to attribute
			// s.accept(this);
		}
		
		// If we did not find a "beginAtLabel", we want to visit the following content
		if (foundBegin == false){
			// DANGER: not all elements have a context! 
			// So we first check that a context exists, 
			// in that case we continue accepting the content.
			if (ctx.content() != null){
				ctx.content().accept(this);
			}
		} else {
			// TODO: here we enter only after the break... is it safe?
			// Here we enter only of we have found the beginAtLabel
			// -> content -> browse all elements
			for (ElementContext s : ctx.content().element() ) {
				
				// Print out the attribute
				System.out.println("AFTER FOUND: element sub-attribute " + s.attribute(0).getChild(2).getText());
				
			}
		}
		
		// return ctx.attribute(0).accept(this);
		return null;
	}

	@Override
	public String visitContent(ContentContext ctx) {
		// TODO With the current impl we reach here from visitElement
		
		System.out.println("CONTENT: I'm in");
		
		// A Content can contain many Elements
		// TODO: this loop does not return... smthing wrong
		for (ElementContext s : ctx.element()){
			// efroroo: not all elements have attributes! This will crash sometimes
			// System.out.println("CONTENT: sub-elemement attr(0): " + s.attribute(0).getText());
			s.accept(this);
		}
		
		return null;
	}	

	@Override
	public String visitAttribute(AttributeContext ctx) {
		// TODO Auto-generated method stub
		
		String attributeEl0 = ctx.getChild(0).getText();
		
		if (attributeEl0 == "name"){
			System.out.println("ATTRIBUTE: " + attributeEl0);
		} else {
			System.out.println("UNEXPECTED ATTRIBUTE: " + attributeEl0);
		}
		
		return null;
	}
	
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	@Override
	public String visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitReference(ReferenceContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitChardata(ChardataContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitProlog(PrologContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitMisc(MiscContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
