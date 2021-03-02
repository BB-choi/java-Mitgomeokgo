package searchstore.dao;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import searchstore.view.StoreDB;

public interface ISearchDAO {
	boolean chkName (String name, String addr, String table);
	public int getGrade(String name);
	public int getIdxNum(String name, String tel, String table);
	public boolean isExistIdx(int idxNo, String table);
	public void increaseViewNum(int idx, String table);
	ObservableList<StoreDB> setAllList(String txt);
	ObservableList<StoreDB> setAllListOrderByView(String txt);
	ObservableList<StoreDB> setList(String table, Label searchWord);
	ObservableList<StoreDB> setListOrderByView(String table, Label searchWord);
	ObservableList<StoreDB> setBothTableList(ArrayList<String> nameLst, ArrayList<String> addrLst);
	ObservableList<StoreDB> setBothListOrderByView(ArrayList<String> nameLst, ArrayList<String> addrLst);
}
