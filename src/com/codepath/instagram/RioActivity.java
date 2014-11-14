package com.codepath.instagram;

import java.util.ArrayList;
import java.util.Date;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class RioActivity extends Activity {

	public static final String clientID = "177173359dae4bd3bf2e6b31466a62e1";

	private ArrayList<Photo> photoList;
	private PhotoAdapter photoAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rio);
		this.fetchPopularPhotos();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.rio, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Method : fetchPopularPhotos
	 */
	private void fetchPopularPhotos() {
		this.photoList = new ArrayList<Photo>();
		this.photoAdapter = new PhotoAdapter(this, this.photoList);

		ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
		lvPhotos.setAdapter(this.photoAdapter);

		String url = "https://api.instagram.com/v1/media/popular?client_id="
				+ clientID;

		AsyncHttpClient client = new AsyncHttpClient();

		client.get(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				JSONArray jsonArray = null;
				JSONObject jsonObject;
				Photo photoEntity;

				try {
					photoList.clear();

					jsonArray = response.getJSONArray("data");

					for (int i = 0; i < jsonArray.length(); i++) {
						jsonObject = jsonArray.getJSONObject(i);

						photoEntity = new Photo();
						photoEntity.setCaption(getCaption(jsonObject));
						photoEntity.setImageHeight(getImageHeight(jsonObject));
						photoEntity.setImageURL(getImageURL(jsonObject));
						photoEntity.setLikeCount(getLikeCount(jsonObject));
						photoEntity.setTimestamp(getTimestamp(jsonObject));
						photoEntity.setUserName(getUserName(jsonObject));
						photoEntity.setUserPicURL(getUserPicURL(jsonObject));
						photoList.add(photoEntity);
					}

					photoAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
			}
		});
	}

	/**
	 * Method : getCaption
	 */
	private String getCaption(JSONObject o) throws JSONException {
		if (o.getJSONObject("caption") != null
				&& o.getJSONObject("caption").getString("text") != null) {
			return o.getJSONObject("caption").getString("text");
		}

		return "No Caption";
	}

	/**
	 * Method : getImageHeight
	 */
	private int getImageHeight(JSONObject o) throws JSONException {
		return o.getJSONObject("images").getJSONObject("standard_resolution")
				.getInt("height");
	}

	/**
	 * Method : getImageURL
	 */
	private String getImageURL(JSONObject o) throws JSONException {
		return o.getJSONObject("images").getJSONObject("standard_resolution")
				.getString("url");
	}

	/**
	 * Method :getLikeCount
	 */
	private int getLikeCount(JSONObject o) throws JSONException {
		return o.getJSONObject("likes").getInt("count");
	}

	/**
	 * getTimestamp
	 */
	private Date getTimestamp(JSONObject o) throws JSONException {
		return new Date(o.getLong("created_time") * 1000);
	}

	/**
	 * Method : getUserName
	 */
	private String getUserName(JSONObject o) throws JSONException {
		return o.getJSONObject("user").getString("username");
	}

	/**
	 * Method : getUserPicURL
	 */
	private String getUserPicURL(JSONObject o) throws JSONException {
		return o.getJSONObject("user").getString("profile_picture");
	}
}
