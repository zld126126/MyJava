import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**  
 * <p>Title: FileUtil.java</p>  
 * <p>Description: </p>    
 * @author dongbao  
 * @date 2018年8月17日
*/

/**  
 * <p>Title: FileUtil.java</p>  
 * <p>Description: 文件工具类</p>    
 * @author dongbao  
 * @date 2018年8月17日
*/
public class FileUtil {
	public static String ReadFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

}
