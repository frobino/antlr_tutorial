package org.frallan.openmpparser.language.listener;

import org.frallan.openmpparser.language.CBaseListener;
import org.frallan.openmpparser.language.CParser.CompoundStatementContext;

public class NaiveInterpreterListener extends CBaseListener{

	@Override
	public void enterCompoundStatement(CompoundStatementContext ctx) {
		System.out.println("Listener: " + ctx.getText());
	}

}
