package multithread.productandconsumer;

import java.math.BigDecimal;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/12 7:54 PM
 * @description :
 */
public class Second {
    public static void main(String args[]) {
        int i, j;
        BigDecimal sum = BigDecimal.valueOf(0);
        int scope = 100;
        for (i = 2; i <= scope; i++) {
            for (j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j > Math.sqrt(i)) {
                BigDecimal product = BigDecimal.valueOf(1);
                for (int m = 1; m <= i; m++) {
                    product = product.multiply(new BigDecimal(m));
                }
                sum = sum.add(product);
            }
        }
        System.out.println("sum:" + sum.toString());
    }

}
