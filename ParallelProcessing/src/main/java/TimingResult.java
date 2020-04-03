public class TimingResult {
    private long linearTime;
    private long _2ThreadsTime;
    private long _4ThreadsTime;
    private long _8ThreadsTime;

    public TimingResult(long linearTime, long _2ThreadsTime, long _4ThreadsTime,long _8ThreadsTime) {
        this.linearTime = linearTime;
        this._2ThreadsTime = _2ThreadsTime;
        this._4ThreadsTime = _4ThreadsTime;
        this._8ThreadsTime = _8ThreadsTime;
    }

    public long getLinearTime() {
        return linearTime;
    }

    public void setLinearTime(long linearTime) {
        this.linearTime = linearTime;
    }

    public long get_2ThreadsTime() {
        return _2ThreadsTime;
    }

    public void set_2ThreadsTime(long _2ThreadsTime) {
        this._2ThreadsTime = _2ThreadsTime;
    }

    public long get_4ThreadsTime() {
        return _4ThreadsTime;
    }

    public void set_4ThreadsTime(long _4ThreadsTime) {
        this._4ThreadsTime = _4ThreadsTime;
    }
    public long get_8ThreadsTime() {
        return _8ThreadsTime;
    }

    public void set_8ThreadsTime(long _8ThreadsTime) {
        this._8ThreadsTime = _8ThreadsTime;
    }
}
