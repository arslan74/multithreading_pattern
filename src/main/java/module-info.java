module com.arslanhiader.www {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.arslanhiader.www to javafx.fxml;
    exports com.arslanhiader.www;
}