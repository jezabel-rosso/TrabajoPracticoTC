// src/main/java/ar/edu/ubp/compilador/IntermediateCodeGenerator.java
package ar.edu.ubp.compilador;

import ar.edu.ubp.compilador.ast.*;
import java.util.concurrent.atomic.AtomicInteger;

public class IntermediateCodeGenerator {
    private final StringBuilder out   = new StringBuilder();
    private final AtomicInteger tcount = new AtomicInteger();
    private final AtomicInteger lcount = new AtomicInteger();

    public String generate(ASTNode root) {
        out.setLength(0);
        visit(root);
        return out.toString();
    }

    private String newTemp()  { return "t" + tcount.getAndIncrement(); }
    private String newLabel() { return "L" + lcount.getAndIncrement(); }

    private void visit(ASTNode n) {
        if (n instanceof CompoundNode c) {
            for (ASTNode ch : c.getChildren()) visit(ch);
        }
        else if (n instanceof FunctionDeclNode f) {
            out.append("func ").append(f.getName()).append(":\n");
            for (String p : f.getParamNames()) {
                out.append("param ").append(p).append("\n");
            }
            visit(f.getBody());
            out.append("endfunc\n\n");
        }
        else if (n instanceof IfNode i) {
            String L1 = newLabel(), L2 = newLabel();
            String tc = eval(i.getCondition());
            out.append("if ").append(tc).append(" goto ").append(L1).append("\n");
            out.append("goto ").append(L2).append("\n");
            out.append(L1).append(":\n");
            visit(i.getThenBranch());
            if (i.getElseBranch() != null) {
                String L3 = newLabel();
                out.append("goto ").append(L3).append("\n");
                out.append(L2).append(":\n");
                visit(i.getElseBranch());
                out.append(L3).append(":\n");
            } else {
                out.append(L2).append(":\n");
            }
        }
        else if (n instanceof ForNode f) {
            String start = newLabel(),
                   body  = newLabel(),
                   upd   = newLabel(),
                   end   = newLabel();
            visit(f.getInit());
            out.append(start).append(":\n");
            String tc = eval(f.getCondition());
            out.append("if ").append(tc).append(" goto ").append(body).append("\n");
            out.append("goto ").append(end).append("\n");
            out.append(body).append(":\n");
            visit(f.getBody());
            out.append(upd).append(":\n");
            visit(f.getUpdate());
            out.append("goto ").append(start).append("\n");
            out.append(end).append(":\n");
        }
        else if (n instanceof VariableDeclNode d) {
            out.append("declare ")
               .append(d.getType()).append(" ")
               .append(d.getName()).append("\n");
        }
        else if (n instanceof AssignmentNode a) {
            String rv = eval(a.getValue());
            out.append(a.getName()).append(" = ").append(rv).append("\n");
        }
        else if (n instanceof ReturnNode r) {
            if (r.getValue() != null) {
                String rv = eval(r.getValue());
                out.append("return ").append(rv).append("\n");
            } else {
                out.append("return\n");
            }
        }
    }

    private String eval(ASTNode e) {
        if (e instanceof LiteralNode lit) {
            return lit.getValue();
        }
        if (e instanceof BinaryExprNode b) {
            String L = eval(b.getLeft()), R = eval(b.getRight());
            String tmp = newTemp();
            out.append(tmp)
               .append(" = ").append(L)
               .append(" ").append(b.getOp())
               .append(" ").append(R).append("\n");
            return tmp;
        }
        if (e instanceof FunctionCallNode c) {
            for (ASTNode arg : c.getArgs()) {
                String av = eval(arg);
                out.append("param ").append(av).append("\n");
            }
            String tmp = newTemp();
            out.append(tmp)
               .append(" = call ").append(c.getFunctionName()).append("\n");
            return tmp;
        }
        return "";
    }
}
