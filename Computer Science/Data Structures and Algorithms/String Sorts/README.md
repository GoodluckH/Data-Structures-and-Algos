# String Sorts

**Runtime Analysis**
| Algorithm | Runtime | Space | Notes | Stable? | In-place?  
|:--:|:--:|:--:|:--:|:--:|:--:
| insertion sort | between *N* and *N^2* | 1 | Good for small and nearly sorted arrays | yes | yes |
| quicksort | *N*log*N* | log*N* | general purpose when space is tight | no | yes |
| mergesort | *N*log*N* | *N* | general purpose stable sort | yes | yes |
| 3-way quicksort | between *N* and *N*log*N* | log*N* | general purpose stable sort | no | yes |
| LSD | *WN* | *N* | small fixed-length strings | yes | no |
| MSD | between *N* and *wN* | *N* + *WR* | random strings | yes | no |
| 3-way string quicksort | between *N* and *wN* | *W* + log*N* | general-purpose, strings with long prefix | no | yes |


## LSD String Sort
LSD stands for leasat significant digit, which means we examine the input from right to left. This sorting algorithm is built upon the key-indexed counting technique that runs in linear time. LSD sort is best for string inputs of the same length (like license plates, phone numbers, IP addresses, etc).

Essentially, we start from the end of the string length, and treat each character's index as key. We can then break down the sorting process in four steps:

1. Count frequency of each char's appearance. This runs in *N* time.
2. Transform counts into index positions. This runs in *R* time. We do so by computing how many elements are less than a certain key, and the resulting number is the starting index to put strings associated with such key.
3. Distribute the input strings to an auxiliary array.
4. Copy the aux to the input.

The implementation is quite simple, too:

```java
class LSD {

    // sort an array of strings with a fixed W length
    public LSD(String[] a, int W) {
        int N = a.length;
        int R = 256;  // depends on your input
        String[] aux = new String[N];
        
        for (int d = W - 1; d >= 0; d--) { //start from the end
            int[] count = new int[R + 1]; // we don't use index 0
            
            // 1. count frequency
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;
            
            // 2. transform counts to indices
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // 3. distribute input to aux
            for (int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // 4. copy aux over to input
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }
}
```
The runtime is proportinoal to *WN + WR*, in most cases, *N* is larger than *R*, so we can say the runtime is about *WN*. Since *W* is a fixed and likely small number, then our algorithm is essentially runs in linear time.

## MSD String Sort
Most significant digit sort is helpful for sorting string inputs of varying lenghs. We need to implement this recursively. First, we need to sort the strings based on their first character. Then, we sort the "intervals". Intervals are determined by their first character. Sometimes, we have reached the end of a string but not for others. In this case, we need a private method to handle it by returning -1. Then, in our transformation step, we need to add 1 to all keys, shifting everything by 1 to the right, so that we have index position 1 reserved for ending indices to ensure shorter strings are placed before longer ones sharing the same shorter strings. 

The implementation also requires us to consider the case where many small subarrays exist. In this case, we can set up a cutoff number below which we just sort these subarrays using insertion sort.

```java
class MSD {
    private static final int M = 15; // cut off subarray size
    private final String[] aux;
    private static int R = 256;
    
    private int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d); else return -1;
    }
    
    public MSD(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private void sort(String[] a, int lo, int hi, int d) {
        if (lo + M > hi){
            Insertion.sort(a, lo, hi, d); return;
        }
        int[] count = new int[R + 2]; // reserve one more space for end indices
        
        // 1. count frequency
        for (int i = lo; i <= hi; i++)
            count[charAt(a[i], d) + 2]++;
        
        // 2. transform counts to indices
        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];
        
        // 3. distribute input strings to aux
        for (int i = lo; i <= hi; i++)
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        
        // 4. copy back
        for (int i = lo; i<= hi; i++)
            a[i] = aux[i - lo];
        
        // recursively sort substrings
        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r+1]-1, d+1);
    }
}
```

As we can observe, the best case is when input strings are mostly unique in their first few characters, so that we only pass through the list once, which gives us a sublinear performance proportional to *N*. The average case is about *Nlog_RN* in runtime, which is nearly linear. The worst case is when all strings are the same, which means we can't reduce the input array to any subarrays, we'd have to do an LSD for each character, therefore the linear runtime follows as *wN + WR* where *w* is the average string length.

The space requirement is proportional to *N + WR* because our recurisve call stack takes about *WR* in size due to our local instantiation of `count[]` with each recursive call.

The MSD might be wasteful if our input strings only contain a few characters. Imagine our R is 256 but all of our strings are made of "a", "b", and "c", then the `count[]` array would be a waste of space and our last for-loop will be inefficient as well.

## Three-way String Quicksort

We can partition strings into "less", "equal", and "greater" subarrays. This makes sure that we don't have any empty subarrays to waste space. The good thing is that this sorting algo is done in place with an implicit call stack for recursion. The rest is very similar to three-way quicksort.

```java
class Quick3String {
    private int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d); else return -1;
    }

    public void sort(String[] a) {
        sort(a, 0, a.length-1, 0);
    }

    private void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;

        while (i<=gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }

        // a[lo...lt-1] < v = a[lt...gt] < a[gt+1..hi]

        sort(a, lo, lt-1, d);
        if (v>=0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }
}
```

The runtime for 3-way string sort is at worst *wN* and at best *N*.