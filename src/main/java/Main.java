public class Main {
    public static void main (String[] args){
        Tree<Integer> testTree = new Tree<>();

        System.out.println("Dodaje elementy...");
        testTree.insert(5);
        testTree.insert(10);
        testTree.insert(15);

        System.out.println("Wynik drzewa: ");
        System.out.println(testTree.draw());

    }
}
