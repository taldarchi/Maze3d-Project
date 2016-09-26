package presenter;

import java.io.Serializable;

/**
 * The Class Properties.
 */
public class Properties implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -705760289686579585L;
	
	/** The generator algorithm. */
	private String generatorAlgorithm;
	
	/** The search algorithm. */
	private String searchAlgorithm;
	
	/** The threads num. */
	private int threadsNum;
	
	/** The user interface. */
	private String userInterface;
	
	/**
	 * Instantiates a new properties.
	 */
	public Properties(){
		this.generatorAlgorithm=null;
		this.searchAlgorithm=null;
		this.threadsNum=0;
	}
	
	/**
	 * Instantiates a new properties.
	 *
	 * @param prop the prop
	 */
	public Properties(Properties prop){
		this.generatorAlgorithm=prop.generatorAlgorithm;
		this.searchAlgorithm=prop.searchAlgorithm;
		this.threadsNum=prop.threadsNum;
	}
	
	/**
	 * Gets the generator algorithm.
	 *
	 * @return the generator algorithm
	 */
	public String getGeneratorAlgorithm() {
		return generatorAlgorithm;
	}
	
	/**
	 * Sets the generator algorithm.
	 *
	 * @param generatorAlgorithm the new generator algorithm
	 */
	public void setGeneratorAlgorithm(String generatorAlgorithm) {
		this.generatorAlgorithm = generatorAlgorithm;
	}
	
	/**
	 * Gets the search algorithm.
	 *
	 * @return the search algorithm
	 */
	public String getSearchAlgorithm() {
		return searchAlgorithm;
	}
	
	/**
	 * Sets the search algorithm.
	 *
	 * @param searchAlgorithm the new search algorithm
	 */
	public void setSearchAlgorithm(String searchAlgorithm) {
		this.searchAlgorithm = searchAlgorithm;
	}
	
	/**
	 * Gets the threads num.
	 *
	 * @return the threads num
	 */
	public int getThreadsNum() {
		return threadsNum;
	}
	
	/**
	 * Sets the threads num.
	 *
	 * @param threadsNum the new threads num
	 */
	public void setThreadsNum(int threadsNum) {
		this.threadsNum = threadsNum;
	}
	
	/**
	 * Gets the user interface.
	 *
	 * @return the user interface
	 */
	public String getUserInterface() {
		return userInterface;
	}
	
	/**
	 * Sets the user interface.
	 *
	 * @param userInterface the new user interface
	 */
	public void setUserInterface(String userInterface) {
		this.userInterface = userInterface;
	}

}
