package com.matej.sepka.bestappever.database;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Animation implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private float playerOneX1;
    private float playerOneY1;
    private float playerOneX2;
    private float playerOneY2;
    private float playerOneX3;
    private float playerOneY3;
    private float playerOneX4;
    private float playerOneY4;
    private float playerOneX5;
    private float playerOneY5;

    private float playerTwoX1;
    private float playerTwoY1;
    private float playerTwoX2;
    private float playerTwoY2;
    private float playerTwoX3;
    private float playerTwoY3;
    private float playerTwoX4;
    private float playerTwoY4;
    private float playerTwoX5;
    private float playerTwoY5;

    private float playerThreeX1;
    private float playerThreeY1;
    private float playerThreeX2;
    private float playerThreeY2;
    private float playerThreeX3;
    private float playerThreeY3;
    private float playerThreeX4;
    private float playerThreeY4;
    private float playerThreeX5;
    private float playerThreeY5;

    private float playerFourX1;
    private float playerFourY1;
    private float playerFourX2;
    private float playerFourY2;
    private float playerFourX3;
    private float playerFourY3;
    private float playerFourX4;
    private float playerFourY4;
    private float playerFourX5;
    private float playerFourY5;

    private float BallOneX1;
    private float BallOneY1;
    private float BallOneX2;
    private float BallOneY2;
    private float BallOneX3;
    private float BallOneY3;
    private float BallOneX4;
    private float BallOneY4;
    private float BallOneX5;
    private float BallOneY5;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPlayerOneX1() {
        return playerOneX1;
    }

    public void setPlayerOneX1(float playerOneX1) {
        this.playerOneX1 = playerOneX1;
    }

    public float getPlayerOneY1() {
        return playerOneY1;
    }

    public void setPlayerOneY1(float playerOneY1) {
        this.playerOneY1 = playerOneY1;
    }

    public float getPlayerOneX2() {
        return playerOneX2;
    }

    public void setPlayerOneX2(float playerOneX2) {
        this.playerOneX2 = playerOneX2;
    }

    public float getPlayerOneY2() {
        return playerOneY2;
    }

    public void setPlayerOneY2(float playerOneY2) {
        this.playerOneY2 = playerOneY2;
    }

    public float getPlayerOneX3() {
        return playerOneX3;
    }

    public void setPlayerOneX3(float playerOneX3) {
        this.playerOneX3 = playerOneX3;
    }

    public float getPlayerOneY3() {
        return playerOneY3;
    }

    public void setPlayerOneY3(float playerOneY3) {
        this.playerOneY3 = playerOneY3;
    }

    public float getPlayerTwoX1() {
        return playerTwoX1;
    }

    public void setPlayerTwoX1(float playerTwoX1) {
        this.playerTwoX1 = playerTwoX1;
    }

    public float getPlayerTwoY1() {
        return playerTwoY1;
    }

    public void setPlayerTwoY1(float playerTwoY1) {
        this.playerTwoY1 = playerTwoY1;
    }

    public float getPlayerTwoX2() {
        return playerTwoX2;
    }

    public void setPlayerTwoX2(float playerTwoX2) {
        this.playerTwoX2 = playerTwoX2;
    }

    public float getPlayerTwoY2() {
        return playerTwoY2;
    }

    public void setPlayerTwoY2(float playerTwoY2) {
        this.playerTwoY2 = playerTwoY2;
    }

    public float getPlayerTwoX3() {
        return playerTwoX3;
    }

    public void setPlayerTwoX3(float playerTwoX3) {
        this.playerTwoX3 = playerTwoX3;
    }

    public float getPlayerTwoY3() {
        return playerTwoY3;
    }

    public void setPlayerTwoY3(float playerTwoY3) {
        this.playerTwoY3 = playerTwoY3;
    }

    public float getBallOneX1() {
        return BallOneX1;
    }

    public void setBallOneX1(float ballOneX1) {
        BallOneX1 = ballOneX1;
    }

    public float getBallOneY1() {
        return BallOneY1;
    }

    public void setBallOneY1(float ballOneY1) {
        BallOneY1 = ballOneY1;
    }

    public float getBallOneX2() {
        return BallOneX2;
    }

    public void setBallOneX2(float ballOneX2) {
        BallOneX2 = ballOneX2;
    }

    public float getBallOneY2() {
        return BallOneY2;
    }

    public void setBallOneY2(float ballOneY2) {
        BallOneY2 = ballOneY2;
    }

    public float getBallOneX3() {
        return BallOneX3;
    }

    public void setBallOneX3(float ballOneX3) {
        BallOneX3 = ballOneX3;
    }

    public float getBallOneY3() {
        return BallOneY3;
    }

    public void setBallOneY3(float ballOneY3) {
        BallOneY3 = ballOneY3;
    }

    public float getPlayerOneX4() {
        return playerOneX4;
    }

    public void setPlayerOneX4(float playerOneX4) {
        this.playerOneX4 = playerOneX4;
    }

    public float getPlayerOneY4() {
        return playerOneY4;
    }

    public void setPlayerOneY4(float playerOneY4) {
        this.playerOneY4 = playerOneY4;
    }

    public float getPlayerOneX5() {
        return playerOneX5;
    }

    public void setPlayerOneX5(float playerOneX5) {
        this.playerOneX5 = playerOneX5;
    }

    public float getPlayerOneY5() {
        return playerOneY5;
    }

    public void setPlayerOneY5(float playerOneY5) {
        this.playerOneY5 = playerOneY5;
    }

    public float getPlayerTwoX4() {
        return playerTwoX4;
    }

    public void setPlayerTwoX4(float playerTwoX4) {
        this.playerTwoX4 = playerTwoX4;
    }

    public float getPlayerTwoY4() {
        return playerTwoY4;
    }

    public void setPlayerTwoY4(float playerTwoY4) {
        this.playerTwoY4 = playerTwoY4;
    }

    public float getPlayerTwoX5() {
        return playerTwoX5;
    }

    public void setPlayerTwoX5(float playerTwoX5) {
        this.playerTwoX5 = playerTwoX5;
    }

    public float getPlayerTwoY5() {
        return playerTwoY5;
    }

    public void setPlayerTwoY5(float playerTwoY5) {
        this.playerTwoY5 = playerTwoY5;
    }

    public float getBallOneX4() {
        return BallOneX4;
    }

    public void setBallOneX4(float ballOneX4) {
        BallOneX4 = ballOneX4;
    }

    public float getBallOneY4() {
        return BallOneY4;
    }

    public void setBallOneY4(float ballOneY4) {
        BallOneY4 = ballOneY4;
    }

    public float getBallOneX5() {
        return BallOneX5;
    }

    public void setBallOneX5(float ballOneX5) {
        BallOneX5 = ballOneX5;
    }

    public float getBallOneY5() {
        return BallOneY5;
    }

    public void setBallOneY5(float ballOneY5) {
        BallOneY5 = ballOneY5;
    }

    public float getPlayerThreeX1() {
        return playerThreeX1;
    }

    public void setPlayerThreeX1(float playerThreeX1) {
        this.playerThreeX1 = playerThreeX1;
    }

    public float getPlayerThreeY1() {
        return playerThreeY1;
    }

    public void setPlayerThreeY1(float playerThreeY1) {
        this.playerThreeY1 = playerThreeY1;
    }

    public float getPlayerThreeX2() {
        return playerThreeX2;
    }

    public void setPlayerThreeX2(float playerThreeX2) {
        this.playerThreeX2 = playerThreeX2;
    }

    public float getPlayerThreeY2() {
        return playerThreeY2;
    }

    public void setPlayerThreeY2(float playerThreeY2) {
        this.playerThreeY2 = playerThreeY2;
    }

    public float getPlayerThreeX3() {
        return playerThreeX3;
    }

    public void setPlayerThreeX3(float playerThreeX3) {
        this.playerThreeX3 = playerThreeX3;
    }

    public float getPlayerThreeY3() {
        return playerThreeY3;
    }

    public void setPlayerThreeY3(float playerThreeY3) {
        this.playerThreeY3 = playerThreeY3;
    }

    public float getPlayerThreeX4() {
        return playerThreeX4;
    }

    public void setPlayerThreeX4(float playerThreeX4) {
        this.playerThreeX4 = playerThreeX4;
    }

    public float getPlayerThreeY4() {
        return playerThreeY4;
    }

    public void setPlayerThreeY4(float playerThreeY4) {
        this.playerThreeY4 = playerThreeY4;
    }

    public float getPlayerThreeX5() {
        return playerThreeX5;
    }

    public void setPlayerThreeX5(float playerThreeX5) {
        this.playerThreeX5 = playerThreeX5;
    }

    public float getPlayerThreeY5() {
        return playerThreeY5;
    }

    public void setPlayerThreeY5(float playerThreeY5) {
        this.playerThreeY5 = playerThreeY5;
    }

    public float getPlayerFourX1() {
        return playerFourX1;
    }

    public void setPlayerFourX1(float playerFourX1) {
        this.playerFourX1 = playerFourX1;
    }

    public float getPlayerFourY1() {
        return playerFourY1;
    }

    public void setPlayerFourY1(float playerFourY1) {
        this.playerFourY1 = playerFourY1;
    }

    public float getPlayerFourX2() {
        return playerFourX2;
    }

    public void setPlayerFourX2(float playerFourX2) {
        this.playerFourX2 = playerFourX2;
    }

    public float getPlayerFourY2() {
        return playerFourY2;
    }

    public void setPlayerFourY2(float playerFourY2) {
        this.playerFourY2 = playerFourY2;
    }

    public float getPlayerFourX3() {
        return playerFourX3;
    }

    public void setPlayerFourX3(float playerFourX3) {
        this.playerFourX3 = playerFourX3;
    }

    public float getPlayerFourY3() {
        return playerFourY3;
    }

    public void setPlayerFourY3(float playerFourY3) {
        this.playerFourY3 = playerFourY3;
    }

    public float getPlayerFourX4() {
        return playerFourX4;
    }

    public void setPlayerFourX4(float playerFourX4) {
        this.playerFourX4 = playerFourX4;
    }

    public float getPlayerFourY4() {
        return playerFourY4;
    }

    public void setPlayerFourY4(float playerFourY4) {
        this.playerFourY4 = playerFourY4;
    }

    public float getPlayerFourX5() {
        return playerFourX5;
    }

    public void setPlayerFourX5(float playerFourX5) {
        this.playerFourX5 = playerFourX5;
    }

    public float getPlayerFourY5() {
        return playerFourY5;
    }

    public void setPlayerFourY5(float playerFourY5) {
        this.playerFourY5 = playerFourY5;
    }
}
