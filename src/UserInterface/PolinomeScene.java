package UserInterface;

import Polinom.Polinom;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class PolinomeScene extends Scene {

    private VBox mainBox;
    private TextField poly1 = new TextField(""), poly2 = new TextField(""), polyRes = new TextField(""), polyRem = new TextField("");
    private Button addB = new Button("+"), subtractB = new Button("-"), multiplyB = new Button("*"), divideB = new Button("/"), diffB = new Button("d/dx"), integrB = new Button("integr");

    public PolinomeScene(Parent root, int xSize, int ySize){
        super(root, xSize, ySize);
        mainBox = new VBox();
        mainBox.getChildren().addAll(new Label("Polinomes"));

        HBox p1Box = new HBox();
        p1Box.getChildren().addAll(new Label("Polinome1: "), poly1);
        HBox p2Box = new HBox();
        p2Box.getChildren().addAll(new Label("Polinome2: "), poly2);
        mainBox.getChildren().addAll(p1Box, p2Box);

        mainBox.getChildren().addAll(new Label("Operations:"));
        HBox operationBox = new HBox();
        operationBox.getChildren().addAll(addB, subtractB, multiplyB,divideB, diffB, integrB);
        mainBox.getChildren().addAll(operationBox);
        mainBox.getChildren().addAll(new Label("Result:"), polyRes);
        mainBox.getChildren().addAll(new Label("Remainder:"), polyRem);
        setRoot(mainBox);
        addEvent();

    }
    private void addEvent(){
        addB.setOnAction(e -> {

            Polinom p1 = Polinom.fromLine(poly1.getText());
            Polinom p2 = Polinom.fromLine(poly2.getText());
            Polinom pres = p1.add(p2);
            polyRes.setText(pres.toString());

        });
        subtractB.setOnAction(e -> {

            Polinom p1 = Polinom.fromLine(poly1.getText());
            Polinom p2 = Polinom.fromLine(poly2.getText());
            Polinom pres = p1.subtract(p2);
            polyRes.setText(pres.toString());

        });
        multiplyB.setOnAction( e ->{

            Polinom p1 = Polinom.fromLine(poly1.getText());
            Polinom p2 = Polinom.fromLine(poly2.getText());
            Polinom pres = p1.multiply(p2);
            polyRes.setText(pres.toString());
        });
        diffB.setOnAction(e -> {
            Polinom p = Polinom.fromLine(poly1.getText());
            Polinom pdiff = p.differentiate();
            polyRes.setText(pdiff.toString());
        });

        integrB.setOnAction(ev -> {
            Polinom p = Polinom.fromLine(poly1.getText());
            Polinom pintegr = p.integrate();
            polyRes.setText(pintegr.toString());
        });
        divideB.setOnAction(ev ->{
            Polinom deimp = Polinom.fromLine(poly1.getText());
            Polinom imp = Polinom.fromLine(poly2.getText());
            List<Polinom> cat = deimp.divide(imp);
            Polinom p1 = cat.get(0);
            Polinom p2 = cat.get(1);
            polyRes.setText(p1.toString());
            polyRem.setText(p2.toString());
        });
    }
}
