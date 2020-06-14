package shape;

import java.util.Arrays;

class ShapeDemo
{
    public static double totalArea( Shape [ ] arr )
    {
        double total = 0;
        
        for( Shape s : arr )
            if( s != null )
                total += s.area( );
        
        return total;
    }

    public static double totalArea( java.util.List<? extends Shape> arr )
    {
        double total = 0;
        
        for( Shape s : arr )
            if( s != null )
                total += s.area( );
        
        return total;
    }
    
    public static double totalSemiperimeter( Shape [ ] arr )
    {
        double total = 0;
        
        for( Shape s : arr )
            if( s != null )
                total += s.semiperimeter( );
        
        return total;
    }
    
    public static void printAll( Shape [ ] arr )
    {
        for( Shape s : arr )
            System.out.println( s );
    }
    
    public static void main( String [ ] args )
    {
        Shape [ ] a = { new Circle( 2.0 ), new Rectangle( 1.0, 3.0 ),
        		new Circle( 0.5 ), new Square( 2.0 ) };
       
        System.out.println( "Total area = " + totalArea( a ) );
        System.out.println( "Total semiperimeter = " + totalSemiperimeter( a ) );
        
        java.util.List<Square> lst = new java.util.ArrayList<Square>( );
        lst.add( new Square( 2.0 ) );
        lst.add( new Square( 1.0 ) );
        
        java.util.TreeSet<Shape> tst = new java.util.TreeSet<Shape>( );
        tst.add( new Square( 2.0 ) );
        tst.add( new Circle( 2.0 ) );
        tst.add( new Circle( 0.5 ) );
        tst.add( new Rectangle( 1.0, 3.0 ) );
        System.out.println(tst);
        //System.out.println( "Total area = " + totalArea( lst ) );
        Arrays.sort(a);
        printAll( a );
        
        
        for (Shape i : a) { 
        	if(i instanceof Stretchable) {
        		
        		( (Stretchable) i).stretch(2);
        		
        	}
        }
        printAll( a );
    }
    
}