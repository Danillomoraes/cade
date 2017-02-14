package moraes.danillo.teste2;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danillom on 13/02/2017.
 */

public class CorSwatch extends BaseAdapter {

    Context mContext;
    List<Integer> cores;

    public CorSwatch(Context cont, List<Integer> cor){
        this.mContext = cont;
        this.cores = cor;
    }

    @Override
    public int getCount() {
        return cores.size();
    }

    @Override
    public Object getItem(int position) {
        return cores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        try {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.adapter_item_list, null);
            } else {
                view = convertView;
            }

            TextView text1 = (TextView) view.findViewById(R.id.rowText);

            view.setBackgroundColor(cores.get(position));
            //view.getBackground().setColorFilter(cores.get(position), PorterDuff.Mode.MULTIPLY);
            text1.setText(String.valueOf(cores.get(position)));

        }catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String error = sw.toString();
            try {
                writeFile(error, "errorcorswatch1");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return view;
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
