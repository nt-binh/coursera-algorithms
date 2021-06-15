public class QuickSort {
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high){
        int mid = low + (high - low)/2;
        int key = arr[mid];
        int i = low;
        int j = high + 1;
        while (true){
            while(arr[i++] < key){
                if (i == high) break;
            }
            while(arr[--j] > key){
                if (j == low) break;
            }
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, mid, j);
        return j;
    }

    public static void sort(int[] arr){
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high){
        if (low >= high) return;
        int j = partition(arr, low, high);
        System.out.println(j);
        sort(arr, low, j - 1);
        sort(arr, j + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {5,1,7,4,9,3};
        sort(arr);
        System.out.println("After sorting");
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
