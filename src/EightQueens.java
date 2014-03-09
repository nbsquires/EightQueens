/**
 * @file EightQueens.java
 * @author nsquires
 * Solves the eight queens problem using various AI techniques
 */
import java.util.*;
import java.text.NumberFormat;

public class EightQueens {
	
	public EightQueens(){
	}
	
	public static void main(String[] args){
		EightQueens board = new EightQueens();
		int numberOfRuns = 2000;
		int hillClimbNodes=0, randomRestartNodes=0, annealNodes=0;
		int hillClimbSuccesses=0, randomRestartSuccesses=0, annealSuccesses=0;
		
		for(int i=0; i<numberOfRuns; i++){
			Queen[] startBoard = board.generateBoard();
			//Node test = new Node();
			//test.setState(startBoard);
			//System.out.println(test);
			
			HillClimbing hillClimber = new HillClimbing(startBoard);
			RandomRestart randomRestart = new RandomRestart(startBoard);
			SimulatedAnnealing anneal = new SimulatedAnnealing(startBoard);			
			
			Node hillSolved = hillClimber.hillClimbing();
			Node randomSolved = randomRestart.randomRestart();
			Node annealSolved = anneal.simulatedAnneal(28, 0.0001);
			
			if(hillSolved.getHeuristic()==0){
				//System.out.println("Hill Climbing Solved:\n"+hillSolved);
				hillClimbSuccesses++;
			}
			if(randomSolved.getHeuristic()==0){
				//System.out.println("Random Restart Solved:\n"+randomSolved);
				randomRestartSuccesses++;
			}
			if(annealSolved.getHeuristic()==0){
				//System.out.println("Anneal Solved:\n"+annealSolved);
				annealSuccesses++;
			}
			
			hillClimbNodes += hillClimber.getNodesGenerated();
			randomRestartNodes += randomRestart.getNodesGenerated();
			annealNodes += anneal.getNodesGenerated();
		}
		
		System.out.println("Hill climb successes: "+hillClimbSuccesses);
		System.out.println("Random restart successes: "+randomRestartSuccesses);
		System.out.println("Simulated Annealing successes: "+annealSuccesses);
		System.out.println();
		
		double hillClimbPercent = (double)hillClimbSuccesses/(double)numberOfRuns;
		System.out.println(hillClimbPercent);
		double randomRestartPercent = (double)(randomRestartSuccesses/numberOfRuns);
		double annealPercent = (double)(annealSuccesses/numberOfRuns);
		NumberFormat fmt = NumberFormat.getPercentInstance();
		
		System.out.println("Hill climbing:\nNodes: "+hillClimbNodes);
		System.out.println("Percent successes: "+fmt.format(hillClimbPercent));
		System.out.println("Random Restart:\nNodes: "+randomRestartNodes);
		System.out.println("Percent successes: "+fmt.format(randomRestartPercent));
		System.out.println("Simulated Annealing:\nNodes: "+annealNodes);
		System.out.println("Percent successes: "+fmt.format(annealPercent));
	}
	
	/**
	 * The starter board
	 * @return Queen[]
	 */
	public Queen[] generateBoard(){
		Queen[] start = new Queen[8];
		Random gen = new Random();
		
		for(int i=0; i<8; i++){
			start[i] = new Queen(gen.nextInt(8),i);
		}
		return start;
	}
}
