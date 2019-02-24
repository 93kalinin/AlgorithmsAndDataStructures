import java.util.Objects;

/*
 * This implementation of a binary search tree relies entirely on the concept of binary node and does not have any
 * external class for the sake of simplicity. It cannot be empty, hold null values or several identical values.
 */
class BinaryNode<T extends Comparable<T>> {

    private final T value;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    private BinaryNode<T> parent;

    BinaryNode(T arg) { this.value = Objects.requireNonNull(arg, "This tree cannot hold null values"); }

    private BinaryNode<T> find(T arg) {
        if (this.value.compareTo(arg) == 0) return this;
        BinaryNode<T> target = (this.value.compareTo(arg) > 0) ? this.left : this.right;
        if (target == null) return null;
        return target.find(arg);
        }

    private BinaryNode<T> rightmost() {
        if (this.right == null) return this;
        return this.right.rightmost();
        }

    boolean insert(T arg) {
        if (arg == null) return false;
        if (this.value.compareTo(arg) == 0) return false;
        if (this.value.compareTo(arg) > 0) {
            if (this.left == null) {
                this.left = new BinaryNode<T>(arg);
                return true;
                }
            else return this.left.insert(arg);
            }
        else {
            if (this.right == null) {
                this.right = new BinaryNode<T>(arg);
                return true;
                }
            else return this.right.insert(arg);
    }   }   }