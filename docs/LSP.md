# 리스코브 치환의 원칙: The Liskov Substitution Principle

리스코브 치환의 원칙 The Liskov Substitution Principle 핵심 키워드는 다음과 같습니다.

* 상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로 동작해야 한다.

## 용어 풀이

> 



앞서 설명한  OCP는 추상화와 다형성(상속)을 이용해서 구현했는데, LSP는 OCP를 받처주는 다형성에 관한 원칙을 제공합니다.


* 상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로 동작해야한다.


리스코프 치환 원칙을 지키지 않으면 기능을 확장하기 어렵게 된다.

유명한 예제(오리와 오리장난감)
* 오리장난감은 오리를 대체할 수 없다. 단 오리장남감을 사용하는 사용자가 기대하는 기능에 따라 오리장난감은 LSP 위반이 아닐 수 있다.
* https://siyoon210.tistory.com/156

## 추가 예제

* 위반예제

```javascript
class Rectangle {
  protected _width: number = -1;
  protected _height: number = -1;

  public get width() {
    return this._width;
  }
  public set width(w: number) {
    this._width = w;
  }

  public get height() {
    return this._height;
  }
  public set height(h: number) {
    this._height = h;
  }

  public get area() {
    return this._width * this._height;
  }
}

class Square extends Rectangle {
  public set width(w: number) {
    this._width = w;
    this._height = w;
  }

  public set height(h: number) {
    this._width = h;
    this._height = h;
  }
}

const rec: Rectangle = new Rectangle();
rec.width = 3;
rec.height = 4;

console.log(rec.area === 12); // true

const rec2: Rectangle = new Square();
rec2.width = 3;
rec2.height = 4;

console.log(rec.area === 12); // false
```

* 해결예제
```javascript
// Shape 인터페이스는 넓이를 구할 수 있는 것(기하학 관점의 면)이라고 가정한다.
interface Shape {
  readonly area: number;
}

class Rectangle implements Shape {
  constructor(public width: number, public height: number) {}

  public get area() {
    return this.width * this.height;
  }
}

class Square implements Shape {
  constructor(public width: number) {}

  public get area() {
    return this.width ** 2;
  }
}

const rec: Shape = new Rectangle(3, 4);
console.log(rec.area); // 12

const sq: Shape = new Square(4);
console.log(sq.area);  // 16
```


