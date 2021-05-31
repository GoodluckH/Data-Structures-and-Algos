# Selection Sort

Selection Sort works by selecting the smallest item and putting it in the front of the array by swapping it with the first item of the array. Then, we proceed to find the second smallest item and swap it with the second item of the array. Since we are always _selecting_, thus the name for this sorting algorithm. 

We should be able to run this algorithm on arrays with all kinds of comparable objects. Python has made that easy for us.

## Implementation

We simply start with index 0 of the array, which is the first item, and we assume it as the smallest value. For the part of the array to the right of the first item, we run through each item to see if such item is smaller than our assumed smallest value. If so, we update the smallest value. When we have reached to the end of the array, we swap the smallest value with the first item.

So, we continue to advance our pointer to index `i`, and run the loop for the portion of the array starting at `i + 1`, and we swap the smallest value found from our loop operation with whatever item on index `i`.

Our code looks like this:

```python
def selection_sort(arr):
    i = 0

    for i in range(len(arr)):
        m = i
        j = i + 1

        while j < len(arr):
            if arr[j] < arr[m]:
                m = j
            j += 1

        arr[i], arr[m] = arr[m], arr[i]
        i += 1
    return arr
```

It is a very simple algorithm to implement. 

## Usage

We added more features on our Python file to allow users create an n-size array with random numbers populated. All the interactions can be done through command line:

```
python3 selection_sort.py
```

## Runtime Analysis

Starting at index 0, we need to loop through `N-1` items to find the smallest value, then we move to index 1, thereby looping through `N-2` items, so it goes. In aggregate, we can observe that it takes `(N - 1) + (N - 2) + (N - 3) + ... + 1` numbers of array access. And this can be simplied to `N(N-1)/2` or `~N^2`. Clearly, this is not an efficient algorithm due to its exponential runtime.

We have nicely provided a function to visualize the runtime. You can see this in your interaction with the command line prompts.


![selection sort runtime on input size up to 5000](https://imgur.com/WGbUt6b.png)

Here, we have plotted runtime in milliseconds different input sizes (array sizes) up to 5000. It is clear to see the exponential pattern on the graph. The sudden drop at the end is caused other hardware-specific factors since we use the library `datetime` to estimate the runtime.
