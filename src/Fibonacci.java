import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть індекс бажаного числа: ");
        int index = scanner.nextInt();

        int fibonacciNumber = fibonacci(index);
        System.out.println("Число Фібоначчі за індексом " + index + " рівне " + fibonacciNumber);
    }
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
}

