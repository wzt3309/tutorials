/**
 * Closures
 *
 * @author xingguan.wzt* @date 2020/09/24
 */

item = 1
println({ item++ }())
println(item)

// it 是隐含的变量
// 不同于普通方法 闭包总返回值
println({println it}())
println({it -> println it})
println({it}(1))

// 多参数和确定类型
println({ String x, int y ->
    println "hey ${x} the value is ${y}"
}("1", 2))

// 闭包是 groovy.lang.Closure的实例
def aListener = {println('done')}
assert aListener instanceof Closure

// 可以当成普通方法一样调用闭包 也可以用 call(...)
def isOdd = {i -> i % 2 != 0}
assert isOdd(3)
assert isOdd.call(3)

def isEven = {it % 2 == 0}
assert isEven(4)
assert isEven.call(4)

// 显示申明无参闭包
def magicNumber = {-> 42}
//magicNumber(1) // call fail

// 闭包使用默认参数
def closureWithDefaultVal = {a, b=1 -> a + b}
assert closureWithDefaultVal(2) == 3

// 闭包使用可变参数
def concat = {...args -> args.join('')}
assert concat("a", "b", "c") == "abc"
def concat2 = {i, ...args -> args.join('') << i}
assert concat2(1, "a", "b").toString() == "ab1"

// this/owner/delegate 比较复杂 直接看 https://groovy-lang.org/closures.html#_delegation_strategy

// 闭包内的属性会隐示使用 delegate 的属性
class Person {
    String name
}
def p = new Person(name: "Jone")
def cl = {x -> "${name.toUpperCase()}, ${x}"}
cl.delegate = p
println(cl())

// 闭包内的属性解析默认策略：优先使用owner的 如果owner不存在使用 delegate

// 闭包本身是一个匿名内部类，内部的动作与 thisObject/owner/delegate相关
class Bear {
    String name
    int age
    // 看成
    // fetchAge = new Closure(owner, this) delegate = owner
    // call() = Object call() {return owner.age}
    // groovy的脚本本身就是一个 类 看起来一些游离的变量定义 其实都是这个"看不见"类的内部属性
    // 通过切换代理方式可以
    // call() {return delegate.age}
    def fetchAge = {age}
}

class Panda {
    int age
}

def bear = new Bear(name: "Big A", age: 10)
def panda = new Panda(age: 12)

def bcl = bear.fetchAge
println(bcl()) // 10
//bcl.owner = panda // Closure 有属性 owner/thisObject/delegate 但是只有delegate才有setter
bcl.delegate = panda
println(bcl()) // 10

bcl.resolveStrategy = Closure.DELEGATE_FIRST
println(bcl()) // 12

// GString 中的闭包
def name = "Jone"
def norGStr = "name=$name" // $name 指向的是 "Jone" 这个内存对象
def clGStr = "name=${-> name}" // 闭包

assert norGStr == "name=Jone"
assert clGStr == "name=Jone"

name = "Jon2" // name引用的对象变了，但是norGStr中 $Name 还是指向 "Jone" 这个对象(本身未发生改变)
assert norGStr == "name=Jone"
assert clGStr == "name=Jon2" // GString 调用 toString() 求值, 执行内部闭包 ${-> name}.toString()

// DSL
// 具体见DSL的章节
def select(action) {
    [on:{what -> [then: { a ->
        action(what(a))
    }]}]
}
def fun1 = {a -> a + 2}
def fun2 = {a -> a * 3}
assert select(fun1).on(fun2).then(1) == (select fun1 on fun2 then 1) // 5