package Main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import DSA.Sorting.*;

public class SortingTest {
    
    // Test veri boyutları
    private static final int SMALL_SIZE = 10;
    private static final int MEDIUM_SIZE = 1000;
    private static final int LARGE_SIZE = 10000;
    
    // Maksimum rastgele sayı değeri
    private static final int MAX_VALUE = 10000;
    
    public static void main(String[] args) {
        // Test edilecek sıralama algoritmaları
        GTUSorter[] sorters = {
            new GTUQuickSort(),
            // Diğer sıralama algoritmalarını buraya ekleyebilirsiniz
            // new GTUInsertionSort(),
            // new GTUMergeSort(),
            // new GTUHeapSort(),
            // vb.
        };
        
        // Algoritmaları test et
        for (GTUSorter sorter : sorters) {
            System.out.println("\n========== " + sorter.getClass().getSimpleName() + " ==========");
            
            // Küçük boyutlu dizi testi
            testSorter(sorter, SMALL_SIZE, "Küçük Dizi");
            
            // Orta boyutlu dizi testi
            testSorter(sorter, MEDIUM_SIZE, "Orta Dizi");
            
            // Büyük boyutlu dizi testi
            testSorter(sorter, LARGE_SIZE, "Büyük Dizi");
            
            // Sıralı dizi testi
            testSortedArray(sorter, MEDIUM_SIZE, "Sıralı Dizi");
            
            // Ters sıralı dizi testi
            testReverseSortedArray(sorter, MEDIUM_SIZE, "Ters Sıralı Dizi");
            
            // Aynı elemanlı dizi testi
            testSameElementsArray(sorter, MEDIUM_SIZE, "Aynı Elemanlı Dizi");
            
            // String dizisi testi
            testStringArray(sorter, SMALL_SIZE, "String Dizisi");
        }
    }
    
    /**
     * Belirtilen sıralama algoritmasını rastgele Integer dizisi üzerinde test eder
     */
    private static void testSorter(GTUSorter sorter, int size, String testName) {
        System.out.println("\n--- " + testName + " (" + size + " eleman) ---");
        
        // Rastgele dizi oluştur
        Integer[] array = generateRandomArray(size);
        
        // Orijinal diziyi kopyala (doğrulama için)
        Integer[] arrayCopy = Arrays.copyOf(array, array.length);
        
        // Diziyi yazdır (küçük diziler için)
        if (size <= SMALL_SIZE) {
            System.out.println("Sıralamadan önce: " + Arrays.toString(array));
        }
        
        // Sıralama süresini ölç
        long startTime = System.nanoTime();
        sorter.sort(array,Comparator.naturalOrder());
        long endTime = System.nanoTime();
        
        // Diziyi yazdır (küçük diziler için)
        if (size <= SMALL_SIZE) {
            System.out.println("Sıralamadan sonra: " + Arrays.toString(array));
        }
        
        // Sıralama süresini yazdır
        System.out.println("Sıralama süresi: " + (endTime - startTime) / 1_000_000.0 + " ms");
        
        // Sıralamanın doğruluğunu kontrol et
        Arrays.sort(arrayCopy);
        boolean isSorted = Arrays.equals(array, arrayCopy);
        System.out.println("Sıralama doğru mu: " + isSorted);
        
        if (!isSorted && size <= SMALL_SIZE) {
            System.out.println("Beklenen: " + Arrays.toString(arrayCopy));
            System.out.println("Gerçek: " + Arrays.toString(array));
        }
    }
    
    /**
     * Sıralı bir dizi üzerinde test eder
     */
    private static void testSortedArray(GTUSorter sorter, int size, String testName) {
        System.out.println("\n--- " + testName + " (" + size + " eleman) ---");
        
        // Sıralı dizi oluştur
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        
        // Diziyi yazdır (küçük diziler için)
        if (size <= SMALL_SIZE) {
            System.out.println("Sıralamadan önce: " + Arrays.toString(array));
        }
        
        // Sıralama süresini ölç
        long startTime = System.nanoTime();
        sorter.sort(array,Comparator.naturalOrder());
        long endTime = System.nanoTime();
        
        // Diziyi yazdır (küçük diziler için)
        if (size <= SMALL_SIZE) {
            System.out.println("Sıralamadan sonra: " + Arrays.toString(array));
        }
        
        // Sıralama süresini yazdır
        System.out.println("Sıralama süresi: " + (endTime - startTime) / 1_000_000.0 + " ms");
        
        // Sıralamanın doğruluğunu kontrol et
        boolean isSorted = isSorted(array, Comparator.naturalOrder());
        System.out.println("Sıralama doğru mu: " + isSorted);
    }
    
    /**
     * Ters sıralı bir dizi üzerinde test eder
     */
    private static void testReverseSortedArray(GTUSorter sorter, int size, String testName) {
        System.out.println("\n--- " + testName + " (" + size + " eleman) ---");
        
        // Ters sıralı dizi oluştur
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i - 1;
        }
        
        // Diziyi yazdır (küçük diziler için)
        if (size <= SMALL_SIZE) {
            System.out.println("Sıralamadan önce: " + Arrays.toString(array));
        }
        
        // Sıralama süresini ölç
        long startTime = System.nanoTime();
        sorter.sort(array, Comparator.naturalOrder());
        long endTime = System.nanoTime();
        
        // Diziyi yazdır (küçük diziler için)
        if (size <= SMALL_SIZE) {
            System.out.println("Sıralamadan sonra: " + Arrays.toString(array));
        }
        
        // Sıralama süresini yazdır
        System.out.println("Sıralama süresi: " + (endTime - startTime) / 1_000_000.0 + " ms");
        
        // Sıralamanın doğruluğunu kontrol et
        boolean isSorted = isSorted(array, Comparator.naturalOrder());
        System.out.println("Sıralama doğru mu: " + isSorted);
    }
    
    /**
     * Aynı elemanlı bir dizi üzerinde test eder
     */
    private static void testSameElementsArray(GTUSorter sorter, int size, String testName) {
        System.out.println("\n--- " + testName + " (" + size + " eleman) ---");
        
        // Aynı elemanlı dizi oluştur
        Integer[] array = new Integer[size];
        Arrays.fill(array, 42);
        
        // Diziyi yazdır (küçük diziler için)
        if (size <= SMALL_SIZE) {
            System.out.println("Sıralamadan önce: " + Arrays.toString(array));
        }
        
        // Sıralama süresini ölç
        long startTime = System.nanoTime();
        sorter.sort(array, Comparator.naturalOrder());
        long endTime = System.nanoTime();
        
        // Diziyi yazdır (küçük diziler için)
        if (size <= SMALL_SIZE) {
            System.out.println("Sıralamadan sonra: " + Arrays.toString(array));
        }
        
        // Sıralama süresini yazdır
        System.out.println("Sıralama süresi: " + (endTime - startTime) / 1_000_000.0 + " ms");
        
        // Sıralamanın doğruluğunu kontrol et
        boolean isSorted = isSorted(array, Comparator.naturalOrder());
        System.out.println("Sıralama doğru mu: " + isSorted);
    }
    
    /**
     * String dizisi üzerinde test eder
     */
    private static void testStringArray(GTUSorter sorter, int size, String testName) {
        System.out.println("\n--- " + testName + " (" + size + " eleman) ---");
        
        // Rastgele String dizisi oluştur
        String[] array = generateRandomStringArray(size);
        
        // Orijinal diziyi kopyala (doğrulama için)
        String[] arrayCopy = Arrays.copyOf(array, array.length);
        
        // Diziyi yazdır
        System.out.println("Sıralamadan önce: " + Arrays.toString(array));
        
        // Sıralama süresini ölç
        long startTime = System.nanoTime();
        sorter.sort(array, Comparator.naturalOrder());
        long endTime = System.nanoTime();
        
        // Diziyi yazdır
        System.out.println("Sıralamadan sonra: " + Arrays.toString(array));
        
        // Sıralama süresini yazdır
        System.out.println("Sıralama süresi: " + (endTime - startTime) / 1_000_000.0 + " ms");
        
        // Sıralamanın doğruluğunu kontrol et
        Arrays.sort(arrayCopy);
        boolean isSorted = Arrays.equals(array, arrayCopy);
        System.out.println("Sıralama doğru mu: " + isSorted);
        
        if (!isSorted) {
            System.out.println("Beklenen: " + Arrays.toString(arrayCopy));
            System.out.println("Gerçek: " + Arrays.toString(array));
        }
    }
    
    /**
     * Belirtilen boyutta rastgele Integer dizisi oluşturur
     */
    private static Integer[] generateRandomArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(MAX_VALUE);
        }
        
        return array;
    }
    
    /**
     * Belirtilen boyutta rastgele String dizisi oluşturur
     */
    private static String[] generateRandomStringArray(int size) {
        String[] array = new String[size];
        Random random = new Random();
        
        for (int i = 0; i < size; i++) {
            // 3-8 karakter uzunluğunda rastgele string oluştur
            int length = random.nextInt(6) + 3;
            StringBuilder sb = new StringBuilder();
            
            for (int j = 0; j < length; j++) {
                char c = (char) (random.nextInt(26) + 'a');
                sb.append(c);
            }
            
            array[i] = sb.toString();
        }
        
        return array;
    }
    
    /**
     * Dizinin sıralı olup olmadığını kontrol eder
     */
    private static <T> boolean isSorted(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
