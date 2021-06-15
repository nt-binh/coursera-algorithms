import java.util.Scanner;
import java.util.Arrays;
// How can we make few practical improvements for Merge Sort?
// #1 Use Insertion Sort for small amount of elements
// #2 Do not merge two subarrays if the combination of two subarrays are already sorted

public class CountInversion {
    private static int inversedPair = 0;

    public static int inversedPair() {
        return inversedPair;
    }
    private static void merge(int[] arr, int low, int mid, int high){
        int left = mid - low + 1;
        int right = high - mid;
        int[] leftArr = new int[left];
        int[] rightArr = new int[right];

        for (int i = 0; i < left; i++){
            leftArr[i] = arr[low + i];
        }

        for (int i = 0; i < right; i++){
            rightArr[i] = arr[mid + i + 1];
        }
        int leftIndex = 0, rightIndex = 0, i = low;
        while (leftIndex < left && rightIndex < right){
            if (leftArr[leftIndex] <= rightArr[rightIndex]){
                arr[i] = leftArr[leftIndex++];
                inversedPair++;
            } else{
                arr[i] = rightArr[rightIndex++];
            }
            i++;
        }
        while (leftIndex < left){
            arr[i++] = leftArr[leftIndex++];
            inversedPair += right;
        }

        while (rightIndex < right){
            arr[i++] = rightArr[rightIndex++];
        }
    }

    private static void sort(int[] arr, int low, int high){
        if (low >= high){
            return;
        }
        int mid = low + (high - low)/2;
        sort(arr, low, mid);
        sort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter length of array: ");
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            System.out.print("Enter element #" + (int) (i + 1) + ": ");
            arr[i] = in.nextInt();
        }
        System.out.println(Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
        System.out.println("Inversed Pair: " + inversedPair());
    }
}
