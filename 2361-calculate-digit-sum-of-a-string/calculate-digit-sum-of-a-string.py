class Solution(object):
    def digitSum(self, s, k):
        while len(s) > k:
            next_s = []
            for i in range(0, len(s), k):
                group_sum = sum(int(ch) for ch in s[i:i+k])
                next_s.append(str(group_sum))
            s = "".join(next_s)
        return s