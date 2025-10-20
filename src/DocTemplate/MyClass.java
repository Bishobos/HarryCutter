package DocTemplate;

/**
 * MyClass has a thorough description here using {@link MyOtherClass} with no # for external links and {@link #myMethod} with # for internal links. <br>
 * For more see <a href="https://www.baeldung.com/javadoc">Baeldung JavaDoc</a> <br>
 * Has Methods:<br>
 * {@link #myMethod(int, int)} short description here. <br>
 * Describe only callable methods. Non-Callable methods are linked to where they are used. <br>
 * Has Attributes: <br>
 * {@link #myInt} short description here.<br>
 * {@link #myString} short description here.
 */
public class MyClass {
    /**
     * myInt is thoroughly described here.
     */
    int myInt;
    /**
     * myString is thoroughly described here.
     */
    String myString;

    /**
     * myMethod has a thorough description here, it uses {@link #myOtherMethod(int, int)} <br>
     * @param x Description of x
     * @param y Description of y
     * @return return is described here
     */
    public int myMethod(int x, int y){

        return myOtherMethod(x, y);
    }

    /**
     * myOtherMethod has a thorough description here
     * @param x Description of x
     * @param y Description of y
     * @return return is described here
     */
    private int myOtherMethod(int x, int y){
        return x + y;
    }
}
