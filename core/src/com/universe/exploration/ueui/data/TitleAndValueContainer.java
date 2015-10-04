package com.universe.exploration.ueui.data;

import java.util.ArrayList;

/**
 * <p>Holds an <tt>ArrayList</tt> of {@link TitleAndValuePair}.</p> 
 * 
 * @author 4.10.2015 Teemu Puurunen 
 *
 */
class TitleAndValueContainer implements ITitleAndValueContainer {

	private ArrayList<TitleAndValuePair> pairList;
	
	protected Object auxiliaryDataContainer;
	
	/**
	 * 
	 */
	public TitleAndValueContainer() {
		pairList = new ArrayList<TitleAndValuePair>();
		createPairs();
	}
	
	public TitleAndValueContainer(Object auxiliaryDataContainer) {
		pairList = new ArrayList<TitleAndValuePair>();
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
	public void add(TitleAndValuePair pair) {
		pairList.add(pair);
	}

	/**
	 * Update label value according to predetermined ID.
	 * @param id
	 * @param newVal
	 */
	public void update(String id, String newVal) {
		for(TitleAndValuePair pair : pairList) {
			if(pair.getId() == id) {
				pair.updateValue(newVal);
			}
		}
	}
	
	/**
	 * @return
	 */
	public ArrayList<TitleAndValuePair> getPairList() {
		return pairList;
	}
	
}