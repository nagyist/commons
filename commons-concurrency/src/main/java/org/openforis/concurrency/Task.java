package org.openforis.concurrency;


/**
 * A unit of work in the system.
 * 
 * Tasks are not reusable.
 * 
 * @author M. Togna
 * @author S. Ricci
 */
public abstract class Task extends Worker {

	private long totalItems;
	private long itemsProcessed;
	private long itemsSkipped;

	public Task() {
		this.totalItems = -1;
		this.itemsProcessed = 0;
		this.itemsSkipped = 0;
	}
	
	@Override
	protected void initInternal() throws Throwable {
		super.initInternal();
		this.totalItems = countTotalItems();
	}
	
	protected long countTotalItems() {
		return -1;
	};

	protected void setItemsProcessed(long itemsProcessed) {
		this.itemsProcessed = itemsProcessed;
	}

	protected long incrementItemsProcessed() {
		return ++this.itemsProcessed;
	}

	protected long incrementItemsSkipped() {
		return ++this.itemsSkipped;
	}

	public long getItemsRemaining() {
		return totalItems - (itemsProcessed + itemsSkipped);
	}

	@Override
	public int getProgressPercent() {
		switch ( getStatus() ) {
		case COMPLETED:
			return 100;
		case RUNNING:
			if ( totalItems > 0 ) {
				int result = Double.valueOf(Math.ceil( ( itemsProcessed + itemsSkipped ) * 100 / totalItems )).intValue();
				return result;
			} else {
				return 0;
			}
		default:
			return 0;
		}
	}
	
	public long getItemsProcessed() {
		return this.itemsProcessed;
	}

	public long getItemsSkipped() {
		return this.itemsSkipped;
	}

	public final long getTotalItems() {
		return this.totalItems;
	}

}
