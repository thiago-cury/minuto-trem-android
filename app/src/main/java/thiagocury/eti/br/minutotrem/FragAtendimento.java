package thiagocury.eti.br.minutotrem;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragAtendimento extends Fragment {


	public FragAtendimento () {
		// Required empty public constructor
	}


	private ImageView ivFace;
	private ImageView ivTwitter;
	private ImageView ivFlickr;
	private ImageView ivTrem;

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_frag_atendimento, container, false);

		ivFace = (ImageView) v.findViewById(R.id.ic_face);
		ivTwitter = (ImageView) v.findViewById(R.id.ic_twitter);
		ivFlickr = (ImageView) v.findViewById(R.id.ic_flickr);
		ivTrem = (ImageView) v.findViewById(R.id.ic_trem);

		ivFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Uri uri = Uri.parse("http://www.facebook.com/trensurboficial");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
			}
		});

		ivTwitter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Uri uri = Uri.parse("http://www.twitter.com/trensurb");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
			}
		});

		ivFlickr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Uri uri = Uri.parse("http://www.flickr.com/trensurb");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
			}
		});

		ivTrem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Uri uri = Uri.parse("http://www.trensurb.gov.br");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
			}
		});

		return v;
	}
}
