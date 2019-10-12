package pl.com.bartusiak.designpatterns.state;

import java.util.ArrayList;
import java.util.stream.Collectors;

class CombinationLock
{
    private int [] combination;
    private ArrayList<Integer> list;
    public String status;

    public CombinationLock(int[] combination)
    {
        this.combination = combination;
        list = new ArrayList<>();
        status="LOCKED";
    }

    public void enterDigit(int digit)
    {
        list.add(digit);
        status = list.stream().map(el->""+el).collect(Collectors.joining());
        if(list.size()==combination.length){
            for (int i=0; i<combination.length; i++){
                status="OPEN";
                if(combination[i]!=list.get(i)){
                    status="ERROR";
                    break;
                }
            }
        }
    }
}

public class Exercise {
    public static void main(String[] args) {
        CombinationLock lock = new CombinationLock(new int[]{1,2,3,4});
        System.out.println(lock.status);
        lock.enterDigit(1);
        System.out.println(lock.status);
        lock.enterDigit(2);
        System.out.println(lock.status);
        lock.enterDigit(3);
        System.out.println(lock.status);
        lock.enterDigit(4);
        System.out.println(lock.status);
    }
}
