module org.tpfinal.finalcamesanaespasandin {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.tpfinal to javafx.fxml;
    exports org.tpfinal;
}