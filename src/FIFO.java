import java.util.LinkedList;
import java.util.ListIterator;

public class FIFO extends PageReplacementAlgorithm {

	public FIFO(Memory physicalMemory, Memory virtualMemory) {
		super(physicalMemory, virtualMemory);
	}

	@Override
	protected MemoryPage throwOutPage() {
		ListIterator<MemoryPage> it = physicalMemory.listIterator();
		
		MemoryPage oldest = it.next();
		MemoryPage current;
		
		while(it.hasNext()) {
			current = it.next();
			
			if(current.locatedInMemoryTime < oldest.locatedInMemoryTime) {
				oldest = current;
			}
		}
		
		physicalMemory.remove(oldest);
		
		return oldest;
	}

}
