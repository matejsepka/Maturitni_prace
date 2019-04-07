package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.Animation;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.GameSituation;

public class AnimationCreatorActivity extends AppCompatActivity {
    private GameSituation gameSituation;
    private int _xDelta;
    private int _yDelta;

    private int frameCount = 0;

    private float playerOneX1;
    private float playerOneY1;
    private float playerOneX2;
    private float playerOneY2;
    private float playerOneX3;
    private float playerOneY3;

    private float playerTwoX1;
    private float playerTwoY1;
    private float playerTwoX2;
    private float playerTwoY2;
    private float playerTwoX3;
    private float playerTwoY3;

    private float ballOneX1;
    private float ballOneY1;
    private float ballOneX2;
    private float ballOneY2;
    private float ballOneX3;
    private float ballOneY3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_creator);

        gameSituation = (GameSituation) getIntent().getExtras().getSerializable("game_situation");
        setTitle("Animace ke cvičení" + gameSituation.getName());
        final ImageView imagePlayerOne = findViewById(R.id.player_one_img);
        final ImageView imagePlayerTwo = findViewById(R.id.player_two_img);
        final ImageView imageBallOne = findViewById(R.id.ball_one_img);
        Button nextFrameButton = findViewById(R.id.next_frame_btn);

        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(70, 70);
        imagePlayerOne.setLayoutParams(layoutParams1);
        imagePlayerOne.setX(50);
        imagePlayerOne.setY(1040);
        imagePlayerOne.setOnTouchListener(new PlayerOneTouchListener());

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(70, 70);
        imagePlayerTwo.setLayoutParams(layoutParams2);
        imagePlayerTwo.setX(200);
        imagePlayerTwo.setY(1040);
        imagePlayerTwo.setOnTouchListener(new PlayerTwoTouchListener());

        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(50 , 50);
        imageBallOne.setLayoutParams(layoutParams3);
        imageBallOne.setX(350);
        imageBallOne.setY(1050);
        imageBallOne.setOnTouchListener(new BallOneTouchListener());

        nextFrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (frameCount) {
                    case 4:
                        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
                        Animation animation = new Animation();
                        animation.setName(gameSituation.getName());
                        animation.setPlayerOneX1(playerOneX1);
                        animation.setPlayerOneY1(playerOneY1);
                        animation.setPlayerOneX2(playerOneX2);
                        animation.setPlayerOneY2(playerOneY2);
                        animation.setPlayerOneX3(playerOneX3);
                        animation.setPlayerOneY3(playerOneY3);
                        animation.setPlayerTwoX1(playerTwoX1);
                        animation.setPlayerTwoY1(playerTwoY1);
                        animation.setPlayerTwoX2(playerTwoX2);
                        animation.setPlayerTwoY2(playerTwoY2);
                        animation.setPlayerTwoX3(playerTwoX3);
                        animation.setPlayerTwoY3(playerTwoY3);
                        animation.setBallOneX1(ballOneX1);
                        animation.setBallOneY1(ballOneY1);
                        animation.setBallOneX2(ballOneX2);
                        animation.setBallOneY2(ballOneY2);
                        animation.setBallOneX3(ballOneX3);
                        animation.setBallOneY3(ballOneY3);
                        appDatabase.getAnimationDao().insert(animation);
                        finish();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),"Již máte maximální počet obrázků v animaci! Klikněte ještě jednou pro uložení.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 2:
                        playerOneX3 = imagePlayerOne.getX();
                        playerOneY3 = imagePlayerOne.getY();
                        playerTwoX3 = imagePlayerTwo.getX();
                        playerTwoY3 = imagePlayerTwo.getY();
                        ballOneX3 = imageBallOne.getX();
                        ballOneY3 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Třetí obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 1:
                        playerOneX2 = imagePlayerOne.getX();
                        playerOneY2 = imagePlayerOne.getY();
                        playerTwoX2 = imagePlayerTwo.getX();
                        playerTwoY2 = imagePlayerTwo.getY();
                        ballOneX2 = imageBallOne.getX();
                        ballOneY2 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Druhý obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 0:
                        playerOneX1 = imagePlayerOne.getX();
                        playerOneY1 = imagePlayerOne.getY();
                        playerTwoX1 = imagePlayerTwo.getX();
                        playerTwoY1 = imagePlayerTwo.getY();
                        ballOneX1 = imageBallOne.getX();
                        ballOneY1 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"První obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                }
            }
        });

    }


    private final class PlayerOneTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams1 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams1.leftMargin;
                    _yDelta = Y - lParams1.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams1.leftMargin = X - _xDelta;
                    layoutParams1.topMargin = Y - _yDelta;
                    layoutParams1.rightMargin = -250;
                    layoutParams1.bottomMargin = -250;
                    view.setLayoutParams(layoutParams1);
                    break;
            }
            return true;
        }
    }

    private final class PlayerTwoTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams2.leftMargin;
                    _yDelta = Y - lParams2.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams2.leftMargin = X - _xDelta;
                    layoutParams2.topMargin = Y - _yDelta;
                    layoutParams2.rightMargin = -250;
                    layoutParams2.bottomMargin = -250;
                    view.setLayoutParams(layoutParams2);
                    break;
            }
            return true;
        }
    }

    private final class BallOneTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams3 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams3.leftMargin;
                    _yDelta = Y - lParams3.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams3.leftMargin = X - _xDelta;
                    layoutParams3.topMargin = Y - _yDelta;
                    layoutParams3.rightMargin = -250;
                    layoutParams3.bottomMargin = -250;
                    view.setLayoutParams(layoutParams3);
                    break;
            }
            return true;
        }
    }
}
