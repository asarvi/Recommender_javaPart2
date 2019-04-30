
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.*;


public class DrawCharts extends Application {

    @Override public void start(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("rates");
        stage.setWidth(500);
        stage.setHeight(500);
     //   final Label caption = new Label("");



        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                      //  new PieChart.Data("overall = 0", 13),
                        new PieChart.Data("overall = 1", 9294),
                        new PieChart.Data("overall = 2", 4555),
                        new PieChart.Data("overall = 3", 5381),
                        new PieChart.Data("overall = 4", 10336),
                         new PieChart.Data("overall = 5", 20434));

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("overalls");
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });
        }


        stage.show();

    }
    public void drawchart(){
        launch();
    }

    public static void main(String[] args) {
       // launch(args);
        DrawCharts d = new
                DrawCharts();
        d.drawchart();
    }
}
