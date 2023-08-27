import heapq

def totalCost(costs, k, candidates):
    if 2*candidates >= len(costs): return sum(sorted(costs)[:k])
    q = costs[:candidates]
    qq = costs[-candidates:]
    heapify(q)
    heapify(qq)
    i = candidates
    ii = len(costs)-candidates-1
    ans = 0
    for _ in range(k):
        if not qq or q and q[0] <= qq[0]:
            ans += heappop(q)
            if i <= ii:
                heappush(q, costs[i])
                i += 1
        else:
            ans += heappop(qq)
            if i <= ii:
                heappush(qq, costs[ii])
                ii -= 1
    return ans

#                                [     ]
#     [     ]
#         a           b
arr = [57,33,26,76,14,67,24,90,72,37,30]
# heapq.heapify(arr)
print(arr)
print(totalCost(arr, 11, 2))