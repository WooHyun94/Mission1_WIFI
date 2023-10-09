package wifi;

import org.json.simple.parser.ParseException;

public class GetWifi {
	public static void main(String[] args) {
//		try {
//			wifiInfo.AddWifi();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		DbTest db = new DbTest();
		db.wifiSelect();
	}
}
