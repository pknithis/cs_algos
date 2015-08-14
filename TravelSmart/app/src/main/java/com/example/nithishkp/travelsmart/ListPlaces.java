package com.example.nithishkp.travelsmart;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Logger;


public class ListPlaces extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_places, menu);
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

    public void launchActivity(View v)
    {
        TextView tx1 =  (TextView) findViewById(R.id.editText);
        TextView tx2 =  (TextView) findViewById(R.id.editText2);
        TextView tx3 =  (TextView) findViewById(R.id.editText3);
        Intent it = new Intent(getBaseContext(),AddPlcaces.class);
        String[] str = {tx1.getText()+"", tx2.getText()+"", tx3.getText()+""};
        it.putExtra("LOCATIONS",str);

        Log.v("Nithish", "Launching activity");
        //System.out.print("Nithish Launching activity");
        startActivity(it);
        this.setVisible(false);
    }
}
