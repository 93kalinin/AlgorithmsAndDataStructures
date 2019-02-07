import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortTest {

    @Test
    void normal() {
        int[] input = {14, 22, 64, 182, 241, 11, -9, 0};
        int[] expected = {-9, 0, 11, 14, 22, 64, 182, 241};

        Sorts.selection(input);

        Assertions.assertArrayEquals(expected, input);
    }

    @Test
    void empty() {
        int[] input = {};
        int[] expected = {};

        Sorts.selection(input);

        Assertions.assertArrayEquals(expected, input);
    }

    @Test
    void oneElement() {
        int[] input = {0};
        int[] expected = {0};

        Sorts.selection(input);

        Assertions.assertArrayEquals(expected, input);
    }   }
