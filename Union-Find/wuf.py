""" Weighted quick-union requires us to union the trees that are smaller in size, not in depth, to the ones that are larger in size """


class wuf():

    def __init__(self, n) -> None:
        self.id = [x for x in range(n)]
        self.size = [1] * n
        self.components = n

    def union(self, p, q):
        if not self.connected(p, q):
            p_id = self.find(p)
            q_id = self.find(q)

            if(self.size[p_id] > self.size[q_id]):
                self.size[p_id] += self.size[q_id]
                self.id[q_id] = p_id
            elif(self.size[p_id] <= self.size[q_id]):
                self.size[q_id] += self.size[p_id]
                self.id[p_id] = q_id
            self.components -= 1
    
    def connected(self, p, q):
        return self.find(p) == self.find(q)

    def find(self, p):
        p_id = self.id[p]

        if p_id != p:
            while p_id != self.id[p]:
                p_id = self.id[p_id]
        return p_id

    def count(self):
        return self.components

    
test = wuf(10)

test.union(1, 2)
test.union(2, 3)

test.union(5, 6)
test.union(7, 6)
test.union(8, 6)

test.union(1, 6)
print(test.id)
print(test.count())
print(test.find(1))