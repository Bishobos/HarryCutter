package Tests;

import Main.Products;

import static Main.Products.SHAMPOO;

public class EnumTest {

    public static void main(String[] args){
        System.out.println(SHAMPOO.getLabel() + ": " + SHAMPOO.getPrice() + ("Kr"));
    }
}
