package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

import java.util.Arrays;
import java.util.List;

public class TestNTreeDelete {

	/*
	 * Testes para o primeiro if:
	 * VV verificar se esta vazio e o elemento nao existe na tree
	 * VF verificar se esta vazio e o elemento existe
	 * FV Verificar se não esta vazio e o elemento não existe
	 * FF Verficar se nao esta vazio e o elemento existe
	 * 
	 * O VF e impossivel testar pois se a tree esta vazia o elemento nao pode existir
	 * 
	 */
	
	/*
	 * to coverage:
	 *     line 1, 2
	 *     branch 1
	 */
	/*
	 * Verifica se esta vazio
	 */
	@Test
	public void testWithEmptyTree() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0);
		mArrayNTree.delete(elem);
		
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(0);
		assertEquals(mArrayNTree.info(), hArrayNTree.info());
	}
	
	
	/*
	 * verifica se o elemento nao existe na tree
	 */
	@Test
	public void testWithNonPresenteElemTree() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(3,0);
		mArrayNTree.delete(elem);
		
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(3,0);
		assertEquals(mArrayNTree.info(), hArrayNTree.info());
	}
	
	
	
	/*
	 * 
	 * Este metodo nao e necessario para testar a line and branch cover
	 *  
	 */
	/*
	 * to coverage:
	 *     line 1, 3, 4, 5
	 *     branch 2, 3, 4
	 */
//	@Test
//	public void testWithLeafNode() {
//		Integer elem = 0;
//		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(0, 0);
//		mArrayNTree.delete(elem);
//		
//		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(0, 0);
//		assertTrue(mArrayNTree.equals(hArrayNTree));
//	}
	
	@Test
	public void testWithLeafNodeNotEmpty() {
		Integer elem = 41;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(list, 1);		
		mArrayNTree.delete(elem);

		list = Arrays.asList(39, 59, 17, 85, 45);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<Integer>(list, 1);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

	@Test
	public void testWithLeafNodeEmpty() {
		Integer elem = 86;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		mArrayNTree.delete(elem);

		list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 4);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

	@Test
	public void testWithElemSmallAllChildren() {
		Integer elem = 38;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		mArrayNTree.delete(elem);

		list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 4);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}

}
