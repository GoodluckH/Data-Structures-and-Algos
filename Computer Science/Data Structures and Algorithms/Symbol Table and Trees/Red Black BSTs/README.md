# Balanced Search Trees

Although binary search trees enable us to perform search and insert operations in logarithmic time, we cannot guarantee that we get a balanced tree every time regardless of the input and the way we operate upon those inputs. Evidently, if someone insert elements in a sorted order, the traditional binary search tree will be a spindly tree, or like a tilted and vertical linked list. The runtime for search and insert, in this case, drops to linear. Therefore, we need more advanced data structures to obviate the worst case scenario. 

## 2-3 Search Trees

A 2-3 Search Tree consists of either empty or contains:
- A 2-node that contains one key and two links with the left one pointing to another 2-3 search tree with a smaller key, and the right link points a 2-3 search tree with larger keys.
- A 3-node that contains two keys and three links with the left one pointing to a smaller 2-3 search tree, the middle one to a 2-3 search tree with a keys between the two keys, and the right one to a 2-3 search tree with larger keys. 

A perfectly balanced 2-3 search tree is when all its null links have equal distance from the root node.

### Search
Like a BST, we simply evaluate whether the argument key is smaller, larger, or in-between keys in a node. And recursively search the sub-2-3 search trees until we get a search hit or miss.

### Insert
Before any modification, we need to run the a search to make sure that the key to be inserted does not exist in the tree already. If so, we just need to update the value. Otherwise, we have a couple of scenarios to consider.

***Insert into a 2-node.*** It's quite simple for this case, we just convert the 2-node into a 3-node by adding the key into the 2-node. Doing so will not affect the height of the tree. 

***Insert into a tree consisting of a single 3-node.*** We temporarily put the key into the 3-node so that it becomes a 4-nodes (with four links and three keys), then, we split the 4-node so that it becomes a 2-node tree, with the middle key being the parent node and the other two keys children. In this case, we increase the tree height by 1.

***Insert into a 3-node whose parent is a 2-node.*** In this case, we will have a temporary 4-node, but then we need to put the middle key to the parent 2-node so that the parent is a 3-node, then point the two links of the middle key to two other keys in our temporary 4-node. In this case, the tree is still balanced and the height unchanged.

***Insert into a 3-node whose parent is a 3-node.*** Similarly, we insert the middle key to the parent node and split the child 4-node into two individual nodes linked to the parent. But since the parent is a 3-node, after inserting the middle key, it will become a 4-node, too. So, we need to insert the parent's middle key to a higher level node, and split the 4-node. We keep doing this until we have inserted a middle key into a 2-node.

However, if the root is a 3-node, then we'd have to increase the height of the tree by one by applying the scenario where we insert into a tree consisting of a single 3-node.

### Runtime Analysis

Even in the worst case scenario for BSTs, where input keys are inserted in sorted order, a 2-3 search tree can still maintain a height of `lgN`. This is because everytime we insert a new key, we insert the key direct into an existing node. By contrast, to insert a key in a BST, we need to create and append a new node to the existing tree, therefore increases its height if keys are inserted in order.

So, the height of a 2-3 search tree is between `log_3 N` (when all nodes are 3-nodes) and `lgN` (if the tree is all 2-nodes). And the search operation just goes down the tree along a path, so the max length of the path is just the height of the tree. And for insertion, the worst case is when all nodes from the insertion point to root are all 3-nodes, in this case we travel all the way up, which is still the height of the tree, and increase the height by 1. So, these operations all run in logarithmic time, guaranteed. 

.....

The implementation of a 2-3 search tree is hard if we want to use the direct representation like we described above. So, we need to come up with a new representation of a 2-3 search tree so that our implementation is easier.

Introducing Red-Black BSTs.

## Red-Black BSTs

We can use different colors to encode 3-nodes. For example, we can use a *red* link between two nodes to represent a 3-node (see image below, credit: *Algorithms 4th Edition*).

 ![An illustration of red-black BST](https://i.imgur.com/N6FGNyY.png)
 
In this way, we can perform the  `get()` operation the same we do it for a traditional BST. 

More, we need to make sure that our red links are left leaning, which means only the links to the left node can be colored with red. To maintain this invariant, we need to introduce some *rotation* operations.

For example, if in our intermediate steps we have a right node that has a red link, we need to `rotateLeft` so that the red link points to a left node. To do this, we set the parent node as `x` and the right node with a red link pointing to it as `h`, then, we set the `x.right = h.left`, and `h.left = x`. Now we'd have demoted `x` to be a child node of `h`. 

To change update the links to their correct color, we set `h.color = x.color` and `x.color = RED`.  Note that the former statement allows `h` to inherit `x`'s color, which is unknown to us. And when we say a node's color, we mean the color of the link that points to this node. 

Lastly, we just need to update the sizes. So, `h.N = x.N`, this is because all we did was to switch `x` and `h`'s hierarchical structure, so `h`'s new size should be the same as that of `x`. But since we have demoted `x`, we need to recompute its size, thus `x.N = 1 + size(x.left) + size(x.right)`.

Together, the `rotateLeft` is a fairly straightforward method to implement. When we call the method, we are calling it on the parent node.

```java
Node rotateLeft(Node x)
{
	Node h = x.right;
	x.right = h.left;
	h.left = x;
	h.color = x.color;
	x.color = RED;
	h.N = x.N;
	x.N = 1 + size(x.left) + size(x.right);
	return h;  // we return the new parent
}
```

To implement `rotateRight` is even easier, we just need to swap left with right and vice versa. 

```java
Node rotateRight(Node x)
{
	Node h = x.left;
	x.left = h.right;
	h.right = x;
	h.color = x.color;
	x.color = RED;
	h.N = x.N;
	x.N = 1 + size(x.left) + size(x.right);
	return h; 
}
```

So, when do we use rotations anyways? Well, when it comes to inserting or calling `put` method, we always insert a red node. Therefore, a couple of scenarios here. 

***Inserting into a 2-node.*** If the key is smaller than the 2-node's key, then we are done. Otherwise, we insert the red node to the right of the 2-node, but then we `rotateLeft` on the parent to preserve our invariant that red links can only be on the left. 

***Inserting into a 3-node.*** We have three cases here: key smaller, larger, and in between. 
If the key is smaller, we put it to the left, but we will have two consecutive red links.  We need to `rotateRight` on the new node's parent, then either nodes of the new parent would be red. So, we `flipColors` on the new parent. 

```java
Node flipColors(Node x)
{
	x.left.color = BLACK;
	x.right.color = BLACK;
	x.color = RED;
	return x;
}
```
If the key is larger, then we just call `flipColors`.
If the key is in between, we'd first insert it to the right of a 3-node that is at a lower level, then, we call `rotateLeft` on parent so that we have two left-leaning red links. In this case, as our recursion unfolds back to the parent's parent, we call `rotateRight` so that we can simply `flipColors`.

Since our insertion will be done recursively, the rotation and color switching mechanisms only stop when encounters a 2-node or the root. Since the root cannot be a child node, we set `root.color = BLACK` after the insertion's done. 

Note that if the color of the root flips from black to red during out `put` operation, that means our tree has grown its height by 1.

```java
public void put(Key key, Value val)
{
	root = put(root, key, val);
	root.color = BLACK;
}

private Node put(Node x, Key key, Value val)
{
	if (x == null) return new Node(key, val, 1, RED);
	
	int cmp = key.compareTo(x.key);
	if (cmp < 0) x.left = put(x.left, key, val);
	else if (cmp > 0) x.right = put(x.right, key, val);
	else x.val = val;

	if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
	if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
	if (isRed(x.left) && isRed(x.right)) flipColors(x);
	x.N = 1 + size(x.left) + size(x.right);
	return x;
}
```
The deletion requires some ingenuity and restructuring of our 2-3 search tree. In short, we want to alter the property of the 2-3 search tree so that we can have some 4-nodes. It will be a top-down approach as we need to transform any 2-nodes into 3-nodes so that we don't end up with a 2-node at the bottom. Because we can't just replace it with a null link, doing so will violate our invariant. Instead, the transformation will make sure that when the present node is at the bottom, it will be either a 3-node or a 4-node, so we just need to delete the smallest key from it. And then we traverse back up to split the 4-nodes.

Although it's challenging to implement, the runtime of `delete` is guaranteed to be logarithmic because the always balanced property of our red black search tree.

### Runtime Analysis
Essentially, all of the operations take logarithmic time (except for `range` operation that have additional proportionality to the number of keys returned). This property is important because it gives an industrial strength implementation for symbol tables.

The worst case runtime is *2lgN* when we have a tree consists of 2-nodes except the leftmost path is all 3-nodes. The leftmost path, therefore, will have a depth twice of the rest of the tree. However, this case is rare to arise and the average length from root to any node is about *1lgN*.
