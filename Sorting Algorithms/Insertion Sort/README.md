# Insertion Sort

Insertion Sort is a simple sorting algorithm but can be quite powerful for practical useage. To begin, the algorithm simply look at the next item in line, and shift it to the left of the current index position, and inset it to the correct place until the left part is sorted.

We can see that one advantage is that if the array is largely sorted, then we don't need to perform any insertion operations. In real life, we usually have partially sorted iterable collections. Therefore, Insertion Sort can give a linear runtime in certain best-case scenarios where the array is almost sorted.

It appears that we can pass in array containing any supported comparable items, and our algorithm will run as desired.

```python 
def insertion_sort(arr):
    for i in range(1, len(arr)):
        j = i
        while j > 0 and arr[j] < arr[j - 1]:
            arr[j], arr[j - 1] = arr[j - 1], arr[j]
            j -= 1
    return arr

```

Note that both Insertion Sort and [Selection Sort](https://github.com/GoodluckH/Data-Structures-and-Algos/tree/main/Sorting%20Algorithms/Selection%20Sort) we implemented are memory efficient because we are modifying the input array in-place.

## Usage

Run the command line input to automatically run the file that gives you the option to run a runtime analysis:

```cmd
python3 insertion_sort.py
```

For the runtime analysis, we generate three arrays of the same size (n = 20000):
```
The sorted array looks like [0, 1, 2, 3, ..., 20000]

The randomly generated array looks like [3129, 1, 33, ..., 10003]

The reversely sorted array looks like [20000, 19999, ..., 3, 2, 1]
```

The exact runtimes, measured in seconds, vary with your hardware specs. For example, on our machine (MacBook Air 2020 with M1 chip), we have the following runtimes:

```cmd
Runtime on sorted array:  0 s
Runtime on randomly generated array:  14 s
Runtime on reversely sorted array:  29 s
```


## Runtime Analysis

If the array is sorted, then our runtime is `N - 1` because we don't have to perform any insertion operations. We are simply comparing `N - 1` items all the way to the end of the array.

In the worst case scenario (reversely sorted array), every next-in-line item will be shifted and inserted to the very begining of the array. So, the runtime for these operations is `1 + 2 + ... + (N - 1)`, or `N(N-1)/2`, or `~N^2`.

Our empirical evidence confirms that when the input array is sorted, Insertion Sort runs in `O(N)` time:

![Insertion Sort Runtime on Sorted Arrays](https://i.imgur.com/GXgq6GF.png)


When input arrays are reversely sorted, the runtime is exponential:

![Insertion Sort Runtime on Reversely Sorted Arrays](https://imgur.com/kZaT1nr.png)


For randomly generated arrays, the runtime is exponential as well but the coefficient of the exponential line should be less than or equal to that of the reversely sorted arrays:

![Insertion Sort Runtime on Randomly Generated Arrays](https://imgur.com/8SuEeXc.png)
