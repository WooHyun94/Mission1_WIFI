package wifi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbTest {
	String DBFileName = "C:\\Users\\94dng\\Desktop\\eclipse\\eclipse_workspace\\Mission1-WIFI\\src\\WIFI.db";
	String[] cols = {"MGR_NO", "WRDOFC", "MAIN_NM", "ADRES1", "ADRES2", 
			"INSTL_FLOOR", "INSTL_TY", "INSTL_MBY", "SVC_SE", "CMCWR", "CNSTC_YEAR", 
			"INOUT_DOOR", "REMARS3", "LNT", "LAT", "WORK_DTTM"};
	
	public static void main(String[] args) {
		DbTest me = new DbTest();
		
	}
	
	
	public List<HistoryMember> historySelect() {
		List<HistoryMember> list = new ArrayList<>();
		Connection conn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    

	    try {
	        Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);

	        String sql = " SELECT * FROM HISTORY ORDER BY LC_ID ASC ";
	        preparedStatement = conn.prepareStatement(sql);
	        
	        
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	        	HistoryMember member = new HistoryMember();
	            
	        	int id = rs.getInt("LC_ID");
	        	double lat = rs.getDouble("LAT");
	        	double lnt = rs.getDouble("LNT");;
	        	String dttm = rs.getString("DTTM");
	        	
	        	member.setId(id);
	        	member.setLat(lat);
	        	member.setLnt(lnt);
	        	member.setDttm(dttm);
	            
	            list.add(member);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null && !rs.isClosed()) {
	                rs.close();
	            }
	            if (preparedStatement != null && !preparedStatement.isClosed()) {
	                preparedStatement.close();
	            }
	            if (conn != null && !conn.isClosed()) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return list;
	}
	
	public void historyInsert(double lat, double lnt) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);
			
			String sql = " insert into HISTORY (LAT, LNT, DTTM) values (?, ?, datetime('now')) ";
			
			System.out.println(sql);
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setDouble(1, lat);
			preparedStatement.setDouble(2, lnt);
			
			int affected = preparedStatement.executeUpdate();
			
			if(affected > 0) {
				System.out.println("저장 성공");
			}else {
				System.out.println("저장 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
//			try {
//				if(!rs.isClosed()) {
//					rs.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			try {
				if(!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public WifiMember wifiSelect3(String mgr_no) {
		WifiMember member = new WifiMember();
		
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);

            String sql = "SELECT * FROM WIFI_INFO WHERE MGR_NO = ? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mgr_no);


            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                double distance = rs.getDouble("DISTANCE");
                String no = rs.getString("MGR_NO");
                String wrdofc = rs.getString("WRDOFC");
                String name = rs.getString("MAIN_NM");
                String add1 = rs.getString("ADRES1");
                String add2 = rs.getString("ADRES2");
                String floor = rs.getString("INSTL_FLOOR");
                String ty = rs.getString("INSTL_TY");
                String mby = rs.getString("INSTL_MBY");
                String se = rs.getString("SVC_SE");
                String cmcwr = rs.getString("CMCWR");
                int year = rs.getInt("CNSTC_YEAR");
                String door = rs.getString("INOUT_DOOR");
                String remars3 = rs.getString("REMARS3");
                double lat = rs.getDouble("LAT");
                double lnt = rs.getDouble("LNT");
                String dttm = rs.getString("WORK_DTTM");

                member.setDistance(distance);
                member.setNo(no);
                member.setWrdofc(wrdofc);
                member.setName(name);
                member.setAdd1(add1);
                member.setAdd2(add2);
                member.setFloor(floor);
                member.setTy(ty);
                member.setCmcwr(cmcwr);
                member.setMby(mby);
                member.setSe(se);
                member.setYear(year);
                member.setDoor(door);
                member.setRemars3(remars3);
                member.setLat(lat);
                member.setLnt(lnt);
                member.setDttm(dttm);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		return member;
	}
	
	public void wifiInsert(String[] args) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);
			
			String sql = " insert into WIFI_INFO (";
			
			for(int i = 0; i < cols.length - 1; i++) {
				sql += (cols[i] + ", ");
			}
			sql += (cols[cols.length - 1] + ") "); 
			
			sql += "values (";
			
			for(int i = 0; i < cols.length - 1; i++) {
				sql += "?, ";
			}
			
			sql += "?) ";
			
//			System.out.println(sql);
			preparedStatement = conn.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++) {
				preparedStatement.setString(i + 1, args[i]);
			}
			
			int affected = preparedStatement.executeUpdate();
			
			if(affected > 0) {
				System.out.println("저장 성공");
			}else {
				System.out.println("저장 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
//			try {
//				if(!rs.isClosed()) {
//					rs.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			try {
				if(!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateDistance(double targetLat, double targetLnt) {
	    Connection conn = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);

	        String sql = "UPDATE WIFI_INFO " +
	                     "SET DISTANCE = (6371 * 2 * ASIN(SQRT(" +
	                     "POWER(SIN(RADIANS((LAT - ?) / 2)), 2) + " +
	                     "COS(RADIANS(?)) * COS(RADIANS(LAT)) * " +
	                     "POWER(SIN(RADIANS((LNT - ?) / 2)), 2)" +
	                     ")))";

	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setDouble(1, targetLat);
	        preparedStatement.setDouble(2, targetLat);
	        preparedStatement.setDouble(3, targetLnt);

	        int affected = preparedStatement.executeUpdate();

	        if (affected > 0) {
	            System.out.println("거리 업데이트 성공");
	        } else {
	            System.out.println("거리 업데이트 실패");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null && !preparedStatement.isClosed()) {
	                preparedStatement.close();
	            }
	            if (conn != null && !conn.isClosed()) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public List<WifiMember> wifiSelect2() {
		List<WifiMember> list = new ArrayList<>();
		Connection conn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    int limit = 20;

	    try {
	        Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);

	        String sql = " SELECT * FROM WIFI_INFO ORDER BY DISTANCE ASC LIMIT ? ";
	        preparedStatement = conn.prepareStatement(sql);
	        
	        preparedStatement.setInt(1, limit);
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            WifiMember member = new WifiMember();
	            
	            double distance = rs.getDouble("DISTANCE");
	            String no = rs.getString("MGR_NO");
	        	String wrdofc = rs.getString("WRDOFC");
	        	String name = rs.getString("MAIN_NM");
	        	String add1 = rs.getString("ADRES1");
	        	String add2= rs.getString("ADRES2");
	        	String floor= rs.getString("INSTL_FLOOR");
	        	String ty= rs.getString("INSTL_TY");
	        	String mby= rs.getString("INSTL_MBY");
	        	String se= rs.getString("SVC_SE");
	        	String cmcwr= rs.getString("CMCWR");
	        	int year= rs.getInt("CNSTC_YEAR");
	        	String door= rs.getString("INOUT_DOOR");
	        	String remars3= rs.getString("REMARS3");
	        	double lat= rs.getDouble("LAT");
	        	double lnt= rs.getDouble("LNT");
	        	String dttm= rs.getString("WORK_DTTM");
	        	
	        	member.setDistance(distance);
	        	member.setNo(no);
	        	member.setWrdofc(wrdofc);
	        	member.setName(name);
	        	member.setAdd1(add1);
	        	member.setAdd2(add2);
	        	member.setFloor(floor);
	        	member.setTy(ty);
	        	member.setCmcwr(cmcwr);
	        	member.setMby(mby);
	        	member.setSe(se);
	        	member.setYear(year);
	        	member.setDoor(door);
	        	member.setRemars3(remars3);
	        	member.setLat(lat);
	        	member.setLnt(lnt);
	        	member.setDttm(dttm);
	            
	            list.add(member);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null && !rs.isClosed()) {
	                rs.close();
	            }
	            if (preparedStatement != null && !preparedStatement.isClosed()) {
	                preparedStatement.close();
	            }
	            if (conn != null && !conn.isClosed()) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return list;
	}
	
	public void wifiSelect() {
	    Connection conn = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);

	        String sql = "SELECT ";
	        for (int i = 0; i < cols.length - 1; i++) {
	            sql += cols[i] + ", ";
	        }
	        sql += cols[cols.length - 1] + " FROM WIFI_INFO";

	        preparedStatement = conn.prepareStatement(sql);

	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            for (int i = 0; i < cols.length; i++) {
	                System.out.println(cols[i] + ": " + rs.getObject(cols[i]));
	            }
	            System.out.println();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null && !rs.isClosed()) {
	                rs.close();
	            }
	            if (preparedStatement != null && !preparedStatement.isClosed()) {
	                preparedStatement.close();
	            }
	            if (conn != null && !conn.isClosed()) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
