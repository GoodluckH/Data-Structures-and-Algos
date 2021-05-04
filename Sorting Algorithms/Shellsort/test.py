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
        
        while i < N:
            j = i
            n = 0
            while j >= h and arr[j] < arr[j-h]:
                arr[j], arr[j-h] = arr[j-h], arr[j]
                j -= h
                n+=1

            i += 1
        print("When h is {}, the number of compare is {}, the ratio is {:.5f}".format(h, n, n/N))
        h //= 3
  
    return arr




i = 100

while i < 1000000:
    print(f"The array size is {i}")
    arr = random.sample(range(0, i), i)
    shellsort(arr)
    i *= 10
    print("-"*20, "\n")
