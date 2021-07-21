import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.FilterUtils;

/**  
 * <p>Title: JsonFileUtil.java</p>  
 * <p>Description: </p>    
 * @author dongbao  
 * @date 2018年8月17日
*/

/**  
 * <p>Title: JsonFileUtil.java</p>  
 * <p>Description: 解析json文件</p>    
 * @author dongbao  
 * @date 2018年8月17日
*/
public class JsonFileUtil {
    public static void main(String[] args) {  
    	String filepath = System.getProperty("user.dir")+"\\src\\person.json"; 
    	String jsonContent = FileUtil.ReadFile(filepath);
    	//将json转换成对象
    	Person person = JSON.parseObject(jsonContent, Person.class);
    	System.out.println(person);
    	//将对象再重新转换成json
    	JSONObject json = (JSONObject) JSON.toJSON(person);
    	System.out.println(json);
    	//将json转换成对象集合
    	//List<Person> list = JSON.parseArray(jsonContent,Person.class);
    	//System.out.println(list);
    	//JSONArray arr = (JSONArray) JSON.toJSON(list);
    	//System.out.println(arr);
    } 
    
}
