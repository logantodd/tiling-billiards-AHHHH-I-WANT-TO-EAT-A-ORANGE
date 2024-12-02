package com.laelcosta.tilingbilliards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class TilingBilliardsController {
    @FXML
    private Label infoText;

    @FXML
    private Slider slider;

    @FXML
    protected void onGoButtonClick() {
        infoText.setText(String.format("Set n = %d.", Math.round(slider.getValue())));
        // TODO(Cecilia): When the button is clicked, draw a regular n-gon to the center of the canvas.
        //  Hint: you will need to access the Canvas (try adding the fx:id attribute in the FXML file), and then draw to
        //  it using a GraphicsContext. See GraphicsContext::setFill() and GraphicsContext::fillPolygon()
    }
}