package cp600assign;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HighestIncrease {


    private static int[] findMaxPair(List<Integer> inputData) {
        int arr[] = new int[3];
        int maxPair=0;
        int pairDiff;
        int index1=0, index2=0;
        for(int i=0; i<inputData.size(); i++){
            for(int j=i+1; j<inputData.size(); j++){
                if(inputData.get(i)<inputData.get(j)){
                    pairDiff = inputData.get(j)-inputData.get(i);
                    if(pairDiff > maxPair) {
                        maxPair = pairDiff;
                        index1 = j;
                        index2 = i;
                    }
                }
            }
        }
        arr[0] = maxPair;
        arr[1] = index1;
        arr[2] = index2;
        return arr;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> inputs = new ArrayList<>();
        while (scanner.hasNextInt()) {
            inputs.add(scanner.nextInt());
            if(scanner.hasNext("stop")) {
                break;
            }
        }
        int[] arr = findMaxPair(inputs);
        int index1 = arr[1]>arr[2]?arr[2]:arr[1];
        int index2 = arr[1]>arr[2]?arr[1]:arr[2];
        System.out.println("Highest increase: "+inputs.get(arr[1])+" - "+"("+inputs.get(arr[2])+") = "+arr[0]+" indices: "+index1+" "+index2);
    }
}
