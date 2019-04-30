import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.Random;

public class ReadyForRecommend {
    Parser parser = new Parser();
    Vector<String> aspects = new Vector<>();


    public void recommendationList(Vector<String> aspects) throws IOException {
        Vector<String> recommendationList=new Vector<>();
        //felan ino gozashtam chon aspecti nadaram
        int aspect = 0;
        PrintWriter file = null;
        file = new PrintWriter(new BufferedWriter(new FileWriter("/home/atie/my_project/Recommender/src/" + "recommendationList.txt")));
        InputStream inputFile = getClass().getResourceAsStream("RecommendedObjects.txt");
        BufferedReader readFile = new BufferedReader(new InputStreamReader(inputFile));
        String file_line;
        try {
            while ((file_line = readFile.readLine()) != null) {
                String[] line1 = file_line.split("\t");
                String id = line1[0];
                String review = "";
                for (int j = 1; j < line1.length; j++) {
                    review = review + line1[j];
                }

                //inja bayad aspect ro da review pardazesh konim
                if( aspect>0){
                    recommendationList.add(id);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        file.println(recommendationList);
        file.close();


    }










    public void makeListReady(String id) throws Exception {
        parser.readMetaData();
        System.out.println("meta Data Read successfully!:)");
      //  parser.readReviewData("reviewData.strict");
        PrintWriter file = null;
        file = new PrintWriter(new BufferedWriter(new FileWriter("/home/atie/my_project/Recommender/src/" + "RecommendedObjects.txt")));

        int index = parser.asin.indexOf(id);
        Vector<String> asins = new Vector<>();


        String category = parser.category.get(index);
        System.out.println(category);
        for ( int i =0 ; i<parser.category.size() ; i++){
            if (parser.category.get(i).equals(category)){
                asins.add(parser.asin.get(i));

            }
        }

        //choose 10 random objects from the same category


       // System.out.println(asins);
        InputStream inputFile = getClass().getResourceAsStream("reviews.txt");
        BufferedReader readFile = new BufferedReader(new InputStreamReader(inputFile));
        String file_line;
        HashMap<String, String> reviewsDic = new HashMap<>();
        Vector<String> reviews = new Vector<>();

            try {
                while ((file_line = readFile.readLine()) != null) {
                    String[] line1 = file_line.split("\t");
                    id = line1[0];
                    String over = line1[1];
                    String review = "";

                   // Vector<String> reviews = new Vector<>();

                    for (int j = 2; j < line1.length; j++) {
                        review = review + line1[j];
                    }

                    for (int k = 0; k < asins.size(); k++) {
                        if (asins.get(k).equals(id) && (over.equals("4.0") || over.equals("5.0"))) {
                            // reviews.add(review);
                           // reviewsDic.put(id, review);
                            file.println(id + "\t" + review);
                            break;
                        }

                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            file.close();

     }

    public static void main(String[] args) throws Exception {
        ReadyForRecommend r = new ReadyForRecommend();
        r.makeListReady("B008BA33Q4");
    }

}
