package trendsonline.cast.li;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TrendsOnlineRSSFetcher implements RSSFetcher{
	private AsyncHttpClient client = new AsyncHttpClient();
	private String url = "https://dl.dropbox.com/u/2440776/trendsonline.rss";
	private RequestParams params = null;
	
	public void get(AsyncHttpResponseHandler responseHandler) {
		client.get(url, params, responseHandler);
	}
}
