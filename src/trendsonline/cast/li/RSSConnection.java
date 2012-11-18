package trendsonline.cast.li;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class RSSConnection {
	public void requestHTTPGET(final String reqUrl, final MainActivity parent) {

		Thread thread = new Thread()
		{
			@Override
			public void run() {
				try {
					URL url;
					url = new URL(reqUrl);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();



					BufferedReader reader = null;
					reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String line = "";
					String res = "";
					reader.readLine();
					while ((line = reader.readLine()) != null) {
						res += "\n" + line;
					}
					reader.close();
					
					parent.httpGetFinished(res);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

			}
		};

		thread.start();
	}
}
