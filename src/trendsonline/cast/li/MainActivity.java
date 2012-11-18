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

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity { 

	ArrayList<Article> articles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RSSConnection rssc = new RSSConnection();
		rssc.requestHTTPGET("http://trendsonline.dk/feed/", this); 
		articles = new ArrayList<Article>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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

			for (Article a : articles) {
				Log.w("app", a.getTitle());
			}

			Log.w("app", String.valueOf(items.getLength()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
