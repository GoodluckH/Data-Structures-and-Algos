# Shellsort

Shellsort is a modified Insertion Sort that improves the runtime significantly. The philosophy behind is to perform Insertion Sort on a every `h` items, and `h`, in our implementation verison, reduces to its third until 1.

To determine the initial `h` to use, we run a loop:

```python
N = len(arr)
h = 1

# Initialize h
while h < N // 3:
    h = 3 * h + 1
```

Then, we'd perform an Insertion Sort operation with an additional outer-loop that reduces `h`:

```python
# Run the algo on h-sorted array
while h >= 1:
    i = h
    
    while i < N:
        j = i
        
        while j >= h and arr[j] < arr[j-h]:
            arr[j], arr[j-h] = arr[j-h], arr[j]
            j -= h

        i += 1
    h //= 3
```

The innermost loop is where the modification comes. Remember, Insertion Sort compares `arr[j]` with `arr[j-1]`, but since we have partitioned our array by `h`, we need to comp `arr[j]` with `arr[j-h]` instead.

To visualize how the algo works, we print out each iteration where two items that are being compared:

```cmd
Initial Array:
K Z C B V C T U G T U G C V E H

h = 13
K _ _ _ _ _ _ _ _ _ _ _ _ V _ _
_ Z _ _ _ _ _ _ _ _ _ _ _ _ E _
_ _ C _ _ _ _ _ _ _ _ _ _ _ _ H

h = 4
K _ _ _ V _ _ _ _ _ _ _ _ _ _ _
_ E _ _ _ C _ _ _ _ _ _ _ _ _ _
_ _ C _ _ _ T _ _ _ _ _ _ _ _ _
_ _ _ B _ _ _ U _ _ _ _ _ _ _ _
_ _ _ _ V _ _ _ G _ _ _ _ _ _ _
_ _ _ _ _ E _ _ _ T _ _ _ _ _ _
_ _ _ _ _ _ T _ _ _ U _ _ _ _ _
_ _ _ _ _ _ _ U _ _ _ G _ _ _ _
_ _ _ _ _ _ _ _ V _ _ _ C _ _ _
_ _ _ _ _ _ _ _ _ T _ _ _ V _ _
_ _ _ _ _ _ _ _ _ _ U _ _ _ Z _
_ _ _ _ _ _ _ _ _ _ _ U _ _ _ H

h = 1
C C _ _ _ _ _ _ _ _ _ _ _ _ _ _
_ C C _ _ _ _ _ _ _ _ _ _ _ _ _
_ _ C B _ _ _ _ _ _ _ _ _ _ _ _
_ _ _ C G _ _ _ _ _ _ _ _ _ _ _
_ _ _ _ G E _ _ _ _ _ _ _ _ _ _
_ _ _ _ _ G T _ _ _ _ _ _ _ _ _
_ _ _ _ _ _ T G _ _ _ _ _ _ _ _
_ _ _ _ _ _ _ T K _ _ _ _ _ _ _
_ _ _ _ _ _ _ _ T T _ _ _ _ _ _
_ _ _ _ _ _ _ _ _ T U _ _ _ _ _
_ _ _ _ _ _ _ _ _ _ U H _ _ _ _
_ _ _ _ _ _ _ _ _ _ _ U V _ _ _
_ _ _ _ _ _ _ _ _ _ _ _ V V _ _
_ _ _ _ _ _ _ _ _ _ _ _ _ V Z _
_ _ _ _ _ _ _ _ _ _ _ _ _ _ Z U

Resulting Array:
B C C C E G G H K T T U U V V Z
```

## Runtime Analysis
So, the moral of the story is we perform a bunch of high-level Insertion Sorts to make the array largely sorted, therefore when `h = 1`, the Insertion Sort is very fast. The runtime, however, is hard to obtain since it's sensitive to how we decide and use `h`. 

It appears to be linearithmic, or `O(nlogn)`, or `N^(5/4)`, `N^(4/3)`, etc.. But one thing that is clear is that it's definitely faster than Insertion Sort's `O(N^2)`runtime.

## Interesting Findings

If we instruct the algorithm to print a number that represents the number of compares over the array size for each increment, then we can find that such number is a small constant, regardless of what we put in.

Simply run `python3 test.py` to observe this:

```cmd
The array size is 100
When h is 40, constant is 0.01000
When h is 13, constant is 0.01000
When h is 4, constant is 0.01000
When h is 1, constant is 0.01000
-------------------- 

The array size is 1000
When h is 364, constant is 0.00100
When h is 121, constant is 0.00100
When h is 40, constant is 0.00100
When h is 13, constant is 0.00100
When h is 4, constant is 0.00100
When h is 1, constant is 0.00100
-------------------- 

The array size is 10000
When h is 9841, constant is 0.00010
When h is 3280, constant is 0.00010
When h is 1093, constant is 0.00010
When h is 364, constant is 0.00010
When h is 121, constant is 0.00010
When h is 40, constant is 0.00010
When h is 13, constant is 0.00010
When h is 4, constant is 0.00010
When h is 1, constant is 0.00010
-------------------- 
```

Note that the constant has a relationship with the array size. So the constant number is simple `1/N` where `N` is the array size.