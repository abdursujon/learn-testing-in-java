package com.learn_testing_in_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayManipulation {
    // 35. Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int maxProfit(int[] prices) {
        int maxPro = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxPro = Math.max(maxPro, prices[j] - prices[i]);
            }
        }
        return maxPro;
    }

    // 283. Move Zeroes to the end while maintaining order of the rest of the items
    public void moveZeroes(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if (num != 0) {
                list.add(num);
            }
        }

        int i = 0;
        while (i < list.size()) {
            nums[i] = list.get(i);
            i++;
        }

        for (int j = list.size(); j < nums.length; j++) {
            nums[j] = 0;
        }

        System.out.println(Arrays.toString(nums));
    }


    public static void main(String[] args) {
        ArrayManipulation ar = new ArrayManipulation();
        ar.moveZeroes(new int[]{1, 4, 0, 0, 56, 6});
    }
}
