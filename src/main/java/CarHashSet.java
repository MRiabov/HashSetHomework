public class CarHashSet implements CarSet{
    private int size=0;
    public static final int INITIAL_CAPACITY=16;
    private Entry[] array= new Entry[INITIAL_CAPACITY];
    private static final double LOAD_FACTOR=0.75;

    @Override
    public boolean add(Car car) {
        if (size>=array.length*LOAD_FACTOR) increaseArray();
        boolean added=add(car, array);
        if (added) size++;
        return added;
    }

    private boolean add(Car car, Entry[] entries){
        int position = getElementPosition(car,entries.length);
        if (entries[position]==null){
            Entry entry = new Entry(car,null);
            entries[position]=entry;
            return true;
        } else {
            Entry existingElement = entries[position];
            while (true){
                if (existingElement.value.equals(car)) return false;
                else if (existingElement.next==null) {
                    existingElement.next = new Entry(car,null);
                    return true;
                } else existingElement=existingElement.next;
            }
        }
    }

    private void increaseArray(){
        Entry[] newArray = new Entry[array.length*2];
        for (Entry entry : array) {
            Entry existingElement=entry;
            while (existingElement!=null){
                add(existingElement.value,newArray);
                existingElement=existingElement.next;
            }
        }
        array=newArray;
    }

    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car,array.length);
                if (array[position]==null) return false;
                else if (array[position].value.equals(car)){
                    Entry secondLast=array[position];
                    Entry last=secondLast.next;
                    if (secondLast.value.equals(car)) {
                        array[position]=last;
                        return true;
                    }else while (last!=null){
                        if (last.value.equals(car)) {
                        secondLast.next=last.next;
                        return true;
                    } else {
                        secondLast=last;
                        last=last.next;
                    }

                }//array[position]=existingElement.next;
            }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size=0;
    }

    private int getElementPosition(Car car, int arrayLength){
        return Math.abs(car.hashCode()%arrayLength);
    }

    private static class Entry{
        private Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }

    }
}
