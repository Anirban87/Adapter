package Adapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Create_CSV
{
	public static void Create_data() throws FileNotFoundException
	{
	//for data collection 
		PrintStream out_data = new PrintStream(new FileOutputStream("C:/Users/AM0C70368/Desktop/output_data.csv"));
		
	    MongoClient mongoClient_data = new MongoClient();
		
	    DB db_data = mongoClient_data.getDB("mydb");
	    System.out.println("Connect to database successfully");
		DBCollection coll_data = db_data.getCollection("data");
		      
	    DBObject keys_data = new BasicDBObject();
		keys_data.put("uidLog","");
		keys_data.put("data", "");
		
		DB mongoTemplate = null;
		DBCursor List = coll_data.find(null, keys_data);
		Map dataMap = new HashMap();
		while(List.hasNext())
		{
			final DBObject result = List.next();
			String uidLog = (String) result.get("uidLog");
		
			List dataList = new ArrayList();
			dataList = (List) result.get("data");
		
			System.out.println(result.get("data"));
			System.setOut(out_data);
			
		}
		out_data.close();
	}	
	
	public static void Create_log() throws FileNotFoundException
	{
		// for groupLog collection
		
		    PrintStream out_log = new PrintStream(new FileOutputStream("C:/Users/AM0C70368/Desktop/output_log.csv"));
		    MongoClient mongoClient = new MongoClient();
			
	        DB db_log = mongoClient.getDB("mydb");
	        System.out.println("Connect to database successfully");
	    	DBCollection coll = db_log.getCollection("groupLog");
			      
	        DBObject keys_log = new BasicDBObject();
	 		keys_log.put("uidLog","");
	 		keys_log.put("curvInfoList.mnemonic", "");

			DBCursor log = coll.find(null, keys_log);
	 		Map mnemonicMap = new HashMap();
	 		
	 		while(log.hasNext())
	 		{

	 			final DBObject result = log.next();
	 			String uidLog = (String) result.get("uidLog");
	 		
	 			List menonmicList = new ArrayList();
	 			menonmicList = (List) result.get("curvInfoList");
	 				 		
	 			mnemonicMap.put(uidLog, menonmicList);
	 			System.out.println(mnemonicMap);
	 			
	 			System.setOut(out_log);
	 		}
	 		out_log.close();
	}
	 		
	 		
}		
		


