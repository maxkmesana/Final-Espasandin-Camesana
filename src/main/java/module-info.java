module org.tpfinal.finalcamesanaespasandin {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.tpfinal.Product.Controller to javafx.fxml;

    exports org.tpfinal;
}