package com.delta.layouts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LayoutActivity extends Activity {

    private Button b1;
    private TextView t1;
    private static final int Default_Code=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rlayout);

        b1= (Button) findViewById(R.id.bb1);
        t1=(TextView) findViewById(R.id.tb1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(view.getContext(),SelctionActiviy.class);
                i.putExtra("Switch","Activity Switched");
                startActivityForResult(i,Default_Code);
            }
        });

        //Intent receiver
        Intent httpi=getIntent();
        String action = httpi.getAction();
        if(action!=null && action.equals(Intent.ACTION_VIEW)){
            Uri data = httpi.getData();
            if(data!=null){
                t1.setText(data.toString());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Default_Code && resultCode==RESULT_OK){
            String str3 = data.getExtras().getString("Done");
            t1.setText(str3);
        }
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
