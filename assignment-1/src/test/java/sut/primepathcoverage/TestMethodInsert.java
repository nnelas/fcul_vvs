package sut.primepathcoverage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import sut.ArrayNTree;

public class TestMethodInsert {

	/*
	 * TEST PATHS
	 * 
	 * a. [1,3,5,7,8,10,1,2] b. [1,3,5,7,8,10,1,3,4] c. [1,3,5,6,7,8,10,1,2] d.
	 * [1,3,5,6,7,8,10,1,3,4] e. [1,3,5,7,8,10,1,3,5,7,9] f.
	 * [1,3,5,7,8,11,12,15,1,2] g. [1,3,5,7,8,10,1,3,5,6,7,9] h.
	 * [1,3,5,7,8,11,12,15,1,3,4] i. [1,3,5,6,7,8,11,12,15,1,2] j.
	 * [1,3,5,6,7,8,11,12,15,1,3,4] k. [1,3,5,7,8,11,13,17,18,20,1,2] l.
	 * [1,3,5,7,8,10,1,3,5,7,8,10,1,2] m. [1,3,5,7,8,11,13,17,18,19,1,3,4] n.
	 * [1,3,5,7,8,10,1,3,5,7,8,11,12,14] o. [1,3,5,6,7,8,11,13,17,18,19,1,3,4] p.
	 * [1,3,5,7,8,10,1,3,5,6,7,8,11,12,14] q. [1,3,5,7,8,10,1,3,5,6,7,8,11,13,16] r.
	 * [1,3,5,7,8,11,13,17,18,19,1,3,5,7,9] s.
	 * [1,3,5,7,8,11,13,17,18,20,1,3,5,6,7,9] t.
	 * [1,3,5,7,8,11,13,17,18,19,1,3,5,6,7,9] u.
	 * [1,3,5,7,8,11,12,15,1,3,5,7,8,11,12,14] v.
	 * [1,3,5,7,8,11,12,15,1,3,5,6,7,8,10,1,2] w.
	 * [1,3,5,7,8,11,12,15,1,3,5,7,8,11,13,16] x.
	 * [1,3,5,7,8,11,12,15,1,3,5,6,7,8,11,12,14] y.
	 * [1,3,5,7,8,11,13,17,18,19,1,3,5,7,8,10,1,2] z.
	 * [1,3,5,7,8,11,13,17,18,20,1,3,5,7,8,10,1,2] 1.
	 * [1,3,5,7,8,10,1,3,5,7,8,11,13,17,18,19,1,2] 2.
	 * [1,3,5,7,8,10,1,3,5,7,8,11,13,17,18,20,1,2]
	 * 
	 * INFEASIBLE PATHS BECAUSE - when final is 2 tree can't be empty on second
	 * round - when final is 4 elem can't exist only for second round
	 * 
	 * a. [1,3,5,7,8,10,1,2] b. [1,3,5,7,8,10,1,3,4] c. [1,3,5,6,7,8,10,1,2] d.
	 * [1,3,5,6,7,8,10,1,3,4] f. [1,3,5,7,8,11,12,15,1,2] h.
	 * [1,3,5,7,8,11,12,15,1,3,4] i. [1,3,5,6,7,8,11,12,15,1,2] j.
	 * [1,3,5,6,7,8,11,12,15,1,3,4] k. [1,3,5,7,8,11,13,17,18,20,1,2] l.
	 * [1,3,5,7,8,10,1,3,5,7,8,10,1,2] m. [1,3,5,7,8,11,13,17,18,19,1,3,4] o.
	 * [1,3,5,6,7,8,11,13,17,18,19,1,3,4] v. [1,3,5,7,8,11,12,15,1,3,5,6,7,8,10,1,2]
	 * y. [1,3,5,7,8,11,13,17,18,19,1,3,5,7,8,10,1,2] z.
	 * [1,3,5,7,8,11,13,17,18,20,1,3,5,7,8,10,1,2] 1.
	 * [1,3,5,7,8,10,1,3,5,7,8,11,13,17,18,19,1,2] 2.
	 * [1,3,5,7,8,10,1,3,5,7,8,11,13,17,18,20,1,2]
	 * 
	 */

	// @Test
	public void testE() {
		// [1,3,5,7,8,10,1,3,5,7,9]

		Integer elem = 2;
		List<Integer> list = Arrays.asList(1, 3, 5);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 3);
		mArrayNTree.insert(elem);

		List<Integer> hold = Arrays.asList(1, 2, 3, 5);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(hold, 3);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

	// @Test
	public void testG() {
		// [1,3,5,7,8,10,1,3,5,6,7,9]

		// Infeasible because elem can't be bigger
		// than root at 1st iteration and then be smaller
		// than root when this.insert(previousValue);
	}

	@Test
	public void testN() {
		// [1,3,5,7,8,10,1,3,5,7,8,11,12,14]

		Integer elem = 2;
		List<Integer> list = Arrays.asList(1, 3, 5);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 3);
		mArrayNTree.insert(elem);

		List<Integer> hold = Arrays.asList(1, 2, 3, 5);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(hold, 3);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

	// @Test
	public void testP() {
		// [1,3,5,7,8,10,1,3,5,6,7,8,11,12,14]

		// Infeasible because elem can't be bigger
		// than root at 1st iteration and then be smaller
		// than root when this.insert(previousValue);
		fail("INFEASIBLE");
	}

	// @Test
	public void testQ() {
		// [1,3,5,7,8,10,1,3,5,6,7,8,11,13,16]

		// Infeasible because elem can't be bigger
		// than root at 1st iteration and then be smaller
		// than root when this.insert(previousValue);~
		fail("INFEASIBLE");
	}

	// @Test
	public void testR() {
		// [1,3,5,7,8,11,13,17,18,19,1,3,5,7,9]

		// inserir numa arvore nao vazia e nao pode ser uma folha 1-3 7-8
		// em que a base e menor do que o elemente que vamos inserir 5-7
		// nao e mais pequeno que TODOS os children
		// nao tem espaço livre e a posiçao != null
		/* nao element can be placed after an existing node N (there's space and it's
		 * larger than all children of N) but we must shift all those on the right
		 */
		//a capacidade esta cheia e o elemento e maior que todos os filhos
		
		//posiçao==capacidade
		//vai voltar a tentar inserir e quando tentar inserir vai verificar que é uma leaf
		//impossivel
		//INFEASIBLE
		fail("INFEASIBLE");
	}

	// @Test
	public void testS() {
		// [1,3,5,7,8,11,13,17,18,20,1,3,5,6,7,9]

		// Infeasible because elem can't be bigger
		// than root at 1st iteration and then be smaller
		// than root when this.insert(previousValue);
		fail("INFEASIBLE");
	}

	// @Test
	public void testT() {
		// [1,3,5,7,8,11,13,17,18,19,1,3,5,6,7,9]

		// Infeasible because elem can't be bigger
		// than root at 1st iteration and then be smaller
		// than root when this.insert(previousValue);
		fail("INFEASIBLE");
	}

	// @Test
	public void testU() {
		// [1,3,5,7,8,11,12,15,1,3,5,7,8,11,12,14]
		
		// inserir numa arvore nao vazia e nao pode ser uma folha 1-3 7-8
		// em que a base e menor do que o elemente que vamos inserir 5-7
		// nao e mais pequeno que TODOS os children
		// tem espaço livre ou a posiçao == null
		// o elemento nao e o maior do todos os childrens do ultimo child
		// depois inserir um element nessa posicao
		// esse elemento e o maior de todos os childrens

		fail("Not yet implemented");
		
	}

	// @Test
	public void testW() {
		// [1,3,5,7,8,11,12,15,1,3,5,7,8,11,13,16]
		fail("Not yet implemented");
	}

	// @Test
	public void testX() {
		// [1,3,5,7,8,11,12,15,1,3,5,6,7,8,11,12,14]

		// Infeasible because elem can't be bigger
		// than root at 1st iteration and then be smaller
		// than root when this.insert(previousValue);
		fail("INFEASIBLE");
	}

}
