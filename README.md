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

### Notes

- All of these tests were written to be used with JUnit 4
- Both Eclipse and Intellij compatible 

## assignment-2

1. Use HtmlUnit to perform and test the following narratives in the webapp running on Wildfly:
(a) insert a new address for an existing customer, then the table of ad- dresses of that client includes that address and its total row size increases by one;
(b) get the first customer listed in the List All Customers use case, then try to insert it again and check if the expected error appears;
(c) create a new customer, them remove him, and check if the list of all clients does not change;
(d) create a new sale for an existing customer, insert a delivery for that sale and then show the sale delivery. Check that all intermediate pages have the expected information.

2. Use DbSetup to populate the database with sample data. These data should be organized to test the following tasks:
(a) after the update of a costumer contact, that information should be properly saved;
(b) after deleting all but one costumer, the list of all customers should have only that remaining customer;
(c) after deleting a certain costumer, its deliveries should be removed from the database;
(d) after deleting a certain costumer, it’s possible to add it back without lifting exceptions;
(e) adding a new delivery increases the total number of all deliveries by one;
Add two extra tests concerning the expected behaviour of sales.

3. Is it possible to mock some SUT business layer’s modules (in this case, services) in order to remove dependencies and test these modules separa- tely? If yes, pick two modules A and B with dependency A → B, mock A and test B using Mockito. If not, explain why you cannot use Mockito as it is, and propose what kind of refactoring would the SUT’s business layer need, in order to perform these mocking tests (show an example to explain your methodology).
