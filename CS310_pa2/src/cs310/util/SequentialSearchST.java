package cs310.util;

/******************************************************************************
 *  Compilation:  javac SequentialSearchST.java
 *  Execution:    java SequentialSearchST
 *  
 *  Symbol table implementation with linked list.
 *
 *  % java SequentialSearchST
 *  128.112.136.11
 *  208.216.181.15
 *  null
 *
 *  Remarks
 *  -------
 *    - Consider using a sentinel to eliminate annoying special
 *      case when deleting at the beginning of the list.
 *
 ******************************************************************************/

public class SequentialSearchST<Key, Value> {

    private int n;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next)  {
            this.key   = key;
            this.value = value;
            this.next  = next;
        }
    }

    // return number of key-value pairs
    public int size() {
        return n;
    }
    public int size1() {
    	int count = 0;
    	Node p = first;
    	while (p != null) {
    		count++;
    		p = p.next;
    	}
    	return count;
    }

    // does this symbol table contain the given key?
    public boolean contains(Object key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) return true;
        return false;
    }

    // add a key-value pair, replacing old key-value pair if key is already present
    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        n++;
    }

    // remove key-value pair with given key, and return associated value
    // return null if no such key
    public Value remove(Object key) {
        // special cases
        if (first == null) return null;

        if (key.equals(first.key)) { // wrong type keys won't be equals
            Value val = first.value;
            first = first.next;
            n--;
            return val;
        }

        // general case
        for (Node x = first; x.next != null; x = x.next)
            if (key.equals(x.next.key)) {
                Value val = x.next.value;
                x.next = x.next.next;
                n--;
                return val;
            }
        return null;
    }

    // return the value associated with the key, or null if the key is not present
    public Value get(Object key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.value;  // wrong type keys won't be equals
        }
        return null;     // not found
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }


   /***************************************************************************
    * Test routine.
    ***************************************************************************/
    public static void main(String[] args) {
        SequentialSearchST<String, String> st = new SequentialSearchST<String, String>();

        // insert some key-value pairs
        st.put("www.cs.princeton.edu",   "128.112.136.11");
        st.put("www.cs.princeton.edu",   "128.112.136.35");
        st.put("www.princeton.edu",      "128.112.130.211");
        st.put("www.math.princeton.edu", "128.112.18.11");
        st.put("www.yale.edu",           "130.132.51.8");
        st.put("www.amazon.com",         "207.171.163.90");
        st.put("www.simpsons.com",       "209.123.16.34");
        st.put("www.stanford.edu",       "171.67.16.120");
        st.put("www.google.com",         "64.233.161.99");
        st.put("www.ibm.com",            "129.42.16.99");
        st.put("www.apple.com",          "17.254.0.91");
        st.put("www.slashdot.com",       "66.35.250.150");
        st.put("www.whitehouse.gov",     "204.153.49.136");
        st.put("www.espn.com",           "199.181.132.250");
        st.put("www.snopes.com",         "66.165.133.65");
        st.put("www.movies.com",         "199.181.132.250");
        st.put("www.cnn.com",            "64.236.16.20");
        st.put("www.iitb.ac.in",         "202.68.145.210");

        // search for IP addresses given URL
        System.out.println("size = " + st.size());
        System.out.println("size1 = " + st.size1());  
        System.out.println(st.get("www.cs.princeton.edu"));
        System.out.println(st.get("www.amazon.com"));
        System.out.println(st.get("www.amazon.edu"));
        System.out.println();

        // test out the iterator
        for (String s : st.keys()) 
        	 System.out.println(s);


        // print out all key-value pairs
        for (String s : st.keys())
        	 System.out.println(s + " " + st.get(s));
        System.out.println();

        System.out.println("Deleting");
        System.out.println(st.remove("www.princeton.edu"));
        System.out.println(st.remove("www.princeton.edu"));
    }

}
