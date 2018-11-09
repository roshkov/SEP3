package common;

public class Package {
public static final String GET = "GET";
public static final String ADD = "ADD";

private String text;

public Package(String string) {
	text = string;
}

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}
}
