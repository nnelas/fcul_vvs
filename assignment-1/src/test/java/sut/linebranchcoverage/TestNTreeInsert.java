package sut.linebranchcoverage;

import static org.junit.Assert.*;

import org.junit.Test;
import sut.ArrayNTree;

import java.util.Arrays;
import java.util.List;

public class TestNTreeInsert {

	
	/*
	 * Cobrir o primeiro if, entrando nele
	 * a tree estar vazia
	 * 
	 * to coverage:
	 *     line 1, 2
	 *     branch isEmpty
	 */
	@Test
	public void testEmptyTree() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0);
		mArrayNTree.insert(elem);

		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(elem, 0);
		assertTrue(mArrayNTree.equals(hArrayNTree));
	}
	
	/*
	 * Cobrir o segundo if, entrando nele
	 * A arvore ja contem o elemento
	 * 
	 * to coverage:
	 *     line 1, 3, 4
	 *     branch !isEmpty, contains
	 */
	@Test
	public void testWitAlreadyInsertedElem() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 0);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0]"));
	}
	
	/*
	 * Cobrir o 3 e o 4 if, entrando neles
	 * A arvore e uma folha e vai inserir um elemento menor que a elemento ja la
	 * inserido
	 * 
	 * to coverage:
	 *     line 1, 3, 5, 6, 7 ,9
	 *     branch !isEmpty, !contains, compareTo>0, isLeaf
	 */
	@Test
	public void testLeafWithElemLesserThenData() {
		Integer elem = 0;
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(1, 1);
		mArrayNTree.insert(elem);

		assertTrue(mArrayNTree.toString().equals("[0:[1]]"));
	}
	
	/*
	 * Cobrir o 5 if, entrando neles
	 * O elemento que queremos colocar numa avore com alguns elementos e o menor elemento
	 * 
	 * to coverage:
	 *     line 1, 3, 5, 6, 7 ,8, 10, 3, 4,5,7,8,11,13,17,18,19
	 *     branch !isEmpty, !contains, compareTo>0, !isLeaf, position =-1 position != -1
	 */
	@Test
	public void testeInserirMenorDeTodosComCapacidadeAtingida() {
		Integer elem = 0;

		List<Integer> lista = Arrays.asList(1,2);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(lista, 1);
		mArrayNTree.insert(elem);
		
		assertTrue(mArrayNTree.toString().equals("[0:[1:[2]]]"));

	}
	
	/*
	 * Cobrir sem atingir a capacidade
	 */
	@Test
	public void testeInserirMenorDeTodosComCapacidadeSemAtingida() {
		Integer elem = 0;

		List<Integer> lista = Arrays.asList(1,2);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(lista, 2);
		mArrayNTree.insert(elem);
		assertTrue(mArrayNTree.toString().equals("[0:[1][2]]"));
	}
	
	/*
	 * Cobrir a atingir a capacidade
	 */
	@Test
	public void testeInserirNoDeTodosComMaisDe3ElementosSemCapacidade() {
		Integer elem = 3;

		List<Integer> lista = Arrays.asList(0,1,4);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(lista, 2);
		mArrayNTree.insert(elem);
		assertTrue(mArrayNTree.toString().equals("[0:[1:[3]][4]]"));
	}
	
	/*
	 * Cobrir a atingir a capacidade
	 */
	@Test
	public void testeInserirNoDeTodosComMaisDe3ElementosComCapacidade() {
		Integer elem = 2;

		List<Integer> lista = Arrays.asList(0,1,3);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(lista, 3);
		mArrayNTree.insert(elem);
		assertTrue(mArrayNTree.toString().equals("[0:[1][2][3]]"));
	}
	
	/*
	 * Cobrir a atingir a capacidade
	 */
	@Test
	public void teste() {
		Integer elem = 6;

		List<Integer> lista = Arrays.asList(7,8,9,10);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(lista, 3);
		mArrayNTree.insert(elem);
		System.out.println(mArrayNTree);
		assertTrue(mArrayNTree.toString().equals("[6:[7:[8]][9][10]]"));
	}
	
//	/*
//	 * to coverage:
//	 *     line 1, 5, 7, 9, 10, 11
//	 *     branch 2, 4, 6, 7
//	 */
//	@Test
//	public void testWithNewElem() {
//		Integer elem = 10;
//		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(0, 1);
//		mArrayNTree.insert(elem);
//
//		List<Integer> list = Arrays.asList(0, 10);
//		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 1);
//		assertTrue(mArrayNTree.equals(hArrayNTree));
//	}
//	
//	/*
//	 * to coverage:
//	 *     line 1, 2, 3, 4, 5, 7, 8, 9, 10
//	 *     branch 1, 2, 4, 5, 6, 8
//	 */
//	
//	@Test
//	public void testWithNewElemSmaller() {
//		Integer elem = 10;
//		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(20, 1);
//		mArrayNTree.insert(elem);
//
//		List<Integer> list = Arrays.asList(10, 20);
//		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 1);
//		assertTrue(mArrayNTree.equals(hArrayNTree));
//	}
//	
//	@Test
//	public void testWithElementsInsideListA() {
//		Integer elem = 65;
//		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
//		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
//		mArrayNTree.insert(elem);
//
//		list = Arrays.asList(39, 59, 17, 85, 41, 45, 65);
//		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 4);
//		assertTrue(mArrayNTree.equals(hArrayNTree));
//	}
//	
//	@Test
//	public void testWithElementsInsideListB() {
//		Integer elem = 1;
//		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
//		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 1);
//		mArrayNTree.insert(elem);
//
//		list = Arrays.asList(39, 59, 17, 85, 41, 45, 1);
//		ArrayNTree<Integer> hArrayNTree = new ArrayNTree<>(list, 1);
//		assertTrue(mArrayNTree.equals(hArrayNTree));
//	}

}
