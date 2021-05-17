# Sorting Algos Summary

## Performance Table



| Algorithm | Best Case  | Average Case  | Worst Case  | Space Complexity | Stable|
| ------------- | :-:|:-:| :-:| :-: | :-: |
| Selection Sort  |  *Ω(N^2)*  | *Θ(N^2)*    | *O(N^2)*   |*O(1)*    | No |
| Insertion Sort | *Ω(N)*  | *Θ(N^2)*   | *O(N^2)* |*O(1)*  | Yes |
| Shellsort | *Ω(N)*  | *Θ(N(lgN)^2)*   | *O(N(lgN)^2)* |*O(1)*  | No |
| Merge Sort  |  *Ω(NlgN)*  | *Θ(NlgN)*     | *O(NlgN)*   |*O(N)* | Yes | 
| Quicksort | *Ω(NlgN)*  | *Θ(NlgN)*    | *O(N^2)* |*O(N)*  | No |
| Heapsort | *Ω(NlgN)*|*Θ(NlgN)*   | *O(NlgN)*   |*O(1)*  | No|

## Comments on Each Algo

**Selection Sort:** it has a quadratic runtime regardless of the input because it will always scan through subarrays to find the minimum item, even if the input array is already sorted.

**Insertion Sort:** On average, its technically faster than Selection Sort by a factor of two because with a partially sorted array, it makes half number of compares than the worst case when the input size is in descending order. 
The best case is when the input array is already sorted, so there's no `insert` operation, the algo would just make `N-1` compares.

**Shellsort:** In the worst case scenario, it takes about `N^4/3 or N^3/2` compares to do the job, hence it's about `N(lgN)^2` for the average and the worst case. It is stil faster than insertion sort upon which this algorithm is derived from. However, the faster runtime is at the expense of stability since h-sort would involve moving items across long distances.

**Merge Sort:** It's going to be linearithmic for all kinds of scenarios because we are always recursively halving the array (which resutls in a tree with a height of log(n), on each level, there will be N compares taking place). The best case scenario is with the modification where the program checks whether the item immediate to the left of the pointer is smaller or equals to the item right of the pointer, if so, skip the merge, and proceed to the next function call on the recursion stack. This would improve the runtime by a linear factor. Another way is to automatically switch to insertion sort when the subarray has been reduced below certain threshold. This would improve the runtime by a small factor.

Note that we also have an iterative bottom-up approach that comes with an abstact in-place sort. However, the space complexity will alwasy be `O(N)` because even though it's a tree structure with `lgN` height, our algo can't perform parallel processing for each level of the tree, thus our call stack will be O(N) as it needs to store the other half of the array to be processes later.

**Quicksort:** Even though its worst case is theoretically quatratic, the fact that we shuffle the array will reduce the probability of such worst case happening to probabilistically negligible. Quicksort is recursive in nature, therefore requiring `O(N)` space complexity in the worst case. However, there are some forms of implementations that would reduce the worst case space complexity to linearithmic. It's in-place, which makes an ideal sorting algorithm when memory is constrained.

The average runtime for quicksort is linearithmic because we are always partitioning the array by half, which gives a tree of height of `lgN`, and on each level, it takes about `N` compares.

**Heapsort:** the runtime is linearithmic for all cases with constant space complexity because it sorts the array in-place without making recursive calls. It's linearithmic in runtime because the heap constructor takes `2N` times (the `sink` operation takes 2 compares each time, and is called ~N times), and the sink down sort operation takes 2NlgN times, for 2lnN is the number of compares in sinking and N because we want to remove and sink N elements.

Therefore, the upper-bound runtime is `O(NlgN)`. 
