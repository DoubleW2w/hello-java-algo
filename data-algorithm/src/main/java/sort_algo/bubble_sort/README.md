## 介绍

它会遍历若干次要排序的数列，每次遍历时，它都会从前往后依次的比较相邻两个数的大小；
如果前者比后者大，则交换它们的位置。这样一次遍历之后，最大的元素就排在数列的末尾！
采用相同的方式进行比遍历以后，第二大的元素就回排在数列的末尾之前，重复操作，直到数列有序为止。

## 时间复杂度

冒泡排序的时间复杂度是O(N2)。
假设被排序的数列中有N个数。遍历一趟的时间复杂度是$O(N)$，需要遍历多少次呢? N-1次！因此，冒泡排序的时间复杂度是$O(N2)$

## 算法稳定性

算法稳定性 -- 假设在数列中存在a[i]=a[j]，若在排序之前，a[i]在a[j]前面；并且排序之后，a[i]仍然在a[j]前面。则这个排序算法是稳定的！

## 算法动图可视化

[https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html](https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html)