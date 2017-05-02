import java.util.ArrayList;
import java.util.ListIterator;

public class Memory {
	private int size;
	private ArrayList<MemoryPage> memory;
	
	public Memory(int size) {
		this.size = size;
		memory = new ArrayList<MemoryPage>();
	}
	
	public boolean add(MemoryPage page) {
		if(memory.size() < size) {
			memory.add(page);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isInMemory(int id) {
		return (getById(id) instanceof NoMemoryPage) ? false : true;
	}
	
	public MemoryPage get(int index) {
		return memory.get(index);
	}
	
	public MemoryPage getById(int id) {
		ListIterator<MemoryPage> it = memory.listIterator();
		
		MemoryPage current;
		MemoryPage found = new NoMemoryPage();
		
		while(it.hasNext()) {
			current = it.next();
			
			if(current.id == id) {
				found = current;
				break;
			}
		}
		
		return found;
	}
	
	public int size() {
		return this.size;
	}
	
	public Memory remove(MemoryPage page) {
		memory.remove(page);
		
		return this;
	}
	
	public ListIterator<MemoryPage> listIterator() {
		return memory.listIterator();
	}
}
