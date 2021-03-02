package searchstore.view;

public class StoreDB {
private String storeName;
private String storeTel;
private String storeAddr;
	


	public StoreDB() {
		// TODO Auto-generated constructor stub
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}

	public String getStoreName() {
		return storeName;
	}
	
	public String getStoreTel() {
		return storeTel;
	}
	
	public String getStoreAddr() {
		return storeAddr;
	}
}
