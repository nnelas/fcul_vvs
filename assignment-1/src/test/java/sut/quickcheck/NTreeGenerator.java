package sut.quickcheck;

import java.util.LinkedList;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import sut.ArrayNTree;

public class NTreeGenerator extends Generator<ArrayNTree<Integer>> {

	public static final int MAX_TREE_VALUE = 100;
	public static final int MAX_CHILD_CAPACITY = 5;
	public static final int MAX_TREE_SIZE = 7;
	

	public NTreeGenerator(Class<ArrayNTree<Integer>> type) {
		super(type);
	}
	
	@Override
	public ArrayNTree<Integer> generate(SourceOfRandomness src, GenerationStatus status) {
		int size = 1 + src.nextInt(MAX_TREE_SIZE);
		LinkedList<Integer> list = new LinkedList<>();
		while(size-- > 0)
			list.add(src.nextInt(MAX_TREE_VALUE) + 1);  	
		return new ArrayNTree<>(list, src.nextInt(MAX_CHILD_CAPACITY) + 1);
	}

}
