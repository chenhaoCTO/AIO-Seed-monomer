package com.seed.ninomer.common.util;

import java.util.Random;

/**
 * @description: Id算法
 * @author: Mr.chen
 * @date: 2020/3/27 16:34
 * @version: 1.0
 */
public class IdWorker {
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long twepoch = 1288834974657L;
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;
    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp = -1L;
    private String prifix;

    public IdWorker(long workerId, long datacenterId, int prifix) {

        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;

        if (prifix > 1024 || prifix < 1) {
            throw new IllegalArgumentException("参数错误, prifix取值范围为[1-1024]");
        }

        if (prifix >= 1000) {
            this.prifix = prifix + "";
        } else if (prifix >= 100) {
            this.prifix = prifix + random(1);
        } else if (prifix >= 10) {
            this.prifix = prifix + random(2);
        } else {
            this.prifix = prifix + random(3);
        }
    }

    private String random(int length) {

        if (length == 3) {
            int a = new Random().nextInt(1000);
            if (a >= 100) {
                return a + "";
            }
            if (a >= 10) {
                return a + "0";
            }
            if (a >= 0) {
                return a + "00";
            }
        } else if (length == 2) {
            int a = new Random().nextInt(100);
            if (a >= 10) {
                return a + "";
            }
            if (a >= 0) {
                return a + "0";
            }
        } else if (length == 2) {
            return new Random().nextInt(10) + "";
        }

        return "000";
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        long result = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        return calc(result);
    }

    private long calc(long result) {
        String a = String.valueOf(result);
        String id = this.prifix +  a.substring(7);
        return Long.parseLong(id);
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}
