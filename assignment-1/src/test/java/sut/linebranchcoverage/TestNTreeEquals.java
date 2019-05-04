package sut.linebranchcoverage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import sut.ArrayNTree;

public class TestNTreeEquals {

	/*
	 * to coverage: 
	 *     line 1
	 *     branch 1
	 */

	@Test
	public void testEqualsTree() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 2);
		ArrayNTree<Integer> mArrayNTree2 = new ArrayNTree<Integer>(Arrays.asList(3, 4), 2);
		ArrayNTree<Integer> clone = mArrayNTree.clone(); 
		
		assertTrue(mArrayNTree.equals(mArrayNTree));
		assertTrue(mArrayNTree.equals(clone));
		assertFalse(mArrayNTree.equals(mArrayNTree2));
		assertFalse(mArrayNTree.equals(new String("Hello World")));
	}
}
