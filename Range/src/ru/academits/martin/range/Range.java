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

    private Range(Range interval) {
        this(interval.getFrom(), interval.getTo());
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
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
        double begin = Math.max(this.from, interval2.from);
        double end = Math.min(interval2.to, this.to);
        if (end > begin) {
            return new Range(begin, end);
        } else {
            return null;
        }
    }

    public Range[] getUnion(Range interval2) {
        if (interval2.from > this.to || this.from > interval2.to) {
            return new Range[]{new Range(this), new Range(interval2)};
        } else if (interval2.from >= this.from && interval2.to >= this.to) {
            return new Range[]{new Range(this.from, interval2.to)};
        } else if (interval2.from >= this.from) {
            return new Range[]{new Range(this)};
        } else if (this.from > interval2.from && this.to >= interval2.to) {
            return new Range[]{new Range(interval2.from, this.to)};
        } else {
            return new Range[]{new Range(interval2)};
        }
    }

    public Range[] getDifference(Range interval2) {
        if (interval2.from >= this.to || this.from >= interval2.to) {
            return new Range[]{new Range(this)};
        } else if (interval2.from > this.from && this.to > interval2.to) {
            return new Range[]{new Range(this.from, interval2.from), new Range(interval2.to, this.to)};
        } else if (interval2.from > this.from) {
            return new Range[]{new Range(this.from, interval2.from)};
        } else if (this.from >= interval2.from && this.to > interval2.to) {
            return new Range[]{new Range(interval2.to, this.to)};
        } else {
            return new Range[0];
        }
    }
}