

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
//import org.json.JSONArray;



public class Parser {

    public Vector<String> asin = new Vector<>();
    private Vector<String> title = new Vector<>();
    private Vector<Double> price = new Vector<>();
    private Vector<Vector<String>> related = new Vector<>();
    private Vector<String> brand = new Vector<>();
    public Vector<String> category = new Vector<>();
    private Vector<String> description = new Vector<>();

    public Vector<String> getAsin2() {
        return asin2;
    }

    public Vector<Double> getOverall() {
        return overall;
    }

    public Vector<String> asin2 = new Vector<>();
    private Vector<String> reviewer_ID = new Vector<>();
    private  Vector<String> reviewer_Name = new Vector<>();
    private Vector<Long> helpFul =new Vector<>();
    public Vector<String>  reviews =new Vector<>();
    public Vector<Double>  overall =new Vector<>();
    private Vector<String> summeryVec =new Vector<>();

    private HashMap<String, String> helpFulDic = new HashMap<>();
    public HashMap<String, String> reviewsDic = new HashMap<>();
    private HashMap<String, String> overallDic = new HashMap<>();
    private HashMap<String, String> summeryDic = new HashMap<>();


    public void readMetaData() throws Exception {
        InputStream inputFile = getClass().getResourceAsStream("meta.strict");
        BufferedReader readFile = new BufferedReader(new InputStreamReader(inputFile));
        String file_line;

        try {
            while ((file_line = readFile.readLine()) != null) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = new JSONArray();

            Object object = jsonParser.parse(file_line);
            JSONObject jsonObject = (JSONObject) object;
          //   JSONObject jsonarrObject = (JSONObject) object;

                String Asin = (String) jsonObject.get("asin");
                asin.add(Asin);

            String brnd = (String)jsonObject.get("brand");
            brand.add(brnd);
            Double pric =  (Double)jsonObject.get("price");
            price.add(pric);
            String  tit =(String )jsonObject.get("title");
            title.add(tit);

            String descript =  (String) jsonObject.get("description");
            description.add(descript);

                JSONArray Category= (JSONArray) jsonObject.get("categories");
                String categories= Category.toJSONString();
                category.add(categories);


                JSONObject Related =(JSONObject) jsonObject.get("related") ;
                if(file_line.contains("also_viewed")){
                JSONArray also_viewed =(JSONArray) Related.get("also_viewed");
                 Vector<String> also_Viewed = new Vector<>();
                for (int i = 0; i < also_viewed.size(); i++) {
                    String jsonObject1 = (String) also_viewed.get(i);//3
                //    JSONObject jo = (JSONObject)jsonObject1.get(0);

                  also_Viewed.add(jsonObject1);

                }
                related.add(also_Viewed);
                }
                else{
                    related.add(null);
                }

               // System.out.println(related);

                }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readReviewData(String file) throws Exception {
        InputStream inputFile = getClass().getResourceAsStream(file);
        BufferedReader readFile = new BufferedReader(new InputStreamReader(inputFile));
        String file_line;

        PrintWriter reviewsfile = null;
        reviewsfile = new PrintWriter(new BufferedWriter(new FileWriter("/home/atie/my_project/Recommender/src/" + "reviews.txt")));

int x =0;
        try {
            while ((file_line = readFile.readLine()) != null ) {
             //   file_line = file_line.replace('\t',' ');
                JSONParser jsonParser = new JSONParser();
                JSONArray jsonArray = new JSONArray();

                Object object = jsonParser.parse(file_line);
                JSONObject jsonObject = (JSONObject) object;
                //   JSONObject jsonarrObject = (JSONObject) object;

                String Asin = (String) jsonObject.get("asin");
                asin2.add(Asin);

                String reviewerID = (String)jsonObject.get("reviewerID");
                reviewer_ID.add(reviewerID);

                String reviewerName  =  (String) jsonObject.get("reviewerName");
                reviewer_Name.add(reviewerName );

                String  reviewText  =(String )jsonObject.get("reviewText");
              //  reviews.add(reviewText);
              //    String y = x+ " " + Asin;
                   reviewsDic.putIfAbsent(Asin,reviewText);
              //  reviewsDic.put(Asin,reviewText);

                Double overal = (Double)jsonObject.get("overall") ;

                overall.add(overal);
                reviewsfile.println(Asin + "\t" + overal+ "\t" + reviewText);
                String summary  =  (String) jsonObject.get("summary");
                summeryVec.add(summary);

                JSONArray helpful = (JSONArray) jsonObject.get("helpful") ;
                Vector<String> hf = new Vector<>();
                for (int i = 0; i < helpful.size(); i++) {
                    long jsonObject1 = (long) helpful.get(i);//3
                    helpFul.add(jsonObject1);
                 //   System.out.println(jsonObject1);
                    x++;
                }




            }

         //   System.out.println(reviewsDic.size());


        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        reviewsfile.close();
     //   System.out.println("asinsize");
     //   System.out.println(title.size());
     //   System.out.println(overall.size());
     //   System.out.println(asin.size());

    }


    public  String  getAsin(String id){
      int number =  asin.indexOf(id);
      Vector rel = related.get(number);
      if ( rel != null){
          String s = (String)rel.get(0);
          return s;
      }
      else
           return "asdsfdg";

    }



    public static void main(String args[]) throws Exception {
            Parser m=new Parser();

            m.readMetaData();
     //   m.readReviewData("reviewData.strict");
            m.readReviewData("shortCopy1reviewData.strict");
        System.out.println("sa;adsfgf");
        m.readReviewData("shortCopy2reviewData.strict");
        m.readReviewData("shortCopy3reviewData.strict");
      //  m.readReviewData("shortCopy1reviewData.strict");



        }
    }

