import java.io.*;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Введіть шлях до файлу:");
        Scanner scanner = new Scanner(System.in);
        String inputFilePath = scanner.next();
        File file = new File(inputFilePath);
        String outputFilePath = "out.txt";

        if (file.exists()) {

            String content = getFileContent(file);
            String reversedContent = new StringBuilder(content).reverse().toString();

            FileWriter fileWriter = new FileWriter(outputFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(reversedContent);
            bufferedWriter.close();

        } else {
            System.out.println("Файл незнайдено.");
        }
    }
    public static String getFileContent(File file) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Windows-1251"))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}