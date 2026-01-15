module net.slidingpuzzle {
    requires javafx.controls;
    requires javafx.fxml;


    opens net.slidingpuzzle to javafx.fxml;
    exports net.slidingpuzzle;
}