/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heapsort;

/**
 *
 * @author nithishkp
 */
public class HeapSort {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int arr[] = {-1,9,6,8,2,5,7,10};
		 HeapSort ob = new HeapSort();
		 ob.contructMaxHeap(arr,arr.length);
                 System.out.print("Heap:");
                 for(int i = 1 ;i<arr.length;i++)
                 {
                     System.out.print(arr[i]+" ");
                 }
                 System.out.print(" \n");
                 ob.heapSort(arr);
    }
    public void heapSort(int arr[])
    {
        int i = 1;
        int sortedArray[] = new int[arr.length];
        while(i < arr.length )
        {
            sortedArray[arr.length-i] = arr[1];
        
            int temp = arr[1];
            System.out.print(temp+" ");            
            arr[1] = arr[arr.length-i];
            arr[arr.length-i] = temp;
            
            this.contructMaxHeap(arr,arr.length-i);
            
            i++;
        }
        System.out.print("\nSorted Heap:");
                 for(int ix = 1 ;ix<sortedArray.length;ix++)
                 {
                     System.out.print(sortedArray[ix]+" ");
                 }
        
    }
	public void contructMaxHeap(int arr[],int size)
	{
		
		
		for(int i = size/2;i >= 1; i--)
		{
			int k = i;
			int v = arr[k];
			boolean heap = false;
			
			while(!heap && 2*k <= size)
			{
				int j = 2*k;
				if(j<size && j+1 < size)
				{                                                                             
				   if(arr[j]<arr[j+1])
					j++;
                                   
				    if(v>=arr[j])
				    	heap = true;
				    else
				    	{
				    	   arr[k] = arr[j];
				    	   k = j;
				    	}
				}
                                else
                                     break;
                        }	
			arr[k] = v;
			
		}
	}    
}
