/**
 * 
 */
package common.universe.exploration.common;

/**
 * @author 27.9.2015 Teemu Puurunen 
 *
 */
public enum Lifeforms {
	CIVILIZED("TITLE_LIFEFORMS_CIVILIZED"),
	ANIMAL("TITLE_LIFEFORMS_ANIMAL"),
	VEGETATION("TITLE_LIFEFORMS_VEGETATION"),
	BACTERIAL("TITLE_LIFEFORMS_BACTERIAL"),
	NONE("TITLE_LIFEFORMS_NONE");
	
	private final String localKey;
	
	/**
	 * @return the localKey
	 */
	public String getLocalKey() {
		return localKey;
	}

	Lifeforms(String localKey) {
		this.localKey = localKey;
	}
}
