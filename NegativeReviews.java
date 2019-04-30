
import java.io.*;
import java.util.HashMap;
import java.util.Vector;

//import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class NegativeReviews {
    private Parser parser = new Parser();
    private  DrawCharts drawCharts= new DrawCharts();

    private Vector<String> negativeReviews = new Vector<>();
    private Vector<String > negativePoints = new Vector<>();
    private Vector<String> ids = new Vector<>();

    public void seperateNegativeComments() throws Exception {
        Vector overall = parser.getOverall();
        int counterPoint0 = 0;
        int counterPoint1 = 0;
        int counterPoint2 = 0;
        int counterPoint3 = 0;
        int counterPoint4 = 0;
        int counterPoint5 = 0;

//        parser.readReviewData( "shortCopy1reviewData.strict");
  //      parser.readReviewData( "shortCopy2reviewData.strict");
    //    parser.readReviewData( "shortCopy3reviewData.strict");
        InputStream inputFile = getClass().getResourceAsStream("reviews.txt");
        BufferedReader readFile = new BufferedReader(new InputStreamReader(inputFile));
        String file_line;

        try {
            while ((file_line = readFile.readLine()) != null) {
             String[] lines = file_line.split("\t");
            String  id=(lines[0]);
            String point =(lines[1]);
            String review ="";
            for(int j = 2; j<lines.length;j++){
                review = review+lines[j];
            }

                 //   System.out.println(parser.reviewsDic.get(parser.getAsin2().get(i)));
                //    String review =(parser.reviewsDic.get(parser.getAsin2().get(i)));
                    if (point.contains("3")) {
                        counterPoint3++;
                    } else if (point.contains("4")) {
                        counterPoint4++;
                    } else if (point.contains("5")) {
                        counterPoint5++;
                    }
                    else if (point.contains("0")||point.contains("1")||point.contains("2") ) {
                  ids.add(id);
                  negativeReviews.add(review);
                  negativePoints.add(point);

                    }


            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



        int total = counterPoint0 + counterPoint1 + counterPoint2 + counterPoint3 + counterPoint4 + counterPoint5;
        System.out.println(counterPoint0);
        System.out.println(counterPoint1);
        System.out.println(counterPoint2);
        System.out.println(counterPoint3);
        System.out.println(counterPoint4);
        System.out.println(counterPoint5);
        System.out.println(total);
        System.out.println(negativePoints);
        writeInFiles();

        drawCharts.drawchart();

    }

  public void writeInFiles() throws IOException {
      PrintWriter negativeReviewsFile = null;
      negativeReviewsFile = new PrintWriter(new BufferedWriter(new FileWriter("/home/atie/my_project/Recommender/src/"  + "negativeReviews.txt")));
      for(int i =0 ;  i < negativeReviews.size() ; i++){
          String id = ids.get(i);
          String review = negativeReviews.get(i);
      negativeReviewsFile.print(id);
      negativeReviewsFile.print("\t");
      negativeReviewsFile.println(review);

      }

//System.out.println(ids);

  }


   public static void main(String args[]) throws Exception {
       NegativeReviews n = new NegativeReviews();
       n.seperateNegativeComments();
   }
}

