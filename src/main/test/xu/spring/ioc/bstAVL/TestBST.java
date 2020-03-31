package xu.spring.ioc.bstAVL;


import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author xu
 * @date 2020/3/27 19:53
 * @description:
 */

public class TestBST {


    private static Random random = new Random();

    private static final int MAX1 = 16;

    private static final int MAX2 = 65;


    @Test
    public void testQuery() {

        AVLMap<Integer, String> map = new AVLMap<>();

        map.put(4, "a");
        map.put(2, "b");
        map.put(6, "c");
        map.put(2, "d");
        map.put(1, "a");
        map.put(3, "d");
        map.put(5, "e");
        map.put(1, "f");

        Assert.assertTrue(map.get(4).equals("a"));
        Assert.assertTrue(map.get(1).equals("f"));
        Assert.assertTrue(map.get(7) == null);
        Assert.assertTrue(map.containsKey(6));
        Assert.assertTrue(!map.containsKey(-1));
        Assert.assertTrue(map.containsValue("d"));
        Assert.assertTrue(!map.containsValue("xxxx"));
    }

    @Test
    public void testQueryWithJDK() {
        AVLMap<Integer, String> map1 = new AVLMap<>();
        TreeMap<Integer, String> map2 = new TreeMap<>();
        for (int i = 0; i < MAX2; i++) {
            int key = random.nextInt(MAX2);
            String value = random.nextInt(MAX2) + "";
            map1.put(key, value);
            map2.put(key, value);
        }

        for (int i = 0; i < MAX2; i++) {
            int key = random.nextInt(MAX2);
            Assert.assertTrue(map1.containsKey(key) == map2.containsKey(key));

            if (map1.get(key) == null) {
                Assert.assertTrue(map2.get(key) == null);
            } else {
                Assert.assertTrue(map1.get(key).equals(map2.get(key)));
            }
        }

        for (int i = 0; i < 255; i++) {
            String value = random.nextInt(MAX2) + "";
            Assert.assertTrue(map1.containsValue(value) == map2.containsValue(value));
        }
    }

    @Test
    public void testPutAndItrWithJDK() {
        AVLMap<Integer, String> map1 = new AVLMap<>();
        TreeMap<Integer, String> map2 = new TreeMap<>();
        for (int i = 0; i < MAX2; i++) {
            int key = random.nextInt(MAX2);
            String value = random.nextInt(MAX2) + "";
            map1.put(key, value);
            map2.put(key, value);
        }

        System.out.println(map1.size() == map2.size());
        System.out.println(map1.size());


        Iterator<AVLEntry<Integer, String>> it1 = map1.iterator();
        Iterator<Map.Entry<Integer, String>> it2 = map2.entrySet().iterator();

        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().getKey().equals(it2.next().getKey())) {
                System.out.println("!=: ");
            }
        }

        System.out.println("all iterator done! " + (!it1.hasNext() && !it2.hasNext()));


    }

    @Test
    public void testPutAndItr() {

        AVLMap<Integer, String> map = new AVLMap<Integer, String>();

        for (int i = 0; i < MAX1; i++) {
            map.put(random.nextInt(MAX1), random.nextInt(MAX1) + "");
        }

        Iterator<AVLEntry<Integer, String>> it = map.iterator();

        while (it.hasNext()) {
            System.out.println(it.next().getKey() + " ");
        }
        System.out.println();

    }


    @Test
    public void testRemoveCase1() {
        AVLMap<Integer, String> map = new AVLMap<>();

        int[] array = {5, 2, 6, 1, 4, 7, 3};
        for (int key : array) {
            map.put(key, key + "");
        }

        System.out.println(map.remove(1));
        map.levelOrder();

        Iterator<AVLEntry<Integer, String>> it = map.iterator();

        while (it.hasNext()) {
            System.out.print(it.next().getKey() + " ");
        }
        System.out.println();

    }

    @Test
    public void testRemoveCase2() {
        AVLMap<Integer, String> map = new AVLMap<>();

        int[] array = {5, 2, 6, 1, 4, 7, 3};
        for (int key : array) {
            map.put(key, key + "");
        }

        System.out.println(map.remove(4));
        map.levelOrder();

        Iterator<AVLEntry<Integer, String>> it = map.iterator();

        while (it.hasNext()) {
            System.out.print(it.next().getKey() + " ");
        }
        System.out.println();

    }

    @Test
    public void testRemoveCase3() {
        AVLMap<Integer, String> map = new AVLMap<>();

        int[] array = {6, 2, 7, 1, 4, 8, 3, 5};
        for (int key : array) {
            map.put(key, key + "");
        }

        System.out.println(map.remove(2));
        map.levelOrder();

        Iterator<AVLEntry<Integer, String>> it = map.iterator();

        while (it.hasNext()) {
            System.out.print(it.next().getKey() + " ");
        }
        System.out.println();

    }

    @Test
    public void testRemoveWithJDK() {

        AVLMap<Integer, String> map1 = new AVLMap<>();
        TreeMap<Integer, String> map2 = new TreeMap<>();
        for (int i = 0; i < MAX2; i++) {
            Integer key = random.nextInt(MAX2);
            String value = random.nextInt(MAX2) + "";
            map1.put(key, value);
            map2.put(key, value);
        }
        print("map1.size", map1.size());
        map1.levelOrder();
        printAvlMap(map1);
        treeMapLevelOrder(map2);

        for (int i = 0; i < MAX2 >>> 1; i++) {
            Integer key = random.nextInt(MAX2);
            map1.levelOrder();
            if (map1.containsKey(key)) {
                Assert.assertTrue(map1.remove(key).equals(map2.remove(key)));
            } else {
                Assert.assertTrue(map1.remove(key) == null && map2.remove(key) == null);
            }
        }
        print("map1.size", map1.size());

        Assert.assertTrue(map1.size() == map2.size());
        Iterator<AVLEntry<Integer, String>> it1 = map1.iterator();
        Iterator<Map.Entry<Integer, String>> it2 = map2.entrySet().iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Assert.assertTrue(it1.next().getKey().equals(it2.next().getKey()));
        }
        Assert.assertTrue(!it1.hasNext() && !it2.hasNext());

    }

    @Test
    public void testAVLDelete() {

        AVLMap<Integer, String> map1 = new AVLMap<>();
        TreeMap<Integer, String> map2 = new TreeMap<>();
        for (int i = 0; i < MAX2; i++) {
            Integer key = random.nextInt(MAX2);
            String value = random.nextInt(MAX2) + "";
            map1.put(key, value);
            map2.put(key, value);
        }
        printAvlMap(map1);
        treeMapLevelOrder(map2);
        print("" + map1.size(), map2.size());
        for (int i = 0; i < MAX2 >> 1; i++) {
            Integer key = random.nextInt(MAX2);
            map1.remove(key);
            map2.remove(key);
        }
        map1.checkBalance();
        print("" + map1.size(), map2.size());
        Assert.assertTrue(map1.size() == map2.size());
        Iterator<AVLEntry<Integer, String>> it1 = map1.iterator();
        Iterator<Map.Entry<Integer, String>> it2 = map2.entrySet().iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Assert.assertTrue(it1.next().getKey().equals(it2.next().getKey()));
        }
        Assert.assertTrue(!it1.hasNext() && !it2.hasNext());
    }

    private void printAvlMap(AVLMap<Integer, String> root) {
        Iterator<AVLEntry<Integer, String>> it = root.iterator();
        while (it.hasNext()) {
            AVLEntry<Integer, String> entry = it.next();
            print("", entry.toString());
        }
        print("====================avlMap done======================================", "");
    }

    private void treeMapLevelOrder(TreeMap<Integer, String> root) {
        Iterator<Map.Entry<Integer, String>> it = root.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            print("", entry.toString());
        }
        print("====================treeMap done======================================", "");


    }

    private void print(String msg, Object o) {
        System.out.println(String.format("%s:%s", msg, o));
    }

}
