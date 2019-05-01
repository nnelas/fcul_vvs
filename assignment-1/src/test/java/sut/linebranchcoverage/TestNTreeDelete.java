package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

import java.util.Arrays;
import java.util.List;

public class TestNTreeDelete {

	/*
	 * to coverage:
	 *     line 1, 2
	 *     branch 1
	 */
	
	@Test
	public void testWithEmptyTree() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0);
		mArrayNTree.delete(elem);
		
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(0);
		assertEquals(mArrayNTree.info(), hArrayNTree.info());
	}
	
	/*
	 * to coverage:
	 *     line 1, 3, 4, 5
	 *     branch 2, 3, 4
	 */

	@Test
	public void testWithLeafNode() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(0, 0);
		mArrayNTree.delete(elem);
		
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(0, 0);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}
	
	@Test
	public void testWithLeafNodeNotEmpty() {
		Integer elem = 41;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(list, 1);		
		mArrayNTree.delete(elem);

		list = Arrays.asList(39, 59, 17, 85, 45);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<Integer>(list, 1);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

	@Test
	public void testWithLeafNodeEmpty() {
		Integer elem = 86;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		mArrayNTree.delete(elem);

		list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 4);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

	@Test
	public void testWithElemSmallAllChildren() {
		Integer elem = 38;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		mArrayNTree.delete(elem);

		list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 4);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

}
