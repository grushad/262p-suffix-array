package project5;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
class Suffix{  
  int index, rank, next;
  Suffix(int index, int rank, int next){
    this.index = index;
    this.rank = rank;
    this.next = next;
  }
}
public class SuffixArray {
  
  public ArrayList<Integer> construct(String S) {
    // Your code goes here
    // The function should return a vector of integers storing the integer IDs of the suffix array

    int len = S.length();
    Suffix[] suffixs = new Suffix[len];
    for(int i = 0; i < len; i++){
      suffixs[i] = new Suffix(i, S.charAt(i) - '$', 0); //since only lowercase alphabet      
    }
    for(int i = 0; i < len; i++){
      suffixs[i].next = (i + 1 < len ? suffixs[i + 1].rank : -1);
    }

    ArrayList<Integer> list = new ArrayList<>();
    //convert the below to radix sort algorithm to improve complexity
    Arrays.sort(suffixs, new Comparator<Suffix>(){
      public int compare(Suffix s1, Suffix s2){
        if(s1.rank == s2.rank)
          return s1.next - s2.next;
        return s1.rank - s2.rank;
        // if (s1.rank != s2.rank) 
        //   return Integer.compare(s1.rank, s2.rank);
        // return Integer.compare(s1.next, s2.next);

      }
    });

    int[] ind = new int[len];

    for(int i = 4; i < 2 * len; i <<= 1){
      int rank = 0, prev = suffixs[0].rank;
      suffixs[0].rank = rank;
      ind[suffixs[0].index] = 0;
      for(int j = 1; j < len; j++){
        prev = suffixs[j].rank;
        if(suffixs[j].rank == prev && suffixs[j].next == suffixs[j - 1].next){          
          suffixs[j].rank = rank;
        }else{
          suffixs[j].rank = ++rank;
        }
        ind[suffixs[j].index] = j;
      }
      for(int j = 0; j < len; j++){
        int nextR = suffixs[j].index + i / 2;
        suffixs[j].next = nextR < len ? suffixs[ind[nextR]].rank : -1;
      }
       //convert the below to radix sort algorithm to improve complexity
      Arrays.sort(suffixs, new Comparator<Suffix>(){
        public int compare(Suffix s1, Suffix s2){
          if(s1.rank == s2.rank)
            return s1.next - s2.next;
          return s1.rank - s2.rank;
          // if (s1.rank != s2.rank) 
          //   return Integer.compare(s1.rank, s2.rank);
          // return Integer.compare(s1.next, s2.next);
        }
      });
    }
    for(int i = 0; i < len; i++){
      list.add(suffixs[i].index);
    }    
    return list;
  }

  void radixSort(Suffix[] arr){

  }

}
