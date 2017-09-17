package com.aditya.tictactoe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 for circle and 1 for cross
    int turn=0,played[]={2,2,2,2,2,2,2,2,2};
    boolean active=true,gameover=true;
    public void clicked(View view)
    {
        ImageView img = (ImageView) view;
        int tag=Integer.parseInt(img.getTag().toString());
        int winpos[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        TextView a= (TextView) findViewById(R.id.winner);
        LinearLayout layout= (LinearLayout) findViewById(R.id.played);
        if(played[tag]==2&&active)
        {
            played[tag] = turn;
            img.setTranslationY(-1000f);
            if (turn == 0)
            {
                img.setImageResource(R.drawable.circle);
                turn = 1;
            }
            else
            {
                img.setImageResource(R.drawable.cross);
                turn = 0;
            }
            img.animate().translationYBy(1000f).setDuration(500);
            for(int win[] : winpos)
            {
                if (played[win[0]] == played[win[1]]
                        && played[win[1]] == played[win[2]]
                        && played[win[0]] != 2)
                {
                    active = false;

                    if (played[win[0]] == 0)
                        a.setText("Winner is Circle");
                    else
                        a.setText("Winner is Cross");
                    layout.setVisibility(view.VISIBLE);
                    break;
                }
                else
                {
                    gameover = true;
                    for(int done=0; done<played.length; done++)
                    {
                        if(played[done]==2)
                            gameover=false;
                    }
                    if(gameover)
                    {
                        a.setText("Draw");
                        layout.setVisibility(view.VISIBLE);
                    }
                }
            }
            }
        }

    public void playAgain(View view)
    {
        turn=0;
        for(int i=0;i<9;i++)
        {
            played[i]=2;
        }
        active=true;
        LinearLayout layout= (LinearLayout) findViewById(R.id.played);
        layout.setVisibility(view.INVISIBLE);
        GridLayout a= (GridLayout) findViewById(R.id.grid);
        for(int i=0;i< a.getChildCount();i++)
        {
            ((ImageView) a.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}
