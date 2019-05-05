package sut.allcouplingusecoverage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import sut.ArrayNTree;

public class TestMethodDelete {
	
	/*
	 * LAST-DEF (method, var, line) <-> FIRST-USE (method, var, line)
	 * 
	 * delete, data, 8				<-> delete, data, 1
	 * delete, position, 15			<-> delete, elem, 1
	 * 
	 */

	@Test
	public void testA() {
		Integer elem = 41;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<Integer>(list, 1);		
		mArrayNTree.delete(elem);

		assertTrue(mArrayNTree.toString().equals("[17:[39:[45:[59:[85]]]]]"));
	}
	
	@Test
	public void testB() {
		Integer elem = 86;
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		mArrayNTree.delete(elem);
		
		assertTrue(mArrayNTree.toString().equals("[17:[39][41:[45]][59][85]]"));
	}

}
