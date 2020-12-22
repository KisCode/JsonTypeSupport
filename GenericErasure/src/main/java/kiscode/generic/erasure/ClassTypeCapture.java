package kiscode.generic.erasure;


public class ClassTypeCapture<T> {
    //通过引入类型标签来补偿擦除
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object obj) {
        //判定指定的 Object 是否与此 Class 所表示的对象兼容
        //等于 obj instance kind
        return kind.isInstance(obj);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));

        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
    }
}


class Building {
}

class House extends Building {
}