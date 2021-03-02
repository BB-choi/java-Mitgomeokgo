package searchstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import searchstore.view.StoreDB;

public class SearchStoreDaoImpl implements ISearchDAO{
	
	final String DRIVER = "org.sqlite.JDBC";
	final String DB = "jdbc:sqlite:db/database.db";
	private Connection conn;
	ObservableList<StoreDB> sList; 
	
	public SearchStoreDaoImpl() {
		try {
	         Class.forName(DRIVER);
	         conn = DriverManager.getConnection(DB);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}

	@Override
	public ObservableList<StoreDB> setAllList(String txt) {
		
		List<StoreDB> lst;
		lst = getAllList(txt);
		sList = FXCollections.observableArrayList();
		for (StoreDB s : lst) {
			sList.add(s);
		}
		
		return sList;
	}
	@Override
	public ObservableList<StoreDB> setAllListOrderByView(String txt) {
		
		List<StoreDB> lst;
		lst = getAllListOrderByView(txt);
		sList = FXCollections.observableArrayList();
		for (StoreDB s : lst) {
			sList.add(s);
		}
		
		return sList;
	}
	
	@Override
	public ObservableList<StoreDB> setList(String table, Label searchWord) {
		List<StoreDB> lst;
		lst = getList(table, searchWord);
		sList = FXCollections.observableArrayList();
		for (StoreDB s : lst) {
			sList.add(s);
		}
		
		return sList;
	}
	
	private void tryQuery(String query, List<StoreDB> lst) {
		StoreDB sdb;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
		while(rs.next()) {
			sdb = new StoreDB();
			sdb.setStoreName(rs.getString("name"));
			sdb.setStoreTel(rs.getString("tel"));
			sdb.setStoreAddr(rs.getString("addr"));
			
			lst.add(sdb);
		}
		rs.close();
		ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private List<StoreDB> getAllList(String txt) {
		
		List<StoreDB> lst= new ArrayList<>();
		String q = "SELECT name, addr, tel from hygiene WHERE addr like '%"+txt+"%'"
				+ " UNION SELECT name, addr, tel from safety WHERE addr like '%"+txt+"%' UNION "
				+ "SELECT name, addr, tel from hygiene WHERE name like '%"+txt+"%'"
				+ " UNION SELECT name, addr, tel from safety WHERE name like '%"+txt+"%' "
				+ "EXCEPT "
				+ "SELECT DISTINCT safety.name, safety.addr, safety.tel from safety, hygiene "
				+ "where safety.name=hygiene.name ";
		
		tryQuery(q, lst);
		
		return lst;
	}

	public List<StoreDB> getList(String table, Label searchWord) {
		List<StoreDB> lst= new ArrayList<>();
		String txt = searchWord.getText();
		
		String q = "SELECT name, addr, tel from "+table+" WHERE addr like '%"+txt+"%'"
				+ " UNION SELECT name, addr, tel from "+table+" WHERE name like '%"+txt+"%'";
		
		tryQuery(q, lst);
		return lst;
		
	}
	
	private List<StoreDB> getAllListOrderByView(String txt) {
		
		List<StoreDB> lst= new ArrayList<>();
		String q = "SELECT * FROM (SELECT name, addr, tel, view from hygiene,hygiene_view WHERE addr like '%"+txt+"%' "
				+ "and hygiene.idx_no=hygiene_view.idx_no UNION "
				+ "SELECT name, addr, tel, view from safety,safety_view WHERE addr like '%"+txt+"%' "
				+ "and safety.idx_no=safety_view.idx_no UNION "
				+ "SELECT name, addr, tel, view from hygiene, hygiene_view WHERE name like '%"+txt+"%' "
				+ "and hygiene.idx_no=hygiene_view.idx_no "
				+ "UNION SELECT name, addr, tel, view from safety, safety_view WHERE name like '%"+txt+"%' "
				+ "and safety.idx_no=safety_view.idx_no "
				+ "EXCEPT SELECT DISTINCT safety.name, safety.addr, safety.tel,safety_view.view from safety, hygiene, safety_view "
				+ "where safety.name=hygiene.name and safety.idx_no=safety_view.idx_no "
				+ ") order by view desc";
		
		tryQuery(q, lst);
		
		return lst;
	}

	@Override
	public boolean chkName(String name, String addr, String table) {
		String query = "SELECT EXISTS "
				+ "(SELECT * from "
				+ table
				+" where name = '"+name+"' "
				+ "and addr like'%"+addr+"%') as exist";
		String result = null;
		int isExist;
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				result = rs.getString("exist");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		isExist = Integer.parseInt(result);
		
		if(isExist >= 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getIdxNum(String name, String tel, String table) {
		
		String query = "SELECT idx_no "
				+ "FROM "+table+" WHERE tel ='"+tel+"'"
				+ "AND name like '%"+name+"%'";
		int idxNo=0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idxStr = rs.getString("idx_no");
				idxNo = Integer.parseInt(idxStr);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idxNo;
	}
	
	@Override
	public boolean isExistIdx(int idxNo, String table) {
		String query = "SELECT count(*) as count "
				+ "FROM "+table+" WHERE idx_no="+idxNo+";";
		String isExist=null;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				isExist = rs.getString("count");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (isExist.equals("1")) return true;
		
		return false;
	}
	
	@Override
	public void increaseViewNum(int idx, String table) {
		String query = "UPDATE "+table+" SET "
				+ "idx_no = "+idx+", view = view+1 "
				+ "WHERE idx_no = "+idx+";";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getGrade(String name) {
		String query = "select grade from hygiene WHERE name like "
				+ "'%"+ name + "%'";
		int grade=0;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				grade = rs.getInt("grade");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return grade;
	}

	@Override
	public ObservableList<StoreDB> setListOrderByView(String table, Label searchWord) {
		List<StoreDB> lst;
		lst = getListOrderByView(table, searchWord);
		sList = FXCollections.observableArrayList();
		for (StoreDB s : lst) {
			sList.add(s);
		}
		
		return sList;
	}

	private List<StoreDB> getListOrderByView(String table, Label searchWord) {
		List<StoreDB> lst= new ArrayList<>();
		String txt = searchWord.getText();
		
		String q = "SELECT * FROM("
				+ "SELECT name, addr, tel, view from "+table+", "+table+"_view WHERE addr like '%"+txt+"%'"
				+ " AND "+table+".idx_no="+table+"_view.idx_no "
				+ " UNION SELECT name, addr, tel, view from "+table+", "+table+"_view WHERE name like '%"+txt+"%'"
				+ " AND "+table+".idx_no="+table+"_view.idx_no)"
				+ " ORDER by view desc;";
		
		tryQuery(q, lst);
		return lst;
	}
	
	@Override
	public ObservableList<StoreDB> setBothTableList(ArrayList<String> nameLst, ArrayList<String> addrLst) {
		List<StoreDB> lst;
		lst = getList(nameLst, addrLst);
		sList = FXCollections.observableArrayList();
		for (StoreDB s : lst) {
			sList.add(s);
		}
		
		return sList;
	}
	
	
	private List<StoreDB> getList(ArrayList<String> nameLst, ArrayList<String> addrLst) {
		List<StoreDB> lst= new ArrayList<>();
		String q = "SELECT * FROM (";
		for(int i=0;i<nameLst.size();i++) {
			
			if(i==0) {
				q += "SELECT name, addr, tel, view from hygiene, hygiene_view WHERE addr like '"+addrLst.get(i)+"'";
			}
			else {
				q+= " union SELECT name, addr, tel, view from hygiene, hygiene_view WHERE addr like '"+addrLst.get(i)+"'";
			}
			
			q+= " and name = '"+nameLst.get(i)+"' and hygiene.idx_no = hygiene_view.idx_no "
			+ "union SELECT name, addr, tel, view from safety, safety_view WHERE addr like '"+addrLst.get(i)+"'"
			+ " and name = '"+nameLst.get(i)+"' and safety.idx_no = safety_view.idx_no";
			
		}
		q+= ") ORDER by name;";
		
		tryQuery(q, lst);
		
		return lst;
	}

	@Override
	public ObservableList<StoreDB> setBothListOrderByView(ArrayList<String> nameLst, ArrayList<String> addrLst) {
		List<StoreDB> lst;
		lst = getListOrderByView(nameLst, addrLst);
		sList = FXCollections.observableArrayList();
		for (StoreDB s : lst) {
			sList.add(s);
		}
		
		return sList;
	}

	private List<StoreDB> getListOrderByView(ArrayList<String> nameLst, ArrayList<String> addrLst) {
		List<StoreDB> lst= new ArrayList<>();
		String q = "SELECT * FROM (";
		for(int i=0;i<nameLst.size();i++) {
			
			if(i==0) {
				q += "SELECT name, addr, tel, view from hygiene, hygiene_view WHERE addr like '"+addrLst.get(i)+"'";
			}
			else {
				q+= " union SELECT name, addr, tel, view from hygiene, hygiene_view WHERE addr like '"+addrLst.get(i)+"'";
			}
			
			q+= " and name = '"+nameLst.get(i)+"' and hygiene.idx_no = hygiene_view.idx_no "
			+ "union SELECT name, addr, tel, view from safety, safety_view WHERE addr like '"+addrLst.get(i)+"'"
			+ " and name = '"+nameLst.get(i)+"' and safety.idx_no = safety_view.idx_no";
			
		}
		q+= ") ORDER by view desc;";
		
		tryQuery(q, lst);
		
		return lst;
	}

}
