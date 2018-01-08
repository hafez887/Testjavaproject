public class Quicksort {
    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }
    public static int[] doInsertionSort(int[] input){
    	   
        int temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }


    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[(low + high) / 2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot elment then we exchange the
            // values.
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }
    public static void SelectionSort ( int [ ] num )
    {
         int i, j, first, temp;  
         for ( i = num.length - 1; i > 0; i --)  
         {
              first = 0;   //initialize to subscript of first element
              for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
              {
                   if( num[ j ] < num[ first ] )         
                     first = j;
              }
              temp = num[ first ];   //swap smallest found with element in position i.
              num[ first ] = num[ i ];
              num[ i ] = temp; 
          }           
    }


    public static void bubbleSort(int[] numArray) {
        /*
         * In bubble sort, we basically traverse the array from first
         * to array_length - 1 position and compare the element with the next one.
         * Element is swapped with the next element if the next element is greater.
         */

        int n = numArray.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (numArray[j - 1] > numArray[j]) {
                    temp = numArray[j - 1];
                    numArray[j - 1] = numArray[j];
                    numArray[j] = temp;
                }

            }
        }
    }
    //In comparison to Quicksort the divide part in Mergesort is simple, while the merging part is complex.

  //  Quicksort can sort "inline" in an existing collection, e.g. it does not have to create a copy of the collection while Mergesort requires a copy.


    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}