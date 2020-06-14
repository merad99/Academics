public class Factorial {
// Returns n!.
  public static int factorial ( int n) {
    int result = 1;
    for (int i = 1; i <= n; i ++) {
         result *= i;
    }
    return result ;
  }
/* Test client . */
  public static void main ( String [] args ) {
    int n = Integer.parseInt ( args [0]);
      System.out.println (n + "! = " + Factorial.factorial (n ));
  }
}
