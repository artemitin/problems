from queue import PriorityQueue
import heapq

def totalCost(costs, k, candidates):
    cost = 0
    #init queues
    if candidates * 2 < len(costs):
        class PQueue(PriorityQueue):
            def peek(self):
                return self.queue[0]
        left = PQueue()
        right = PQueue()
        add = 0
        for i in range(candidates):
            left.put(costs.pop(0))
        for i in range(len(costs) - candidates, len(costs)):
            right.put(costs.pop())
        for i in range(k):
            if left.empty():
                add = right.get()
            elif right.empty():
                add = left.get()
            elif left.peek() <= right.peek():
                add = left.get()
                if len(costs) > 0:
                    left.put(costs.pop(0))
            elif right.peek() < left.peek():
                add = right.get()
                if len(costs) > 0:
                    right.put(costs.pop())
            cost += add
    else:
        # alternative algorithm, when candidates intervals overlap
        costs.sort()
        for i in range(k):
            cost += costs.pop(0)

    return cost
#                                [     ]
#     [     ]
#         a           b
arr = [57,33,26,76,14,67,24,90,72,37,30]
x = 3
# heapq.heapify(arr)
print("arr = ", arr)
print("arr[:3] = ", arr[:x])
print("arr[3:] = ", arr[x:])
print("arr[-3:] = ", arr[-x:])
print("arr[:-3] = ", arr[:-x])
# print(totalCost(arr, 11, 2))