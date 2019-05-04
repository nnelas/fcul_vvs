package sut.linebranchcoverage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import sut.ArrayNTree;

public class TestNTreeClone {

	/*
	 * to coverage: 
	 *     line 1
	 *     branch 1
	 */

	@Test
	public void testCloneTree() {
		List<Integer> list = Arrays.asList(39);  
		ArrayNTree<Integer> tree = new ArrayNTree<>(list,4);
		ArrayNTree<Integer> clone = tree.clone();
		assertTrue(tree.equals(clone));
	}
}
