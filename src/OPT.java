import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

public class OPT {
	Memory physicalMemory;
	Memory virtualMemory;
	int missErrors = 0;
	
	LinkedList<Integer> requests;
	
	public OPT(Memory physicalMemory, Memory virtualMemory) {
		this.physicalMemory = physicalMemory;
		this.virtualMemory = virtualMemory;
		this.requests = new LinkedList<Integer>();
	}
	
	public OPT setRequests(LinkedList<Integer> list) {
		this.requests = list;
		
		return this;
	}
	
	public int run(LinkedList<Integer> requests) {
		missErrors = 0;
		
		setRequests(requests);
		ListIterator<Integer> it = this.requests.listIterator();
		
		int n = 0;
		int current;
		MemoryPage thrownOut;
		MemoryPage page;
		
		while(it.hasNext()) {
			current = it.next();
			
			if(!physicalMemory.isInMemory(current)) {
				missErrors++;
				//System.out.println(current + " out of physical memory; missErrors = " + missErrors);
				
				try {
					thrownOut = throwOutPage(n);
					page = virtualMemory.getById(current);
					
					if(page instanceof NoMemoryPage) {
						throw new PageDoesntExistException();
					} else {
						Date currentTime = new Date();
						page.locatedInMemoryTime = currentTime.getTime();
						physicalMemory.add(page);
						virtualMemory.remove(page);
						virtualMemory.add(thrownOut);
					}
				} catch(PageDoesntExistException pdee) {
					System.out.println("Page #" + current + " doesn't exist in any memory. Possible incorrect result of simulation.");
				}
			} else {
				//System.out.println(current + " in physical memory.");
			}
			
			Date currentTime = new Date();
			
			page = physicalMemory.getById(current);
			page.lastRequestTime = currentTime.getTime();
			
			n++;
		}
		
		return missErrors;
	}

	protected MemoryPage throwOutPage(int requestId) {
		ListIterator<MemoryPage> it;
		ListIterator<Integer> requestIt = requests.listIterator(requestId);
		
		int foundId;
		int currentRequestId;
		MemoryPage unnecessary;
		MemoryPage current;
		
		Memory physicalMemoryClone = PageReplacementAlgorithm.copyMemory(physicalMemory);
		
		ArrayList<MemoryPage> necessaryPages = new ArrayList<MemoryPage>();
		int length = physicalMemory.size() - 1;
		
		while(requestIt.hasNext() && necessaryPages.size() < length) {
			currentRequestId = requestIt.next();
			it = physicalMemoryClone.listIterator();
			
			while(it.hasNext()) {
				current = it.next();
				
				if(current.id == currentRequestId) {
					necessaryPages.add(current);
					physicalMemoryClone.remove(current);
					break;
				}
			}
		}
		
		it = physicalMemoryClone.listIterator();
		unnecessary = it.next();
		foundId = unnecessary.id;
		
		unnecessary = physicalMemory.getById(foundId);
		physicalMemory.remove(unnecessary);
		
		return unnecessary;
	}
}