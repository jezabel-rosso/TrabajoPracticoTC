// src/main/java/ar/edu/ubp/compilador/ASTBuilder.java
package ar.edu.ubp.compilador;

import ar.edu.ubp.compilador.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends CPPSubsetParserBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(CPPSubsetParser.ProgramContext ctx) {
        CompoundNode program = new CompoundNode();
        for (ParseTree child : ctx.children) {
            ASTNode node = visit(child);
            if (node != null) program.addChild(node);
        }
        return program;
    }

    @Override
    public ASTNode visitFunctionDecl(CPPSubsetParser.FunctionDeclContext ctx) {
        String retType = ctx.type().getText();
        String name    = ctx.ID().getText();

        List<String> paramTypes = new ArrayList<>();
        List<String> paramNames = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (CPPSubsetParser.ParamContext p : ctx.paramList().param()) {
                paramTypes.add(p.type().getText());
                paramNames.add(p.ID().getText());
            }
        }

        CompoundNode body = (CompoundNode) visit(ctx.block());
        return new FunctionDeclNode(retType, name, paramTypes, paramNames, body);
    }

    @Override
    public ASTNode visitVariableDecl(CPPSubsetParser.VariableDeclContext ctx) {
        return new VariableDeclNode(
            ctx.type().getText(),
            ctx.ID().getText(),
            ctx.expr() != null ? visit(ctx.expr()) : null
        );
    }

    @Override
    public ASTNode visitAssignment(CPPSubsetParser.AssignmentContext ctx) {
        return new AssignmentNode(ctx.ID().getText(), visit(ctx.expr()));
    }

    @Override
    public ASTNode visitReturnStatement(CPPSubsetParser.ReturnStatementContext ctx) {
        return new ReturnNode(ctx.expr() != null ? visit(ctx.expr()) : null);
    }

    @Override
    public ASTNode visitIfStatement(CPPSubsetParser.IfStatementContext ctx) {
        ASTNode cond = visit(ctx.expr());
        CompoundNode thenB = (CompoundNode) visit(ctx.block(0));
        CompoundNode elseB = ctx.block().size() > 1
            ? (CompoundNode) visit(ctx.block(1))
            : null;
        return new IfNode(cond, thenB, elseB);
    }

    @Override
    public ASTNode visitForLoop(CPPSubsetParser.ForLoopContext ctx) {
        ASTNode init = (ctx.variableDecl() != null)
            ? visit(ctx.variableDecl())
            : visit(ctx.assignment(0));
        ASTNode cond   = visit(ctx.expr());
        ASTNode update = visit(ctx.assignment(ctx.assignment().size() - 1));
        CompoundNode body = (CompoundNode) visit(ctx.block());
        return new ForNode(init, cond, update, body);
    }

    @Override
    public ASTNode visitWhileLoop(CPPSubsetParser.WhileLoopContext ctx) {
        // Si no tienes WhileNode, simplemente lo traduces a un CompoundNode
        ASTNode cond = visit(ctx.expr());
        CompoundNode body = (CompoundNode) visit(ctx.block());
        CompoundNode loop = new CompoundNode();
        loop.addChild(cond);
        loop.addChild(body);
        return loop;
    }

    @Override
    public ASTNode visitBlock(CPPSubsetParser.BlockContext ctx) {
        CompoundNode block = new CompoundNode();
        for (CPPSubsetParser.StatementContext stmt : ctx.statement()) {
            ASTNode node = visit(stmt);
            if (node != null) block.addChild(node);
        }
        return block;
    }

    @Override
    public ASTNode visitStatement(CPPSubsetParser.StatementContext ctx) {
        if (ctx.variableDecl() != null)   return visit(ctx.variableDecl());
        if (ctx.assignment() != null)     return visit(ctx.assignment());
        if (ctx.ifStatement() != null)    return visit(ctx.ifStatement());
        if (ctx.loopStatement() != null)  return visit(ctx.loopStatement());
        if (ctx.returnStatement() != null) return visit(ctx.returnStatement());
        if (ctx.block() != null)          return visit(ctx.block());
        return null;
    }

    // —— Expr etiquetados —— //

    @Override
    public ASTNode visitIntLiteralExpr(CPPSubsetParser.IntLiteralExprContext ctx) {
        return new LiteralNode(ctx.INTLIT().getText());
    }

    @Override
    public ASTNode visitDoubleLiteralExpr(CPPSubsetParser.DoubleLiteralExprContext ctx) {
        return new LiteralNode(ctx.DOUBLELIT().getText());
    }

    @Override
    public ASTNode visitCharLiteralExpr(CPPSubsetParser.CharLiteralExprContext ctx) {
        return new LiteralNode(ctx.CHARLIT().getText());
    }

    @Override
    public ASTNode visitIdExpr(CPPSubsetParser.IdExprContext ctx) {
        return new LiteralNode(ctx.ID().getText());
    }

    @Override
    public ASTNode visitParenExpr(CPPSubsetParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ASTNode visitFunctionCallExpr(CPPSubsetParser.FunctionCallExprContext ctx) {
        String fn = ctx.ID().getText();
        List<ASTNode> args = ctx.expr().stream().map(this::visit).toList();
        return new FunctionCallNode(fn, args);
    }

    @Override
    public ASTNode visitMulDivExpr(CPPSubsetParser.MulDivExprContext ctx) {
        ASTNode L = visit(ctx.left), R = visit(ctx.right);
        return new BinaryExprNode(ctx.op.getText(), L, R);
    }

    @Override
    public ASTNode visitAddSubExpr(CPPSubsetParser.AddSubExprContext ctx) {
        ASTNode L = visit(ctx.left), R = visit(ctx.right);
        return new BinaryExprNode(ctx.op.getText(), L, R);
    }

    @Override
    public ASTNode visitCompExpr(CPPSubsetParser.CompExprContext ctx) {
        ASTNode L = visit(ctx.left), R = visit(ctx.right);
        return new BinaryExprNode(ctx.op.getText(), L, R);
    }

    @Override
    public ASTNode visitAndExpr(CPPSubsetParser.AndExprContext ctx) {
        ASTNode L = visit(ctx.left), R = visit(ctx.right);
        return new BinaryExprNode(ctx.op.getText(), L, R);
    }

    @Override
    public ASTNode visitOrExpr(CPPSubsetParser.OrExprContext ctx) {
        ASTNode L = visit(ctx.left), R = visit(ctx.right);
        return new BinaryExprNode(ctx.op.getText(), L, R);
    }
}
