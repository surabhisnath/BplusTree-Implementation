# B+ Tree Implementation

Implemented the following 5 features of B+ tree:

1) Find (Value V) - Returns the leaf node L and index i such that L.Pi points to the first record with search value V. You will be required to load the index file in memory and then use that to locate the leaf node that contains the record with that key if any.
2) PrintAll (Value V) - Print all records with the search value V. You will be required to load the index file first and then perform the printing of records.
3) FindRange (L, U) - Find all records with search key values in a specified range (L, U) i.e. between L and U.
4) Insert (Record R) - Append the record R at the end of the datafile and update the index file accordingly.
5) Delete (Record R) - Delete the record R from the datafile and update the index file accordingly.
