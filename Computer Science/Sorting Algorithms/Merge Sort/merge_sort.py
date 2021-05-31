
def mergesort(arr, BU = 0):
    if BU:
        sortBU(arr)
    else:
        sort(arr, 0, len(arr) - 1)
    return arr

def sort(arr, lo, hi):
    if hi <= lo:
        return
    
    mid = lo + (hi - lo) // 2

    sort(arr, lo, mid)
    sort(arr, mid+1, hi)

    merge(arr, lo, mid, hi)

def sortBU(arr): 
    N = len(arr)
    sz = 1

    while sz < N:
        lo = 0
        while lo < N - sz:
            merge(arr, lo, lo + sz - 1, min(N-1, lo+sz+sz-1))
            lo += 2 * sz
        sz *= 2

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


   

arr = [12,3,4,12,2,4,1,23,3,3,3,3,2,2,1,2]
print(mergesort(arr))
arr = [12,3,4,12,2,4,1,23,3,3,3,3,2,2,1,2]
print(mergesort(arr, 1))