package redblacktree;

import binarysearchtree.ITree;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tree_2_3.Tree_2_3;

import java.util.Random;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/17 12:59
 * @project: hello-java-algo
 */
public class RedBlackTreeTest {
    private final Logger logger = LoggerFactory.getLogger(RedBlackTreeTest.class);

    @Test
    public void test_insert(){
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.insert(100);
        System.out.println(redBlackTree);
        redBlackTree.insert(90);
        System.out.println(redBlackTree);
        redBlackTree.insert(110);
        System.out.println(redBlackTree);
        redBlackTree.insert(120);
        System.out.println(redBlackTree);
    }

    @Test
    public void test_insert_fix(){
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.insert(100);
        System.out.println(redBlackTree);
        redBlackTree.insert(90);
        System.out.println(redBlackTree);
        redBlackTree.insert(110);
        System.out.println(redBlackTree);
        redBlackTree.insert(80);
        System.out.println(redBlackTree);
    }


    @Test
    public void test_insert_incr() {
        Tree_2_3 tree_23 = new Tree_2_3();
        ITree tree_rb = new RedBlackTree();
        for (int i = 1; i <= 5; i++) {
            tree_23.insert(i);
            tree_rb.insert(i);
            System.out.println("=================================================================================\n");
            System.out.println(tree_23);
            System.out.println(tree_rb);
        }
    }

    @Test
    public void test_insert_decr() {
        ITree tree_rb = new RedBlackTree();
        for (int i = 4; i >= 1; i--) {
            tree_rb.insert(i);
            System.out.println(tree_rb);
        }
    }

    /**
     * 2-3 树 VS 红黑树：通过染色调衡
     */
    @Test
    public void test_insert_dye() {
        Tree_2_3 tree_23 = new Tree_2_3();
        ITree tree_rb = new RedBlackTree();

        for (int i : new int[]{4, 5, 3, 1, 2}) {
            tree_23.insert(i);
            tree_rb.insert(i);
            System.out.println("=================================================================================\n");
            System.out.println(tree_23);
            System.out.println(tree_rb);
        }
    }

    @Test
    public void test_rl() {
        ITree tree_rb = new RedBlackTree();
        int[] ints = {3, 2, 4, 1, 5};
        for (int i : ints) {
            tree_rb.insert(i);
            System.out.println(tree_rb);
        }
    }

    @Test
    public void test_binary_search_tree() {
        ITree tree = new RedBlackTree();
        for (int i = 0; i < 20; i++) {
            tree.insert(new Random().nextInt(100));
        }
        System.out.println(tree);
    }

    @Test
    public void test_delete() {
        ITree tree = new RedBlackTree();
        tree.insert(2);
        tree.insert(5);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(3);

        System.out.println(tree);
        tree.delete(6);
        System.out.println(tree);
    }

    @Test
    public void test_2_3_insert() {
        Tree_2_3 tree = new Tree_2_3();
        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(3);

        System.out.println(tree);
    }
}
