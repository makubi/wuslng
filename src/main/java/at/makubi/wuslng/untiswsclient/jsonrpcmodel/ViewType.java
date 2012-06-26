package at.makubi.wuslng.untiswsclient.jsonrpcmodel;

public abstract class ViewType {
	
	private int id;
	private String name;
	private String longName;
	private String foreColor;
	private String backColor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getForeColor() {
		return foreColor;
	}
	public void setForeColor(String foreColor) {
		this.foreColor = foreColor;
	}
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
}
