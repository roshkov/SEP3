package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {
	public static final String GET = "GET";
	public static final String RENT = "RENT";
	public static final String EXIT = "EXIT";
	
	@SerializedName("Header")
	@Expose
	private String header;
	@SerializedName("Body")
	@Expose
	private String body;
	@SerializedName("Id")
	@Expose
	private int id;

	public Package(String header) {
		this.header = header;
	}

	public Package(String header, String body) {
		this.header = header;
		this.body = body;
	}

	public Package(String header, int id) {
		this.header = header;
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
