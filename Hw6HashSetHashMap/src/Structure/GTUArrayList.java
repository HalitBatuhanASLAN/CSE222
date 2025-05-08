package Structure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GTUArrayList<T> implements Iterable<T>
{
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    public GTUArrayList(){elements = new Object[DEFAULT_CAPACITY];}

    public void add(T element)
    {
        ensureCapacity();
        elements[size++] = element;
    }



    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) elements[index];
    }
/*
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index); // İndeksi kontrol et
        T removedElement = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved); // Elemanları kaydır
        }
        elements[--size] = null; // Son elemanı null yap
        return removedElement;
    }
*/
    public int size(){return size;}

    public boolean isEmpty() {return size == 0;}

    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    private void ensureCapacity()
    {
        if (size == elements.length)
        {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

     @Override
    public Iterator<T> iterator() {
        return new GTUArrayListIterator();
    }

    // Özel bir Iterator iç sınıfı
    private class GTUArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) elements[currentIndex++];
        }
    }
}
