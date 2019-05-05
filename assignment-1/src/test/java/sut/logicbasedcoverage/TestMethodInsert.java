package sut.logicbasedcoverage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import sut.ArrayNTree;

public class TestMethodInsert {
	
	/*
	 * CLAUSE COVERAGE (CC)
	 * 
	 * PREDICATES
	 * 	p1. isEmpty()
	 * 	p2. contains(elem)
	 * 	p3. data.compareTo(elem)>0
	 * 	p4. isLeaf()
	 * 	p5. position==-1
	 * 	p6. nChildren<capacity && children[position] == null
	 * 	p7. elem.compareTo(children[position-1].max())>0
	 * 	p8. nChildren<capacity && elem.compareTo(children[position].max())>0
	 * 	p9. nChildren==capacity || elem.compareTo(children[position].max())<0
	 * 	p10. position==capacity
	 * 	
	 * 
	 * 
	 * CLAUSES
	 * 	c1. isEmpty()
	 * 	c2. contains(elem)
	 * 	c3. data.compareTo(elem)>0
	 * 	c4. isLeaf()
	 * 	c5. position==-1
	 * 	c6. nChildren<capacity
	 * 	c7. children[position] == null
	 * 	c8. elem.compareTo(children[position-1].max())>0
	 * 	c9. elem.compareTo(children[position].max())>0
	 * 	c10. nChildren==capacity
	 * 	c11. elem.compareTo(children[position].max())<0
	 * 	c12. position==capacity
	 * 
	 */
	
	/*
	 * PREDICATES WITH MORE THAN ONE CLAUSE
	 * 
	 * p6.
	 *   | nChildren<capacity | children[position] == null | nChildren<capacity && children[position] == null
	 *  1|          T         |             T              |                    T 
	 *  2|          T         |             F              |                    F
	 *  3|          F         |             T              |                    F
	 *  4|          F         |             F              |                    F
	 * 
	 * ***
	 * 
	 * p8.
	 *   | nChildren<capacity | elem.compareTo(children[position].max())>0 | nChildren<capacity && elem.compareTo(children[position].max())>0
	 *  1|          T         |                        T                   |                           T 
	 *  2|          T         |                        F                   |                           F
	 *  3|          F         |                        T                   |                           F
	 *  4|          F         |                        F                   |                           F
	 * 
	 * ***
	 * 
	 * p9.
	 *   | nChildren==capacity | elem.compareTo(children[position].max())<0 | nChildren==capacity || elem.compareTo(children[position].max())<0
	 *  1|          T          |                       T                    |                           T 
	 *  2|          T          |                       F                    |                           T
	 *  3|          F          |                       T                    |                           T
	 *  4|          F          |                       F                    |                           F
	 * 
	 */

	@Test
	public void testP1() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0]"));
	}
	
	@Test
	public void testP2() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 0);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0]"));
	}
	
	@Test
	public void testP3nP4() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(1, 1);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0:[1]]"));
	}

	@Test
	public void testP5nP9nP10() {
		Integer elem = 0;

		List<Integer> list = Arrays.asList(1, 2);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 1);
		mArrayNTree.insert(elem);
		
		assertTrue(mArrayNTree.toString().equals("[0:[1:[2]]]"));

	}
	
	@Test
	public void testP6nP7nP9() {
		Integer elem = 3;

		List<Integer> list = Arrays.asList(0, 1, 4);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 2);
		mArrayNTree.insert(elem);
		assertTrue(mArrayNTree.toString().equals("[0:[1:[3]][4]]"));
	}
	
	@Test
	public void testP8() {
		Integer elem = 2;

		List<Integer> list = Arrays.asList(0, 1, 3);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 3);
		mArrayNTree.insert(elem);
		assertTrue(mArrayNTree.toString().equals("[0:[1][2][3]]"));
	}

}
