package xu.spring.ioc.bstAVL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xu
 * @date 2020/3/27 19:36
 * @description:
 */
public class BSTIterator {

    private Iterator<Integer> itr;

    public BSTIterator(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        itr = list.iterator();
    }

    private void inOrder(TreeNode p, List list) {

        if (null != p) {
            inOrder(p.left, list);
            list.add(p.val);
            inOrder(p.right, list);
        }
    }

    public boolean hasNext() {
        return itr.hasNext();
    }

    public int next() {
        return itr.next();
    }
}
