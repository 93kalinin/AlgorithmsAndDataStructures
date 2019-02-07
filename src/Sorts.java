class Sorts {

    /**
     * Insertion sort divides an array in two parts -- sorted and not sorted. At the beginning, the first part
     * contains only one element, which is considered sorted. The algorithm then shifts every element that is larger
     * than the one in question to the right by using System.arraycopy. It puts the element in that position of the
     * array, thus enlarging the sorted part by one element. Then the process repeats until the whole array is sorted.
     */
    static void insertion(int[] input) {
        if (input == null  ||  input.length <= 1) return;
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
     * puts the smallest element it found in that free cell and repeats the whole process until the array is sorted.
     */
    static void selection(int[] input) {
        if (input == null  ||  input.length <= 1) return;
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
     * Heap sort turns an array into a binary heap. Then it puts the root of the heap into the sorted part of the array
     * at its end and replaces it with a leaf that can be found right before the sorted part. The heap at this point is
     * broken, so it fixes it and proceeds adding new roots of the heap one by one to the sorted part and fixing the
     * heap until there is nothing left to sorted. The root of the heap is always the largest in the heap, so the
     * algorithm appends to the sorted part the largest element that is left in the unsorted (heap) part of the array.
     */
    static void heap(int[] input) {
        if (input == null  ||  input.length <= 1) return;
        for (int sortedLength = 0; sortedLength < input.length; ++sortedLength) {
            fixHeap(input, sortedLength);
            int tmp = input[input.length - sortedLength - 1];
            input[input.length - sortedLength - 1] = input[0];
            input[0] = tmp;
        }   }

    /**
     * This utility method can turn a random array of integers into a binary heap or fix a binary heap if it has been
     * corrupted in some way. For instance, it happens after removal of the root element of a heap. Facilitates heap
     * sort by allowing to store the sorted part of the array at the end of the same array and ignoring that part of it
     * @param sorted is the length of the sorted part at the end
     */
    private static void fixHeap(int[] input, int sorted) {
        for (int i = 0; i < input.length - sorted; ++i) {
            for (int j = i; j != 0; j = (j - 1) / 2) {
                if (input[j] > input[(j - 1) / 2]) {
                    int tmp = input[j];
                    input[j] = input[(j - 1) / 2];
                    input[(j - 1) / 2] = tmp;
                    }
                else break;
    }   }   }   }