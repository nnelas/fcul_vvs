package sut.quickcheck;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import sut.ArrayNTree;

@RunWith(JUnitQuickcheck.class)
public class QuickCheckProperties {

	/*
	 * Test case 1 Given a set of elements, shuﬄing their order of insertion does not break the n-tree’s invariant
	 * 
	 * É gerada uma lista de inteiros random e esta é inserida numa tree, de seguida esta lista é misturada
	 * e adiocionada a outra tree, no fim compara-se as duas para saber se são iguais
	 * */
	@Property 
	public void testShuffleOrderInsert(@From(ElementsGenerator.class) List<Integer> listOfElements) {
		ArrayNTree<Integer> treeOne = new ArrayNTree<>(2);
		ArrayNTree<Integer> treeTwo = new ArrayNTree<>(2);
		
		listOfElements.forEach((e) -> treeOne.insert(e));
		
		Collections.shuffle(listOfElements);
		
		listOfElements.forEach((e) -> treeTwo.insert(e));
		
		assertTrue(treeOne.equals(treeTwo));
	}

	/*
	 * Test case 2  If you remove all elements from a tree, the n-tree must be empty 
	 * 
	 * É gerada uma tree random e todos os elementos desta são removidos, no fim verifica-se se está vazia
	 * */
	@Property 
	public void testRemoveAll(@From(NTreeGenerator.class) ArrayNTree<Integer> arrayNTree) {
		List<Integer> listElements = arrayNTree.toList();
		listElements.forEach((e) -> arrayNTree.delete(e));
		assertTrue(arrayNTree.isEmpty());
	}
	
	/*
	 * Test case 3  Given a n-tree, inserting and then removing the same element will not modify other elements 
	 * 
	 * É gerada uma tree random e um inteiro random entre 1 e 50 , depois este é inserido e removido
	 * e depois é confirmado que a tree não sofreu alterações
	 * 
	 * */
	@Property 
	public void testInsertAndRemoveSameElement(@From(NTreeGenerator.class) ArrayNTree<Integer> arrayNTree) {
		ArrayNTree<Integer> aux = arrayNTree;
		Integer element = getRandomInt(1, 50);
		arrayNTree.insert(element);
		arrayNTree.delete(element);
		assertTrue(arrayNTree.equals(aux));
	}
	
	/*
	 * Test case 4  Given a n-tree, inserting all its elements again will produce no eﬀect
	 * 
	 *  É gerada uma tree random, vai se buscar a lista de todos os seu elementos e inserem-se todos
	 *  no fim é verificado que a tree se mantém igual
	 *  
	 * */
	@Property 
	public void testInsertAllSameElements(@From(NTreeGenerator.class) ArrayNTree<Integer> arrayNTree) {
		ArrayNTree<Integer> aux = arrayNTree;
		List<Integer> listElements = arrayNTree.toList();
		listElements.forEach((e) -> arrayNTree.insert(e));
		assertTrue(arrayNTree.equals(aux));
	}
	
	/*
	 * Test case 5  Given a n-tree, inserting an element already there several times over will produce no eﬀect
	 * 
	 * É gerada uma tree random, e vai se buscar um elemento da tree random, e depois este é inserido um número de vezes
	 * random na tree, no fim é verificado que a tree se mantém igual
	 * 
	 * */
	@Property 
	public void testInsertSameElement(@From(NTreeGenerator.class) ArrayNTree<Integer> arrayNTree) {
		ArrayNTree<Integer> aux = arrayNTree;
		List<Integer> listElements = arrayNTree.toList();
		Integer child = listElements.get(getRandomInt(0, listElements.size()));
		int timesToInsert = getRandomInt(1, 10); 
		while (timesToInsert-- > 0) {
			arrayNTree.insert(child);	
		}
		assertTrue(arrayNTree.equals(aux));
	}
	
	private int getRandomInt(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min)) + min);
	}
}
