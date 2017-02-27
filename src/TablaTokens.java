/**
 * Esta clase es una tabla de tokens. sirve para identificar los distintos elementos del lenguaje java.
 * 
 * @author alu0100888102
 * @version 1.0
 * Ángel Hamilton Lopez
 * alu0100888102@ull.es
 */


import java.util.Hashtable;

public class TablaTokens {
	Hashtable<String, String> tablaSimbolos;
	
	public  TablaTokens(){
		tablaSimbolos = new Hashtable<String,String>();
		tablaSimbolos.put("abstract","KWABSTRACT");
		tablaSimbolos.put("assert","KWASSERT");
		tablaSimbolos.put("boolean","KWBOOLEAN");
		tablaSimbolos.put("break","KWBREAK");
		tablaSimbolos.put("byte","KWBYTE");
		tablaSimbolos.put("case","KWCASE");
		tablaSimbolos.put("catch","KWCATCH");
		tablaSimbolos.put("char","KWCHAR");
		tablaSimbolos.put("class","KWCLASS");
		tablaSimbolos.put("const","KWCONST");
		tablaSimbolos.put("continue","KWCONTINUE");
		tablaSimbolos.put("default","KWDEFAULT");
		tablaSimbolos.put("do","KWDO");
		tablaSimbolos.put("double","KWDOUBLE");
		tablaSimbolos.put("else","KWELSE");
		tablaSimbolos.put("extends","KWEXTENDS");
		tablaSimbolos.put("false","KWFALSE");
		tablaSimbolos.put("final","KWFINAL");
		tablaSimbolos.put("finally","KWFINALLY");
		tablaSimbolos.put("float","KWFLOT");
		tablaSimbolos.put("for","KWFOR");
		tablaSimbolos.put("goto","KWGOT");
		tablaSimbolos.put("if","KWIF");
		tablaSimbolos.put("implements","KWIMPLEMENTS");
		tablaSimbolos.put("import","KWIMPORT");
		tablaSimbolos.put("instanceof","KWINSTANCEOF");
		tablaSimbolos.put("int","KWINT");
		tablaSimbolos.put("interface","KWINTERFACE");
		tablaSimbolos.put("long","KWLONG");
		tablaSimbolos.put("native","KWNATIVE");
		tablaSimbolos.put("new","KWNEW");
		tablaSimbolos.put("package","KWPACKAGE");
		tablaSimbolos.put("private","KWPRIVATE");
		tablaSimbolos.put("protected","KWPROTECTED");
		tablaSimbolos.put("public","KWPUBLIC");
		tablaSimbolos.put("return","KWETURN");
		tablaSimbolos.put("short","KWSHORT");
		tablaSimbolos.put("static","KWSTATIC");
		tablaSimbolos.put("super","KWSUPER");
		tablaSimbolos.put("switch","KWSWITCH");
		tablaSimbolos.put("synchronized","KWSYNCHRONIZED");
		tablaSimbolos.put("this","KWTHIS");
		tablaSimbolos.put("throw","KWTHROW");
		tablaSimbolos.put("transient","KWTRANSIENT");
		tablaSimbolos.put("true","KWTRUE");
		tablaSimbolos.put("try","KWTRY");
		tablaSimbolos.put("void","KWVOID");
		tablaSimbolos.put("volatile","KWVOLATILE");
		tablaSimbolos.put("while","KWWHILE");
		tablaSimbolos.put(";","SEMICOLON");
		tablaSimbolos.put(",","COMMA");
		tablaSimbolos.put(".","PERIOD");
		tablaSimbolos.put("(","OPAR");
		tablaSimbolos.put(")","CPAR");
		tablaSimbolos.put("{","OBRACE");
		tablaSimbolos.put("}","CBRACE");
		tablaSimbolos.put("[","OBRACK");
		tablaSimbolos.put("]","CBRACK");
		tablaSimbolos.put("+","ADDITION");
		tablaSimbolos.put("-","SUBTRACTION");
		tablaSimbolos.put("*","MULTIPLICATION");
		tablaSimbolos.put("/","DIVISION");
		tablaSimbolos.put("%","MODULUS");
		tablaSimbolos.put("&","BITAND");
		tablaSimbolos.put("|","BITOR");
		tablaSimbolos.put("^","BITXOR");
		tablaSimbolos.put("~","BITCOMPLIMENT");
		tablaSimbolos.put("&&","LOGICAND");
		tablaSimbolos.put("||","LOGICOR");
		tablaSimbolos.put("!","LOGICNOT");
		tablaSimbolos.put("<","LESSTHAN");
		tablaSimbolos.put(">","GREATERTHAN");
		tablaSimbolos.put("<=","LESSOREQTHAN");
		tablaSimbolos.put(">=","GTOREQTHAN");
		tablaSimbolos.put("<<","LEFTSHIFT");
		tablaSimbolos.put(">>","RIGHTSHIFT");
		tablaSimbolos.put(">>>","ZRIGHTSHIFT");
		tablaSimbolos.put("=","ASSIGN");
		tablaSimbolos.put("?","CONDITIONAL");
		tablaSimbolos.put(":","CONDITIONAL");
		tablaSimbolos.put("++","INCREMENT");
		tablaSimbolos.put("--","DECREMENT");
		tablaSimbolos.put("==","EQUALTO");
		tablaSimbolos.put("+=","ADDASSGN");
		tablaSimbolos.put("-=","SUBASSIGN");
		tablaSimbolos.put("*=","MULTASSIGN");
		tablaSimbolos.put("/=","DIVASSIGN");
		tablaSimbolos.put("%=","MODASSIGN");
		tablaSimbolos.put("&=","BITANDASSIGN");
		tablaSimbolos.put("|=","BITORASSIGN");
		tablaSimbolos.put("^=","BITXORASSIGN");
		tablaSimbolos.put("!=","NOTEQUALTO");
		tablaSimbolos.put("<<=","LEFTSHIFTASSIGN");
		tablaSimbolos.put(">>=","RIGHTSHIFTASSIGN");
		tablaSimbolos.put(">>>=","ZRIGHTSIFTASSIGN");
	}
	
	public String getToken(String word){
		if(!tablaSimbolos.containsKey(word))
			return null;
		return tablaSimbolos.get(word);
	}
}
