package com.feign.server.config;

import static java.util.concurrent.TimeUnit.SECONDS;

import feign.RetryableException;
import feign.Retryer;

public class SimpleRetryer implements Retryer{
	 private final int maxAttempts;
	    private final long period;
	    private final long maxPeriod;
	    int attempt;
	    long sleptForMillis;

	    public SimpleRetryer() {
	      this(100, SECONDS.toMillis(1), 5);
	    }

	    public SimpleRetryer(long period, long maxPeriod, int maxAttempts) {
	      this.period = period;
	      this.maxPeriod = maxPeriod;
	      this.maxAttempts = maxAttempts;
	      this.attempt = 1;
	    }

	    // visible for testing;
	    protected long currentTimeMillis() {
	      return System.currentTimeMillis();
	    }

	    public void continueOrPropagate(RetryableException e) {
	      if (attempt++ >= maxAttempts) {
	        throw e;
	      }

	      long interval;
	      if (e.retryAfter() != null) {
	        interval = e.retryAfter().getTime() - currentTimeMillis();
	        if (interval > maxPeriod) {
	          interval = maxPeriod;
	        }
	        if (interval < 0) {
	          return;
	        }
	      } else {
	        interval = nextMaxInterval();
	      }
	      try {
	        Thread.sleep(interval);
	      } catch (InterruptedException ignored) {
	        Thread.currentThread().interrupt();
	      }
	      sleptForMillis += interval;
	    }

	    /**
	     * Calculates the time interval to a retry attempt. <br> The interval increases exponentially
	     * with each attempt, at a rate of nextInterval *= 1.5 (where 1.5 is the backoff factor), to the
	     * maximum interval.
	     *
	     * @return time in nanoseconds from now until the next attempt.
	     */
	    long nextMaxInterval() {
	      long interval = (long) (period * Math.pow(1.5, attempt - 1));
	      return interval > maxPeriod ? maxPeriod : interval;
	    }

	    @Override
	    public Retryer clone() {
	      return new Default(period, maxPeriod, maxAttempts);
	    }

}
