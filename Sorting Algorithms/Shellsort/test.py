import random

def shellsort(arr):
    N = len(arr)
    h = 1
    
    # Initialize h
    while h < N//3:
        h = 3 * h + 1
    
    # Run the algo on h-sorted array
    while h >= 1:
        i = h
        n = []

        while i < N:
            j = i
            while j >= h and compare(arr[j], arr[j-h], n):   
                arr[j], arr[j-h] = arr[j-h], arr[j]
                j -= h
            i += 1
        
        print("When h is {}, the number of compare is {}, the ratio is {:.1f}".format(h, sum(n), (sum(n))/((N-h)*2)))
        h //= 3
  
    return arr

def compare(a, b, n):
    n.append(1)
    return b > a


i = 10000

while i < 10010:
    print(f"The array size is {i}")
    arr = random.sample(range(0, i), i)
    shellsort(arr)
    i *= 10
    print("-"*20, "\n")
