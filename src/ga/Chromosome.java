package ga;

import java.util.BitSet;
import java.util.Random;
import java.lang.Math;

public class Chromosome implements Comparable<Chromosome>{

    private FINAL int size = 16;
    private BitSet genes;
    private double fitness;

    public Chromosome(){

        Random rand = new Random();

        this.genes = BitSet(this.size);


        for(int i = 0; i < this.size; i++){

            boolean bitValue;

            if(Random.nextDouble() > 0.5)
                bitValue = true;
            else
                bitValue = false;

            this.genes.set(i, bitValue);
        }

        this.fitness = this.calcFitness();
    }

    public Chromosome(Bitset genes){
        this.genes = genes;
        this.fitness = this.calcFitness();
    }

    public double getFitness(){
        return this.fitness;
    }

    public BitSet getGenes(){
        return this.genes.clone();
    }

    public setBit(int idx, boolean val){
        this.genes.set(idx, val);
    }

    public double getXValue(){
        String str = this.genes.toString().substring(0,7);
        int val = Integer.parseInt(str, 2);
        return (val * 0.0235294) - 3;
    }

    public double getYValue(){
        String str = this.genes.toString().substring(8);
        int val = Integer.parseInt(str, 2);
        return (val * 0.0235294) - 3;
    }

    public double calcFitness(){
        double x = this.getXValue();
        double y = this.getYValue();
        double a, b, c, d;
        return Math.pow((1 - x), 2) * Math.pow(Math.e, (-Math.pow(x, 2) - Math.pow(y+1, 2))) - (x - Math.pow(x, 3) - Math.pow(y, 3)) * Math.pow(Math.e, (-Math.pow(x,2) - Math.pow(y,2)));
    }

    public void crossover(Chromosome mate){
        Random rand = new Random();
        BitSet mateCpy = mate.getGenes();
        BitSet myCpy = this.getGenes();

        int idx = rand.nextInt(size);       

        for(int i = idx; i < this.size; i++){
            this.setBit(i, mateCpy.get(i));
            mate.setBit(i, myCpy.get(i));
        }
    }

    public void mutate(){
        int idx = new Random().nextInt(this.size);
        this.genes.flip(idx);
    }

    @Override public int compareTo(Chromosome c){
        return Double.compare(this.fitness, c.getFitness);
    }

    public String toString(){
        return genes.toString();
    }


}