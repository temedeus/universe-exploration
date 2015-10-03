package com.universe.exploration.ueui.data;

import java.util.ArrayList;

class DataAndValueContainer implements IDataAndValueContainer {

	private ArrayList<DataAndValuePair> pairList;
	
	protected Object auxiliaryDataContainer;
	
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

	}
	
	/**
	 * Add value pair to list.
	 * @param pair
	 */
	public void add(DataAndValuePair pair) {
		pairList.add(pair);
	}

	/**
	 * Update label value according to predetermined ID.
	 * @param id
	 * @param newVal
	 */
	public void update(String id, String newVal) {
		for(DataAndValuePair pair : pairList) {
			if(pair.getId() == id) {
				pair.updateValue(newVal);
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