import java.util.Random;
import java.util.Scanner;

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
        ANN[] genGroup;
        ANN[] genePool = new ANN[10];

        for (int gen = 0; gen < maxGen; gen++){

            genGroup = new ANN[100];

            if (initBatch){
                for (int x = 0; x < 100; x++){
                    genGroup[x] = new ANN(1,10);
                }
                initBatch = false;
            }
            else{
                for (int x = 0; x < 10; x++){
                    for (int y = 0; y < 10; y++) {
                        //System.out.println("GENNIN " + ((x*10)+y));
                        genGroup[x*10+y] = new ANN(1, 10,genePool[x].gene1,genePool[x].gene2);
                    }
                }
            }

            genePool = new ANN[10];

            for (int child = 0; child < 100; child++){
                System.out.println(genGroup[child].ToString())
                genePool = m.ANNAddIfHigher(genePool, genGroup[child]);
                System.out.println(genGroup[child].ToString());
            }
            System.out.println("\n\nGEN " + gen + "\n");
            for (int x = 0; x < 10; x++){
                System.out.println(genePool[x].ToString());
            }
            System.out.println("\n\n");
        }


    }

    public ANN[] ANNAddIfHigher(ANN[] anns, ANN annInQuestion){
        ANN[] returnList = new ANN[10];
        boolean added = false;
        //System.out.println("\n\n");
        for (int x = 0; x < 10; x++){

            //DEBUG
            /*
            if (anns[x] != null){
                System.out.print("\tORIGINAL" + anns[x].ToString() + " | ");
            }
            else{
                System.out.print("\tORIGINAL null" + " | ");
            }
            */
            //DEBUG

            if (!added){
                if (anns[x] == null){
                    //System.out.print("ADDITION");
                    returnList[x] = annInQuestion;
                    added = true;
                }
                else if (anns[x].output > annInQuestion.output && annInQuestion.output > 5.5f || anns[x].output < annInQuestion.output && annInQuestion.output < 5.5f){
                    //System.out.print("REPLACEMENT");
                    returnList[x] = annInQuestion;
                    added = true;
                }
                else{
                    returnList[x] = anns[x];
                }
            }
            else{
                returnList[x] = anns[x];
            }

            //DEBUG
            /*
            if (returnList[x] != null){
                System.out.print(" | NEW\t" + returnList[x].ToString());
            }
            else{
                System.out.print(" | NEW\tnull");
            }
            System.out.print("\n");
            */
            //DEBUG

        }
        //System.out.println("\n\n");
        return returnList;
    }

    //USE ANN TO GENERATE AN AVERAGE CALCULATOR
    //ANN
    //GIVEN MIN, MAX
    //RETURNS ESTIMATED AVERAGE
    //GENERATE 100 ANN FROM RANDOM
    //DO THIS 100 TIMES
    //TOP 10 ANNS GET TO STAY

    //WINNER ANNS CREATE 10 BABIES

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

class ANN{

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
        String s = "G1: " + gene1 + "  \t G2: " + gene2 + "\t OUT: " + output;
        return s;
    }

}