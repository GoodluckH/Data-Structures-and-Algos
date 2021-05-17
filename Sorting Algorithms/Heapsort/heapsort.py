def heapsort(arr):
    N = len(arr)
    
    k = N // 2     # start at the mid of the array to ensure subheaps have sizes larger than 1
    print(f'Input array: {arr}')
    while k >= 1:
        sink(arr, k, N)
        k -= 1
    print(f'Heapified array" {arr}')
    
    while N > 1:
        arr[1], arr[N -1] = arr[N-1], arr[1]
        N -= 1
        sink(arr,1, N)
    return arr

def sink(arr, k, N):
    while 2*k <= N - 1:
        j = 2 * k
        if j < N - 1 and arr[j] < arr[j + 1]: j += 1
        if arr[k] >= arr[j]: break
        arr[j], arr[k] = arr[k], arr[j]
        k = j
    return

arr = [None, 1,9,5,8,2,7]

print(f'Heapsorted array: {heapsort(arr)}')
