/*
Base64 Encoder/Decoder of Ascii Charecters in Java By Sravan
Date 19-10-2015
Usage: java Base64e_d -option "text"
options:
-e	encode the text supplied
-d	decode the text supplied
*/
import java.util.List;
import java.util.Arrays;

public class Base64e_d{
	 //global int i;
	public static void main (String[] args)
	{
		
		String s="abcd";
		try
		{
			args[0].equals("cool code");
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			Usage();
			return ;
		}
		if(args[0].equals("-e"))
		{
			String res_enc=Base64_Converter(args[1]);
			System.out.println(res_enc);
		}
		else if(args[0].equals("-d"))
		{
			String res_dec=Base64_Decoder(args[1]);
			System.out.println(res_dec);
		}
		else
		{
			Usage();
		}
		//System.out.println(s);
		//Ascii_Binary();
		
	}

	private static void Usage()
	{
			System.out.println("=======Coded by Sravan=====");
			System.out.println("Usage: java Base64e_d -option text");
			System.out.println("Options:");
			System.out.println("-e	encode the text supplied");
			System.out.println("-d	decode the text supplied");
			System.out.println("===========================");
	}
	
	private static int Ascii_Converter(char letter)
	{
		//char to Ascii Converter
		
		int ascii_int=(int)letter;
		return ascii_int;
		
		
	}
	
	
	
	private static String  Base64_Converter(String str)
	{
		//Ascii to Base64 Converter 
		int len=str.length();
		String Binarydata="";
		String tmpstr="";
		int i;
		for(i=0;i<len;i++)
		{
			tmpstr=Ascii_Binary(Ascii_Converter(str.charAt(i)));
			Binarydata=Binarydata+tmpstr;
		}
		//System.out.println("Binarydata: "+Binarydata); //Debug
		return Binary_to64(Binarydata);
		
	}
	
	
	private static String Ascii_Binary(int ascii_int)
	{
		//Ascii to Binary Converter
		String ascii_bin1=Integer.toBinaryString(ascii_int);
		String pad="00000000";
		String ascii_bin="";
		int pad_len=8-(ascii_bin1.length()%8);
		if(pad_len!=8)
		{
			ascii_bin=pad.substring(0,pad_len)+""+ascii_bin1;
		}
		else 
		{
			ascii_bin=ascii_bin1;
		}
		return ascii_bin;
		
		
	}
	
	private static String  Base64_Decoder(String str)
	{
		//Base64 to Binary Converter
		String result="";
		String ascii_bin="";
		
		if(str.substring(str.length()-2,str.length()).equals("=="))
		{
			result=str.substring(0,str.length()-2)+"AA";
		}
		else if(str.substring(str.length()-1,str.length()).equals("="))
		{
			result=str.substring(0,str.length()-1)+"A";
		
		}
		else 
		{
			result=str;
		}
		int i;
		for (i=0;i<result.length();i++)
		{
			ascii_bin=ascii_bin+Asciib64_Binary(Base64_Ascii(result.charAt(i)));
		}
		//System.out.println(ascii_bin); //Debug
		String ascii_string_decoded=b64__to_ascii_build(ascii_bin);
		
		
		return ascii_string_decoded;
	}
	
	private static int Base64_Ascii(char b64_char)
	{
		//Convert b64 char to ascii
		String tmpb64;
		tmpb64=b64_char+"";
		List<String> Base64_table=Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9","+","/");
		int ascii_int=Base64_table.indexOf(tmpb64);
		
		//System.out.println("b64_char is "+tmpb64+" and ascii_int is :"+ascii_int); //Debug
		return ascii_int;
	}
	
	private static String Asciib64_Binary(int ascii_int)
	{
		String binary_b64_str=Integer.toBinaryString(ascii_int);
		String pad="00000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		String ascii_binary_part="";
		int pad_len=6-(binary_b64_str.length()%6);
		if(pad_len!=6)
		{
			ascii_binary_part=pad.substring(0,pad_len)+binary_b64_str;
		}
		else
		{
			ascii_binary_part=binary_b64_str;
		}
		//System.out.println("ascii_int is "+ascii_int +"and pad_len is "+pad_len+"ascii_binary_part is "+ascii_binary_part );		//Debug
		//b64__to_ascii_build(ascii_binary_part);
		return ascii_binary_part;
	}	
	
	
	private static String b64__to_ascii_build(String ascii_bin)
	{
		//String ascii_trim_tail0=ascii_bin.
		String bin_str=ascii_bin;
		int i=0;
		String res_ascii="";
		for(i=0;i<bin_str.length();i=i+8)
		{
			//System.out.println(i); //Debug
			try {
				bin_str.substring(i,i+8);
				
			}
			catch(StringIndexOutOfBoundsException e)
			{
				System.out.println("Looks like invalid base64!");
				return "";
			}
			res_ascii=res_ascii+Binary_Ascii_Decode(bin_str.substring(i,i+8));
		
		}
		//System.out.println(res_ascii); //Debug
		return res_ascii;
		
	}
	
	private static char Int_Ascii(int ascii_int)
	{
		//Ascii Integer to char Converter
		char ascii_char=(char)ascii_int;
		
		//System.out.println("In Int_Ascii" +ascii_char); //Debug
		//System.out.println(ascii_char);  //Debug
		return ascii_char;
	}
	
	
	private static char Ascii_Base64(int ascii_int)
	{
		//Function to look up base64 char for Ascii Integer
		char Base64_table[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'};
		
		return Base64_table[ascii_int];
		
	}
	
	private static String Binary_Ascii_Decode(String str)
	{
		int ascii_int=Integer.parseInt(str,2);
		char ascii_char=(char) ascii_int;
		return ascii_char+"";
	}
	
	private static String  Binary_Ascii(String str)
	{
		//Binary to Ascii Converter
		int ascii_int=Integer.parseInt(str,2);
		//System.out.println("Binary_Ascii  ascii_int:"+ascii_int); //Debug
		char ascii_char=Ascii_Base64(ascii_int);
		return ascii_char+"";
	}
	
	private static String Binary_to64(String str)
	{
		
		//Binary to b64 Converter
		int len=str.length();
		int byte_pad=0;
		if((len%24)!=0)
		{
		 byte_pad=24-(len%24);
		}
		//System.out.println("Padded Bytes:" +byte_pad); //Debug
			String pad="00000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		String str_b64_padded=str+""+pad.substring(0,byte_pad);
		String res_b64="";	
		int i;
		String tmpbinstr="";
		for(i=0;i<str_b64_padded.length();i=i+6)
		{
			tmpbinstr=str_b64_padded.substring(i,i+6);
			//System.out.println("Binary_to64 tmpbinstr"+tmpbinstr); //Debug
			res_b64=res_b64+""+Binary_Ascii(tmpbinstr);
			
		}
	
		//return res_b64;
		//Fix the "=" concat  logic
		String result="";
		if(res_b64.substring(res_b64.length()-2,res_b64.length()).equals("AA"))
		{
			result=res_b64.substring(0,res_b64.length()-2)+"==";
		}
		else if(res_b64.substring(res_b64.length()-1,res_b64.length()).equals("A"))
		{
			result=res_b64.substring(0,res_b64.length()-1)+"=";
		
		}
		else 
		{
			result=res_b64;
		}
		return result;
	}
}

