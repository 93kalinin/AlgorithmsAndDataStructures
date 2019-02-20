public class Searches {

    /**
     * Works only on sorted arrays. If the array is unsorted, it will overflow the stack. Does not validate the input.
     */
    static int binary(int[] input, int find, int from, int to) {
        int length = to - from;
        if (length == 0) return -1;
        int pivot = from + (to - from) / 2;
        if (input[pivot] == find) return pivot;
        else if (input[pivot] < find) return binary(input, find, pivot, to);
        else return binary(input, find, from, pivot);
    }   }