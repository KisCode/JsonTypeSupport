package kiscode.generic.erasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 创建ArrayList泛型数组
 * Author: keno
 * CreateDate: 2020/12/21 22:25
 */

public class ListOfGenerics<T> {
    //通过
    private List<T> array = new ArrayList<>();

    public void add(T item) {
        array.add(item);
    }

    public T get(int index) {
        return array.get(index);
    }

    public List<T> getData() {
        return array;
    }
}