import random
from tqdm import tqdm
from datetime import datetime
import matplotlib.pyplot as plt


def insertion_sort(arr):

    for i in tqdm(range(1, len(arr))):
        j = i
        while j > 0 and arr[j] < arr[j - 1]:
            arr[j], arr[j - 1] = arr[j - 1], arr[j]
            j -= 1
    return arr


def is_sorted(arr):
    for i in range(1, len(arr)):
        if arr[i] < arr[i - 1]:
            print([arr[i-1], arr[i]])
            return False
    return True

#################### ---Client Side Implementation--- ####################


res = input("Wanna run the runtime analysis? (Y/N) ")

if res == "Y" or res == "y":
    print("Sorting a sorted array containing consecutive numbers from 0 to 20000...", "\n")

    arr = []
    for i in range(20001):
        arr.append(i)

    start_time = datetime.now()
    insertion_sort(arr)
    end_time = datetime.now()

    deltas = []
    deltas.append((end_time - start_time).seconds)

    print("\n")
    print("A randomly generated array with 20000 items is created.", "\n")

    arr = random.sample(range(0, 20001), 20001)

    start_time = datetime.now()
    insertion_sort(arr)
    end_time = datetime.now()

    deltas.append((end_time - start_time).seconds)

    print("\n")
    print("Sorting on a reversely sorted array containing consecutive numbers from 20000 to 0...", "\n")

    arr = []
    for i in range(20001):
        arr.append(20000 - i)

    start_time = datetime.now()
    insertion_sort(arr)
    end_time = datetime.now()

    deltas.append((end_time - start_time).seconds)

    print("\n")
    print("Runtime on sorted array: ", deltas[0], "s")
    print("Runtime on randomly generated array: ", deltas[1], "s")
    print("Runtime on reversely sorted array: ", deltas[2], "s")



#################### ---Visualization--- ####################

def vis_runtime_sorted_arr():
    time_stamp = []
    input_size = []

    i = 10

    # Plot runtime on already sorted arrays
    while i <= 100000:
        input_size.append(i)
        i += 100

    for i in range(len(input_size)):
        arr = [x for x in range(input_size[i])]
        start_time = datetime.now()
        insertion_sort(arr)
        end_time = datetime.now()
        delta = end_time-start_time
        time_stamp.append(delta.microseconds)
    
    # Plot everything!
    plt.title("Insertion Sort Runtime on Sorted Arrays")
    plt.xlabel("Input Size")
    plt.ylabel("Time to run (ms)")
    plt.plot(input_size[:], time_stamp[:], color="red")
    plt.show()
    

def vis_runtime_random_arr():
    time_stamp = []
    input_size = []

    i = 10

    while i <= 5000:
        input_size.append(i)
        i += 100

    for i in range(len(input_size)):
        arr = random.sample(range(0, input_size[i]), input_size[i])
        start_time = datetime.now()
        insertion_sort(arr)
        end_time = datetime.now()
        delta = end_time-start_time
        time_stamp.append(delta.microseconds)

    # Plot everything!
    plt.title("Insertion Sort Runtime on Randomly Generated Arrays")
    plt.xlabel("Input Size")
    plt.ylabel("Time to run (ms)")
    plt.plot(input_size[:], time_stamp[:], color="Green")
    plt.show()


def vis_runtime_reverse_arr():
    time_stamp = []
    input_size = []

    i = 10

    while i <= 3600:
        input_size.append(i)
        i += 100

    for i in range(len(input_size)):
        arr = [input_size[i] - x for x in range(input_size[i])]
        start_time = datetime.now()
        insertion_sort(arr)
        end_time = datetime.now()
        delta = end_time-start_time
        time_stamp.append(delta.microseconds)

    # Plot everything!
    plt.title("Insertion Sort Runtime on Reversely Sorted Arrays")
    plt.xlabel("Input Size")
    plt.ylabel("Time to run (ms)")
    plt.plot(input_size[:], time_stamp[:], color="blue")
    plt.show()


# vis_runtime_sorted_arr()
# vis_runtime_random_arr()
# vis_runtime_reverse_arr()
