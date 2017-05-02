
public class MemoryPage {
	private static int I = 1;
	
	public int id;
	public long lastRequestTime;
	public long locatedInMemoryTime;
	
	public MemoryPage() {
		this.id = I++;
		this.lastRequestTime = 0;
		this.locatedInMemoryTime = 0;
	}
}
