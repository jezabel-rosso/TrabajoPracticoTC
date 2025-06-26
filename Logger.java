public class Logger {

    // Códigos ANSI para colores
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";

    public static void success(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void warning(String message) {
        System.out.println(YELLOW + message + RESET);
    }

    public static void error(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void info(String message) {
        System.out.println(message);
    }

    // Este es el método que te falta
    public static void log(String message) {
        info(message);
    }
}
