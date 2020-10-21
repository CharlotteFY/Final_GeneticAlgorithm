/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;
public class Individual {
    int gene[];
    int fitness;
    public Individual(int n){
        this.gene = new int[n];
    }
    public String toString(){
        StringBuilder sb = new StringBuilder(gene.length);
        for (int i : gene) {
            sb.append(i);
        }
        return sb.toString();
    }
}