class Solution:
    def closedIsland(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])

        def dfs(r, c):
            if r < 0 or r >= m or c < 0 or c >= n or grid[r][c] == 1:
                return
            grid[r][c] = 1
            dfs(r + 1, c)
            dfs(r - 1, c)
            dfs(r, c + 1)
            dfs(r, c - 1)
        for r in range(m):
            for c in range(n):
                if (r in (0, m - 1) or c in (0, n - 1)) and grid[r][c] == 0:
                    dfs(r, c)
        count = 0
        for r in range(m):
            for c in range(n):
                if grid[r][c] == 0:
                    dfs(r, c)
                    count += 1

        return count