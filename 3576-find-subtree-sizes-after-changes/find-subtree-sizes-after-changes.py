class Solution(object):
    def findSubtreeSizes(self, parent, s):
        n = len(parent)
        adj = [[] for _ in range(n)]
        for i in range(1, n):
            adj[parent[i]].append(i)

        new_parent = list(parent)
        last_seen = [-1] * 26

        def dfs_reparent(u):
            c = ord(s[u]) - ord('a')
            prev = last_seen[c]
            if prev != -1:
                new_parent[u] = prev
            last_seen[c] = u
            for v in adj[u]:
                dfs_reparent(v)
            last_seen[c] = prev

        dfs_reparent(0)

        new_adj = [[] for _ in range(n)]
        for i in range(1, n):
            new_adj[new_parent[i]].append(i)

        ans = [0] * n

        def dfs_size(u):
            total = 1
            for v in new_adj[u]:
                total += dfs_size(v)
            ans[u] = total
            return total

        dfs_size(0)
        return ans