import random

def quicksort(a):
    random.shuffle(a)
    sort(a, 0, len(a) - 1)
    return a

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


arr = [6,5,4,3,2,1,2]
print(quicksort(arr))
    
