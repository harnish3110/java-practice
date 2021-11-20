package interview.google;

import java.util.*;

public class Third {

/*
    Given two binary trees, determine if they have the same inorder traversal. Order is Left, parent then Right


            2.    1
            / \.    \
            1.  3.    2
                  /    /   \
                4    4    3

            1,2,4,3       1,4,2,3

            // 2
            2,3,4        4
*/

    // Node -> Left, Right, Value
    private static class Node {
        Object name;
        Node left, right;

        Node(Object name, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.name = name;
        }
    }

    List<Object> matchtree(Node root1, Node root2) {
        if (root1 == null && root2 == null) return Collections.emptyList();
        if (root1 == null || root2 == null) return null;

        Stack<Node> f_Coll = new Stack<>();
        Stack<Node> s_Coll = new Stack<>();

        List<Object> result = new ArrayList<>();

        Node first, second;

        getLeftMost(root1, f_Coll);
        getLeftMost(root2, s_Coll);

        while (!f_Coll.isEmpty() && !s_Coll.isEmpty()) {
            first = f_Coll.pop(); // 1 === 1
            second = s_Coll.pop(); // 2nd 2.3.4       2,4
            if (first.name == second.name) {
                result.add(first.name);
                if (first.right != null) {
                    getLeftMost(first.right, f_Coll);
                }

                if (second.right != null) {
                    getLeftMost(second.right, s_Coll);
                }
            } else return null;

        }
        if (f_Coll.isEmpty() && s_Coll.isEmpty()) return result;

        return null;

    }

    public void printStack(Collection<Node> stack) {
        stack.forEach(node -> {
            System.out.println(node.name);
        });
    }

    void getLeftMost(Node node, Stack<Node> st) {
        if (node.left == null) {
            st.add(node);
            return;
        }

        while (node != null) {
            st.add(node);
            node = node.left;
        }
    }

    public static void main(String[] arg) {
        Node x = new Node(2, new Node(1, null, null), new Node(3, new Node(4, null, null), null));
        Node y = new Node(1, null, new Node(2, null, new Node(3, new Node(4, null, null), null)));

        Third compare = new Third();
        List<Object> isMatched = compare.matchtree(x, y);
        if (isMatched == null) System.out.println("The inorder traversal is not same");
        else System.out.println("The collection are matched and inorder traversal is: " + isMatched);

    }
}
