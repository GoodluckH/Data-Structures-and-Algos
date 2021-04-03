# Stable Matching

Created: Mar 30, 2021 6:46 PM
Notes: https://www.eecs70.org/static/notes/n4.pdf
Tags: CS, Discrete Math

# Propose-and-reject Algorithm

### **How does it work?**

1. Proposers extend their proposals to their most preferred candidates who haven't rejected them.
2. Candidates put the most favored offer **on string (**meaning, it's a 'maybe' to them, but not a definitive "yes" yet). And reject all other offers.
3. Proposers cross out candidates who rejected them. 

... and the cycle repeats. 

### **This algorithm guarantees two things**

1. the algorithm terminates with a matching

    [PROOF](https://www.notion.so/PROOF-902807cbb9f541ba8f7823f9f534bab8)

2. the matches are stable

    [PROOF](https://www.notion.so/PROOF-b8d449b99d0640aa94ff895395ef4996)

### **Stable Match**

A match is considered as stable if there's no *rogue couples,* or two individuals in different matches prefer to each other more than their current matches. 

In the job matching example, it will result in the company firing an innocent employee, and an employee quits her job so that she can join the most preferred company that also prefers her as the top candidate. 

### **Improvement Lemma**

The lemmas says that if job J offers a candidate C on the *kth* day, then on subsequent days, C will have job offers **on hand** that she likes at least as much as J. 

*this is true because when a better offer appears, C can just reject the current one.*

# Terms

### **Employer/job optimal:**

This is basically saying the algorithm is designed to help employers to get their optimal candidates. 

Employers are the ones that make the proposal

### **Candidate optimal**

Very similarly, candidates make proposals. And they are ensured to get the optimal jobs.

### **Optimal Candidate**

The most preferred candidate under in a stable matching set. That means there's no rogue couple.

### **Optimal Jobs**

Similarly, the most preferred job you can get under the principle of stable matching.