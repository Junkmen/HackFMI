package xtrordinary.games.enb;

import com.badlogic.gdx.utils.TimeUtils;

public class Timer {
	private long lastTick;
	private long tickTime;
	
	public Timer(long tickTime) {
		lastTick = TimeUtils.millis(); //Initialize timer.
		this.tickTime = tickTime;
	}

	public void setTickTime(long millis) {
		this.tickTime = millis; //Set time to tick. 
	}
	
	public void update() {
		if (TimeUtils.timeSinceMillis(lastTick) > tickTime) { //Checks if timer should tick
			lastTick = TimeUtils.millis();                            //moves the timer forward.
 			action();
		}
	}

    public long getTimeLeft() {
        return tickTime - TimeUtils.timeSinceMillis(lastTick);
    }

	public void action() {
		//Called on every tick.
	}
	public void reset() {
		lastTick = TimeUtils.millis();
	}
}
