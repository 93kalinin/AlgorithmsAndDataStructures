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
                System.out.println("insertHere " + insertHere + " endOfSorted " + endOfSorted);
                System.arraycopy(input, insertHere, input, insertHere + 1, endOfSorted - insertHere);
                input[insertHere] = toBeSorted;
                }
            for (int i : input) System.out.print(i + " ");
            System.out.println();
            }
        }

    /**
     * Сортировка выбором циклически просматривает несортированную часть массива и находит наименьший элемент с помощью
     * метода findSmallest, затем освобождает место в сортированной части массива сдвигом элементов вправо на единицу
     * до той ячейки, откуда был скопирован наименьший элемент вызовом System.arraycopy и помещает этот элемент
     * в освободившуюся ячейку, тем самым присоединяя его к расширенной за счет сдвига сортированной части.
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
     * Доработанный алгоритм сортировки пузырьком. Берет первый неупорядоченный относительно следующего элемент и
     * сдвигает следующие элементы до тех пор, пока не найдет взятому элементу место. Проходит по такому принципу
     * в прямом и обратном направлениях, ускоряя тем самым помещение элементов в конечные позиции. Дополнительно,
     * пользуется "окном" -- просматривает только ту часть массива, в которой были сдвиги элементов при прошлом
     * просмотре. Если элементы не были сдвинуты, то эта часть массива уже упорядочена. Останавливается при сужении
     * окна до нуля.
     */
    public static void main(String... args) {
        int[] input = {14, 22, 64, 182, 241, 11, -9, 0};

        Sorts.insertion(input);
        }
    }