# 인터페이스 분리의 원칙: Interface Segregation Principle

인터페이스 분리 원칙이란 클라이언트가 자신이 이용하지 않는 메서드에 의존하지 않아야 한다는 원칙이다.

* 이 원칙은 큰 덩어리의 인터페이스들은 구체적이고 작은 단위들로 분리 시킴으로써 클라이언트들이 꼭 필요한 메서드들만 이용할 수 있게 한다.
* 이와 같은 작은 단위들을 역할 인터페이스라고 부른다.인터페이스 분리 원칙을 통해 시스템의 내부 의존성을 약화시켜 리팩토링, 수정, 재배포를 쉽게 할 수 있다.

## 용어 풀이

> 

단일 책임 원칙(SRP)과 인터페이스 분할 원칙(ISP)의 관계를 생각해보자.

여러 책임을 갖는 클래스가 있다. 이 클래스를 역할에 맞게 책임을 인터페이스 단위로 분리하였다.

이 경우 ISP에 만족 한다고 할 수 있다. 하지만 SRP에 만족하면 ISP 또한 만족되는가?

다음과 같은 경우를 생각해보자

```java
class PaymentController {
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void pay(@RequestBody CardPaymentDto.PaymentRequest req) {
        final CardPaymentService cardPaymentService = cardPaymentFactory.getType(req.getType());
        cardPaymentService.pay(req);
    }
}

public interface CardPaymentService {
    void pay(CardPaymentDto.PaymentRequest req);
}

public class ShinhanCardPaymentService implements CardPaymentService {
    @Override
    public void pay(CardPaymentDto.PaymentRequest req) {
        shinhanCardApi.pay(req);
    }

    @Override
    public void payOverseas(CardPaymentDto.PaymentRequest req) {
        shinhanCardApi.pay(req);
    }

}

public class WooriCardPaymentService implements CardPaymentService {

    private WooriCardApi wooriCardApi;

    @Override
    public void pay(CardPaymentDto.PaymentRequest req) {
        wooriCardApi.pay(paymentRequest);
    }

    @Override
    public void payOverseas(CardPaymentDto.PaymentRequest req) {
        // 우리 카드 결제는 해외 결제 기능이 없음...
    }
}

```

카드 결제 서비는 국내, 해외 결제가 가능하다. 그리고 신한카드는 국내, 해외 결제가 가능하나 우리카드는 국내만 결제가 가능하다.  
앞으로 추가될 파트너사 카드 지불 서비스는 해외, 국내 모두 가능하게 개발할 예정이다.
그러면 결제 서비스 인터페이스를 가지고 우리 은행 결제 서비스를 구현한다면, 사용하지 않는 메소드를 구현해야 함으로 ISP에 위배 된다.

## 의견 

> 
하지만 현재는 아니지만 향후 해외 결제가 가능하도록 우리 은행에서 계획하고 있다면 과연 ISP 위반을 해소 하기 위해서 별도의 국내 결제만
가능한 인터페이스 서비스를 만드는 것이 생산적인가? 효율적인가? 


## 추가예제
* ISP 위반 예제

```javascript
class Bird implements Animal {
  eat(): void {
    console.log('새가 음식을 먹었어요!');
  }
  sleep(): void {
    console.log('Zzzzzz....');
  }
  cry(): void {
    console.log('짹짹!!');
  }
  fly(): void {
    console.log('새가 하늘을 날았어요!');
  }
}

class Human implements Animal {
  eat(): void {
    console.log('아 배부르다');
  }
  sleep(): void {
    console.log('Zzzzzz....');
  }
  cry(): void {
    console.log('ㅠㅠㅠ');
  }
  fly(): void {
    // ????
  }
}

```

```javascript
interface Animal {
  eat(): void;
  sleep(): void;
  cry(): void;
}

interface FlyableAnimal extends Animal {
  fly(): void;
}

class Bird implements FlyableAnimal {
  eat(): void {
    console.log('새가 음식을 먹었어요!');
  }
  sleep(): void {
    console.log('Zzzzzz....');
  }
  cry(): void {
    console.log('짹짹!!');
  }
  fly(): void {
    console.log('새가 하늘을 날았어요!');
  }
}

class Human implements Animal {
  eat(): void {
    console.log('아 배부르다');
  }
  sleep(): void {
    console.log('Zzzzzz....');
  }
  cry(): void {
    console.log('ㅠㅠㅠ');
  }
  // fly() 메서드를 구현하지 않는다. ISP 준수!
}

```


