package presenter;

import java.io.Serializable;

public class Properties implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -705760289686579585L;
	private String generatorAlgorithm;
	private String searchAlgorithm;
	private int threadsNum;
	
	public Properties(){
		this.generatorAlgorithm=null;
		this.searchAlgorithm=null;
		this.threadsNum=0;
	}
	public Properties(Properties prop){
		this.generatorAlgorithm=prop.generatorAlgorithm;
		this.searchAlgorithm=prop.searchAlgorithm;
		this.threadsNum=prop.threadsNum;
	}
	
	public String getGeneratorAlgorithm() {
		return generatorAlgorithm;
	}
	public void setGeneratorAlgorithm(String generatorAlgorithm) {
		this.generatorAlgorithm = generatorAlgorithm;
	}
	public String getSearchAlgorithm() {
		return searchAlgorithm;
	}
	public void setSearchAlgorithm(String searchAlgorithm) {
		this.searchAlgorithm = searchAlgorithm;
	}
	public int getThreadsNum() {
		return threadsNum;
	}
	public void setThreadsNum(int threadsNum) {
		this.threadsNum = threadsNum;
	}

}
