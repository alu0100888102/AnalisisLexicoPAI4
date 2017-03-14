package Exports;
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
		tablaSimbolos.put("&","BIT_AND");
		tablaSimbolos.put("|","BIT_OR");
		tablaSimbolos.put("^","BIT_XOR");
		tablaSimbolos.put("~","BIT_COMPLIMENT");
		tablaSimbolos.put("&&","LOGI_CAND");
		tablaSimbolos.put("||","LOGI_COR");
		tablaSimbolos.put("!","LOGIC_NOT");
		tablaSimbolos.put("<","LESS_THAN");
		tablaSimbolos.put(">","GREATER_THAN");
		tablaSimbolos.put("<=","LESS_OR_EQTHAN");
		tablaSimbolos.put(">=","GT_OR_EQ_THAN");
		tablaSimbolos.put("<<","LEFT_SHIFT");
		tablaSimbolos.put(">>","RIGHT_SHIFT");
		tablaSimbolos.put(">>>","Z_RIGHT_SHIFT");
		tablaSimbolos.put("=","ASSIGN");
		tablaSimbolos.put("?","CONDITIONAL");
		tablaSimbolos.put(":","CONDITIONAL");
		tablaSimbolos.put("++","INCREMENT");
		tablaSimbolos.put("--","DECREMENT");
		tablaSimbolos.put("==","EQUAL_TO");
		tablaSimbolos.put("+=","ADD_ASSGN");
		tablaSimbolos.put("-=","SUB_ASSIGN");
		tablaSimbolos.put("*=","MULT_ASSIGN");
		tablaSimbolos.put("/=","DIV_ASSIGN");
		tablaSimbolos.put("%=","MOD_ASSIGN");
		tablaSimbolos.put("&=","BIT_AND_ASSIGN");
		tablaSimbolos.put("|=","BIT_OR_ASSIGN");
		tablaSimbolos.put("^=","BIT_XOR_ASSIGN");
		tablaSimbolos.put("!=","NOT_EQUAL_TO");
		tablaSimbolos.put("<<=","LEFT_SHIFT_ASSIGN");
		tablaSimbolos.put(">>=","RIGHT_SHIFT_ASSIGN");
		tablaSimbolos.put(">>>=","Z_RIGHT_SHIFT_ASSIGN");
		tablaSimbolos.put("\"","QUOTE");
		tablaSimbolos.put("'","SINGLE_QUOTE");
		tablaSimbolos.put("/*","COMMENT");
		tablaSimbolos.put("/**","COMMENT");
		tablaSimbolos.put("//","LINE_COMMENT");
		tablaSimbolos.put("*/","END_COMMENT");
	}
	
	public String getToken(String word){
		if(word == null)
			return "TOKEN_ERROR";
		if((word.endsWith("\"") || word.endsWith("'")) && word.length() >1)
			return "STRING";
		if(word.startsWith("\"" ) || word.startsWith("'" ))
			return "UNFINISHED_STRING";
		if(word.matches("^([+-]?\\d+)$"))
			return "INT";
		if(word.matches("^([+-]?\\d+\\.\\d+)$"))
			return "FLOAT";
		if(!tablaSimbolos.containsKey(word))
			if(word.matches("^(\\w[a-zA-Z0-9_]*)$"))
				return "ID";
			else
				return "TOKEN_ERROR";
		return tablaSimbolos.get(word);
	}
}
