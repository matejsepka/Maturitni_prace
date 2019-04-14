package com.matej.sepka.appPackage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.matej.sepka.appPackage.R;
import com.matej.sepka.appPackage.database.Animation;
import com.matej.sepka.appPackage.database.AppDatabase;
import com.matej.sepka.appPackage.database.GameSituation;
import com.matej.sepka.appPackage.dialog.AboutDialogAnimationCreatorActivity;

public class AnimationCreatorActivity extends AppCompatActivity {

    //implementace proměnných
    private GameSituation gameSituation;
    private int _xDelta;
    private int _yDelta;

    //počet vytvořených obrázků
    private int frameCount = 0;

    //proměnné pro obrázek prvního hráče
    private float playerOneX1 = 0;
    private float playerOneY1 = 0;
    private float playerOneX2 = 0;
    private float playerOneY2 = 0;
    private float playerOneX3 = 0;
    private float playerOneY3 = 0;
    private float playerOneX4 = 0;
    private float playerOneY4 = 0;
    private float playerOneX5 = 0;
    private float playerOneY5 = 0;
    private float playerOneX6 = 0;
    private float playerOneY6 = 0;
    private float playerOneX7 = 0;
    private float playerOneY7 = 0;
    private float playerOneX8 = 0;
    private float playerOneY8 = 0;
    private float playerOneX9 = 0;
    private float playerOneY9 = 0;
    private float playerOneX10 = 0;
    private float playerOneY10 = 0;

    //proměnné pro obrázek druhého hráče
    private float playerTwoX1 = 0;
    private float playerTwoY1 = 0;
    private float playerTwoX2 = 0;
    private float playerTwoY2 = 0;
    private float playerTwoX3 = 0;
    private float playerTwoY3 = 0;
    private float playerTwoX4 = 0;
    private float playerTwoY4 = 0;
    private float playerTwoX5 = 0;
    private float playerTwoY5 = 0;
    private float playerTwoX6 = 0;
    private float playerTwoY6 = 0;
    private float playerTwoX7 = 0;
    private float playerTwoY7 = 0;
    private float playerTwoX8 = 0;
    private float playerTwoY8 = 0;
    private float playerTwoX9 = 0;
    private float playerTwoY9 = 0;
    private float playerTwoX10 = 0;
    private float playerTwoY10 = 0;

    //proměnné pro obrázek třetího hráče
    private float playerThreeX1 = 0;
    private float playerThreeY1 = 0;
    private float playerThreeX2 = 0;
    private float playerThreeY2 = 0;
    private float playerThreeX3 = 0;
    private float playerThreeY3 = 0;
    private float playerThreeX4 = 0;
    private float playerThreeY4 = 0;
    private float playerThreeX5 = 0;
    private float playerThreeY5 = 0;
    private float playerThreeX6 = 0;
    private float playerThreeY6 = 0;
    private float playerThreeX7 = 0;
    private float playerThreeY7 = 0;
    private float playerThreeX8 = 0;
    private float playerThreeY8 = 0;
    private float playerThreeX9 = 0;
    private float playerThreeY9 = 0;
    private float playerThreeX10 = 0;
    private float playerThreeY10 = 0;

    //proměnné pro obrázek čtvrtého hráče
    private float playerFourX1 = 0;
    private float playerFourY1 = 0;
    private float playerFourX2 = 0;
    private float playerFourY2 = 0;
    private float playerFourX3 = 0;
    private float playerFourY3 = 0;
    private float playerFourX4 = 0;
    private float playerFourY4 = 0;
    private float playerFourX5 = 0;
    private float playerFourY5 = 0;
    private float playerFourX6 = 0;
    private float playerFourY6 = 0;
    private float playerFourX7 = 0;
    private float playerFourY7 = 0;
    private float playerFourX8 = 0;
    private float playerFourY8 = 0;
    private float playerFourX9 = 0;
    private float playerFourY9 = 0;
    private float playerFourX10 = 0;
    private float playerFourY10 = 0;

    //proměnné pro obrázek prvního balonu
    private float ballOneX1 = 0;
    private float ballOneY1 = 0;
    private float ballOneX2 = 0;
    private float ballOneY2 = 0;
    private float ballOneX3 = 0;
    private float ballOneY3 = 0;
    private float ballOneX4 = 0;
    private float ballOneY4 = 0;
    private float ballOneX5 = 0;
    private float ballOneY5 = 0;
    private float ballOneX6 = 0;
    private float ballOneY6 = 0;
    private float ballOneX7 = 0;
    private float ballOneY7 = 0;
    private float ballOneX8 = 0;
    private float ballOneY8 = 0;
    private float ballOneX9 = 0;
    private float ballOneY9 = 0;
    private float ballOneX10 = 0;
    private float ballOneY10 = 0;


    //onCreate metoda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_creator);

        //cvičení
        gameSituation = (GameSituation) getIntent().getExtras().getSerializable("game_situation");
        //název stránky
        setTitle("Animace ke cvičení " + gameSituation.getName());
        //jednotlivé obrázky
        final ImageView imagePlayerOne = findViewById(R.id.player_one_img);
        final ImageView imagePlayerTwo = findViewById(R.id.player_two_img);
        final ImageView imageBallOne = findViewById(R.id.ball_one_img);
        final ImageView imagePlayerThree = findViewById(R.id.player_three_img);
        final ImageView imagePlayerFour = findViewById(R.id.player_four_img);
        //tlačítka pro ukládání
        Button nextFrameBtn = findViewById(R.id.next_frame_btn);
        Button saveBtn = findViewById(R.id.save_btn);

        //Nastavení parametrů k obrázku prvního hráče + odkaz na metodu pro posouvání obrázku
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(70, 70);
        imagePlayerOne.setLayoutParams(layoutParams1);
        imagePlayerOne.setX(50);
        imagePlayerOne.setY(1040);
        imagePlayerOne.setOnTouchListener(new PlayerOneTouchListener());

        //Nastavení parametrů k obrázku druhého hráče + odkaz na metodu pro posouvání obrázku
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(70, 70);
        imagePlayerTwo.setLayoutParams(layoutParams2);
        imagePlayerTwo.setX(150);
        imagePlayerTwo.setY(1040);
        imagePlayerTwo.setOnTouchListener(new PlayerTwoTouchListener());

        //Nastavení parametrů k obrázku prvního balonu + odkaz na metodu pro posouvání obrázku
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(50 , 50);
        imageBallOne.setLayoutParams(layoutParams3);
        imageBallOne.setX(450);
        imageBallOne.setY(1050);
        imageBallOne.setOnTouchListener(new BallOneTouchListener());

        //Nastavení parametrů k obrázku třetího hráče + odkaz na metodu pro posouvání obrázku
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(70, 70);
        imagePlayerThree.setLayoutParams(layoutParams4);
        imagePlayerThree.setX(250);
        imagePlayerThree.setY(1040);
        imagePlayerThree.setOnTouchListener(new PlayerThreeTouchListener());

        //Nastavení parametrů k obrázku čtvrtého hráče + odkaz na metodu pro posouvání obrázku
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(70, 70);
        imagePlayerFour.setLayoutParams(layoutParams5);
        imagePlayerFour.setX(350);
        imagePlayerFour.setY(1040);
        imagePlayerFour.setOnTouchListener(new PlayerFourTouchListener());

        //při kliknutí na tlačítko pro uložení nového obrázku
        nextFrameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //podle počtů již vytvořených obrázků odlišíme do jaké proměnné se má uložit aktuální pozice obrázku
                switch (frameCount) {
                    case 10:
                        Toast.makeText(getApplicationContext(),"Již máte maximální počet obrázků v animaci! Klikněte na tlačítko pro uložení.", Toast.LENGTH_LONG).show();
                        break;
                    case 9:
                        playerOneX10 = imagePlayerOne.getX();
                        playerOneY10 = imagePlayerOne.getY();
                        playerTwoX10 = imagePlayerTwo.getX();
                        playerTwoY10 = imagePlayerTwo.getY();
                        playerThreeX10 = imagePlayerThree.getX();
                        playerThreeY10 = imagePlayerThree.getY();
                        playerFourX10 = imagePlayerFour.getX();
                        playerFourY10 = imagePlayerFour.getY();
                        ballOneX10 = imageBallOne.getX();
                        ballOneY10 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Desátý obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 8:
                        playerOneX9 = imagePlayerOne.getX();
                        playerOneY9 = imagePlayerOne.getY();
                        playerTwoX9 = imagePlayerTwo.getX();
                        playerTwoY9 = imagePlayerTwo.getY();
                        playerThreeX9 = imagePlayerThree.getX();
                        playerThreeY9 = imagePlayerThree.getY();
                        playerFourX9 = imagePlayerFour.getX();
                        playerFourY9 = imagePlayerFour.getY();
                        ballOneX9 = imageBallOne.getX();
                        ballOneY9 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Devátý obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 7:
                        playerOneX8 = imagePlayerOne.getX();
                        playerOneY8 = imagePlayerOne.getY();
                        playerTwoX8 = imagePlayerTwo.getX();
                        playerTwoY8 = imagePlayerTwo.getY();
                        playerThreeX8 = imagePlayerThree.getX();
                        playerThreeY8 = imagePlayerThree.getY();
                        playerFourX8 = imagePlayerFour.getX();
                        playerFourY8 = imagePlayerFour.getY();
                        ballOneX8 = imageBallOne.getX();
                        ballOneY8 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Osmý obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 6:
                        playerOneX7 = imagePlayerOne.getX();
                        playerOneY7 = imagePlayerOne.getY();
                        playerTwoX7 = imagePlayerTwo.getX();
                        playerTwoY7 = imagePlayerTwo.getY();
                        playerThreeX7 = imagePlayerThree.getX();
                        playerThreeY7 = imagePlayerThree.getY();
                        playerFourX7 = imagePlayerFour.getX();
                        playerFourY7 = imagePlayerFour.getY();
                        ballOneX7 = imageBallOne.getX();
                        ballOneY7 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Sedmý obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 5:
                        playerOneX6 = imagePlayerOne.getX();
                        playerOneY6 = imagePlayerOne.getY();
                        playerTwoX6 = imagePlayerTwo.getX();
                        playerTwoY6 = imagePlayerTwo.getY();
                        playerThreeX6 = imagePlayerThree.getX();
                        playerThreeY6 = imagePlayerThree.getY();
                        playerFourX6 = imagePlayerFour.getX();
                        playerFourY6 = imagePlayerFour.getY();
                        ballOneX6 = imageBallOne.getX();
                        ballOneY6 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Šestý obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 4:
                        playerOneX5 = imagePlayerOne.getX();
                        playerOneY5 = imagePlayerOne.getY();
                        playerTwoX5 = imagePlayerTwo.getX();
                        playerTwoY5 = imagePlayerTwo.getY();
                        playerThreeX5 = imagePlayerThree.getX();
                        playerThreeY5 = imagePlayerThree.getY();
                        playerFourX5 = imagePlayerFour.getX();
                        playerFourY5 = imagePlayerFour.getY();
                        ballOneX5 = imageBallOne.getX();
                        ballOneY5 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Pátý obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 3:
                        playerOneX4 = imagePlayerOne.getX();
                        playerOneY4 = imagePlayerOne.getY();
                        playerTwoX4 = imagePlayerTwo.getX();
                        playerTwoY4 = imagePlayerTwo.getY();
                        playerThreeX4 = imagePlayerThree.getX();
                        playerThreeY4 = imagePlayerThree.getY();
                        playerFourX4 = imagePlayerFour.getX();
                        playerFourY4 = imagePlayerFour.getY();
                        ballOneX4 = imageBallOne.getX();
                        ballOneY4 = imageBallOne.getY();
                        Toast.makeText(getApplicationContext(),"Čtvrtý obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                    case 2:
                        playerOneX3 = imagePlayerOne.getX();
                        playerOneY3 = imagePlayerOne.getY();
                        playerTwoX3 = imagePlayerTwo.getX();
                        playerTwoY3 = imagePlayerTwo.getY();
                        playerThreeX3 = imagePlayerThree.getX();
                        playerThreeY3 = imagePlayerThree.getY();
                        playerFourX3 = imagePlayerFour.getX();
                        playerFourY3 = imagePlayerFour.getY();
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
                        playerThreeX2 = imagePlayerThree.getX();
                        playerThreeY2 = imagePlayerThree.getY();
                        playerFourX2 = imagePlayerFour.getX();
                        playerFourY2 = imagePlayerFour.getY();
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
                        playerThreeX1 = imagePlayerThree.getX();
                        playerThreeY1 = imagePlayerThree.getY();
                        playerFourX1 = imagePlayerFour.getX();
                        playerFourY1 = imagePlayerFour.getY();
                        ballOneX1 = imageBallOne.getX();
                        ballOneY1 = imageBallOne.getY();
                        //Pokud nebyly obrázky při prvním snímku použity stanou se uživateli neviditelnými
                        if (playerOneX1 == 50 && playerOneY1 == 1040) {
                            imagePlayerOne.setVisibility(View.INVISIBLE);
                        }
                        if (playerTwoX1 == 150 && playerTwoY1 == 1040) {
                            imagePlayerTwo.setVisibility(View.INVISIBLE);
                        }
                        if (playerThreeX1 == 250 && playerThreeY1 == 1040) {
                            imagePlayerThree.setVisibility(View.INVISIBLE);
                        }
                        if (playerFourX1 == 350 && playerFourY1 == 1040) {
                            imagePlayerFour.setVisibility(View.INVISIBLE);
                        }
                        if (ballOneX1 == 450 && ballOneY1 == 1050) {
                            imageBallOne.setVisibility(View.INVISIBLE);
                        }
                        Toast.makeText(getApplicationContext(),"První obrázek animace byl vytvořen.", Toast.LENGTH_LONG).show();
                        frameCount++;
                        break;
                }
            }
        });

        //při kliknutí na tlačítko pro uložení celé animace
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frameCount < 2) {
                    Toast.makeText(getApplicationContext(), "Před uložením animace musíte nejprve vytvořit nejméně dva snímky!", Toast.LENGTH_LONG).show();
                } else {
                    saveAnimation();
                    Toast.makeText(getApplicationContext(),"Animace byla uložena.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    //metoda pro vytvoření nové animace, nastavení jejích parametrů a její uložení do databáze
    private void saveAnimation() {
        AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        Animation animation = new Animation();
        animation.setName(gameSituation.getName());
        animation.setPlayerOneX1(playerOneX1);
        animation.setPlayerOneY1(playerOneY1);
        animation.setPlayerOneX2(playerOneX2);
        animation.setPlayerOneY2(playerOneY2);
        animation.setPlayerOneX3(playerOneX3);
        animation.setPlayerOneY3(playerOneY3);
        animation.setPlayerOneX4(playerOneX4);
        animation.setPlayerOneY4(playerOneY4);
        animation.setPlayerOneX5(playerOneX5);
        animation.setPlayerOneY5(playerOneY5);
        animation.setPlayerOneX6(playerOneX6);
        animation.setPlayerOneY6(playerOneY6);
        animation.setPlayerOneX7(playerOneX7);
        animation.setPlayerOneY7(playerOneY7);
        animation.setPlayerOneX8(playerOneX8);
        animation.setPlayerOneY8(playerOneY8);
        animation.setPlayerOneX9(playerOneX9);
        animation.setPlayerOneY9(playerOneY9);
        animation.setPlayerOneX10(playerOneX10);
        animation.setPlayerOneY10(playerOneY10);

        animation.setPlayerTwoX1(playerTwoX1);
        animation.setPlayerTwoY1(playerTwoY1);
        animation.setPlayerTwoX2(playerTwoX2);
        animation.setPlayerTwoY2(playerTwoY2);
        animation.setPlayerTwoX3(playerTwoX3);
        animation.setPlayerTwoY3(playerTwoY3);
        animation.setPlayerTwoX4(playerTwoX4);
        animation.setPlayerTwoY4(playerTwoY4);
        animation.setPlayerTwoX5(playerTwoX5);
        animation.setPlayerTwoY5(playerTwoY5);
        animation.setPlayerTwoX6(playerTwoX6);
        animation.setPlayerTwoY6(playerTwoY6);
        animation.setPlayerTwoX7(playerTwoX7);
        animation.setPlayerTwoY7(playerTwoY7);
        animation.setPlayerTwoX8(playerTwoX8);
        animation.setPlayerTwoY8(playerTwoY8);
        animation.setPlayerTwoX9(playerTwoX9);
        animation.setPlayerTwoY9(playerTwoY9);
        animation.setPlayerTwoX10(playerTwoX10);
        animation.setPlayerTwoY10(playerTwoY10);

        animation.setPlayerThreeX1(playerThreeX1);
        animation.setPlayerThreeY1(playerThreeY1);
        animation.setPlayerThreeX2(playerThreeX2);
        animation.setPlayerThreeY2(playerThreeY2);
        animation.setPlayerThreeX3(playerThreeX3);
        animation.setPlayerThreeY3(playerThreeY3);
        animation.setPlayerThreeX4(playerThreeX4);
        animation.setPlayerThreeY4(playerThreeY4);
        animation.setPlayerThreeX5(playerThreeX5);
        animation.setPlayerThreeY5(playerThreeY5);
        animation.setPlayerThreeX6(playerThreeX6);
        animation.setPlayerThreeY6(playerThreeY6);
        animation.setPlayerThreeX7(playerThreeX7);
        animation.setPlayerThreeY7(playerThreeY7);
        animation.setPlayerThreeX8(playerThreeX8);
        animation.setPlayerThreeY8(playerThreeY8);
        animation.setPlayerThreeX9(playerThreeX9);
        animation.setPlayerThreeY9(playerThreeY9);
        animation.setPlayerThreeX10(playerThreeX10);
        animation.setPlayerThreeY10(playerThreeY10);

        animation.setPlayerFourX1(playerFourX1);
        animation.setPlayerFourY1(playerFourY1);
        animation.setPlayerFourX2(playerFourX2);
        animation.setPlayerFourY2(playerFourY2);
        animation.setPlayerFourX3(playerFourX3);
        animation.setPlayerFourY3(playerFourY3);
        animation.setPlayerFourX4(playerFourX4);
        animation.setPlayerFourY4(playerFourY4);
        animation.setPlayerFourX5(playerFourX5);
        animation.setPlayerFourY5(playerFourY5);
        animation.setPlayerFourX6(playerFourX6);
        animation.setPlayerFourY6(playerFourY6);
        animation.setPlayerFourX7(playerFourX7);
        animation.setPlayerFourY7(playerFourY7);
        animation.setPlayerFourX8(playerFourX8);
        animation.setPlayerFourY8(playerFourY8);
        animation.setPlayerFourX9(playerFourX9);
        animation.setPlayerFourY9(playerFourY9);
        animation.setPlayerFourX10(playerFourX10);
        animation.setPlayerFourY10(playerFourY10);

        animation.setBallOneX1(ballOneX1);
        animation.setBallOneY1(ballOneY1);
        animation.setBallOneX2(ballOneX2);
        animation.setBallOneY2(ballOneY2);
        animation.setBallOneX3(ballOneX3);
        animation.setBallOneY3(ballOneY3);
        animation.setBallOneX4(ballOneX4);
        animation.setBallOneY4(ballOneY4);
        animation.setBallOneX5(ballOneX5);
        animation.setBallOneY5(ballOneY5);
        animation.setBallOneX6(ballOneX6);
        animation.setBallOneY6(ballOneY6);
        animation.setBallOneX7(ballOneX7);
        animation.setBallOneY7(ballOneY7);
        animation.setBallOneX8(ballOneX8);
        animation.setBallOneY8(ballOneY8);
        animation.setBallOneX9(ballOneX9);
        animation.setBallOneY9(ballOneY9);
        animation.setBallOneX10(ballOneX10);
        animation.setBallOneY10(ballOneY10);

        appDatabase.getAnimationDao().insert(animation);
    }

    //přiřazení menu ke stránce
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //při kliknutí na jednotku v menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                showAboutDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //metoda pro načtení dialogu s nápovědou
    private void showAboutDialog() {
        AboutDialogAnimationCreatorActivity aboutDialog = new AboutDialogAnimationCreatorActivity();
        aboutDialog.show(getSupportFragmentManager(), "dialog_fragment_about");
    }

    //třída pro pohyb s obrázkem prvního hráče
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

    //třída pro pohyb s obrázkem druhého hráče
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

    //třída pro pohyb s obrázkem třetího hráče
    private final class PlayerThreeTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams4 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams4.leftMargin;
                    _yDelta = Y - lParams4.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams4.leftMargin = X - _xDelta;
                    layoutParams4.topMargin = Y - _yDelta;
                    layoutParams4.rightMargin = -250;
                    layoutParams4.bottomMargin = -250;
                    view.setLayoutParams(layoutParams4);
                    break;
            }
            return true;
        }
    }

    //třída pro pohyb s obrázkem čtvrtého hráče
    private final class PlayerFourTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams5 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams5.leftMargin;
                    _yDelta = Y - lParams5.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams5.leftMargin = X - _xDelta;
                    layoutParams5.topMargin = Y - _yDelta;
                    layoutParams5.rightMargin = -250;
                    layoutParams5.bottomMargin = -250;
                    view.setLayoutParams(layoutParams5);
                    break;
            }
            return true;
        }
    }

    //třída pro pohyb s obrázkem prvního balonu
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
