module edu.umgc.cmsc215.project4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.umgc.cmsc215.project4 to javafx.fxml;
    exports edu.umgc.cmsc215.project4;
}
