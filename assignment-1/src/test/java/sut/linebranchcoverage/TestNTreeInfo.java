package sut.linebranchcoverage;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import sut.ArrayNTree;

public class TestNTreeInfo {

	/*
	 * to coverage: 
	 *     line 1
	 *     branch 1
	 */

	@Test
	public void testInfo() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 0, 4), 2);

		assertTrue(mArrayNTree.info().equals("[0:[2][4]], size: 3, height: 2, nLeaves: 2"));
	}
}
