package trendsonline.cast.li;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class ArticleActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		TextView v = (TextView) findViewById(R.id.article_text);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.ARTICLE_MESSAGE);
		v.setText(Html.fromHtml(message));
		Log.w("app", "hi: " + message);
	}


}
