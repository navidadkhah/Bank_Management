public class Tree extends Node {

    public static Node add(Node root, int[] xy, String name, String father_name, int k) {
        if (root == null) {
            return create_Node(name, father_name, xy);
        }
        int kd = k % 2;
        if (xy[kd] < root.xy[kd]) {
            root.left = add(root.left, xy, name, father_name, k + 1);
        } else {
            root.right = add(root.right, xy, name, father_name, k + 1);
        }
        return root;
    }

}