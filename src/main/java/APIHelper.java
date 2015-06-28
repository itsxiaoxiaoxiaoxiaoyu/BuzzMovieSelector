
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * APIHelper Class which helps send API requests to Rotten Tomatoes
 * and returns JSON Information.
 * @author huangdun
 */
public final class APIHelper {

    /**
     * APIKEY.
     */
    private static final String APIKEY = "n6u4camyzqju6hc4nf7wk8m2";
    /**
    *Private Constructor.
    */
    private APIHelper() { };
    /**
     * Static Method which help translate request map to the API request and
     * send it to Rotten Tomatoes to get requested information.
     * @param requestMap a map which contains all request names and values
     * @param requestType a String which contains request type
     * @return String returned by API
     */
    public static String apiRequest(final Map<String, String> requestMap,
            final String requestType) {
        StringBuffer requestBuf = new StringBuffer();
        requestBuf.append("http://api.rottentomatoes.com/api/public/v1.0");
        requestBuf.append(requestType);
        requestBuf.append("?apikey=");
        requestBuf.append(APIKEY);

        if (requestMap != null) {
            for (Map.Entry<String, String> requestEntry:requestMap.entrySet()) {
                requestBuf.append("&");
                requestBuf.append(requestEntry.getKey());
                requestBuf.append("=");
                requestBuf.append(requestEntry.getValue()
                        .replaceAll(" ", "+"));
            }
        }

        String request = requestBuf.toString();

        try {
            final int normal = 200;
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() != normal) {
                throw new IOException(conn.getResponseMessage());
            }
            StringBuilder sb;
            try (BufferedReader rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),
                            StandardCharsets.UTF_8))) {
                    sb = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        sb.append(line);
                }
            }

            conn.disconnect();
            return sb.toString();

        } catch (MalformedURLException ex) {
            Logger.getLogger(APIHelper.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (IOException ex) {
            Logger.getLogger(APIHelper.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

       return null;
    }
}
