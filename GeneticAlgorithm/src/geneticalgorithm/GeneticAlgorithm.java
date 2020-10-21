/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.*;
public class GeneticAlgorithm {
    
    public static void main(String[] args) {
        //add input 
        Scanner scan = new Scanner(System.in);
        
       //EX 
       double mutationRate = 0.02;
       double crossoverRate = 0.8;
       int lchrom = 5;
       int popsize = 100;
       int maxgen = 200;
       int P = 5;
        
       //scan input sizeMatrix,totalaround,station
       int sizeMatrix = scan.nextInt();
       int totalaround = scan.nextInt();
       int station = scan.nextInt();

       //setting
       lchrom = sizeMatrix;
       popsize = sizeMatrix;
       maxgen = totalaround;
       P = station;
       
       //input Matrix
        System.out.println("input_Matrix");
       int inputMatrix[][] = new int[popsize][popsize];
       for(int a =0;a<popsize;a++){
           for(int b =0 ;b<popsize;b++){
               int e = scan.nextInt();
               inputMatrix[a][b] = e;
           }
       }
       //check input
       for(int a =0;a<popsize;a++){
        for(int b =0 ;b<popsize;b++){
            System.out.print(inputMatrix[a][b]+" ");
        }
        System.out.println("");
    }
    //Distance Least in Vertax
    
    int totalDistanceInMatrix[] = new int[popsize];
    
    int max[] = new int[popsize];
    for(int a =0;a<popsize;a++){
        max[a]=Integer.MIN_VALUE; 
        for(int b =0 ;b<popsize;b++){
            totalDistanceInMatrix[b] +=inputMatrix[a][b];
            if(inputMatrix[a][b]>=max[a]){
                max[a] = inputMatrix[a][b];
            }
        }
    }
    for(int b =0 ;b<popsize;b++){
        System.out.println("totalDistanceInMatrix "+totalDistanceInMatrix[b]);      
    }
    
    
    
       //Random Generator for Integer
       Random rand = new Random((long)24);
       //rand.setSeed((long)100);
       //create new population
       Individual population[]= new Individual[popsize];
       for (int i = 0; i < popsize; i++) 
            population[i] = new Individual(lchrom);
      //fills individual's gene with binary randomly
       for (int i = 0; i < popsize; i++) {
           //int checkP= 0;
            for (int j = 0; j < lchrom; j++) {
              // if(checkP<5){
                population[i].gene[j] = (rand.nextDouble() < 0.5) ? 0 : 1;
                   /*if(population[i].gene[j]==1){
                      checkP++;
                    }
               }else{
                   break;
               } */
            }
       }
       //for (int i = 0; i < popsize; i++) {
           //int checkP= 0;
           // for (int j = 0; j < lchrom; j++) {
              // if(checkP<5){
               // System.out.print(population[i].gene[j]);
                   /*if(population[i].gene[j]==1){
                      checkP++;
                    }
               }else{
                   break;
               } */
            //}
            ///System.out.println("geneticalgorithm.GeneticAlgorithm.main()");
       //}
       for(int gcounter=0; gcounter<maxgen; gcounter++){
             
             //evaluate each individual
             for (int i = 0; i < popsize; i++) {
                 population[i].fitness = 0;
                 int Matrix[] = new int[popsize];
                 for (int j = 0; j < lchrom; j++) {
                     if (population[i].gene[j] == 1) {
                         Matrix[j]=1;
                         
                         //
                       population[i].fitness += totalDistanceInMatrix[j];
                         
                     }
                     else if(population[i].gene[j] == 0){
                          Matrix[j]=0;
                     }     
                 }
                 //System.out.println(Matrix[i]);
                 //for(int u =0; u<lchrom ;u++){
                     //System.out.println(updatemartix(inputMatrix,Matrix)[u]);
                     //if(Matrix[u]==1){
                     //population[i].fitness = updateMatrix(inputMatrix,Matrix);
                     //}
                 //}
                 for(int a =0;a<popsize;a++){
        /*for(int b =0 ;b<popsize;b++){
            System.out.print(inputMatrix[a][b]+" ");
        }
        System.out.println("");
    }*/
                 
             }
             /*for(int i = 0 ;i<popsize;i++){
                 int matrix[] = new int[popsize]; 
                 for(int j =0;j<popsize;j++){
                     if(population[i].gene[j]==1){
                         matrix[j]=1;                         
                     }
                     else{
                         matrix[j]=0;
                     }
                    // System.out.print(matrix[j]);
                 }
                 //System.out.println("geneticalgorithm.GeneticAlgorithm.main()");
                 //population[i].fitness = updateMatrix(inputMatrix, matrix);
             }*/
             
             
             }
             
            int min = Integer.MAX_VALUE; 
            // Print Genome  
            int showr= gcounter+1;
            System.out.println("");
            System.out.println(""+showr+" round show population ");
            for(int i=0; i < popsize; i++){ 
                 System.out.println(population[i]);
            }
            // Total Fitness and Average
            System.out.println("");
            System.out.println("value fitness in 1-100");
            getFitness(population, popsize);
            System.out.println("Total fitness " + getTotalFitness(population, popsize));
            System.out.println("Mean fitness " + getMeanFitness(population, popsize));      
            // Create new offspring 
            Individual offsprings[] = new Individual[popsize];
            for (int i = 0; i < popsize; i++) {
                 offsprings[i] = new Individual(lchrom);
            }
            // Tournament Selection
            for (int j=0; j < popsize; j++) {
                int champion = rand.nextInt(popsize);
                int contender = rand.nextInt(popsize);
                if (population[champion].fitness>population[contender].fitness) //>
                     champion = contender;
                for (int k=0; k < lchrom; k++)
                     offsprings[j].gene[k]=population[champion].gene[k]; 
            }
            // One-Point Crossover
            for (int i = 0; i < popsize; i = i + 2) {
               if (rand.nextDouble() < crossoverRate) {
                    int splitPoint = rand.nextInt(lchrom);
                    for (int j = splitPoint; j < lchrom; j++) {
                        int temp = offsprings[i].gene[j];
                        offsprings[i].gene[j] = offsprings[i + 1].gene[j];
                        offsprings[i + 1].gene[j] = temp;
                    }
               }
            }
            // One-Point Mutation 
            for (int i = 0; i < popsize; i++) {
               if ( rand.nextDouble() < mutationRate) {
                   int mutationPoint = rand.nextInt(lchrom);
                   if(offsprings[i].gene[mutationPoint] == 1)
                      offsprings[i].gene[mutationPoint] = 0;
                   else
                      offsprings[i].gene[mutationPoint] = 1;
               }
            }
            // Copy offspring to the next generation   
            for (int i = 0; i < popsize; i++) {
               for( int j = 0; j < lchrom; j++) {
                      population[i].gene[j]=offsprings[i].gene[j]; 
               }
            }
            //check if P < 5 
           /*for (int i = 0; i < popsize; i++) {
           ArrayList<Integer> checkP= new ArrayList<>();
           for(int p = 0;p<P;p++){           
               int randomp = rand.nextInt(lchrom);
                   for(;;){
                       if(checkP.contains(randomp)){
                           randomp = rand.nextInt(lchrom);
                           
                       }
                       else{
                           break;
                       }
                   }
                   checkP.add(randomp);
                   population[i].gene[randomp]=1;    
           }
            for (int j = 0; j < lchrom; j++) {
               for(int p = 0;p<P;p++){           
                   if(!checkP.contains(j)){      
                     population[i].gene[j] = 0;          
                   }
                }      
            }        
           }*/
            
           int amount_choose = P;
           for (int i = 0; i < popsize; i++) {
             ArrayList<Integer> indexOfBase = new ArrayList<>();
             indexOfBase.clear();
            for (int j = 0; j < lchrom; j++) {
                        
                if(population[i].gene[j]== 1){
                      indexOfBase.add(j); 
                     population[i].gene[j]= 0; 
                } 
              }
            Collections.shuffle(indexOfBase);
            
            for(int p=0;p<amount_choose;++p) { 
               // System.out.println(indexOfBase.get(p));
               if(p>=indexOfBase.size()){
                   int randomp = rand.nextInt(lchrom);
                   for(;;){
                       if(indexOfBase.contains(randomp)){
                           randomp = rand.nextInt(lchrom);
                           
                       }
                       else{
                           break;
                       }
                   }
                   population[i].gene[randomp]=1;
                   continue;
               }
                population[i].gene[indexOfBase.get(p)] =1; 
            }
            
           }
           //if(gcounter==maxgen-1){
              //getfinail(population, popsize);
          // }
           
       }
       //for (int i = 0; i < popsize; i++) {
               for( int j = 0; j < lchrom; j++) {
                    if(population[popsize-1].gene[j]==1){
                        int s = j+1;
                        System.out.println("station is "+s+" ");
                    } 
            }
        //}
       // End of Main Loop 
    }
    // End of Main Method 
    public static int getTotalFitness(Individual pop[], int popsize) {
        int totalFitness = 0;
        for (int i = 0; i < popsize; i++) {
            totalFitness = totalFitness + pop[i].fitness;
        }
        return totalFitness;
    }
    public static void getFitness(Individual pop[], int popsize) {
        int min = Integer.MAX_VALUE;
        int getFitness = 0;
        for (int i = 0; i < popsize; i++) {
            getFitness = pop[i].fitness;
            if(getFitness<min){
                min = getFitness;
            }
            System.out.println(getFitness+" ");
        }
        ///////////////////////////////////////
        ///        hhhhhhhhhj             ////
        //System.out.println("min value = " +min);
    }
    /*public static void getfinail(Individual pop[], int popsize) {
        int min = Integer.MAX_VALUE;
        int getFitness = 0;
        for (int i = 0; i < popsize; i++) {
            getFitness = pop[i].fitness;
            if(getFitness<min){
                min = getFitness;
            }
            //System.out.println(getFitness+" ");
        }
        System.out.println("min value = " +min);
        ArrayList<Integer> resufinal = new  ArrayList<>();   
        for(int j = 0 ;j<popsize;j++){              
            if(pop[0].gene[j]==1){
                resufinal.add(j);
            }
        }
        for(int i =0 ;i<resufinal.size();i++){
            System.out.print("Answer "+resufinal.add(i));
        }
    }*/
    public static double getMeanFitness(Individual pop[], int popsize) {
        double meanFitness = getTotalFitness(pop, popsize) / (double) popsize;
        return meanFitness;
    }
    
    public static int updateMatrix(int[][] matrix, int[] gen) {
 
        int[][] newMatrix = new int[matrix.length][matrix.length];
        int[] selected = new int[gen.length];
        int[] result = new int[matrix.length]; 
        int answer = 0;
        for(int a =0;a<matrix.length;a++){
            for(int b =0;b < matrix.length;b++){
                newMatrix[a][b] = matrix[a][b];
            }
        }
        for(int c =0;c < gen.length;c++){
                selected[c] = gen[c];
        }
        //initialize
        for(int i=0;i<result.length;++i) result[i] = 0;
 
        for(int i=0;i<gen.length;++i) { // 5 round
 
            if(gen[i] == 0) continue;
 
            /**
             * 11 12 13 14 15
             * 21 22 23 24 25
             * 31 32 33 34 35
             * 41 42 43 44 45
             * 51 52 53 54 55
             */
 
            for(int k=0;k<gen.length;++k) {
                for(int j=0;j<gen.length;++j) {
                    if(selected[k] == -1) continue;
                    result[k] += newMatrix[j][k];
                }
            }
            selected[i] = -1;
            answer+=result[i];
 
           // print(newMatrix, result);
 
 
            for(int k=0;k<gen.length;++k) {
                newMatrix[i][k] = 0;
                if(selected[k] != -1) {
                    result[k] = 0;
                }
            }
        }
        //for(int i=0;i<newMatrix.length;++i){
            //System.out.print(answer);
            //System.out.print("");
       // }     
        return answer;
 
    }
}

