module org.tpfinal.finalcamesanaespasandin {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;
    requires com.google.gson;
    requires java.desktop;

    opens org.tpfinal.Product.Controller to javafx.fxml;
    opens org.tpfinal.Abstracts to com.google.gson;
    opens org.tpfinal.Product.Model.Entity to com.google.gson;
    opens org.tpfinal.Users.Model.Entity to com.google.gson;
    opens org.tpfinal to javafx.fxml;
    opens org.tpfinal.Users.Controller to javafx.fxml;
    opens org.tpfinal.StockFile.Controller to javafx.fxml;
    opens org.tpfinal.StockFile.Model.Entity;
    opens org.tpfinal.Seat.Entity;

    exports org.tpfinal;
    exports org.tpfinal.Users.Controller;
}