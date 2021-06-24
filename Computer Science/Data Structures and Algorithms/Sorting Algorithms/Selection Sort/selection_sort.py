import random
from tqdm import tqdm
from datetime import datetime
import matplotlib.pyplot as plt


def selection_sort(arr):
    
    for i in tqdm(range(len(arr))):
        m = i
        j = i + 1

        while j < len(arr):
            if arr[j] < arr[m]:
                m = j
            j += 1

        arr[i], arr[m] = arr[m], arr[i]

    return arr


def is_sorted(arr):
    for i in range(1, len(arr)):
        if arr[i] < arr[i - 1]:
            print([arr[i-1], arr[i]])
            return False
    return True


#################### ---Client Side Implementation--- ####################
l = input("Provide lower bound (an integer): ")
u = input("Provide upper bound: ")
s = input("Provide the size of the array (smaller than upper bound): ", )

print("\n")
print("Sorting progress:")

test = random.sample(range(int(l), int(u)), int(s))
sorted_arr = selection_sort(test[:])

print("\n")
if int(s) <= 10:
    print("Input Array: ", test)
    print("Sorted Array: ", sorted_arr, "\n")
else:
    abbrev = test[:5] + ["..."] + test[int(s) - 5:]
    print("Input Array: ", abbrev)
    abbrev = sorted_arr[:5] + ["..."] + sorted_arr[int(s) - 5:]
    print("Sorted Array: ", abbrev, "\n")

print("Machine-check if arr is sorted:", is_sorted(sorted_arr), "\n")

res = input("Do you want to visualize runtime? (Y/N) ")
i_size = input("What is the largest input size you'd like (defaul = 5000)? ")


def vis_runtime(s):
    time_stamp = []
    input_size = []

    i = 10

    #
    while i < s:
        input_size.append(i)
        i += 100

    for i in range(len(input_size)):
        start_time = datetime.now()
        selection_sort(random.sample(range(1, s), input_size[i]))
        end_time = datetime.now()
        delta = end_time-start_time
        time_stamp.append(delta.microseconds)

    # Plot everything!
    plt.title("Selection Sort Runtime Visualization")
    plt.xlabel("Input Size")
    plt.ylabel("Time to run (ms)")
    plt.plot(input_size[:], time_stamp[:], color="red")
    plt.show()


if res == "Y" or res == "y":
    vis_runtime(int(i_size))
