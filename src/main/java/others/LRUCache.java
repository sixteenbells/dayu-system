package others;

import java.util.HashMap;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/10 11:41 AM
 * @description :自己实现LRU
 * https://www.cnblogs.com/nicky-160330/archive/2018/08/18/9498481.html
 * https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/LRU%E7%AE%97%E6%B3%95.md
 */
public class LRUCache<K, V> {
    private HashMap<K, Node> map;
    private int capacity;
    private DoubleList list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        list = new DoubleList();
    }

    /**
     * 插入数据
     *
     * @param key
     * @param value
     * @return
     */
    public Node put(K key, V value) {
        Node node = map.get(key);
        // 如果已经存在，则删除
        if (node != null) {
            list.remove(node);
        } else {
            node = new Node(key, value);
        }

        // 如果队列满了，则删除最后一个
        if (map.size() >= capacity) {
            Node lastNode = list.removeLast();
            map.remove(lastNode.key);
        }
        // 插入队列和MAP
        list.addFirst(node);
        map.put(key, node);
        return node;
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public Node get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return node;
        } else {
            list.remove(node);
        }

        list.addFirst(node);
        return node;
    }


    class Node<K, V> {
        K key;
        V value;
        Node pre, next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoubleList {
        Node head;
        Node tail;

        /**
         * 队列头部插入元素
         *
         * @param node
         */
        public void addFirst(Node node) {
            // 是不是空的
            if (head != null) {
                node.next = head;
                head.pre = node;
            }
            head = node;
            if (tail == null) {
                tail = node;
            }
        }

        /**
         * 删除元素
         *
         * @param node
         * @return
         */
        public void remove(Node node) {
            // 判断该节点是不是头节点
            if (node.pre == null) {
                head = node.next;
            } else {
                node.pre.next = node.next;
            }
            // 判断是不是尾结点
            if (node.next == null) {
                tail = node.pre;
            } else {
                node.next.pre = node.pre;
            }
            node.next = null;
            node.pre = null;
        }

        /**
         * 删除最后的节点
         *
         * @return
         */
        public Node removeLast() {
            // 没有数据
            if (tail == null) {
                return null;
            }
            Node node = tail;
            // 一个数据，head == tail
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = node.pre;
                node.pre.next = null;
                node.pre = null;
            }
            return node;
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            Node node = head;
            while (node != null) {
                buffer.append("[")
                        .append(node.key)
                        .append(",")
                        .append(node.value)
                        .append("]");
                node = node.next;
            }
            return "DoubleList{" + buffer.toString() + "}";
        }
    }

    @Override
    public String toString() {
        return "LRUCache{" +
                "list=" + list +
                '}';
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache(5);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.put(5, 5);
        cache.get(3);
        System.out.println(cache.toString());
        cache.put(6, 6);
        System.out.println(cache.toString());
    }
}


