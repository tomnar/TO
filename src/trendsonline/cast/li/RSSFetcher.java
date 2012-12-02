package trendsonline.cast.li;

import com.loopj.android.http.AsyncHttpResponseHandler;

public interface RSSFetcher {
	public void get(AsyncHttpResponseHandler responseHandler);
}
