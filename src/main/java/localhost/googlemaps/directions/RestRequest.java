package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * 
 *
 * Sample class that makes a request to Google Maps
 *
 *@author Tucker Allard
 *@since 2016-09-06
 *
 */
public class RestRequest 
{
	/**
	 * The link to the website
	 */
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	
	/**
	 * The character set to use when encoding URL parameters
	 */
	protected static String charset = "UTF -8";
	
	/**
	 * The API key used for making request to API
	*/
	protected static String key = "AIzaSyDGP5PNMHqUms__GLT_Org_lRAPxe-qIx8";
	
	public static void main(String[] args)
	{

		try 
		{
			//The origin for starting point for direction
			String origin = "Columbia MD";
			
			//The destination or end point for directions
			String destination = "Charlotte NC";
			
			//The return type of the response xmL|json
			String returnType = "json";
			
			//creates the url parameters as a string encoding them with the defined char
			String queryString = String.format("origin=%s&destination=%s&key=%s",
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination,charset),
					URLEncoder.encode(key, charset));
			
			//creates a new URL out of the endpoint, returnType and queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
			//if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() !=200) 
			{
				throw new RuntimeException("Failed : HTTP error code: " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it retruns null meaning there are no more lines
			while (br.readLine() != null) 
			{
				System.out.println(br.readLine());
				
			}
			
			//close connection to API
			connection.disconnect();
			
		}
		catch(MalformedURLException e)
			{
				e.printStackTrace();
			}
			
			catch(IOException e)
			{
				
			}
	}
}