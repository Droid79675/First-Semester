import java.util.Random;
import java.io.*;
public class ArraysSets {
    public static void main(String[] args) {
        Random rd = new Random();
        int n = 0;
        int size;
        while(n < 50){
            size = 100 + rd.nextInt(10001);
            System.out.println(size);
            int [] array = new int [size];
            for(int i = 0; i < array.length; i++){
                array[i] = rd.nextInt(100000);
            }

            writeInFile(array,n);
            n++;
        }
    }

    private static void writeInFile(int[] array, int n) {
        String text = "";
        try {
            for(int i = 0; i < array.length; i++){
                text += array[i] + " ";
            }
            FileWriter fw = new FileWriter("setsofarrays.txt", true);
            BufferedWriter bf = new BufferedWriter(fw,16384);

            bf.write(text);
            bf.append('\n');



            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
