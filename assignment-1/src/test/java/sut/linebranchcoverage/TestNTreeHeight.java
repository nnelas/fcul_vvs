package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

import java.util.Arrays;
import java.util.List;

public class TestNTreeHeight {
	
	/*
	 * to coverage:
	 *     line 1, 2
	 *     branch 1
	 */
	
	@Test
	public void testWithEmptyTree() {
		ArrayNTree<?> mArrayNTree = new ArrayNTree<>(0);
		assertEquals(0, mArrayNTree.height());
	}
	
	/*
	 * to coverage:
	 *     line 1, 3, 4, 5, 6, 7, 8, 9, 10
	 *     branch 2, 3, 4, 5, 6, 7, 8
	 */

	@Test
	public void testWithElementsInsideList() {
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		assertEquals(3, mArrayNTree.height());
	}

}
