package thiagocury.eti.br.minutotrem.misc;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by thiagocury on 08/09/15.
 */
public class ConnectParse extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Enable Local Datastore.
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "Eca7sP2zfVC2thuuwEeXVZjVH7Cyy7wk3Q3qAXIo", "idQ0cYkoixYtP2vsDA2NkS56dPaotgIOFk0pA58Y");
	}
}
