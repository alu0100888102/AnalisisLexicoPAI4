package Exports;
/**
 * Esta clase tiene sirve para contener los tokens.
 * 
 * @author alu0100888102
 * @version 1.0
 * Ángel Hamilton Lopez
 * alu0100888102@ull.es
 */
public class AlToken {
	int line;
	int pos;
	String token;
	String text;
	
	public AlToken(){}
	public AlToken(int l, int p, String to, String te){
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
	
	 @Override
	public boolean equals(Object o){
		if(o == this)
			return true;
		if(o instanceof AlToken){
			AlToken t = (AlToken)o;
			if(t.getLine()!= this.line)
				return false;
			if(t.getPos() != this.pos)
				return false;
			if(t.getText() != this.text)
				return false;
			if(t.getToken() != this.token)
				return false;
			return true;
		}
		return false;
	} 
}
