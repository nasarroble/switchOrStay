package com.example.nasar.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
      private Toast toast;
      private Context context;
      private CharSequence text;
      private final int duration = Toast.LENGTH_SHORT;
      private ImageButton door1, door2,door3;
      private Button play,stay,switched;
      private int userPick;


      private MontyHallGame game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        text = "Pick a door";
        toast = Toast.makeText(context, text, duration);

        game = new MontyHallGame();
        door1 = (ImageButton)findViewById(R.id.door1);
        door2 = (ImageButton)findViewById(R.id.door2);
        door3 = (ImageButton)findViewById(R.id.door3);
        play = (Button)findViewById(R.id.play);
        switched = (Button)findViewById(R.id.switch_doors);
        stay = (Button)findViewById(R.id.stay);


        door1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPick = 1;
                game.setUserDoor(userPick);
                door3.setEnabled(false);
                door2.setEnabled(false);
                door1.requestFocus();
                TextView text = (TextView) findViewById(R.id.selected_door);
                text.setText("You selected door 1");

                toast.show();

                if(game.revealDummyDoor() == 2) {
                    door2.forceLayout();
                    door2.setBackgroundResource(R.drawable.goat);
                }
                else {
                    door3.forceLayout();
                    door3.setBackgroundResource(R.drawable.goat);


                }


            }

        });



        door2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPick = 2;
                game.setUserDoor(userPick);
                door1.setEnabled(false);
                door3.setEnabled(false);
                door2.requestFocus();
                TextView text = (TextView) findViewById(R.id.selected_door);
                text.setText("You selected door 2");

                toast.show();


                if(game.revealDummyDoor() == 1) {
                    door1.forceLayout();
                    door1.setBackgroundResource(R.drawable.goat);

                }
                else {
                    door3.forceLayout();
                    door3.setBackgroundResource(R.drawable.goat);


                }
            }
        });


        door3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userPick = 3;
                game.setUserDoor(userPick);
                door1.setEnabled(false);
                door2.setEnabled(false);
                door3.requestFocus();
                TextView text = (TextView) findViewById(R.id.selected_door);
                text.setText("You selected door 3");
                toast.show();


                if(game.revealDummyDoor() == 2) {
                    door2.forceLayout();
                    door2.setBackgroundResource(R.drawable.goat);

                }
                else {
                    door1.forceLayout();
                    door1.setBackgroundResource(R.drawable.goat);

                }
            }
        });


        switched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.switchDoor();
                text = "Now play";
                toast = Toast.makeText(context, text, duration);
                TextView text = (TextView) findViewById(R.id.selected_door);
                text.setText("You switched doors");
                toast.show();


            }
        });


        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = "Now play";
                toast = Toast.makeText(context, text, duration);
                TextView text = (TextView) findViewById(R.id.selected_door);
                text.setText("You chose to stay");


                toast.show();
            }
        });



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play.getText().equals("Play Again")) {
                    replay();
                    return;
                }
                if(game.userWon())
                     text = "Nice Win, Play Again";
                else {
                    text = "Nice try, Play Again";
                    TextView text = (TextView) findViewById(R.id.selected_door);
                    text.setText("You should've picked door number: " + game.getPrizeDoor());
                      }
                toast = Toast.makeText(context, text, duration);


                if(game.getPrizeDoor() == 2) {
                    door2.forceLayout();
                    door2.setBackgroundResource(R.drawable.car);
                }
                else if(game.getPrizeDoor() == 3) {
                    door3.forceLayout();
                    door3.setBackgroundResource(R.drawable.car);
                }
                else {
                    door1.forceLayout();
                    door1.setBackgroundResource(R.drawable.car);
                }
                toast.show();
                play.setText("Play Again");
                //replay();
            }
        });


    }
   public void replay(){
       door1.setEnabled(true);
       door2.setEnabled(true);
       door3.setEnabled(true);

       door1.setBackgroundResource(R.drawable.cup);
       door2.setBackgroundResource(R.drawable.cup);
       door3.setBackgroundResource(R.drawable.cup);
       play.setText("Play");
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
