package org.frallan.openmpparser.language.listener;

import org.frallan.openmpparser.language.CBaseListener;
import org.frallan.openmpparser.language.CParser.CompoundStatementContext;
import org.frallan.openmpparser.language.CParser.FunctionDefinitionContext;

public class NaiveInterpreterListener extends CBaseListener {

	@Override
	public void enterCompoundStatement(CompoundStatementContext ctx) {
		System.out.println("Listener " + ctx.depth() + ": " + ctx.getText());

		/*
		 * Identify compoundStatement of the main function
		 */
		if (ctx.getParent().getClass() == FunctionDefinitionContext.class) {
			if (ctx.getParent().getChild(1).getChild(0).getChild(0).getText().equals("main")) {
				System.out.println("Listener " + ctx.depth() + ": is MAIN");
			}
		}

	}

}
