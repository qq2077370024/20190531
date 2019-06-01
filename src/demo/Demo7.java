package demo;

/**
 * 不用加减号实现数字相加减
 */
public class Demo7 {
    public static void main(String[] args) {
        System.out.println(Demo7.add(-1,5));

    }


    private static int add(int x, int y){
        if(y == 0) return x;
        int sum, carry;
        sum = x ^ y;
        carry = (x & y) << 1;
        return add(sum,carry);


    }
}
