package test.controller;

import java.util.Random;

/**
 * @ClassName:test
 * @Description:
 * @Author lilei
 * @Date 2020/11/12
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        int a=100;
        double win = 1.2;
        double loser = 0.83;
        int k = 0;
        int l =0;

        double money = 10000;
        for(int i=0;i<=a;i++ ){
            for (int j = 0; j < 10000; j++) {
                if(Math.random()<0.5){
                    money =money*win;
                }else {
                    money = money*loser;
                }
            }
            if(money>10000){
                k=k+1;
                System.out.println("剩余钱+++"+money+"....."+k);
            }
            else {
                l=l+1;
                System.out.println(l);
            }

        }
    }

}
