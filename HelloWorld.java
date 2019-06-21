import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class HelloWorld {
  public static void main(String[] args) { 
    try{
      FileReader reader = new FileReader("D:\\JavaTest\\pro1\\testjava.txt");
      BufferedReader br = new BufferedReader(reader);
      String str = null;
      while((str = br.readLine())!=null){
        if(str.contains("Record")&&str.contains("EGSNPDPRecord")){
          System.out.println(str.substring(8,9));
        }
        if(str.contains("recordType")){
          System.out.print(str.substring(13,17));
        }
        if(str.contains("servedIMSI")){
          System.out.print("|" + str.substring(13));
        } 
//        if(str.contains("ggsniPBinV4AddressI")){
//          //System.out.print("|" + str.replaceall("[0-9]{3}\.[0-9]{3}\.[0-9]{3}\.[0-9]{3}",str));
//        } 
        if(str.contains("accessPointName")){
          str = str.substring(18);
          String strstr = "0123456789ABCDEF";
          char[] hexs = str.toCharArray();
          byte[] bytes = new byte[str.length()/2];
          int n;
          for(int i = 0; i < bytes.length; i++){
            n = strstr.indexOf(hexs[2*i])*16;
            n += strstr.indexOf(hexs[2*i+1]);
            bytes[i] = (byte)(n & 0xff);
          }
          System.out.print("|" + new String(bytes));
        } 
        
        if(str.contains("End")){
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          System.out.println("|" + df.format(new Date()));
          System.out.println("90: " );
        }
      }
      br.close();
      reader.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}
/*          
if(str == null || str.equals("")){
  str = null;
}
str = str.replace(" ","");
byte[] baKeyword = new byte[str.length() / 2];
for(int i = 0; i < baKeyword.length; i++){
  try{
    baKeyword[i] = (byte)(0xff & Integer.parseInt(str.substring(i*2,i*2+2),16));
  }catch (Exception e){
    e.printStackTrace();
  }
}
  try{
    str = new String(baKeyword,"UTF-8");
    new String();
  }catch (Exception e1){
    e1.printStackTrace();
  }
  System.out.print("|" + str);
*/ 
