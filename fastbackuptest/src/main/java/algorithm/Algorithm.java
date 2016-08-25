package algorithm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zhaoyue on 16-8-21.
 */
public class Algorithm {
    public static int num=0;
    public static int i=1;
    ArrayList arrayList=new ArrayList();
    public void addNum(){
        num++;
        arrayList.add(i);
        i++;
    }
    public void dateAssign (int nums){
        int m=0;
        if (nums==1){
            System.out.println(arrayList.get(m)+","+arrayList.get(m+1));
        }else if (nums==2){
            System.out.println(arrayList.get(m+1)+","+arrayList.get(m+2));
        }else if (nums==3){
            System.out.println(arrayList.get(m+2)+","+arrayList.get(m));
        }
    }
    public static void main(String[] args) {
        Algorithm algorithm=new Algorithm();
        algorithm.addNum();
        algorithm.addNum();
        algorithm.addNum();
        System.out.println(num);
        while(true){
            Scanner scanner=new Scanner(System.in);
            algorithm.dateAssign(scanner.nextInt());
        }
    }
}
