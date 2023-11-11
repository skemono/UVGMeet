module com.uvgmeetf.uvgmeetf {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.uvgmeetf.uvgmeetf to javafx.fxml;
    exports com.uvgmeetf.uvgmeetf;
}