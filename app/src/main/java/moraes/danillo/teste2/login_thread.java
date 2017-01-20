package moraes.danillo.teste2;

import android.content.Context;
import android.icu.text.DateFormat;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class login_thread extends AsyncTask<String, String, String> {

    @Override
    public String doInBackground(String... args ){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = args[0];
        String login = args[1];
        String senha = args[2];

        try {
            String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
            String param1 = login;
            String param2 = senha;
// ...

            String query = String.format("param1=%s&param2=%s",
                    URLEncoder.encode(param1, charset),
                    URLEncoder.encode(param2, charset));

            URLConnection con = new URL(url + "?" + query).openConnection();
            InputStream response = new URL(url).openStream();

            return response.toString();

        }catch (MalformedURLException e) {
            e.getStackTrace();
        }catch (Exception e){
            e.getStackTrace();
        }
        return "";
    }

    protected void onPostExecute(String result) {

    }

}
