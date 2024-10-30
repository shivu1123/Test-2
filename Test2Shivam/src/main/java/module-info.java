module org.example.test2shivam {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.mariadb.jdbc;
    requires waffle.jna;


    opens org.example.test2shivam to javafx.fxml;
    exports org.example.test2shivam;
}