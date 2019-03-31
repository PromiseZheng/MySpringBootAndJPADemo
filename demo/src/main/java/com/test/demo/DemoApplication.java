package com.test.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ArrayList<Integer> p=new ArrayList<>();
        String []strArr;
        String temp;
        Scanner scanner=new Scanner(System.in);
        temp=scanner.nextLine();
        strArr=temp.split(",");
        for(int i=0;i<strArr.length;i++){
            p.add(new Integer(strArr[i]));
        }
        int m1=0,m2=0,m3=0,m4=0;
        int templi=0,resultli=0;
        for(int i=0;i<p.size();i++){
            templi=0;
            for(int j=i;j<p.size();j++){
                if(p.get(j)-p.get(i)>0){
                    templi+=p.get(j)-p.get(i);
                    if(templi>resultli){
                        resultli=templi;
                        i=j+1;
                        if(m1!=0&&m2!=0){
                            m3=i;m4=j;
                        } else{
                            m1=i;m2=j;
                        }
                    }

                }
            }
        }
        System.out.println(m1+" "+m2+" "+m3+" "+m4);
       // SpringApplication.run(DemoApplication.class, args);
    }

}
