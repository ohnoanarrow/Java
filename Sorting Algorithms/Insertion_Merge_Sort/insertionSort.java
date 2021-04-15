import java.util.Comparator;
class insertionSort{
    public static <T> T[] insertionSort(T[] unsortedArray,Comparator<? super T> comp){
        int i =0;
        T temp=null;
        while(i<unsortedArray.length){
            int j = i;
            while(j>0 && comp.compare(unsortedArray[j-1] , unsortedArray[j])>0){
                temp = unsortedArray[j];
                unsortedArray[j]=unsortedArray[j-1];
                unsortedArray[j-1]=temp;
                j--;
            }
            i++;
        }
        return unsortedArray;
    }
}
