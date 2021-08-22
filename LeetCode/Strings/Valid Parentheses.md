# Valid Parentheses

https://leetcode.com/problems/valid-parentheses/

## Description
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.

## Approach
We can use a stack because the key of the problem is to check if parentheses are closed in correct orders. When it comes to order, we can see a string like "[{}]" will have the innermost parentheses closed earlier than the outermost one. So, this complies with the LIFO order of a stack. All we need to do is to push a close parenthesis for every open parenthesis we encounter. If we are encountering closed parenthesis, we check if the stack is empty or the lastest element is the correct closing parenthesis.

## Implementation
```java
public boolean isValid(String s) {
  Stack<Character> stack = new Stack<>();
  for (char c: s.toCharArray()) {
      if (c == '(') stack.push(')');
      else if (c == '{') stack.push('}');
      else if (c == '[') stack.push(']');
      else if (stack.isEmpty() || stack.pop() != c) return false;
  }
  return stack.isEmpty();
}
```

## Complexity Analysis
Immediately from the code, we are running in linear time. As for space, it should be proportional to 1/2 N. 
