""" Quick Find Method. For this technique, it takes constant time to find something's parent. But linear time to union things"""


class UF():

    def __init__(self, n) -> None:
        # Inialize our array. This takes O(n) times.
        self.arr = [x for x in range(n)]
        self.components = n

    def union(self, p, q) -> None:
        if not self.connected(p, q):
            for i, x in enumerate(self.arr):
                if x == self.arr[p]:
                    self.arr[i] = q
        self.components -= 1

    def connected(self, p, q):
        return self.find(p) == self.find(q)

    def find(self, p):
        return self.arr[p]

    def count(self):
        return self.components


test = UF(10)

test.union(1, 2)
test.union(2, 3)
print(test.arr)
print(test.count())
