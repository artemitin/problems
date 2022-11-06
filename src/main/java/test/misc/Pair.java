package test.misc;

class Pair {
    public Integer first;
    public Integer second;

    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "{" + first + ", " + second + '}';
    }
}
