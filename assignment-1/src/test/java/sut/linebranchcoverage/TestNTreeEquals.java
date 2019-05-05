package sut.linebranchcoverage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import sut.ArrayNTree;

public class TestNTreeEquals {

	@Test
	public void testSameObject() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 2);		

		assertTrue(mArrayNTree.equals(mArrayNTree));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testNotSameObject() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 2);		

		assertFalse(mArrayNTree.equals(new String("Hello World")));
	}
	
	@Test
	public void testEqualsTree() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 2);
		ArrayNTree<Integer> mArrayNTree2 = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 2);
		
		assertTrue(mArrayNTree.equals(mArrayNTree2));
	}

	/*
	 * Not same by elements but same capacity
	 */
	@Test
	public void testNotEqualsTree1() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 2);
		ArrayNTree<Integer> mArrayNTree2 = new ArrayNTree<Integer>(Arrays.asList(3, 4), 2);
		
		assertFalse(mArrayNTree.equals(mArrayNTree2));
	}
//	
//	/*
//	 * Same by elements but not same capacity
//	 */
//	@Test
//	public void testNotEqualsTree2() {
//		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 2);
//		ArrayNTree<Integer> mArrayNTree2 = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 3);
//		
//		assertTrue(mArrayNTree.equals(mArrayNTree2));
//	}
//	
//	/*
//	 * Not same by elements but not same capacity
//	 */
//	@Test
//	public void testNotEqualsTree3() {
//		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(Arrays.asList(2, 3, 4), 2);
//		ArrayNTree<Integer> mArrayNTree2 = new ArrayNTree<Integer>(Arrays.asList(3, 4), 3);
//		
//		assertFalse(mArrayNTree.equals(mArrayNTree2));
//	}

}
