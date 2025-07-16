// src/main/java/ar/edu/ubp/compilador/Logger.java
package ar.edu.ubp.compilador;

public class Logger {
    private static final String RESET  = "\u001B[0m";
    private static final String RED    = "\u001B[31m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";

    public static void success(String msg) {
        System.out.println(GREEN + msg + RESET);
    }
    public static void warn(String msg) {
        System.out.println(YELLOW + msg + RESET);
    }
    public static void error(String msg) {
        System.err.println(RED + msg + RESET);
    }
    public static void log(String msg) {
        System.out.println(msg);
    }
}
