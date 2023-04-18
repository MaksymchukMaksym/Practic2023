import java.io.*;
import java.util.*;

public class RateWords {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");

        System.out.println(getFileContent(file));
        String text= getFileContent(file);
        String[] words = text.split("[\\s.,!?;:()\"«»<>{}—–+/]+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
        }

        for (Map.Entry<String, Integer> entry : descendingSort(uniqueWords(words))) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
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
    public static Map<String, Integer> uniqueWords(String [] words){
        Map<String, Integer> wordCountMap = new HashMap<>();

        for (String word : words) {
            if (wordCountMap.containsKey(word)) {
                int count = wordCountMap.get(word);
                wordCountMap.put(word, count + 1);
            }

            else {
                wordCountMap.put(word, 1);
            }
        }
        return wordCountMap;

    }
    public static  List<Map.Entry<String, Integer>> descendingSort(Map<String, Integer> wordCountMap){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(wordCountMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> comp1, Map.Entry<String, Integer> comp2) {
                return comp2.getValue().compareTo(comp1.getValue());
            }
        });
        return list;
    }
}