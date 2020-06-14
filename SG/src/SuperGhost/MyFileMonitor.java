package SuperGhost;

public class MyFileMonitor extends AbstractFileMonitor {
	
	private String monitorFilePath;
	private boolean hasCalled = false;

	public MyFileMonitor(String path) throws Exception {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setFilePath(String path) {
		monitorFilePath = path;
		 hasCalled = true;	
	}
		
	 

	@Override
	public String getFilePath() throws IllegalStateException {
		
		return monitorFilePath;
	}

}
