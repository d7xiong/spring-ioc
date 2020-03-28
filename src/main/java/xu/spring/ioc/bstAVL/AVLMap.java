package xu.spring.ioc.bstAVL;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xu
 * @date 2020/3/27 19:09
 * @description:
 */
public class AVLMap<K, V> implements Iterable<AVLEntry<K, V>> {

    private int size;

    private AVLEntry<K, V> root;

    private Comparator<K> comp;

    public V put(K key, V value) {
        // Tree为空
        if (null == root) {
            root = new AVLEntry<>(key, value);
            size++;
            return value;
        }

        AVLEntry<K, V> p = root;
        while (null != p) {
            int compareResult = compare(key, p.key);

            // key等于当前节点key
            if (0 == compareResult) {
                p.setValue(value);
                break;
            }

            // key小于当前节点key
            if (compareResult < 0) {
                if (p.left == null) {
                    p.left = new AVLEntry<>(key, value);
                    size++;
                    break;
                }
                p = p.left;
                continue;
            }

            // key大于当前节点key
            if (p.right == null) {
                p.right = new AVLEntry<>(key, value);
                size++;
                break;
            }

            p = p.right;

        }
        return value;
    }

    public boolean isEmpty() {
        return 0 == size;
    }

    public int size() {
        return size;
    }

    public AVLMap() {

    }

    public AVLMap(Comparator<K> comp) {

        this.comp = comp;
    }

    public AVLMap(AVLEntry<K, V> root) {
        this.root = root;
    }

    public AVLMap(int size, AVLEntry<K, V> root, Comparator<K> comp) {
        this.size = size;
        this.root = root;
        this.comp = comp;
    }

    private int compare(K a, K b) {
        if (null != comp) {
            return comp.compare(a, b);
        }

        Comparable<K> c = (Comparable<K>) a;
        return c.compareTo(b);
    }

    @Override
    public Iterator<AVLEntry<K, V>> iterator() {
        return new AVLIterator<>(root);
    }


    private AVLEntry<K, V> getEntry(K key) {
        AVLEntry<K, V> p = root;
        while (p != null) {
            int compareResult = compare(key, p.key);
            if (compareResult == 0) {
                return p;
            }
            if (compareResult < 0) {
                p = p.left;
                continue;
            }
            p = p.right;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return null != getEntry(key);
    }

    public V get(K key) {
        AVLEntry<K, V> p = getEntry(key);
        return null == p ? null : p.getValue();
    }

    public boolean containsValue(V value) {
        Iterator<AVLEntry<K, V>> itr = this.iterator();
        while (itr.hasNext()) {
            if (itr.next().getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public AVLEntry<K, V> getFirstEntry(AVLEntry<K, V> p) {
        if (null == p) {
            return p;
        }

        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public AVLEntry<K, V> getLastEntry(AVLEntry<K, V> p) {
        if (null == p) {
            return p;
        }

        while (p.right != null) {
            p = p.right;
        }
        return p;
    }


    private AVLEntry<K, V> deleteEntry(AVLEntry<K, V> p, K key) {

        if (null == p) {
            return p;
        }

        int compareResult = compare(key, p.key);
        if (compareResult == 0) {
            if (p.left == null && p.right == null) {
                p = null;
            } else if (p.left != null && p.right == null) {
                p = p.left;
            } else if (p.left == null && p.right != null) {
                p = p.right;
            } else {
                if ((size & 1) == 0) {
                    AVLEntry<K, V> rightMin = getFirstEntry(p.right);
                    p.key = rightMin.key;
                    p.value = rightMin.value;
                    AVLEntry<K, V> newRight = deleteEntry(p.right, p.key);
                    p.right = newRight;
                } else {
                    AVLEntry<K, V> leftMax = getFirstEntry(p.left);
                    p.key = leftMax.key;
                    p.value = leftMax.value;
                    AVLEntry<K, V> newLeft = deleteEntry(p.left, p.key);
                    p.left = newLeft;
                }
            }
        }

        if (compareResult < 0) {
            AVLEntry<K, V> newLeft = deleteEntry(p.left, key);
            p.left = newLeft;
        }

        if (compareResult > 0) {
            AVLEntry<K, V> newRight = deleteEntry(p.right, key);
            p.right = newRight;
        }
        return p;

    }


    public V remove(K key) {
        AVLEntry<K, V> entry = getEntry(key);
        if (null == entry) {
            return null;
        }

        V oldValue = entry.getValue();
        root = deleteEntry(root, key);
        size--;
        return oldValue;
    }

    public void levelOrder() {
        Queue<AVLEntry<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        int preCount = 1;
        int pCount = 0;
        while (!queue.isEmpty()) {
            preCount--;
            AVLEntry<K, V> p = queue.poll();
            System.out.print(p + " ");
            if (p.left != null) {
                queue.offer(p.left);
                pCount++;
            }
            if (p.right != null) {
                queue.offer(p.right);
                pCount++;
            }
            if (preCount == 0) {
                preCount = pCount;
                pCount = 0;
                System.out.println();
            }
        }
    }
}
