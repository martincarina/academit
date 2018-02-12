package ru.academits.martin.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        if (from > to) {
            throw new RuntimeException("Значение нижней границы больше верхней.");
        }
        this.from = from;
        this.to = to;
    }

    public double from() {
        return from;
    }

    public double to() {
        return to;
    }

    public void setFrom(double from) {
        if (from > to) {
            throw new RuntimeException("Значение нижней границы больше верхней.");
        }
        this.from = from;
    }

    public void setTo(double to) {
        if (from > to) {
            throw new RuntimeException("Значение нижней границы больше верхней.");
        }
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return (number >= from && number <= to);
    }

    public Range getIntersection(Range interval2) {
        double begin;
        double end;
        begin = (this.from > interval2.from) ? this.from : interval2.from;
        end = (interval2.to > this.to) ? this.to : interval2.to;
        if (end >= begin) {
            return new Range(begin, end);
        } else {
            return null;
        }
    }

    public Range[] getUnion(Range interval2) {
        if (interval2.from >= this.from && this.to >= interval2.from && interval2.to >= this.to) {
            return new Range[]{new Range(this.from, interval2.to())};
        } else if (interval2.from >= this.from && this.to >= interval2.from) {
            return new Range[]{this};
        } else if (this.from > interval2.from && interval2.to >= this.from && this.to >= interval2.to) {
            return new Range[]{new Range(interval2.from, this.to)};
        } else if (this.from > interval2.from && interval2.to >= this.from) {
            return new Range[]{interval2};
        } else {
            return new Range[]{this, interval2};
        }
    }

    public Range[] getDifference(Range interval2) {
        if (interval2.from > this.from && this.to() > interval2.to) {
            return new Range[]{new Range(this.from, interval2.from), new Range(interval2.to, this.to)};
        } else if (interval2.from > this.from && interval2.to >= this.to && this.to > interval2.from) {
            return new Range[]{new Range(this.from, interval2.from)};
        } else if (interval2.from > this.from && interval2.from >= this.to) {
            return new Range[]{this};
        } else if (this.from >= interval2.from && this.to > interval2.to) {
            return new Range[]{new Range(interval2.to, this.to)};
        } else {
            return null;
        }
    }
}