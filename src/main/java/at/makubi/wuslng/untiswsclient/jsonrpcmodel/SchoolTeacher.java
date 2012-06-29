package at.makubi.wuslng.untiswsclient.jsonrpcmodel;

public class SchoolTeacher extends ViewType {

	private String foreName;
	private int did;

	public String getForeName() {
		return foreName;
	}
	
	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}
}
