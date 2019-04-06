package com.matej.sepka.bestappever.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_watch);

        gameSituation = (GameSituation) getIntent().getExtras().getSerializable("game_situation");
        final AppDatabase appDatabase = AppDatabase.getInstance(getApplication());
        Button startBtn = findViewById(R.id.startBtn);
        final ImageView imgPlayerOne = findViewById(R.id.player_one_img);
        final ImageView imgPlayerTwo = findViewById(R.id.player_two_img);
        final ImageView imgBallOne = findViewById(R.id.ball_one_img);

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

        imgBallOne.setLayoutParams(layoutParamsBall);
        imgBallOne.setX(animation.getBallOneX1());
        imgBallOne.setY(animation.getBallOneY1());

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimatorSet animatorSet = new AnimatorSet();
                AnimatorSet animatorSet1 = new AnimatorSet();
                ObjectAnimator PlayerOneY1 = new ObjectAnimator().ofFloat(imgPlayerOne,"translationY" , animation.getPlayerOneY2());
                ObjectAnimator PlayerOneX1 = new ObjectAnimator().ofFloat(imgPlayerOne,"translationX" , animation.getPlayerOneX2());
                ObjectAnimator PlayerTwoX1 = new ObjectAnimator().ofFloat(imgPlayerTwo,"translationX" , animation.getPlayerTwoX2());
                ObjectAnimator PlayerTwoY1 = new ObjectAnimator().ofFloat(imgPlayerTwo,"translationY" , animation.getPlayerTwoY2());
                ObjectAnimator BallOneX1 = new ObjectAnimator().ofFloat(imgBallOne,"translationX" , animation.getBallOneX2());
                ObjectAnimator BallOneY1 = new ObjectAnimator().ofFloat(imgBallOne,"translationY" , animation.getBallOneY2());
                animatorSet1.playTogether(PlayerOneX1,PlayerOneY1, PlayerTwoX1, PlayerTwoY1, BallOneX1, BallOneY1);
                animatorSet1.setDuration(3000);

                AnimatorSet animatorSet2 = new AnimatorSet();
                ObjectAnimator PlayerOneX2 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationX", animation.getPlayerOneX3());
                ObjectAnimator PlayerOneY2 = new ObjectAnimator().ofFloat(imgPlayerOne, "translationY", animation.getPlayerOneY3());
                ObjectAnimator PlayerTwoX2 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationX", animation.getPlayerTwoX3());
                ObjectAnimator PlayerTwoY2 = new ObjectAnimator().ofFloat(imgPlayerTwo, "translationY", animation.getPlayerTwoY3());
                ObjectAnimator BallOneX2 = new ObjectAnimator().ofFloat(imgBallOne, "translationX", animation.getBallOneX3());
                ObjectAnimator BallOneY2 = new ObjectAnimator().ofFloat(imgBallOne, "translationY", animation.getBallOneY3());
                animatorSet2.playTogether(PlayerOneX2, PlayerOneY2, PlayerTwoX2, PlayerTwoY2, BallOneX2, BallOneY2);
                animatorSet2.setDuration(3000);
                animatorSet.play(animatorSet1).before(animatorSet2);
                animatorSet.start();
            }
        });

    }
}
