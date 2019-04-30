import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Recommander {
    Parser parser = new Parser();


    public void commentReciever() throws Exception {
        parser.readMetaData();

        System.out.println("please enter the ID-Product");
        Scanner scanner = new Scanner(System. in);
        String input = scanner. nextLine();
        String product_ID = input;
        parser.readReviewData( ("shortCopy1reviewData.strict"));
                System.out.println("Now write your comment please");
        Scanner scanner2 = new Scanner(System. in);
        String comment = scanner. nextLine();
        String recommendedProduct =randomRecommender(product_ID);
        System.out.println("we found a product that you might like, the product ID is:");
        System.out.println(recommendedProduct);
    }
    private String randomRecommender(String id) throws Exception {

       String  suggest = parser.getAsin(id);
        return  suggest;

    }
    public  void evaluateRecommender() throws Exception {
        parser.readMetaData();
     InputStream inputFile = getClass().getResourceAsStream("TestFile.txt");
    BufferedReader readFile = new BufferedReader(new InputStreamReader(inputFile));
    String file_line;
    Scanner scanner= new Scanner(System.in);
    int total=0;
    int correctRecommend=0;
    int wrongRecommends=0;
    try {
        while ((file_line = readFile.readLine()) != null) {
            String[] lines1 = file_line.split("%%&%%");
           String[] lines = lines1[0].split("    ");

            String product_ID = lines[0];
         //   parser.readReviewData( ("shortCopy1reviewData.strict"));
            System.out.println("review about"+ lines[0]+ "is:");
            String comment = lines1[1];
            System.out.println(comment);
            String recommendedProduct =randomRecommender(product_ID);
            System.out.println("the product that system found is:");
            System.out.println(recommendedProduct);
          //  System.out.println(lines[1]);
            String[]  lines2 = lines[1].split("  ");

            for(int j=0; j<lines2.length;j++){
            if(lines2[j].equals(recommendedProduct)){

            correctRecommend = correctRecommend+1;}
            else  if(!lines2[j].equals(recommendedProduct)){
                wrongRecommends = wrongRecommends + 1;}
                total = total+1;

        }}
        System.out.println("recall oF the system is:");
        System.out.println(correctRecommend/total);
    } catch (FileNotFoundException fe) {
        fe.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }

}


    public static void main(String[] args) throws Exception {
      Recommander recommander = new Recommander();
      // recommander.commentReciever();
        recommander.evaluateRecommender();

    }
}
