import java.util.Objects;

/*
 * This implementation of a binary search tree relies entirely on the concept of binary node and does not have any
 * external class for the sake of simplicity. It cannot hold null values or several identical values.
 * It will ignore any attempts to add these invalid values. If a node contains null as its value, it's considered
 * to be nonexistent.
 */
class BinaryNode<T extends Comparable<T>> {

    private T value;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    BinaryNode() { this.value = null; }    /* makes an empty tree */

    BinaryNode(T arg) {
        this.value = Objects.requireNonNull(arg, "This tree cannot hold null values");
        }

    private BinaryNode<T> find(T arg) {
        if (this.value == null) return null;
        if (this.value.compareTo(arg) == 0) return this;
        BinaryNode<T> next = (this.value.compareTo(arg) > 0) ? this.left : this.right;
        if (next == null) return null;
        return next.find(arg);
        }

    private BinaryNode<T> rightmost() {
        if (!this.hasRight()) return this;
        return this.right.rightmost();
        }

    private void replaceWith(BinaryNode<T> arg) {
        this.value = arg.value;
        this.left = arg.left;
        this.right = arg.right;
        }

    private boolean hasLeft() { return this.left != null  &&  this.left.value != null; }

    private boolean hasRight() { return this.right != null  &&  this.right.value != null; }

    void insert(T arg) {
        if (arg == null  ||  this.value.compareTo(arg) == 0) return;
        if (this.value.compareTo(arg) > 0) {
            if (!this.hasLeft()) this.left = new BinaryNode<T>(arg);
            else this.left.insert(arg);
            }
        else {
            if (!this.hasRight()) this.right = new BinaryNode<T>(arg);
            else this.right.insert(arg);
        }   }

    void remove(T arg) throws UnsupportedOperationException {
        if (arg == null) return;
        BinaryNode<T> target = this.find(arg);
        if (target == null) return;

        if (!target.hasLeft() && !target.hasRight()) this.value = null;
        else if (target.hasLeft() && !target.hasRight()) target.replaceWith(target.left);
        else if (!target.hasLeft() && target.hasRight()) target.replaceWith(target.right);
        else if (!target.left.hasRight()) target.replaceWith(target.left);
        else {
            BinaryNode<T> rightmost = target.rightmost();
            if (!rightmost.hasLeft() && !rightmost.hasRight()) target.replaceWith(rightmost);
            else if (rightmost.hasLeft()) {
                rightmost.replaceWith(rightmost.left);
                target.replaceWith(rightmost);
                }
            else throw new UnsupportedOperationException("Not implemented yet");
        }   }

    void printInorder() {
        if (this.hasLeft()) this.left.printInorder();
        System.out.print(this.value);
        if (this.hasRight()) this.right.printInorder();
    }   }