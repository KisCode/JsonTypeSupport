package kiscode.generic.erasure;

//工厂接口，强制工厂实现create方法，这个方法用来产生对象
interface FactoryI<T> {
    T create();
}

//产生Integer对象的工厂,使用直接实现接口的方式来实现
class IntegerFactory implements FactoryI<Integer> {
    public Integer create() {
        return new Integer(0);
    }
}

//使用内部类的方式达到对接口的实现
class Widget {
    public static class Factory implements FactoryI<Widget> {
        public Widget create() {
            return new Widget();
        }
    }
}

//泛型类型通过接收一个工厂对象来创建T类型的对象
class Foo<T> {
    private T x;

    public <F extends FactoryI<T>> Foo(F factory) {
        x = factory.create();
    }

    public T getX() {
        return x;
    }

}

class FooTest {
    public static void main(String[] args) {
        Foo<Integer> foo = new Foo<>(new IntegerFactory());
        System.out.println(foo.getClass()+ "----" + foo.getX().getClass());


        Foo<Widget> widgetFoo = new Foo<>(new Widget.Factory());
        System.out.println(widgetFoo.getClass() + "----" + widgetFoo.getClass());
    }
}

