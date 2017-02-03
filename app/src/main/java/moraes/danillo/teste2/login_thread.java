package moraes.danillo.teste2;

import android.content.Context;
import android.icu.text.DateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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


public class login_thread extends Thread {
    String resp = "";
    String mUrl;
    String mLogin;
    String mSenha;
    Handler mHandler;

    login_thread (String url, String login, String senha, Handler h) {

        mUrl = url;
        mLogin = login;
        mSenha = senha;
        mHandler = h;

    }

    @Override
    public void run() {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        String error = "";
        InputStream stream = null;
        BufferedReader reader;
        URL ulr;
        HttpURLConnection con;
        Message msg = mHandler.obtainMessage();
        Bundle b = new Bundle();
        JSONObject json;

        try {
            ulr = new URL(mUrl);
            con = (HttpURLConnection) ulr.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            con.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            con.addRequestProperty("login", mLogin);
            con.addRequestProperty("senha", mSenha);

            if (con.getContentLength() > 0) {
                stream = con.getInputStream();
            } else {
                resp = String.valueOf(con.getResponseCode());
            }

            if (stream != null) {
                resp = readStream(stream, 1500);

                if (resp != "[]") {

                    json = new JSONObject(resp);

                    String cod = json.getString("cod_user");
                    String user = json.getString("login_user");

                    if (!cod.equals("0")) {
                        b.putInt("state", 1);
                        b.putString("cod_user",cod);
                        msg.setData(b);
                        mHandler.sendMessage(msg);
                    } else {
                        b.putInt("state", 2);
                        msg.setData(b);
                        mHandler.sendMessage(msg);
                    }
                }else{
                    b.putInt("state", 3);
                    msg.setData(b);
                    mHandler.sendMessage(msg);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            error = sw.toString();
            b.putInt("state", 3);
            msg.setData(b);
            mHandler.sendMessage(msg);

        } finally {

            if (error != "") {
                try {
                    writeFile(error, "error");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    public String getResp() {
        return resp;
    }

    public void writeFile(String resp, String fileName) throws IOException {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName+".txt");
        FileOutputStream strem = null;
        try {
            strem = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            strem.write(resp.getBytes());

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
           strem.close();
        }

    }
}
