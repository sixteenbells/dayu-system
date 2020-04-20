package gof;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/6 12:58 PM
 * @description : 工厂方法
 * 工厂方法由抽象工厂、具体工厂、抽象产品、具体产品组成
 * http://c.biancheng.net/view/1348.html
 */
public class FactoryMethod {

    /**
     * 抽象产品
     */
    interface Product {
        void show();
    }

    class ProductA implements Product {

        @Override
        public void show() {
            System.out.println("显示产品A");
        }
    }

    class ProductB implements Product {
        @Override
        public void show() {
            System.out.println("显示产品B");
        }
    }

    /**
     * 抽象工厂
     */
    interface Factory {
        Product getProduct();
    }

    class ProductAFactory implements Factory{
        @Override
        public Product getProduct() {
            return new ProductA();
        }
    }

    class ProductBFactory implements Factory{
        @Override
        public Product getProduct() {
            return new ProductB();
        }
    }

    public static void main(String[] args) {
        FactoryMethod factoryMethod = new FactoryMethod();
        ProductAFactory productAFactory = factoryMethod.new ProductAFactory();
        ProductBFactory productBFactory = factoryMethod.new ProductBFactory();
        Product productA = productAFactory.getProduct();
        Product productB = productBFactory.getProduct();
        productA.show();
        productB.show();
    }

}
