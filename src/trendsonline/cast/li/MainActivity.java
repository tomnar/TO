package trendsonline.cast.li;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory; 

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.loopj.android.http.*;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends ListActivity { 

	public final static String ARTICLE_MESSAGE = "trendsonline.cast.li.article_message";
	ArrayList<Article> articles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		articles = new ArrayList<Article>();
		Log.w("apP", "received");
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("https://dl.dropbox.com/u/2440776/trendsonline.rss", new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
				httpGetFinished(response);
		    }
		});
		
//		AsyncHttpClient client2 = new AsyncHttpClient();
//		String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
//		client2.get("http://cdn.media.xda-developers.com/images/XDA2013/thread/post/gplus_orange.png", new BinaryHttpResponseHandler(allowedContentTypes) {
//		    @Override
//		    public void onSuccess(byte[] fileData) {
//		    	ImageView v = (ImageView) findViewById(R.id.img);
//		    	
//		    	v.setImageBitmap(BitmapFactory.decodeByteArray(fileData , 0, fileData.length));
//		    }
//		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds it ems to the action bar if it is present.  
		getMenuInflater().inflate(R.menu.activity_main, menu);  
		return true;
	}

	public void httpGetFinished(String res) {
		Log.w("app", "RSS received, length: " + String.valueOf(res.length()));

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder(); 
			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(res.getBytes("utf-8")))); 

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

			ArrayList<String> titles = new ArrayList<String>();
			for (Article a : articles) {
				titles.add(a.getTitle());
			}

			Log.w("app", "number of articles loaded: " + String.valueOf(items.getLength()));

			@SuppressWarnings({ "rawtypes", "unchecked" })
			ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);

			setListAdapter(adapter);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.w("app", "list item clicked");
		String content = articles.get(position).getContent();
		
		Intent intent = new Intent(this, ArticleActivity.class);
	    intent.putExtra(ARTICLE_MESSAGE, articles.get(position).getContent());
	    startActivity(intent);
	}

}
