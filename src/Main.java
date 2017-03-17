import java.util.*;

import static java.util.Collection.*;

/**
 * Created by s582060 on 3/8/17.
 */
public class Main {

    public static void main(String args[]){

        System.out.println("Hello World!");
        Main m = new Main();

        Scanner s = new Scanner(System.in);

        int maxGen = s.nextInt();
        //int maxChild = s.nextInt();
        boolean initBatch = true;
        List<ANN> genGroup = new ArrayList<ANN>();
        ANN[] genePool = new ANN[1];

        for (int gen = 0; gen < maxGen; gen++){

            genGroup.clear();

            if (initBatch){
                System.out.println("INITIAL BATCH");
                for (int x = 0; x < 100; x++){
                    genGroup.add(new ANN(1,10));
                }
                initBatch = false;
            }
            else{
                if (genePool.length <= 10){
                    //System.out.println("HORRIBLE ERROR!");
                }
                for (int x = 0; x < 50; x++){
                    if (genePool[x] == null){
                        //System.out.println("THE WINNER " + x + ": null placed in " + x);
                    }
                    else{
                        //System.out.println("THE WINNER " + x + ": " + genePool[x].ToString() + " placed in " + x);
                    }

                    genGroup.add(genePool[x]);
                }
                for (int x = 50; x < 100; x++){
                    //System.out.println("placing new ANN in slot " + x);
                    genGroup.add(new ANN(1,10));
                }
            }

            Collections.sort(genGroup);
            //System.out.println("ANN 0: "+ genGroup.get(0).ToString()+"\nANN 99"+genGroup.get(99).ToString());
            genePool = new ANN[50];
            for (int x = 0; x < 50; x++){
                genePool[x] = genGroup.get(x+50);
            }


            //System.out.println(genGroup[99].ToString());
            for (int child = 0; child < 100; child++){
                if (genGroup.get(child) == null){
                    //System.out.println("genGroup[" + child + "] does not exist!");
                }
                else{
                    //System.out.println("ANN_" +child+": "+ genGroup.get(child).ToString());

                    //System.out.println(genGroup[child].ToString());
                }

            }
            //System.out.println("\n\nGEN " + gen + "\n");
            float totalDist = 0;
            for (int x = 0; x < 50; x++){
                //System.out.println("winner " + x + " is " + genePool[x].ToString());
                totalDist+=Math.abs(genePool[x].output-5.5);
            }
            System.out.println("GEN " + gen + " had an average distance of " + (totalDist / 50));
            System.out.println("\n\n");


        }

    }

    //USE ANN TO GENERATE AN AVERAGE CALCULATOR
    //ANN
    //GIVEN MIN, MAX
    //RETURNS ESTIMATED AVERAGE
    //GENERATE 100 ANN FROM RANDOM
    //DO THIS 100 TIMES
    //TOP 10 ANNS GET TO STAY

    //WINNER ANNS CREATE 1 BABIES

    //GENES ARE REALLY JUST NUMBERS BETWEEN -10.0f, 10.0f
    //GENERATED RANDOMLY BY GETTING RANDOM NUMBER BETWEEN 0 AND 199
    //SUBTRACT 100
    //GOT -100 to 99
    //DIVIDE BY 10
    //SAVE AS FLOAT
    //PROFIT

    //TO MAKE BABY FROM GENES
    //MODIFY IT BY MULTIPLYING IT BY -1.5 to 1.5
    //DO THIS BY GENERATING A RANDOM NUMBER BETWEEN 0 AND 299
    //SUBTRACT 150
    //GOT -150 to 149
    //DIVIDE BY 100
    //SAVE AS FLOAT
    //PROFIT

}

class ANN implements Comparable<ANN>{

    public float gene1,gene2;
    float output;
    Random rand;

    public ANN(int in1, int in2){

        //RANDOM GENE GENERATION
        rand = new Random();
        gene1 = (rand.nextInt(200)-100)/10.0f;
        gene2 = (rand.nextInt(200)-100)/10.0f;

        //OUTPUT
        output = gene1 * in1 + gene2 * in2;
    }

    public ANN(int in1, int in2, float _g1, float _g2){

        //GENE MUTATION
        rand = new Random();
        gene1 = gene1 * ((rand.nextInt(300)-150)/100);
        gene2 = gene2 * ((rand.nextInt(300)-150)/100);

        //OUTPUT
        output = gene1 * in1 + gene2 * in2;

    }

    public String ToString(){
        String s = "G1: " + gene1 + "  \t G2: " + gene2 + "\t OUT: " + output + "\t DIST: " + Math.abs(output - 5.5f);
        return s;
    }

    //if output - 5.5 < theiroutput - 5.5
    //then  1
    //else
    //then -1

    public int compareTo(ANN other){
        if (Math.abs(this.output - 5.5f) < Math.abs(other.output - 5.5f)){
            return 1;
        }
        else{
            return -1;
        }
    }

}