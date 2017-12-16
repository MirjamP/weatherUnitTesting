package open;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mirja on 26/11/2017.
 */
public class HttpReader {
    public InputStreamReader read(URL url) throws IOException {
        if (Boolean.parseBoolean(ResourceBundle.getBundle("strings").getString("SIMULATE_NO_INTERNET"))) {
            throw new IOException("Your internet connection has been disabled by comcast");
        }
        return new InputStreamReader(url.openStream());
    }
}
