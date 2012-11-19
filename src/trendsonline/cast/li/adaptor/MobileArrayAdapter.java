package trendsonline.cast.li.adaptor;

import java.io.InputStream;
import java.net.URL;

import trendsonline.cast.li.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public MobileArrayAdapter(Context context, String[] values) {
		super(context, R.layout.list_mobile, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View rowView = inflater.inflate(R.layout.list_mobile, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position]);

		// Change icon based on name
		String s = values[position];

		System.out.println(s);

		try {
			URL url;
			url = new URL(
					"https://twimg0-a.akamaihd.net/profile_images/1332196839/heart_100px_normal.jpg");
			// Bitmap bmp =
			// BitmapFactory.decodeStream((InputStream)url.getContent());
			// imageView.setImageBitmap(bmp);
			// imageView.setImageResource(R.drawable.android_logo);

			
			
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
							}
						});
				
				//imageView.setImageResource(R.drawable.windowsmobile_logo);
				Log.w("lala", "Fajl");
			} else if (s.equals("Try to Reconnect")) {
				// imageViewz.setImageDrawable(grabImageFromUrl(url.toString()));
				// Bitmap bmp =
				// BitmapFactory.decodeStream(url.openConnection().getInputStream());
				// imageView.setImageBitmap(bmp);
				//imageView.setImageResource(R.drawable.ios_logo);
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
							}
						});
			} else if (s.equals("Blackberry")) {
				// imageViewz.setImageDrawable(grabImageFromUrl(url.toString()));
				// Bitmap bmp =
				// BitmapFactory.decodeStream(url.openConnection().getInputStream());
				// imageView.setImageBitmap(bmp);
				//imageView.setImageResource(R.drawable.blackberry_logo);
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
							}
						});
			} else {
				// imageViewz.setImageDrawable(grabImageFromUrl(url.toString()));
				// Bitmap bmp =
				// BitmapFactory.decodeStream(url.openConnection().getInputStream());
				// imageView.setImageBitmap(bmp);
				//imageView.setImageResource(R.drawable.android_logo);
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
							}
						});
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowView;
	}

	private Drawable grabImageFromUrl(String url) throws Exception {
		Log.w("lala", "Fajlzzzzzzzzz");
		return Drawable.createFromStream(
				(InputStream) new URL(url).getContent(), "src");
	}

}
