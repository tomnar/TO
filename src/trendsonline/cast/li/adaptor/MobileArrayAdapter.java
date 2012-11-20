package trendsonline.cast.li.adaptor;

import java.io.InputStream;
import java.net.URL;

import trendsonline.cast.li.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

public class MobileArrayAdapter extends ArrayAdapter<String> {
	//Setting up global private variables
	private final Context context;
	private final String[] values;
	
	
	public MobileArrayAdapter(Context context, String[] values) {
		super(context, R.layout.list_mobile, values);
		this.context = context;
		this.values = values;
	}

	
	// Initiases the view, and sets the right content at the list view.
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View rowView = inflater.inflate(R.layout.list_mobile, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position]);
		Animation animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fadein);
	    textView.startAnimation(animationFadeIn);
		
		// Change icon based on name
		String s = values[position];

		System.out.println(s);

		try {
			//Setting up url to stock image
			URL url;
			url = new URL(
					"https://twimg0-a.akamaihd.net/profile_images/1332196839/heart_100px_normal.jpg");
			
			//looking if the String[] is not initialized and gives the user an error.
			if (s.equals("Connection Error")) {
				AsyncHttpClient client2 = new AsyncHttpClient();
				String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
				client2.get(
						url.toString(),
						new BinaryHttpResponseHandler(allowedContentTypes) {
							@Override
							public void onSuccess(byte[] fileData) {
								ImageView v = (ImageView) rowView.findViewById(R.id.logo);
								
								v.setImageBitmap(BitmapFactory.decodeByteArray(
										fileData, 0, fileData.length));
								Animation animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fadein);
							    v.startAnimation(animationFadeIn);
							}
						});
				
				Log.w("lala", "Fajl");
			} else if (s.equals("Try to Reconnect")) {
				AsyncHttpClient client2 = new AsyncHttpClient();
				String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
				client2.get(
						url.toString(),
						new BinaryHttpResponseHandler(allowedContentTypes) {
							@Override
							public void onSuccess(byte[] fileData) {
								ImageView v = (ImageView) rowView.findViewById(R.id.logo);

								v.setImageBitmap(BitmapFactory.decodeByteArray(
										fileData, 0, fileData.length));
								Animation animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fadein);
							    v.startAnimation(animationFadeIn);
							}
						});
			} else {
				
				AsyncHttpClient client2 = new AsyncHttpClient();
				String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
				client2.get(
						url.toString(),
						new BinaryHttpResponseHandler(allowedContentTypes) {
							@Override
							public void onSuccess(byte[] fileData) {
								ImageView v = (ImageView) rowView.findViewById(R.id.logo);

								v.setImageBitmap(BitmapFactory.decodeByteArray(
										fileData, 0, fileData.length));
								Animation animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fadein);
							    v.startAnimation(animationFadeIn);
							}
						});
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowView;
	}

}
