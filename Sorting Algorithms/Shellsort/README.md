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

If we instruct the algorithm to print a number that represents the number of compares over the array size for each increment, then we can find that such number is a small constant for each `h`, regardless of what we put in.

Simply run `python3 test.py` to observe this. Here, we ran the test for three times, and when the array size is 100, here is the result from the three runs:

```cmd
Run 1
When h is 40, the number of compare is 73, the ratio is 0.6
When h is 13, the number of compare is 152, the ratio is 0.9
When h is 4, the number of compare is 219, the ratio is 1.1
When h is 1, the number of compare is 286, the ratio is 1.4

Run 2
When h is 40, the number of compare is 71, the ratio is 0.6
When h is 13, the number of compare is 153, the ratio is 0.9
When h is 4, the number of compare is 219, the ratio is 1.1
When h is 1, the number of compare is 272, the ratio is 1.4

Run 3
When h is 40, the number of compare is 77, the ratio is 0.6
When h is 13, the number of compare is 151, the ratio is 0.9
When h is 4, the number of compare is 252, the ratio is 1.3
When h is 1, the number of compare is 265, the ratio is 1.3
```
You can see that the results are basically the same.

We can run the same experiment on arrays with size of 10000:

```cmd
Run 1
When h is 9841, the number of compare is 159, the ratio is 0.5
When h is 3280, the number of compare is 9043, the ratio is 0.7
When h is 1093, the number of compare is 17360, the ratio is 1.0
When h is 364, the number of compare is 23767, the ratio is 1.2
When h is 121, the number of compare is 28913, the ratio is 1.5
When h is 40, the number of compare is 36812, the ratio is 1.8
When h is 13, the number of compare is 47609, the ratio is 2.4
When h is 4, the number of compare is 43160, the ratio is 2.2
When h is 1, the number of compare is 27543, the ratio is 1.4

Run 2
When h is 9841, the number of compare is 159, the ratio is 0.5
When h is 3280, the number of compare is 9008, the ratio is 0.7
When h is 1093, the number of compare is 17203, the ratio is 1.0
When h is 364, the number of compare is 23521, the ratio is 1.2
When h is 121, the number of compare is 29886, the ratio is 1.5
When h is 40, the number of compare is 36041, the ratio is 1.8
When h is 13, the number of compare is 46890, the ratio is 2.3
When h is 4, the number of compare is 45661, the ratio is 2.3
When h is 1, the number of compare is 27542, the ratio is 1.4

Run 3
When h is 9841, the number of compare is 159, the ratio is 0.5
When h is 3280, the number of compare is 9070, the ratio is 0.7
When h is 1093, the number of compare is 17222, the ratio is 1.0
When h is 364, the number of compare is 23947, the ratio is 1.2
When h is 121, the number of compare is 29565, the ratio is 1.5
When h is 40, the number of compare is 37490, the ratio is 1.9
When h is 13, the number of compare is 53237, the ratio is 2.7
When h is 4, the number of compare is 48279, the ratio is 2.4
When h is 1, the number of compare is 27938, the ratio is 1.4
```

With large array sizes, there are fewer variations for our constant. 