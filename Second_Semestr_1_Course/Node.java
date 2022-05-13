public class Node<Key, Value> {
    Key key;
    Value value;
    Node left, right;

    public Node(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
