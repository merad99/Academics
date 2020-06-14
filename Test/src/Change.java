
public class Change {
	//k is number of denominations of the coin or length of d
	  public static int coinChange(int differentCoins[], int maxChange) {
		  
	    int[] coinsUsed = new int[maxChange+1];
	    coinsUsed[0] = 0;

	    int[] lastCoin = new int[maxChange+1];
	    lastCoin[0] = 0;

	    for(int cents=1; cents<=maxChange; cents++) {
	      int minCoins = cents;
	      int newCoin = 0;

	      for(int i=1; i<differentCoins.length; i++) {
	        if(cents >= differentCoins[i]) {
	          if((1+coinsUsed[cents-differentCoins[i]]) < minCoins) {
	            minCoins = 1+coinsUsed[cents-differentCoins[i]];
	            newCoin = i;
	          }
	        }
	      }
	      coinsUsed[cents] = minCoins;
	      lastCoin[cents] = newCoin;
	    }

	    int l = maxChange;
	    while(l>0) {
	      System.out.println(differentCoins[lastCoin[l]]);
	      l = l-differentCoins[lastCoin[l]];
	    }
	    return coinsUsed[maxChange];
	  }

	  public static void main(String[] args) {
	    // array starting from 1, element at index 0 is fake
	  int d[] = {1, 5, 10, 18};
	  coinChange(d, 22);
	  }

}
