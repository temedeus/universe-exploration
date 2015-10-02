package com.universe.exploration.ueui.data;

import java.util.ArrayList;

class DataAndValueContainer implements IDataAndValueContainer {

	private ArrayList<DataAndValuePair> pairList;
	Object auxiliaryDataContainer;
	/**
	 * 
	 */
	public DataAndValueContainer() {
		pairList = new ArrayList<DataAndValuePair>();
		createPairs();
	}
	
	public DataAndValueContainer(Object auxiliaryDataContainer) {
		pairList = new ArrayList<DataAndValuePair>();
		this.auxiliaryDataContainer = auxiliaryDataContainer;
		createPairs();
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.ui.data.IDataAndValueContainer#createPairs()
	 */
	@Override
	public void createPairs() {
		// TODO Auto-generated method stub
		
	}
	
	public void add(DataAndValuePair pair) {
		pairList.add(pair);
	}
	
	public void update(String id, String newVal) {
		for(DataAndValuePair pair : pairList) {
			if(pair.getId() == id) {
				pair.getValue().setText(newVal);
			}
		}
		
	}
	
	/**
	 * @return
	 */
	public ArrayList<DataAndValuePair> getPairList() {
		return pairList;
	}
	
}