/**
 * Klasa implementująca algorytm drzewa binarnego.
 * @author Mateusz Mikus
 */

public class Tree<T extends Comparable<T>> {

    /**
     * Wewnętrzna klasa reprezentująca pojedynczy węzel w strukturze drzewa.
     */
    private class Node {
        T value;
        Node left;
        Node right;
        int height;

        Node(T val) {
            this.value = val;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }


    private Node root;

    /**
     * Konstruktor inicjalizujący puste drzewo.
     */
    public Tree() {
        this.root = null;
    }



    private int getHeight(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    private int getBalance (Node N) {
        if (N == null) {
            return 0;
        }
        return getHeight(N.left) - getHeight(N.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;


        return y;
    }

    private Node insertAVL(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = insertAVL(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insertAVL(node.right, value);
        } else {
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if (balance > 1 && value.compareTo(node.left.value) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 && value.compareTo(node.right.value) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && value.compareTo(node.left.value) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && value.compareTo(node.right.value) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;

    }



    public synchronized boolean insert(T value) {
        if (search(value)) {
            return false;
        } else {
            root = insertAVL(root, value);
            return true;
        }
    }

    /**
     * Wykonuje wstawienie nowej wartości do drzewa.
     * @param value Element przeznaczony do wstawienia.
     * @return true, jeśli wstawienie się powiodło; false, jeśli element juz znajduje się w drzewie.
     */
    public synchronized boolean insertold(T value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            return true;
        }

        Node current = root;
        Node parent;

        while (true) {
            parent = current;

            if (value.compareTo(current.value) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return true;
                }
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return true;
                }
            } else {
                return false;
            }
        }

    }

    /**
     * Przeszukuje strukturę drzewa w celu zlokalizowania zadanego elementu.
     * @param value Element poszukiwany
     * @return true w przypadku zlokalizowania węzła; false w przeciwnym razie 
     */
    public synchronized boolean search(T value) {
        Node current = root;

        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Dokonuje usunięcie zadanego elementu.
     * @param value Element przeznaczony do usunięcia.
     */
    public synchronized void deleteNode(T value) {
        Node parent = null;
        Node current = root;

        while (current != null && value.compareTo(current.value) != 0) {
            parent = current;
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return;
        }

        if (current.left != null && current.right != null) {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }


            current.value = successor.value;
            current = successor;
            parent = successorParent;
        }

        Node child;
        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right;
        }

        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }

    }


    /**
     * Metoda rekurencyjna tranformująca gałęzie drzewa do formy tekstowej.
     * @param node Referencja na analizowany węzeł.
     * @param level Stopień zagłębienia w hierarchii.
     * @param sb Obiekt łączący wyodrębnione wartości tekstowe.
     */
    private void drawPrivate(Node node, int level, StringBuilder sb) {
        if (node != null) {
            drawPrivate(node.right, level + 1, sb);
            for (int i =0; i < level; i++) {
                sb.append("|    ");
            }

            sb.append("|----");

            sb.append(node.value).append("\n");
            drawPrivate(node.left, level + 1, sb);
        }
    }

    /**
     * Metoda inicjujące pełne rysowanie struktury drzewa.
     * @return Ciąg reprezentujący hierarchię drzewa.
     */
    public synchronized String draw() {
        if (root == null) {
            return "Drzewo jest puste\n";
        }
        StringBuilder sb = new StringBuilder();
        drawPrivate(root, 0, sb);
        return sb.toString();
    }

}