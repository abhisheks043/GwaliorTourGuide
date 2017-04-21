package com.example.abhisingh.gwalior;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Abhi Singh on 6/2/2016.
 */
public class Particulars extends AppCompatActivity
{

    String name;
    TextView tv;
    TextView tv1;
    TextView tv2;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_lay);

        Bundle b = getIntent().getExtras();
        name = b.getString("name");

        tv = (TextView)findViewById(R.id.head);
        tv1 = (TextView)findViewById(R.id.body);
        tv2 = (TextView)findViewById(R.id.timing);
        tv3 = (TextView)findViewById(R.id.fee);

        pasteContent();

    }

    void pasteContent()
    {
        int i=0;
        tv.setText(name);
        String[] mon= getResources().getStringArray(R.array.mons);
        String[] mus= getResources().getStringArray(R.array.mus);
        String[] hot= getResources().getStringArray(R.array.hot);
        String[] eat= getResources().getStringArray(R.array.eat);
        String[] pub= getResources().getStringArray(R.array.pub);

        String[] body = {};
        String[] time = {};
        String[] fee = {};

        for(i=0;i<7;i++)
        {
            if(mon[i]==name)
            {
                body = getResources().getStringArray(R.array.mons_body);
                time = getResources().getStringArray(R.array.mons_tims);
                fee = getResources().getStringArray(R.array.mons_fee);
                break;
            }

         /*   if(mus[i]==name)
            {

                break;
            }

            if(hot[i]==name)
            {

                break;
            }

            if(eat[i]==name)
            {

                break;
            }

            if(pub[i]==name)
            {

                break;
            }
*/
        }

        tv1.setText(body[i]);
        tv2.setText(time[i]);
        tv3.setText(fee[i]);


    }


}
