package graph;

import org.junit.jupiter.api.Test;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/23 21:46
 * @project: hello-java-algo
 */
public class AdjacencyMatrixListTest {

    @Test
    public void test_U_U() {
        AdjacencyMatrixList.U_U matrix = new AdjacencyMatrixList.U_U(6, 9);
        matrix.insert(0, 1);
        matrix.insert(0, 3);
        matrix.insert(0, 2);
        matrix.insert(0, 4);
        matrix.insert(1, 4);
        matrix.insert(2, 4);
        matrix.insert(2, 5);
        matrix.insert(3, 5);
        System.out.println(matrix);
    }

    @Test
    public void test_U_W() {
        AdjacencyMatrixList.U_W matrix = new AdjacencyMatrixList.U_W(6, 9);
        matrix.insert(0, 1, 1);
        matrix.insert(0, 3, 2);
        matrix.insert(0, 2, 3);
        matrix.insert(0, 4, 5);
        matrix.insert(1, 4, 1);
        matrix.insert(2, 4, 3);
        matrix.insert(2, 5, 7);
        matrix.insert(3, 5, 4);
        System.out.println(matrix);
    }

    @Test
    public void test_D_U() {
        AdjacencyMatrixList.D_U matrix = new AdjacencyMatrixList.D_U(6, 9);
        matrix.insert(0, 1);
        matrix.insert(0, 3);
        matrix.insert(0, 2);
        matrix.insert(0, 4);
        matrix.insert(1, 4);
        matrix.insert(2, 4);
        matrix.insert(2, 5);
        matrix.insert(3, 5);
        System.out.println(matrix);
    }

    @Test
    public void test_D_W() {
        AdjacencyMatrixList.D_W matrix = new AdjacencyMatrixList.D_W(6, 9);
        matrix.insert(0, 1, 1);
        matrix.insert(0, 3, 2);
        matrix.insert(0, 2, 3);
        matrix.insert(0, 4, 5);
        matrix.insert(1, 4, 1);
        matrix.insert(2, 4, 3);
        matrix.insert(2, 5, 7);
        matrix.insert(3, 5, 4);
        System.out.println(matrix);
    }
}
