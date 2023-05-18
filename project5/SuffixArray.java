package project5;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
class Suffix{
  String suffix;
  int index;
  Suffix(String suffix, int index){
    this.suffix = suffix;
    this.index = index;
  }
}
public class SuffixArray {
  
  public ArrayList<Integer> construct(String S) {
    // Your code goes here
    // The function should return a vector of integers storing the integer IDs of the suffix array

    int len = S.length();
    Suffix[] suffixs = new Suffix[len];
    for(int i = 0; i < len; i++){
      suffixs[i] = new Suffix(S.substring(i, len), i);    
    }
    ArrayList<Integer> list = new ArrayList<>();
    Arrays.sort(suffixs, new Comparator<Suffix>(){
      public int compare(Suffix s1, Suffix s2){
        return s1.suffix.compareTo(s2.suffix);
      }
    });
    for(int i = 0; i < len; i++){
      list.add(suffixs[i].index);
    }    
    return list;
  }

}
