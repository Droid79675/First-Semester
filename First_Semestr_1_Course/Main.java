import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("setsofarrays.txt"))
        {

            Scanner sc = new Scanner(fr);

            while(sc.hasNextLine()){

                String stroka = sc.nextLine();
                String [] array = stroka.split(" ");
                int [] numbers = new int[array.length];
                for(int i = 0; i < array.length; i++){
                    numbers[i] = Integer.parseInt(array[i]);
                }

                combAssortment(numbers);


            }


        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


    }
    public static long combAssortment(int [] array) {
        long start = System.nanoTime();
        long count = 0;
        final double stepConstant = 1.24733095;
        int step = array.length;
        while (step > 1) {
            step = (int) (step / stepConstant);
            for (int i = 0; step + i < array.length; i++) {
                if (array[i] > array[i + step]) {
                    swap(array, i, i + step);
                }
            }
            count++;
        }
        long end = System.nanoTime();
        System.out.print(end - start + " ");
        System.out.print(count);
        System.out.println();
        return count;
    }

    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
