package timeTracker;


public class Interval {

		
		/**
		 */
		public long getTotalTime(){
			return 0;
		}

		/**
		 * @uml.property  name="startingTime"
		 */
		private long startingTime;

		/** 
		 * Getter of the property <tt>start</tt>
		 * @return  Returns the start.
		 * @uml.property  name="startingTime"
		 */
		public long getStartingTime() {
			return startingTime;
		}

		/** 
		 * Setter of the property <tt>start</tt>
		 * @param start  The start to set.
		 * @uml.property  name="startingTime"
		 */
		public void setStartingTime(long startingTime) {
			this.startingTime = startingTime;
		}

		/**
		 * @uml.property  name="finishingTime"
		 */
		private long finishingTime;

		/** 
		 * Getter of the property <tt>finish</tt>
		 * @return  Returns the finish.
		 * @uml.property  name="finishingTime"
		 */
		public long getFinishingTime() {
			return finishingTime;
		}

		/** 
		 * Setter of the property <tt>finish</tt>
		 * @param finish  The finish to set.
		 * @uml.property  name="finishingTime"
		 */
		public void setFinishingTime(long finishingTime) {
			this.finishingTime = finishingTime;
		}

			
			/**
			 */
			public void startTimer(){
			}

				
				/**
				 */
				public void stopTimer(){
				}

}
