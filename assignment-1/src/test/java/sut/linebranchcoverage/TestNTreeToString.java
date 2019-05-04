package sut.linebranchcoverage;

import java.util.Arrays;

import org.junit.Test;

import sut.ArrayNTree;

public class TestNTreeToString {

	/*
	 * to coverage: 
	 *     line 1
	 *     branch 1
	 */

	@Test
	public void testEqualsTree() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 0, 4), 2);
		ArrayNTree<Integer> clone = new ArrayNTree<Integer>(0); 
		
		//Rest of string and brt null
		mArrayNTree.delete(4);
		mArrayNTree.toString();
		
		//Empty and leaf
		clone.toString();
	}
}
