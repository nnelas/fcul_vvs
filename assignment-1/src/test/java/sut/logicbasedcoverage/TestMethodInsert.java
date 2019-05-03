package sut.logicbasedcoverage;

import static org.junit.Assert.*;

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
	 *  c9. elem.compareTo(children[position].max())>0
	 *  c10. nChildren==capacity
	 *  c11. elem.compareTo(children[position].max())<0
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

	/*
	 * to coverage
	 * 		p1
	 * 		c1
	 */
	
	@Test
	public void testPOne() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0);
		mArrayNTree.insert(0);

		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(0, 0);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}
	
	/*
	 * to coverage
	 * 		!p1, p2
	 * 		!c1, p2
	 */
	
	@Test
	public void testPTwo() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 0);
		mArrayNTree.insert(elem);

		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(0, 0);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

}
