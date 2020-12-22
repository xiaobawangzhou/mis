package cn.offcn.utils;

import java.util.Scanner;

public class NumTest {

    public static  int inputNum(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入(1-7之间的整数):");
        int num=sc.nextInt();
        if(num<=0 || num>7){
            num=inputNum();
        }
        return num;
    }

    public static void main(String[] args) {
        inputNum();
    }

}
