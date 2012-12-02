package trendsonline.cast.li;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ArticleActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		
		//fetches the intent from the ListMobileActivity. 
		Intent intent = getIntent();
		String message = intent.getStringExtra(ListMobileActivity.ARTICLE_MESSAGE);
		String title = intent.getStringExtra(ListMobileActivity.ARTICLE_TITLE);
		//sets up a WebView with javascript enabled.
		WebView v = (WebView) findViewById(R.id.article_text);
		v.getSettings().setJavaScriptEnabled(true);
		v.loadData(message, "text/html; charset=UTF-8", null);
		//sets the title bar to the title of the article.
		setTitle(title);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}


}
