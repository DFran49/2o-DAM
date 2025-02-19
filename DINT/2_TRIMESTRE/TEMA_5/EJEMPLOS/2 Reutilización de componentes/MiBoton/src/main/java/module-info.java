/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module com.javafx.miscvs { //Declara el módulo, ha de ser de nombre único
    requires javafx.fxml; //Usa el módulo fxml...
    requires javafx.controls;//Usa el módulo....
    requires javafx.swing;
    requires javafx.graphics;
    requires javafx.base;
    exports cvs; //Declara que el paquete cvs y sus clases son accesibles 
    //desde el exterior del jar
    opens cvs to javafx.fxml;//MUY IMPORTANTE:  abre cvs a javafx.fxml 
    //para que acceda a Controlador y pueda inyectar código
}
