package sut.linebranchcoverage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import sut.ArrayNTree;

public class TestNTreeIterator {

	@Test
	public void testIterator() {
		List<Integer> list = Arrays.asList(39, 59, 17, 85, 41, 45);
		ArrayNTree<Integer> mArrayNTree = new ArrayNTree<>(list, 4);
		Iterator<Integer> temp = mArrayNTree.iterator();
	}
}
