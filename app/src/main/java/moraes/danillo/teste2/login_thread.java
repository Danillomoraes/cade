package moraes.danillo.teste2;

import android.content.Context;
import android.icu.text.DateFormat;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
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
        String resp= "";
        InputStream stream;
        BufferedReader reader;
        URL ulr;
        HttpURLConnection con;

        try {
            ulr = new URL(url);
            con = (HttpURLConnection) ulr.openConnection();
            con.addRequestProperty("login", "danillom");
            con.addRequestProperty("senha", "zelda9891");
            con.setRequestMethod("GET");
            con.setDoInput(true);

            con.connect();

            int responcecode = con.getResponseCode();

            if (responcecode != HttpURLConnection.HTTP_OK) {
                return String.valueOf(responcecode);
            }

            stream = con.getInputStream();

            if (stream != null) {
                resp = readStream(stream, 1500);
            }

        }catch (MalformedURLException e) {
            getlog();
            resp= e.getStackTrace().toString();
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            resp = sw.toString();
        }catch (NetworkOnMainThreadException e){
            getlog();
            resp= e.getStackTrace().toString();
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            resp = sw.toString();
        }catch (Exception e){
            getlog();
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            resp = sw.toString();
        }
        finally {

            return resp;
        }
    }

    public String getlog () {
        StringBuilder log=new StringBuilder();
        try {
            java.lang.Process process = Runtime.getRuntime().exec("logcat -t 1000 -f /log.txt");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }

        }
        catch (IOException e) {}

        return  log.toString();

    }

    private String readStream(InputStream stream, int maxLength) throws IOException {
        String result = null;
        // Read InputStream using the UTF-8 charset.
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        // Create temporary buffer to hold Stream data with specified max length.
        char[] buffer = new char[maxLength];
        // Populate temporary buffer with Stream data.
        int numChars = 0;
        int readSize = 0;
        while (numChars < maxLength && readSize != -1) {
            numChars += readSize;
            int pct = (100 * numChars) / maxLength;
            readSize = reader.read(buffer, numChars, buffer.length - numChars);
        }
        if (numChars != -1) {
            // The stream was not empty.
            // Create String that is actual length of response body if actual length was less than
            // max length.
            numChars = Math.min(numChars, maxLength);
            result = new String(buffer, 0, numChars);
        }
        return result;
    }

    protected void onPostExecute(String result) {

    }
}
