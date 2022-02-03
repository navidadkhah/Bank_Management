public class Node {
    Node left;
    Node right;
    int[] xy = new int[2];
    String name;
    String father_name;
    public static Node rishe;

    public static Node create_Node(String name, String father_name, int[] xy) {
        Node n = new Node();
        n.name = name;
        n.father_name = father_name;
        n.xy[0] = xy[0];
        n.xy[1] = xy[1];
        n.right = n.left = null;
        return n;
    }

}