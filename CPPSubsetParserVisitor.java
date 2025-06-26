// Generated from CPPSubsetParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CPPSubsetParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CPPSubsetParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CPPSubsetParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(CPPSubsetParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(CPPSubsetParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(CPPSubsetParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#variableDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDecl(CPPSubsetParser.VariableDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(CPPSubsetParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(CPPSubsetParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CPPSubsetParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(CPPSubsetParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(CPPSubsetParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatement(CPPSubsetParser.LoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#forLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(CPPSubsetParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#whileLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(CPPSubsetParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(CPPSubsetParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CPPSubsetParser.ExprContext ctx);
}