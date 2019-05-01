# Validação e Verificação de Software

## assignment-1

- Line and Branch Coverage for all public methods;
- Edge-Pair Coverage and at least 50% coverage for Prime Path Coverage for method insert;
- All-Coupling-Use Coverage for method delete and its private methods;
- Select and apply one Logic-based test coverage for method insert, justify your option.

For methods equals and equalTrees, include a test set based of Input State Partitioning, namely Base Choice Coverage, using the following characteristics:
1. Tree 1 is empty
2. Tree 2 is empty
3. Tree 2 is null
4. Tree 1 intersection of Tree 2 is empty/full/partial

Verify your test set via program mutation using PIT. Write a report com- paring the mutation coverage achieved by each criteria.
Use JUnit QuickCheck to create an n-tree random generator. Test the fol- lowing properties:
1. Given a set of elements, shuffling their order of insertion does not break the n-tree’s invariant
2. If you remove all elements from a tree, the n-tree must be empty
3. Given a n-tree, inserting and then removing the same element will not modify other elements
4. Given a n-tree, inserting all its elements again will produce no effect
5. Given a n-tree, inserting an element already there several times over will produce no effect

## Notes

- All of these tests were written to be used with JUnit 4
- Both Eclipse and Intellij compatible 