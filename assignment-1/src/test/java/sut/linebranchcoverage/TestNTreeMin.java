package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

public class TestNTreeMin {
	
	/*
	 * to coverage:
	 *     line 1
	 */

	@Test
	public void testWithTreeWithOneElement() {
		Integer expected = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(0, 0);
		assertEquals(expected, mArrayNTree.min());
	}
}
