module net.slidingpuzzle
{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens net.slidingpuzzle to javafx.fxml;
    opens net.slidingpuzzle.events to javafx.fxml;
    exports net.slidingpuzzle;
}