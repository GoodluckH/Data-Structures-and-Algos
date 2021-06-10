# Symbol Tables

We need an efficient way to store and search things. Symbol tables provide a great solution to data processing problems. A symbol table can be thought of an associated array where the index of the array is the `key`, and it returns `value` associated with such `key`. In Python, this abstraction is well designed as users can add and search for keys as they would with a typical array.

Here's a summary of the cost for different implementations on various of operations.

|Implementation| Worst Case Insert|Worst Case Search|Average Case Insert|Average Case Search| Support Ordered Operations?|
|:--|:--:|:--:|:--:|:--:|:--:|
|*Sequential Search with unordered linked lists*| N | N | N | N/2| No|
|*Binary Search with ordered arrays*| N | lgN | N/2 | lgN | Yes |
|*Binary Tree Search with BST*| N | N | 1.39lgN | 1.39lgN| Yes|
|*2-3 Tree Search with red-black BST*| 2lgN | 2lgN | 1.00lgN | 1.00lgN| Yes|
|*Hash Table with Separate Chaining*| lgN | lgN | 3-5 | 3-5 |No|

