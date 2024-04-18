package com.splider.tcc;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * @author splider
 * @create 2023-12-12 14:16
 */
public class CountTCCPt_PowNewton_Pingfang {
    private double y1;
    private double y2;
    private double y3;
    private double aim;

    /**
     * 幂函数y = a * b^x + c
     *
     * @param input 输入的Double[]数组，分别为
     * @return Double[]数组，分别为参数a,b,c的值
     */
    public Double[] countArgs_Power(Double[] input) {
        double x1 = input[0];
        double x2 = input[1];
        double x3 = input[2];
        double y1 = input[3];
        double y2 = input[4];
        double y3 = input[5];
        aim = (log(x3) - log(x1)) / (log(x2) - log(x1));
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        double c = getEistimate(0, 0.00000001, 1000);
        double b = (log(y2 - c) - log(y1 - c)) / (log(x2) - log(x1));
        double a = Math.exp(log(y1 - c) / (b * log(x1)));
        return new Double[]{a, b, c};
    }

    public Double getPlayerPt(Double score, Double[] args) {
        double a = args[0];
        double b = args[1];
        double c = args[2];
        return a * pow(score / 100000000.0, b) + c;
    }


    /**
     * @param x 初值
     * @param e 最大允许区间长度
     * @param N 最大迭代次数
     * @return 迭代结果
     */
    private double getEistimate(double x, double e, int N) {
        double x1;
        for (int k = 0; true; k++) {
            if (f(x) == 0) {
                throw new RuntimeException("出现奇异情况....");
            }
            x1 = x - f(x) / f2(x);
            if (Math.abs(x1 - x) < e) {
                System.out.println("迭代" + k + "次得到结果");
                return x1;
            }
            if (k == N) {
                throw new RuntimeException("迭代失败，已达最大迭代次数N....");
            }
            System.out.println("迭代第" + k + "次，此时：x0 = " + x + ", x1 = " + x1);
            x = x1;
        }
    }

    private double f(double x) {
        return (2 * (1 / (x - y1) - 1 / (x - y2)) * (log(y1 - x) - log(y3 - x))) / pow((aim + log(y1 - x) - log(y2 - x)), 3)
                - (1 / (x - y1) - 1 / (x - y3)) / pow((aim + log(y1 - x) - log(y2 - x)), 2);
    }

    private double f2(double x) {
        return (1 / (x - y1) * (x - y1) - 1 / (x - y3) * (x - y3)) / pow((aim + log(y1 - x) - log(y2 - x)), 2) -
                (6 * pow((1 / (x - y1) - 1 / (x - y2)),2) * (log(y1 - x) - log(y3 - x))) / pow((aim + log(y1 - x) - log(y2 - x)), 4) +
                (4 * (1 / (x - y1) - 1 / (x - y2)) * (1 / (x - y1) - 1 / (x - y3))) / pow((aim + log(y1 - x) - log(y2 - x)), 3) -
                (2 * (1 / (x - y1) * (x - y1) - 1 / (x - y2) * (x - y2)) * (log(y1 - x) - log(y3 - x))) / pow((aim + log(y1 - x) - log(y2 - x)), 3);
    }

    private double F(double x) {
        return (((log(y3 - x) - log(y1 - x)) / (log(y2 - x) - log(y1 - x))) - aim) * (((log(y3 - x) - log(y1 - x)) / (log(y2 - x) - log(y1 - x))) - aim);
    }
}
