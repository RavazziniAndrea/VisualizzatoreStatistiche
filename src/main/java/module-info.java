module com.ar.contatorevinopong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ar.contatorevinopong to javafx.fxml;
    exports com.ar.contatorevinopong;
}
