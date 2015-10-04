/**
 * 
 */
package com.universe.exploration.ueui.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Note: this class does not extend Table, but it utilizes Table.
 * @author 24.9.2015 Teemu Puurunen 
 * 
 * TODO: If only one widgetgroup added, don't create new table for that single cell.
 *
 */
public class BasicTable {
	/**
	 * Generic table for containing data.
	 */
	private Table table;

	/**
	 * Temporary table containing cell data. 
	 */
	private Table tempCellTable;

	/**
	 * 
	 */
	public BasicTable(int align) {
		table = new Table();
		tempCellTable = new Table();
		table.align(align);
		table.padTop(30);
		table.padLeft(30);
	}
	
	/**
	 * Add data to cell. Call {@link column} to wrap it up. To change row, call {@link row}.
	 * @param actor
	 */
	public <T extends Actor> void add(T actor) {
		tempCellTable.add(actor);	
	}
	
	/**
	 * Next table column.
	 */
	public void column() {
		table.add(tempCellTable).left();
		tempCellTable = new Table();
	}
	
	/**
	 * Next table row.
	 */
	public void row() {
		table.row();
	}
	
	/**
	 * Creates a spacer row.
	 */
	public void spacerRow() {
		row();
		row();
	}
	
	/**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}
}
