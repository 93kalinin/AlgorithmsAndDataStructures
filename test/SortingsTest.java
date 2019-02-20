import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class SortingsTest {

    private static Random random = new Random();

    @ParameterizedTest
    @MethodSource("arraysProvider")
    void quickSort(IntStream input_) {
        int[] input = input_.toArray();
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);

        Sorts.quick(input, 0, input.length);

        Assertions.assertArrayEquals(expected, input);
        }

    @ParameterizedTest
    @MethodSource("arraysProvider")
    void insertionSort(IntStream input_) {
        int[] input = input_.toArray();
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);

        Sorts.insertion(input);

        Assertions.assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("arraysProvider")
    void selectionSort(IntStream input_) {
        int[] input = input_.toArray();
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);

        Sorts.selection(input);

        Assertions.assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("arraysProvider")
    void bubbleSort(IntStream input_) {
        int[] input = input_.toArray();
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);

        Sorts.bubble(input);

        Assertions.assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @MethodSource("arraysProvider")
    void heapSort(IntStream input_) {
        int[] input = input_.toArray();
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);

        Sorts.heap(input);

        Assertions.assertArrayEquals(expected, input);
    }

    static Stream<IntStream> arraysProvider() {
        return Stream.of(IntStream.of(0, 1, 2, 3, 4, 5),
            IntStream.of(-9, -2, -11, Integer.MIN_VALUE),
            IntStream.of(3, 2),
            IntStream.of(22, 0, -458, 66, -12384),
            IntStream.generate(random::nextInt)
                .limit(random.nextInt(42)));
    }   }