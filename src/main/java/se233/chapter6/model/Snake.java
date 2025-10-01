package se233.chapter6.model;

import javafx.geometry.Point2D;
import se233.chapter6.view.GameStage;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private se233.chapter6.model.Direction direction;
    private Point2D head;
    private Point2D prev_tail;
    private List<Point2D> body;

    public Snake(Point2D position) {
        direction = se233.chapter6.model.Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);
    }

    public boolean checkDead() {
        Point2D head = body.get(0);

        // ถ้าหัวออกนอกขอบกระดาน → ตาย
        if (head.getX() < 0 || head.getX() >= GameStage.WIDTH ||
                head.getY() < 0 || head.getY() >= GameStage.HEIGHT) {
            return true;
        }

        // เช็คชนตัวเอง
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void move() {
        head = head.add(direction.current);
        prev_tail = body.remove(body.size() - 1);
        body.add(0, head);
    }

    public void setDirection(se233.chapter6.model.Direction direction) {
        // ไม่อนุญาตให้เปลี่ยนทิศทางตรงข้ามกับที่กำลังเคลื่อนไป
        if (this.direction == se233.chapter6.model.Direction.UP && direction == se233.chapter6.model.Direction.DOWN) return;
        if (this.direction == se233.chapter6.model.Direction.DOWN && direction == se233.chapter6.model.Direction.UP) return;
        if (this.direction == se233.chapter6.model.Direction.LEFT && direction == se233.chapter6.model.Direction.RIGHT) return;
        if (this.direction == se233.chapter6.model.Direction.RIGHT && direction == se233.chapter6.model.Direction.LEFT) return;
        this.direction = direction;
    }

    public se233.chapter6.model.Direction getDirection () {
        return this.direction;
    }

    public Point2D getHead () {
        return head;
    }

    public boolean collided(Food food) {
        return head.equals(food.getPosition());
    }

    public void grow() {
        body.add(prev_tail);
    }

    public int getLength() {
        return body.size();
    }

    public List<Point2D> getBody() {
        return body;
    }
}