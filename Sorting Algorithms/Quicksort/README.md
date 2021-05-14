# Quicksort

Quicksort has been around since Antony Hoare discovered it in 1960s. The sorting algorithm is in-place and very efficient for practical applications. Modifications like Three Way partitioning make Quicksort a preferred method for data collections containing duplicate values, which is common in real life.

It works by first shuffling the array, doing so to prevent rare cases where it might take quadratic time to compute. Then, we select an item, usually the first item since we already shuffled array, as our partition object, `v`. Then, starting from the beginning of the array, indexed with `i` and the end of the array, indexed with `j`, we compare `a[i]` and `a[j]` with `v`.

We want to swap `a[i]` with `a[j]` if the former is larger than and the latter is smaller than `v`. In this way, we make sure that when `i` and `j` crosses, everything to the left will be smaller than `v` and vice versa. The last step is simply to swap `v` with `a[j]`, then return `j`, which will be used in the `sort` method to recursively partition the rest of the array.

The plain vanilla implementation is as below:

```python
import random

def quicksort(a):
    random.shuffle(a)
    sort(a, 0, len(a) - 1)
    return a

def sort(a, lo, hi):
    if (hi <= lo): return
    
    j = partition(a, lo, hi)
    sort(a, lo, j - 1)
    sort(a, j + 1, hi)
    

def partition(a, lo, hi):
    i, j = lo + 1, hi
    v = a[lo]

    while True:
        while a[i] < v and i <= hi: i += 1
        while a[j] > v: j -= 1
        if j <= i: break
        a[i], a[j] = a[j], a[i]
    a[j], a[lo] = a[lo], a[j]
    return j
    
```

This one works well as long as the array mostly contains distinctive keys. However, in real life, we usually have duplicate keys. A modification is to use the Three Way partition technique that partitions an array into three parts, one contains elements less than `v`, one equals to `v`, and one larger than `v`:

```python
def sort(a, lo, hi):
    if (hi <= lo): return
    
    ls, i, gt = lo, lo + 1, hi
    v = a[lo]

    while i <= gt:
        if a[i] < v: 
            a[ls], a[i] = a[i], a[ls]
            ls += 1
            i += 1
        elif a[i] > v:
            a[i], a[gt] = a[gt], a[i]
            gt -= 1
        else: i += 1
    
    sort(a, lo, ls - 1)
    sort(a, gt + 1, hi)
```

Note that we no long need a separate `partition` function, we can processes everything recursively within the `sort`.

## Runtime Analysis. 

Technically, the default Quicksort method has an upperbound of *1.38NlgN* runtime, therefore making it about 38% slower than mergesort but in reality should be faster because there's no auxiliary arrays and data movements involved in Quicksort.

For Three Way partitioning method, the best case can be linear. Or strictly, *NH - N*, where *H* is the Shannon entropy. 

For both methods, the worst case can be *N^2*, but since we shuffle the array, we can discount this scenario since the probability for it to happen is *de minimis*.
