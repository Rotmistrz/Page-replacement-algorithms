import java.util.LinkedList;

public class PageReplacementAlgorithms {

	public static void main(String[] args) throws Exception {
		Memory physicalMemory = new Memory(10);
		Memory virtualMemory = new Memory(20);
		
		for(int i = 0; i < 10; i++) {
			physicalMemory.add(new MemoryPage());
		}
		
		for(int i = 0; i < 20; i++) {
			virtualMemory.add(new MemoryPage());
		}

		LinkedList<Integer> requests = ListsFactory.createIntegerList("requests.txt");
		
		FIFO fifo = new FIFO(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
		LRU lru = new LRU(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
		RAND rand = new RAND(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
		OPT opt = new OPT(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
		
		System.out.println("FIFO: " + fifo.run(requests));
		System.out.println("LRU: " + lru.run(requests));
		System.out.println("RAND: " + rand.run(requests));
		System.out.println("OPT: " + opt.run(requests));
		
	}
}
