package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {
	@SerializedName("Id")
	@Expose
	private int id;
	@SerializedName("Size")
	@Expose
	private int size;
	@SerializedName("Description")
	@Expose
	private String description;
	
	public Room(int id, int size, String description)
	{
		this.id = id;
		this.size = size;
		this.description = description;
	}
	
	public int getId()
	{
		return id;
	}
	public int getSize()
	{
		return size;
	}
	public String getDescription()
	{
		return description;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
