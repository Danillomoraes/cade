package moraes.danillo.teste2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

//import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Acivity_main extends AppCompatActivity  {
    int i = 0;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public ImageView img_profile;
    public ImageView img_capa;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigate;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    Bitmap bitm;
    Bitmap bit_capa;
    Bitmap bit;
    Bitmap new_bitm;

    //e la vamos nos 7
    //segundo teste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            /*Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); */
        }



        setContentView(R.layout.activity_main);

        navigate = (NavigationView) findViewById(R.id.nav_view);

        View h = navigate.getHeaderView(0);

        img_profile = (ImageView) h.findViewById(R.id.img_foto);
        img_capa = (ImageView) h.findViewById(R.id.img_back);
        View s = (View) h.findViewById(R.id.status_view);

        s.setBackgroundColor(getResources().getColor(R.color.aplha_black));
        bitm = BitmapFactory.decodeResource(getResources(), R.drawable.image_doge);
        bit_capa =  BitmapFactory.decodeResource(getResources(), R.drawable.image_hobbit);


        float x = 320f/(float) (bit_capa.getWidth()); //calculo para scale

        bit = Bitmap.createScaledBitmap(bit_capa, (int)(bit_capa.getWidth()*x), (int) (bit_capa.getHeight()*x), true);

        new_bitm = createRoundImage(bitm);
        img_profile.setImageBitmap(new_bitm);
        img_capa.setImageBitmap(bit);

        navigate.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){

                    // For rest of the options we just show a toast on click

                    case R.id.item_home:
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_chat:
                        Toast.makeText(getApplicationContext(),"Conversas",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_Mapas:
                        Toast.makeText(getApplicationContext(),"Mapas", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_profile:
                        Toast.makeText(getApplicationContext(),"Profile Page",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_settings:
                        Toast.makeText(getApplicationContext(),"Configurações",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle (this, mDrawerLayout, toolbar, R.string.app_name, R.string.hello_world) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle("Cade meu Livro?");
                //vamos ver
            }

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

        /*
        mNavItems.add(new NavItem("Home","Pagina Inicial", R.drawable.ic_home_black_18dp));
        mNavItems.add(new NavItem("Profile Page","Pagina Pessoal", R.drawable.ic_account_circle_black_18dp));
        mNavItems.add(new NavItem("Mapas","Procure Livros Pelo Mapa", R.drawable.ic_explore_black_18dp));
        mNavItems.add(new NavItem("Conversas","Paginas de Chat", R.drawable.ic_chat_black_18dp));
        mNavItems.add(new NavItem("Configurações","Configurações do App", R.drawable.ic_settings_black_18dp));


        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DrawerListAdapter adapter = new DrawerListAdapter (this, mNavItems);
        mDrawerList.setAdapter(adapter);



        */


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

    private void onStart (View v) {
        super.onStart();

        //View h = navigate.findViewById(R.id.nav_view);

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
        //Button bt = (Button) findViewById(R.id.bt_sera);
        AppCompatButton bt = (AppCompatButton) findViewById(R.id.bt_sera);
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

        //bt.setBackgroundColor(cor[i]);
        bt2.setBackgroundColor(statuscolor);
        bt.setSupportBackgroundTintList(getResources().getColorStateList(R.color.blue_grey));

        i+=1;
    }

    public void setStatusAndActionBarColor (int color) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //View status = (View) findViewById(R.id.status);
        int light_color;
        int dark_color;
        float h[] = new float[3];

        Color.colorToHSV(color, h);

        if (h[2] >= 0.7f) {
            light_color = color;
            h[2] = h[2] - 0.1f;
            dark_color = Color.HSVToColor(h);
        } else {
            dark_color = color;
            h[2] = h[2] + 0.1f;
            light_color = Color.HSVToColor(h);
        }

        toolbar.setBackgroundColor(light_color);
        //status.setBackgroundColor(dark_color);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(dark_color);
            }else {
            getWindow().getDecorView().setBackgroundColor(light_color);
            }
    }

    public int dark (int color) {
        int new_color;
        float h[] = new float[3];

        Color.colorToHSV(color, h);

        if (h[2] >= 0.8f) {
            h[2] = h[2] - 0.1f;
        } else {
            h[2] = h[2] + 0.1f;
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
