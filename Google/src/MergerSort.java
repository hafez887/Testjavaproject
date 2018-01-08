import java.util.*;

public class MergerSort
{
	public static void main(String[] args)
	{
		 int x=0; 
		 char y= (char)x;
		Integer[] a = {2, 6, 3, 5, 1};
		Integer[] res= 	mergeSort(a);
		System.out.println(y);
	}
	
	public static Integer[] mergeSort(Integer [] a)
	{
		 return mergeSort(a,   0,  a.length - 1);
	}


	private static Integer[] mergeSort(Integer [ ] a, int left, int right)
	{
		if( left < right )
		{
			int center = (left + right) / 2;
			mergeSort(a,  left, center);
			mergeSort(a,  center + 1, right);
			return mergearr(a, left, center+1 , right);
		}
		return null;
	}


    private static void merge(Integer[ ] a, int left, int right, int rightEnd )
    { 
    	Integer[ ] tmp=new Integer[a.length];
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
    private static Integer[] mergearr(Integer[] aa, int left, int right, int rightend)
    {
    	Integer[] temp =new Integer[aa.length];
    	int leftend= right-1, size= rightend-left+1, index=left;
    	while(left<= leftend&& right<= rightend)
    		if(aa[left]<= aa[right])
    			temp[index++]= aa[left++];
    		else 
    			temp[index++]= aa[right++];
    	while(left<=leftend)
    		temp[index++]= aa[left++];
    	while(right<=rightend)
    		temp[index++]= aa[right++];
    	for (int i =0; i< size; i++, rightend--)
    		aa[rightend]= temp[rightend];
    	return aa;
    }
 }