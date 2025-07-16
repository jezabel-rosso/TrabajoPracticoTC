// programaEjemplo.cpp

int suma(int a, int b) {
    return a + b;
}

int main() {
    int x;
    x = suma(5, 10);

    if (x > 10) {
        x = x - 1;
    }

    for (int i = 0; i < x; i = i + 1) {
        x = x + i;
    }

    return x;
}
