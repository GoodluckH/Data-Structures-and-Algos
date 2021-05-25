# Symbol Tables

We need an efficient way to store and search things. Symbol tables provide a great solution to data processing problems. A symbol table can be thought of an associated array where the index of the array is the `key`, and it returns `value` associated with such `key`. In Python, this abstraction is well designed as users can add and search for keys as they would with a typical array.

## Sequential Search Symbol Table with Unordered Linked List

This is going to be a primitive implementation of a symbol table that does not have any adavanced features. The most essential operations that a client might use are `put`, `get`, `delete` and perhaps `contains` methods. It's quite straightforward to implement with a linked list. However, we are not able to rank keys to perform other computational tasks.

The `put` operation will run in *N* time in the worst case scenario because we need to go through the linked list to see if the key we are putting in already exists, if so, update the value, otherwise, insert a node to the beginning of the linked list. So if we were to insert N items in an empty symbol table, it will take *~N^2/2* runtime. 

The `get` operation runs in linear time as well because we need to run through the linked list to see if the key exists, if so, return the value associated, otherwise, return null.

Similarly, `delete` takes linear amount of compares to confirm a key's existence, and delete the node by setting the previous node's `next` to current node's `next`, thereby breaking up all the variables that point to the current node, allowing Java's garbage collector to extirpate the node.

