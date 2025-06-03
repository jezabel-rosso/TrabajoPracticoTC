// Generated from CPPSubsetParser.g4 by ANTLR 4.13.2
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
	 * Enter a parse tree produced by {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CPPSubsetParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CPPSubsetParser.ExprContext ctx);
}