package org.example;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private int key;
    private int CHANNEL = 40;
    private int start;
    private int end;
    private int edgeNums;
    private int channelLeft;
    private int channelRight;
    private int value;
    private int channelSpare;
    private List<Integer> path = new ArrayList<>();

    public List<Integer> getPath() {
        return path;
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }

    public void addPath(Integer edge) {
        path.add(edge);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Service(int key, int start, int end, int edgeNums, int channelLeft, int channelRight, int value) {
        this.key = key;
        this.start = start;
        this.end = end;
        this.edgeNums = edgeNums;
        this.channelLeft = channelLeft;
        this.channelRight = channelRight;
        this.value = value;
        this.channelSpare = CHANNEL - (channelRight - channelLeft + 1);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getEdgeNums() {
        return edgeNums;
    }

    public void setEdgeNum(int edgeNums) {
        this.edgeNums = edgeNums;
    }

    public int getChannelLeft() {
        return channelLeft;
    }

    public void setChannelLeft(int channelLeft) {
        this.channelLeft = channelLeft;
    }

    public int getChannelRight() {
        return channelRight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setChannelRight(int channelRight) {
        this.channelRight = channelRight;
    }

    @Override
    public String toString() {
        return "Service{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
