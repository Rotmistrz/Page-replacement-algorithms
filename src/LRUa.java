import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
 
public class LRUa extends PageReplacementAlgorithm {
 
    public LRUa(Memory physicalMemory, Memory virtualMemory) {
        super(physicalMemory, virtualMemory);
    }
 
    @Override
    protected MemoryPage throwOutPage() {
       
    	return physicalMemory.poll();
    }
}