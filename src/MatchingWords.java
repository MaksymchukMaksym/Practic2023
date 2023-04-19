import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MatchingWords {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть слово для пошуку у файлі: ");
        String searchingWord = scanner.next();
        System.out.println("Виберіть режим роботи, 1 - пошук часткових співпадінь, 2 - пошук повних співпадінь: ");
        int mode = Integer.parseInt(scanner.next());
        if(mode!=1 && mode!=2){
            System.out.println("Режим роботи вибрано невірно");
            return;
        }
        System.out.println("Введіть шлях файлу: ");
        String filePath = scanner.next();
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "Windows-1251"));
            List<String> lines = reader.lines().collect(Collectors.toList());
            String content = String.join(System.lineSeparator(), lines);
            System.out.println(content);
            if(mode==1){
                int countPartial = 0;
                Pattern pattern = Pattern.compile(searchingWord);
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    countPartial++;
                }
                System.out.println("Часткові співпадіння: " + countPartial);
            }
            else{
                int countPartial = 0;
                Pattern pattern = Pattern.compile("\\b"+searchingWord+"\\b");
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    countPartial++;
                }
                System.out.println("Повні співпадіння: " + countPartial);

            }


        }
        else {
        System.out.println("Файл незнайдено.");
        }
        scanner.close();
    }
}
