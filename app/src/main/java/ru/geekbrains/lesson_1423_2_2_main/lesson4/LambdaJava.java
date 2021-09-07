package ru.geekbrains.lesson_1423_2_2_main.lesson4;

import android.util.Log;

public class LambdaJava {
    public static void main(){
        Operation operation = new Operation() {
            @Override
            public int calculate(int x, int y) {
                return x+y;
            }
        };
        int z = operation.calculate(2,3);
        Log.d("mylogs","z= "+z);

        Operation operation2  = (x,y)->x+y;
        int z2 = operation2.calculate(2,3);
        Log.d("mylogs","z= "+z2);
    }
}
interface Operation{
    int calculate(int x,int y);
}
