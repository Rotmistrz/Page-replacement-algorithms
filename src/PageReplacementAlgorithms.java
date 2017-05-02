import java.util.LinkedList;

public class PageReplacementAlgorithms {

	public static void main(String[] args) throws Exception {
		Memory physicalMemory = new Memory(20);
		Memory virtualMemory = new Memory(40);
		
		for(int i = 0; i < 20; i++) {
			physicalMemory.add(new MemoryPage());
		}
		
		for(int i = 0; i < 40; i++) {
			virtualMemory.add(new MemoryPage());
		}

			LinkedList<Integer> requests = ListsFactory.createIntegerList("requests-2.txt");
			
			FIFO fifo = new FIFO(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
			LRU lru = new LRU(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
			RAND rand = new RAND(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
			
			System.out.println("FIFO: " + fifo.run(requests));
			System.out.println("LRU: " + lru.run(requests));
			System.out.println("RAND: " + rand.run(requests));
		
	}
}
