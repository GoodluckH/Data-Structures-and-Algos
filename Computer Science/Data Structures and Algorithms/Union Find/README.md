# Overview
Union Find is one of the most basic data structures out there. The idea is to solve dynamic connectivity problems like percolation, social network, etc.

Usally, we want to use array lists to implement our UF constructor.

There are different ways of implementing an UF class, but they all shared the following APIs (Java representation):
 ```java
 public void UF(int n) // Construct an UF object with size n 
 public void union(int p, int q) // Union two items represented by integer IDs
 public int find(int p) // Return the compoenent to which P belongs
 public boolean connected(int p, int q) // Check if two query values share the same component
 public int count() // Return the number of components
 ``` 
 I used Java to represent APIs because this language shows all the data and return types. However, I decided to implement everything in Python because I took the course in Java so I don't want to rely on memorization.
 
 ## Quick Find
 
Most naive implementation is to maintain an array of size n. In python, we can simply run: 
```python
id = [x for x in range(n)]
```
This allows us to have a size-n array with ascending elements starting from 0 to n-1.

Quick Find simply means it takes constant, or O(1), time to perform the `find` operation. Before that, I want to show you how the mechanism works.

So, shortly after we construct a new `UF` object, we will have an array representation of our `UF` object. For example, if `n = 5`, then we will have this array:
`[0, 1, 2, 3, 4]`
The fascinating thing about arrays is that we can access elements using index numbers, which takes constant time. And the index number itself can be a representation of our elements. 

Now, if we call `union(1, 2)`, our array would look like one of the following, depending on different implementation philosophies:
```
[0, 1, 1, 3, 4]
[0, 2, 2, 3, 4]
```
So, let's say our implementation results the second array. Then, when we call `find(1)`, we will simply return `id[1]`, which gives us `2`. Since we are just returning the result of an array access, `find` takes O(1) time, hence 'Quick Find'.

However, the `union` operation will be slow because, consider we now call `union(1, 3)` from the above example. our resulting array will be:
```
[0, 3, 3, 3, 4]
```
When we call `union`, we are basically looping through each element to see if any of them belongs to the compoenent that `1` belongs to, if so, we change the element value to `3`.

This takes about O(N) time to run.

 ## Quick Union
 
 Similarly, we can find a way to get an O(1) runtime for our `union` operation at the expense of an O(N) `find` operation.
 
 Instead of looping through every element, when we call `union(p, q)`, we simply change `id[p]` to `q`. So, we are essentially creating a tree structure with hiarchical relationship between elements.
 
If we call `union(2, 4)`, our array would instead look like this:
 ```
 [0, 1, 4, 3, 4]
 ```
When we do `find(q)`, we need to check if `id[q] == q`, if not, that means `q` is not the root element. In other words, `q` has a parent.

And how to we trace all the way to the root? Well, we iteratively call `id[q]` with `q` being updated to `id[q]` each time.

So, in our example, the root of `2` is `id[id[2]]`, which is `id[4]`, which is `4`.

This gets more costly when we have a spindly tree where the `q` we are finding is a leaf node: 
```
1
 \
  2
   \
    3
     \
      4
       \
        0
```

Imagine we call `find(0)` on the above tree. Our algorithm would have to traverse all the way up to return `1`. And since this spindly tree has a depth of N-1. We say the `find` operation takes O(N) times. 


## Weighted Union Find

Is there a way to avoid any spindly tree? What if we always connect the smaller tree in size to the larger ones?

That would indeed solve our problem but at the expense of memory since we will have to create another array that keeps track of the sizes of each element.

So, if we are always attaching smaller trees to the larger ones, that means, in order for a tree to increase its depth by 1, there has to be another smaller tree with a depth level equal to or deeper than our tree in question.

Therefore, the size of our tree will always be `2^(height)`, so `height` is just lg n, or log n in upper bound. 

What about the `union` operation? Well, we need to perform `find` to get the root elements of our variable parameters. Then, we just need to change the smaller component's root element's value to that of the bigger one, which takes constant time. So, `union` should take O(log n) time to run as well.

## Weighted Union Find with Path Compression

We can do better!

Imagine whenever we `find(q)` , we change `id[q]` to the root element's value. Then, we would have a very flat tree. At this point, it's going to be very hard for the tree to increase in its height. 

So, we would have an inverse Ackermann function to describe the runtime, which is a(n), for a is alpha. 

This would be very close to constant runtime if we amortize the cost over time. 

# Summary Runtime

| Technique | Find | Union |
| ------ | ------ | ------ |
| Quick Find |  O(1) | O(n) |
| Quick Union | O(N) | O(1) | 
| Weighted Quick Find | O(log n) | O(log n) |
| Weighted Quick Find with Path Compression | O(a(n)) | O(a(n)) |
