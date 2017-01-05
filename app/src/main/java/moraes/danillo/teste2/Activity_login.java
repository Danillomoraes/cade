package moraes.danillo.teste2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Activity_login extends AppCompatActivity{

    public ImageView img_background;
    public int[] back;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 17) {
            getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                );
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_login);

        Palette palette;
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_book_background);
        if (myBitmap != null && !myBitmap.isRecycled()) {
          palette = Palette.from(myBitmap).generate();

            int i_default = 0x000000;
            int vibrant = palette.getVibrantColor(i_default);
            int vibrantlight = palette.getLightVibrantColor(i_default);
            int vibrantdark = palette.getDarkVibrantColor(i_default);
            int muted = palette.getMutedColor(i_default);
            int mutedlight = palette.getLightMutedColor(i_default);
            int muteddark = palette.getDarkMutedColor(i_default);
        }

        back = new int[7];

        back[0] = R.drawable.image_book_background;
        back[1] = R.drawable.image_books;
        back[2] = R.drawable.image_books2;
        back[3] = R.drawable.image_books3;
        back[4] = R.drawable.image_book4;
        back[5] = R.drawable.image_book5;
        back[6] = R.drawable.image_senhor;
        back[7] = R.drawable.image_hobbit;

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

    }
    @Override
    protected void onStart () {
        super.onStart();

        try {
            if (i > 7) {
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

        //img_background = (ImageView) findViewById(R.id.img_background);
        RelativeLayout rela = (RelativeLayout) findViewById(R.id.relativelayaout);

        bit_back = BitmapFactory.decodeResource(getResources(), r);

        //getting screen resolution
        DisplayMetrics met = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(met);

        float x = (float) (met.widthPixels)/(float) (bit_back.getWidth()) ; //getting scale to downscale
        bit_back = Bitmap.createScaledBitmap(bit_back, (int)(bit_back.getWidth()*x), (int)(bit_back.getHeight()*x), true );

        //img_background.setImageBitmap(bit_back);
        rela.setBackground(new BitmapDrawable(bit_back));
    }

    public void teste_cor (View v) {

        Palette palette;
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_book_background);
        palette = Palette.from(myBitmap).generate();

            int i_default = 0x000000;
            int vibrant = palette.getVibrantColor(i_default);
            int vibrantlight = palette.getLightVibrantColor(i_default);
            int vibrantdark = palette.getDarkVibrantColor(i_default);
            int muted = palette.getMutedColor(i_default);
            int mutedlight = palette.getLightMutedColor(i_default);
            int muteddark = palette.getDarkMutedColor(i_default);



        //TextView txt = (TextView) findViewById(R.id.textView2);
        Button bt = (Button) findViewById(R.id.bt_login);
        Button bt2 = (Button) findViewById(R.id.bt_signup);
        Button bt3 = (Button) findViewById(R.id.bt_face);
        Button bt4 = (Button) findViewById(R.id.bt_google);

        //bt.setBackgroundColor(vibrant);
        //bt2.setBackgroundColor(vibrantlight);

        bt.getBackground().setColorFilter(vibrant, PorterDuff.Mode.MULTIPLY);
        bt2.getBackground().setColorFilter(vibrantdark, PorterDuff.Mode.MULTIPLY);

        bt3.setBackgroundColor(vibrantdark);
        bt4.setBackgroundColor(muted);

    }

    public static Drawable setTint(Drawable drawable, int color) {
        final Drawable newDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(newDrawable, color);
        return newDrawable;
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
        Intent intent = new Intent(this, Acivity_main.class);
        startActivity(intent);

    }

    public void sign_form (View v) {
        Button bt = (Button) v;
        String nome = bt.getText().toString();
        LinearLayout lay_login = (LinearLayout) findViewById(R.id.linear_login);
        LinearLayout lay_sign = (LinearLayout) findViewById(R.id.linear_signup);

        if (nome.equals("sign up") == true) {
            lay_login.setVisibility(View.GONE);
            lay_sign.setVisibility(View.VISIBLE);
            bt.setText("back");
        }else if (nome.equals("back") == true) {
            lay_sign.setVisibility(View.GONE);
            lay_login.setVisibility(View.VISIBLE);
            bt.setText("sign up");
        }

        if (i>7) {
            i=0;
        }

        change_background(back[i]);
        i+=1;

    }

    public void getlog () {

        try {
            Process process = Runtime.getRuntime().exec("logcat -d > logcat.txt");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log=new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }

        }
        catch (IOException e) {}

    }
}
