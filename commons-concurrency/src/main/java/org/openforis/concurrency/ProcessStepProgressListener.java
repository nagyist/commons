package org.openforis.concurrency;

/**
 * 
 * @author S. Ricci
 *
 */
public class ProcessStepProgressListener implements DetailedProgressListener {
	
	private ProcessProgressListener processProgressListener;
	private ProgressListener outerProgressListener;

	public ProcessStepProgressListener(ProcessProgressListener totalProgressListener,
			ProgressListener outerProgressListener) {
		super();
		this.processProgressListener = totalProgressListener;
		this.outerProgressListener = outerProgressListener;
	}

	public void progressMade() {}
	
	public void progressMade(Progress stepProgress) {
		processProgressListener.stepProgressMade(stepProgress);
		outerProgressListener.progressMade();
		if (outerProgressListener instanceof DetailedProgressListener) {
			((DetailedProgressListener) outerProgressListener).progressMade(processProgressListener.getProgress());
		}
	}
}