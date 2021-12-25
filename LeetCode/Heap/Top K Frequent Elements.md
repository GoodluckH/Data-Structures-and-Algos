# Top K Frequent Elements
https://leetcode.com/problems/top-k-frequent-elements/

## Description
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

## Approach
There are three approaches:
1. Use Min PQ so that we are only keeping the ones with the highest frequency counts
2. Bucket sort, this would give us a linear algorithm
3. Use quickselect, which would give us a linear runtime but in the worst case, it runs quadratic


## Implementation
Heap approach:
```java
public int[] topKFrequent(int[] nums, int k) {
  if (nums.length == k) return nums;
  Map<Integer, Integer> count = new HashMap<>();
  for (int n: nums) count.put(n, count.getOrDefault(n, 0) + 1);
  
  Queue<Integer> heap = new PriorityQueue<>((a, b) -> count.get(a) - count.get(b)); // Makes sure we are using a min-based PQ.
  
  for (int n: count.keySet()) {
    heap.add(n);
    if (heap.size() > k) heap.poll();
  }
  
  int[] ans = new int[k];
  for (int i = 0; i < k; i++) ans[i] = heap.poll();
  return ans;
}
```

Bucket Sort approach: 
```java
public int[] topKFrequent(int[] nums, int k) {
  if (nums.length == k) return nums;
  int[] ans = new int[k];
  List[] bucket = new List[nums.length + 1]; // +1 to reflect the real count number.
  Arrays.setAll(bucket, idx -> new ArrayList<Integer>());
  
  Map<Integer, Integer> count = new HashMap<>();
  for (int n: nums) count.put(n, count.getOrDefault(n, 0) + 1); 
  for (int n: count.keySet()) bucket[count.get(n)].add(n); 
  
  for (int i = bucket.length - 1; i >= 0; i--) {
    if (!bucket[i].isEmpty()) {
      for (int j = 0; j < bucket[i].size(); j++) {
        ans[k-- - 1] = (Integer) bucket[i].get(j);
        if (k <= 0) break; 
      }
    }
    if (k <= 0) break;
  }
  
  return ans;
}
```

Quickselect approach: 
```java
class Solution {
    Map<Integer, Integer> count;
    int[] unique; 
    
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == k) return nums;
        
        count = new HashMap<>();
        for (int n: nums) count.put(n, count.getOrDefault(n, 0) + 1);
        
        int n = count.size(), i = 0;
        unique = new int[n];        
        for (int num: count.keySet()) unique[i++] = num;
        
        quickselect(0, n - 1, n - k);
        return Arrays.copyOfRange(unique, n - k, n); 
        
    }
    
    private void quickselect(int left, int right, int k) {
        if (left == right) return;
        
        int p = left + (right - left) / 2; 
        p = partition(left, right, p);
        
        if (p == k) return;
        else if (p > k) quickselect(left, p - 1, k);
        else quickselect(p + 1, right, k);
    }
    
    private int partition(int left, int right, int p) {
        int storedIdx = left, pFreq = count.get(unique[p]);
        swap(right, p);
        
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pFreq) swap(storedIdx++, i);
        }
        swap(storedIdx, right); // remember p is at right's position now
        return storedIdx;
    }
    
    private void swap(int i, int j) {
        int t = unique[i];
        unique[i] = unique[j];
        unique[j] = t;
    }
    
}

```

## Complexity Analysis
1. Heap approach: Since we are using a heap, the swim and sink operation would take logN time, but since we are cappping the heap size to k, then our runtime is actually O(Nlogk). For space, it's O(N + k) since our hash map has no more than N items, and our heap will always have k items. 
2. Bucket sort approach: Immediately from the codes, we can see this is an O(N) runtime algorithm. As for space, it's essentially capped at O(N) since the `bucket` array is always less than `N` to capture the frequencies.
3. Quickselect approach: It's O(N) actually, because we are not recursively doing quicksort on both side of the pivot. We only interested in the part that makes sense to our mission. The unlikely worst case is O(N^2) when your pivot is the rightmost index and every other elements have smaller frequencies in a sorted fashion. As for space, it's O(N) managed by the hash map.
