import string
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
            
            # To visualize the process on StdOut
            p = ["_"] * N
            p[i] = str(arr[i])
            p[j - h] = str(arr[j-h])
            print(" ".join(p))

            while j >= h and arr[j] < arr[j-h]:
                arr[j], arr[j-h] = arr[j-h], arr[j]
                j -= h
           
            i += 1
        h //= 3
    return arr

arr = [random.choice(string.ascii_uppercase) for _ in range(16)]
print(" ".join(arr))
shellsort(arr)
print(" ".join(arr))