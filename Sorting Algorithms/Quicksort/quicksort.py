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

arr = [6,5,4,3,2,1,2]
print(quicksort(arr))
    
