package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

import java.util.Arrays;
import java.util.List;

public class TestNTreeIsLeaf {

	/*
	 * to coverage: 
	 *     line 1
	 *     branch 1
	 */

	@Test
	public void testWithEmptyTree() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0);
		assertFalse(mArrayNTree.isLeaf());
	}
	
	/*
	 * to coverage: 
	 *     branch 2, 3, 4
	 */

	@Test
	public void testWithElementsInsideList() {
		List<Integer> list = Arrays.asList(39, 59, 17, 85);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(list, 1);
		assertFalse(mArrayNTree.isLeaf());
	}
	
	@Test
	public void testWithOneElem() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(1,0);
		assertTrue(mArrayNTree.isLeaf());
	}

}
