package com.splider.tcc;

/**
 * @author splider
 * @create 2023-12-12 14:16
 */
public class CountTCCPt_PowLn {
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
        aim = (Math.log(x3) - Math.log(x1)) / (Math.log(x2) - Math.log(x1));
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        double c = getEistimate(-5, 2.999, 0.0000000001, 100);
        double b = (Math.log(y2 - c) - Math.log(y1 - c)) / (Math.log(x2) - Math.log(x1));
        double a = (y1 - c) / Math.pow(x1, b);
        return new Double[]{a, b, c};
    }

    public Double getPlayerPt(Double score, Double[] args) {
        double a = args[0];
        double b = args[1];
        double c = args[2];
        return a * Math.pow(score / 100000000.0, b) + c;
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
            System.out.println("迭代第" + k + "次，此时：x0 = " + x0 + ", x1 = " + x1);
            x0 = x1;
            x1 = x2;
        }
    }

    private double f(double x0, double x1) {
        return (F(x1) - F(x0)) / (x1 - x0);
    }

    private double F(double x) {
        return (Math.log(y3 - x) - Math.log(y1 - x)) / (Math.log(y2 - x) - Math.log(y1 - x)) - aim;
    }
}
