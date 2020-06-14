package cs310;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;
import java.util.TreeSet;

public class TestTokenizer{


	public TestTokenizer( Reader inStream1, Reader inStream2 )
	{
		RegexTok = new RegexTokenizer( inStream1 );
		WeissTok = new WeissTokenizer( inStream2 );
	}



	private static JavaTokenizer WeissTok;   // tokenizer object
	private static JavaTokenizer RegexTok;

	public static void main(String[] args)

	{
		TreeSet<String> Weissts = new TreeSet<String>();
		TreeSet<String> Regexts = new TreeSet<String>();

		TestTokenizer p;

		try 
		{
			FileReader f1 = new FileReader( args[ 0 ] );
			FileReader f2 = new FileReader( args[ 0 ] );
			p = new TestTokenizer( f1, f2 );

			Weissts = (TreeSet<String>) TestTokenizer.executeTokenizer(WeissTok);
			//System.out.println(Weissts);

			Regexts = (TreeSet<String>) TestTokenizer.executeTokenizer(RegexTok);
			//System.out.println(Regexts);

			f1.close( );
			f2.close( );
		}
		catch( IOException e )
		{
			System.err.println( e + args[ 0 ] );
		}


		Weissts.removeAll(Regexts);
		//Regexts.removeAll(Weissts);
		System.out.println(Weissts);

	}



	static Set<String> executeTokenizer(JavaTokenizer tok) {	  

		TreeSet<String> Copie = new TreeSet<String>();


		String token;
		while ((token = tok.getNextID())!= "") {

			Copie.add(token + " on " + tok.getLineNumber());
		}

		return Copie;

	}





}
