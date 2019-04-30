/*
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Vector;

public class AnalyseReviews extends Application {
    private int UsefulCommentsInNegativeReviewsPercent = 97;
    private int UnusefulCommentsInNegativeReviewsPercent = 2;
    private int incorrectDelivered = 1;

    public int getUsefulCommentsInNegativeReviewsPercent(){
        return UsefulCommentsInNegativeReviewsPercent;
    }

    public int getUnusefulCommentsInNegativeReviewsPercent(){
        return  UnusefulCommentsInNegativeReviewsPercent;
    }

    public  int getIncorrectDelivered(){
        return  incorrectDelivered;
    }



    public void seperateRandomSentences() throws IOException {
        PrintWriter file = null;
        file = new PrintWriter(new BufferedWriter(new FileWriter("F:\\my_project\\Recommender\\src\\" + "100negativeReviews.txt")));

        InputStream inputFile = getClass().getResourceAsStream("negativeReviews.txt");
        BufferedReader readFile = new BufferedReader(new InputStreamReader(inputFile));
        String file_line;
        int counter =0;
        try {
            while ((file_line = readFile.readLine()) != null && counter<100) {
                file.println(file_line);
                counter++;
                // System.out.println(related);

            }
            file.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void drawchart(){
        launch();
    }
    @Override public void start(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("rates");
        stage.setWidth(500);
        stage.setHeight(500);
        //   final Label caption = new Label("");



        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        //  new PieChart.Data("overall = 0", 13),
                        new PieChart.Data("UsefulData", 97),
                        new PieChart.Data("UnusefulData", 2),
                        new PieChart.Data("WrongDelivered", 1));


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

    public static void main(String args[]) throws IOException {
        AnalyseReviews n = new AnalyseReviews();
        n.seperateRandomSentences();
        n.drawchart();
    }

}
*/