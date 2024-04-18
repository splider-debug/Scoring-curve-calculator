package com.splider.tcc;

/**
 * @author splider
 * @create 2023-12-05 22:13
 */
public class CountTCCPt_Exp {
    private double x1;
    private double x2;
    private double x3;
    private double aim;

    /**
     * 指数函数y = a * e^(b * x) + c
     *
     * @param input 输入的Double[]数组，分别为
     * @return Double[]数组，分别为参数a,b,c的值
     */
    public Double[] countArgs_Exponential(Double[] input) {
        double x1 = input[0];
        double x2 = input[1];
        double x3 = input[2];
        double y1 = input[3];
        double y2 = input[4];
        double y3 = input[5];
        aim = (y3 - y1) / (y2 - y1);
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        double b = getEistimate(0.01, 0.8, 0.00000000001, 100);
        double a = (y2 - y1) / (Math.exp(b * x2) - Math.exp(b * x1));
        double c = y1 - a * Math.exp(b * x1);
        return new Double[]{a, b, c};
    }

    public Double getPlayerPt(Double score, Double[] args) {
        double a = args[0];
        double b = args[1];
        double c = args[2];
        return a * Math.exp(b * (score / 100000000.0)) + c;
    }

    /**
     * @param x0 初值1
     * @param x1 初值2
     * @param e  最大允许区间长度
     * @param N  最大迭代次数
     * @return 迭代结果
     */
    private double getEistimate(double x0, double x1, double e, int N) {
        double x2;
        for (int k = 0; true; k++) {
            if (f(x0, x1) == 0) {
                throw new RuntimeException("出现奇异情况....");
            }
            x2 = x1 - F(x1) / f(x0, x1);
            if (Math.abs(x2 - x1) < e) {
                System.out.println("迭代" + k + "次得到结果");
                return x2;
            }
            if (k == N) {
                throw new RuntimeException("迭代失败，已达最大迭代次数N....");
            }
            x0 = x1;
            x1 = x2;
        }
    }

    private double f(double x0, double x1) {
        return (F(x1) - F(x0)) / (x1 - x0);
    }

    private double F(double x) {
        return (Math.exp(x * x3) - Math.exp(x * x1)) / (Math.exp(x * x2) - Math.exp(x * x1)) - aim;
    }
}
