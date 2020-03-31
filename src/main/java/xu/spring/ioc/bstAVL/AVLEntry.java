package xu.spring.ioc.bstAVL;

import java.util.Map;

/**
 * @author xu
 * @date 2020/3/27 19:03
 * @description:
 */
public class AVLEntry<K, V> implements Map.Entry<K, V> {

    K key;
    V value;
    AVLEntry<K, V> left;
    AVLEntry<K, V> right;
    int height = 1;

    public AVLEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public AVLEntry(K key, V value, AVLEntry<K, V> left, AVLEntry<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AVLEntry{");
        sb.append("key=").append(key);
        sb.append(", value=").append(value);
        sb.append(", left=").append(left == null ? "NULL" : left.getKey());
        sb.append(", right=").append(right == null ? "NULL" : right.getKey());
        sb.append(", height=").append(height);
        sb.append('}');
        return sb.toString();
    }
}
