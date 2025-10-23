package Tests;
import InputValidater.ValidateProduct;
import Main.Products;

public class ProductTest {
    public static void main(String[] args) {
        ValidateProduct validateProduct = new ValidateProduct();

        System.out.println("Validation test");

        Products selectedProduct = validateProduct.productValidated();

        System.out.println("Du valgte: " + selectedProduct.getLabel() + "  Pris:  " + selectedProduct.getPrice());
    }

}
