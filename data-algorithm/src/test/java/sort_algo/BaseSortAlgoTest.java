package sort_algo;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/21 18:56
 * @project: hello-java-algo
 */
public class BaseSortAlgoTest {

	public void sortBeforePrint(int[] a){
		System.out.println("before sort:");
		for (int j : a) {
			System.out.printf("%d ", j);
		}
		System.out.println();
	}

	public void sortAfterPrint(int[] a){
		System.out.println("after   sort:");
		for (int j : a) {
			System.out.printf("%d ", j);
		}
		System.out.println();
	}
}
