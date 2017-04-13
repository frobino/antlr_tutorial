package org.frallan.openmpparser.language.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.frallan.openmpparser.language.CParser.AbstractDeclaratorContext;
import org.frallan.openmpparser.language.CParser.AdditiveExpressionContext;
import org.frallan.openmpparser.language.CParser.AlignmentSpecifierContext;
import org.frallan.openmpparser.language.CParser.AndExpressionContext;
import org.frallan.openmpparser.language.CParser.ArgumentExpressionListContext;
import org.frallan.openmpparser.language.CParser.AssignmentExpressionContext;
import org.frallan.openmpparser.language.CParser.AssignmentOperatorContext;
import org.frallan.openmpparser.language.CParser.AtomicTypeSpecifierContext;
import org.frallan.openmpparser.language.CParser.BlockItemContext;
import org.frallan.openmpparser.language.CParser.BlockItemListContext;
import org.frallan.openmpparser.language.CParser.CastExpressionContext;
import org.frallan.openmpparser.language.CParser.CompilationUnitContext;
import org.frallan.openmpparser.language.CParser.CompoundStatementContext;
import org.frallan.openmpparser.language.CParser.ConditionalExpressionContext;
import org.frallan.openmpparser.language.CParser.ConstantExpressionContext;
import org.frallan.openmpparser.language.CParser.DeclarationContext;
import org.frallan.openmpparser.language.CParser.DeclarationListContext;
import org.frallan.openmpparser.language.CParser.DeclarationSpecifierContext;
import org.frallan.openmpparser.language.CParser.DeclarationSpecifiers2Context;
import org.frallan.openmpparser.language.CParser.DeclarationSpecifiersContext;
import org.frallan.openmpparser.language.CParser.DeclaratorContext;
import org.frallan.openmpparser.language.CParser.DesignationContext;
import org.frallan.openmpparser.language.CParser.DesignatorContext;
import org.frallan.openmpparser.language.CParser.DesignatorListContext;
import org.frallan.openmpparser.language.CParser.DirectAbstractDeclaratorContext;
import org.frallan.openmpparser.language.CParser.DirectDeclaratorContext;
import org.frallan.openmpparser.language.CParser.EnumSpecifierContext;
import org.frallan.openmpparser.language.CParser.EnumerationConstantContext;
import org.frallan.openmpparser.language.CParser.EnumeratorContext;
import org.frallan.openmpparser.language.CParser.EnumeratorListContext;
import org.frallan.openmpparser.language.CParser.EqualityExpressionContext;
import org.frallan.openmpparser.language.CParser.ExclusiveOrExpressionContext;
import org.frallan.openmpparser.language.CParser.ExpressionContext;
import org.frallan.openmpparser.language.CParser.ExpressionStatementContext;
import org.frallan.openmpparser.language.CParser.ExternalDeclarationContext;
import org.frallan.openmpparser.language.CParser.FunctionDefinitionContext;
import org.frallan.openmpparser.language.CParser.FunctionSpecifierContext;
import org.frallan.openmpparser.language.CParser.GccAttributeContext;
import org.frallan.openmpparser.language.CParser.GccAttributeListContext;
import org.frallan.openmpparser.language.CParser.GccAttributeSpecifierContext;
import org.frallan.openmpparser.language.CParser.GccDeclaratorExtensionContext;
import org.frallan.openmpparser.language.CParser.GenericAssocListContext;
import org.frallan.openmpparser.language.CParser.GenericAssociationContext;
import org.frallan.openmpparser.language.CParser.GenericSelectionContext;
import org.frallan.openmpparser.language.CParser.IdentifierListContext;
import org.frallan.openmpparser.language.CParser.InclusiveOrExpressionContext;
import org.frallan.openmpparser.language.CParser.InitDeclaratorContext;
import org.frallan.openmpparser.language.CParser.InitDeclaratorListContext;
import org.frallan.openmpparser.language.CParser.InitializerContext;
import org.frallan.openmpparser.language.CParser.InitializerListContext;
import org.frallan.openmpparser.language.CParser.IterationStatementContext;
import org.frallan.openmpparser.language.CParser.JumpStatementContext;
import org.frallan.openmpparser.language.CParser.LabeledStatementContext;
import org.frallan.openmpparser.language.CParser.LogicalAndExpressionContext;
import org.frallan.openmpparser.language.CParser.LogicalOrExpressionContext;
import org.frallan.openmpparser.language.CParser.MultiplicativeExpressionContext;
import org.frallan.openmpparser.language.CParser.NestedParenthesesBlockContext;
import org.frallan.openmpparser.language.CParser.ParameterDeclarationContext;
import org.frallan.openmpparser.language.CParser.ParameterListContext;
import org.frallan.openmpparser.language.CParser.ParameterTypeListContext;
import org.frallan.openmpparser.language.CParser.PointerContext;
import org.frallan.openmpparser.language.CParser.PostfixExpressionContext;
import org.frallan.openmpparser.language.CParser.PrimaryExpressionContext;
import org.frallan.openmpparser.language.CParser.RelationalExpressionContext;
import org.frallan.openmpparser.language.CParser.SelectionStatementContext;
import org.frallan.openmpparser.language.CParser.ShiftExpressionContext;
import org.frallan.openmpparser.language.CParser.SpecifierQualifierListContext;
import org.frallan.openmpparser.language.CParser.StatementContext;
import org.frallan.openmpparser.language.CParser.StaticAssertDeclarationContext;
import org.frallan.openmpparser.language.CParser.StorageClassSpecifierContext;
import org.frallan.openmpparser.language.CParser.StructDeclarationContext;
import org.frallan.openmpparser.language.CParser.StructDeclarationListContext;
import org.frallan.openmpparser.language.CParser.StructDeclaratorContext;
import org.frallan.openmpparser.language.CParser.StructDeclaratorListContext;
import org.frallan.openmpparser.language.CParser.StructOrUnionContext;
import org.frallan.openmpparser.language.CParser.StructOrUnionSpecifierContext;
import org.frallan.openmpparser.language.CParser.TranslationUnitContext;
import org.frallan.openmpparser.language.CParser.TypeNameContext;
import org.frallan.openmpparser.language.CParser.TypeQualifierContext;
import org.frallan.openmpparser.language.CParser.TypeQualifierListContext;
import org.frallan.openmpparser.language.CParser.TypeSpecifierContext;
import org.frallan.openmpparser.language.CParser.TypedefNameContext;
import org.frallan.openmpparser.language.CParser.UnaryExpressionContext;
import org.frallan.openmpparser.language.CParser.UnaryOperatorContext;
import org.frallan.openmpparser.language.CVisitor;

public class NaiveInterpreterVisitor implements CVisitor<String>{

	@Override
	public String visit(ParseTree tree) {
		// TODO Auto-generated method stub
		tree.accept(this);
		return null;
	}

	@Override
	public String visitChildren(RuleNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitPrimaryExpression(PrimaryExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitGenericSelection(GenericSelectionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitGenericAssocList(GenericAssocListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitGenericAssociation(GenericAssociationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitPostfixExpression(PostfixExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitArgumentExpressionList(ArgumentExpressionListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnaryExpression(UnaryExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnaryOperator(UnaryOperatorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitCastExpression(CastExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitMultiplicativeExpression(MultiplicativeExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitAdditiveExpression(AdditiveExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitShiftExpression(ShiftExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitRelationalExpression(RelationalExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitEqualityExpression(EqualityExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitAndExpression(AndExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExclusiveOrExpression(ExclusiveOrExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitInclusiveOrExpression(InclusiveOrExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitLogicalAndExpression(LogicalAndExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitLogicalOrExpression(LogicalOrExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitConditionalExpression(ConditionalExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitAssignmentExpression(AssignmentExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitAssignmentOperator(AssignmentOperatorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpression(ExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitConstantExpression(ConstantExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclaration(DeclarationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclarationSpecifiers(DeclarationSpecifiersContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclarationSpecifiers2(DeclarationSpecifiers2Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclarationSpecifier(DeclarationSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitInitDeclaratorList(InitDeclaratorListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitInitDeclarator(InitDeclaratorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStorageClassSpecifier(StorageClassSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypeSpecifier(TypeSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStructOrUnionSpecifier(StructOrUnionSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStructOrUnion(StructOrUnionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStructDeclarationList(StructDeclarationListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStructDeclaration(StructDeclarationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitSpecifierQualifierList(SpecifierQualifierListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStructDeclaratorList(StructDeclaratorListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStructDeclarator(StructDeclaratorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitEnumSpecifier(EnumSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitEnumeratorList(EnumeratorListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitEnumerator(EnumeratorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitEnumerationConstant(EnumerationConstantContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitAtomicTypeSpecifier(AtomicTypeSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypeQualifier(TypeQualifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitFunctionSpecifier(FunctionSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitAlignmentSpecifier(AlignmentSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclarator(DeclaratorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDirectDeclarator(DirectDeclaratorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitGccDeclaratorExtension(GccDeclaratorExtensionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitGccAttributeSpecifier(GccAttributeSpecifierContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitGccAttributeList(GccAttributeListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitGccAttribute(GccAttributeContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNestedParenthesesBlock(NestedParenthesesBlockContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitPointer(PointerContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypeQualifierList(TypeQualifierListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitParameterTypeList(ParameterTypeListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitParameterList(ParameterListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitParameterDeclaration(ParameterDeclarationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitIdentifierList(IdentifierListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypeName(TypeNameContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitAbstractDeclarator(AbstractDeclaratorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDirectAbstractDeclarator(DirectAbstractDeclaratorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypedefName(TypedefNameContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitInitializer(InitializerContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitInitializerList(InitializerListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDesignation(DesignationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDesignatorList(DesignatorListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDesignator(DesignatorContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStaticAssertDeclaration(StaticAssertDeclarationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStatement(StatementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitLabeledStatement(LabeledStatementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitCompoundStatement(CompoundStatementContext ctx) {
		// goto blockItemList
		System.out.println("5");
		ctx.blockItemList().accept(this);
		return null;
	}

	@Override
	public String visitBlockItemList(BlockItemListContext ctx) {
		// 
		System.out.println("6");
		/*
		if (ctx.blockItem().statement().compoundStatement().isEmpty()){
			System.out.println("Found omp");
		} else {
			System.out.println("NOT found omp");
		}
		*/
		return null;
	}

	@Override
	public String visitBlockItem(BlockItemContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpressionStatement(ExpressionStatementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitSelectionStatement(SelectionStatementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitIterationStatement(IterationStatementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitJumpStatement(JumpStatementContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitCompilationUnit(CompilationUnitContext ctx) {
		// go to translationUnit
		System.out.println("1");
		ctx.translationUnit().accept(this);
		return null;
	}

	@Override
	public String visitTranslationUnit(TranslationUnitContext ctx) {
		// goto externalDeclaration
		System.out.println("2");
		ctx.externalDeclaration().accept(this);
		return null;
	}

	@Override
	public String visitExternalDeclaration(ExternalDeclarationContext ctx) {
		// goto functionDefinition (main)
		System.out.println("3");
		ctx.functionDefinition().accept(this);
		return null;
	}

	@Override
	public String visitFunctionDefinition(FunctionDefinitionContext ctx) {
		// goto compoundStatement - {...}
		System.out.println("4");
		ctx.compoundStatement().accept(this);
		return null;
	}

	@Override
	public String visitDeclarationList(DeclarationListContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
