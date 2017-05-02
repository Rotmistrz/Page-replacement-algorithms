import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class RAND extends PageReplacementAlgorithm {

	public RAND(Memory physicalMemory, Memory virtualMemory) {
		super(physicalMemory, virtualMemory);
	}

	@Override
	protected MemoryPage throwOutPage() {
		Random rand = new Random();

		int  n = rand.nextInt(physicalMemory.size());

		MemoryPage page = physicalMemory.get(n);
		physicalMemory.remove(page);
		
		return page;
	}

}