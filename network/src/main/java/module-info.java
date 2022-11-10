module bhcc.edu.mandip {
    requires javafx.controls;
    requires javafx.fxml;

    opens bhcc.edu.mandip to javafx.fxml;
    exports bhcc.edu.mandip;
}
