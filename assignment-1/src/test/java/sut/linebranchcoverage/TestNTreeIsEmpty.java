package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

public class TestNTreeIsEmpty {

	/*
	 * to coverage: 
	 *     line 1
	 *     branch 1
	 */

	@Test
	public void testWithEmptyTree() {
		ArrayNTree mArrayNTree = new ArrayNTree(0);
		assertTrue(mArrayNTree.isEmpty());
	}
}
