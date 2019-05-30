package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

	private static final int CONNECT_TIMEOUT = 2000;
	
	private static final int READ_TIMEOUT = 5000;
	
	private static final String ENCODING = "utf-8";
	

	public static String post(String url, String param) throws Exception {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL( url );
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput( true );
			conn.setDoInput( true );
			
			conn.setUseCaches( false );
			conn.setRequestMethod( "POST" );
			conn.setConnectTimeout( CONNECT_TIMEOUT );
			conn.setReadTimeout( READ_TIMEOUT );
			conn.setRequestProperty( "Content-Type", "application/json" );
			conn.setRequestProperty( "Accept", "application/json" );
			conn.setRequestProperty( "Authorization", "token" );

			conn.connect();

			out = new OutputStreamWriter( conn.getOutputStream(), ENCODING );
			out.write( param );

			out.flush();
			out.close();

			in = new BufferedReader( new InputStreamReader( conn.getInputStream(), ENCODING ) );
			String line = "";
			while ( ( line = in.readLine() ) != null ) {
				result += line;
			}
			in.close();
		} catch ( Exception e ) {
			e.printStackTrace();
			result = e.getMessage();
			if ( in != null ) {
				in.close();
			}
			throw e;
		} finally {
			try {
				if ( in != null || out != null ) {
					in.close();
					out.close();
				}
			} catch ( IOException e ) {
				e.printStackTrace();
				result = e.getMessage();
				throw e;
			}
		}
		return result;
	}
}
