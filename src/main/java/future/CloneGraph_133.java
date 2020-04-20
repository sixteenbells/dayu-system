package future;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/3 11:43 AM
 * @description :
 */
public class CloneGraph_133 {

    public static Map<Integer, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return helper(node);
    }

    public static Node helper(Node node) {
        if (visited.get(node.val) != null) {
            return visited.get(node.val);
        }

        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node.val, cloneNode);
        for (Node neighborNode : node.neighbors) {
            Node cnNode = helper(neighborNode);
            cloneNode.neighbors.add(cnNode);
        }
        return cloneNode;
    }

    public static void main(String[] args) throws Exception {

    }

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
