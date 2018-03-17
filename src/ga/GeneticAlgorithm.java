package ga;

import java.util.Collections;

public class GeneticAlgorithm {

	protected int mPopulationSize;
	protected int mTournamentsSize;
	protected double mCrossoverProb;
	protected double mMutationProb;
	private ArrayList<Chromosome> pop;

	public GeneticAlgorithm(int populationSize,
			int tournamentsSize, double crossoverProb, double mutationProb) {
		mPopulationSize = populationSize;
		mTournamentsSize = tournamentsSize;
		mCrossoverProb = crossoverProb;
		mMutationProb = mutationProb;
		pop = new ArrayList<Chromosome>(mPopulationSize);
		// ...

		createInitialPopulation();
	}

	public void createInitialPopulation() {
		for(int i = 0; i < mPopulationSize; i++){
			pop.add(new Chromosome());
		}
	}

	public void runOneGeneration() {
		ArrayList newPop = new ArrayList(mPopulationSize);
		Random rand = new Random();

		for(int i = 0; i < mPopulationSize; i++){
				Collections.shuffle(pop);
				BitSet winner = (pop.get(0).getFitness() > pop.get(1).getFitness()) ? pop.get(0).getGenes : pop.get(1).getGenes;
				newPop.add(new Chromosome(winner));
				if(i % 2 = 0 && rand.nextDouble() <= mCrossoverProb)
					newPop.get(i).crossover(newPop.get(i-1));
			}

		for(int i = 0; i < newPop.size(); i++){
			if(rand.nextDouble() <= mMutationProb)
				newPop.get(i).mutate();
		}

		this.swapPopulation(newPop);
	}

	public double getAverageFitness() {

		double total = 0;
		for(Chromosome c : this.pop){
			total += c.getFitness();
		}
		return total / this.pop.size();
	}

	public double getBestFitness() {
		// to be implemented; remove 0.0
		double high = Double.NEGATIVE_INFINITY;
		for(Chromosome c : this.pop){
			if(c.getFitness() > high)
				high = c.getFitness()
		}
		return high; 
	}

	public void swapPopulation(ArrayList<Chromosome> newPop){
		pop.clear();
		for(Chromosome c : newPop){
			pop.add(c);
		}
	}

	// other methods to be implemented

}
