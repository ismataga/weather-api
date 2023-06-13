package com.folkdev.weather.controller;

import java.util.List;

public class Test {


//    price [1.2.3]
//    query[3,2,1,5]

    public static long getMinCost(int n, int k, List<List<Integer>> plans) {
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Long.MAX_VALUE;

                for (List<Integer> plan : plans) {
                    int start = plan.get(0);
                    int end = plan.get(1);
                    int cores = plan.get(2);
                    int price = plan.get(3);

                    if (i >= start && i <= end && cores >= j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + price);
                        dp[i][j] = Math.min(dp[i][j], dp[i - start][j - 1] + price);
                    }
                }
            }
        }

        long minCost = Long.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            minCost = Math.min(minCost, dp[i][k]);
        }

        return minCost;
    }
}
