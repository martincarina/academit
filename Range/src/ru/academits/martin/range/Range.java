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

    public Range getIntersection(Range interval1, Range interval2) {
        double begin;
        double end;
        double epsilon = 1.0e-10;
        if (interval1.getFrom() - interval2.getFrom() > epsilon) {
            begin = interval1.getFrom();
        } else {
            begin = interval2.getFrom();
        }

        if (interval2.getTo() - interval1.getTo() > epsilon) {
            end = interval1.getTo();
        } else {
            end = interval2.getTo();
        }

        if (end - begin >= -epsilon) {
            return new Range(begin, end);
        } else {
            return null;
        }
    }

    public Range[] getUnion(Range interval1, Range interval2) {
        double epsilon = 1.0e-10;
        Range[] union = new Range[2];
        if (interval2.getFrom() - interval1.getFrom() >= -epsilon && interval1.getTo() - interval2.getFrom() >= -epsilon && interval2.getTo() - interval1.getTo() >= -epsilon) {
            union[0] = new Range(interval1.getFrom(), interval2.getTo());
        } else if (interval2.getFrom() - interval1.getFrom() >= -epsilon && interval1.getTo() - interval2.getFrom() >= -epsilon) {
            union[0] = interval1;
        } else if (interval1.getFrom() - interval2.getFrom() > epsilon && interval2.getTo() - interval1.getFrom() >= -epsilon && interval1.getTo() - interval2.getTo() >= -epsilon) {
            union[0] = new Range(interval2.getFrom(), interval1.getTo());
        } else if (interval1.getFrom() - interval2.getFrom() > epsilon && interval2.getTo() - interval1.getFrom() >= -epsilon) {
            union[0] = interval2;
        } else {
            union[0] = interval1;
            union[1] = interval2;
        }
        return union;
    }

    public Range[] getDifference(Range interval1, Range interval2) {
        double epsilon = 1.0e-10;
        Range[] difference = new Range[2];
        if (interval2.getFrom() - interval1.getFrom() > epsilon && interval1.getTo() - interval2.getTo() > epsilon) {
            difference[0] = new Range(interval1.getFrom(), interval2.getFrom());
            difference[1] = new Range(interval2.getTo(), interval1.getTo());
        } else if (interval2.getFrom() - interval1.getFrom() > epsilon && interval2.getTo() - interval1.getTo() >= -epsilon && interval1.getTo() - interval2.getFrom() > epsilon) {
            difference[0] = new Range(interval1.getFrom(), interval2.getFrom());
        } else if (interval2.getFrom() - interval1.getFrom() > epsilon && interval2.getFrom() - interval1.getTo() >= -epsilon) {
            difference[0] = interval1;
        } else if (interval1.getFrom() - interval2.getFrom() >= -epsilon && interval1.getTo() - interval2.getTo() > epsilon) {
            difference[0] = new Range(interval2.getTo(), interval1.getTo());
        } else {
            return null;
        }
        return difference;
    }
}
