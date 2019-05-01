package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

import java.util.Arrays;
import java.util.List;

public class TestNTreeMax {
	
	/*
	 * to coverage:
	 *     line 1, 2, 3
	 *     branch 1, 2
	 */

	@Test
	public void testWithElementsInsideList() {
		Integer expected = 85;
		List<Integer> list = Arrays.asList(39, 59, 17, 85);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 2);
		assertEquals(expected, mArrayNTree.max());
	}

}
