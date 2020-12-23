package kiscode.generic.erasure;


class ClassAsFactory<T> {
    T x;

    public ClassAsFactory(Class<T> kind) {
        try {
            //通过newInstance()可创建泛型的对象，但由于泛型类型类是否有无参构造函数是未知的，所以此处可能因找不到对应构造函数发生异常
            x = kind.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}