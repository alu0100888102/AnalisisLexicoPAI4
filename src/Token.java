/**
 * Esta clase tiene sirve para contener los tokens.
 * 
 * @author alu0100888102
 * @version 1.0
 * �ngel Hamilton Lopez
 * alu0100888102@ull.es
 */
public class Token {
	int line;
	int pos;
	String token;
	String text;
	
	public Token(){}
	public Token(int l, int p, String to, String te){
		line = l;
		pos = p;
		token = to;
		text = te;
	}
	public void setLine(int l){
		line = l;
	}
	public void setPos(int p){
		pos = p;
	}
	public void setToken(String t){
		token = t;
	}
	public void setText(String t){
		text = t;
	}
	public int getLine(){
		return line;
	}
	public int getPos(){
		return pos;
	}
	public String getToken(){
		return token;
	}
	public String getText(){
		return text;
	}
	
	public String toString(){
		return line + " " + pos + " " + token + " " + text+ " ";
	}
}