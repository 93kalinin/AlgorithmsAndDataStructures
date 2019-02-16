class Sorts {

    /**
     * Insertion sort divides an array in two parts -- sorted and not sorted. At the beginning, the first part
     * contains only one element, which is considered sorted. The algorithm then shifts every element that is larger
     * than the one in question to the right by using System.arraycopy. It puts the element in that position of the
     * array, thus enlarging the sorted part by one element. Then the process repeats until the whole array is sorted.
     */
    static void insertion(int[] input) {
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
        for (int endOfSortedPart = 0; endOfSortedPart < input.length; ++endOfSortedPart) {
            int indexOfSmallest = Utilities.findSmallest(input, endOfSortedPart);
            int smallest = input[indexOfSmallest];
            System.arraycopy(input, endOfSortedPart, input, endOfSortedPart + 1,
            indexOfSmallest - endOfSortedPart);
            input[endOfSortedPart] = smallest;
        }   }

    /**
     * Bubble sort passes an array forwards and backwards and swaps any pair of elements that it finds to be unordered.
     * Additionally, it only passes through the unsorted part of the array. If it passes a certain part of the array
     * and makes no swaps, the algorithm marks that part as sorted and ignores it for the rest of sorting.
     */
    static void bubble(int[] input) {
        boolean isSorted = false;
        boolean isFirstLeft = false;
        boolean isFirstRight = false;
        int firstLeft = 0;
        int firstRight = input.length - 1;
        while (!isSorted) {
            isSorted = true;
            isFirstLeft = false;
            isFirstRight = false;
            for (int i = firstLeft; i < firstRight; ++i) {
                if (input[i] > input[i + 1]) {
                    isSorted = false;
                    if (!isFirstLeft) firstLeft = i;
                    isFirstLeft = true;
                    int temporary = input[i];
                    input[i] = input[i + 1];
                    input[i + 1] = temporary;
                }
            }
            for (int i = input.length - 1; i > 1; --i) {
                if (input[i] < input[i - 1]) {
                    isSorted = false;
                    if (!isFirstRight) firstRight = i;
                    isFirstRight = true;
                    int temporary = input[i];
                    input[i] = input[i - 1];
                    input[i - 1] = temporary;
                }
            }
        }
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
            Utilities.fixHeap(input, sortedLength);
            int tmp = input[input.length - sortedLength - 1];
            input[input.length - sortedLength - 1] = input[0];
            input[0] = tmp;
        }   }


    /**
     * !!!BROKEN!!!
     * Quicksort picks an array element and moves every element which is larger than the picked one to the right part
     * of the array, and each element that is smaller than the picked one to the left part. It then recursively calls
     * itself on those parts until they are one element long and therefore sorted. Finally, it merges the results
     * in the sorted order. This implementation chooses a random dividing element and sorts in place. It can be done
     * by going through the following steps. Swap the dividing element with the one at the beginning of the array.
     * Remove it from the array. That frees up the first cell of it. Now search for an element which is less than the
     * dividing one, starting from the end of the array. Put it in the free cell. There is another free cell now where
     * that smaller element was removed from. Find an element that is larger than the dividing one, starting from
     * the beginning of the array and put it in the free cell. Repeat until the two regions in which the search is
     * being done meet somewhere and put the dividing item in the free cell Call the method recursively to keep sorting
     * @param beginning -- the index of the beginning of the part of the array which should be sorted, inclusive
     * @param end -- the index of the end of the part of the array which should be sorted, exclusive
     */
    static void quick(int[] input, int beginning, int end) {
        int segmentLength = end - beginning;
        if (segmentLength <= 1) return;
        int randomIndex = Utilities.random.nextInt(segmentLength);
        int divider = input[randomIndex];
        input[randomIndex] = input[beginning];
        int currentHole = beginning;
        int previousHole = end - 1;

        boolean foundSmaller = true;
        boolean foundBigger = true;
        while(foundBigger || foundSmaller) {
            int smallerItemIndex = Utilities.searchFromBack(input, previousHole, currentHole, divider);
            if (smallerItemIndex == -1) foundSmaller = false;
            else {
                input[currentHole] = input[smallerItemIndex];
                previousHole = currentHole;
                currentHole = smallerItemIndex;
                }
            int biggerItemIndex = Utilities.searchFromFront(input, previousHole, currentHole, divider);
            if (biggerItemIndex == -1) foundBigger = false;
            else {
                input[currentHole] = input[biggerItemIndex];
                previousHole = currentHole;
                currentHole = biggerItemIndex;
            }   }

        input[currentHole] = divider;
        quick(input, currentHole + 1, end);
        quick(input, beginning, currentHole - 1);
    }   }
