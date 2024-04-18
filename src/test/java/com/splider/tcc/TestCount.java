package com.splider.tcc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author splider
 * @create 2023-12-05 21:08
 */
@Slf4j
public class TestCount {
    private final CountTCCPt_Pow countPt1 = new CountTCCPt_Pow();
    private final CountTCCPt_Exp countPt2 = new CountTCCPt_Exp();
    private final CountTCCPt_PowLn countPt3 = new CountTCCPt_PowLn();
    private final CountTCCPt_PowNewton countPt4 = new CountTCCPt_PowNewton();
    private final CountTCCPt_PowNewton_Pingfang countPt5 = new CountTCCPt_PowNewton_Pingfang();

    @Test
    public void test1_1() {
        Double[] inputs = {2000000000.0, 2169000000.0, 2214000000.0, 3.0, 10.0, 30.0};
//        Double[] inputs1 = {18.0, 21.0, 22.1, 3.0, 10.0, 30.0};
        Double[] output = countPt1.countArgs_Power(inputs);
        log.info("args(a,b,c) = " + Arrays.toString(output));
        log.info("k = " + calculateK(2000000000.0, 2169000000.0, 2214000000.0, 3.0, 10.0, 30.0));
        log.info("kAvg = " + calculateKAvg(2000000000.0, 2169000000.0, 2214000000.0, 3.0, 10.0, 30.0));
//        Double playerPt = countPt1.getPlayerPt(1700000000.0, output);
//        log.info("playerPt = " + playerPt);
    }
    @Test
    public void test1_2() {
//        Double[] inputs = {1800000000.0, 2100000000.0, 2210000000.0, 3.0, 10.0, 30.0};
        Double[] inputs = {20.0, 21.69, 22.14, 3.0, 10.0, 30.0};
        Double[] output = countPt1.countArgs_Power(inputs);
        log.info("args(a,b,c) = " + Arrays.toString(output));
        log.info("k = " + calculateK(20.0, 21.69, 22.14, 3.0, 10.0, 30.0));
        log.info("kAvg = " + calculateKAvg(20.0, 21.69, 22.14, 3.0, 10.0, 30.0));
//        Double playerPt = countPt1.getPlayerPt(1700000000.0, output);
//        log.info("playerPt = " + playerPt);
    }

    private double calculateK(double x1,double x2,double x3,double y1,double y2,double y3){
        return ((x1*y1 + x2*y2 + x3*y3) - 3 * (avg(x1, x2, x3) * avg(y1, y2, y3))) / ((x1*x1 + x2*x2 + x3*x3)  - 3 * avg(x1, x2, x3) * avg(x1, x2, x3));
    }

    private double calculateKAvg(double x1,double x2,double x3,double y1,double y2,double y3){
        return avg(((y3 - y2)/(x3 - x2)),((y2 - y1)/(x2 - x1)));
    }

    private double avg(double... x){
        double sum = 0.0;
        for (double v : x) {
            sum += v;
        }
        return sum / x.length;
    }

    @Test
    public void test2() {
        Double[] output = countPt2.countArgs_Exponential(new Double[]{15.0, 18.0, 20.86, 3.0, 10.0, 30.0});
        log.info("args(a,b,c) = " + Arrays.toString(output));
        Double playerPt = countPt2.getPlayerPt(1700000000.0, output);
        log.info("playerPt = " + playerPt);
    }

    @Test
    public void test4(){
        Double[] output = countPt3.countArgs_Power(new Double[]{20.0, 21.69, 22.10, 3.0, 10.0, 30.0});
        log.info("args(a,b,c) = " + Arrays.toString(output));
        Double playerPt = countPt3.getPlayerPt(1700000000.0, output);
        log.info("playerPt = " + playerPt);
    }

    @Test
    public void test5(){
        Double[] output = countPt4.countArgs_Power(new Double[]{20.0, 21.69, 22.10, 3.0, 10.0, 30.0});
        log.info("args(a,b,c) = " + Arrays.toString(output));
        Double playerPt = countPt4.getPlayerPt(1700000000.0, output);
        log.info("playerPt = " + playerPt);
    }

    @Test
    public void test6(){
        Double[] output = countPt5.countArgs_Power(new Double[]{20.0, 21.69, 22.10, 3.0, 10.0, 30.0});
        log.info("args(a,b,c) = " + Arrays.toString(output));
        Double playerPt = countPt5.getPlayerPt(1700000000.0, output);
        log.info("playerPt = " + playerPt);
    }


    @Test
    public void test3() {
//        System.out.print("请输入初值1：");
        double x0 = 0.4;
//        System.out.print("请输入初值2：");
        double x1 = 1.5;
//        System.out.print("请输入最大允许区间长度：");
        double e = 0.0000001;
//        System.out.print("请输入最大迭代次数：");
        int N = 20;
        double res = getEistimate(x0, x1, e, N);
        System.out.println("快速弦截法方式得到的解为：" + res);
    }

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
        return Math.log(x) + x * x;
    }


}
