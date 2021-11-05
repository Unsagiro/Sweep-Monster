public class Pair {
    Integer key;
    Integer value;
    public Pair(Integer key, Integer value){
        this.key = key;
        this.value = value;
    }

    public Integer getKey(){
        return key;
    }

    public Integer getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair p = (Pair) o;
        if (key != p.key) return false;
        if (value != p.value) return false;

        return true;

    }

    @Override
    public int hashCode(){
        int result = key;
        result = result * 31 + value;
        return result;
    }

}
