package com.autoSerwis;
import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        for(int i = 0; i < 20; ++i)
        {
            list1.add(i);
        }

        for(int i = 5; i < 15; ++i)
        {
            list2.add(i);
        }

        for(Integer i: list1)
        {
            boolean con = false;
            for(Integer j: list2)
            {
                if(i.equals(j))
                {
                    con = true;
                    break;
                }
            }
            if(con == false)
                list3.add(i);
        }

        for(Integer i: list3)
        {
            System.out.println(i);
        }
    }
}
