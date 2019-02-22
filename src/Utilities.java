import java.util.Random;

class Utilities {

    static Random random = new Random();

    /**
     * @param beginning the index from which the method will start searching, inclusive
     * @param end the index on which the method will stop searching, exclusive
     * @param divider the item to which other items in the search segment will be compared to checkIfPresent the one that is
     * smaller than the divider. Search segment is the part of the input array between beginning and end.
     * @return index of the item in the input array which is smaller than the divider or -1 if no such item is found
     */
    static int searchFromBack(int[] input, int beginning, int end, int divider) {
        for (int index = beginning; index != end; --index)
            if (input[index] < divider) return index;
        return -1;
        }

    /**
     * @param beginning the index from which the method will start searching, inclusive
     * @param end the index on which the method will stop searching, exclusive
     * @param divider the item to which other items in the search segment will be compared to checkIfPresent the one that is
     * bigger or equal to the the divider. Search segment is the part of the input array between beginning and end.
     * @return index of the item in the input array which is bigger than or equal to the divider or -1 if no such
     * item is found
     */
    static int searchFromFront(int[] input, int beginning, int end, int divider) {
        for (int index = beginning; index != end; ++index)
            if (input[index] >= divider) return index;
        return -1;
    }

    //TODO: this method is a special case (or is it?) of a more general searchFromFront ang should call it
    static int findSmallest(int[] input, int beginning) {
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
     * This method can turn a random array of integers into a binary heap or fix a binary heap if it has been
     * corrupted in some way. For instance, it happens after removal of the root element of a heap. Facilitates heap
     * sort by allowing to store the sorted part of the array at the end of the same array and ignoring that part of it
     * @param sorted is the length of the sorted part at the end
     */
    static void fixHeap(int[] input, int sorted) {
        for (int i = 0; i < input.length - sorted; ++i) {
            for (int j = i; j != 0; j = (j - 1) / 2) {
                if (input[j] > input[(j - 1) / 2]) {
                    int tmp = input[j];
                    input[j] = input[(j - 1) / 2];
                    input[(j - 1) / 2] = tmp;
                }
                else break;
    }        }   }   }
