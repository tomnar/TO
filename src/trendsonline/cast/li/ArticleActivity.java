package trendsonline.cast.li;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class ArticleActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(ListMobileActivity.ARTICLE_MESSAGE);
		String title = intent.getStringExtra(ListMobileActivity.ARTICLE_TITLE);
		WebView v = (WebView) findViewById(R.id.article_text);
		v.getSettings().setJavaScriptEnabled(true);
		v.loadData(message, "text/html; charset=UTF-8", null);
		setTitle(title);
	}


}
