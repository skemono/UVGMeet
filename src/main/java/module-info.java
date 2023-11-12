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
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires google.cloud.core;
    requires google.cloud.core.grpc;
    requires google.cloud.core.http;
    requires com.google.api.apicommon;
    requires com.google.auth;

    opens com.uvgmeetf.uvgmeetf to javafx.fxml;
    exports com.uvgmeetf.uvgmeetf;
}