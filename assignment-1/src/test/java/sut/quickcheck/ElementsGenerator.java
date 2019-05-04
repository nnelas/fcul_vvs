package sut.quickcheck;

import java.util.LinkedList;
import java.util.List;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class ElementsGenerator extends Generator<List<Integer>> {

	public static final int MAX_ELEMENT_VALUE = 100;
	public static final int MAX_LIST_SIZE = 7;
	

	public ElementsGenerator(Class<List<Integer>> type) {
		super(type);
	}
	
	@Override
	public List<Integer> generate(SourceOfRandomness src, GenerationStatus status) {
		int size = 1 + src.nextInt(MAX_LIST_SIZE);
		LinkedList<Integer> list = new LinkedList<>(); 
		while(size-- > 0) 
			list.add(src.nextInt(MAX_ELEMENT_VALUE)); 
		return list;
	}

}
