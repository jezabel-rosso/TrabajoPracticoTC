# Trabajo Práctico – Técnicas de Compilación

Este repositorio contiene la implementación de un compilador dividido en tres fases, desarrollado utilizando **ANTLR4** y Java, según la consigna de la materia Técnicas de Compilación.

---

## 📁 Estructura de ramas

Cada fase está organizada en su propia rama, conteniendo **solo los archivos necesarios para esa etapa**:

### 🔹 `fase1-analisis-lexico`
- Implementación del analizador léxico con ANTLR4
- Definición de tokens (`CPPSubsetLexer.g4`)
- Archivos generados por ANTLR
- Prueba de lexer (`LexerTest.java`)
- Archivo de entrada (`programaEjemplo.cpp`)
- JAR de ANTLR

### 🔹 `fase2-analisis-sintactico`
- Gramática completa con estructura sintáctica (`CPPSubsetParser.g4`)
- Archivos generados por ANTLR
- Prueba del parser (`ParserTest.java`)
- Archivo de entrada
- JAR de ANTLR

### 🔹 `fase3-analisis-semantico`
- Implementación del analizador semántico (`SemanticAnalyzer.java`)
- Archivos del parser necesarios para recorrer el AST
- Archivo de entrada
- JAR de ANTLR

---

## 🧪 Requisitos

- Java 17 o superior
- ANTLR 4.13.2
- Editor o entorno como IntelliJ IDEA o VS Code

---

## 🚀 Cómo clonar y trabajar con una fase

```bash
git clone https://github.com/jezabel-rosso/TrabajoPracticoTC.git
cd TrabajoPracticoTC

# Cambiar a una de las fases:
git checkout fase1-analisis-lexico
# o
git checkout fase2-analisis-sintactico
# o
git checkout fase3-analisis-semantico
