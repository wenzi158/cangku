package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by zhaoyue on 16-8-24.
 */
public class AlgorithmPlus {
    public static int i=1;
    public static int j=2;
    ArrayList arrayList=new ArrayList();
    public static HashSet hashSet=new HashSet();

    public void addNum(){
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        result(i);
    }

    public void result(int i){
        if (j<=arrayList.size()){
            String part=i+","+j;
            hashSet.add(part);
            j++;
            result(i);
        }else{
            i=i+1;
            j=i+1;
            if (j<=arrayList.size()){
                result(i);
            }
        }

    }
    public void out(){
        Iterator iterator=hashSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        AlgorithmPlus algorithmPlus=new AlgorithmPlus();
        algorithmPlus.addNum();
        algorithmPlus.out();
    }
}
