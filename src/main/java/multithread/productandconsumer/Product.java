package multithread.productandconsumer;

import java.util.UUID;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/27 12:11 PM
 * @description : 产品类
 */
public class Product {
    private UUID code;

    public Product(UUID code) {
        this.code = code;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }
}
