package sut.edgepaircoverage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import sut.ArrayNTree;

public class TestMethodInsert {

	/*
	 * REQUIREMENTS NEEDED
	 * 
	 * 1. [1,2]
	 * 2. [1,3,4]
	 * 3. [1,3,5]
	 * 4. [3,5,6]
	 * 5. [3,5,7]
	 * 6. [5,6,7]
	 * 7. [5,7,9]
	 * 8. [5,7,8]
	 * 9. [6,7,9]
	 * 10. [6,7,8]
	 * 11. [7,8,10]
	 * 12. [7,8,11]
	 * 13. [8,10,1]
	 * 14. [8,11,12]
	 * 15. [8,11,13]
	 * 16. [10,1,2]
	 * 17. [10,1,3]
	 * 18. [11,12,14]
	 * 19. [11,12,15]
	 * 20. [11,13,16]
	 * 21. [11,13,17]
	 * 22. [12,15,1]
	 * 23. [15,1,2]
	 * 24. [15,1,3]
	 * 25. [13,17,18]
	 * 26. [17,18,19]
	 * 27. [17,18,20]
	 * 28. [18,19,1]
	 * 29. [18,20,1]
	 * 30. [19,1,2]
	 * 31. [19,1,3]
	 * 32. [20,1,2]
	 * 33. [20,1,3]
	 * 
	 */

	/*
	 * TEST PATHS
	 * 
	 * a. [1,2]
	 * b. [1,3,4]
	 * c. [1,3,5,7,9]
	 * d. [1,3,5,6,7,9]
	 * e. [1,3,5,6,7,8,10,1,2]
	 * f. [1,3,5,6,7,8,10,1,3,4]
	 * g. [1,3,5,7,8,11,12,14]
	 * h. [1,3,5,7,8,11,13,16]
	 * i. [1,3,5,7,8,11,12,15,1,2]
	 * j. [1,3,5,7,8,11,12,15,1,3,4]
	 * k. [1,3,5,7,8,11,13,17,18,19,1,2]
	 * l. [1,3,5,7,8,11,13,17,18,19,1,3,4]
	 * m. [1,3,5,7,8,11,13,17,18,20,1,2]
	 * n. [1,3,5,7,8,11,13,17,18,20,1,3,4]
	 */

	@Test
	public void testA() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0]"));
	}

	@Test
	public void testB() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 0);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0]"));
	}

	@Test
	public void testC() {
		Integer elem = 1;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 1);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0:[1]]"));
	}

	@Test
	public void testD() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(1, 1);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0:[1]]"));
	}

	@Test
	public void testE() {

		/*
		 * TEST PATH: [1,3,5,6,7,8,10,1,2]
		 * 
		 * elem must be smaller than root
		 * can't be leaf
		 * elem must be smaller than all children
		 * tree must be empty next iteration
		 * 
		 * INFEASIBLE
		 */

	}

	@Test
	public void testF() {

		/* 
		 * TEST PATH: [1,3,5,6,7,8,10,1,3,4]
		 * 
		 * elem must be smaller than root
		 * can't be leaf
		 * elem must be smaller than all children
		 * root must already exist
		 * 
		 * INFEASIBLE
		 */

	}

	@Test
	public void testG() {

		/* 
		 * TEST PATH: [1,3,5,7,8,11,12,14]
		 * 
		 * elem must not be smaller than root
		 * can't be leaf
		 * elem cannot be smaller than all children
		 * capacity bigger than nChildren
		 * elem larger than all children of the last child
		 * 
		 */

		Integer elem = 4;
		List<Integer> list = Arrays.asList(1, 3);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 3);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[1:[3][4]]"));

	}

	@Test
	public void testH() {

		/* 
		 * TEST PATH: [1,3,5,7,8,11,13,16]
		 * 
		 * elem must not be smaller than root
		 * can't be leaf
		 * elem cannot be smaller than all children
		 * capacity bigger than nChildren but 
		 * 
		 */

		Integer elem = 4;
		List<Integer> list = Arrays.asList(1, 3, 5);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 3);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[1:[3][4][5]]"));

	}

	@Test
	public void testI() {

		/*
		 * TEST PATH: [1,3,5,7,8,11,12,15,1,2]
		 * 
		 * elem must be smaller than root
		 * can't be leaf
		 * elem must be smaller than all children
		 * tree must be empty next iteration
		 * 
		 * INFEASIBLE
		 */

	}

	@Test
	public void testJ() {

		/*
		 * TEST PATH: [1,3,5,7,8,11,12,15,1,3,4]
		 * 
		 * elem must be smaller than root
		 * can't be leaf
		 * elem must be smaller than all children
		 * root must already exist
		 * 
		 * INFEASIBLE
		 */

	}

	@Test
	public void testK() {

		/*
		 * TEST PATH: [1,3,5,7,8,11,13,17,18,19,1,2]
		 * 
		 * elem must be smaller than root
		 * can't be leaf
		 * elem must be smaller than all children
		 * tree must be empty next iteration
		 * 
		 * INFEASIBLE
		 */

	}

	@Test
	public void testL() {

		/*
		 * TEST PATH: [1,3,5,7,8,11,13,17,18,19,1,3,4]
		 * 
		 * elem must be smaller than root
		 * can't be leaf
		 * elem must be smaller than all children
		 * root must already exist
		 * 
		 * INFEASIBLE
		 */

	}

	@Test
	public void testM() {

		/*
		 * TEST PATH: [1,3,5,7,8,11,13,17,18,20,1,2]
		 * 
		 * elem must be smaller than root
		 * can't be leaf
		 * elem must be smaller than all children
		 * tree must be empty next iteration
		 * 
		 * INFEASIBLE
		 */

	}

	@Test
	public void testN() {

		/*
		 * TEST PATH: [1,3,5,7,8,11,13,17,18,20,1,3,4]
		 * 
		 * elem must be smaller than root
		 * can't be leaf
		 * elem must be smaller than all children
		 * root must already exist
		 * 
		 * INFEASIBLE
		 */

	}

}
