import java.util.ArrayList;
import java.util.List;

public class IntermediateCodeGenerator extends CPPSubsetParserBaseVisitor<String> {
    private final List<String> code = new ArrayList<>();
    private int tempCount = 0;

    private String newTemp() {
        return "t" + (tempCount++);
    }

    public List<String> getCode() {
        return code;
    }

    @Override
    public String visitAssignment(CPPSubsetParser.AssignmentContext ctx) {
        String value = visit(ctx.expr());
        String id = ctx.ID().getText();
        code.add(id + " = " + value);
        return null;
    }

    @Override
    public String visitExpr(CPPSubsetParser.ExprContext ctx) {
        if (ctx.INTLIT() != null) {
            return ctx.INTLIT().getText();
        } else if (ctx.ID() != null) {
            return ctx.ID().getText();
        } else if (ctx.op != null && ctx.left != null && ctx.right != null) {
            String left = visit(ctx.left);
            String right = visit(ctx.right);
            String temp = newTemp();
            code.add(temp + " = " + left + " " + ctx.op.getText() + " " + right);
            return temp;
        }
        return null;
    }

    @Override
    public String visitReturnStatement(CPPSubsetParser.ReturnStatementContext ctx) {
        String value = visit(ctx.expr());
        code.add("return " + value);
        return null;
    }
}
