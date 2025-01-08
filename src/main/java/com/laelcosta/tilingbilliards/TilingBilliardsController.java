package com.laelcosta.tilingbilliards;

import com.laelcosta.tilingbilliards.geometry.Vector2D;
import com.laelcosta.tilingbilliards.tiling.PolygonalTiling;
import com.laelcosta.tilingbilliards.tiling.QuasiRegularTiling;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class TilingBilliardsController {
    private final static double ZOOM_SPEED = 0.005;
    private final PolygonalTiling tiling = new QuasiRegularTiling(3, 6);

    double startX = 0;
    double startY = 0;

    double mouseX;
    double mouseY;

    @FXML
    private Slider slider;
    
    @FXML    
    private Canvas canvas;

    @FXML
    private Canvas canvas;

    private DrawController drawController;

    @FXML
    public void initialize() {
         drawController = new DrawController(
                 canvas.getWidth(), canvas.getHeight(), canvas.getGraphicsContext2D()
         );
         tiling.generate(20);
         tiling.tilingBilliard(new Vector2D(startX, startY), -slider.getValue() * Math.PI, 100000);
         this.draw();

         slider.valueProperty().addListener((_, _, newValue) -> {
             tiling.tilingBilliard(new Vector2D(startX, startY), -newValue.doubleValue() * Math.PI, 100000);
             this.draw();
         });
    }

    @FXML
    protected void onCanvasDragStart(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
        this.draw();
    }

    @FXML
    protected void onCanvasDrag(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        double z = drawController.getZoom();
        drawController.translate((x - mouseX) / z, (y - mouseY) / z);

        mouseX = x;
        mouseY = y;
        this.draw();
    }

    @FXML
    protected void onCanvasScroll(ScrollEvent event) {
        double x = event.getX();
        double y = event.getY();

        Vector2D v = drawController.getCenter();
        double z = drawController.getZoom();
        // translate v so that (x, y) is in the center
        v.x += (x - drawController.width / 2) / z;
        v.y += (y - drawController.height / 2) / z;

        z = drawController.getZoom() * Math.exp(event.getDeltaY() * ZOOM_SPEED);
        drawController.setZoom(z);

        v.x -= (x - drawController.width / 2) / z;
        v.y -= (y - drawController.height / 2) / z;

        drawController.setCenter(v.x, v.y);
        this.draw();
    }

    void draw() {
        drawController.clear();
        tiling.draw(drawController);
    }
}