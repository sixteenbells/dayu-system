package others;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/5 7:16 PM
 * @description : LRU算法实现
 *
 * 基于LinkedHashMap实现，LinkedHashMap实现了双端队列，其参数accessOrder设置是否保持访问顺序，
 * 默认为false，保持插入顺序，true时保存LRU顺序。
 * 两个重要的方法afterNodeAccess和afterNodeInsertion，分别在访问节点和插入节点后访问，用于维持访问顺序。
 * afterNodeInsertion调用了removeEldestEntry判断是否删除老节点，默认实现直接返回false，所以需要重写该方法。
 * 相关wiki：https://blog.csdn.net/u013568373/article/details/90607083
 * https://www.cnblogs.com/zlting/p/10775887.html
 * https://www.cnblogs.com/nicky-160330/archive/2018/08/18/9498481.html
 * https://blog.csdn.net/xiaoyao2246/article/details/88836769
 */
public class LinkedHashMapLRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxCapacity;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final Lock lock = new ReentrantLock();

    public LinkedHashMapLRUCache(int maxCapacity) {
        super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > maxCapacity;
    }

    @Override
    public V get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            return super.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            lock.lock();
            return super.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.lock();
            return super.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            super.clear();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LinkedHashMapLRUCache<Integer, Integer> cache = new LinkedHashMapLRUCache<>(5);
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
