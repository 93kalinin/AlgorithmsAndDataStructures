class Sorts {

    /**
     * Insertion sort divides an array in two parts -- sorted and not sorted. At the beginning, the first part
     * contains only one element, which is considered sorted. The algorithm then shifts every element that is larger
     * than the one in question to the right by using System.arraycopy. It puts the element in that position of the
     * array, thus enlarging the sorted part by one element. Then the process repeats until the whole array is sorted.
     */
    static void insertion(int[] input) {
        if (input.length <= 1) return;
        for (int endOfSorted = 1; endOfSorted < input.length; ++endOfSorted) {
            int toBeSorted = input[endOfSorted];
            int insertHere = 0;
            boolean shiftRequired = toBeSorted < input[endOfSorted - 1];
            while (insertHere < endOfSorted  &&  toBeSorted > input[insertHere]) insertHere++;
            if (shiftRequired) {
                System.arraycopy(input, insertHere, input, insertHere + 1, endOfSorted - insertHere);
                input[insertHere] = toBeSorted;
        }   }   }

    /**
     * Selection sort finds the smallest element in the unsorted part of the array and makes one free cell for it
     * by shifting every element between the end of the sorted part and the smallest element to the right. It then
     * puts the smallest element it found in that free cell and repeats the whole process untill the array is sorted.
     */
    static void selection(int[] input) {
        if (input.length <= 1) return;
        for (int endOfSortedPart = 0; endOfSortedPart < input.length; ++endOfSortedPart) {
            int indexOfSmallest = findSmallest(input, endOfSortedPart);
            int smallest = input[indexOfSmallest];
            System.arraycopy(input, endOfSortedPart, input, endOfSortedPart + 1,
            indexOfSmallest - endOfSortedPart);
            input[endOfSortedPart] = smallest;
        }   }

    private static int findSmallest(int[] input, int beginning) {
        int smallestUnsorted = Integer.MAX_VALUE;
        int index = 0;
        for (int i = beginning; i < input.length; ++i) {
            if (input[i] < smallestUnsorted) {
                smallestUnsorted = input[i];
                index = i;
            }   }
        return index;
        }

    /**
     * Bubble sort with several adjustments. Makes space for the element it currently works on by shifting smaller
     * elements instead of swapping pairs of elements. Additionally, it interleaves passes from right to left
     * and from left to right for better performance in some cases. Lastly, it only works on the part of the array
     * that had some changes happen to it in previous passes, because there is no point in checking the rest
     * of the array -- it is already sorted. The sorting is done when that active part of the array contracts to zero.
     */
    static void bubble(int[] input) {
        if (input.length <= 1) return;
        int leftMargin = 0;
        int rightMargin = input.length - 1;
        while (rightMargin != leftMargin) {
            for (int forward = leftMargin; forward < rightMargin; ++forward) {
                boolean shiftIsRequired = input[forward] > input[forward + 1];
                if (input[forward] > input[forward + 1]
            }