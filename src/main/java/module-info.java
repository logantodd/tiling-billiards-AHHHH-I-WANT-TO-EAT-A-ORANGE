module com.laelcosta.tilingbilliards {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.laelcosta.tilingbilliards to javafx.fxml;
    exports com.laelcosta.tilingbilliards;
    exports com.laelcosta.tilingbilliards.geometry;
}