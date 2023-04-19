import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random();

        int[] array1 = new int[ random.nextInt(8, 13)];
        int[] array2 = new int[ random.nextInt(8,13)];

        System.out.println("Масив №1: ");
        for(int i=0; i< array1.length; i++){
            array1[i] = random.nextInt(10, 1001);
            System.out.print(array1[i] + " ");
        }
        System.out.println("\nМасив №2: ");
        for(int i=0; i< array2.length; i++){
            array2[i] = random.nextInt(10, 1001);
            System.out.print(array2[i] + " ");
        }

        int preResult[] = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, preResult, array1.length, array2.length);


        for(int i=0; i< preResult.length; i++){
            if(preResult[i]% 5 != 0) {
                set.add(preResult[i]);
            }
        }
        int[] resultArray = new int[set.size()];
        int index = 0;
        for(int num : set) {
            resultArray[index++] = num;
        }
        Arrays.sort(resultArray);

        System.out.println("\nРезультат: ");
        for(int i=0; i< resultArray.length; i++){
            System.out.print(resultArray[i] + " ");
        }

    }
}