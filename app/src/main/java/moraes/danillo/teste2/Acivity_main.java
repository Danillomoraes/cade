package moraes.danillo.teste2;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Acivity_main extends AppCompatActivity  {
    int i = 0;
    int o = 0;
    int p = 0;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public ImageView img_profile;
    public ImageView img_capa;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigate;
    public int[] back;
    int color[][];

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
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            /*Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); */
        }



        setContentView(R.layout.activity_main);

        try {

            navigate = (NavigationView) findViewById(R.id.nav_view);

            View h = navigate.getHeaderView(0);

            img_profile = (ImageView) h.findViewById(R.id.img_foto);
            img_capa = (ImageView) h.findViewById(R.id.img_back);
            //View s = (View) h.findViewById(R.id.status_view);

            //s.setBackgroundColor(getResources().getColor(R.color.aplha_black));
            bitm = BitmapFactory.decodeResource(getResources(), R.drawable.image_doge);
            bit_capa = BitmapFactory.decodeResource(getResources(), R.drawable.image_hobbit);


            float x = 320f / (float) (bit_capa.getWidth()); //calculo para scale

            bit = Bitmap.createScaledBitmap(bit_capa, (int) (bit_capa.getWidth() * x), (int) (bit_capa.getHeight() * x), true);

            new_bitm = createRoundImage(bitm);
            img_profile.setImageBitmap(new_bitm);
            img_capa.setImageBitmap(bit);

            navigate.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                // This method will trigger on item Click of navigation menu
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {


                    //Checking if the item is in checked state or not, if not make it in checked state
                    if (menuItem.isChecked()) menuItem.setChecked(false);
                    else menuItem.setChecked(true);

                    //Closing drawer on item click
                    mDrawerLayout.closeDrawers();

                    //Check to see which item was being clicked and perform appropriate action
                    switch (menuItem.getItemId()) {

                        // For rest of the options we just show a toast on click

                        case R.id.item_home:
                            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.item_chat:
                            Toast.makeText(getApplicationContext(), "Conversas", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.item_Mapas:
                            Toast.makeText(getApplicationContext(), "Mapas", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.item_profile:
                            Toast.makeText(getApplicationContext(), "Profile Page", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.item_settings:
                            Toast.makeText(getApplicationContext(), "Configurações", Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                            return true;

                    }
                }
            });

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.hello_world) {
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


            Button bt = (Button) findViewById(R.id.bt_vibrant);
            Button bt_muted = (Button) findViewById(R.id.bt_muted);

            bt_muted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        colorsSwatch(v);
                    } catch (IOException e) {
                        e.printStackTrace();
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        String error = sw.toString();
                        try {
                            writeFile(error, "errorcolorswatch");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), getIntent().getStringExtra("cod_user"), Toast.LENGTH_LONG).show();
                    try {
                        colors(v);
                    } catch (IOException e) {
                        e.printStackTrace();
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        String error = sw.toString();
                        try {
                            writeFile(error, "errorcolors");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });

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

            ImageView img_categoria1 = (ImageView) findViewById(R.id.img_categoria1);
            ImageView img_categoria2 = (ImageView) findViewById(R.id.img_categoria2);
            ImageView img_categoria3 = (ImageView) findViewById(R.id.img_categoria3);

            //try {

            img_categoria1.setImageBitmap(getBlur(getSizedBitmap(back[11], dpToPx(225), (int) (getApplicationContext().getResources().getDisplayMetrics().widthPixels)), 15));
            img_categoria2.setImageBitmap(getBlur(getSizedBitmap(back[7], dpToPx(225), (int) (getApplicationContext().getResources().getDisplayMetrics().widthPixels)), 15));
            img_categoria3.setImageBitmap(getBlur(getSizedBitmap(back[9], dpToPx(225), (int) (getApplicationContext().getResources().getDisplayMetrics().widthPixels)), 15));

            //} catch (Exception e) {
            //getlog();

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

        }catch (Exception e){
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String error = sw.toString();
            try {
                writeFile(error, "error");
            }catch (Exception x){
                x.printStackTrace();
            }
        }
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

    public void colorsSwatch(View v) throws IOException {

        try {
            Bitmap myBitmap;
            myBitmap = BitmapFactory.decodeResource(getResources(), back[i]);
            ColorP colorP = new ColorP();
            List<Palette.Swatch> list = colorP.getSwatch(myBitmap);
            List<String> cores = new ArrayList<String>(); // new ArrayList();
            String [] value = new String[] {"shiuahsdiuahsdiuhasd", "iaushdiaushdiaushduiashd", "ioauscaksbclajshbcajlsbhc"};
            ListView lista = (ListView) findViewById(R.id.lt_swatch);

            for (int o = 0; o >= list.size() - 1; o++) {

                cores.add("iasuhdaiusdh"+o);  //String.valueOf(list.get(o).getRgb()));

            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, value);
            lista.setAdapter(adapter);

            for (int o = 0; o >= lista.getChildCount() - 1; o++) {
                //getViewByPosition(o,lista).getBackground().setColorFilter(cores.get(o), PorterDuff.Mode.MULTIPLY);
            }
        }catch (Exception e) {

            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String error = sw.toString();
            writeFile(error, "errorcolorswatch");

        }
/*
        try {
            //Button bt = (Button) findViewById(R.id.bt_sera);
            Button bt_muted = (Button) findViewById(R.id.bt_muted);
            AppCompatButton bt_vibrant = (AppCompatButton) findViewById(R.id.bt_vibrant);
            Button bt_vibrant_light = (Button) findViewById(R.id.bt_vibrant_light);
            Button bt_vibrant_dar = (Button) findViewById(R.id.bt_vibrant_dark);
            Button bt_muted_light = (Button) findViewById(R.id.bt_muted_light);
            Button bt_muted_dark = (Button) findViewById(R.id.bt_muted_dark);

            try {

                Bitmap myBitmap;
                myBitmap = BitmapFactory.decodeResource(getResources(), back[i]);
                ColorP colorP = new ColorP();
                List<Palette.Swatch> list = colorP.getSwatch(myBitmap);
                List<Button> listb = new ArrayList<>();

                listb.add(bt_muted);
                listb.add(bt_vibrant_light);
                listb.add(bt_vibrant_dar);
                listb.add(bt_vibrant);
                listb.add(bt_muted_light);
                listb.add(bt_muted_dark);


            if(p>=list.size()-1){
                p=0;
            }

            for (int o = 0; o>=5;o++) {
                listb.get(o).getBackground().setColorFilter(list.get(p).getRgb(), PorterDuff.Mode.MULTIPLY);
                p++;
            }
            }catch (Exception e){
                e.printStackTrace();
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String error = sw.toString();
                writeFile(error, "errorcolorswatch");
            }

        }catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String error = sw.toString();
            writeFile(error, "errorlog");
        } */

    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void colors(View v) throws IOException {
        //Button bt = (Button) findViewById(R.id.bt_sera);
        CardView card = (CardView) findViewById(R.id.cardview);
        ImageView img_view = (ImageView) findViewById(R.id.img);
        AppCompatButton bt_vibrant = (AppCompatButton) findViewById(R.id.bt_vibrant);
        Button bt_vibrant_light = (Button) findViewById(R.id.bt_vibrant_light);
        Button bt_vibrant_dar = (Button) findViewById(R.id.bt_vibrant_dark);
        Button bt_muted_light = (Button) findViewById(R.id.bt_muted_light);
        Button bt_muted_dark = (Button) findViewById(R.id.bt_muted_dark);
        Button bt_muted = (Button) findViewById(R.id.bt_muted);


        if (Build.VERSION.SDK_INT >= 21) {
            i = ThreadLocalRandom.current().nextInt(0, 12 + 1);
            o =ThreadLocalRandom.current().nextInt(0, 5 + 1);
        }
        else {
            if (i>12){
                i=0;
            }
            i +=1;
            if (o>5){
                o=0;
            }
            o +=1;
        }

        change_background_view(back[i], img_view, card);

        Bitmap myBitmap;
        myBitmap = BitmapFactory.decodeResource(getResources(), back[i]);
        ColorP colorP = new ColorP();
        int[] cor = new int[6];

        try {
            cor = colorP.getBitmapPalette(myBitmap);
        }catch (Exception e){
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String error = sw.toString();
            writeFile(error, "errorlog");
        }
        setStatusAndActionBarColor(cor[3]);

        bt_vibrant.getBackground().setColorFilter(cor[0], PorterDuff.Mode.MULTIPLY);
        bt_vibrant_dar.getBackground().setColorFilter(cor[2], PorterDuff.Mode.MULTIPLY);
        bt_vibrant_light.getBackground().setColorFilter(cor[1], PorterDuff.Mode.MULTIPLY);
        bt_muted.getBackground().setColorFilter(cor[3], PorterDuff.Mode.MULTIPLY);
        bt_muted_dark.getBackground().setColorFilter(cor[5], PorterDuff.Mode.MULTIPLY);
        bt_muted_light.getBackground().setColorFilter(cor[4], PorterDuff.Mode.MULTIPLY);

        i+=1;
    }

    private int dpToPx(int dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }

    private int pxtoDp (int px) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) Math.round(px/density);
    }

    public void change_background_view (int r, ImageView img_background, CardView card) {
        Bitmap bit_back;

        bit_back = BitmapFactory.decodeResource(getResources(), r);

        float x = card.getWidth()/(float) (bit_back.getWidth()) ; //getting scale x to downscale
        float y = (float) (dpToPx(300)) /(float) (bit_back.getHeight()); //gettin scale y to downscale
        float z;

        if (bit_back.getHeight() > bit_back.getWidth()) {

            z = y;

        } else {

            z = x;
        }

        bit_back = Bitmap.createScaledBitmap(bit_back, (int)(bit_back.getWidth()*z), (int)(bit_back.getHeight()*z), true );

        int cardheight = card.getHeight() - img_background.getHeight();

        expandCard(card,bit_back.getHeight()+cardheight);
        expandCard(img_background, bit_back.getHeight());

        img_background.setImageBitmap(bit_back);
        }

    public Bitmap getSizedBitmap (int r, int height, int width) {
        Bitmap new_bitmap;

        new_bitmap = BitmapFactory.decodeResource(getResources(), r);

        float x = (float) (width) / (float) (new_bitmap.getWidth());
        float y = (float) (height) / (float) (new_bitmap.getHeight());
        float z;

        if (new_bitmap.getHeight() > new_bitmap.getWidth()) {

            z = y;

        } else {

            z = x;
        }

        new_bitmap = Bitmap.createScaledBitmap(new_bitmap,(int)(new_bitmap.getWidth()*z),(int) (new_bitmap.getHeight()*z), true);

        return  new_bitmap;
    }

    public void expandCard (final View v, int newHeight) {

        // set the values we want to animate between and how long it takes
        // to run
        ValueAnimator slideAnimator = ValueAnimator
                .ofInt(v.getHeight(), newHeight)
                .setDuration(300);

        // we want to manually handle how each tick is handled so add a
        // listener
        slideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // get the value the interpolator is at
                Integer value = (Integer) animation.getAnimatedValue();
                // I'm going to set the layout's height 1:1 to the tick
                v.getLayoutParams().height = value.intValue();
                // force all layouts to see which ones are affected by
                // this layouts height change
                v.requestLayout();
            }
        });

        // create a new animationset
        AnimatorSet set = new AnimatorSet();
        // since this is the only animation we are going to run we just use
        // play
        set.play(slideAnimator);
        // this is how you set the parabola which controls acceleration
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        // start the animation
        set.start();
    }

    public void setStatusAndActionBarColor (int color) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        View status = (View) findViewById(R.id.status);
        ColorP colorp = new ColorP(color);

        colorp.getDarkColor();

        toolbar.setBackgroundColor(colorp.colors[0]);
        status.setBackgroundColor(colorp.colors[1]);

        if (Build.VERSION.SDK_INT >= 21) {
            //getWindow().setStatusBarColor(Color.TRANSPARENT);
            }else {
            //getWindow().getDecorView().setBackgroundColor(light_color);
            }
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

    public void getlog () {

        try {
            Process process = Runtime.getRuntime().exec("logcat -t 500 -f sdcard/log.txt");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log=new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }

            Toast toast = Toast.makeText(getApplicationContext(), log.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
        catch (IOException e) {

            Toast toast = Toast.makeText(getApplicationContext(), "agora fudeu", Toast.LENGTH_LONG);
            toast.show();

        }

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
