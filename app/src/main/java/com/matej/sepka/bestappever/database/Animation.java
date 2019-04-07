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
}
