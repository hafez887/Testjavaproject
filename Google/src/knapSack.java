public class knapSack {
   
    /*A simple solution is to consider all subsets of items and calculate the total weight and value of all subsets. Consider the only subsets whose total weight is smaller than W. From all such subsets, pick the maximum value subset.

    1) Optimal Substructure:
    To consider all subsets of items, there can be two cases for every item: (1) the item is included in the optimal subset, (2) not included in the optimal set.
    Therefore, the maximum value that can be obtained from n items is max of following two values.
    1) Maximum value obtained by n-1 items and W weight (excluding nth item).
    2) Value of nth item plus maximum value obtained by n-1 items and W minus weight of the nth item (including nth item).
*/

    int knapSack(int W, int wt[], int val[], int n)
    {
       // Base Case
       if (n == 0 || W == 0)
           return 0;
     
       // If weight of the nth item is more than Knapsack capacity W, then
       // this item cannot be included in the optimal solution
       if (wt[n-1] > W)
           return knapSack(W, wt, val, n-1);
     
       // Return the maximum of two cases: 
       // (1) nth item included 
       // (2) not included
       else return Math.max( val[n-1] + knapSack(W-wt[n-1], wt, val, n-1),
                        knapSack(W, wt, val, n-1)
                      );
    }

}
