// src/main/java/ar/edu/ubp/compilador/CodeOptimizer.java
package ar.edu.ubp.compilador;

import java.util.*;
import java.util.regex.*;

public class CodeOptimizer {
    private final List<String> code;

    public CodeOptimizer(List<String> code) {
        this.code = code;
    }

    public List<String> optimize() {
        // (1) CONSTANT FOLDING
        List<String> pass1 = foldConstants(code);

        // (2) SIMPLIFICACIÓN DE IDENTIDADES
        List<String> pass2 = simplifyIdentities(pass1);

        // (3) ELIMINACIÓN DE TEMPORALES MUERTOS
        List<String> pass3 = eliminateDeadTemporals(pass2);

        // (4) PROPAGACIÓN DE COPIAS
        List<String> pass4 = propagateCopies(pass3);

        // (5) PEEPHOLE DE GOTOS
        List<String> pass5 = eliminateRedundantGotos(pass4);

        // (6) ELIMINAR ASIGNACIONES x = x
        List<String> pass6 = eliminateSelfAssigns(pass5);

        // (7) ELIMINAR DECLARACIONES MUERTAS
        List<String> pass7 = eliminateDeadDeclarations(pass6);

        return pass7;
    }

    // 1) Folding de constantes: tN = a op b  donde a,b literales
    private List<String> foldConstants(List<String> in) {
        Pattern p = Pattern.compile("^(t\\d+) = (\\d+) ([-+*/]) (\\d+)$");
        List<String> out = new ArrayList<>();
        for (String ln : in) {
            Matcher m = p.matcher(ln.trim());
            if (m.matches()) {
                int a = Integer.parseInt(m.group(2));
                int b = Integer.parseInt(m.group(4));
                String op = m.group(3);
                int r = switch(op) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    default -> a / b;
                };
                out.add(m.group(1) + " = " + r);
            } else {
                out.add(ln);
            }
        }
        return out;
    }

    // 2) Simplificar identidades: x = y+0  → x=y   ,   x=y*1 → x=y
    private List<String> simplifyIdentities(List<String> in) {
        List<String> out = new ArrayList<>();
        for (String ln : in) {
            String s = ln
                .replaceAll("\\b(\\w+) = (\\w+) \\+ 0\\b", "$1 = $2")
                .replaceAll("\\b(\\w+) = (\\w+) \\* 1\\b", "$1 = $2");
            out.add(s);
        }
        return out;
    }

    // 3) Eliminar temporales muertos
    private List<String> eliminateDeadTemporals(List<String> in) {
        Set<String> assigned = new HashSet<>(), used = new HashSet<>();
        Pattern assignP = Pattern.compile("^(t\\d+) =");
        for (String ln : in) {
            Matcher m = assignP.matcher(ln);
            if (m.find()) assigned.add(m.group(1));
            for (String t : assigned) {
                if (ln.contains(t) && !ln.startsWith(t + " =")) {
                    used.add(t);
                }
            }
        }
        List<String> out = new ArrayList<>();
        for (String ln : in) {
            Matcher m = assignP.matcher(ln);
            if (m.find() && !used.contains(m.group(1))) {
                // temporal muerto: omitimos
            } else {
                out.add(ln);
            }
        }
        return out;
    }

    // 4) Propagación de copias: t1 = t0  → sustituye usos de t1 por t0
    private List<String> propagateCopies(List<String> in) {
        Map<String,String> copyMap = new HashMap<>();
        List<String> out = new ArrayList<>();
        Pattern copyP = Pattern.compile("^(t\\d+) = (t\\d+)$");

        for (String ln : in) {
            Matcher m = copyP.matcher(ln.trim());
            if (m.matches()) {
                // registro la copia: t1 → origen(t0)
                String dst = m.group(1), src = m.group(2);
                src = copyMap.getOrDefault(src, src);
                copyMap.put(dst, src);
            } else {
                // reemplazo en la línea cualquier tN copiado
                String s = ln;
                for (var e : copyMap.entrySet()) {
                    s = s.replaceAll("\\b" + e.getKey() + "\\b", e.getValue());
                }
                out.add(s);
            }
        }
        return out;
    }

    // 5) Peephole de gotos: quita 'goto L' si la siguiente línea es 'L:'
    private List<String> eliminateRedundantGotos(List<String> in) {
        List<String> out = new ArrayList<>();
        for (int i = 0; i < in.size(); i++) {
            String ln = in.get(i).trim();
            if (ln.startsWith("goto ")) {
                String lbl = ln.substring(5).trim();
                if (i+1 < in.size() && in.get(i+1).trim().equals(lbl + ":")) {
                    continue; // quito el goto redundante
                }
            }
            out.add(in.get(i));
        }
        return out;
    }

    // 6) Eliminar asignaciones redundantes x = x
    private List<String> eliminateSelfAssigns(List<String> in) {
        List<String> out = new ArrayList<>();
        for (String ln : in) {
            if (!ln.trim().matches("(\\w+) = \\1")) {
                out.add(ln);
            }
        }
        return out;
    }

    // 7) Eliminar declaraciones de variables nunca usadas
    private List<String> eliminateDeadDeclarations(List<String> in) {
        // 1) recolectar todas las variables usadas en RHS
        Set<String> usedVars = new HashSet<>();
        Pattern rhsVar = Pattern.compile("\\b(?!t\\d+|L\\d+)([a-zA-Z_]\\w*)\\b");
        for (String ln : in) {
            if (ln.startsWith("declare ")) continue;
            Matcher m = rhsVar.matcher(ln);
            while (m.find()) {
                usedVars.add(m.group(1));
            }
        }
        // 2) filtrar declaraciones
        List<String> out = new ArrayList<>();
        Pattern declP = Pattern.compile("^declare\\s+(\\w+)");
        for (String ln : in) {
            Matcher m = declP.matcher(ln.trim());
            if (m.find() && !usedVars.contains(m.group(1))) {
                // omitimos la declaración
            } else {
                out.add(ln);
            }
        }
        return out;
    }
}


