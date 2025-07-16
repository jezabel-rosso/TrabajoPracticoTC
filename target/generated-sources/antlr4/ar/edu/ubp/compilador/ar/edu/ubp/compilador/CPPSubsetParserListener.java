// Generated from ar/edu/ubp/compilador/CPPSubsetParser.g4 by ANTLR 4.13.2
package ar.edu.ubp.compilador;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPPSubsetParser}.
 */
public interface CPPSubsetParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CPPSubsetParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CPPSubsetParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(CPPSubsetParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(CPPSubsetParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(CPPSubsetParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(CPPSubsetParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(CPPSubsetParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(CPPSubsetParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void enterVariableDecl(CPPSubsetParser.VariableDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void exitVariableDecl(CPPSubsetParser.VariableDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(CPPSubsetParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(CPPSubsetParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CPPSubsetParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CPPSubsetParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CPPSubsetParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CPPSubsetParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CPPSubsetParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CPPSubsetParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(CPPSubsetParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(CPPSubsetParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(CPPSubsetParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(CPPSubsetParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(CPPSubsetParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(CPPSubsetParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(CPPSubsetParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(CPPSubsetParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(CPPSubsetParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(CPPSubsetParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleLiteralExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteralExpr(CPPSubsetParser.DoubleLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleLiteralExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteralExpr(CPPSubsetParser.DoubleLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(CPPSubsetParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(CPPSubsetParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intLiteralExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteralExpr(CPPSubsetParser.IntLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intLiteralExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteralExpr(CPPSubsetParser.IntLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(CPPSubsetParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(CPPSubsetParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpr(CPPSubsetParser.FunctionCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpr(CPPSubsetParser.FunctionCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(CPPSubsetParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(CPPSubsetParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charLiteralExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharLiteralExpr(CPPSubsetParser.CharLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charLiteralExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharLiteralExpr(CPPSubsetParser.CharLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(CPPSubsetParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(CPPSubsetParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(CPPSubsetParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(CPPSubsetParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(CPPSubsetParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(CPPSubsetParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(CPPSubsetParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(CPPSubsetParser.AndExprContext ctx);
}