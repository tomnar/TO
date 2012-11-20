package trendsonline.cast.li;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import trendsonline.cast.li.adaptor.MobileArrayAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class ListMobileActivity extends ListActivity {
	
	public final static String ARTICLE_MESSAGE = "trendsonline.cast.li.article_message";
	public final static String ARTICLE_TITLE = "trendsonline.cast.li.article_title";
	ArrayList<Article> articles;
	
	String[] articlez = new String[] {"Connection Error", "Try to Reconnect."};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//creates a list for the articles
		articles = new ArrayList<Article>();
		Log.w("apP", "received");
		AsyncHttpClient client = new AsyncHttpClient();
		//fetches the rss feed
		client.get("https://dl.dropbox.com/u/2440776/trendsonline.rss", new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
				httpGetFinished(response);
				
		    }
		});
		setTitle("Nyheder");
		
	}
	
	public void httpGetFinished(String res) {
		Log.w("app", "RSS received, length: " + String.valueOf(res.length()));

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder(); 
			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(res.getBytes("utf-8")))); 
			
			//transfers the content of the rss to the articles array.
			NodeList items = doc.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++){
				if (items.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element n = (Element) items.item(i);
					String title = n.getElementsByTagName("title").item(0).getTextContent();
					String link = n.getElementsByTagName("link").item(0).getTextContent();
					String date = n.getElementsByTagName("pubDate").item(0).getTextContent();
					String description = n.getElementsByTagName("description").item(0).getTextContent();
					String content = n.getElementsByTagName("content:encoded").item(0).getTextContent();
					articles.add(new Article(title, link, date, description, content));
				}
			}
			
			//runs through the article array to get all the titles.
			ArrayList<String> titles = new ArrayList<String>();
			for (Article a : articles) {
				titles.add(a.getTitle());
			}
			//transfers the titles to a String[]
		    articlez = new String[titles.size()];
		    articlez = titles.toArray(articlez);
		    setListAdapter(new MobileArrayAdapter(this, articlez));
		    
			Log.w("app", "number of articles loaded: " + String.valueOf(items.getLength()));


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Sets up the Clicklistener on every item on the list.
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.w("app", "list item clicked");
		
		Intent intent = new Intent(this, ArticleActivity.class);
		//pushes content to the intent
	    intent.putExtra(ARTICLE_MESSAGE, articles.get(position).getContent());
	    intent.putExtra(ARTICLE_TITLE, articles.get(position).getTitle());
	    startActivity(intent);
	}

}