move lib\crud_javafx_libros_base_informes_DIST.jar .
del lib\commons-logging-1.3.0.jar
del lib\xml-apis-1.4.01.jar

java --module-path lib --add-modules javafx.base,javafx.controls,javafx.graphics,javafx.fxml,javafx.web,org.controlsfx.controls -jar crud_javafx_libros_base_informes_DIST.jar
pause


