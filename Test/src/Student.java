/* Like Orders, Students are ordered on basis of id only. */
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class Student implements Comparable<Student>  {  
	
	private int id;
	private String name;
	private double gpa;

	public Student(String n, int i, double gpa) { 
		name = n; id = i; this.gpa = gpa;
		} 
	
    static HashMap<String,String> book = new HashMap<String,String>();
	
	private static class Book {
		
		
		
		public static boolean hasBook(String ISBN) {
			
			return book.containsKey(ISBN);
			
		}
		
	}
	
	public String whichBook(Object Student, String ISBN) {
		
		String title = null;
		
		if(Book.hasBook(ISBN)) {
			title = book.get(ISBN);
		}
		
		
		return title;
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getGpa() {
		return gpa;
	}
	
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public boolean equals(Object rhs)  {   // more compact code, but still correct
		if (rhs == null || getClass() != rhs.getClass()) 
			return false; 
		Student other = (Student) rhs;
		return id == other.id; 
	} 
	
	public int compareTo(Student other) {
		return Integer.valueOf(id).compareTo(Integer.valueOf(other.id)); 
		} 
	
	public int hashCode() {
		return Integer.valueOf(id).hashCode(); 
		}
	
	public String toString() { 
		return "(" + name+ " " + id + " " + gpa + ")";
		} 
	
	public static class Sortbygpa implements Comparator<Student> 
	{ 
	    // Used for sorting in ascending order of 
	    // gpa 
	    public int compare(Student a, Student b) 
	    { 
	        return (int) (a.gpa - b.gpa); 
	    } 
	}
	
	public static void main(String[] args) 
    { 
 
          
        ArrayList<Student> ar = new ArrayList<Student>(); 
        ar.add(new Student("Bob", 1, 4.1)); 
        ar.add(new Student("Joe", 2, 3.0)); 
        ar.add(new Student("Bob", 3, 3.9)); 
  
        System.out.println("Unsorted"); 
        for (int i=0; i<ar.size(); i++) 
            System.out.println(ar.get(i)); 
  
        Collections.sort(ar, new Sortbygpa()); 
  
        System.out.println("\nSorted by gpa"); 
        for (int i=0; i<ar.size(); i++) 
            System.out.println(ar.get(i)); 
    }
}