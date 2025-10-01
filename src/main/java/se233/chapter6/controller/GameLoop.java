package se233.chapter6.controller;

import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.input.KeyCode;
import se233.chapter6.model.Direction;
import se233.chapter6.model.Food;
import se233.chapter6.model.Snake;
import se233.chapter6.model.SpecialFood;
import se233.chapter6.view.GameStage;

import java.lang.reflect.Field;

public class GameLoop implements Runnable {

    private int score = 0;

    public Food normalFood = new Food();
    public SpecialFood specialFood = new SpecialFood();

    private GameStage gameStage;
    private Snake snake;
    private float interval = 1000.0f / 10;
    private boolean running;

    public GameLoop(GameStage gameStage, Snake snake) {
        this.snake = snake;
        this.gameStage = gameStage;
        running = true;
    }

    private void keyProcess() {
        KeyCode curKey = gameStage.getKey();
        Direction curDirection = snake.getDirection();
        if (curKey == KeyCode.UP && curDirection != Direction.DOWN)
            snake.setDirection(Direction.UP);
        else if (curKey == KeyCode.DOWN && curDirection != Direction.UP)
            snake.setDirection(Direction.DOWN);
        else if (curKey == KeyCode.LEFT && curDirection != Direction.RIGHT)
            snake.setDirection(Direction.LEFT);
        else if (curKey == KeyCode.RIGHT && curDirection != Direction.LEFT)
            snake.setDirection(Direction.RIGHT);
    }

    public void checkCollision() {

        if (snake.collided(normalFood)) {
            snake.grow();
            normalFood.respawn();
            score += 1;
        }

        if (snake.collided(specialFood)) {
            for (int i = 0; i < specialFood.getGrowthValue(); i++) {
                snake.grow();
            }
            specialFood.respawn();
            score += 5;
        }
    }

    private void redraw() {
        gameStage.render(snake, normalFood, specialFood);
    }

    @Override
    public void run() {
        while (running) {
            keyProcess();
            snake.move();

            if (snake.checkDead()) {
                running = false; // หยุด loop
                break;
            }

            checkCollision();
            redraw();
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        javafx.application.Platform.runLater(() -> {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("Game Over!");
            alert.setContentText("Game Over!\nYour Score: " + score);
            alert.showAndWait();
        });
    }

    private Object getField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    public int getScore() {
        return score;
    }
}