import java.util.LinkedList;
 
public class PageReplacementAlgorithms {
 
    public static void main(String[] args) throws Exception {
        int physical = 10;
        int virtual = 90;
       
        Memory physicalMemory = new Memory(physical);
        Memory virtualMemory = new Memory(virtual);
       
        for(int i = 0; i < physical; i++) {
            physicalMemory.add(new MemoryPage());
        }
       
        for(int i = 0; i < virtual; i++) {
            virtualMemory.add(new MemoryPage());
        }
 
        LinkedList<Integer> requests = ListsFactory.createIntegerList("requests-3.txt");
       
        FIFO fifo = new FIFO(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
        LRU lru = new LRU(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
        LRUa lrua = new LRUa(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));     
        RAND rand = new RAND(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
        OPT opt = new OPT(PageReplacementAlgorithm.copyMemory(physicalMemory), PageReplacementAlgorithm.copyMemory(virtualMemory));
       
       
        System.out.println("FIFO: " + fifo.run(requests));
        System.out.println("LRU: " + lru.run(requests));
        System.out.println("LRUa: " + lrua.run(requests));
        System.out.println("RAND: " + rand.run(requests));
        System.out.println("OPT: " + opt.run(requests));
       
    }
}