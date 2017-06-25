package com.delta.layouts;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SelctionActiviy extends Activity {
    private Button but1=null,b2=null;
    private Spinner spin1=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Bundle extra = this.getIntent().getExtras();
        if(extra != null){
            String str =extra.getString("Switch");
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        }
        but1=(Button)findViewById(R.id.bb2);
        b2=(Button) findViewById(R.id.bb3);
        spin1=(Spinner)findViewById(R.id.sb1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str2 = spin1.getSelectedItem().toString();
                Intent ret = new Intent();
                ret.putExtra("Done",str2);
                setResult(RESULT_OK,ret);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = spin1.getSelectedItemPosition();
                Intent implicitIntent = null;
                switch (pos) {
                    case 1:
                        //call
                        implicitIntent =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+91)8056263913"));
                        break;
                    case 2:
                        //website
                        implicitIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://kproxy.in"));
                        break;
                    case 3:
                        //picture
                        implicitIntent =new Intent("android.media.action.IMAGE_CAPTURE");
                        break;
                    case 4:
                        //contact
                        implicitIntent=new Intent(Intent.ACTION_EDIT,Uri.parse("content://contacts/people/1"));
                        break;
                    case 5:
                        //map
                        implicitIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("geo:30.123,-100"));
                        break;
                }
                if(implicitIntent!=null){
                    if(isIntentAvail(implicitIntent)){
                        startActivity(implicitIntent);
                    }else{
                        Toast.makeText(view.getContext(),"No App Available",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }

    public boolean isIntentAvail(Intent intent){
        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent,0);
        boolean safe=(list.size()>0);
        return safe;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

