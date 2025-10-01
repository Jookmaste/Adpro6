package se233.chapter6.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se233.chapter6.model.Food;
import se233.chapter6.model.Snake;

public class GameStage extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;
    private Canvas canvas;
    private KeyCode key;

    public GameStage() {
        this.setHeight(TILE_SIZE * HEIGHT);
        this.setWidth(TILE_SIZE * WIDTH);
        canvas = new Canvas(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        this.getChildren().add(canvas);
    }

    public void render(Snake snake, Food normalFood, Food specialFood) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        // วาดงู (สีน้ำเงิน)
        gc.setFill(Color.BLUE);
        snake.getBody().forEach(p -> {
            gc.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        });

        // วาดอาหารปกติ (สีแดง)
        gc.setFill(Color.RED);
        gc.fillRect(normalFood.getPosition().getX() * TILE_SIZE,
                normalFood.getPosition().getY() * TILE_SIZE,
                TILE_SIZE, TILE_SIZE);

        // วาดอาหารพิเศษ (สีเขียว)
        gc.setFill(Color.GREEN);
        gc.fillRect(specialFood.getPosition().getX() * TILE_SIZE,
                specialFood.getPosition().getY() * TILE_SIZE,
                TILE_SIZE, TILE_SIZE);
    }

    public KeyCode getKey() {
        return key;
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }
}