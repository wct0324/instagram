package com.codepath.instagram;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoAdapter extends ArrayAdapter<Photo> {
	private DecimalFormat df = new DecimalFormat("#,###");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");

	public PhotoAdapter(Context context, List<Photo> photoList) {
		super(context, R.layout.photo_layout, photoList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Photo photo = this.getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.photo_layout, parent, false);
		}

		ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
		ivPhoto.getLayoutParams().height = photo.getImageHeight();
		ivPhoto.setImageResource(0);
		Picasso.with(getContext()).load(photo.getImageURL()).into(ivPhoto);

		ImageView ivUser = (ImageView) convertView.findViewById(R.id.ivUser);
		ivUser.getLayoutParams().height = 30;
		ivUser.setImageResource(0);
		Picasso.with(getContext()).load(photo.getUserPicURL()).into(ivUser);

		TextView tvUser = (TextView) convertView.findViewById(R.id.tvUser);
		tvUser.setText("USER : " + photo.getUserName());

		TextView tvCaption = (TextView) convertView
				.findViewById(R.id.tvCaption);
		tvCaption.setText("CAPTION : " + photo.getCaption());

		TextView tvLike = (TextView) convertView.findViewById(R.id.tvLike);
		tvLike.setText("LIKES : "
				+ String.valueOf(df.format(photo.getLikeCount())));

		TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
		tvTime.setText("CREATED : " + this.sdf.format(photo.getTimestamp()));

		return convertView;
	}
}
