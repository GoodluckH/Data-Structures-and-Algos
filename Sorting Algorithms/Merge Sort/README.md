# Merge Sort

This is such a great algorithm! In fact, it's so efficient and stable that many programming languages actually implement their native sorting methods based on merge sort.

At the heart of the algo, it's the divide-and-conquer technique. Basically, we are splitting the array into half recursively until the smallest subarrays reach size 2. From there, we copy the subarray, `arr`, into an auxilary array, `aux`, then set up to pointers, `i` and `j`. Pointer `i` starts at the beginning of the subarray while `j` at the `mid + 1` position, then, we will just need to compare `aux[i]` with `aux[j]`.

If `aux[j] > aux[i]`, we'd put `aux[i]` in `arr[k]`, and increment `i` by one, and vice versa. Of course, there might be cases where everything from in the left array are smaller than those in the right. In this case, we would have exhausted our left array, evidenced by `i > mid`. In this case, all we need to do is to put the rest of the right-array to `arr`.

Once we have merged two subarrays, we return to the previous recursion call, where we do the whole operations again until we have sorted the entire initial array.

## Implementation

In my Python implementation, I have a very simple client function:

```python
# Client function
def mergesort(arr):
    sort(arr, 0, len(arr) - 1)
    return arr
```

The client function calls other functions (these functions are supposed to be private but it's hard to illustrate that in Python. In Java, we would just make these functions `private`):
```python
# Everything below is inaccessible to clients
def sort(arr, lo, hi):
    if hi <= lo:
        return
    
    mid = lo + (hi - lo) // 2

    sort(arr, lo, mid) # sort and merge the left array
    sort(arr, mid+1, hi) # sort and merge the right array

    merge(arr, lo, mid, hi)

# Top-down merge sort
def merge(arr, lo, mid, hi):
    i = lo
    j = mid + 1

    aux = arr[:]

    for k in range(lo, hi + 1):
        if i > mid:  # Exhausted the left array
            arr[k] = aux[j]
            j += 1
        elif j > hi: # Exhausted the right array
            arr[k] = aux[i]
            i += 1
        elif aux[j] < aux[i]:
            arr[k] = aux[j]
            j += 1
        else:
            arr[k] = aux[i]
            i += 1
```

A bottom-up approach can also do the job without recusion. We simply replace the `sort` method with `sortBU`:

```python
def sortBU(arr): 
    N = len(arr)
    sz = 1

    while sz < N:
        lo = 0
        while lo < N - sz:
            merge(arr, lo, lo + sz - 1, min(N-1, lo+sz+sz-1))
            lo += 2 * sz
        sz *= 2
```

The bottom-up approach simply reverse the process by sorting the smallest subarrays first and then the larger ones.


## Properties and Runtime

Note that we can improve the algorithm by insertion sorting the small arrays so that it will improve the runtime speed by about 20%. This is because the recursive calls can be expensive and redundant if the array size is small.

Another improvement is to see if the subarray is already in order, we do this by checking if `arr[mid] <= arr[mid + 1]`, if so, we don't need to call the `merge` method.

More, we can pass down the `aux` array from the client function and alternate the usage of `aux` and `arr` in our `sort` method so that we don't need to initiate a new array every time.

### Stability

Merge Sort is stable because for items that are equal to each other, we append the one from the left side to the `arr` array, so that the other equal item from the right array will come after the left one, which makes sense.

So, Merge Sort and Insertion Sort are the two that are stable because they handle "less than and equal to" properly without disrupting their positions.

As a counter example, Shellsort or Selection Sort is not stable. Imagine we have an array:

```
[B1, B2, A]
```

With Selection Sort, we'd swap `A` with `B1`, so the resulting array is `[A, B2, B1]`. Our `B` will not in proper order in this case. 

Similarly, Shellsort also have the tendency of catapulting elements across long distance. For example, we have

```
[B1, B2, B3, B4, A]
```

The resulting array after h-sort, for `h = 5`, would be `[A, B2, B3, B4, B1]`. Clearly, the `B`s are out of order.

### Runtime

We can see that for every "halfing", we generate two more subarrays for each array at the current level. So, the total height of the tree, would be `lgN`, because we have halfing all the way until smallest subarrays are of size 2. And for each level, we perform N comparisons. Therefore, the resulting expression would be `NlgN`. Now, the best case scenario is `NlgN`, worst case could be `6NlgN` (2N for comparison, 2N for copying to `aux`, and 2N for move back).
