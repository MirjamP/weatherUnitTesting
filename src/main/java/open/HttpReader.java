package open;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class HttpReader {
    public InputStreamReader read(URL url) throws IOException {
        return new InputStreamReader(url.openStream());
    }
}
