package sut.linebranchcoverage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import sut.ArrayNTree;


/*
 * Este iterator quando a colecao e vazia este tem um elemento que e null ou o ultimo numero que teve antes de ser apagado
 */
public class TestNTreeIterator {
	
	// O que é necessario testar
	// Source: https://stackoverflow.com/questions/14920164/how-to-junit-test-a-iterator
	
	/*
	 * Testar o iterator hasNext numa empty collection
	 */
	@Test
	public void testIteratorHasNextOnEmpty() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(4);
		
		Iterator<Integer> iterator = mArrayNTree.iterator();
		iterator.next();
		assertEquals(false, iterator.hasNext());
	}
	
	/*
	 * Testar o next do iterator numa empty collection
	 */
	@Test
	public void testIteratorNextOnEmpty() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(4);
		
		Iterator<Integer> iterator = mArrayNTree.iterator();
		iterator.next();
	    assertThrows(NoSuchElementException.class, () -> {
	        iterator.next();
	    });
	}
	
	/*
	 * Testar o iterator hasNext numa nonempty collection
	 */
	@Test
	public void testIteratorHasNextOnNonEmpty() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(3,4);
		
		Iterator<Integer> iterator = mArrayNTree.iterator();
		assertEquals(true, iterator.hasNext());
	}
	
	/*
	 * Testar o iterator next numa collection com 1 elemento
	 */
	@Test
	public void testIteratorNextOnOneElement() {
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(3,4);
		Iterator<Integer> iterator = mArrayNTree.iterator();
		assertEquals(Integer.valueOf(3), iterator.next());
	}
	
	/*
	 * Testar o iterator next numa collection com 2 ou mais elementos
	 */
	@Test
	public void testIteratorNextOnNonEmpty() {
		List<Integer> lista = Arrays.asList(3,4,5);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(lista,4);
		Iterator<Integer> iterator = mArrayNTree.iterator();
		assertEquals(Integer.valueOf(3), iterator.next());
	}
	
	/*
	 * Testar o iterator num todo, se a lista que ele da e igual a lista que temos
	 */
	@Test
	public void testIteratorListaToda() {
		List<Integer> lista = Arrays.asList(3,4,5);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(lista,4);
		Iterator<Integer> iterator = mArrayNTree.iterator();
		assertEquals(Integer.valueOf(3), iterator.next());
		assertEquals(Integer.valueOf(4), iterator.next());
		assertEquals(Integer.valueOf(5), iterator.next());
	}
	
}
