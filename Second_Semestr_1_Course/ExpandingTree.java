public class ExpandingTree<Key extends Comparable<Key>, Value> {

    private Node root;
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // value на выходе связано с поданным ключом
    public Value get(Key key) {
        root = splay(root, key);
        int compareKey = key.compareTo((Key) root.key);
        if (compareKey == 0) {
            return (Value) root.value;
        }
        else{
            return null;
        }
    }

    public void insert(Key key, Value value) {
        // исп-ем splay к root-у
        if (root == null) {
            root = new Node(key, value);
            return;
        }

        root = splay(root, key);

        int compareKey = key.compareTo((Key) root.key);

        // вставка "новой" вершины слева
        if (compareKey < 0) {
            Node newNode = new Node(key, value);
            newNode.left = root.left;
            newNode.right = root;
            root.left = null;
            root = newNode;
        }

        // вставка "новой" вершины справа
        else if (compareKey > 0) {
            Node newNode = new Node(key, value);
            newNode.right = root.right;
            newNode.left = root;
            root.right = null;
            root = newNode;
        }

        // последний else на случай если у нас дубликат. Заменяем на новое value
        else {
            root.value = value;
        }

    }
    public void remove(Key key) {
        if (root == null) {
            return;
        }

        root = splay(root, key);

        int compareKey = key.compareTo((Key) root.key);

        if (compareKey == 0) {
            if (root.left == null) {
                root = root.right;
            }
            else {
                Node temp = root.right;
                root = root.left;
                splay(root, key);
                root.right = temp;
            }
        }

    }

    //  исп-ем splay в соответствии с поданной вершиной. Если вершина с этим ключом существует,
    //  он расширяется до корня дерева. Если нет, то расширяется до последней вершины.
    //  along the search path for the key is splayed to the root.
    private Node splay(Node parent, Key key) {
        if (parent == null) return null;

        int compareKey1 = key.compareTo((Key) parent.key);
        // тут либо zig-zig либо zig-zag
        if (compareKey1 < 0) {
            // поданное key отсутствует в дереве - выходим
            if (parent.left == null) {
                return parent;
            }
            int compareKey2 = key.compareTo((Key) parent.left.key);
            if (compareKey2 < 0) {
                parent.left.left = splay(parent.left.left, key);
                parent = rotateRight(parent);
            }
            else if (compareKey2 > 0) {
                parent.left.right = splay(parent.left.right, key);
                if (parent.left.right != null)
                    parent.left = rotateLeft(parent.left);
            }

            if (parent.left == null) {
                return parent;
            }
            else {
                return rotateRight(parent);
            }

        }
        // тут либо zag-zig либо zag-zag
        else if (compareKey1 > 0) {
            //  поданное key отсутствует в дереве - выходим
            if (parent.right == null) {
                return parent;
            }

            int compareKey2 = key.compareTo((Key) parent.right.key);
            if (compareKey2 < 0) {
                parent.right.left  = splay(parent.right.left, key);
                if (parent.right.left != null)
                    parent.right = rotateRight(parent.right);
            }
            else if (compareKey2 > 0) {
                parent.right.right = splay(parent.right.right, key);
                parent = rotateLeft(parent);
            }

            if (parent.right == null) {
                return parent;
            }
            else{
                return rotateLeft(parent);
            }
        }

        else return parent;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
}
