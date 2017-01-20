package moraes.danillo.teste2;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.NetworkOnMainThreadException;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ThreadLocalRandom;


//import com.lgvalle.material_animations.databinding.ActivityTransition2Binding;

public class Activity_login extends AppCompatActivity{

    public ImageView img_background;
    public int[] back;
    int i = 0;
    public String respo;
    public String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 17) {
            //getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                //View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_login);

        back = new int[13];

        back[0] = R.drawable.image_book_background;
        back[1] = R.drawable.image_books;
        back[2] = R.drawable.image_books2;
        back[3] = R.drawable.image_books3;
        back[4] = R.drawable.image_book4;
        back[5] = R.drawable.image_book5;
        back[6] = R.drawable.image_senhor;
        back[7] = R.drawable.image_apocalispe;
        back[8] = R.drawable.image_codigo_da_vinci;
        back[9] = R.drawable.image_excalibur;
        back[10] = R.drawable.image_martin;
        back[11] = R.drawable.image_retorno_do_rei;
        back[12] = R.drawable.image_hobbit;

        //TextView txt = (TextView) findViewById(R.id.textView2);
        //Button bt = (Button) findViewById(R.id.bt_login);
        //Button bt2 = (Button) findViewById(R.id.bt_signup);
        //Button bt3 = (Button) findViewById(R.id.bt_facebook);
        //Button bt4 = (Button) findViewById(R.id.bt_google);
        //Button bt5 = (Button) findViewById(R.id.button);
        //Button bt6 = (Button) findViewById(R.id.button2);

        //bt.setTextColor(Color.WHITE);
        //bt2.setTextColor(Color.WHITE);
        //bt3.setTextColor(Color.WHITE);
        //bt4.setTextColor(Color.WHITE);
        //txt.setTextColor(Color.WHITE);

        /*
        final Button button = (Button) findViewById(R.id.button);
        final EditText tb_teste = (EditText) findViewById(R.id.editText);
        final TextView lb_hello = (TextView) findViewById(R.id.textView);
        View view =findViewById(R.id.content);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lb_hello.setText(tb_teste.getText());

            }
        }); */

        Button bt_login = (Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                callactivity(v);
            }
        });

    }
    @Override
    protected void onStart () {
        super.onStart();

        if (Build.VERSION.SDK_INT >= 21) {
            i = ThreadLocalRandom.current().nextInt(0, 12 + 1);
        }
        try {
            if (i > 12) {
                i = 0;
            }
            change_background(back[i]);

            i += 1;

        }
        catch (Exception o) {

            getlog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

  /*  public void Teste (View v) {
        TextView lb2 = (TextView) findViewById(R.id.textView2);
        EditText tb = (EditText) findViewById(R.id.editText);

        lb2.setText(tb.getText());
    } */

    public void change_background (View v, int r) {
        Bitmap bit_back;
        img_background = (ImageView) findViewById(R.id.img_background);

        bit_back = BitmapFactory.decodeResource(getResources(), r);

        //getting screen resolution
        DisplayMetrics met = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(met);

        float x = (float) (met.widthPixels)/(float) (bit_back.getWidth()) ; //getting scale to downscale
        bit_back = Bitmap.createScaledBitmap(bit_back, (int)(bit_back.getWidth()*x), (int)(bit_back.getHeight()*x), true );

        img_background.setImageBitmap(bit_back);
    }

    public void change_background (int r) {
        Bitmap bit_back;
        Bitmap bit_blur;

        img_background = (ImageView) findViewById(R.id.img_background);
        RelativeLayout rela = (RelativeLayout) findViewById(R.id.relativelayaout);

        bit_back = BitmapFactory.decodeResource(getResources(), r);

        //getting screen resolution
        DisplayMetrics met = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(met);

        float x = (float) (met.widthPixels)/(float) (bit_back.getWidth()) ; //getting scale x to downscale
        float y = (float) (met.heightPixels)/(float) (bit_back.getHeight()); //gettin scale y to downscale
        float z;

        if (bit_back.getHeight() > bit_back.getWidth()) {

            z = y;

        } else {

            z = x;
        }

        bit_back = Bitmap.createScaledBitmap(bit_back, (int)(bit_back.getWidth()*z), (int)(bit_back.getHeight()*z), true );
        bit_blur = getBlur(bit_back, 12);

        img_background.setImageBitmap(bit_blur);
        img_background.setAlpha(0.7f);
        //rela.setBackground(new BitmapDrawable(bit_back));
    }

    public void validalogin (View v) {
        EditText tb_email = (EditText) findViewById(R.id.tb_email);
        EditText tb_senha = (EditText) findViewById(R.id.tb_senha);

        if (tb_email != null || tb_senha != null) {
            Toast.makeText(getApplicationContext(), "Logando!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Email ou Senha incorretos!", Toast.LENGTH_LONG).show();
        }
    }

    public void callactivity (View v) {
        /*try {
            URL url = new URL("http://webservice_php-danillodan5243966.codeanyapp.com/server_rest.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.addRequestProperty("login","danillom");
            con.addRequestProperty("senha","zelda9891");
            con.setRequestMethod("POST");

            String resp = con.getResponseMessage();

            Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_LONG).show();

        }catch (MalformedURLException e) {
            getlog();
        }catch (IOException e){
            getlog();
        } */
        final String url = "http://webservice_php-danillodan5243966.codeanyapp.com/server_rest.php";
        final String login = "danillom";
        final String senha = "zelda9891";

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    URL ulr =new URL(url);
                    HttpURLConnection con = (HttpURLConnection) ulr.openConnection();
                    con.addRequestProperty("login", login);
                    con.addRequestProperty("senha", senha);

                    InputStream response = new BufferedInputStream(con.getInputStream());

                    respo = convertStreamToString(response);

                }catch (MalformedURLException e) {
                    //getlog();
                    error = e.getMessage();
                }catch (NetworkOnMainThreadException e){
                    //getlog();
                    e.printStackTrace();
                    error = e.toString();
                }catch (IOException e){
                    error = e.toString();
                }
                finally {

                }

            }
        };

        runnable.run();

        //resp = new login_thread().doInBackground(url, login, senha);
        if (error != "" ) {
            Toast t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
            t.show();
        }

        Intent intent = new Intent(this, Acivity_main.class);
        intent.putExtra("EXTRA_SESSION_ID", respo);
        startActivity(intent);
    }

    public void sign_form (View v) {
        Button bt = (Button) v;
        String nome = bt.getText().toString();
        LinearLayout lay_login = (LinearLayout) findViewById(R.id.linear_login);
        LinearLayout lay_sign = (LinearLayout) findViewById(R.id.linear_signup);
        RelativeLayout rela_google = (RelativeLayout) findViewById(R.id.rela_google);
        AnimatorSet set;
        Button bt_face = (Button) findViewById(R.id.bt_face);
        Button bt_google = (Button) findViewById(R.id.bt_google);
        Button bt_login = (Button) findViewById(R.id.bt_login);

        if (nome.equals("sign up") == true) {
            /*set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(lay_login,View.ALPHA, 0.0f),
                    ObjectAnimator.ofFloat(lay_login,View.Y, 100f)
                    );
            set.setDuration(100);
            set.start();*/

            lay_login.animate().alpha(0.0f).setDuration(100);
            lay_login.setVisibility(View.GONE);
            bt.animate().y(300f);
            bt_login.animate().y(150f);
            rela_google.animate().alpha(0.0f).setDuration(100);
            bt.setText("Voltar");
            bt_login.setText("Cadastrar");
            rela_google.setVisibility(View.GONE);
            lay_sign.setVisibility(View.VISIBLE);
            lay_sign.animate().alpha(1f);

        }else if (nome.equals("Voltar") == true) {
            lay_sign.animate().alpha(0.0f);
            lay_sign.setVisibility(View.GONE);
            lay_login.setVisibility(View.VISIBLE);
            lay_login.animate().alpha(1f).setDuration(100);
            bt.setText("sign up");
            bt.animate().y(150f);
            bt_login.setText("Logar");
            bt_login.animate().y(0.0f);
            rela_google.animate().setDuration(100);
            rela_google.setY(300f);
            rela_google.setVisibility(View.VISIBLE);
            rela_google.animate().alpha(1f);
        }

        if (i>7) {
            i=0;
        }

    }

    public String getlog () {
        StringBuilder log=new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }

            Toast toast = Toast.makeText(getApplicationContext(), log.toString(), Toast.LENGTH_LONG);
            toast.show();

        }
        catch (IOException e) {}

        return  log.toString();

    }

    public Bitmap getBlur (Bitmap in, float radius) { //method to set blur image, radius max 20
        Bitmap out;

        out = Bitmap.createBitmap(in);
        final RenderScript render = RenderScript.create(this);
        Allocation tmpIn = Allocation.createFromBitmap(render, in);
        Allocation tmpOut = Allocation.createFromBitmap(render, out);

        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(render, Element.U8_4(render));
        theIntrinsic.setRadius(radius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(out);

        return out;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
