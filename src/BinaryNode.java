import java.util.Objects;

/*
 * This implementation of a binary search tree relies entirely on the concept of binary node and does not have any
 * external class for the sake of simplicity. It cannot hold null values or several identical values.
 * It will ignore any attempts to add these invalid values.
 */
class BinaryNode<T extends Comparable<T>> {
    /*TODO: предусмотреть пустое дерево из единственного узла, содержащего null. Новый конструктор и пересмотр методов
    в этой реализации можно считать узел отсутствующим, если ссылка на него null или если его value == null
    проверку двух этих условий, возможно, можно просто вынести в convenience method
     */
    private T value;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    private BinaryNode<T> parent;

    BinaryNode(T arg) throws NullPointerException {
        this.value = Objects.requireNonNull(arg, "This tree cannot hold null values");
        }

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

    private void replaceWith(BinaryNode<T> arg) {
        this.value = arg.value;
        this.left = arg.left;
        this.right = arg.right;
        this.parent = arg.parent;
        }

    private boolean hasLeft() { return this.left != null  &&  this.left.value != null; }

    private boolean hasRight() { return this.right != null  &&  this.right.value != null; }

    void insert(T arg) {
        if (arg == null  ||  this.value.compareTo(arg) == 0) return;
        if (this.value.compareTo(arg) > 0) {
            if (this.left == null) this.left = new BinaryNode<T>(arg);
            else this.left.insert(arg);
            }
        else {
            if (this.right == null) this.right = new BinaryNode<T>(arg);
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

    }