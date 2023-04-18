import java.util.Scanner;

public class BiggerAngle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть координати точки А (x1, y1): ");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        System.out.print("Введіть координати точки В (x2, y2): ");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();

        double angle1 = getAngle(x1, y1, 1, 0);
        double angle2 = getAngle(x2, y2, 1, 0);

        System.out.println("OA|OX кут = " + angle1);
        System.out.println("OB|OX кут = " + angle2);
        if (angle1 > angle2) {
            System.out.println("Відрізок OA утворює більший кут з віссю OX");
        } else {
            System.out.println("Відрізок OB утворює більший кут з віссю OX");
        }
    }
    public static double getAngle(double x1,double y1,double x2,double y2) {
        double cosinus = (x1 * x2 + y1 * y2) / (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2));
        double angle = Math.acos(cosinus) * 180/ Math.PI;
        return angle;
    }
}
