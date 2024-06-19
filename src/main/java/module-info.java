module org.tpfinal.finalcamesanaespasandin {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;
    requires com.google.gson;
    requires java.desktop;

    opens org.tpfinal to javafx.fxml;
    opens org.tpfinal.Users.Controller to javafx.fxml;
    exports org.tpfinal;
    exports org.tpfinal.Users.Controller;
}