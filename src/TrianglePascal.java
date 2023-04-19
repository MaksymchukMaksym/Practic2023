import java.util.Scanner;

public class TrianglePascal {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть номер рядка: ");
        int numberRow = scanner.nextInt();

        int[] rowPascal = new int[numberRow + 1];
        rowPascal[0] = 1;
        rowPascal[rowPascal.length-1] = 1;

        for(int i=1;i<rowPascal.length-1;i++){
            rowPascal[i]= calculateElementRow(i,numberRow);
        }

        System.out.print(numberRow+" рядок трикутника Паскаля: ");
        for (int i=0; i<rowPascal.length; i++){
            System.out.print(rowPascal[i]+" ");
        }
        scanner.close();
    }
    public static int factorial(int number){
            int result = 1;
            for (int i = 1; i <= number; i++) {
                result = result * i;
            }
        return result;
    }
    public static int calculateElementRow(int k, int n) {
        int result = (factorial(n))/(factorial(k)*factorial(n-k));
        return result;
    }
}