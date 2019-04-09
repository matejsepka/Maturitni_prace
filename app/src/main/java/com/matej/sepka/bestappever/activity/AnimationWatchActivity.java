package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.matej.sepka.bestappever.R;
import com.matej.sepka.bestappever.database.Animation;
import com.matej.sepka.bestappever.database.AppDatabase;
import com.matej.sepka.bestappever.database.GameSituation;

import java.util.List;

public class AnimationWatchActivity extends AppCompatActivity {
    private GameSituation gameSituation;
    private Animation animation;
    private ImageView imgPlayerOne;
    private ImageView imgPlayerTwo;
    private ImageView imgPlayerThree;
    private ImageView imgPlayerFour;
    private ImageView imgBallOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_watch);

        gameSituation = (GameSituation) getIntent().getExtras().getSerializable("game_situation");
        final AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        final Button startBtn = findViewById(R.id.startBtn);
        imgPlayerOne = findViewById(R.id.player_one_img);
        imgPlayerTwo = findViewById(R.id.player_two_img);
        imgPlayerThree = findViewById(R.id.player_three_img);
        imgPlayerFour = findViewById(R.id.player_four_img);
        imgBallOne = findViewById(R.id.ball_one_img);

        List<Animation> AllAnimationsList = appDatabase.getAnimationDao().getAll();
        for (int i = 0; i < AllAnimationsList.size(); i++) {
            Animation anim = AllAnimationsList.get(i);
            if (anim.getName().equals(gameSituation.getName())) {
                animation = anim;
            }
        }

        RelativeLayout.LayoutParams layoutParamsPlayer = new RelativeLayout.LayoutParams(70, 70);
        RelativeLayout.LayoutParams layoutParamsBall = new RelativeLayout.LayoutParams(50, 50);

        imgPlayerOne.setLayoutParams(layoutParamsPlayer);
        imgPlayerOne.setX(animation.getPlayerOneX1());
        imgPlayerOne.setY(animation.getPlayerOneY1());

        imgPlayerTwo.setLayoutParams(layoutParamsPlayer);
        imgPlayerTwo.setX(animation.getPlayerTwoX1());
        imgPlayerTwo.setY(animation.getPlayerTwoY1());

        imgPlayerThree.setLayoutParams(layoutParamsPlayer);
        imgPlayerThree.setX(animation.getPlayerThreeX1());
        imgPlayerThree.setY(animation.getPlayerThreeY1());

        imgPlayerFour.setLayoutParams(layoutParamsPlayer);
        imgPlayerFour.setX(animation.getPlayerFourX1());
        imgPlayerFour.setY(animation.getPlayerFourY1());

        imgBallOne.setLayoutParams(layoutParamsBall);
        imgBallOne.setX(animation.getBallOneX1());
        imgBallOne.setY(animation.getBallOneY1());

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgPlayerOne.setX(animation.getPlayerOneX1());
                imgPlayerOne.setY(animation.getPlayerOneY1());
                imgPlayerTwo.setX(animation.getPlayerTwoX1());
                imgPlayerTwo.setY(animation.getPlayerTwoY1());
                imgPlayerThree.setX(animation.getPlayerThreeX1());
                imgPlayerThree.setY(animation.getPlayerThreeY1());
                imgPlayerFour.setX(animation.getPlayerFourX1());
                imgPlayerFour.setY(animation.getPlayerFourY1());
                imgBallOne.setX(animation.getBallOneX1());
                imgBallOne.setY(animation.getBallOneY1());

                playAnimation();
            }
        });

    }

    private void playAnimation() {
        AnimatorSet animatorSet1 = new AnimatorSet();
        ObjectAnimator PlayerOneY1 = new ObjectAnimator().ofFloat(imgPlayerOne,"translationY" , animation.getPlayerOneY2());
        ObjectAnimator PlayerOneX1 = new ObjectAnimator().ofFloat(imgPlayerOne,"translationX" , animation.getPlayerOneX2());
        ObjectAnimator PlayerTwoX1 = new ObjectAnimator().ofFloat(imgPlayerTwo,"translationX" , animation.getPlayerTwoX2());
        ObjectAnimator PlayerTwoY1 = new ObjectAnimator().ofFloat(imgPlayerTwo,"translationY" , animation.getPlayerTwoY2());
        ObjectAnimator PlayerThreeX1 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX2());
        ObjectAnimator PlayerThreeY1 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY2());
        ObjectAnimator PlayerFourX1 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX2());
        ObjectAnimator PlayerFourY1 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY2());
        ObjectAnimator BallOneX1 = new ObjectAnimator().ofFloat(imgBallOne,"translationX" , animation.getBallOneX2());
        ObjectAnimator BallOneY1 = new ObjectAnimator().ofFloat(imgBallOne,"translationY" , animation.getBallOneY2());
        animatorSet1.playTogether(PlayerOneX1,PlayerOneY1, PlayerTwoX1, PlayerTwoY1,PlayerThreeX1,PlayerThreeY1,PlayerFourX1,PlayerFourY1, BallOneX1, BallOneY1);
        animatorSet1.setDuration(2500);

        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator PlayerOneX2 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX3());
        ObjectAnimator PlayerOneY2 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY3());
        ObjectAnimator PlayerTwoX2 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX3());
        ObjectAnimator PlayerTwoY2 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY3());
        ObjectAnimator PlayerThreeX2 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX3());
        ObjectAnimator PlayerThreeY2 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY3());
        ObjectAnimator PlayerFourX2 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX3());
        ObjectAnimator PlayerFourY2 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY3());
        ObjectAnimator BallOneX2 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX3());
        ObjectAnimator BallOneY2 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY3());
        animatorSet2.playTogether(PlayerOneX2, PlayerOneY2, PlayerTwoX2, PlayerTwoY2, PlayerThreeX2, PlayerThreeY2, PlayerFourX2, PlayerFourY2, BallOneX2, BallOneY2);
        animatorSet2.setDuration(2500);

        AnimatorSet animatorSet3 = new AnimatorSet();
        ObjectAnimator PlayerOneX3 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX4());
        ObjectAnimator PlayerOneY3 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY4());
        ObjectAnimator PlayerTwoX3 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX4());
        ObjectAnimator PlayerTwoY3 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY4());
        ObjectAnimator PlayerThreeX3 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX4());
        ObjectAnimator PlayerThreeY3 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY4());
        ObjectAnimator PlayerFourX3 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX4());
        ObjectAnimator PlayerFourY3 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY4());
        ObjectAnimator BallOneX3 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX4());
        ObjectAnimator BallOneY3 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY4());
        animatorSet3.playTogether(PlayerOneX3, PlayerOneY3, PlayerTwoX3, PlayerTwoY3, PlayerThreeX3, PlayerThreeY3, PlayerFourX3, PlayerFourY3, BallOneX3, BallOneY3);
        animatorSet3.setDuration(2500);

        AnimatorSet animatorSet4 = new AnimatorSet();
        ObjectAnimator PlayerOneX4 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX5());
        ObjectAnimator PlayerOneY4 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY5());
        ObjectAnimator PlayerTwoX4 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX5());
        ObjectAnimator PlayerTwoY4 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY5());
        ObjectAnimator PlayerThreeX4 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX5());
        ObjectAnimator PlayerThreeY4 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY5());
        ObjectAnimator PlayerFourX4 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX5());
        ObjectAnimator PlayerFourY4 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY5());
        ObjectAnimator BallOneX4 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX5());
        ObjectAnimator BallOneY4 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY5());
        animatorSet4.playTogether(PlayerOneX4, PlayerOneY4, PlayerTwoX4, PlayerTwoY4, PlayerThreeX4, PlayerThreeY4, PlayerFourX4, PlayerFourY4, BallOneX4, BallOneY4);
        animatorSet4.setDuration(2500);

        AnimatorSet animatorSet5 = new AnimatorSet();
        ObjectAnimator PlayerOneX5 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX6());
        ObjectAnimator PlayerOneY5 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY6());
        ObjectAnimator PlayerTwoX5 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX6());
        ObjectAnimator PlayerTwoY5 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY6());
        ObjectAnimator PlayerThreeX5 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX6());
        ObjectAnimator PlayerThreeY5 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY6());
        ObjectAnimator PlayerFourX5 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX6());
        ObjectAnimator PlayerFourY5 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY6());
        ObjectAnimator BallOneX5 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX6());
        ObjectAnimator BallOneY5 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY6());
        animatorSet5.playTogether(PlayerOneX5, PlayerOneY5, PlayerTwoX5, PlayerTwoY5, PlayerThreeX5, PlayerThreeY5, PlayerFourX5, PlayerFourY5, BallOneX5, BallOneY5);
        animatorSet5.setDuration(2500);

        AnimatorSet animatorSet6 = new AnimatorSet();
        ObjectAnimator PlayerOneX6 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX7());
        ObjectAnimator PlayerOneY6 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY7());
        ObjectAnimator PlayerTwoX6 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX7());
        ObjectAnimator PlayerTwoY6 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY7());
        ObjectAnimator PlayerThreeX6 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX7());
        ObjectAnimator PlayerThreeY6 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY7());
        ObjectAnimator PlayerFourX6 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX7());
        ObjectAnimator PlayerFourY6 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY7());
        ObjectAnimator BallOneX6 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX7());
        ObjectAnimator BallOneY6 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY7());
        animatorSet6.playTogether(PlayerOneX6, PlayerOneY6, PlayerTwoX6, PlayerTwoY6, PlayerThreeX6, PlayerThreeY6, PlayerFourX6, PlayerFourY6, BallOneX6, BallOneY6);
        animatorSet6.setDuration(2500);

        AnimatorSet animatorSet7 = new AnimatorSet();
        ObjectAnimator PlayerOneX7 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX8());
        ObjectAnimator PlayerOneY7 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY8());
        ObjectAnimator PlayerTwoX7 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX8());
        ObjectAnimator PlayerTwoY7 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY8());
        ObjectAnimator PlayerThreeX7 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX8());
        ObjectAnimator PlayerThreeY7 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY8());
        ObjectAnimator PlayerFourX7 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX8());
        ObjectAnimator PlayerFourY7 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY8());
        ObjectAnimator BallOneX7 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX8());
        ObjectAnimator BallOneY7 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY8());
        animatorSet7.playTogether(PlayerOneX7, PlayerOneY7, PlayerTwoX7, PlayerTwoY7, PlayerThreeX7, PlayerThreeY7, PlayerFourX7, PlayerFourY7, BallOneX7, BallOneY7);
        animatorSet7.setDuration(2500);

        AnimatorSet animatorSet8 = new AnimatorSet();
        ObjectAnimator PlayerOneX8 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX9());
        ObjectAnimator PlayerOneY8 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY9());
        ObjectAnimator PlayerTwoX8 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX9());
        ObjectAnimator PlayerTwoY8 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY9());
        ObjectAnimator PlayerThreeX8 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX9());
        ObjectAnimator PlayerThreeY8 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY9());
        ObjectAnimator PlayerFourX8 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX9());
        ObjectAnimator PlayerFourY8 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY9());
        ObjectAnimator BallOneX8 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX9());
        ObjectAnimator BallOneY8 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY9());
        animatorSet8.playTogether(PlayerOneX8, PlayerOneY8, PlayerTwoX8, PlayerTwoY8, PlayerThreeX8, PlayerThreeY8, PlayerFourX8, PlayerFourY8, BallOneX8, BallOneY8);
        animatorSet8.setDuration(2500);

        AnimatorSet animatorSet9 = new AnimatorSet();
        ObjectAnimator PlayerOneX9 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX10());
        ObjectAnimator PlayerOneY9 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY10());
        ObjectAnimator PlayerTwoX9 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX10());
        ObjectAnimator PlayerTwoY9 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY10());
        ObjectAnimator PlayerThreeX9 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationX", animation.getPlayerThreeX10());
        ObjectAnimator PlayerThreeY9 = new ObjectAnimator().ofFloat(imgPlayerThree, "translationY", animation.getPlayerThreeY10());
        ObjectAnimator PlayerFourX9 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationX", animation.getPlayerFourX10());
        ObjectAnimator PlayerFourY9 = new ObjectAnimator().ofFloat(imgPlayerFour, "translationY", animation.getPlayerFourY10());
        ObjectAnimator BallOneX9 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX10());
        ObjectAnimator BallOneY9 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY10());
        animatorSet9.playTogether(PlayerOneX9, PlayerOneY9, PlayerTwoX9, PlayerTwoY9, PlayerThreeX9, PlayerThreeY9, PlayerFourX9, PlayerFourY9, BallOneX9, BallOneY9);
        animatorSet9.setDuration(2500);

        if (animation.getPlayerOneX3() == 0) {
            animatorSet1.start();
        } else if (animation.getPlayerOneX4() == 0) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(animatorSet1).before(animatorSet2);
            animatorSet.start();
        } else if (animation.getPlayerOneX5() == 0) {
            AnimatorSet animatorSetA = new AnimatorSet();
            animatorSetA.play(animatorSet1).before(animatorSet2);
            AnimatorSet animatorSetB = new AnimatorSet();
            animatorSetB.play(animatorSetA).before(animatorSet3);
            animatorSetB.start();
        } else if (animation.getPlayerOneX6() == 0) {
            AnimatorSet animatorSetA = new AnimatorSet();
            animatorSetA.play(animatorSet1).before(animatorSet2);
            AnimatorSet animatorSetB = new AnimatorSet();
            animatorSetB.play(animatorSet3).before(animatorSet4);
            AnimatorSet animatorSetC = new AnimatorSet();
            animatorSetC.play(animatorSetA).before(animatorSetB);
            animatorSetC.start();
        } else if (animation.getPlayerOneX7() == 0) {
            AnimatorSet animatorSetA = new AnimatorSet();
            animatorSetA.play(animatorSet1).before(animatorSet2);
            AnimatorSet animatorSetB = new AnimatorSet();
            animatorSetB.play(animatorSet3).before(animatorSet4);
            AnimatorSet animatorSetC = new AnimatorSet();
            animatorSetC.play(animatorSetA).before(animatorSetB);
            AnimatorSet animatorSetD = new AnimatorSet();
            animatorSetD.play(animatorSetC).before(animatorSet5);
            animatorSetD.start();
        } else if (animation.getPlayerOneX8() == 0) {
            AnimatorSet animatorSetA = new AnimatorSet();
            animatorSetA.play(animatorSet1).before(animatorSet2);
            AnimatorSet animatorSetB = new AnimatorSet();
            animatorSetB.play(animatorSet3).before(animatorSet4);
            AnimatorSet animatorSetC = new AnimatorSet();
            animatorSetC.play(animatorSet5).before(animatorSet6);
            AnimatorSet animatorSetD = new AnimatorSet();
            animatorSetD.play(animatorSetA).before(animatorSetB);
            AnimatorSet animatorSetE = new AnimatorSet();
            animatorSetE.play(animatorSetD).before(animatorSetC);
            animatorSetE.start();
        } else if (animation.getPlayerOneX9() == 0) {
            AnimatorSet animatorSetA = new AnimatorSet();
            animatorSetA.play(animatorSet1).before(animatorSet2);
            AnimatorSet animatorSetB = new AnimatorSet();
            animatorSetB.play(animatorSet3).before(animatorSet4);
            AnimatorSet animatorSetC = new AnimatorSet();
            animatorSetC.play(animatorSet5).before(animatorSet6);
            AnimatorSet animatorSetD = new AnimatorSet();
            animatorSetD.play(animatorSetA).before(animatorSetB);
            AnimatorSet animatorSetE = new AnimatorSet();
            animatorSetE.play(animatorSetC).before(animatorSet7);
            AnimatorSet animatorSetF = new AnimatorSet();
            animatorSetF.play(animatorSetD).before(animatorSetE);
            animatorSetF.start();
        } else if (animation.getPlayerOneX10() == 0) {
            AnimatorSet animatorSetA = new AnimatorSet();
            animatorSetA.play(animatorSet1).before(animatorSet2);
            AnimatorSet animatorSetB = new AnimatorSet();
            animatorSetB.play(animatorSet3).before(animatorSet4);
            AnimatorSet animatorSetC = new AnimatorSet();
            animatorSetC.play(animatorSet5).before(animatorSet6);
            AnimatorSet animatorSetD = new AnimatorSet();
            animatorSetD.play(animatorSet7).before(animatorSet8);
            AnimatorSet animatorSetE = new AnimatorSet();
            animatorSetE.play(animatorSetA).before(animatorSetB);
            AnimatorSet animatorSetF = new AnimatorSet();
            animatorSetF.play(animatorSetC).before(animatorSetD);
            AnimatorSet animatorSetG = new AnimatorSet();
            animatorSetG.play(animatorSetE).before(animatorSetF);
            animatorSetG.start();
        } else {
            AnimatorSet animatorSetA = new AnimatorSet();
            animatorSetA.play(animatorSet1).before(animatorSet2);
            AnimatorSet animatorSetB = new AnimatorSet();
            animatorSetB.play(animatorSet3).before(animatorSet4);
            AnimatorSet animatorSetC = new AnimatorSet();
            animatorSetC.play(animatorSet5).before(animatorSet6);
            AnimatorSet animatorSetD = new AnimatorSet();
            animatorSetD.play(animatorSet7).before(animatorSet8);
            AnimatorSet animatorSetE = new AnimatorSet();
            animatorSetE.play(animatorSetD).before(animatorSet9);
            AnimatorSet animatorSetF = new AnimatorSet();
            animatorSetF.play(animatorSetA).before(animatorSetB);
            AnimatorSet animatorSetG = new AnimatorSet();
            animatorSetG.play(animatorSetC).before(animatorSetE);
            AnimatorSet animatorSetH = new AnimatorSet();
            animatorSetH.play(animatorSetF).before(animatorSetG);
            animatorSetH.start();
        }
    }
}
