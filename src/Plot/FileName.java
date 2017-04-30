package Plot;

public class FileName {
	
	String estimator;
	String filename;
	
	public FileName(String estimator, String filename) {
		this.estimator = estimator;
		this.filename = filename;
	}

	public String getEstimator() {
		return estimator;
	}

	public void setEstimator(String estimator) {
		this.estimator = estimator;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
