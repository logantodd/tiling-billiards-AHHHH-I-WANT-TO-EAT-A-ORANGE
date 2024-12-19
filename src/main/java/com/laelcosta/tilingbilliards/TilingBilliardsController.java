package com.laelcosta.tilingbilliards;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class TilingBilliardsController {
    @FXML
    private Label infoText;

    @FXML
    private Slider slider;
    
    @FXML    
    private Canvas canvas;

    @FXML
    protected void onGoButtonClick() {
    	
        infoText.setText(String.format("Set n = %d.", Math.round(slider.getValue())));
        // TODO(Cecilia): When the button is clicked, draw a regular n-gon to the center of the canvas.
        //  Hint: you will need to access the Canvas (try adding the fx:id attribute in the FXML file), and then draw to
        //  it using a GraphicsContext. See GraphicsContext::setFill() and GraphicsContext::fillPolygon()
        
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        graphicsContext.setFill(Color.VIOLET);
        
        int n = (int)Math.round(slider.getValue());
        
        
        
        double[] xCoordinates = new double[n];
        double[] yCoordinates = new double[n];
        
        for(int i=0; i<n; i++)
        {
        	xCoordinates[i] = 100*(Math.cos(2*i*Math.PI/n)) + canvas.getWidth()/2;
        	yCoordinates[i] = 100*(Math.sin(2*i*Math.PI/n)) + canvas.getHeight()/2;
        }
        
        graphicsContext.fillPolygon(xCoordinates, yCoordinates, n);
    }
}