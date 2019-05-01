package sut.linebranchcoverage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import sut.ArrayNTree;

public class TestNTreeContains {

	/*
	 * to coverage:
	 *     line 1, 2
	 *     branch 1
	 */
	
	@Test
	public void testWithEmptyTree() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0);
		assertFalse(mArrayNTree.contains(elem));
	}
	
	/*
	 * to coverage:
	 *     line 1, 3, 4
	 *     branch 2, 3
	 */
	
	@Test
	public void testWithTreeWithOneElement() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 0);
		assertTrue(mArrayNTree.contains(elem));
	}
	
	/*
	 * to coverage:
	 *     line 1, 3, 5, 6, 7, 8, 9, 10
	 *     branch 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
	 */
	
	@Test
	public void testWithElementsInsideList() {
		Integer elem = 45;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		assertTrue(mArrayNTree.contains(elem));
	}

}
