import java.util.*;

public class CodeOptimizer {

    private final List<String> originalCode;
    private final List<String> optimizedCode = new ArrayList<>();
    private final Map<String, String> constants = new HashMap<>();
    private final Set<String> usedVariables = new HashSet<>();

    public CodeOptimizer(List<String> code) {
        this.originalCode = code;
        detectarVariablesUsadas();
    }

    public List<String> optimize() {
        for (String line : originalCode) {

            // Asignación constante
            if (line.matches("[a-zA-Z_][a-zA-Z0-9_]* = \\d+")) {
                String[] parts = line.split(" = ");
                String var = parts[0].trim();
                String value = parts[1].trim();

                // Solo guardamos si se usa después
                if (usedVariables.contains(var)) {
                    constants.put(var, value);
                    optimizedCode.add(line);
                }
            }

            // Return de variable
            else if (line.matches("return [a-zA-Z_][a-zA-Z0-9_]*")) {
                String var = line.split(" ")[1].trim();
                if (constants.containsKey(var)) {
                    optimizedCode.add("return " + constants.get(var));
                } else {
                    optimizedCode.add(line);
                }
            }

            // Return directo o línea desconocida
            else {
                optimizedCode.add(line);
            }
        }

        return optimizedCode;
    }

    private void detectarVariablesUsadas() {
        for (String line : originalCode) {
            // Buscar variables usadas en return, expresiones, etc.
            for (String word : line.split("\\W+")) {
                if (word.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                    // Si no es una asignación del tipo "x = ..."
                    if (!line.matches("^" + word + " = .*")) {
                        usedVariables.add(word);
                    }
                }
            }
        }
    }
}
