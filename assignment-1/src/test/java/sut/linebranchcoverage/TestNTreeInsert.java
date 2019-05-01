package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

import java.util.Arrays;
import java.util.List;

public class TestNTreeInsert {

	/*
	 * to coverage:
	 *     line 1, 5, 6
	 *     branch 2, 3
	 */

	@Test
	public void testWitAlreadyInsertedElem() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 0);
		mArrayNTree.insert(elem);

		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(0, 0);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}
	
	/*
	 * to coverage:
	 *     line 1, 5, 7, 9, 10, 11
	 *     branch 2, 4, 6, 7
	 */
	
	@Test
	public void testWithNewElem() {
		Integer elem = 10;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 1);
		mArrayNTree.insert(elem);

		List<Integer> list = Arrays.asList(0, 10);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 1);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}
	
	/*
	 * to coverage:
	 *     line 1, 2, 3, 4, 5, 7, 8, 9, 10
	 *     branch 1, 2, 4, 5, 6, 8
	 */
	
	@Test
	public void testWithNewElemSmaller() {
		Integer elem = 10;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(20, 1);
		mArrayNTree.insert(elem);

		List<Integer> list = Arrays.asList(10, 20);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 1);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}
	
	@Test
	public void testWithElementsInsideListA() {
		Integer elem = 65;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		mArrayNTree.insert(elem);

		list = Arrays.asList(39, 59, 17, 85, 41, 45, 65);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 4);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}
	
	@Test
	public void testWithElementsInsideListB() {
		Integer elem = 1;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 1);
		mArrayNTree.insert(elem);

		list = Arrays.asList(39, 59, 17, 85, 41, 45, 1);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 1);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

}
