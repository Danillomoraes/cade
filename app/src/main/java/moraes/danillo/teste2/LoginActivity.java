package moraes.danillo.teste2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONException;



public class LoginActivity extends AppCompatActivity  {
    int i = 0;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public ImageView img_profile = null;
    private ActionBarDrawerToggle mDrawerToggle;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    Bitmap bitm;
    Bitmap new_bitm;

    //e la vamos nos 7
    //segundo teste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().hide();
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (Build.VERSION.SDK_INT <= 17) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        //getWindow().setStatusBarColor(R.color.brown_900);

        setContentView(R.layout.activity_login);

        img_profile = (ImageView) findViewById(R.id.img_profile);
        bitm = BitmapFactory.decodeResource(getResources(), R.drawable.doge);
        new_bitm = createRoundImage(bitm);
        img_profile.setImageBitmap(new_bitm);

        mNavItems.add(new NavItem("Home","Pagina Inicial", R.drawable.ic_home_black_18dp));
        mNavItems.add(new NavItem("Profile Page","Pagina Pessoal", R.drawable.ic_account_circle_black_18dp));
        mNavItems.add(new NavItem("Mapas","Procure Livros Pelo Mapa", R.drawable.ic_explore_black_18dp));
        mNavItems.add(new NavItem("Conversas","Paginas de Chat", R.drawable.ic_chat_black_18dp));
        mNavItems.add(new NavItem("Configurações","Configurações do App", R.drawable.ic_settings_black_18dp));


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DrawerListAdapter adapter = new DrawerListAdapter (this, mNavItems);
        mDrawerList.setAdapter(adapter);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle (this, mDrawerLayout, toolbar, R.string.app_name, R.string.hello_world) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle("Cade meu Livro?");
                //vamos ver
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle("Não sei");
            }
        };

        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_48dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Button bt = (Button) findViewById(R.id.bt_sera2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colors(v);
            }
        });


        //mDrawerList = (ListView) findViewById(R.id.left_drawer);

        //mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item_list, mPlanetTitles));

        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        // create our manager instance after the content view is set
        //SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        //tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        //tintManager.setNavigationBarTintEnabled(true);
        // set a custom navigation bar resource
        //tintManager.setTintColor(R.color.brown_900);
        //tintManager.setStatusBarTintResource(R.color.statusbar_bg);



        //Window window = LoginActivity.getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.setStatusBarColor(ContextCompat.getColor(LoginActivity , R.color.brown_900));
        //toolbar.setBackgroundColor(000000);



        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        //String response = makeRequest("http://jsonplaceholder.typicode.com/");

        //try{
            //JSONObject json = new JSONObject(response);
            //String name = json.getString("title");
            //String phone = json.getString("phone");
            //String address = json.getJSONObject("location").getString("street");
            //int likes = json.getInt("likes");
            //String city = json.getJSONObject("location").getString("city");

            //nameText.setText(name);
            //phoneText.setText(getString(R.string.phone_label, phone));
            //addressText.setText(getString(R.string.address_label, address));
            //cityText.setText(getString(R.string.city_label, city));
            //likesText.setText(getString(R.string.likes_label, likes));

        //}
        //catch (JSONException e){
            //e.printStackTrace();
        //}


    }

    private Bitmap createRoundImage(Bitmap loadedImage) {
        Bitmap circleBitmap = Bitmap.createBitmap(loadedImage.getWidth(), loadedImage.getHeight(), Bitmap.Config.ARGB_8888);

        BitmapShader shader = new BitmapShader(loadedImage, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(loadedImage.getWidth() / 2, loadedImage.getHeight() / 2, loadedImage.getWidth() / 2, paint);

        return circleBitmap;
    }

    public void colors(View v) {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button bt = (Button) findViewById(R.id.bt_sera);
        Button bt2 = (Button) findViewById(R.id.bt_sera3);
        Palette palette;
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        palette = Palette.from(myBitmap).generate();

        int i_default = 0x000000;
        int vibrant = palette.getVibrantColor(i_default);
        int vibrantlight = palette.getLightVibrantColor(i_default);
        int vibrantdark = palette.getDarkVibrantColor(i_default);
        int muted = palette.getMutedColor(i_default);
        int mutedlight = palette.getLightMutedColor(i_default);
        int muteddark = palette.getDarkMutedColor(i_default);
        int cor[] = new int[22];

        int statuscolor;
        cor[0] = vibrant;
        cor[1] = getResources().getColor(R.color.white);
        cor[2] = getResources().getColor(R.color.red);
        cor[3] = getResources().getColor(R.color.pink);
        cor[4] = getResources().getColor(R.color.purple);
        cor[5] = getResources().getColor(R.color.deep_purple);
        cor[6] = getResources().getColor(R.color.indigo);
        cor[7] = getResources().getColor(R.color.blue);
        cor[8] = getResources().getColor(R.color.light_blue);
        cor[9] = getResources().getColor(R.color.cyan);
        cor[10] = getResources().getColor(R.color.teal);
        cor[11] = getResources().getColor(R.color.green);
        cor[12] = getResources().getColor(R.color.dark_green);
        cor[13] = getResources().getColor(R.color.light_green);
        cor[14] = getResources().getColor(R.color.lime);
        cor[15] = getResources().getColor(R.color.yellow);
        cor[16] = getResources().getColor(R.color.amber);
        cor[17] = getResources().getColor(R.color.orange);
        cor[18] = getResources().getColor(R.color.deep_orange);
        cor[19] = getResources().getColor(R.color.brown_900);
        cor[20] = getResources().getColor(R.color.grey);


        /*if (isColorDark(cor[i]) == true) {
            statuscolor = darker(cor[i], 2f);
            toolbar.setBackgroundColor(statuscolor);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(cor[i]);
            }else {
                //tintManager.setTintColor(Color.TRANSPARENT);
            }
        }else {
            statuscolor = darker(cor[i], 2f);
            toolbar.setBackgroundColor(cor[i]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(statuscolor);
            }else {
                //tintManager.setTintColor(Color.TRANSPARENT);
            }
        }*/

        statuscolor = dark(cor[i]);

        setStatusAndActionBarColor(cor[i]);

        bt.setBackgroundColor(cor[i]);
        bt2.setBackgroundColor(statuscolor);

        i+=1;
    }

    public void setStatusAndActionBarColor (int color) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int light_color;
        int dark_color;
        float h[] = new float[3];

        Color.colorToHSV(color, h);

        if (h[2] >= 0.8f) {
            light_color = color;
            h[2] = h[2] - 0.2f;
            dark_color = Color.HSVToColor(h);
        } else {
            dark_color = color;
            h[2] = h[2] + 0.2f;
            light_color = Color.HSVToColor(h);
        }

        toolbar.setBackgroundColor(light_color);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(dark_color);
            View status = (View) findViewById(R.id.status_bar);
            status.setBackgroundColor(dark_color);
        }else {
            getWindow().getDecorView().setBackgroundColor(light_color);
            View status = (View) findViewById(R.id.status_bar);
            status.setBackgroundColor(light_color);
        }

    }

    public int dark (int color) {
        int new_color;
        float h[] = new float[3];

        Color.colorToHSV(color, h);

        if (h[2] >= 0.8f) {
            h[2] = h[2] - 0.2f;
        } else {
            h[2] = h[2] + 0.2f;
        }

        new_color = Color.HSVToColor(h);

        return new_color;
    }

    public boolean isColorDark (int color) {
        double darkness = 1-(0.299*Color.red(color)+0.587*Color.green(color)+ 0.114*Color.blue(color))/255;
        if (darkness<0.5) {
            return false;
        } else{
            return true;
        }
    }

    public static int darker (int color, float factor) {
        int a = Color.alpha( color );
        int r = Color.red( color );
        int g = Color.green( color );
        int b = Color.blue( color );

        return Color.argb( a,
                Math.max( (int)(r * factor), 0 ),
                Math.max( (int)(g * factor), 0 ),
                Math.max( (int)(b * factor), 0 ) );
    }

    private String makeRequest(String urlAddress) {
        HttpURLConnection con = null;
        URL url = null;
        String response = null;

        try{
            url = new URL(urlAddress);
            con = (HttpURLConnection) url.openConnection();
            response = readStream(con.getInputStream());

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return response;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try{
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

}
