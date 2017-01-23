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

        try {
            URL ulr = new URL(url);
            HttpURLConnection con = (HttpURLConnection) ulr.openConnection();
            con.setDoOutput(true);

            String data = URLEncoder.encode("login", "UTF-8")
                    + "=" + URLEncoder.encode(login, "UTF-8");

            data += "&" + URLEncoder.encode("senha", "UTF-8") + "="
                    + URLEncoder.encode(senha, "UTF-8");

            String text = "";
            BufferedReader reader = null;
            try {


                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(data);
                wr.flush();

                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    // Append server response in string
                    sb.append(line + "\n");
                }

                text = sb.toString();

            }catch (Exception e) {
                e.printStackTrace();
                getlog();
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                resp = sw.toString();
            }
            finally {

                reader.close();
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
        }catch (IOException e){
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
            java.lang.Process process = Runtime.getRuntime().exec("logcat -t 500 -f /storage/sdcard1/log.txt");
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

    protected void onPostExecute(String result) {

    }
}
