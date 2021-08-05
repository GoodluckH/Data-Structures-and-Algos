# Add Two Numbers

https://leetcode.com/problems/add-two-numbers/

## Description
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

## Approach

For this one, it appears that an elegant solution would require extra space. So we can just do that. Interestingly, the approach is similar to the [adding binary](https://github.com/GoodluckH/learn/blob/main/LeetCode/Arrays/Add%20Binary.md) problem in which we set up a while loop with conditionals to make sure that the loop continues after one of the inputs has reached null. 

Another finding is that sometimes we want to have a sentinel node if we decide to use the inputs as pointers. I think these LL problems are basically testing our knowledge on how variables are assigned and referenced. Also it tests your ability to set up stopping mechanisms to avoid the null exception errors.

The key to addition is to know how to use `/` and `%` operators to get the "carry" needed for the arithmic.

## Implementation
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode sentinel = new ListNode(), cur = sentinel;
    int sum = 0; 

    while (l1 != null || l2 != null) {
        sum /= 10;
        if (l1 != null) {sum += l1.val; l1 = l1.next;}
        if (l2 != null) {sum += l2.val; l2 = l2.next;}
        cur.next = new ListNode(sum % 10);
        cur = cur.next;
    }
    if (sum / 10 == 1) cur.next = new ListNode(1);
    return sentinel.next;
}
```

## Complexity Analysis

Immediately from the code, this is a linear solution with linear space requirements. One can solve this problem in-place but the code will not be as elegant and might not be a good practice to modify the input LLs. 