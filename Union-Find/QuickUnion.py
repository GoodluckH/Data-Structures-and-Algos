""" Quick Union means it takes constant time to union things but linear or worse time to find things """

class quick_union():
    def __init__(self, n) -> None:
        self.id = [x for x in range(n)]
        self.components = n

    def union(self, p, q):
        q_id = self.find(q)
        if not self.connected(p, q):
            self.id[p] = q_id
        self.components -= 1
   
    def connected(self, p, q):
        return self.find(p) == self.find(q)

    def find(self, p):
        p_id = self.id[p]
        if p_id == p:
            return p_id
        else:
            while(p_id != self.id[p_id]):
                p_id = self.id[p_id]
            return p_id
    
    def count(self):
        return self.components
        
test = quick_union(10)

test.union(1, 2)
test.union(2, 3)
test.union(4, 3)
print(test.id)
print(test.count())
print(test.find(1))
      