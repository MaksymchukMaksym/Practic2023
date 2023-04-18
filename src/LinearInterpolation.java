import java.util.*;

public class LinearInterpolation {
    private static final int screenWidth = 1920;
    private static final int screenHeight = 1080;

    private static final int minDistance = 50;
    private static Set<String> pointSet = new HashSet<>();

    public static void main(String[] args) {
        System.out.print("Виберіть спосіб введення координат екрану: ");
        Scanner scanner = new Scanner(System.in);
        String choiseFilling = scanner.next();

        System.out.print("Введіть к-сть точок вхідного масиву: ");
        int numOfPoints = scanner.nextInt();
        int[][] points = new int[numOfPoints][2];

        switch (choiseFilling) {
            case "Рандомно":
                Random random = new Random();
                for (int i = 0; i < numOfPoints; i++) {
                    int x, y;
                    boolean isUnique;
                    do {
                        x = random.nextInt(1920);
                        y = random.nextInt(1080);
                        isUnique = true;
                        for (int j = 0; j < i; j++) {
                            if (Math.abs(x - points[j][0]) < minDistance || Math.abs(y - points[j][1]) < minDistance) {
                                isUnique = false;
                                break;
                            }
                        }
                    } while (!isUnique);
                    points[i][0] = x;
                    points[i][1] = y;
                }
                System.out.println("Згенеровані точки:");
                for (int i = 0; i < numOfPoints; i++) {
                    System.out.printf("(%d, %d)%n", points[i][0], points[i][1]);
                }
                break;
            case "Вручну":
                for (int i = 0; i < numOfPoints; i++) {
                    boolean isValid = false;
                    boolean errorShown = false;
                    System.out.printf("Введіть точку #%d (x,y): ", i + 1);
                    do {

                        String point = scanner.next();

                        if (point.matches("\\(-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?\\)")) {
                            String[] coords = point.replaceAll("\\(|\\)", "").split(",");
                            int x = Integer.parseInt(coords[0]);
                            int y = Integer.parseInt(coords[1]);

                            if (isValidPoint(x, y)) {
                                points[i][0] = x;
                                points[i][1] = y;
                                isValid = true;
                            } else {
                                if (!errorShown) {
                                    System.out.println("Неправильно введена точка. Введіть її правильно");
                                    System.out.printf("Введіть точка #%d (x, y): ", i + 1);
                                    errorShown = true;
                                }
                            }
                        } else {
                            if (!errorShown) {
                                System.out.println("Неправильно введена точка. Введіть її правильно");
                                System.out.printf("Введіть точка #%d (x, y): ", i + 1);
                                errorShown = true;

                            }
                        }
                    } while (!isValid);
                }
                break;
            default:
                System.out.println("Невірне значення");
                break;

        }
        System.out.print("Введіть кількість точок для вихідного інтерпольованого масиву: ");
        String input = scanner.next();
        int countResult = Integer.parseInt(input);
        int[][] interpolationPoints = new int[countResult][2];
        interpolationPoints = linearInterpolation(points, countResult);

        System.out.println("Точки після інтерполяції:");
        for (int i = 0; i < countResult; i++) {
            System.out.printf("(%d, %d)%n", interpolationPoints[i][0], interpolationPoints[i][1]);
        }


        scanner.close();
    }

    public static boolean isValidPoint(int x, int y) {
        String pointStr = x + "," + y;
        if (x < 0 && x > screenHeight && y < 0 && y > screenWidth) {
            return false;
        }
        if (x < 0 && y < 0) {
            return false;
        }
        if (!pointSet.add(pointStr)) {
            return false;
        }

        return true;
    }

    public static int[][] linearInterpolation(int[][] screenCoordinates, int numPoints) {
        double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;
        int n = screenCoordinates.length;

        for (int i = 0; i < n; i++) {
            sumX += screenCoordinates[i][0];
            sumY += screenCoordinates[i][1];
            sumXY += screenCoordinates[i][0] * screenCoordinates[i][1];
            sumXX += screenCoordinates[i][0] * screenCoordinates[i][0];
        }

        double b = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        double a = (sumY - b * sumX) / n;

        double firstX = screenCoordinates[0][0];
        double lastX = screenCoordinates[n - 1][0];
        double step = (lastX - firstX) / (numPoints - 1);

        int[][] newPoints = new int[numPoints][2];

        for (int i = 0; i < numPoints; i++) {
            double x = firstX + i * step;
            double y = a + b * x;

            newPoints[i][0] = (int) x;
            newPoints[i][1] = (int) y;
        }
        SKV_interpolationDeviation(screenCoordinates,a,b);
        return newPoints;

    }

    public static  void SKV_interpolationDeviation(int [][] coordinates, double a, double b ) {

        double sumError = 0;

        for (int[] point : coordinates) {
            double x = point[0];
            double y = point[1];

            double interpolatedY = a + b * x;
            double error = y - interpolatedY;

            sumError += error * error;
        }

        double skv = Math.sqrt(sumError / coordinates.length);

        double deviation = Math.sqrt(skv/coordinates.length);

        System.out.println("СКВ: " + skv);

        System.out.println("Відхилення: " + deviation+" чим менше це число тим точніша інтерполяція");

    }
}

