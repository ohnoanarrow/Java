import java.util.Arrays;
import java.util.Comparator;
class mergesort{
    public static  <T> T[] mergeSort(T[] array,Comparator<? super T> comp){
            if (array.length<=1){
                return array;
            }
            else{
                int mid= array.length/2;
                T[] A = mergeSort(Arrays.copyOfRange(array,0,mid),comp);
                T[] B = mergeSort(Arrays.copyOfRange(array,mid,array.length),comp);
                return merge(A,B,comp);
            }

    }
    public static  <T> T[] merge(T[] array,T[] array2,Comparator<? super T> comp){
            int num=(array.length)+(array2.length);
            Object[] C =  new Object[num];
            int i=0;
            int j=0;
            for(int k=0; k<C.length;k++){
                if(i==array.length){
                    C[k]=array2[j];
                    j++;
                }
                else if(j==array2.length){
                    C[k]=array[i];
                    i++;
                }
                else if(comp.compare(array[i],array2[j])>=0){
                    C[k]=array[i];
                    i++;
                }
                else if(comp.compare(array[i],array2[j])<0){
                    C[k]=array2[j];
                    j++;
                }
            }

            return (T[])C;
    }

}
