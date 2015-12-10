import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpClock {
	private final URL url;
	private volatile long x = 0;

	public HttpClock( String u ) throws MalformedURLException {
		url = new URL( u );
	}

	public void sync() throws Exception {
		URLConnection connection = url.openConnection();
		connection.setReadTimeout( 5000 );
		connection.setConnectTimeout( 5000 );
		connection.connect();
		long res = connection.getHeaderFieldDate( "Date", 0 );
		if( res == 0 )
			throw new Exception( "time sync failed" );
		x = res - System.nanoTime() / 1000000;
	}

	public long getTime() throws Exception {
		if( x == 0 )
			throw new Exception( "no sync" );
		return x + System.nanoTime() / 1000000;
	}
}
