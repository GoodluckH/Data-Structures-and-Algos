# Hash Tables
The key is to "hash" whatever we put in to something that can be easily referenced to and accessed. Many programming languages have built-in hash functions for primitive types like Integers, Strings, Float, etc. For ADTs, we can also implement our own hash function.

A good hash function has the following three characteristics:
1. It's *consistent*, meaning two equal items return the same hash code
2. It's *efficient* to compute
3. It can *uniformly distribute* the keys

There are primarily two ways of building a symbol table using hashing. The first one is Separate Chaining, where we maintain a list of linked lists. We get a hash code of the key we are putting on, and get the modulo on that hashcode to get the index position of the list. Then we insert the key to that index position. However, to avoid collisions, we need to build a linked list if there are existing different keys occupying the index position. Usually, we want to use a prime number for the size of our list to help uniformly distribute our keys. Therefore, on average, we should expect `N/M` runtime to access a key, for `N` is the total number of keys, and `M` the length of our list.

Another way is linear probing. Basically, we just need to maintain two lists, one for keys, another for values. And to avoid collisions, we "probe" the next slot in the list to see if it's empty, if so, we insert the key there; otherwise, keep probing. An important thing to consider is to implement `resize` properly for `put` and `delete` operations. This is because we don't want a full table, which will cause an infinite loop for the `search` operation. 

To get a sense on the runtime of linear probing is tricky. Here, we have a concept of "clustering", basically means subarrays. if we have subarrays with quite long length, then our performance will be poor because we'd have to go through the entire subarray, in the worst case, to get our key value pair.
